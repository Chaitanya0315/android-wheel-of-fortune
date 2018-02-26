package edu.gatech.seclass.sdpguessit.data.models;

import com.orm.SugarRecord;

public class Puzzle extends SugarRecord<Puzzle> {
    Player player;
    String phrase;
    int maxGuesses;
}
