package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter recycleAdapter;
    RecyclerView.LayoutManager layoutManager;
    String [] title={"Monica Geller","Joey Tribbiani","Rachel Green","Chandler Bing","Ross Geller","Phoebe Buffay"};
    String [] des={"Courteney Cox","Matt LeBlanc","Jennifer Aniston","Matthew Perry","David Schwimmer","Lisa Kudrow"};
    int [] img={R.drawable.mon,R.drawable.joy,R.drawable.rach,R.drawable.chan,R.drawable.ross,R.drawable.pheob};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.RV);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recycleAdapter = new RecycleAdapter(this,title,des,img);
        recyclerView.setAdapter(recycleAdapter);
    }
}