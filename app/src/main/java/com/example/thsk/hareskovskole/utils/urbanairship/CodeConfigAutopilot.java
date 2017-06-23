package com.example.thsk.hareskovskole.utils.urbanairship;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.thsk.hareskovskole.BuildConfig;
import com.example.thsk.hareskovskole.R;
import com.urbanairship.AirshipConfigOptions;
import com.urbanairship.Autopilot;

import static android.util.Log.DEBUG;
import static android.util.Log.VERBOSE;

/**
 * Created by thsk on 21/06/2017.
 */


public class CodeConfigAutopilot extends Autopilot {

    private String gcmCode = "1069682797347";
    private boolean inProduction = true;
    private String productionAppKey = "RM5XqGBVTzyEWzZv58qwvA";
    private String productionAppSecret = "KqO_Z7xnTcGKek6ITyo41g";
    private String developmentAppKey = "RM5XqGBVTzyEWzZv58qwvA";
    private String developmentAppSecret = "KqO_Z7xnTcGKek6ITyo41g";

    @Nullable
    @Override
    public AirshipConfigOptions createAirshipConfigOptions(@NonNull Context context) {



        AirshipConfigOptions options = new AirshipConfigOptions.Builder()
                .setAnalyticsEnabled(true)
                .setDevelopmentAppKey(developmentAppKey)
                .setDevelopmentAppSecret(developmentAppSecret)
                .setProductionAppKey(productionAppKey)
                .setProductionAppSecret(productionAppSecret)
                .setInProduction(!BuildConfig.DEBUG)
                .setGcmSender(gcmCode)
                .setNotificationIcon(R.drawable.common_full_open_on_phone)
                .setNotificationAccentColor(0x7986CB)
                .setProductionLogLevel(VERBOSE)
                .setDevelopmentLogLevel(VERBOSE)
                .build();

        return options;
    }
}
