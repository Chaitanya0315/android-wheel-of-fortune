package edu.gatech.seclass.sdpguessit;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import edu.gatech.seclass.sdpguessit.data.managers.PlayerManager;
import edu.gatech.seclass.sdpguessit.data.managers.PuzzleManager;
import edu.gatech.seclass.sdpguessit.data.models.Player;
import edu.gatech.seclass.sdpguessit.data.models.Puzzle;
import edu.gatech.seclass.sdpguessit.data.models.PuzzleRecord;

public class PlayPuzzleActivity extends AppCompatActivity {
    private static final String EXTRA_ID = "extra:puzzle_id";

    @Inject PlayerManager playerManager;
    @Inject PuzzleManager puzzleManager;

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.phrase) TextView phrase;
    @BindView(R.id.prize) TextView prize;
    @BindView(R.id.totalprize) TextView totalprize;
    @BindView(R.id.remainingguesses) TextView remainingguesses;
    @BindView(R.id.guesses) Spinner guesses;
    @BindView(R.id.vowels) Spinner vowels;
    @BindView(R.id.buy) Button buy;
    @BindView(R.id.solve_puzzle) EditText solve_puzzle;

    private Unbinder unbinder;
    private Player player;
    private Puzzle puzzle;
    private PuzzleRecord puzzleRecord;

    private ArrayAdapter<Character> guessesArrayAdapter;
    private ArrayAdapter<Character> vowelsArrayAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GuessItApplication.component(this).inject(this);

        setContentView(R.layout.activity_play_puzzle);
        unbinder = ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        player = playerManager.getCurrentLoggedInPlayer();
        puzzle = puzzleManager.getPuzzle(getIntent().getLongExtra(EXTRA_ID, -1L));
        puzzleRecord = puzzleManager.addNewPuzzleRecord(player, puzzle);
    }

    @Override
    protected void onResume() {
        super.onResume();

        updatePlayingBoard();
    }

    private void updatePlayingBoard() {
        phrase.setText(puzzleRecord.getPuzzlePhrase());
        prize.setText(String.valueOf(puzzleRecord.generatePrizeValue()));
        totalprize.setText(String.valueOf(puzzleRecord.getPrizeValue()));
        remainingguesses.setText(String.valueOf(puzzleRecord.getRemainingGuessCount()));

        guessesArrayAdapter = new ArrayAdapter<Character>
                (this, android.R.layout.simple_spinner_item,
                        puzzleRecord.getUnChosenLetters());
        guessesArrayAdapter.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);
        guesses.setAdapter(guessesArrayAdapter);

        vowelsArrayAdapter = new ArrayAdapter<Character>
                (this, android.R.layout.simple_spinner_item,
                        puzzleRecord.getUnChosenVowels());
        vowelsArrayAdapter.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);
        vowels.setAdapter(vowelsArrayAdapter);

        buy.setEnabled(puzzleRecord.getPrizeValue() >= 300);
    }

    @OnClick(R.id.guess)
    void guess() {
        puzzleRecord.guessConsonantForPrizeValue(guessesArrayAdapter.getItem(guesses.getSelectedItemPosition()), Integer.valueOf(prize.getText().toString()));
        updatePlayingBoard();

        shouldWeExit();
    }

    @OnClick(R.id.buy)
    void buy() {
        puzzleRecord.buyVowel(vowelsArrayAdapter.getItem(vowels.getSelectedItemPosition()));
        updatePlayingBoard();

        shouldWeExit();
    }

    @OnClick(R.id.solve)
    void solve() {
        puzzleRecord.solve(solve_puzzle.getText().toString());
        updatePlayingBoard();

        shouldWeExit();
    }

    private void shouldWeExit() {
        if (puzzleRecord.isOutOfGuesses()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Sorry You Lose")
                    .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            puzzleRecord.complete();
                            puzzleRecord.save();
                            finish();
                        }
                    });
            builder.show();
        }else if (puzzleRecord.isPuzzleSolved()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Congrats! You Win!")
                    .setMessage("Title Prize " + puzzleRecord.getPrizeValue() + "!!")
                    .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            puzzleRecord.save();
                            finish();
                        }
                    });
            builder.show();
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Exit Puzzle")
                .setMessage("If you exit this puzzle will be completed with 0 prize!")
                .setPositiveButton("Stay Here", null)
                .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        puzzleRecord.complete();
                        puzzleRecord.save();
                        finish();
                    }
                });
        builder.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    public static final Intent newIntent(Context context, Long id) {
        Intent intent = new Intent(context, PlayPuzzleActivity.class);

        intent.putExtra(EXTRA_ID, id);

        return intent;
    }
}
