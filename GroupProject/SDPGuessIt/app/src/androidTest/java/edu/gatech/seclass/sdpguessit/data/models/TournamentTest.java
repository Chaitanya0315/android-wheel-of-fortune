package edu.gatech.seclass.sdpguessit.data.models;
import edu.gatech.seclass.sdpguessit.MainActivity;
import edu.gatech.seclass.sdpguessit.data.managers.PlayerManager;
import edu.gatech.seclass.sdpguessit.data.managers.PuzzleManager;
import edu.gatech.seclass.sdpguessit.data.managers.TournamentManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;


/**
 * Created by SAHAN_DE_SILVA on 2/28/2018.
 */
@RunWith(AndroidJUnit4.class)
public class TournamentTest{

    TournamentManager tournamentM;
    PuzzleManager puzzleM;
    PlayerManager playerM;
    Player player;

    public TournamentTest() {
    }

    @Before
    public void setUp() {
        playerM = new PlayerManager(InstrumentationRegistry.getTargetContext());
        tournamentM = new TournamentManager();
        puzzleM = new PuzzleManager();

    }

    @After
    public void tearDown() {
        tournamentM = null;
        puzzleM = null;
        playerM = null;
    }

    @Test
    public void test1() {
        Long puzzleID1 = puzzleM.createNewPuzzle(player,8,"Georgia Tech");
        Long puzzleID2 = puzzleM.createNewPuzzle(player,8,"Washington DC");
        Long puzzleID3 = puzzleM.createNewPuzzle(player,8,"United States of America");
        Long puzzleID4 = puzzleM.createNewPuzzle(player,8,"Florida");
        Long puzzleID5 = puzzleM.createNewPuzzle(player,8,"Georgia");
        Long puzzleID6 = puzzleM.createNewPuzzle(player,8,"Seattle");

        List<Puzzle> puzzleList = new ArrayList<>();
        puzzleList.add(puzzleM.getPuzzle(puzzleID1));
        puzzleList.add(puzzleM.getPuzzle(puzzleID2));
        puzzleList.add(puzzleM.getPuzzle(puzzleID3));
        puzzleList.add(puzzleM.getPuzzle(puzzleID4));
        puzzleList.add(puzzleM.getPuzzle(puzzleID5));

        playerM.addNewPlayer("Team11", "Team11", "Team11", "Team11@gmail.com");

        tournamentM.createNewTournament(player,"Tournamnet1_Player1",puzzleList);

        assertEquals(playerM.doesUsenameExist("Team11"),true);
    }
}
