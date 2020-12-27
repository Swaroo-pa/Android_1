package com.example.sqlite;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText username, userPassword;
    Button enter, show;
    SqlClass dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new SqlClass(this);

        username = findViewById(R.id.username);
        userPassword = findViewById(R.id.password);
        enter = findViewById(R.id.button);
        show = findViewById(R.id.button1);

        addData(); // inserting data into sql database
        showData(); // creating new activity to show entered data

        }
    public void addData(){
        enter.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(username.getText().toString() == null || userPassword.getText().toString() == null ){
                    Toast.makeText(MainActivity.this, " You cannot left username blank", Toast.LENGTH_SHORT).show();
                }
                    boolean flag = dbHelper.insertData(username.getText().toString(), userPassword.getText().toString());
                    username.setText("");
                    userPassword.setText("");
                    if (flag == true) {
                        Toast.makeText(MainActivity.this, "Data inserted successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Data insertion failed", Toast.LENGTH_SHORT).show();
                    }


            }
        });
    }
    public void showData(){
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent intent = new Intent(MainActivity.this,Showcase.class);
                 startActivity(intent);
            }
        });
    }
}
