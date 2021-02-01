package com.example.markettrades;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

public class FilterBottomSheet extends BottomSheetDialogFragment {

    ArrayList<DataCollection> arrayList;
    ArrayList<DataCollection> arrayList_temp;
    SortingArray sortingArray;
    Button price;
    Button change;
    ListView listView;
    Context context;

    public FilterBottomSheet(Context context, ArrayList<DataCollection> arrayList, ListView listView){
        this.arrayList = arrayList;
        this.context = context;
        this.listView = listView;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

         View v = inflater.inflate(R.layout.filter_bottom_sheeet,container,false);
         price = (Button) v.findViewById(R.id.price);
         change = (Button) v.findViewById(R.id.change);
         sortingArray = new SortingArray(arrayList);

         // on clicking price button calling arrayList sorting function as per price value
         price.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 arrayList_temp = sortingArray.sorting_array_by_price();
                 CustomAdapter customAdapter = new CustomAdapter(context,arrayList_temp);
                 listView.setAdapter(customAdapter);
                 listView.deferNotifyDataSetChanged();

             }
         });

        // on clicking change button calling arrayList sorting function as per change_price value
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                arrayList_temp = sortingArray.sorting_array_by_change();
                CustomAdapter customAdapter = new CustomAdapter(context,arrayList_temp);
                listView.setAdapter(customAdapter);
                listView.deferNotifyDataSetChanged();

            }
        });

        return v;
    }

}
