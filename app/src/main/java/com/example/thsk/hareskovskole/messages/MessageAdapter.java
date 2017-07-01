package com.example.thsk.hareskovskole.messages;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.thsk.hareskovskole.utils.data.MoneyTransferItem;

import java.util.ArrayList;

/**
 * Created by thsk on 02/07/2017.
 */

public class MessageAdapter extends BaseAdapter {
    private LayoutInflater myLayoutInflater;

    public MessageAdapter(ArrayList<MoneyTransferItem> transferItems, Context context) {
        myLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
