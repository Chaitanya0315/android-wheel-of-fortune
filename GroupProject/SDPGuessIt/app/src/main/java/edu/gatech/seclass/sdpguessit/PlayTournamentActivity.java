package edu.gatech.seclass.sdpguessit;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import edu.gatech.seclass.sdpguessit.data.managers.PlayerManager;
import edu.gatech.seclass.sdpguessit.data.managers.PuzzleManager;
import edu.gatech.seclass.sdpguessit.data.managers.TournamentManager;
import edu.gatech.seclass.sdpguessit.data.models.Player;
import edu.gatech.seclass.sdpguessit.data.models.Puzzle;
import edu.gatech.seclass.sdpguessit.data.models.PuzzleRecord;
import edu.gatech.seclass.sdpguessit.data.models.Tournament;
import edu.gatech.seclass.sdpguessit.data.models.TournamentRecord;

public class PlayTournamentActivity extends AppCompatActivity {
    private static final String EXTRA_ID = "extra:tournament_id";

    @Inject PlayerManager playerManager;
    @Inject PuzzleManager puzzleManager;
    @Inject TournamentManager tournamentManager;

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.name) TextView name;
    @BindView(R.id.puzzlecontainer) LinearLayout puzzlecontainer;

    private Unbinder unbinder;
    private Player player;
    private Tournament tournament;
    private TournamentRecord tournamentRecord;
    boolean continuetournament = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GuessItApplication.component(this).inject(this);

        setContentView(R.layout.activity_play_tournament);
        unbinder = ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        player = playerManager.getCurrentLoggedInPlayer();
        tournament = tournamentManager.getTournament(getIntent().getLongExtra(EXTRA_ID, -1L));
        tournamentRecord = tournamentManager.getTournamentRecord(player, tournament);

        if(tournamentRecord == null){
            tournamentRecord = tournamentManager.addNewTournamentRecord(player, tournament);
        }else{
            continuetournament = true;
        }

        name.setText(tournament.getName());
        tournamentRecord.save();
    }

    @Override
    protected void onResume() {
        super.onResume();

        puzzlecontainer.removeAllViews();

        List<View> btns = new ArrayList<>(tournament.getPuzzles().size());

        boolean puzzleNotComplete = false;
        for (Puzzle puzzle : tournament.getPuzzles()) {
            PuzzleRecord puzzleRecord = puzzleManager.getPuzzleRecord(player, puzzle);

            Button pzlBtn = new Button(this);
            pzlBtn.setText(puzzle.toString() + (puzzleRecord != null ? "(Prize $" + puzzleRecord.getPrizeValue() + ")" : ""));
            pzlBtn.setEnabled(puzzleRecord == null);
            pzlBtn.setTag(puzzle.getId());
            pzlBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(PlayPuzzleActivity.newIntent(PlayTournamentActivity.this, (Long) v.getTag()));
                }
            });

            if (puzzleRecord == null) {
                puzzleNotComplete = true;
            }

            btns.add(pzlBtn);
            puzzlecontainer.addView(pzlBtn);
        }

        if (!puzzleNotComplete) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Congrats! You Finished The Tournament!")
                    .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            tournamentRecord.markComplete();
                            tournamentRecord.save();
                            finish();
                        }
                    });
            builder.setCancelable(false);
            builder.show();
        }else{
            if(continuetournament){
                continuetournament = false;

                for(View btn: btns){
                    if(btn.isEnabled()){
                        btn.performClick();
                        break;
                    }
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    public static final Intent newIntent(Context context, Long id) {
        Intent intent = new Intent(context, PlayTournamentActivity.class);

        intent.putExtra(EXTRA_ID, id);

        return intent;
    }
}
