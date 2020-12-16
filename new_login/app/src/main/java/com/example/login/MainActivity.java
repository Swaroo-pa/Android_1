package com.example.login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Credentials;
import android.net.wifi.hotspot2.pps.Credential;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText password,  username;
    Button button;
    String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        button = findViewById(R.id.submit);
        SharedPreferences sharedP = getPreferences(Context.MODE_PRIVATE);
        username.setText(sharedP.getString("user",""), TextView.BufferType.EDITABLE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            // checking whether the input matches the username and password then redirecting to next activity
            public void onClick(View v) {
                // taking values from user_input
               // name = username.getText().toString()
                //lock = password.getText().toString()

             //  user = sharedP.getString("user","");
                //  getting value from shared_preferences
              // key = sharedP.getString("key","");

                if(username.getText().toString().equals("admin") && password.getText().toString().equals("password")){
                    SharedPreferences sharedP = getPreferences(Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedP.edit();
                    editor.putString("user",username.getText().toString());
                    editor.putString("key",password.getText().toString());
                    editor.commit();
                    Intent intent = new Intent(MainActivity.this,page.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Invalid Username or Password",Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}