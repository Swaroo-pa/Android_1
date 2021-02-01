package com.example.markettrades;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

public class BottomSheet extends BottomSheetDialogFragment {

    TextView exc_name;
    TextView sym_name;
    TextView change_price;
    TextView change_per;
    TextView ltp;
    int position;
    ArrayList<DataCollection> arrayList;
    Button buy;
    Button sell;

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String SYMBOL = "sys_name";
    public static final String EXCHANGE = "exc_name";
    public static final String PRICE = "ltp";

    SharedPreferences sharedpreferences;

    public BottomSheet(int position, ArrayList<DataCollection> arrayList){
        this.position = position;
        this.arrayList = arrayList;

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheet,container,false);

        exc_name = (TextView)v.findViewById(R.id.exc_name);
        sym_name = (TextView)v.findViewById(R.id.sym_name);
        change_price = (TextView)v.findViewById(R.id.change_price);
        change_per = (TextView)v.findViewById(R.id.change_per);
        ltp = (TextView)v.findViewById(R.id.ltp);

        exc_name.setText(arrayList.get(position).getExc_name());
        sym_name.setText(arrayList.get(position).getSym_name());
        change_per.setText(arrayList.get(position).getChange_per());
        change_price.setText(arrayList.get(position).getChange_price());
        ltp.setText(arrayList.get(position).getLtp());

        buy = (Button)v.findViewById(R.id.buy);
        sell = (Button)v.findViewById(R.id.sell);

        // for passing data of clicked list item to the sell/by activity
        sharedpreferences = getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();

        // passing to buy activity
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editor.putString(SYMBOL,arrayList.get(position).getSym_name() );
                editor.putString(EXCHANGE,arrayList.get(position).getExc_name() );
                editor.putString(PRICE,arrayList.get(position).getLtp());
                editor.commit();

                Intent intent = new Intent(getActivity(),BuyActivity.class);
                startActivity(intent);

            }
        });

        // passing to sell activity
        sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editor.putString(SYMBOL,arrayList.get(position).getSym_name() );
                editor.putString(EXCHANGE,arrayList.get(position).getExc_name() );
                editor.putString(PRICE,arrayList.get(position).getLtp() );
                editor.commit();

               Intent intent = new Intent(getActivity(),SellActivity.class);
               startActivity(intent);

            }
        });
        return v;
    }
}
