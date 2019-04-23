package com.example.el7a2ny;


import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.allyants.chipview.ChipView;
import com.allyants.chipview.SimpleChipAdapter;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class elaag extends Fragment{

    private SimpleChipAdapter adapter;

    public elaag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final Dialog fbDialogue = new Dialog(getActivity(), android.R.style.Theme_Black_NoTitleBar);

        final View tmp = LayoutInflater.from(getContext()).inflate(R.layout.choose, null);
        final ArrayList<Object> data = new ArrayList<>();
        final ChipView cvTag = tmp.findViewById(R.id.cvTag);
        View view = inflater.inflate(R.layout.fragment_elaag, container, false);
        final Button cancel = view.findViewById(R.id.btnCancel);
        final RadioGroup rg = view.findViewById(R.id.RG);
        final EditText et = view.findViewById(R.id.ageET);
        final ListView lst = view.findViewById(R.id.lstV);
        final Button add = view.findViewById(R.id.btnAdd);
        final Button enter = tmp.findViewById(R.id.btnOk);
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.sympID.clear();
                Toast.makeText(getActivity(), "hello55555", Toast.LENGTH_SHORT).show();
                for (int i = 0 ; i<adapter.getCount() ; ++i)
                {
                    if(adapter.isSelected(i))
                    {
                        MainActivity.sympID.add(data.get(i).toString());
                       // MainActivity.sympID.add(sympID[i]);
                    }
                }
                lst.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, MainActivity.sympID));
                fbDialogue.cancel();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                rg.clearCheck();
                et.setText("");
                lst.setAdapter(null);
                adapter = new SimpleChipAdapter(data);
                cvTag.setAdapter(adapter);
                cvTag.refreshDrawableState();
                adapter.refresh();
            }
        });



        Button btn = tmp.findViewById(R.id.btnOk);
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "ara-hamah-homs.ttf");
           btn.setTypeface(font);
        data.add("First Item");
        data.add("Second Item");
        data.add("Third Item");
        data.add("Fourth Item");
        data.add("Fifth Item");
        data.add("Sixth Item");
        data.add("First Item");
        data.add("Second Item");
        data.add("Third Item");
        data.add("Fourth Item");
        data.add("Fifth Item");
        data.add("Sixth Item");
        data.add("Seventh Item");
        data.add("First Item");
        data.add("Second Item");
        data.add("Third Item");
        data.add("Fourth Item");
        data.add("Fifth Item");
        data.add("Sixth Item");
        data.add("Seventh Item");
        data.add("Seventh Item");
        adapter = new SimpleChipAdapter(data);
        cvTag.setAdapter(adapter);

        add.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                fbDialogue.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(100, 0, 0, 0)));
                if(tmp.getParent() != null)
                    ((ViewGroup)tmp.getParent()).removeView(tmp);

                fbDialogue.setContentView(tmp);

                fbDialogue.setCancelable(true);
                fbDialogue.show();
            }
        });
        return view ;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        RadioButton rbm =  view.findViewById(R.id.rbm);
        RadioButton rbf = view.findViewById(R.id.rbf);
        TextView tv = view.findViewById(R.id.ageTV);
        EditText et = view.findViewById(R.id.ageET);
        Button add = view.findViewById(R.id.btnAdd);
        Button cancel = view.findViewById(R.id.btnCancel);
        Button next = view.findViewById(R.id.btnNext);

        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "ara-hamah-homs.ttf");
            rbm.setTypeface(font);
            rbf.setTypeface(font);
            tv.setTypeface(font);
            et.setTypeface(font);
            add.setTypeface(font);
            cancel.setTypeface(font);
            next.setTypeface(font);

        }


    }


