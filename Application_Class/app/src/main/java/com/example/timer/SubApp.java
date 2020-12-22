package com.example.timer;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import static com.google.android.material.internal.ContextUtils.getActivity;
import static java.security.AccessController.getContext;

public class SubApp extends Application {
    String name;
    static Activity mActivity;
    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "Application current state onCreate", Toast.LENGTH_SHORT).show();
        name = getProcessName();
        Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
        Log.d("Activity", "Application current state onCreate");

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(@NonNull Activity activity) {
                  mActivity = activity;
            }

            @Override
            public void onActivityResumed(@NonNull Activity activity) {
                    mActivity = activity;
                Toast.makeText(activity,"onActivityResumed", Toast.LENGTH_SHORT).show();
                Log.d("Activity", "onActivityResumed");
            }

            @Override
            public void onActivityPaused(@NonNull Activity activity) {
                     mActivity = null;
            }

            @Override
            public void onActivityStopped(@NonNull Activity activity) {
                Toast.makeText(activity,"onActivityStopped", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {
                Toast.makeText(activity,"onActivityDestroyed", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            Toast.makeText(this, "ORIENTATION_LANDSCAPE", Toast.LENGTH_SHORT).show();
        }
        else if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(this, "ORIENTATION_PORTRAIT", Toast.LENGTH_SHORT).show();
        }
    }



}
