package edu.gatech.seclass.sdpguessit;

import android.content.Context;
import android.content.Intent;
import android.widget.Button;

import java.util.List;

import edu.gatech.seclass.sdpguessit.data.models.Puzzle;
import edu.gatech.seclass.sdpguessit.data.models.PuzzleRecord;
import edu.gatech.seclass.sdpguessit.data.models.Tournament;
import edu.gatech.seclass.sdpguessit.data.models.TournamentRecord;

public class TournamentStatsActivity extends StatsActivity {
    @Override
    protected void onResume() {
        super.onResume();

        List<Tournament> tournaments = tournamentManager.getTournaments();

        if (tournaments.size() > 0) {
            container.removeAllViews();

            for (Tournament tournament : tournaments) {
                int totalPlayers = 0;
                int topPrize = 0;
                String topPlayer = "n/a";
                for (TournamentRecord tournamentRecord : tournamentManager.getTournamentRecordsForTournament(tournament)) {
                    if (tournamentRecord.isComplete()) {
                        totalPlayers++;

                        int totalPuzzlePrize = 0;
                        for(Puzzle puzzle: tournamentRecord.getTournament().getPuzzles()){
                            totalPuzzlePrize += puzzleManager.getPuzzleRecord(tournamentRecord.getPlayer(), puzzle).getPrizeValue();
                        }

                        if (totalPuzzlePrize >= topPrize) {
                            topPrize = totalPuzzlePrize;
                            topPlayer = tournamentRecord.getPlayer().getUserName();
                        }
                    }
                }

                Button pzlBtn = new Button(this);
                pzlBtn.setText(tournament.getName() + "\n"
                        + "Total Players: " + totalPlayers + "\n"
                        + "Top Player: " + topPlayer + "\n"
                        + "Top Prize: " + topPrize);

                container.addView(pzlBtn);
            }
        }
    }

    @Override
    String getNoStatsText() {
        return "No Tournament Stats";
    }

    public static final Intent newIntent(Context context) {
        Intent intent = new Intent(context, TournamentStatsActivity.class);

        return intent;
    }
}
