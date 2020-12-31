package com.example.weatherinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.VoiceInteractor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    public static final String WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather?q=kozhikode&appid=d6be010bb325f83eaa9af773f6bf955e";
   public static final String WEATHER_URL2 = "https://api.openweathermap.org/data/2.5/weather?q=chennai&appid=d6be010bb325f83eaa9af773f6bf955e"; //"https://api.openweathermap.org/data/2.5/weather?q=london&appid=ca970cb83f0f7ff959ba5159475d8109";
   public static double KELVIN = 272.15;

   TextView temp_text, speed_text, humidity_text, pressure_text,
            place_text,date_text,time_text,degree_text,visibility_text,clouds_text;
   Button button1, button2;// button2 for using library

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        date_text = findViewById(R.id.date);
        time_text = findViewById(R.id.time);
        temp_text = findViewById(R.id.temp);
        place_text = findViewById(R.id.place);
        speed_text = findViewById(R.id.speed);
        degree_text = findViewById(R.id.degree);
        clouds_text = findViewById(R.id.clouds);
        humidity_text = findViewById(R.id.humidity);
        pressure_text = findViewById(R.id.pressure);
        visibility_text = findViewById(R.id.visibility);

        dateTimeDisplay();
        weatherUpdate(button1);
        weatherUpdateByLibrary(WEATHER_URL2,button2);

  }

// current date and time
  public void dateTimeDisplay(){
      Date currentTime = Calendar.getInstance().getTime();
      String formattedDate = DateFormat.getDateInstance(DateFormat.FULL).format(currentTime);
      date_text.setText(formattedDate);
      SimpleDateFormat formattedTime = new SimpleDateFormat("HH:mm:ss");
      String time = formattedTime.format(currentTime);
      time_text.setText(time);
  }

    public  void uiUpdateFromJson(String temperature, String humidity, String pressure, String speed, String clouds, String place, String visibility){
        Double temp = Double.parseDouble(temperature) - KELVIN; // converting kelvin to celsius
        Long distance = Long.parseLong(visibility)/1000;  // converting meters to kilometers

        temp_text.setText(temp.toString().substring(0,4));
        degree_text.setText("\u2103");
        humidity_text.setText("Humidity : " + humidity + "%");
        pressure_text.setText("Air Pressure : " + pressure + " hPa");

        speed_text.setText("Wind : " + speed + "km/h");

        clouds_text.setText("Clouds : " + clouds + "%");

        place_text.setText(place);
        visibility_text.setText("Visibility : " + String.valueOf(distance) + "km");

    }

    // to display weather info using URL class and HttpUrlConnection class
    public void weatherUpdate(View view){
        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                        dateTimeDisplay();
                        WeatherUpdate weather2 = new WeatherUpdate(WEATHER_URL); //asyncTask object
                try {
                    String[] result = weather2.execute().get();
                    String temperature = result[0];
                    String humidity = result[1];
                    String pressure = result[2];
                    String speed = result[3];
                    String clouds = result[4];
                    String place = result[5];
                    String visibility = result[6];
                    uiUpdateFromJson(temperature, humidity, pressure, speed, clouds, place, visibility);

                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    public void weatherUpdateByLibrary(String url, View view) {
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateTimeDisplay();
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONObject object_main = response.getJSONObject("main");
                            JSONObject object_wind = response.getJSONObject("wind");
                            JSONObject object_clouds = response.getJSONObject("clouds");

                            String temperature = object_main.getString("temp");

                            String humidity = object_main.getString("humidity");
                            String pressure = object_main.getString("pressure");

                            String speed = object_wind.getString("speed");

                            String clouds = object_clouds.getString("all");

                            String place = response.getString("name");
                            String visibility = response.getString("visibility");

                            uiUpdateFromJson(temperature, humidity, pressure, speed, clouds, place, visibility);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
                queue.add(request);
            }
        });

    }


}