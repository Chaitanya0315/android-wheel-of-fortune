package edu.gatech.seclass.sdpguessit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import edu.gatech.seclass.sdpguessit.data.managers.PlayerManager;
import edu.gatech.seclass.sdpguessit.data.managers.PuzzleManager;
import edu.gatech.seclass.sdpguessit.data.managers.TournamentManager;
import edu.gatech.seclass.sdpguessit.data.models.Player;


public abstract class StatsActivity extends AppCompatActivity {
    @Inject PlayerManager playerManager;
    @Inject PuzzleManager puzzleManager;
    @Inject TournamentManager tournamentManager;

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.container) LinearLayout container;
    @BindView(R.id.no_stats) TextView no_stats;

    protected Unbinder unbinder;
    protected Player player;

    abstract String getNoStatsText();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GuessItApplication.component(this).inject(this);

        setContentView(R.layout.activity_stats);
        unbinder = ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        player = playerManager.getCurrentLoggedInPlayer();
        no_stats.setText(getNoStatsText());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
