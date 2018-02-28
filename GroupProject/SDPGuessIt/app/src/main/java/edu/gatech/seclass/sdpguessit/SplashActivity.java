package edu.gatech.seclass.sdpguessit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.orm.StringUtil;
import com.orm.query.Condition;
import com.orm.query.Select;

import javax.inject.Inject;

import edu.gatech.seclass.sdpguessit.data.managers.PlayerManager;
import edu.gatech.seclass.sdpguessit.data.models.Player;
import edu.gatech.seclass.sdpguessit.data.models.Puzzle;
import edu.gatech.seclass.sdpguessit.data.models.Tournament;

public class SplashActivity extends AppCompatActivity {
    @Inject PlayerManager playerManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GuessItApplication.component(this).inject(this);

        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onResume() {
        super.onResume();

        setupTestData();

        if (playerManager.getCurrentLoggedInPlayer() != null) {
            startActivity(MainActivity.newIntent(this));
        } else {
            startActivity(LoginActivity.newIntent(this));
        }

        finish();
    }

    private void setupTestData() {
        Player player = Select.from(Player.class)
                .where(Condition.prop(StringUtil.toSQLName("userName")).eq("rojoiii"))
                .first();
        
        if(player == null){
            // Let's set up some test data!
            
            Player rojoiii = new Player("rojoiiiF", "rojoiiiL", "rojoiii", "rojoiiiE"); rojoiii.save();
            Puzzle rojoiiiP1 = new Puzzle(rojoiii, "rojoiiiP1", 2); rojoiiiP1.save();
            Puzzle rojoiiiP2 = new Puzzle(rojoiii, "rojoiiiP2", 4); rojoiiiP2.save();
            Puzzle rojoiiiP3 = new Puzzle(rojoiii, "rojoiiiP3", 6); rojoiiiP3.save();
            
            
            Player player1 = new Player("player1F", "player1L", "player1", "player1E"); player1.save();
            Puzzle player1P1 = new Puzzle(player1, "player1P1", 2); player1P1.save();
            Puzzle player1P2 = new Puzzle(player1, "player1P2", 4); player1P2.save();
            Puzzle player1P3 = new Puzzle(player1, "player1P3", 6); player1P3.save();
            
            
            Player player2 = new Player("player2F", "player2L", "player2i", "player2E"); player2.save();
            Puzzle player2P1 = new Puzzle(player2, "player2P1", 2); player2P1.save();
            Puzzle player2P2 = new Puzzle(player2, "player2P2", 4); player2P2.save();
            Puzzle player2P3 = new Puzzle(player2, "player2P3", 6); player2P3.save();


            Tournament rojoiiiT1 = new Tournament(rojoiii, "rojoiiiT1", player1P1.getId() + "," + player2P1.getId()); rojoiiiT1.save();
            Tournament rojoiiiT2 = new Tournament(rojoiii, "rojoiiiT2", player1P2.getId() + "," + player2P2.getId()); rojoiiiT2.save();
            Tournament rojoiiiT3 = new Tournament(rojoiii, "rojoiiiT3", player1P3.getId() + "," + player2P3.getId()); rojoiiiT3.save();

            Tournament player1T1 = new Tournament(player1, "player1T1", rojoiiiP1.getId() + "," + player2P1.getId()); player1T1.save();
            Tournament player1T2 = new Tournament(player1, "player1T2", rojoiiiP2.getId() + "," + player2P2.getId()); player1T2.save();
            Tournament player1T3 = new Tournament(player1, "player1T3", rojoiiiP3.getId() + "," + player2P3.getId()); player1T3.save();

            Tournament player2T1 = new Tournament(player2, "player2T1", player1P1.getId() + "," + rojoiiiP1.getId()); player2T1.save();
            Tournament player2T2 = new Tournament(player2, "player2T2", player1P2.getId() + "," + rojoiiiP2.getId()); player2T2.save();
            Tournament player2T3 = new Tournament(player2, "player2T3", player1P3.getId() + "," + rojoiiiP3.getId()); player2T3.save();
        }

    }
}
