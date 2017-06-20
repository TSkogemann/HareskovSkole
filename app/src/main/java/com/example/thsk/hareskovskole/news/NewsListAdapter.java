package com.example.thsk.hareskovskole.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.thsk.hareskovskole.R;

import java.util.ArrayList;

/**
 * Created by thsk on 20/06/2017.
 */

public class NewsListAdapter extends BaseAdapter {

    private static ArrayList<NewsItem> newsItemArrayList;
    private LayoutInflater myLayoutInflater;
    private Context context;

    public NewsListAdapter(Context newsFragment, ArrayList<NewsItem> results){
        newsItemArrayList = results;
        context = newsFragment;
        myLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return newsItemArrayList.size();
    }

    @Override
    public Object getItem(int arg0) {
        return newsItemArrayList.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder holder;

        if(convertView == null){
            convertView = myLayoutInflater.inflate(R.layout.fragment_news, null);
            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.news_titel);
            holder.maintext = (TextView) convertView.findViewById(R.id.news_main_text);
            holder.feedpicture = (ImageView) convertView.findViewById(R.id.news_feed_picture);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.title.setText(newsItemArrayList.get(position).getTitle());
        holder.maintext.setText(newsItemArrayList.get(position).getMainText());
        Glide.with(context)
                .load(newsItemArrayList.get(position).getFeedpicture())
                .error(R.drawable.ic_menu_send)
                .placeholder(R.drawable.ic_menu_camera)
                .into(holder.feedpicture);

        return convertView;
    }
    static class ViewHolder{
        TextView title, maintext,feedtext;
        ImageView feedpicture;
    }
}
