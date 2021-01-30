package com.example.stocktrades;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* ********** Custom Array Adapter for ListView ********** */
public class CustomAdapter extends BaseAdapter {
    Context context;
    ArrayList<DataCollection> data;

    public CustomAdapter(Context context, ArrayList<DataCollection> data){
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        String exc_name = data.get(position).getExc_name();
        String sym_name = data.get(position).getSym_name();
        String change_per = data.get(position).getChange_per();
        String change_price = data.get(position).getChange_price();
        String ltp = data.get(position).getLtp();

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(R.layout.list_item,parent,false);

        TextView exc_name_text = (TextView)convertView.findViewById(R.id.exc_name);
        TextView sym_name_text = (TextView)convertView.findViewById(R.id.sym_name);
        TextView change_per_text = (TextView)convertView.findViewById(R.id.change_per);
        TextView change_price_text = (TextView)convertView.findViewById(R.id.change_price);
        TextView ltp_text = (TextView)convertView.findViewById(R.id.ltp);

        exc_name_text.setText(exc_name);
        sym_name_text.setText(sym_name);
        change_per_text.setText(change_per);
        change_price_text.setText(change_price);
        ltp_text.setText(ltp);

        return convertView;

    }
}
