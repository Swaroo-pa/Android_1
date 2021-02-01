package com.example.markettrades;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TopGainers extends Fragment {

    public static final String POST_URL = "https://mobaelinx.angelbroking.com/AngelService/MoversNews/Movers/1.0.0";
    public static final String POST_URL_1 = "https://mobaelinx.angelbroking.com/AngelService/Market/GetNiftySensex/1.0.0";

    View view;
    ListView topGainers;
    SearchView searchView;
    ImageButton filter;
    ArrayList<DataCollection> arrayList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        arrayList.clear();
        view =  inflater.inflate(R.layout.top_gainer_layout,container,false);
        topGainers = (ListView) view.findViewById(R.id.listView);
        searchView = (SearchView) view.findViewById(R.id.searchView);
        filter = (ImageButton) view.findViewById(R.id.filter);

        // calling jsonParsing function for parsing and showing data from api in to listView
        try {
            jsonParsing();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        // on clicking topGainers listView items calling fragment for buy/sell BottomSheet
        topGainers.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BottomSheet bottomSheet = new BottomSheet(position, arrayList);
                bottomSheet.show(getFragmentManager(),"bottomSheet");
            }
        });

        // Calling search function in the topGainers listView
        in_searchView_field(searchView);

        // on clicking filter button to show change/price filter options in filterBottomSheet
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FilterBottomSheet bottomSheet = new FilterBottomSheet(getContext(),arrayList,topGainers);
                bottomSheet.show(getFragmentManager(),"bottomSheet");
            }
        });

        return view;
    }



    // searching in the array_list as per the search text and passing to custom listView arrayAdapter
    public void in_searchView_field(SearchView searchView){

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<DataCollection> filter_data = new ArrayList<>();

                for(int i=0; i< arrayList.size();i++){
                    DataCollection name = arrayList.get(i);

                    if(name.getSym_name().toLowerCase().contains((newText.toLowerCase()))){
                        filter_data.add(name);
                    }
                }
                CustomAdapter customAdapter = new CustomAdapter(getContext(),filter_data);
                topGainers.setAdapter(customAdapter);
                return true;
            }
        });

    }
    /* *****************************  for parsing and displaying in custom listView topGainers  ************************************************** */

    // to add the json array data of BSE and NSE to the arrayList to show in listView
    public void adding_array_data_object_to_arrayList(JSONArray jsonArray) throws JSONException {

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject object = jsonArray.getJSONObject(i);
            DataCollection data = new DataCollection();
            data.setExc_name(object.getString("exchName"));
            data.setSym_name(object.getString("symbolName"));
            data.setChange_per("(" + object.getString("changePer") + "%)");
            data.setChange_price(object.getString("change"));
            data.setLtp(object.getString("ltp"));
            arrayList.add(data);
        }
    }

    // to add the json data of Nifty and sensex to the arrayList to show in listView
    public void adding_data_object_to_arrayList( JSONObject object) throws JSONException {

        DataCollection data = new DataCollection();
        data.setExc_name(object.getString("exchName"));
        data.setSym_name(object.getString("symbolName"));
        data.setChange_per("("+object.getString("chp")+")");
        data.setChange_price(object.getString("ch"));
        data.setLtp(object.getString("ltp"));
        arrayList.add(data);
    }


    // Downloading  TOP GAINERS data from POST_LINK and POST_LICK_1
    public void jsonParsing() throws JSONException {

        JsonRequestBody jsonRequestBody = new JsonRequestBody();
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        // Calling function for json requestBody generation
        JSONObject requestBody = jsonRequestBody.set_requestBody_object("TOPGAINER");

        // Post request for POST_URL_1
        JsonObjectRequest jsonObjectRequest1 = new JsonObjectRequest(Request.Method.POST, POST_URL_1, requestBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                           // System.out.println(response);

                            JSONObject object = response.getJSONObject("response");
                            JSONObject object1 = object.getJSONObject("data");

                            JSONObject object3 = object1.getJSONObject("nifty");
                            JSONObject object2 = object1.getJSONObject("sensex");

                            adding_data_object_to_arrayList(object3);
                            adding_data_object_to_arrayList( object2);

                            CustomAdapter customAdapter = new CustomAdapter(getContext(),arrayList);
                            topGainers.setAdapter(customAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(jsonObjectRequest1);

        /* **************************************************************  post request 2  ********************************************************* */

        // Post request for POST_URL
        JsonObjectRequest jsonObjectRequest2 = new JsonObjectRequest(Request.Method.POST, POST_URL, requestBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // System.out.println(response);
                            JSONObject object = response.getJSONObject("response");
                            JSONObject object1 = object.getJSONObject("data");
                            JSONArray jsonArray = object1.optJSONArray("bse");
                            JSONArray jsonArray1 = object1.optJSONArray("nse");

                            adding_array_data_object_to_arrayList(jsonArray);
                            adding_array_data_object_to_arrayList(jsonArray1);

                            // passing new array_list to customAdapter for the custom listView
                            CustomAdapter customAdapter = new CustomAdapter(getContext(),arrayList);
                            topGainers.setAdapter(customAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(jsonObjectRequest2);

    }

}
