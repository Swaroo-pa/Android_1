package com.example.timer;

import android.os.Handler;

import java.util.Timer;
import java.util.TimerTask;

class ClockSetter {
    private static ClockSetter object;
    private static int number=0;
   // TimerTask timerTask;
   // Timer timer = new Timer();
   // Handler handler = new Handler();
    // singleton support
    private ClockSetter()
    {
        // private to prevent anyone else instantiating
    }
    public static ClockSetter getInstance(){
        if(object ==  null ){

            object = new ClockSetter();

        }
            return object;
    }
    public int display(){
        return number;
    }
    public int increase(){
        number = number+1;
        return number;
    }
    public int decrease(){
        number = number-1;
        return number;
    }
  /*  public int clockSet(){
        timerTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        number++;
                        if (number == 0 ) {
                            timerTask.cancel();
                        }
                    }
                });

            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
        return number;
    }*/
}
