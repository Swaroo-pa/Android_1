package com.example.markettrades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class FinalActivity extends AppCompatActivity {

    TextView success_or_failure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        getSupportActionBar().setTitle("BuyActivity");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle b = getIntent().getExtras();
        String string = b.getString("text");

        // final output of order success or failure
        success_or_failure = findViewById(R.id.textView);
        success_or_failure.setText(string);
    }
}