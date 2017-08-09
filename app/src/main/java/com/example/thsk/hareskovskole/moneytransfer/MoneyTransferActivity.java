package com.example.thsk.hareskovskole.moneytransfer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thsk.hareskovskole.MenuActivity;
import com.example.thsk.hareskovskole.R;
import com.example.thsk.hareskovskole.login.CreateUserFragment;
import com.example.thsk.hareskovskole.login.LoginFragment;
import com.example.thsk.hareskovskole.utils.Utility;
import com.example.thsk.hareskovskole.utils.data.Group;
import com.example.thsk.hareskovskole.utils.data.MoneyTransferItem;
import com.example.thsk.hareskovskole.utils.data.User;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by thsk on 20/06/2017.
 */

public class MoneyTransferActivity extends MenuActivity {

    @BindView(R.id.money_transfer_choose_reciever_spinner)
    Spinner recieverSpinner;
    @BindView(R.id.money_transfer_send_money_button)
    Button sendMoneyButton;
    @BindView(R.id.money_transfer_send_amount_et)
    EditText amountEditText;
    @BindView(R.id.money_transfer_fun_money)
    TextView funMoneyTopBarTv;
    @BindView(R.id.money_transfer_real_money)
    TextView realMoneyTopBarTv;


    private User currentUser;
    private ArrayList<String> recieverList;
    private String sendToName;
    private ArrayList<MoneyTransferItem> listOfTransactions = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moneytransfer);
        ButterKnife.bind(this);

        currentUser = User.getUser();
        listOfTransactions.addAll(currentUser.getPrimaryEnvironment().getListOfTransaction());

        ListView newsView = (ListView) findViewById(R.id.money_transfer_previous_transfers);
        newsView.setAdapter(new MoneyTransactionAdapter(listOfTransactions, this));

        init();
        setTopBar();
    }

    private void setTopBar() {
        funMoneyTopBarTv.setClickable(false);
        funMoneyTopBarTv.setBackgroundColor(getResources().getColor(R.color.appBackgroundColor));
        realMoneyTopBarTv.setClickable(true);
        realMoneyTopBarTv.setBackgroundColor(Utility.stringToColor(currentUser.getPrimaryEnvironment().getPrimaryColorDark()));
        funMoneyTopBarTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                funMoneyTopBarTv.setClickable(false);
                funMoneyTopBarTv.setBackgroundColor(getResources().getColor(R.color.appBackgroundColor));
                realMoneyTopBarTv.setClickable(true);
                realMoneyTopBarTv.setBackgroundColor(Utility.stringToColor(currentUser.getPrimaryEnvironment().getPrimaryColorDark()));
              /*  FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(fragmentResource,new LoginFragment());
                ft.commit();
                */
            }
        });

        realMoneyTopBarTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realMoneyTopBarTv.setClickable(false);
                realMoneyTopBarTv.setBackgroundColor(getResources().getColor(R.color.appBackgroundColor));
                funMoneyTopBarTv.setClickable(true);
                funMoneyTopBarTv.setBackgroundColor(Utility.stringToColor(currentUser.getPrimaryEnvironment().getPrimaryColorDark()));
              /*  FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(fragmentResource, new CreateUserFragment());
                ft.commit();
                */
            }
        });
    }

    private void init() {
        sendMoneyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!amountEditText.getText().toString().equals("")) {
                    int amount = Integer.parseInt(amountEditText.getText().toString());
                    MoneyTransferItem itemToSend = new MoneyTransferItem(sendToName, MoneyTransferItem.TransactionType.SEND, amount, null);
                    System.out.println("Send button clicked with amount: " + itemToSend.getAmount());
                } else {
                    Toast.makeText(getApplicationContext(), "Vælg et beløb",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        recieverList = setRecieverList();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, recieverList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        recieverSpinner.setAdapter(dataAdapter);

        recieverSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sendToName = recieverList.get(position);
                System.out.println("name picked " +sendToName);
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private ArrayList<String> setRecieverList() {
        ArrayList<String> list = new ArrayList<>();
        for (Group group : currentUser.getPrimaryEnvironment().getGroups()) {
            if (group.getAllowPayment()) {
                list.add(group.getName());
            }
        }
        return list;
    }
}
