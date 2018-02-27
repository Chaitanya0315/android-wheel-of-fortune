package edu.gatech.seclass.sdpguessit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import edu.gatech.seclass.sdpguessit.data.managers.PlayerManager;
import edu.gatech.seclass.sdpguessit.data.managers.PuzzleManager;
import edu.gatech.seclass.sdpguessit.data.models.Player;

public class CreatePuzzleActivity extends AppCompatActivity {
    @Inject PlayerManager playerManager;
    @Inject PuzzleManager puzzleManager;

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.phrase) EditText phrase;
    @BindView(R.id.maxguesses) Spinner maxguesses;

    private Unbinder unbinder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GuessItApplication.component(this).inject(this);

        setContentView(R.layout.activity_create_puzzle);
        unbinder = ButterKnife.bind(this);

        setSupportActionBar(toolbar);
    }

    @OnClick(R.id.add_puzzle)
    void addPuzzle(View v) {
        String mPhrase = phrase.getText().toString();
        int maxGuesses = maxguesses.getSelectedItemPosition();
        Player player = playerManager.getCurrentLoggedInPlayer();

        if (TextUtils.isEmpty(mPhrase)) {
            Snackbar.make(v, "Sorry the Phrase cannot be empty", Snackbar.LENGTH_LONG)
                    .setAction("Dismiss", null).show();
        } else {
            puzzleManager.createNewPuzzle(player, maxGuesses, mPhrase);
            Toast.makeText(this, "Puzzle Added!", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    public static final Intent newIntent(Context context) {
        Intent intent = new Intent(context, CreatePuzzleActivity.class);

        // Add extras if needed

        return intent;
    }
}
