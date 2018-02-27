package edu.gatech.seclass.sdpguessit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import edu.gatech.seclass.sdpguessit.data.managers.TournamentManager;

public class PlayTournamentActivity extends AppCompatActivity {
    private static final String EXTRA_ID = "extra:tournament_id";

    @Inject TournamentManager tournamentManager;

    @BindView(R.id.toolbar) Toolbar toolbar;

    private Unbinder unbinder;
    private Long id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GuessItApplication.component(this).inject(this);

        setContentView(R.layout.activity_play_tournament);
        unbinder = ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        id = getIntent().getLongExtra(EXTRA_ID, -1L);
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
