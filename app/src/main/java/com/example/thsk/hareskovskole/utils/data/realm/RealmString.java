package com.example.thsk.hareskovskole.utils.data.realm;

import io.realm.RealmObject;

/**
 * Created by thsk on 25/06/2017.
 */

public class RealmString extends RealmObject {

    private String string;
    public RealmString() {
        //required empty constructor
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}
