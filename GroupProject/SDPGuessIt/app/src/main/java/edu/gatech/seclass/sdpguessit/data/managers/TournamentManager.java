package edu.gatech.seclass.sdpguessit.data.managers;

import android.text.TextUtils;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.seclass.sdpguessit.data.models.Player;
import edu.gatech.seclass.sdpguessit.data.models.Puzzle;
import edu.gatech.seclass.sdpguessit.data.models.Tournament;
import edu.gatech.seclass.sdpguessit.data.models.TournamentRecord;

public class TournamentManager {
    public enum Filter {
        none,
        not_played_yet,
        played
    }

    public TournamentManager() {
    }

    public Long createNewTournament(Player player, String name, List<Puzzle> puzzles) {
        List<Long> ids = new ArrayList<>(puzzles.size());
        for (Puzzle puzzle : puzzles) {
            ids.add(puzzle.getId());
        }

        Tournament tournament = new Tournament(player, name, StringUtils.join(ids, ','));
        tournament.save();
        return tournament.getId();
    }

    public List<Tournament> getTournaments() {
        return Tournament.listAll(Tournament.class);
    }

    public boolean doesTournamentNameExist(String name){
        for(Tournament tournament : getTournaments()){
            if(TextUtils.equals(tournament.getName().toLowerCase(), name.toLowerCase())){
                return true;
            }
        }

        return false;
    }

    public List<Tournament> getPlayableTournamentsForUser(Player player, TournamentManager.Filter filter) {
        List<Tournament> filtered = new ArrayList<>();

        for (Tournament tournament : getTournaments()) {
            if (tournament.getPlayer().getId() != player.getId()) {
                TournamentRecord tournamentRecord = getTournamentRecord(player, tournament);
                if (tournamentRecord == null || !tournamentRecord.isComplete()) {
                    boolean ownedPuzzle = false;
                    for (Puzzle puzzle : tournament.getPuzzles()) {
                        if (puzzle.getPlayer().getId() == player.getId()) {
                            ownedPuzzle = true;
                            break;
                        }
                    }

                    if (!ownedPuzzle) {
                        switch(filter){
                            default:
                            case none:
                                filtered.add(tournament);
                                break;
                            case not_played_yet:
                                if(tournamentRecord == null) {
                                    filtered.add(tournament);
                                }
                                break;
                            case played:
                                if(tournamentRecord != null) {
                                    filtered.add(tournament);
                                }
                                break;
                        }
                    }
                }
            }
        }

        return filtered;
    }

    public List<TournamentRecord> getTournamentRecords() {
        return TournamentRecord.listAll(TournamentRecord.class);
    }

    public List<TournamentRecord> getTournamentRecordsForPlayer(Player player) {
        List<TournamentRecord> tournamentRecords = new ArrayList<>();

        for(TournamentRecord tournamentRecord : getTournamentRecords()){
            if(tournamentRecord.getPlayer().getId() == player.getId()){
                tournamentRecords.add(tournamentRecord);
            }
        }

        return tournamentRecords;
    }

    public List<TournamentRecord> getTournamentRecordsForTournament(Tournament tournament) {
        List<TournamentRecord> tournamentRecords = new ArrayList<>();

        for(TournamentRecord tournamentRecord : getTournamentRecords()){
            if(tournamentRecord.getTournament().getId() == tournament.getId()){
                tournamentRecords.add(tournamentRecord);
            }
        }

        return tournamentRecords;
    }

    public Tournament getTournament(long id) {
        return Tournament.findById(Tournament.class, id);
    }

    public TournamentRecord getTournamentRecord(Player player, Tournament tournament) {
        TournamentRecord tournamentRecord = null;
        for (TournamentRecord tr : getTournamentRecords()) {
            if (tr.getTournament().getId() == tournament.getId() && tr.getPlayer().getId() == player.getId()) {
                tournamentRecord = tr;
                break;
            }
        }

        return tournamentRecord;
    }

    public TournamentRecord addNewTournamentRecord(Player player, Tournament tournament) {
        TournamentRecord tournamentRecord = null;
        for (TournamentRecord tr : getTournamentRecords()) {
            if (tr.getTournament().getId() == tournament.getId() && tr.getPlayer().getId() == player.getId()) {
                tournamentRecord = tr;
                break;
            }
        }

        if (tournamentRecord == null) {
            tournamentRecord = new TournamentRecord(player, tournament, tournament.getPuzzles(), false);
        }

        return tournamentRecord;
    }
}
