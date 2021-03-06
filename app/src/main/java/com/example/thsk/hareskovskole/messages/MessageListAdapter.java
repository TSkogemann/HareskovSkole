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
import com.example.thsk.hareskovskole.utils.data.User;

import java.util.List;

/**
 * Created by thsk on 05/08/2017.
 */

public class MessageListAdapter extends BaseAdapter {
    private LayoutInflater myLayoutInflater;
    private List<Message> messages;
    Context context;
    User currentUser;

    public MessageListAdapter (List<Message> messages, Context context) {
        myLayoutInflater = LayoutInflater.from(context);
        this.messages = messages;
        this.context = context;
        this.currentUser = User.getUser();
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
            holder.otherll = (LinearLayout) convertView.findViewById(R.id.message_item_other_ll);
            holder.sendMsg = (TextView) convertView.findViewById(R.id.message_item_chatbubble_self_tv);
            holder.sendDate = (TextView) convertView.findViewById(R.id.message_item_date_self_tv);
            holder.send = (LinearLayout) convertView.findViewById(R.id.message_item_self);
            holder.sendll = (LinearLayout) convertView.findViewById(R.id.message_item_self_ll);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.other.setBackgroundColor(Utility.stringToColor(currentUser.getPrimaryEnvironment().getPrimaryColor()));
        holder.send.setBackgroundColor(context.getResources().getColor(R.color.commercialDialogColor));

        // getCount() -1 -position to get reverse order of the list
        if (messages.get(getCount() -1 - position).getSenderName() != null ) {
            holder.otherll.setVisibility(View.VISIBLE);
            holder.sendll.setVisibility(View.INVISIBLE);
            holder.recievedMsg.setText(messages.get(getCount() -1 - position).getMessageText());
            holder.recievedDate.setText("sendt " + messages.get(getCount() -1 - position).getDateAndTime());
        } else {
           holder.sendll.setVisibility(View.VISIBLE);
            holder.otherll.setVisibility(View.INVISIBLE);
            holder.sendMsg.setText(messages.get(getCount() -1 - position).getMessageText());
            holder.sendDate.setText("sendt " + messages.get(getCount() -1 - position).getDateAndTime());
        }

        // Setting text, font and text size
        /*
        if(moneyTransferItemList.get(position).getTransactionType() == MoneyTransferItem.TransactionType.SEND) {
            holder.amountTransfered.setTextColor(Color.RED);
            holder.fromUser.setText(User.getUser().getName());
            holder.fromUser.setTypeface(Typeface.SANS_SERIF, Typeface.ITALIC);
            holder.toUser.setText(moneyTransferItemList.get(position).getGroupName());
        } else {
            holder.amountTransfered.setTextColor(Color.GREEN);
            holder.fromUser.setText(moneyTransferItemList.get(position).getGroupName());
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
        LinearLayout send,sendll,other,otherll;
    }
}
