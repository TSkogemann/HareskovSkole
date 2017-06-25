package com.example.thsk.hareskovskole.commercials;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.thsk.hareskovskole.R;

import java.util.List;

/**
 * Created by thsk on 25/06/2017.
 */

public class ScreenSliderAdapter extends PagerAdapter {

    Context mContext;
    List<String> commercialExtraPictures;

    public ScreenSliderAdapter(Context mContext, List<String> commercialExtraPictures) {
        this.mContext = mContext;
        this.commercialExtraPictures = commercialExtraPictures;
    }

    @Override
    public int getCount() {
        return commercialExtraPictures.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (ImageView) object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = mLayoutInflater.inflate(R.layout.activity_commercial_detail, container, false);
        ImageView imageView = (ImageView) itemView.findViewById(R.id.commercial_detail_picture_pager);
        Glide.with(mContext)
                .load(commercialExtraPictures)
                .placeholder(R.drawable.ic_menu_camera)
                .into(imageView);
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
