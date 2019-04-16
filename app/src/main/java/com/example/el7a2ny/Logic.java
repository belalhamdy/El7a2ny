package com.example.el7a2ny;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Logic {
    public static String resperror = "ERROR IN REQUEST CODE 404.";

    public static String getVolley(AppCompatActivity activ, String url)
    {
        final String[] resp = new String[1];
        RequestQueue queue = Volley.newRequestQueue(activ);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Log.d("weather" , "weather" + response);
                        resp[0] = response;
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                resp[0] = resperror;
            }
        });
        queue.add(stringRequest);
        return resp[0];
    }
}
