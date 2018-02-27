package edu.gatech.seclass.sdpguessit;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import edu.gatech.seclass.sdpguessit.data.managers.PlayerManager;
import edu.gatech.seclass.sdpguessit.data.managers.PuzzleManager;
import edu.gatech.seclass.sdpguessit.data.managers.TournamentManager;
import edu.gatech.seclass.sdpguessit.data.models.Puzzle;


public class MainActivity extends AppCompatActivity {
    @Inject PlayerManager playerManager;
    @Inject PuzzleManager puzzleManager;
    @Inject TournamentManager tournamentManager;

    @BindView(R.id.toolbar) Toolbar toolbar;

    private Unbinder unbinder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GuessItApplication.component(this).inject(this);

        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);

        setSupportActionBar(toolbar);
    }

    @OnClick(R.id.create_puzzle)
    void createPuzzle() {
        startActivity(CreatePuzzleActivity.newIntent(this));
    }

    @OnClick(R.id.create_tournament)
    void createTournament() {
        startActivity(CreateTournamentActivity.newIntent(this));
    }

    @OnClick(R.id.play_puzzle)
    void playPuzzle() {
        // TODO: prompt for puzzle selection with proper filtering
        List<Puzzle> puzzles = puzzleManager.getPuzzles();
        final PuzzleAdapter puzzleAdapter = new PuzzleAdapter(puzzles);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Puzzle");
        builder.setSingleChoiceItems(puzzleAdapter, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                startActivity(PlayPuzzleActivity.newIntent(MainActivity.this, puzzleAdapter.getItem(which).getId()));
            }
        });
        builder.show();
    }

    @OnClick(R.id.play_tournament)
    void playTournament() {
        // TODO: prompt for tournament selection with proper filtering
        startActivity(PlayTournamentActivity.newIntent(this, -1L));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_logout) {
            playerManager.setCurrentLoggedInPlayer(null);
            startActivity(LoginActivity.newIntent(this));
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    public static final Intent newIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);

        // Add extras if needed

        return intent;
    }

    private class PuzzleAdapter extends ArrayAdapter<Puzzle> {
        public PuzzleAdapter(List<Puzzle> puzzles) {
            super(MainActivity.this, android.R.layout.simple_list_item_1, puzzles);
        }
    }
}
