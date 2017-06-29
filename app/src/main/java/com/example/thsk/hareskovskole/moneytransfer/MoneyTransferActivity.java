package com.example.thsk.hareskovskole.moneytransfer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.widget.ListView;

import com.example.thsk.hareskovskole.MenuActivity;
import com.example.thsk.hareskovskole.R;
import com.example.thsk.hareskovskole.utils.Utility;
import com.example.thsk.hareskovskole.utils.data.MoneyTransferItem;
import com.example.thsk.hareskovskole.utils.data.User;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by thsk on 20/06/2017.
 */

public class MoneyTransferActivity extends MenuActivity {

    private User currentUser;
    private ArrayList<MoneyTransferItem> listOfTransactions = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moneytransfer);
        ButterKnife.bind(this);


        currentUser = Utility.loadCurrentUser();
        listOfTransactions.addAll(currentUser.getPrimaryEnvironment().getListOfTransaction());

        ListView newsView = (ListView) findViewById(R.id.money_transfer_previous_transfers);
        newsView.setAdapter(new MoneyTransactionAdapter(listOfTransactions, this));

    }
}
