package com.example.thsk.hareskovskole.utils.urbanairship;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.thsk.hareskovskole.R;
import com.urbanairship.AirshipConfigOptions;
import com.urbanairship.Autopilot;

/**
 * Created by thsk on 21/06/2017.
 */


public class CodeConfigAutopilot extends Autopilot {

    private String gcmCode = "1069682797347";
    private boolean inProduction = true;
    private String productionAppKey = "";
    private String productionAppSecret = "";
    private String developmentAppKey = "";
    private String developmentAppSecret = "";

    @Nullable
    @Override
    public AirshipConfigOptions createAirshipConfigOptions(@NonNull Context context) {



        AirshipConfigOptions options = new AirshipConfigOptions.Builder()
                .setAnalyticsEnabled(true)
                .setDevelopmentAppKey(developmentAppKey)
                .setDevelopmentAppSecret(developmentAppSecret)
                .setProductionAppKey(productionAppKey)
                .setProductionAppSecret(productionAppSecret)
                .setInProduction(inProduction)
                .setGcmSender(gcmCode)
                .setNotificationIcon(R.drawable.common_full_open_on_phone)
                .setNotificationAccentColor(0x7986CB)
                .build();

        return options;
    }
}
