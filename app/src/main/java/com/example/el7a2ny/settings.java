package com.example.el7a2ny;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import me.omidh.liquidradiobutton.LiquidRadioButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class settings extends Fragment {


    public settings() {
        // Required empty public constructor
    }
    SharedPreferences t;
    void setSplash(int o){
        SharedPreferences.Editor tt = t.edit();
        tt.putInt("splash",o);
        tt.apply();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_settings, container, false);
        LiquidRadioButton rdb0 = v.findViewById(R.id.rdb0);
        LiquidRadioButton rdb1 = v.findViewById(R.id.rdb1);
        LiquidRadioButton rdb2 = v.findViewById(R.id.rdb2);

        t = getContext().getSharedPreferences("userdetails", Context.MODE_PRIVATE);

        rdb0.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) setSplash(0);
            }
        });
        rdb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) setSplash(1);
            }
        });
        rdb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) setSplash(2);
            }
        });

        int c = t.getInt("splash",1);

        if (c == 0) rdb0.setChecked(true);
        else if (c == 1) rdb1.setChecked(true);
        else if (c == 2) rdb2.setChecked(true);

        return v;
    }

}
