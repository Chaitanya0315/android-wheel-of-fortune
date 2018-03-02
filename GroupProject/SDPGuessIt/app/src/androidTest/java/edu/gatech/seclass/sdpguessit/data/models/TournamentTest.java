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

import com.orm.SugarRecord;

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
        playerM = new PlayerManager(InstrumentationRegistry.getTargetContext());
        tournamentM = new TournamentManager();
        puzzleM = new PuzzleManager();
    }

    @Before
    public void setUp() {


    }

    @After
    public void tearDown() {
        SugarRecord.deleteAll(Player.class);
        SugarRecord.deleteAll(Puzzle.class);
        SugarRecord.deleteAll(Tournament.class);
        SugarRecord.deleteAll(PuzzleRecord.class);
        SugarRecord.deleteAll(TournamentRecord.class);
    }

    @Test
    public void testDBTournamentCreate() {

        playerM.addNewPlayer("Tom", "Bush", "TomB", "TomB@gmail.com");
        Player player = playerM.getPlayerByUsername("TomB");

        puzzleM.createNewPuzzle(player,8,"Seattle");

        List<Puzzle> puzzleList = new ArrayList<>();
        puzzleList.add(puzzleM.getPuzzle(puzzleM.createNewPuzzle(player,8,"Clalifornia")));
        puzzleList.add(puzzleM.getPuzzle(puzzleM.createNewPuzzle(player,8,"New Jersey")));
        puzzleList.add(puzzleM.getPuzzle(puzzleM.createNewPuzzle(player,8,"New York")));
        puzzleList.add(puzzleM.getPuzzle(puzzleM.createNewPuzzle(player,8,"Florida")));
        puzzleList.add(puzzleM.getPuzzle(puzzleM.createNewPuzzle(player,8,"Georgia")));

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

        List<Puzzle> puzzleListTournament1 = new ArrayList<>();
        puzzleListTournament1.add(puzzleM.getPuzzle(puzzleM.createNewPuzzle(player,8,"United States of America")));
        puzzleListTournament1.add(puzzleM.getPuzzle(puzzleM.createNewPuzzle(player,8,"United KingDom")));
        puzzleListTournament1.add(puzzleM.getPuzzle(puzzleM.createNewPuzzle(player,8,"France")));
        puzzleListTournament1.add(puzzleM.getPuzzle(puzzleM.createNewPuzzle(player,8,"Germany")));
        puzzleListTournament1.add(puzzleM.getPuzzle(puzzleM.createNewPuzzle(player,8,"Japan")));

        List<Puzzle> puzzleListTournament2 = new ArrayList<>();
        puzzleListTournament2.add(puzzleM.getPuzzle(puzzleM.createNewPuzzle(player,8,"China")));
        puzzleListTournament2.add(puzzleM.getPuzzle(puzzleM.createNewPuzzle(player,8,"Australia")));
        puzzleListTournament2.add(puzzleM.getPuzzle(puzzleM.createNewPuzzle(player,8,"Sri Lanka")));
        puzzleListTournament2.add(puzzleM.getPuzzle(puzzleM.createNewPuzzle(player,8,"Canada")));
        puzzleListTournament2.add(puzzleM.getPuzzle(puzzleM.createNewPuzzle(player,8,"New Zeland")));


       // Long idTournament1 = tournamentM.createNewTournament(player,"Countries",puzzleListTournament1);
       // Long idTournament2 = tournamentM.createNewTournament(player,"Countries",puzzleListTournament2);

       // assertEquals(tournamentM.getTournament(idTournament1).name,tournamentM.getTournament(idTournament2).name);
    }

    @Test
    public void testIfnoTournamentsReturnedWhenNoOtherPlayerHasCreatedTournaments() {

        playerM.addNewPlayer("Tom", "Bush", "TomB", "TomB@gmail.com");
        Player player = playerM.getPlayerByUsername("TomB");

        List<Puzzle> puzzleListTournament1 = new ArrayList<>();
        puzzleListTournament1.add(puzzleM.getPuzzle(puzzleM.createNewPuzzle(player,8,"United States of America")));
        puzzleListTournament1.add(puzzleM.getPuzzle(puzzleM.createNewPuzzle(player,8,"United KingDom")));
        puzzleListTournament1.add(puzzleM.getPuzzle(puzzleM.createNewPuzzle(player,8,"France")));
        puzzleListTournament1.add(puzzleM.getPuzzle(puzzleM.createNewPuzzle(player,8,"Germany")));
        puzzleListTournament1.add(puzzleM.getPuzzle(puzzleM.createNewPuzzle(player,8,"Japan")));
        Long idTournament1 = tournamentM.createNewTournament(player,"Countries",puzzleListTournament1);

        tournamentM.createNewTournament(player,"Countries",puzzleListTournament1);
        List<Tournament> playableTournaments = tournamentM.getPlayableTournamentsForUser(player,TournamentManager.Filter.not_played_yet);

        assertEquals(playableTournaments.isEmpty(),true);

    }

    @Test
    public void testIftheCorrectTournamentIsReturnedWhenOnlyOneExistValid() {

        playerM.addNewPlayer("Tom", "Bush", "TomB", "TomB@gmail.com");
        playerM.addNewPlayer("George","Allen","GAllen","gallen@gmail.com");

        Player player1 = playerM.getPlayerByUsername("TomB");
        Player player2 = playerM.getPlayerByUsername("GAllen");

        List<Puzzle> puzzleListPlayer1 = new ArrayList<>();
        puzzleListPlayer1.add(puzzleM.getPuzzle(puzzleM.createNewPuzzle(player1,8,"United States of America")));
        puzzleListPlayer1.add(puzzleM.getPuzzle(puzzleM.createNewPuzzle(player1,8,"United KingDom")));
        puzzleListPlayer1.add(puzzleM.getPuzzle(puzzleM.createNewPuzzle(player1,8,"France")));
        puzzleListPlayer1.add(puzzleM.getPuzzle(puzzleM.createNewPuzzle(player1,8,"Germany")));
        puzzleListPlayer1.add(puzzleM.getPuzzle(puzzleM.createNewPuzzle(player1,8,"Japan")));
        Long idTournament1 = tournamentM.createNewTournament(player1,"Countries",puzzleListPlayer1);

        List<Puzzle> puzzleListPlayer2 = new ArrayList<>();
        puzzleListPlayer2.add(puzzleM.getPuzzle(puzzleM.createNewPuzzle(player2,8,"Clalifornia")));
        puzzleListPlayer2.add(puzzleM.getPuzzle(puzzleM.createNewPuzzle(player2,8,"New Jersey")));
        puzzleListPlayer2.add(puzzleM.getPuzzle(puzzleM.createNewPuzzle(player2,8,"New York")));
        puzzleListPlayer2.add(puzzleM.getPuzzle(puzzleM.createNewPuzzle(player2,8,"Florida")));
        puzzleListPlayer2.add(puzzleM.getPuzzle(puzzleM.createNewPuzzle(player2,8,"Georgia")));
        tournamentM.createNewTournament(player2,"States",puzzleListPlayer2);
        List<Tournament> playableTournaments = tournamentM.getPlayableTournamentsForUser(player2,TournamentManager.Filter.not_played_yet);

        for(int i = 0;i<10;i++){
            Tournament playableT = playableTournaments.get(0);
            assertEquals(playableT.getName(),"Countries");
            assertEquals(playableTournaments.size(),1);
        }


    }



}
