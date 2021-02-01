package com.example.markettrades;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonRequestBody {

    /*   {"request":{"appID":"f363c1745f5f63433a57e369a01c5752",
     *               "data":{"category":"TOPLOSER"/"TOPGAINERS","sessionID":"guest","usrID":"guest"},
     *               "formFactor":"M",
     *               "futures":"0",
     *               "response_format":"json"}
     *   }
     */

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

}
