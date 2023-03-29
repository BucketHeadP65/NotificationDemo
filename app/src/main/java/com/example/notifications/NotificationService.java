package com.example.notifications;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

import androidx.core.app.NotificationCompat;

public class NotificationService extends NotificationListenerService {
    private String TAG = this .getClass().getSimpleName() ;
    Context context ;
    static MyListener1 myListener ;
    @Override
    public void onCreate () {
        super .onCreate() ;
        context = getApplicationContext() ;
    }
    @Override
    public void onNotificationPosted (StatusBarNotification sbn) {
        Log. i ( TAG , "********** onNotificationPosted" ) ;
        for (String t: sbn.getNotification().extras.keySet()) {
            Log.i(TAG, t);
            try {
                Log.i(TAG, t + ": " + sbn.getNotification().extras.getCharSequence(t));
            }
            catch(Exception E){
                //
            }
        }

        Notification notification = sbn.getNotification();
        Bundle extras = notification.extras;
        String template = extras.getString(Notification.EXTRA_TEMPLATE);

        Log.i(TAG, "Content text:" + NotificationCompat.getContentText(notification));
        Log.i(TAG, "Content subtext:" + NotificationCompat.getSubText(notification));

                //.setStyle(new NotificationCompat.BigTextStyle().bigText("This is a big text style"));
        Log. i ( TAG , "ID :" + sbn.getId() + " \t " + sbn.getNotification().tickerText + " \t " + sbn.getPackageName() + " \t" + sbn.getNotification().extras.getString("android.text"));
        Log.i(TAG, "android.textLines: " + ((CharSequence[]) extras.get("android.textLines"))[0]);
        myListener .setValue( "Post: " + sbn.getPackageName()) ;

    }
    @Override
    public void onNotificationRemoved (StatusBarNotification sbn) {
        Log. i ( TAG , "********** onNotificationRemoved" ) ;
        Log. i ( TAG , "ID :" + sbn.getId() + " \t " + sbn.getNotification().tickerText + " \t " + sbn.getPackageName()) ;
        myListener .setValue( "Remove: " + sbn.getPackageName()) ;
    }
    public void setListener (MyListener1 myListener) {
        NotificationService. myListener = myListener ;
    }
}