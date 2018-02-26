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
import edu.gatech.seclass.sdpguessit.data.managers.TournamentManager;


public class PlayTournamentActivity extends AppCompatActivity {
    private static final String EXTRA_ID = "extra:tournament_id";

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.fab) FloatingActionButton fab;

    private String id;

    @Inject TournamentManager tournamentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_tournament);
        setSupportActionBar(toolbar);

        id = getIntent().getStringExtra(EXTRA_ID);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public static final Intent newIntent(Context context, String id) {
        Intent intent = new Intent(context, PlayTournamentActivity.class);

        intent.putExtra(EXTRA_ID, id);

        return intent;
    }
}