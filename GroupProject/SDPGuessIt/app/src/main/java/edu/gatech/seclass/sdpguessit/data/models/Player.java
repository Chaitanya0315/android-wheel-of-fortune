package edu.gatech.seclass.sdpguessit.data.models;

import com.orm.SugarRecord;

public class Player extends SugarRecord<Player> {
    String firstName;
    String lastName;
    String userName;
    String email;

    public Player() {
    }

    public Player(String firstName, String lastName, String userName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }
}
