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

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;


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
        final ChipView cvTag = tmp.findViewById(R.id.cvTag);
        final Button enter = tmp.findViewById(R.id.btnOk);

        final View view = inflater.inflate(R.layout.fragment_elaag, container, false);
        final Button cancel = view.findViewById(R.id.btnCancel);
        final RadioGroup rg = view.findViewById(R.id.RG);
        final EditText et = view.findViewById(R.id.ageET);
        final Button add = view.findViewById(R.id.btnAdd);
        final Button next = view.findViewById(R.id.btnNext);

        final RadioButton rdbMale = view.findViewById(R.id.rbm);
        final RadioButton rdbFemale = view.findViewById(R.id.rbf);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                adapter = new SimpleChipAdapter(Logic.symptomObjects);
                cvTag.setAdapter(adapter);
                cvTag.refreshDrawableState();
                adapter.refresh();
            }
        });



        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "ara-hamah-homs.ttf");
        enter.setTypeface(font);


        adapter = new SimpleChipAdapter(Logic.symptomObjects);
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

        next.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if (!rdbFemale.isChecked() && !rdbMale.isChecked()){
                    Toast.makeText(getActivity(), "يجب ان تحدد الجنس", Toast.LENGTH_SHORT).show();
                    return;
                }
                int age;
                try{
                    age = Integer.parseInt(et.getText().toString());
                    if (age <= 1 || age > 150){
                        throw new Exception();
                    }
                }
                catch(Exception ex){
                    Toast.makeText(getActivity(), "برجاء ادخال عمر صحيح", Toast.LENGTH_SHORT).show();
                    return;
                }

                boolean female = rdbFemale.isChecked();
                HashMap<Object,Object> hm = new HashMap<>();
                hm.put("sex", (female ? "fe" : "") + "male");
                hm.put("age", age);
                ArrayList<HashMap<String,String>> ev = new ArrayList<>();
                for (int i = 0 ; i<adapter.getCount() ; ++i)
                {
                    if(adapter.isSelected(i))
                    {
                        HashMap<String,String> tmp = new HashMap<>();
                        tmp.put("id","s_" + (Logic.symptomLinks.get(i)).getID());
                        tmp.put("choice_id","present");
                        ev.add(tmp);
                    }
                }
                hm.put("evidence",ev.toArray());


                //ALRIGHT TODO FOR BELAL
                //'hm' is the hash map that we are interested in
                //you just need to call Logic.contactServer with 'hm'
                //and deal with the response
                //HAVE FUN





                //


                //the following line is just a test feel free to remove it
                System.out.println(new JSONObject(hm).toString());
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


