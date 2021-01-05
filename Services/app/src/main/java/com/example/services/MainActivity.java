package com.example.services;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
Button button1,button2,button3,button4;
public static final String CHANNEL_ID = "one";
public static final String CHANNEL_NAME = "channel";
ExampleBroadcastReceiver exampleBroadcastReceiver = new ExampleBroadcastReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,CHANNEL_NAME,NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            manager.createNotificationChannel(channel);
        }

        button1 = findViewById(R.id.start);
        button2 = findViewById(R.id.stop);
        button3 = findViewById(R.id.next);
        button4 = findViewById(R.id.notify);

         startMusic(button1);
         stopMusic(button2);
         nextPage(button3);
         notification(button4);

    }

    // to start the background Service
    public void startMusic(View view){
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent( getApplicationContext(), NewService.class ) );
            }
        });
    }

    // to stop the background Service
    public void stopMusic(View view){
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent( getApplicationContext(), NewService.class ) );
            }
        });
    }

    // directing to file system task activity
    public void nextPage(View view){
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Notification.class);
                startActivity(intent);
            }
        });
    }

    //for creating notification
    public void notification(View view){
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "This is notification example";

                NotificationCompat.Builder builder =
                        new NotificationCompat.Builder(MainActivity.this,CHANNEL_ID)
                                .setSmallIcon(R.drawable.icon)
                                .setContentTitle("Notifications Example")
                                .setContentText("This is a test notification")
                                .setAutoCancel(true);

                // to attach an activity with notification
                Intent notificationIntent = new Intent(getApplicationContext(), MainActivity.class);
                PendingIntent contentIntent = PendingIntent.getActivity(getApplicationContext(), 0, notificationIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(contentIntent);

                // Add as notification
                NotificationManagerCompat manager = NotificationManagerCompat.from(MainActivity.this);
                manager.notify(0, builder.build());

            }
        });
    }

    // registering broadcast receiver when activity starts
    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(exampleBroadcastReceiver,filter);
    }

    //unRegister when activity stops
    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(exampleBroadcastReceiver);
    }

}