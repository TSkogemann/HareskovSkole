package com.example.thsk.hareskovskole.messages;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.example.thsk.hareskovskole.R;

/**
 * Created by thsk on 20/06/2017.
 */

public class MessageActivity extends FragmentActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
    }
}
