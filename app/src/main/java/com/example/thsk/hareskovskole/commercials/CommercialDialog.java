package com.example.thsk.hareskovskole.commercials;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.thsk.hareskovskole.R;

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

    public CommercialDialog(Context context, CommercialItem currentCommercial) {
        super(context);
        this.currentCommercial = currentCommercial;
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
        if (!hasDetails) {
            commercialDialogPositiveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

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
        //  commercialDialogTitleTv.setText(currentCommercial.getDialogTitle());
        Glide.with(getContext())
                .load(currentCommercial.getDialogDetailPicture())
                .error(R.drawable.ic_menu_send)
                .centerCrop()
                .into(commercialDialogPictureIv);
        commercialDialogTextTv.setText(currentCommercial.getDialogText());
    }

    private void checkForDetails() {
        if (currentCommercial.getDialogDetailTitle() != null &&
                currentCommercial.getDialogDetailPicture() != null &&
                currentCommercial.getDialogDetailText() != null) {
            hasDetails = true;
        } else {
            hasDetails = false;
        }
    }
}
