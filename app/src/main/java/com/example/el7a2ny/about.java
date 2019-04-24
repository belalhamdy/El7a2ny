package com.example.el7a2ny;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
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
        View v = inflater.inflate(R.layout.fragment_about, container, false);
        TextView tv = v.findViewById(R.id.abouttxt);
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "ara-hamah-homs.ttf");
        tv.setTypeface(font);


        try
        {
            InputStream inputStream = getResources().getAssets().open("about.html");

            String html = Logic.slurp(inputStream,1024*1024);

            tv.setText(Html.fromHtml(html));
        }
        catch (IOException exception)
        {
            tv.setText("Failed loading html.");
        }

        return v;
    }


}
