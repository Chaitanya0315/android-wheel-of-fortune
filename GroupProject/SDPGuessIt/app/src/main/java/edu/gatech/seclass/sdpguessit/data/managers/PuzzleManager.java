package edu.gatech.seclass.sdpguessit.data.managers;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.seclass.sdpguessit.data.models.Player;
import edu.gatech.seclass.sdpguessit.data.models.Puzzle;
import edu.gatech.seclass.sdpguessit.data.models.PuzzleRecord;

public class PuzzleManager {
    public PuzzleManager() {
    }

    public Long createNewPuzzle(Player player, int maxGuesses, String mPhrase) {
        Puzzle puzzle = new Puzzle(player, mPhrase, maxGuesses);
        puzzle.save();
        return puzzle.getId();
    }

    public List<Puzzle> getPuzzles() {
        return Puzzle.listAll(Puzzle.class);
    }

    public List<PuzzleRecord> getPuzzleRecords() {
        return PuzzleRecord.listAll(PuzzleRecord.class);
    }

    public Puzzle getPuzzle(long id) {
        return Puzzle.findById(Puzzle.class, id);
    }

    public PuzzleRecord addNewPuzzleRecord(Player player, Puzzle puzzle) {
        PuzzleRecord puzzleRecord = new PuzzleRecord(player, puzzle, 0, new ArrayList<Character>(0), puzzle.getMaxGuesses(), false);
        return puzzleRecord;
    }
}
