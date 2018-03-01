package edu.gatech.seclass.sdpguessit;

import android.content.Context;
import android.content.Intent;
import android.widget.Button;

import java.util.List;

import edu.gatech.seclass.sdpguessit.data.models.Puzzle;
import edu.gatech.seclass.sdpguessit.data.models.PuzzleRecord;

public class PuzzleStatsActivity extends StatsActivity {
    @Override
    protected void onResume() {
        super.onResume();

        List<Puzzle> puzzles = puzzleManager.getPuzzles();

        if (puzzles.size() > 0) {
            container.removeAllViews();

            for (Puzzle puzzle : puzzles) {
                int totalPlayers = 0;
                int topPrize = 0;
                String topPlayer = "n/a";
                for (PuzzleRecord puzzleRecord : puzzleManager.getPuzzleRecordsForPuzzle(puzzle)) {
                    if (puzzleRecord.isComplete()) {
                        totalPlayers++;
                        if (puzzleRecord.getPrizeValue() >= topPrize) {
                            topPrize = puzzleRecord.getPrizeValue();
                            topPlayer = puzzleRecord.getPlayer().getUserName();
                        }
                    }
                }

                Button pzlBtn = new Button(this);
                pzlBtn.setText(puzzle.toString() + "\n"
                        + "Total Players: " + totalPlayers + "\n"
                        + "Top Player: " + topPlayer + "\n"
                        + "Top Prize: " + topPrize);

                container.addView(pzlBtn);
            }
        }
    }

    @Override
    String getNoStatsText() {
        return "No Puzzle Stats";
    }

    public static final Intent newIntent(Context context) {
        Intent intent = new Intent(context, PuzzleStatsActivity.class);

        return intent;
    }
}
