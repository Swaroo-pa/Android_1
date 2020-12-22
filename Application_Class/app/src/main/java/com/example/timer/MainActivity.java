package com.example.timer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {
    TextView clock;
    Button next, set, setBack;
    ClockSetter clockSetter;
    int time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // SubApp.setContext(this);

        clock = findViewById(R.id.clock);
        next = findViewById(R.id.next);
        set = findViewById(R.id.set);
        setBack = findViewById(R.id.setback);
        clockSetter = ClockSetter.getInstance();

        Toast.makeText(getApplicationContext(), "Activity-1 current state: onCreate", Toast.LENGTH_SHORT).show();
        Log.d("Activity1", "Activity current state onCreate");
        /* ***************** three buttons ************************* */
        // next page
        //    runOnUiThread(new Runnable() {
        //     @Override
        //     public void run() {
        clock.setText(String.valueOf(clockSetter.display()));
        //    }
        //});
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Second.class);
                startActivity(intent);
            }
        });
        // setting timer

        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clock.setText(String.valueOf(clockSetter.increase()));
            }
        });
        // resetting
        setBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clock.setText(String.valueOf(clockSetter.decrease()));

            }
        });


    }


}
    /* ************************** ACTIVITY LIFE CYCLE *********************************** */
   /* @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext(),
                "Activity-1 current state: onStart",
                Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(getApplicationContext(),
                "Activity-1 current state: onStop ",
                Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(),
                "Activity-1 current state: onDestroy",
                Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(getApplicationContext(),
                "Activity-1 current state: onPause ",
                Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(getApplicationContext(),
                "Activity-1 current state: onResume",
                Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(getApplicationContext(),
                "Activity-1 current state: onRestart",
                Toast.LENGTH_LONG).show();
    }  */
