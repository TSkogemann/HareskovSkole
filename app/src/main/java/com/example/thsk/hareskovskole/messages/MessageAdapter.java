package com.example.thsk.hareskovskole.messages;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.thsk.hareskovskole.R;

import java.util.List;

/**
 * Created by thsk on 02/07/2017.
 */

public class MessageAdapter extends BaseAdapter {
    private LayoutInflater myLayoutInflater;
    private List<ChatObject> chatObjects;

    public MessageAdapter(List<ChatObject> chatObjects, Context context) {
        myLayoutInflater = LayoutInflater.from(context);
        this.chatObjects = chatObjects;
    }

    @Override
    public int getCount() {
        return chatObjects.size();
    }

    @Override
    public Object getItem(int position) {
        return chatObjects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        // Binding views
        if (convertView == null) {
            convertView = myLayoutInflater.inflate(R.layout.item_chat_name, null);
            holder = new ViewHolder();
            holder.fromUser = (TextView) convertView.findViewById(R.id.chat_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ChatObject chatObject = chatObjects.get(position);
        if (chatObject.getGroupName() != null){
            holder.fromUser.setText(chatObject.getGroupName());
        } else {
            holder.fromUser.setText(chatObject.getEnviromentName());
        }
        // Setting text, font and text size


//        if(moneyTransferItemList.get(position).getTransactionType() == MoneyTransferItem.TransactionType.SEND) {
//            holder.amountTransfered.setTextColor(Color.RED);
//            holder.fromUser.setText(User.getUser().getName());
//            holder.fromUser.setTypeface(Typeface.SANS_SERIF, Typeface.ITALIC);
//            holder.toUser.setText(moneyTransferItemList.get(position).getToUserName());
//        } else {
//            holder.amountTransfered.setTextColor(Color.GREEN);
//            holder.fromUser.setText(moneyTransferItemList.get(position).getToUserName());
//            holder.toUser.setTypeface(Typeface.SANS_SERIF, Typeface.ITALIC);
//            holder.toUser.setText(User.getUser().getName());
//
//        }
//        holder.amountTransfered.setText(moneyTransferItemList.get(position).getAmount()+"");
//        holder.amountTransfered.setTypeface(Typeface.DEFAULT, Typeface.ITALIC);
//        holder.amountTransfered.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);

        return convertView;


    }

    static class ViewHolder {
        TextView fromUser;
    }
    }

