package edu.gatech.seclass.sdpguessit.data.models;

import com.orm.SugarRecord;

import java.util.List;

public class Tournament extends SugarRecord<Tournament> {
    Player player;
    String name;
    List<Puzzle> puzzles;

    public Tournament() {
    }

    public Tournament(Player player, String name, List<Puzzle> puzzles) {
        this.player = player;
        this.name = name;
        this.puzzles = puzzles;
    }

    @Override
    public String toString() {
        return name + " (" + player.userName + ")";
    }
}
