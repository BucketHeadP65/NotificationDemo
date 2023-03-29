package com.example.notifications;
import android.app.NotificationChannel ;
import android.app.NotificationManager ;
import android.content.Intent ;
import android.os.Bundle ;
import android.view.Menu ;
import android.view.MenuItem ;
import android.view.View ;
import android.widget.Button ;
import android.widget.TextView ;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;


public class MainActivity extends AppCompatActivity implements MyListener1 {
    private TextView txtView ;
    public static final String NOTIFICATION_CHANNEL_ID = "10001" ;
    private final static String default_notification_channel_id = "default" ;
    private final String test = "adasd ad Asd asd asd \n adasdasd asd asdasasdas asd as\nasd sadasd asda sdsa asd asd asd \na sdadasdasdasdsadasdasdasdasd" +
            "\nadasodfkosadifnsadfas fjasfnosanflasonsanfsfnsjidgn idsung sdgodsng uoidgiuhiudshg iosdh giuodhphag wouygi aiuf baihsdgba\nkonsdgndsgoihnds oighdsoig hdsoiug" +
            "\npiojsgoijdnsgjdsgpinjdsgoi jhdsgiojhdgsd gs\nposjgisdjgpisdjng \npiohndog \nujaua";

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super .onCreate(savedInstanceState) ;


        setContentView(R.layout. activity_main ) ;
        new NotificationService().setListener( this ) ;
        txtView = findViewById(R.id.textView) ;
        Button btnCreateNotification = findViewById(R.id.btnCreateNotification ) ;
        btnCreateNotification.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                NotificationManager mNotificationManager = (NotificationManager) getSystemService( NOTIFICATION_SERVICE ) ;
                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(MainActivity. this, default_notification_channel_id ) ;
                mBuilder.setContentTitle( "My Notification" ) ;
                mBuilder.setContentText( "Notification Listener Service Example" ) ;
                //mBuilder.setTicker(test) ;
                mBuilder.setSmallIcon(R.drawable. ic_launcher_foreground ) ;
                mBuilder.setStyle(new NotificationCompat.BigTextStyle().bigText(test));
                NotificationCompat.BigTextStyle a = new NotificationCompat.BigTextStyle().bigText(test);
                //a.bigText("asaf")
                mBuilder.setStyle(new NotificationCompat.InboxStyle()
                      .addLine("Yo mFO")
                      .addLine("Yo mbro"));
                //mBuilder.setStyle(a);
                mBuilder.setAutoCancel( true ) ;
                if (android.os.Build.VERSION. SDK_INT >= android.os.Build.VERSION_CODES. O ) {
                    int importance = NotificationManager. IMPORTANCE_HIGH ;
                    NotificationChannel notificationChannel = new NotificationChannel( NOTIFICATION_CHANNEL_ID , "NOTIFICATION_CHANNEL_NAME" , importance) ;
                    mBuilder.setChannelId( NOTIFICATION_CHANNEL_ID ) ;
                    assert mNotificationManager != null;
                    mNotificationManager.createNotificationChannel(notificationChannel) ;
                }
                assert mNotificationManager != null;
                mNotificationManager.notify(( int ) System. currentTimeMillis () , mBuilder.build()) ;
            }
        }) ;
    }
    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu. menu_main , menu) ; //Menu Resource, Menu
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        switch (item.getItemId()) {
            case R.id. action_settings :
                Intent intent = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS" ) ;
                startActivity(intent) ;
                return true;
            default :
                return super .onOptionsItemSelected(item) ;
        }
    }
    @Override
    public void setValue (String packageName) {
        txtView .append( " " + packageName) ;
    }
}