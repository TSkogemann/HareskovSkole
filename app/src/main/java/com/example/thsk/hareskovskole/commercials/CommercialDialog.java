package com.example.thsk.hareskovskole.commercials;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.thsk.hareskovskole.R;
import com.example.thsk.hareskovskole.utils.Utility;
import com.example.thsk.hareskovskole.utils.data.User;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by thsk on 23/06/2017.
 */

public class CommercialDialog extends AlertDialog {

    @BindView(R.id.commercial_dialog_title)
    TextView commercialDialogTitleTv;
    @BindView(R.id.commercial_dialog_picture)
    ImageView commercialDialogPictureIv;
    @BindView(R.id.commercial_dialog_text)
    TextView commercialDialogTextTv;
    @BindView(R.id.commercial_dialog_positive_button)
    Button commercialDialogPositiveButton;
    @BindView(R.id.commercial_dialog_negative_button)
    Button commercialDialogNegativeButton;

    CommercialItem currentCommercial;
    boolean hasDetails;
    AlertDialog commercialDialog;
    Context currentContext;

    public CommercialDialog(Context context, CommercialItem currentCommercial) {
        super(context);
        this.currentCommercial = currentCommercial;
        this.currentContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_commercial);
        ButterKnife.bind(this);
        init();

    }

    private void init() {
        setDialogData();
        checkForDetails();
        setupButtons();
    }

    private void setupButtons() {
        if (hasDetails) {
            if(User.getUser().getPrimaryEnvironment().getPrimaryColor()!= null){
                commercialDialogPositiveButton.setBackgroundColor(Utility.stringToColor(User.getUser().getPrimaryEnvironment().getPrimaryColorDark()));
            }
            commercialDialogPositiveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(currentContext, CommercialDetailActivity.class);
                    intent.putExtra("commercial", currentCommercial);
                    currentContext.startActivity(intent);
                }
            });
        } else {
            commercialDialogPositiveButton.setVisibility(View.GONE);

        }
        commercialDialogNegativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    private void setDialogData() {
          commercialDialogTitleTv.setText(currentCommercial.getDialogTitle());
        if(User.getUser().getPrimaryEnvironment().getPrimaryColor()!= null){
            commercialDialogTitleTv.setTextColor(Utility.stringToColor(User.getUser().getPrimaryEnvironment().getPrimaryColorDark()));
        }
        Glide.with(getContext())
                .load(currentCommercial.getCommercialDetailPicture())
                .error(R.drawable.ic_menu_send)
                .centerCrop()
                .into(commercialDialogPictureIv);
        commercialDialogTextTv.setText(currentCommercial.getDialogText());
    }

    private void checkForDetails() {
        if (currentCommercial.getCommercialDetailTitle() != null &&
                currentCommercial.getCommercialDetailPicture() != null &&
                currentCommercial.getCommercialDetailText() != null) {
            hasDetails = true;
        } else {
            hasDetails = false;
        }
    }
}
