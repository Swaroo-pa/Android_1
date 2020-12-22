package com.example.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class Second extends AppCompatActivity {
    TextView clock;
    Button back, start;
    // Timer timer;
    //int time,starter;
    // TimerTask timerTask;
    // Handler handler;
    ClockSetter clockSetter;
    // boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        clock = findViewById(R.id.clock);
        back = findViewById(R.id.back);
        start = findViewById(R.id.start);
        //timer = new Timer();
        // handler = new Handler();
        clockSetter = ClockSetter.getInstance();

        Toast.makeText(getApplicationContext(), "Activity-2 current state: onCreate", Toast.LENGTH_LONG).show();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Second.this, MainActivity.class);
                startActivity(intent);
            }
        });

                clock.setText(String.valueOf(clockSetter.display()));

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               
                        clock.setText(String.valueOf(clockSetter.increase()));


              /*  time = clockSetter.increase();
               /* runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        clock.setText(String.valueOf(time));
                    }
                }); */
            }
        });

    }
}
    /* ******************** timer **************************** */
 /*   public void startTimer() {
        MyThread thread1 = new MyThread();
        thread1.start();
    }

    public class MyThread extends Thread {
        int time ;

        public void run() {
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                                time++;
                            if (time == 0 ) {
                                timerTask.cancel();
                            }
                            clock.setText(String.valueOf(time));
                        }
                    });

                }
            };
            timer.scheduleAtFixedRate(timerTask, 0, 1000);
        }
    }  */

    /* ************************************ ACTIVITY LIFECYCLE ************************ */
   /* @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext(),
                "Activity-2 current state: onStart",
                Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(getApplicationContext(),
                "Activity-2 current state: onStop ",
                Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(),
                "Activity-2 current state: onDestroy",
                Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(getApplicationContext(),
                "Activity-2 current state: onPause ",
                Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(getApplicationContext(),
                "Activity-2 current state: onResume",
                Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(getApplicationContext(),
                "Activity-2 current state: onRestart",
                Toast.LENGTH_LONG).show();
    } */
