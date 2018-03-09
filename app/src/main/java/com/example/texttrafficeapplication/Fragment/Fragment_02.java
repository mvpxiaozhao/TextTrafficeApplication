package com.example.texttrafficeapplication.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.texttrafficeapplication.R;
import com.example.texttrafficeapplication.UrlBean;
import com.example.texttrafficeapplication.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 赵洪斌 on 2017/10/16.
 */

public class Fragment_02 extends Fragment {
    private ExpandableListView exlv;
    private int stationId;
    private int netFlag;
    private String urlHost;
    private List<Map<String, Object>> groupDate;
    private List<List<Map<String, Object>>> childDate;

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_02, container, false);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        exlv = (ExpandableListView) getActivity().findViewById(R.id.exlv);
        UrlBean connection = Util.loadSetting(getContext());
        urlHost = "http://"+connection.getUrl()+":"+connection.getPort()+"/tr";
        stationId = 1;
        netFlag = 1;
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("BusStationId",stationId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        intent(urlHost,jsonObject);


    }

    private void intent(String urlHost, JSONObject jsonObject) {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, urlHost, jsonObject, new Response.Listener<JSONObject>() {
            private String distance2;
            private String distance1;
            private String distance3;
            private String distance4;
            private JSONObject jo1;
            private JSONObject jo2;
            private JSONObject jo3;
            private JSONObject jo4;
            private JSONArray jsonArray;
            private String serverinfo;

            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    serverinfo = jsonObject.optString("serverinfo");
                    jsonArray = new JSONArray(serverinfo);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (netFlag ==1){

                    try {
                        jo1 = jsonArray.getJSONObject(0);
                        jo2 = jsonArray.getJSONObject(1);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    distance1 = jo1.optString("Distance");
                    distance2 = jo2.optString("Distance");
                    nextStation();
                }else if (netFlag ==2){
                    try {
                        jo3 = jsonArray.getJSONObject(0);
                        jo4 = jsonArray.getJSONObject(1);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    distance3 = jo3.optString("Distance");
                    distance4 = jo4.optString("Distance");
                    paixu(distance1,distance2,distance3,distance4);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    private void paixu(String distance1, String distance2, String distance3, String distance4) {
        int d1 = Integer.parseInt(distance1);
        int d2 = Integer.parseInt(distance2);
        int d3 = Integer.parseInt(distance3);
        int d4 = Integer.parseInt(distance4);

        int time1 = d1/(2000/60);
        int time2 = d2/(2000/60);
        int time3 = d3/(2000/60);
        int time4 = d4/(2000/60);

        if (d1>d2){
            int d = d1;
            d1 = d2;
            d2 = d;
        }
        if (d3>d4){
            int d = d3;
            d3 = d4;
            d4 = d;
        }

        groupDate = new ArrayList<>();
        childDate = new ArrayList<>();

        Map<String,Object> date = new HashMap<>();
        date.put("name","中医院");
        groupDate.add(date);

        date = new HashMap<>();
        date.put("name","县医院");
        groupDate.add(date);

        List<Map<String,Object>> child = new ArrayList<>();
        Map<String,Object> son  = new HashMap<>();

        son.put("time",time1);
        son.put("distance",d1);
        son.put("number","101人");
        child.add(son);

        son  = new HashMap<>();
        son.put("time",time2);
        son.put("distance",d2);
        son.put("number","101人");
        child.add(son);
        childDate.add(child);

        child = new ArrayList<>();
        son  = new HashMap<>();
        son.put("time",time3);
        son.put("distance",d3);
        son.put("number","101人");
        child.add(son);

        son  = new HashMap<>();
        son.put("time",time4);
        son.put("distance",d4);
        son.put("number","101人");
        child.add(son);
        childDate.add(child);

        exlv.setAdapter(new BaseExpandableListAdapter() {
            @Override
            public int getGroupCount() {
                return groupDate.size();
            }

            @Override
            public int getChildrenCount(int i) {
                return groupDate.get(i).size();
            }

            @Override
            public Object getGroup(int i) {
                return groupDate.get(i);
            }

            @Override
            public Object getChild(int i, int i1) {
                return groupDate.get(i).get(i1);
            }

            @Override
            public long getGroupId(int i) {
                return i;
            }

            @Override
            public long getChildId(int i, int i1) {
                return i1;
            }

            @Override
            public boolean hasStableIds() {
                return false;
            }

            @Override
            public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
                 ImageView imageView;
                 TextView text;

                if (view ==null){
                    view = View.inflate(getContext(),R.layout.item,null);
                }
                imageView = (ImageView) view.findViewById(R.id.imageView);
                text = (TextView) view.findViewById(R.id.text);
                if (b){
                    imageView.setImageResource(R.drawable.jian);
                }else {
                    imageView.setImageResource(R.drawable.jia);
                }
                text.setText(groupDate.get(i).toString());
                return view;
            }

            @Override
            public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {

                 ImageView imageView;
                 TextView number;
                 TextView time;
                 TextView distance;
                if (view == null){
                    view = View.inflate(getContext(),R.layout.item2,null);
                }
                imageView = (ImageView)view. findViewById(R.id.imageView);
                number = (TextView)view. findViewById(R.id.number);
                time = (TextView)view. findViewById(R.id.time);
                distance = (TextView) view.findViewById(R.id.distance);

                number.setText(childDate.get(i).get(i1).get("number").toString());
                time.setText(childDate.get(i).get(i1).get("time").toString());
                distance.setText(childDate.get(i).get(i1).get("distance").toString());
                return view;
            }

            @Override
            public boolean isChildSelectable(int i, int i1) {
                return false;
            }
        });


    }

    private void nextStation() {
        stationId++;
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("BusStationId",stationId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        netFlag = 2;

        intent(urlHost,jsonObject);
    }
}
