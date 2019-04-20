package com.example.el7a2ny;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.UnsupportedEncodingException;


/**
 * A simple {@link Fragment} subclass.
 */
public class about extends Fragment {


    public about() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        final TextView t = view.findViewById(R.id.txtMsg);
        try {
            Logic.translate(getActivity(), "hello, my name is belal, and this is my friend ahmed.", "en", "ar", new Logic.DoWithResult() {
                @Override
                public void start(String param) {
                    t.setText(param);
                }
            });
            //} catch (JSONException e) {
            //    Log.d("drdr","drdr : " + e.getMessage());
        }catch(UnsupportedEncodingException e){
            Log.d("drdr","drdr : " + e.getMessage());
        }
    }
}
