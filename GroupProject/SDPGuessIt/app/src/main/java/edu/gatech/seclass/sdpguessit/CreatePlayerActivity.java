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
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import edu.gatech.seclass.sdpguessit.data.managers.PlayerManager;

public class CreatePlayerActivity extends AppCompatActivity {
    @Inject PlayerManager playerManager;

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.firstname) EditText firstname;
    @BindView(R.id.lastname) EditText lastname;
    @BindView(R.id.username) EditText username;
    @BindView(R.id.email) EditText email;

    private Unbinder unbinder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GuessItApplication.component(this).inject(this);

        setContentView(R.layout.activity_create_player);
        unbinder = ButterKnife.bind(this);

        setSupportActionBar(toolbar);
    }

    @OnClick(R.id.add_player)
    void addPlayer(View v) {
        String firstName = firstname.getText().toString();
        String lastName = lastname.getText().toString();
        String userName = username.getText().toString();
        String eMail = email.getText().toString();

        // TODO: Check validity of fields
        boolean hadError = false;
        if(TextUtils.isEmpty(firstName)){
            hadError = true;
            firstname.setError("Cannot be empty.");
        }

        if(TextUtils.isEmpty(lastName)){
            hadError = true;
            lastname.setError("Cannot be empty.");
        }

        if(TextUtils.isEmpty(userName)){
            hadError = true;
            username.setError("Cannot be empty.");
        }

        if(TextUtils.isEmpty(eMail)){
            hadError = true;
            email.setError("Cannot be empty.");
        }


        if(hadError){
            return;
        }

        if (playerManager.doesUsernameExist(userName)) {
            Snackbar.make(v, "Sorry that user already exists", Snackbar.LENGTH_LONG)
                    .setAction("Dismiss", null).show();
        } else {
            playerManager.addNewPlayer(firstName, lastName, userName, eMail);
            Toast.makeText(this, "Player Added!", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    public static final Intent newIntent(Context context) {
        Intent intent = new Intent(context, CreatePlayerActivity.class);

        // Add extras if needed

        return intent;
    }

}
