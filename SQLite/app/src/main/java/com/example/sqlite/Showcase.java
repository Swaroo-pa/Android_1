package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Showcase extends AppCompatActivity {
   RecyclerView recyclerView;
   RecyclerView.Adapter recyclerAdapter;
   RecyclerView.LayoutManager layoutManager;
   ArrayList<String> username,password;
   SqlClass dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showcase);

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);

        username = new ArrayList<>(); // for passing to recyclerView
        password = new ArrayList<>();

        dbHelper = new SqlClass(this);

        display(); // to display the rows 
        recyclerAdapter = new RecyclerAdapter(this, username, password);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(layoutManager);
    }

        void display(){
             Cursor cursor = dbHelper.getAllData();
            if (cursor.getCount() == 0) {
                Toast.makeText(Showcase.this, "No data entered", Toast.LENGTH_SHORT).show();
            }else {
                while (cursor.moveToNext()) {
                    username.add(cursor.getString(0) + "\n");
                    password.add(cursor.getString(1) + "\n");
                }

            }
    }
}
