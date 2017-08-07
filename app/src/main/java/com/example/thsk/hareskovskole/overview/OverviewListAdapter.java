package com.example.thsk.hareskovskole.overview;

import android.content.Context;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.thsk.hareskovskole.R;
import com.example.thsk.hareskovskole.utils.Utility;
import com.example.thsk.hareskovskole.utils.data.Group;
import com.example.thsk.hareskovskole.utils.data.User;

import java.util.ArrayList;

/**
 * Created by thsk on 07/08/2017.
 */

public class OverviewListAdapter extends BaseAdapter {

    private static ArrayList<Group> groupItemArrayList;
    private LayoutInflater myLayoutInflater;
    private Context context;

    public OverviewListAdapter(Context overviewFragment, ArrayList<Group> groups) {
        this.context = overviewFragment;
        groupItemArrayList = groups;
        myLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return groupItemArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return groupItemArrayList.get(position);
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
            convertView = myLayoutInflater.inflate(R.layout.fragment_overview, null);
            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.overview_name);
            holder.logo = (ImageView) convertView.findViewById(R.id.overview_logo);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        // Setting text, font and text size
        holder.name.setText(groupItemArrayList.get(position).getName());
        holder.name.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);
        holder.name.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 32);
            holder.name.setTextColor(context.getResources().getColor(R.color.colorPrimary));
            if(User.getUser().getPrimaryEnvironment().getPrimaryColor()!= null){
                holder.name.setTextColor(Utility.stringToColor(User.getUser().getPrimaryEnvironment().getPrimaryColor()));
            }

        // Setting picture
        Glide.with(context)
                .load(groupItemArrayList.get(position).getLogo())
                .error(R.drawable.ic_menu_send)
                .centerCrop()
                .into(holder.logo);

        return convertView;

    }


    static class ViewHolder {
        TextView name;
        ImageView logo;
    }
}
