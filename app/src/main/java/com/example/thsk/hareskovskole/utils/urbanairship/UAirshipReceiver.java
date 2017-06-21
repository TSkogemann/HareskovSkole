package com.example.thsk.hareskovskole.utils.urbanairship;

import android.content.Context;
import android.support.annotation.NonNull;

import com.urbanairship.AirshipReceiver;
import com.urbanairship.push.PushMessage;

/**
 * Created by thsk on 21/06/2017.
 */

public class UAirshipReceiver extends AirshipReceiver {

    private static final String TAG = UAirshipReceiver.class.getSimpleName();

    @Override
    protected boolean onNotificationOpened(@NonNull Context context, @NonNull AirshipReceiver.NotificationInfo notificationInfo) {
        // do something when notification gets opened

        final PushMessage message = notificationInfo.getMessage();
        System.out.println("onNotificationOpened: " + message.getAlert());
        System.out.println("bundle: " + message.getPushBundle().toString());
        System.out.println("type: " + message.getPushBundle().get("type"));
        System.out.println("article_id: " + message.getPushBundle().get("article_id"));

        // Return false here to allow Urban Airship to auto launch the launcher activity
        return false;
    }
    @Override
    protected void onChannelCreated(@NonNull Context context, @NonNull String channelId) {
        System.out.println("Channel created. Channel Id:" + channelId + ".");
    }

    @Override
    protected void onChannelUpdated(@NonNull Context context, @NonNull String channelId) {
        System.out.println("Channel updated. Channel Id:" + channelId + ".");
    }

    @Override
    protected void onChannelRegistrationFailed(Context context) {
        System.out.println("Channel registration failed.");
    }

    @Override
    protected void onPushReceived(@NonNull Context context, @NonNull PushMessage message, boolean notificationPosted) {
        System.out.println("Received push message. Alert: " + message.getAlert() + ". posted notification: " + notificationPosted);
    }

    @Override
    protected void onNotificationPosted(@NonNull Context context, @NonNull NotificationInfo notificationInfo) {
        System.out.println("Notification posted. Alert: " + notificationInfo.getMessage().getAlert() + ". NotificationId: " + notificationInfo.getNotificationId());
    }


    @Override
    protected boolean onNotificationOpened(@NonNull Context context, @NonNull NotificationInfo notificationInfo, @NonNull ActionButtonInfo actionButtonInfo) {
        System.out.println("Notification action button opened. Button ID: " + actionButtonInfo.getButtonId() + ". NotificationId: " + notificationInfo.getNotificationId());

        // Return false here to allow Urban Airship to auto launch the launcher
        // activity for foreground notification action buttons
        return false;
    }

    @Override
    protected void onNotificationDismissed(@NonNull Context context, @NonNull NotificationInfo notificationInfo) {
        System.out.println("Notification dismissed. Alert: " + notificationInfo.getMessage().getAlert() + ". Notification ID: " + notificationInfo.getNotificationId());
    }
}
