package edu.gatech.seclass.sdpguessit.data.managers;

import android.content.Context;
import android.content.SharedPreferences;

import com.orm.StringUtil;
import com.orm.query.Condition;
import com.orm.query.Select;

import edu.gatech.seclass.sdpguessit.data.models.Player;

public class PlayerManager {
    private static final String PREF_PLAYER_ID = "pref:player_id";

    private final SharedPreferences sharedPref;

    public PlayerManager(Context context) {
        sharedPref = context.getSharedPreferences(PlayerManager.class.getSimpleName(), Context.MODE_PRIVATE);
    }

    public void addNewPlayer(String firstName, String lastName, String userName, String email){
        Player player = new Player(firstName, lastName, userName, email);
        player.save();
    }

    public boolean doesUsernameExist(String username) {
        return getPlayerByUsername(username) != null;
    }

    public Player getPlayerByUsername(String username) {
        return Select.from(Player.class)
                .where(Condition.prop(StringUtil.toSQLName("userName")).eq(username))
                .first();
    }

    public Player getCurrentLoggedInPlayer() {
        return Player.findById(Player.class, sharedPref.getLong(PREF_PLAYER_ID, -1));
    }

    public void setCurrentLoggedInPlayer(Player player) {
        if(player == null){
            sharedPref.edit().putLong(PREF_PLAYER_ID, -1).apply();
        }else{
            sharedPref.edit().putLong(PREF_PLAYER_ID, player.getId()).apply();
        }
    }
}
