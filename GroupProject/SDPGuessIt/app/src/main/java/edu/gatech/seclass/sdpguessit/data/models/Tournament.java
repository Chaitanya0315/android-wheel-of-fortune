package edu.gatech.seclass.sdpguessit.data.models;

import com.orm.SugarRecord;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class Tournament extends SugarRecord<Tournament> {
    Player player;
    String name;
    String puzzlesCSV;

    public Tournament() {
    }

    public Tournament(Player player, String name, String puzzlesCSV) {
        this.player = player;
        this.name = name;
        this.puzzlesCSV = puzzlesCSV;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public List<Puzzle> getPuzzles() {
        List<Puzzle> puzzles = new ArrayList<>();
        for(String id : StringUtils.split(puzzlesCSV, ',')){
            puzzles.add(Puzzle.findById(Puzzle.class, Long.valueOf(id)));
        }
        return puzzles;
    }

    public Player getPlayer() {
        return player;
    }
}
