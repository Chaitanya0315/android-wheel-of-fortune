package edu.gatech.seclass.sdpguessit.data.models;

import com.orm.SugarRecord;

import java.util.List;

public class TournamentRecord extends SugarRecord<TournamentRecord> {
    Player player;
    Tournament tournament;
    List<Puzzle> puzzles;
    boolean isComplete;
}
