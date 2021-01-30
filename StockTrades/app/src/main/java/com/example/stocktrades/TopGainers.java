package com.example.stocktrades;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
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

public class TopGainers extends Fragment {
    public static final String POST_URL = "https://mobaelinx.angelbroking.com/AngelService/MoversNews/Movers/1.0.0";
    public static final String POST_URL_1 = "https://mobaelinx.angelbroking.com/AngelService/Market/GetNiftySensex/1.0.0";
    ListView topGainers;
    View view;
    SearchView searchView;
    final ArrayList<DataCollection> arrayList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.top_gainer_layout,container,false);
        topGainers = (ListView) view.findViewById(R.id.listView);
        searchView = (SearchView) view.findViewById(R.id.searchView);

        try {
            jsonParsing();

        } catch (JSONException e) {
            e.printStackTrace();
        }
      topGainers.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BottomSheet bottomSheet = new BottomSheet(position, arrayList);
                bottomSheet.show(getFragmentManager(),"bottomSheet");
            }
        });

        in_searchView_field(searchView);
  
        return view;
    }

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
                    if(name.getSym_name().toLowerCase().contains(newText.toLowerCase())){
                            filter_data.add(name);
                    }
                }
                CustomAdapter customAdapter = new CustomAdapter(getContext(),filter_data);
                topGainers.setAdapter(customAdapter);
                return false;
            }
        });

    }

    // generating request body object for POST request
    public JSONObject set_requestBody_object(String category){
        JSONObject request = new JSONObject();
        try {
            request.put("appID", "f363c1745f5f63433a57e369a01c5752");
            request.put("formFactor", "M");
            request.put("futures", "0");
            request.put("response_format", "json");

            JSONObject data = new JSONObject();
            data.put("category", category);
            data.put("sessionID", "guest");
            data.put("usrID", "guest");
            request.put("data", data);

            JSONObject requestBody = new JSONObject();
            requestBody.put("request", request);

            return requestBody;

        } catch (JSONException e) {
        e.printStackTrace();
    }
      return null;
    }

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
        data.setChange_per("("+object.getString("chp")+"%)");
        data.setChange_price(object.getString("ch"));
        data.setLtp(object.getString("ltp"));
        arrayList.add(data);
    }


    // Downloading  TOP GAINERS data from POST_LINK and POST_LICK_1
    public void jsonParsing() throws JSONException {

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JSONObject requestBody = set_requestBody_object("TOPGAINER");

        // Post request for POST_URL_1
        JsonObjectRequest jsonObjectRequest1 = new JsonObjectRequest(Request.Method.POST, POST_URL_1, requestBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            System.out.println(response);
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

        /* ********************************************************************************************************************************************* */

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
