package edu.gatech.seclass.sdpguessit;

import android.content.Context;
import android.content.Intent;
import android.widget.Button;

import java.util.List;

import edu.gatech.seclass.sdpguessit.data.models.PuzzleRecord;

public class PlayerPuzzleStatsActivity extends StatsActivity {
    @Override
    protected void onResume() {
        super.onResume();

        List<PuzzleRecord> puzzleRecordList = puzzleManager.getPuzzleRecordsForPlayer(player);

        if (puzzleRecordList.size() > 0) {
            container.removeAllViews();

            for (PuzzleRecord puzzleRecord : puzzleRecordList) {
                if (puzzleRecord.isComplete()) {
                    Button pzlBtn = new Button(this);
                    pzlBtn.setText(puzzleRecord.getPuzzle().toString() + " (Prize $" + puzzleRecord.getPrizeValue() + ")");

                    container.addView(pzlBtn);
                }
            }
        }
    }

    @Override
    String getNoStatsText() {
        return "No Puzzle Stats";
    }

    public static final Intent newIntent(Context context) {
        Intent intent = new Intent(context, PlayerPuzzleStatsActivity.class);

        return intent;
    }
}
