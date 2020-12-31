package com.example.weatherinfo;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class WeatherUpdate extends AsyncTask<Void,Void,String[]> {

    String link;

    WeatherUpdate(String link){
        this.link = link;

    }

    @Override
    protected String[] doInBackground(Void... voids) {
        URL url;
        HttpURLConnection httpURLConnection;
        InputStream inputStream;
        InputStreamReader inputStreamReader;
        String result = "";

        try {
            url = new URL(link);
            httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.connect();
            inputStream = httpURLConnection.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);
            int data = inputStreamReader.read();
            while(data != -1){
                result += (char)data;
                data = inputStreamReader.read();
            }
            try {

                JSONObject object = new JSONObject(result);
                JSONObject object_main = object.getJSONObject("main");
                JSONObject object_wind = object.getJSONObject("wind");
                JSONObject object_clouds = object.getJSONObject("clouds");

                String temperature = object_main.getString("temp");

                String humidity = object_main.getString("humidity");
                String pressure = object_main.getString("pressure");

                String speed = object_wind.getString("speed");

                String clouds = object_clouds.getString("all");

                String place = object.getString("name");
                String visibility = object.getString("visibility");

                return  new String[] {temperature, humidity, pressure, speed, clouds, place, visibility};

            } catch (JSONException e) {
                e.printStackTrace();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
