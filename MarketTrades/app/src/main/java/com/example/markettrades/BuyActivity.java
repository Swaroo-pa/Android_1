package com.example.markettrades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BuyActivity extends AppCompatActivity {

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String SYMBOL = "sys_name";
    public static final String EXCHANGE = "exc_name";
    public static final String PRICE = "ltp";

    EditText quantity;
    TextView price;
    TextView sym_name;
    TextView exc_name;
    TextView ltp;
    TextView comment;
    Button add;
    Button buy;
    SharedPreferences sharedpreferences;
    long value;
    double money;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        getSupportActionBar().setTitle("BuyActivity");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        quantity = findViewById(R.id.quantity_number);
        price = findViewById(R.id.price);
        sym_name = findViewById(R.id.sym_name);
        exc_name = findViewById(R.id.exc_name);
        ltp = findViewById(R.id.ltp);
        comment = findViewById(R.id.comment);
        add = findViewById(R.id.add);
        buy = findViewById(R.id.buy);

        // sharedPreference for retrieving data of clicked listView item
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        sym_name.setText(sharedpreferences.getString(SYMBOL,""));
        exc_name.setText(sharedpreferences.getString(EXCHANGE,""));
        ltp.setText(sharedpreferences.getString(PRICE,""));

        // for calculating the price of product as per the quantity inserted
        // if quantity field is empty, treated as 0
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(quantity.getText().toString())){
                    price.setText("000.0");
                    value = 0;
                }
                else{
                    comment.setText("");
                    value = Long.parseLong(quantity.getText().toString());
                    money = Double.parseDouble(ltp.getText().toString());
                    money = value * money;
                    price.setText(String.valueOf(money));
                }

            }
        });

        // buy will not consider the quantity number if add button is not already pressed
        // for buying the product with a condition of minimum of one quantity is selected
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(value > 0) {

                    OrderTask orderTask = new OrderTask(BuyActivity.this, comment, buy);
                    orderTask.execute();
                }
                else{
                    comment.setText("You need to select minimum 1 quantity");
                }
            }
        });
    }
}