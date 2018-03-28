package com.example.texttrafficeapplication.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.texttrafficeapplication.R;
import com.example.texttrafficeapplication.UrlBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fragment_01 extends Fragment {
    private Spinner spinnerFrag4;
    private Button btnFrag4;
    private LinearLayout linearFrag4;
    List<String> lists;
    String[] line1s = {"序号","车号","充值金额","操作人","充值时间"};
    String eeeee="0";
    DBAdapter dbAdapter;
    List<Map<String,String>> liii;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment4, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        spinnerFrag4 = (Spinner) getActivity().findViewById(R.id.spinner_frag4);
        btnFrag4 = (Button) getActivity().findViewById(R.id.btn_frag4);
        linearFrag4 = (LinearLayout) getActivity().findViewById(R.id.linear_frag4);
        //spinner
        lists = new ArrayList<>();
        lists.add("时间升序");
        lists.add("时间降序");
        dbAdapter=new DBAdapter(getContext());
        dbAdapter.openDB();
        ArrayAdapter adapter = new ArrayAdapter(getActivity(),R.layout.support_simple_spinner_dropdown_item,lists);
        spinnerFrag4.setAdapter(adapter);
        btnFrag4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linearFrag4.removeAllViews();
                drawLines();
                liii=new ArrayList<>();
                if(eeeee.equals("111")){
                    rrrrtt[] rrrrtt= dbAdapter.qurryAll();
                    if(rrrrtt==null){

                    }else{
                        for(int i=0;i<rrrrtt.length;i++){
                            Map<String,String> map=new HashMap<>();
                            map.put("che",rrrrtt[i].getChehao());
                            map.put("time",rrrrtt[i].getTimetime());
                            map.put("ren","admin");
                            map.put("jin",rrrrtt[i].getJiner());
                            liii.add(map);
                        }
                    }


                    drawLine1();
                }else{


                    rrrrtt[] rrrrtt= dbAdapter.qurryAll();
                    if(rrrrtt==null){

                    }else {
                        for(int i=rrrrtt.length-1;i>=0;i--){
                        Map<String,String> map=new HashMap<>();
                        map.put("che",rrrrtt[i].getChehao());
                        map.put("time",rrrrtt[i].getTimetime());
                        map.put("ren","admin");
                        map.put("jin",rrrrtt[i].getJiner());
                        liii.add(map);
                    }
                    }


                    drawLine1();
                }
            }
        });
        spinnerFrag4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        eeeee="111";
                        break;
                    case 1:
                        eeeee="222";
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }
    public void drawLine1(){
        for(int tw=0;tw<liii.size();tw++){
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.line1_frag4,null);
            TextView textView = (TextView) view.findViewById(R.id.tv_line1_1);
            textView.setText(""+(tw+1));
            textView =(TextView) view.findViewById(R.id.tv_line1_2);
            textView.setText(liii.get(tw).get("che"));
            textView =(TextView) view.findViewById(R.id.tv_line1_3);
            textView.setText(liii.get(tw).get("jin"));
            textView =(TextView) view.findViewById(R.id.tv_line1_4);
            textView.setText(liii.get(tw).get("ren"));
            textView = (TextView)view.findViewById(R.id.tv_line1_5);
            textView.setText(liii.get(tw).get("time"));
            linearFrag4.addView(view);
        }

    }
    public void drawLines(){
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.line1_frag4,null);
        TextView textView = (TextView)view.findViewById(R.id.tv_line1_1);
        textView.setText("序号");
        textView =(TextView) view.findViewById(R.id.tv_line1_2);
        textView.setText(line1s[1]);
        textView = (TextView)view.findViewById(R.id.tv_line1_3);
        textView.setText(line1s[2]);
        textView = (TextView)view.findViewById(R.id.tv_line1_4);
        textView.setText(line1s[3]);
        textView =(TextView) view.findViewById(R.id.tv_line1_5);
        textView.setText(line1s[4]);
        linearFrag4.addView(view);
    }
}
