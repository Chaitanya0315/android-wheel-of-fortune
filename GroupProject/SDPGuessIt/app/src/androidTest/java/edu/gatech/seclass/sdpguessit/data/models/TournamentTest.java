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

    /*
    Test if the Tournament Created is saved properly in the Database
     */

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
        Test if the playable Tournaments are 0 when no other Player has created Tournaments but the Player
        intended to play the tournament has one tournament created.
     */
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

    /*
        Scenario - Two Tournaments exist and each tournament is created by two different Players.
        Test for multiple times that the playable Tournament for Player1 is only the Tournament
        created by the other Player.
     */

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
        tournamentM.createNewTournament(player1,"Countries",puzzleListPlayer1);

        List<Puzzle> puzzleListPlayer2 = new ArrayList<>();
        puzzleListPlayer2.add(puzzleM.getPuzzle(puzzleM.createNewPuzzle(player2,8,"Clalifornia")));
        puzzleListPlayer2.add(puzzleM.getPuzzle(puzzleM.createNewPuzzle(player2,8,"New Jersey")));
        puzzleListPlayer2.add(puzzleM.getPuzzle(puzzleM.createNewPuzzle(player2,8,"New York")));
        puzzleListPlayer2.add(puzzleM.getPuzzle(puzzleM.createNewPuzzle(player2,8,"Florida")));
        puzzleListPlayer2.add(puzzleM.getPuzzle(puzzleM.createNewPuzzle(player2,8,"Georgia")));
        tournamentM.createNewTournament(player2,"States",puzzleListPlayer2);
        List<Tournament> playableTournaments = tournamentM.getPlayableTournamentsForUser(player2,TournamentManager.Filter.not_played_yet);

        for(int i = 0;i<10;i++){
            assertEquals(playableTournaments.size(),1);
        }
    }

    /*
       Test if the Tournament is playable even when that has one or more Puzzles which have already
       been completed by the Player.
    */

    @Test
    public void testIfthePlayerCanSelectMoreThan5PuzzlesToCreateATournament() {

        playerM.addNewPlayer("Tom", "Bush", "TomB", "TomB@gmail.com");
        playerM.addNewPlayer("George","Allen","GAllen","gallen@gmail.com");

        Player player1 = playerM.getPlayerByUsername("TomB");
        Player player2 = playerM.getPlayerByUsername("GAllen");

        Long id = puzzleM.createNewPuzzle(player1,8,"United States of America");

        List<Puzzle> puzzleListPlayer1 = new ArrayList<>();
        puzzleListPlayer1.add(puzzleM.getPuzzle(id));
        puzzleListPlayer1.add(puzzleM.getPuzzle(puzzleM.createNewPuzzle(player1,8,"United KingDom")));
        puzzleListPlayer1.add(puzzleM.getPuzzle(puzzleM.createNewPuzzle(player1,8,"France")));
        puzzleListPlayer1.add(puzzleM.getPuzzle(puzzleM.createNewPuzzle(player1,8,"Germany")));
        puzzleListPlayer1.add(puzzleM.getPuzzle(puzzleM.createNewPuzzle(player1,8,"Japan")));

        tournamentM.createNewTournament(player1,"New Tournament",puzzleListPlayer1);

        puzzleM.addNewPuzzleRecord(player2,puzzleM.getPuzzle(id));


        PuzzleRecord puzzleR = new PuzzleRecord(player2, puzzleM.getPuzzle(id), 100, "United States of america", 2, true);
        puzzleR.save();

        List<Tournament> tournamentList = tournamentM.getPlayableTournamentsForUser(player2, TournamentManager.Filter.not_played_yet);

        assertEquals(tournamentList.size(),1);
    }

    /*
        Test if the Tournaments completed are saved properly as completed ones in the database.
     */
    @Test
    public void testIFTournamentRecordsAreSavedCorrectly() {

        playerM.addNewPlayer("Tom", "Bush", "TomB", "TomB@gmail.com");
        playerM.addNewPlayer("George","Allen","GAllen","gallen@gmail.com");

        Player player1 = playerM.getPlayerByUsername("TomB");
        Player player2 = playerM.getPlayerByUsername("GAllen");

        Long id1 = puzzleM.createNewPuzzle(player1,8,"United States of America");
        Long id2 = puzzleM.createNewPuzzle(player1,8,"United KingDom");
        Long id3 = puzzleM.createNewPuzzle(player1,8,"France");
        Long id4 = puzzleM.createNewPuzzle(player1,8,"Germany");
        Long id5 = puzzleM.createNewPuzzle(player1,8,"Japan");

        List<Puzzle> puzzleListPlayer1 = new ArrayList<>();
        puzzleListPlayer1.add(puzzleM.getPuzzle(id1));
        puzzleListPlayer1.add(puzzleM.getPuzzle(id2));
        puzzleListPlayer1.add(puzzleM.getPuzzle(id3));
        puzzleListPlayer1.add(puzzleM.getPuzzle(id4));
        puzzleListPlayer1.add(puzzleM.getPuzzle(id5));

        Long id = tournamentM.createNewTournament(player1,"New Tournament",puzzleListPlayer1);

        TournamentRecord tournamentR = new TournamentRecord(player2, tournamentM.getTournament(id), puzzleListPlayer1, true);
        tournamentR.save();

        TournamentRecord tournamentList = tournamentM.getTournamentRecord(player2,tournamentM.getTournament(id));

        assertEquals(tournamentList.getTournament().getId(),id);
    }

    /*
        Test if the Tournaments completed are saved properly in the Database and marked as
        not playable for the same Player.
     */
    @Test
    public void testIFCompletedTournamentsAreNotChosenAsPlayable() {

        playerM.addNewPlayer("Tom", "Bush", "TomB", "TomB@gmail.com");
        playerM.addNewPlayer("George","Allen","GAllen","gallen@gmail.com");

        Player player1 = playerM.getPlayerByUsername("TomB");
        Player player2 = playerM.getPlayerByUsername("GAllen");

        Long id1 = puzzleM.createNewPuzzle(player1,8,"United States of America");
        Long id2 = puzzleM.createNewPuzzle(player1,8,"United KingDom");
        Long id3 = puzzleM.createNewPuzzle(player1,8,"France");
        Long id4 = puzzleM.createNewPuzzle(player1,8,"Germany");
        Long id5 = puzzleM.createNewPuzzle(player1,8,"Japan");

        List<Puzzle> puzzleListPlayer1 = new ArrayList<>();
        puzzleListPlayer1.add(puzzleM.getPuzzle(id1));
        puzzleListPlayer1.add(puzzleM.getPuzzle(id2));
        puzzleListPlayer1.add(puzzleM.getPuzzle(id3));
        puzzleListPlayer1.add(puzzleM.getPuzzle(id4));
        puzzleListPlayer1.add(puzzleM.getPuzzle(id5));

        Long id = tournamentM.createNewTournament(player1,"New Tournament",puzzleListPlayer1);

        TournamentRecord tournamentR = new TournamentRecord(player2, tournamentM.getTournament(id), puzzleListPlayer1, true);
        tournamentR.save();

        List<Tournament> tournamentList = tournamentM.getPlayableTournamentsForUser(player2, TournamentManager.Filter.not_played_yet);

        assertEquals(tournamentList.isEmpty(),true);
    }
}
