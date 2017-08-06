package com.example.thsk.hareskovskole.messages;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.thsk.hareskovskole.R;
import com.example.thsk.hareskovskole.utils.Utility;
import com.example.thsk.hareskovskole.utils.data.Message;

import java.util.List;

/**
 * Created by thsk on 05/08/2017.
 */

public class MessageListAdapter extends BaseAdapter {
    private LayoutInflater myLayoutInflater;
    private List<Message> messages;
    Context context;

    public MessageListAdapter (List<Message> messages, Context context) {
        myLayoutInflater = LayoutInflater.from(context);
        this.messages = messages;
        this.context = context;
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int position) {
        return messages.get(position);
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
            convertView = myLayoutInflater.inflate(R.layout.item_message, null);
            holder = new ViewHolder();
            holder.recievedMsg = (TextView) convertView.findViewById(R.id.message_item_chatbubble_other_tv);
            holder.recievedDate = (TextView) convertView.findViewById(R.id.message_item_date_other_tv);
            holder.other = (LinearLayout) convertView.findViewById(R.id.message_item_other);
            holder.sendMsg = (TextView) convertView.findViewById(R.id.message_item_chatbubble_self_tv);
            holder.sendDate = (TextView) convertView.findViewById(R.id.message_item_date_self_tv);
            holder.send = (LinearLayout) convertView.findViewById(R.id.message_item_self);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (messages.get(position).getSenderName() != null ) {
            holder.other.setVisibility(View.VISIBLE);
            holder.send.setVisibility(View.INVISIBLE);
            holder.recievedMsg.setText(messages.get(position).getMessageText());
            holder.recievedDate.setText("sendt " + messages.get(position).getDateAndTime());
        } else {
           holder.send.setVisibility(View.VISIBLE);
            holder.other.setVisibility(View.INVISIBLE);
            holder.sendMsg.setText(messages.get(position).getMessageText());
            holder.sendDate.setText("sendt " + messages.get(position).getDateAndTime());
        }

        // Setting text, font and text size
        /*
        if(moneyTransferItemList.get(position).getTransactionType() == MoneyTransferItem.TransactionType.SEND) {
            holder.amountTransfered.setTextColor(Color.RED);
            holder.fromUser.setText(User.getUser().getName());
            holder.fromUser.setTypeface(Typeface.SANS_SERIF, Typeface.ITALIC);
            holder.toUser.setText(moneyTransferItemList.get(position).getToUserName());
        } else {
            holder.amountTransfered.setTextColor(Color.GREEN);
            holder.fromUser.setText(moneyTransferItemList.get(position).getToUserName());
            holder.toUser.setTypeface(Typeface.SANS_SERIF, Typeface.ITALIC);
            holder.toUser.setText(User.getUser().getName());

        }
        holder.amountTransfered.setText(moneyTransferItemList.get(position).getAmount()+"");
        holder.amountTransfered.setTypeface(Typeface.DEFAULT, Typeface.ITALIC);
        holder.amountTransfered.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
*/
        return convertView;


    }

    static class ViewHolder {
        TextView recievedMsg, sendMsg,recievedDate,sendDate;
        LinearLayout send,other;
    }
}
