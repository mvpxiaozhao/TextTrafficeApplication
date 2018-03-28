package com.example.texttrafficeapplication.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.texttrafficeapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 赵洪斌 on 2017/10/16.
 */

public class Fragment_06 extends Fragment {
    private ExpandableListView el;
    private int number;
    private int distance3;
    private int distance4;
    private int distance2;
    private int distance1;
    private int[] distance;
    private TextView tvTitle;
    private TextView station;
    private TextView distanceCar;
    private String url;
    private List<Map<String, Object>> group;
    private List<List<Map<String, Object>>> childDate;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_06, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        el = (ExpandableListView) getActivity().findViewById(R.id.el);

        url = "http://192.168.1.102:8080/transportservice/type/jason/action/GetBusStationInfo.do";
        JSONObject jsonObject = new JSONObject();
        number = 1;
        try {
            jsonObject.put("BusStationId", number);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        getValue(url,jsonObject);

    }

    private void getValue(String url, JSONObject jsonObject) {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {

            private JSONArray jsonArray;

            @Override
            public void onResponse(JSONObject jsonObject) {
                String serverinfo = jsonObject.optString("serverinfo");
                Log.v("111",serverinfo);
                try {
                    jsonArray = new JSONArray(serverinfo);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (number ==1){
                    try {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(0);
                        distance1 = jsonObject1.getInt("Distance");
                        JSONObject jsonObject2 = jsonArray.getJSONObject(1);
                        distance2 = jsonObject2.getInt("Distance");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    nextDate();
                }else if (number ==2){
                    try {
                        JSONObject jsonObject3 = jsonArray.getJSONObject(0);
                        distance3 = jsonObject3.getInt("Distance");
                        JSONObject jsonObject4 = jsonArray.getJSONObject(1);
                        distance4 = jsonObject4.getInt("Distance");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    makeDate();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getContext(), "失败", Toast.LENGTH_SHORT).show();

            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    private void makeDate() {
        group = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        map.put("name_group","一号公交站");
        group.add(map);
        map = new HashMap<>();
        map.put("name_group","二号公交站");
        group.add(map);

        if (distance1>distance2){
            int tem = distance1;
            distance1 = distance2;
            distance2 = tem;
        }
        if (distance3>distance4){
            int tem = distance3;
            distance3=distance4;
            distance4 =tem;
        }

        childDate = new ArrayList<>();


        List<Map<String,Object>> child = new ArrayList<>();
        Map<String,Object> map_child = new HashMap<>();
        map_child.put("distance",String.valueOf(distance1));
        map_child.put("name","一号公交车");
        child.add(map_child);
        map_child = new HashMap<>();
        map_child.put("name","二号公交车");
        map_child.put("distance",String.valueOf(distance2));
        child.add(map_child);

        childDate.add(child);
        Log.v("test", String.valueOf(distance2));

        List<Map<String,Object>> child2= new ArrayList<>();
        Map<String,Object> map_child2 = new HashMap<>();
        map_child2.put("distance",String.valueOf(distance3));
        map_child2.put("name","一号公交车");
        child2.add(map_child2);

        map_child2 = new HashMap<>();
        map_child2.put("name","二号公交车");
        map_child2.put("distance",String.valueOf(distance4));
        child2.add(map_child2);

        childDate.add(child2);

        MyAdapter myAdapter = new MyAdapter();
        el.setAdapter(myAdapter);

    }

    private void nextDate() {
        number ++;
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("BusStationId", number);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        getValue(url,jsonObject);
    }
    class MyAdapter extends BaseExpandableListAdapter{

        @Override
        public int getGroupCount() {
            return group.size();
        }

        @Override
        public int getChildrenCount(int i) {
            return childDate.get(i).size();
        }

        @Override
        public Object getGroup(int i) {
            return group.get(i);
        }

        @Override
        public Object getChild(int i, int i1) {
            return childDate.get(i1);//???
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
            view = View.inflate(getContext(),R.layout.fragment4_item1,null);
            tvTitle = (TextView)view. findViewById(R.id.tv_title);
            tvTitle.setText(group.get(i).get("name_group").toString());
            return view;
        }

        @Override
        public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
            view = View.inflate(getContext(),R.layout.fragmet4_item2,null);

            station = (TextView) view.findViewById(R.id.station);
            distanceCar = (TextView)  view.findViewById(R.id.distance_car);
            distanceCar.setText(childDate.get(i).get(i1).get("distance").toString());
            station.setText(childDate.get(i).get(i1).get("name").toString());
            return view;
        }

        @Override
        public boolean isChildSelectable(int i, int i1) {
            return false;
        }
    }
}

