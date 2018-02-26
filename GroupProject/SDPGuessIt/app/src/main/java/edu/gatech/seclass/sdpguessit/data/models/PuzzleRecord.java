package edu.gatech.seclass.sdpguessit.data.models;

import com.orm.SugarRecord;

import java.util.List;

public class PuzzleRecord  extends SugarRecord<PuzzleRecord> {
    Player player;
    Puzzle puzzle;
    int priveValue;
    List<Character> guessedLetters;
    int remainingGuessCount;
    boolean isComplete;
}
