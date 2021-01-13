package com.example.listview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {
    ListView simpleList;
    ArrayAdapter<String> arrayAdapter;
    String countryList[] = {"India", "China", "australia", "Portugle", "America", "NewZealand","England","Korea"};
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // making a java object to create the UI
        View view = inflater.inflate(R.layout.home_fragment,container,false);

      // listView is a used to groups several items and display them in vertical scrollable list
        simpleList = (ListView) view.findViewById(R.id.listView);

        //  Adapter that pulls content from a source such as an array or database and, are automatically inserted to the listView
        // creates a view for each array item
        arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_view, R.id.textView, countryList);
        simpleList.setAdapter(arrayAdapter);

        return view;
    }

}
