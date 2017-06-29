package com.example.thsk.hareskovskole.moneytransfer;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thsk.hareskovskole.R;
import com.example.thsk.hareskovskole.news.NewsItem;
import com.example.thsk.hareskovskole.utils.data.MoneyTransferItem;
import com.example.thsk.hareskovskole.utils.data.User;

import java.util.ArrayList;

/**
 * Created by thsk on 29/06/2017.
 */

public class MoneyTransactionAdapter extends BaseAdapter {
    private static ArrayList<MoneyTransferItem> moneyTransferItemList;
    private LayoutInflater myLayoutInflater;

    public MoneyTransactionAdapter(ArrayList<MoneyTransferItem> transferItems, Context context) {
    this.moneyTransferItemList = transferItems;
        myLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return moneyTransferItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return moneyTransferItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        // Binding views
        if (convertView == null) {
            convertView = myLayoutInflater.inflate(R.layout.item_money_transfer_transaction, null);
            holder = new ViewHolder();
            holder.fromUser = (TextView) convertView.findViewById(R.id.money_transfer_from_user_tv);
            holder.toUser = (TextView) convertView.findViewById(R.id.money_transfer_to_user_tv);
            holder.amountTransfered = (TextView) convertView.findViewById(R.id.money_transfer_amount_tv);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // Setting text, font and text size


        if(moneyTransferItemList.get(position).getTransactionType() == MoneyTransferItem.TransactionType.SEND) {
            holder.amountTransfered.setTextColor(Color.RED);
            holder.fromUser.setText(User.getUser().getName());
            holder.fromUser.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);
            holder.fromUser.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 32);
            holder.toUser.setText(moneyTransferItemList.get(position).getToUserName());
        } else {
            holder.amountTransfered.setTextColor(Color.GREEN);
            holder.fromUser.setText(moneyTransferItemList.get(position).getToUserName());
            holder.fromUser.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);
            holder.fromUser.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 32);
            holder.toUser.setText(User.getUser().getName());

        }
        holder.amountTransfered.setText(moneyTransferItemList.get(position).getAmount()+"");
        holder.amountTransfered.setTypeface(Typeface.DEFAULT, Typeface.ITALIC);
        holder.amountTransfered.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);

        return convertView;


    }

    static class ViewHolder {
        TextView fromUser, toUser, amountTransfered;
    }
}
