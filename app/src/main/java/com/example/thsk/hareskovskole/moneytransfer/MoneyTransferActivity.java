package com.example.thsk.hareskovskole.moneytransfer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.example.thsk.hareskovskole.MenuActivity;
import com.example.thsk.hareskovskole.R;

/**
 * Created by thsk on 20/06/2017.
 */

public class MoneyTransferActivity extends MenuActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moneytransfer);
    }
}
