package com.example.el7a2ny;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.allyants.chipview.ChipView;
import com.allyants.chipview.SimpleChipAdapter;
import com.github.loadingview.LoadingView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
    private void updateTextView(String newtxt)
    {
        TextView tv = getView().findViewById(R.id.disTV);
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "ara-hamah-homs.ttf");
        tv.setTypeface(font);
        tv.setText(newtxt);
        LoadingView prog = getView().findViewById(R.id.loading);
        prog.stop();
    }
    private void doWithResponse(String response) throws JSONException {
        JSONObject wholeResp = new JSONObject(response);
        JSONArray Answer = wholeResp.getJSONArray("conditions");
        JSONObject ret = Answer.getJSONObject(0);//returns the first answer only and this is the respnonse
        String disease = ret.getString("name");
        //Toast.makeText(getContext(), disease, Toast.LENGTH_SHORT).show();

        try {
            Logic.translate(getContext(), disease, "en", "ar", new Logic.DoWithResult() {
                @Override
                public void start(String param) {
                    updateTextView(param);
                }
            });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
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
        final TextView disTv = view.findViewById(R.id.disTV);
        final RadioButton rdbMale = view.findViewById(R.id.rbm);
        final RadioButton rdbFemale = view.findViewById(R.id.rbf);
        final LoadingView prog = view.findViewById(R.id.loading);
        prog.stop();
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
                disTv.setText("");
                adapter = new SimpleChipAdapter(Logic.symptomObjects);
                cvTag.setAdapter(adapter);
                cvTag.refreshDrawableState();
                adapter.refresh();
                prog.stop();
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
                prog.start();
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
                Logic.contactServer(getContext(), hm, new Logic.DoWithResult() {
                    @Override
                    public void start(String param) {
                        try {
                            doWithResponse(param);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

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
        TextView disTv = view.findViewById(R.id.disTV);
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "ara-hamah-homs.ttf");
            rbm.setTypeface(font);
            rbf.setTypeface(font);
            tv.setTypeface(font);
            et.setTypeface(font);
            add.setTypeface(font);
            cancel.setTypeface(font);
            next.setTypeface(font);
            disTv.setTypeface(font);
        }

    }


