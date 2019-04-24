package com.example.el7a2ny;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.JsonToken;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

public class Logic {

    public static class Linker{
        public Linker(String s, int i){
            str = s;
            id = i;
        }
        private String str;
        private int id;
        public String getStr(){
            return str;
        }
        public int getID(){
            return id;
        }
    }

    private static final String appID = "486a43c6";
    private static final String appKey = "bd2a557280bbec912007328ed4efe44e";

    private static final String translateURL = "https://script.google.com/macros/s/AKfycbxKR6VflxLD86oHRxfjyOlr8a2yfBHlBiivJLz00yuG-mHFLS3i/exec";
    private static final String infermedicaURL = "https://api.infermedica.com/v2/diagnosis";
    public static String[] symptomNames;
    public static String[] conditionNames;
    public static ArrayList<Linker> symptomLinks = new ArrayList<>();
    public static ArrayList<Object> symptomObjects = new ArrayList<>();

    public interface DoWithResult {
        void start(String param);
    }
    public static void translate(Context activ, String text, String langFrom, String langTo, final DoWithResult t) throws UnsupportedEncodingException {
        RequestQueue queue = Volley.newRequestQueue(activ);
        String urlStr =  translateURL+
                "?q=" + URLEncoder.encode(text, "UTF-8") +
                "&target=" + langTo +
                "&source=" + langFrom;
        StringRequest req = new StringRequest(Request.Method.GET, urlStr,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        t.start(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        t.start(error.getMessage());
                    }
                });
        queue.add(req);
    }
    public static void contactServer(Context activ, HashMap<Object, Object> sym, final DoWithResult t) {
        // Instantiate the RequestQueue.
        /*String mytest = "{\n" +
                "    \"sex\": \"male\",\n" +
                "    \"age\": 30,\n" +
                "    \"evidence\": [\n" +
                "      {\"id\": \"s_1193\", \"choice_id\": \"present\"},\n" +
                "      {\"id\": \"s_488\", \"choice_id\": \"present\"},\n" +
                "      {\"id\": \"s_418\", \"choice_id\": \"present\"}\n" +
                "    ]\n" +
                "  }";
        */

        RequestQueue queue = Volley.newRequestQueue(activ);

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, infermedicaURL, new JSONObject(sym),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        t.start(response.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        t.start(error.getMessage());
                    }
                }
        ){
            @Override
            public HashMap<String, String> getHeaders() {
                HashMap<String,String> params = new HashMap<>();
                params.put("App-Id", appID);
                params.put("App-Key", appKey);
                params.put("Content-Type","application/json");
                return params;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(req);
    }
}
