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
    public void testDBTournamentCreate() {

        playerM.addNewPlayer("Tom", "Bush", "TomB", "TomB@gmail.com");
        Player player = playerM.getPlayerByUsername("TomB");

        Long puzzleID1 = puzzleM.createNewPuzzle(player,8,"Clalifornia");
        Long puzzleID2 = puzzleM.createNewPuzzle(player,8,"New Jersey");
        Long puzzleID3 = puzzleM.createNewPuzzle(player,8,"New York");
        Long puzzleID4 = puzzleM.createNewPuzzle(player,8,"Florida");
        Long puzzleID5 = puzzleM.createNewPuzzle(player,8,"Georgia");
        Long puzzleID6 = puzzleM.createNewPuzzle(player,8,"Seattle");

        List<Puzzle> puzzleList = new ArrayList<>();
        puzzleList.add(puzzleM.getPuzzle(puzzleID1));
        puzzleList.add(puzzleM.getPuzzle(puzzleID2));
        puzzleList.add(puzzleM.getPuzzle(puzzleID3));
        puzzleList.add(puzzleM.getPuzzle(puzzleID4));
        puzzleList.add(puzzleM.getPuzzle(puzzleID5));

        Long id = tournamentM.createNewTournament(player,"States",puzzleList);
        assertEquals(tournamentM.getTournament(id).name,"States");
    }


    /*
      Would need to change this TC after fixing the issue that Tournament name should be unique,
      which was detected by using this test and created a github issue.
     */
    @Test
    public void testIfDuplicateTournamentNamesIgnored() {

        playerM.addNewPlayer("Tom", "Bush", "TomB", "TomB@gmail.com");
        Player player = playerM.getPlayerByUsername("TomB");

        Long puzzleID1 = puzzleM.createNewPuzzle(player,8,"United States of America");
        Long puzzleID2 = puzzleM.createNewPuzzle(player,8,"United KingDom");
        Long puzzleID3 = puzzleM.createNewPuzzle(player,8,"France");
        Long puzzleID4 = puzzleM.createNewPuzzle(player,8,"Germany");
        Long puzzleID5 = puzzleM.createNewPuzzle(player,8,"Japan");

        Long puzzleID6 = puzzleM.createNewPuzzle(player,8,"China");
        Long puzzleID7 = puzzleM.createNewPuzzle(player,8,"Australia");
        Long puzzleID8 = puzzleM.createNewPuzzle(player,8,"Sri Lanka");
        Long puzzleID9 = puzzleM.createNewPuzzle(player,8,"Canada");
        Long puzzleID10 = puzzleM.createNewPuzzle(player,8,"New Zeland");


        List<Puzzle> puzzleListTournament1 = new ArrayList<>();
        puzzleListTournament1.add(puzzleM.getPuzzle(puzzleID1));
        puzzleListTournament1.add(puzzleM.getPuzzle(puzzleID2));
        puzzleListTournament1.add(puzzleM.getPuzzle(puzzleID3));
        puzzleListTournament1.add(puzzleM.getPuzzle(puzzleID4));
        puzzleListTournament1.add(puzzleM.getPuzzle(puzzleID5));

        List<Puzzle> puzzleListTournament2 = new ArrayList<>();
        puzzleListTournament2.add(puzzleM.getPuzzle(puzzleID6));
        puzzleListTournament2.add(puzzleM.getPuzzle(puzzleID7));
        puzzleListTournament2.add(puzzleM.getPuzzle(puzzleID8));
        puzzleListTournament2.add(puzzleM.getPuzzle(puzzleID9));
        puzzleListTournament2.add(puzzleM.getPuzzle(puzzleID10));


       // Long idTournament1 = tournamentM.createNewTournament(player,"States",puzzleListTournament1);
       // Long idTournament2 = tournamentM.createNewTournament(player,"States",puzzleListTournament2);

       // assertEquals(tournamentM.getTournament(idTournament1).name,tournamentM.getTournament(idTournament2).name);
    }

 /*   @Test
    public void test() {

        playerM.addNewPlayer("Tom", "Bush", "TomB", "TomB@gmail.com");
        Player player = playerM.getPlayerByUsername("TomB");

        Long puzzleID1 = puzzleM.createNewPuzzle(player,8,"Georgia Tech");
        Long puzzleID2 = puzzleM.createNewPuzzle(player,8,"Washington DC");
        Long puzzleID3 = puzzleM.createNewPuzzle(player,8,"United States of America");
        Long puzzleID4 = puzzleM.createNewPuzzle(player,8,"Florida");
        Long puzzleID5 = puzzleM.createNewPuzzle(player,8,"Georgia");

        Long puzzleID6 = puzzleM.createNewPuzzle(player,8,"Georgia Tech");
        //Long puzzleID7 = puzzleM.createNewPuzzle(player,8,"Washington DC");
        //Long puzzleID8 = puzzleM.createNewPuzzle(player,8,"United States of America");
        //Long puzzleID9 = puzzleM.createNewPuzzle(player,8,"Florida");
        //Long puzzleID10 = puzzleM.createNewPuzzle(player,8,"Georgia");


        List<Puzzle> puzzleListTournament1 = new ArrayList<>();
        puzzleListTournament1.add(puzzleM.getPuzzle(puzzleID1));
        puzzleListTournament1.add(puzzleM.getPuzzle(puzzleID2));
        puzzleListTournament1.add(puzzleM.getPuzzle(puzzleID3));
        puzzleListTournament1.add(puzzleM.getPuzzle(puzzleID4));
        puzzleListTournament1.add(puzzleM.getPuzzle(puzzleID5));

        List<Puzzle> puzzleListTournament2 = new ArrayList<>();
        puzzleListTournament2.add(puzzleM.getPuzzle(puzzleID6));

        playerM.addNewPlayer("Tom", "Bush", "TomB", "TomB@gmail.com");
        Player player = playerM.getPlayerByUsername("Team11");


        Long idTournament1 = tournamentM.createNewTournament(player,"Tournamnet1_Player1",puzzleListTournament1);
        Long idTournament2 = tournamentM.createNewTournament(player,"Tournamnet1_Player1",puzzleListTournament2);

        assertEquals(tournamentM.getTournament(idTournament1).name,tournamentM.getTournament(idTournament2).name);
    }
*/
}
