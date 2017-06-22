package com.example.thsk.hareskovskole.news;

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
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.thsk.hareskovskole.R;

import java.util.ArrayList;

/**
 * Created by thsk on 20/06/2017.
 */

public class NewsListAdapter extends BaseAdapter {

    private static ArrayList<NewsItem> newsItemArrayList;
    private LayoutInflater myLayoutInflater;
    private Context context;

    public NewsListAdapter(Context newsFragment, ArrayList<NewsItem> results) {
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
        ViewHolder holder;

        // Binding views
        if (convertView == null) {
            convertView = myLayoutInflater.inflate(R.layout.fragment_news, null);
            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.news_titel);
            holder.feedtext = (TextView) convertView.findViewById(R.id.news_feed_text);
            holder.feedpicture = (ImageView) convertView.findViewById(R.id.news_feed_picture);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // Setting text, font and text size
        holder.title.setText(newsItemArrayList.get(position).getTitle());
        holder.title.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);
        holder.title.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 32);
        holder.title.setTextColor(context.getResources().getColor(R.color.black));
        holder.feedtext.setText(newsItemArrayList.get(position).getFeedText());
        holder.feedtext.setTypeface(Typeface.DEFAULT, Typeface.ITALIC);
        holder.feedtext.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
        // Setting picture
        Glide.with(context)
                .load(newsItemArrayList.get(position).getFeedpicture())
                .error(R.drawable.ic_menu_send)
                .listener(requestListener)
                .centerCrop()
                .into(holder.feedpicture);

        return convertView;
    }

    private RequestListener<String, GlideDrawable> requestListener = new RequestListener<String, GlideDrawable>() {
        @Override
        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
            System.out.println(e);

            // important to return false so the error placeholder can be placed
            return false;
        }

        @Override
        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
            return false;
        }
    };

    static class ViewHolder {
        TextView title, feedtext;
        ImageView feedpicture;
    }
}
