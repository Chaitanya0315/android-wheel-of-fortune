package edu.gatech.seclass.sdpguessit;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        // TODO: check if logged in
//        if(loggedIn){
//            startActivity(MainActivity.newIntent(this));
//        }else{
//            startActivity(LoginActivity.newIntent(this));
//        }
//
//        finish();
    }
}
