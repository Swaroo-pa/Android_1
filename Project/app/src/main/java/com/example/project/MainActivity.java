package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    EditText phone;
    TextView clock,comment; //success;
    Button button;
    Timer timer;
    TimerTask timerTask;
    int time = 0;
    Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        phone = findViewById(R.id.phone);
        clock = findViewById(R.id.clock);
        button = findViewById(R.id.button);
        comment = findViewById(R.id.comment);
       // success = findViewById(R.id.success);
        handler =  new Handler();
        timer = new Timer();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(button.getText().toString() != "SEND"){

                   if (TextUtils.isEmpty(phone.getText().toString())){
                          Toast.makeText(MainActivity.this, "Empty field not allowed!", Toast.LENGTH_SHORT).show();
                   }
                   else {
                       runOnUiThread(new Runnable() {
                           @Override
                           public void run() {
                               phone.setText(" ");
                               button.setText("SEND");
                               Toast.makeText(MainActivity.this, "OTP number will be disabled after 5 seconds automatically", Toast.LENGTH_SHORT).show();
                               // success.setText("OTP number will be disabled after 5 seconds automatically ");

                           }
                       });
                       startTimer();
                   }
                }
                else {

                        Second second = new Second(MainActivity.this, comment, button);
                        second.execute();
                        timerTask.cancel();
                        button.setEnabled(false);
                        runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            phone.setText("");
                            clock.setText(" ");
                            button.setText("NEXT");

                        }
                    });

                 }
            }
        });
    }
    public void startTimer(){
        MyThread thread1 = new MyThread();
        thread1.start();
    }
    public class MyThread extends Thread{
        MainActivity obj = new MainActivity();
        public void run(){
            timerTask = new TimerTask(){
            @Override
               public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        time++;
                        clock.setText(String.valueOf(time));
                        if(time == 5 ) {
                            phone.setText("0857");
                        }
                    }
                });

                        }
                    }; timer.scheduleAtFixedRate(timerTask, 0, 1000);
                }
         }
   }
