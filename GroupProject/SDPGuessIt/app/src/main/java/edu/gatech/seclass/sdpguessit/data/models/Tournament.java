package edu.gatech.seclass.sdpguessit.data.models;

import com.orm.SugarRecord;

import java.util.List;

public class Tournament extends SugarRecord<Tournament> {
    Player player;
    String name;
    List<Puzzle> puzzles;
}
