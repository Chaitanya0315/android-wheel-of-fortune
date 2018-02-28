package edu.gatech.seclass.sdpguessit.data.managers;

import com.orm.StringUtil;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.seclass.sdpguessit.data.models.Player;
import edu.gatech.seclass.sdpguessit.data.models.Puzzle;
import edu.gatech.seclass.sdpguessit.data.models.Tournament;
import edu.gatech.seclass.sdpguessit.data.models.TournamentRecord;

public class TournamentManager {
    public TournamentManager() {
    }

    public Long createNewTournament(Player player, String name, List<Puzzle> puzzles) {
        List<Long> ids = new ArrayList<>(puzzles.size());
        for(Puzzle puzzle : puzzles){
            ids.add(puzzle.getId());
        }

        Tournament tournament = new Tournament(player, name, StringUtils.join(ids, ','));
        tournament.save();
        return tournament.getId();
    }

    public List<Tournament> getTournaments() {
        return Tournament.listAll(Tournament.class);
    }

    public List<Tournament> getPlayableTournamentsForUser(Player player) {
        // TODO: implement proper filtering using playertttttt
        return getTournaments();
    }

    public List<TournamentRecord> getTournamentRecords() {
        return TournamentRecord.listAll(TournamentRecord.class);
    }

    public Tournament getTournament(long id) {
        return Tournament.findById(Tournament.class, id);
    }

    public TournamentRecord addNewTournamentRecord(Player player, Tournament tournament) {
        TournamentRecord tournamentRecord = null;
        for (TournamentRecord tr : getTournamentRecords()) {
            if(tr.getTournament().getId() == tournament.getId() && tr.getPlayer().getId() == player.getId()){
                tournamentRecord = tr;
                break;
            }
        }

        if(tournamentRecord == null) {
            tournamentRecord = new TournamentRecord(player, tournament, tournament.getPuzzles(), false);
            tournamentRecord.save();
        }

        return tournamentRecord;
    }
}
