package edu.gatech.seclass.sdpguessit.data.models;

import com.orm.SugarRecord;

public class Puzzle extends SugarRecord<Puzzle> {
    Player player;
    String phrase;
    int maxGuesses;

    public Puzzle() {
    }

    public Puzzle(Player player, String phrase, int maxGuesses) {
        this.player = player;
        this.phrase = phrase;
        this.maxGuesses = maxGuesses;
    }

    @Override
    public String toString() {
        return phrase + " (" + player.userName + ")";
    }

    public String getPhrase(){
        return phrase;
    }

    public int getMaxGuesses(){
        return maxGuesses;
    }
}
