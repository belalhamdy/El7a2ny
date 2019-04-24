package com.example.el7a2ny;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;


/**
 * A simple {@link Fragment} subclass.
 */
public class wekaya extends Fragment {


    public wekaya() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_wekaya, container, false);
        TextView tv = v.findViewById(R.id.txt);
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "ara-hamah-homs.ttf");
        tv.setTypeface(font);

        try
        {
            InputStream inputStream = getResources().getAssets().open("wekaya.html");

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
