package edu.gatech.seclass.sdpguessit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import edu.gatech.seclass.sdpguessit.data.managers.PlayerManager;

public class SplashActivity extends AppCompatActivity {
    @Inject PlayerManager playerManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GuessItApplication.component(this).inject(this);

        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (playerManager.getCurrentLoggedInPlayer() != null) {
            startActivity(MainActivity.newIntent(this));
        } else {
            startActivity(LoginActivity.newIntent(this));
        }

        finish();
    }
}
