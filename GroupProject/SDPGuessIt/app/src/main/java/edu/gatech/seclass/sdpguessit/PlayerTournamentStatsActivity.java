package edu.gatech.seclass.sdpguessit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.LinearLayout;

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
import edu.gatech.seclass.sdpguessit.data.models.TournamentRecord;

public class PlayerTournamentStatsActivity extends StatsActivity {
    @Override
    protected void onResume() {
        super.onResume();

        List<TournamentRecord> tournamentRecords = tournamentManager.getTournamentRecordsForPlayer(player);

        if (tournamentRecords.size() > 0) {
            container.removeAllViews();

            for (TournamentRecord tournamentRecord : tournamentManager.getTournamentRecordsForPlayer(player)) {
                if(tournamentRecord.isComplete()) {
                    int prizeTotal = 0;
                    for (Puzzle puzzle : tournamentRecord.getTournament().getPuzzles()) {
                        prizeTotal += puzzleManager.getPuzzleRecord(player, puzzle).getPrizeValue();
                    }

                    Button pzlBtn = new Button(this);
                    pzlBtn.setText(tournamentRecord.getTournament().getName() + " (Prize $" + prizeTotal + ")");

                    container.addView(pzlBtn);
                }
            }
        }
    }

    @Override
    String getNoStatsText() {
        return "No Tournament Stats";
    }

    public static final Intent newIntent(Context context) {
        Intent intent = new Intent(context, PlayerTournamentStatsActivity.class);

        return intent;
    }
}
