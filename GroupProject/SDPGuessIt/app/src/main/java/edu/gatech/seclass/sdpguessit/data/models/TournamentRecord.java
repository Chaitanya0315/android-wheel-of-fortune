package edu.gatech.seclass.sdpguessit.data.models;

import com.orm.SugarRecord;

import java.util.List;

public class TournamentRecord extends SugarRecord<TournamentRecord> {
    Player player;
    Tournament tournament;
    List<Puzzle> puzzles;
    boolean isComplete;

    public TournamentRecord(Player player, Tournament tournament, List<Puzzle> puzzles, boolean isComplete) {
        this.player = player;
        this.tournament = tournament;
        this.puzzles = puzzles;
        this.isComplete = isComplete;
    }

    public TournamentRecord() {

    }

    public Tournament getTournament() {
        return tournament;
    }

    public Player getPlayer() {
        return player;
    }

    public void markComplete(){
        isComplete = true;
    }

    public boolean isComplete() {
        return isComplete;
    }
}
