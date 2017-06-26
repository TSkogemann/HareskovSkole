package com.example.thsk.hareskovskole.commercials;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.thsk.hareskovskole.MenuActivity;
import com.example.thsk.hareskovskole.R;
import com.example.thsk.hareskovskole.news.NewsItem;
import com.example.thsk.hareskovskole.utils.Utility;
import com.example.thsk.hareskovskole.utils.data.User;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by thsk on 23/06/2017.
 */

public class CommercialDetailActivity extends MenuActivity {

    @BindView(R.id.commercial_detail_title)
    TextView commercialDetailTitle;
    @BindView(R.id.commercial_detail_picture)
    ImageView commercialDetailPicture;
    @BindView(R.id.commercial_detail_text)
    TextView commercialDetailText;
    @BindView(R.id.commercial_detail_picture_pager)
    ViewPager commercialDetailPicturePager;

    CommercialItem currentItem;
    PagerAdapter pageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commercial_detail);
        ButterKnife.bind(this);
        currentItem = (CommercialItem) getIntent().getSerializableExtra("commercial");

        //iniate viewpager and adapter
        pageAdapter = new ScreenSliderAdapter(getApplicationContext(),currentItem.getDialogDetailExtraPictures());

        setData();
    }

    private void setData() {
        commercialDetailTitle.setText(currentItem.getDialogDetailTitle());
        if(User.getUser().getPrimaryEnvironment().getPrimaryColor()!= null){
            commercialDetailTitle.setTextColor(Utility.stringToColor(User.getUser().getPrimaryEnvironment().getPrimaryColorDark()));
        }
        Glide.with(this)
                .load(currentItem.getDialogDetailPicture())
                .error(R.drawable.ic_menu_send)
                .centerCrop()
                .into(commercialDetailPicture);
        commercialDetailText.setText(currentItem.getDialogDetailText());
    }
}
