package com.example.thsk.hareskovskole.messages;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.thsk.hareskovskole.R;
import com.example.thsk.hareskovskole.utils.Utility;
import com.example.thsk.hareskovskole.utils.data.User;

import java.util.List;

/**
 * Created by thsk on 02/07/2017.
 */

public class MessageOverviewAdapter extends BaseAdapter {
    private LayoutInflater myLayoutInflater;
    private List<ChatObject> chatObjects;
    Context context;

    public MessageOverviewAdapter(List<ChatObject> chatObjects, Context context) {
        myLayoutInflater = LayoutInflater.from(context);
        this.chatObjects = chatObjects;
        this.context = context;
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
            holder.chatwindow = (LinearLayout) convertView.findViewById(R.id.message_overview_last_chat_msg_window);
            holder.name = (TextView) convertView.findViewById(R.id.message_overview_name);
            holder.message = (TextView) convertView.findViewById(R.id.message_overview_last_chat_msg);
            holder.logo = (ImageView) convertView.findViewById(R.id.message_overview_logo);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // setting text
        ChatObject chatObject = chatObjects.get(position);
        if (chatObject.getGroupName() != null){
            holder.name.setText(chatObject.getGroupName());
        } else {
            holder.name.setText(chatObject.getEnviromentName());
        }

        // last message
        if(chatObject.getChat().size() > 0) {
            holder.message.setText(chatObject.getChat().get(0).getMessageText());
            if (chatObject.getChat().get(0).getSenderName() != null){
                holder.chatwindow.setBackgroundColor(Utility.stringToColor(User.getUser().getPrimaryEnvironment().getPrimaryColor()));
            } else {
                holder.chatwindow.setBackgroundColor(context.getResources().getColor(R.color.commercialDialogColor));
            }
        } else {
            holder.message.setText("Ingen tidligere beskeder");
            holder.message.setTypeface(null,Typeface.ITALIC);
            holder.chatwindow.setBackgroundColor(context.getResources().getColor(R.color.white));
        }

        // setting picture
        if(chatObject.getLogo() != null){
            Glide.with(context)
                    .load(chatObject.getLogo())
                    .error(R.drawable.ic_menu_send)
                    .centerCrop()
                    .into(holder.logo);
        }

        // Setting text, font and text size


//        if(moneyTransferItemList.get(position).getTransactionType() == MoneyTransferItem.TransactionType.SEND) {
//            holder.amountTransfered.setTextColor(Color.RED);
//            holder.fromUser.setText(User.getUser().getName());
//            holder.fromUser.setTypeface(Typeface.SANS_SERIF, Typeface.ITALIC);
//            holder.toUser.setText(moneyTransferItemList.get(position).getGroupName());
//        } else {
//            holder.amountTransfered.setTextColor(Color.GREEN);
//            holder.fromUser.setText(moneyTransferItemList.get(position).getGroupName());
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
        TextView name,message;
        ImageView logo;
        LinearLayout chatwindow;
    }
    }

