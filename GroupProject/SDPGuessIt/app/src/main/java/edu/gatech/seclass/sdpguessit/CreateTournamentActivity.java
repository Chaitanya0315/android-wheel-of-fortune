package edu.gatech.seclass.sdpguessit;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import edu.gatech.seclass.sdpguessit.data.managers.PlayerManager;
import edu.gatech.seclass.sdpguessit.data.managers.PuzzleManager;
import edu.gatech.seclass.sdpguessit.data.managers.TournamentManager;
import edu.gatech.seclass.sdpguessit.data.models.Player;
import edu.gatech.seclass.sdpguessit.data.models.Puzzle;

public class CreateTournamentActivity extends AppCompatActivity {
    @Inject PlayerManager playerManager;
    @Inject PuzzleManager puzzleManager;
    @Inject TournamentManager tournamentManager;

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.name) EditText name;

    private Unbinder unbinder;
    private List<Puzzle> puzzles;
    private List<Puzzle> selectedPuzzles;
    private Player player;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GuessItApplication.component(this).inject(this);

        setContentView(R.layout.activity_create_tournament);
        unbinder = ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        selectedPuzzles = new ArrayList<>();
        player = playerManager.getCurrentLoggedInPlayer();
        puzzles = puzzleManager.getCreatedOrCompltedPuzzlesForUser(player);
    }

    private CharSequence[] buildCharSequenceArrayFromPuzzleList(List<Puzzle> puzzles) {
        CharSequence[] vals = new CharSequence[puzzles.size()];
        for(int i = 0; i < vals.length; i++){
            vals[i] = puzzles.get(i).toString();
        }
        return vals;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick(R.id.select_puzzles)
    void selectPuzzles(){
        selectedPuzzles.clear();

        CharSequence[] puzzleStrings = buildCharSequenceArrayFromPuzzleList(puzzles);
        boolean[] checks = new boolean[puzzleStrings.length];
        Arrays.fill(checks, false);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Puzzles");
        builder.setPositiveButton("Done", null);
        builder.setCancelable(false);
        builder.setMultiChoiceItems(puzzleStrings, checks, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if(isChecked){
                    selectedPuzzles.add(puzzles.get(which));
                }else{
                    selectedPuzzles.remove(puzzles.get(which));
                }
            }
        });
        builder.show();
    }

    @OnClick(R.id.add_tournament)
    void addTournament(View v) {
        String mName = name.getText().toString();

        if(!tournamentManager.doesTournamentNameExist(mName)) {
            if (TextUtils.isEmpty(mName) || selectedPuzzles.isEmpty() || selectedPuzzles.size() >= 6) {
                Snackbar.make(v, "Sorry the name cannot be empty and you must select 1-5 puzzles", Snackbar.LENGTH_LONG)
                        .setAction("Dismiss", null).show();
            } else {
                Long id = tournamentManager.createNewTournament(player, mName, selectedPuzzles);
                Toast.makeText(this, "Tournament Added! (" + id + ")", Toast.LENGTH_SHORT).show();
                finish();
            }
        }else{
            name.setError("Name already exists");
        }
    }

    public static final Intent newIntent(Context context) {
        Intent intent = new Intent(context, CreateTournamentActivity.class);

        // Add extras if needed

        return intent;
    }
}
