package com.example.texttrafficeapplication.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.texttrafficeapplication.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by 赵洪斌 on 2017/10/16.
 */

public class Fragment_03 extends Fragment {
    private Spinner spinner;
    private Button bt;
    private LinearLayout content;
    private TextView lukou;
    private TextView hongdeng;
    private TextView huangdeng;
    private TextView lvdeng;
    private String url;
    private int number_lukou;
    private String[] item;
    private int[] yellow;
    private int[] red;
    private int[] green;
    private int paixu_flag;
    private int[] number1;
    int yellowLight[] = new int[3];
    int redLight[] = new int[3];
    int greenLight[] = new int[3];
    int number_car[] = new int[3];
    private int netFlag = 1;
    private int green3;
    private int red3;
    private int yellow3;
    private int green2;
    private int red2;
    private int yellow2;
    private int green1;
    private int red1;
    private int yellow1;
    private JSONObject jsonObject1;
    private List<Map<String, String>> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_03, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        spinner = (Spinner) getActivity().findViewById(R.id.spinner);
        bt = (Button) getActivity().findViewById(R.id.bt);
        content = (LinearLayout) getActivity().findViewById(R.id.content);
        list = new ArrayList<>();

        item = new String[]{"路口升序", "路口降序", "红灯升序", "红灯降序", "黄灯升序", "黄灯降序", "绿灯升序", "绿灯降序"};
        ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, item);
        spinner.setAdapter(adapter);

        //网络通信
        url = "http://192.168.1.102:8080/transportservice/type/jason/action/GetTrafficLightConfigAction.do";



        //第一个路口
        number_lukou = 1;
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("TrafficLightId", number_lukou);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        getValue(jsonObject, url);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = spinner.getSelectedItem().toString();
                content.removeAllViews();

                if (s.equals(item[0])) {
                    paixu_flag = 0;
                    Log.v("spinner", "1");

                } else if (s.equals(item[1])) {
                    paixu_flag = 1;
                    Log.v("spinner", "2");

                } else if (s.equals(item[2])) {
                    Log.v("spinner", "3");
                    paixu_flag = 2;

                } else if (s.equals(item[3])) {
                    Log.v("spinner", "4");
                    paixu_flag = 3;

                } else if (s.equals(item[4])) {
                    Log.v("spinner", "5");
                    paixu_flag = 4;
                    Log.v("flag", String.valueOf(paixu_flag));

                } else if (s.equals(item[5])) {
                    paixu_flag = 5;

                } else if (s.equals(item[6])) {
                    paixu_flag = 6;

                } else if (s.equals(item[7])) {
                    paixu_flag = 7;
                }
                paixu(paixu_flag);
            }
        });


    }

    private void getValue(JSONObject jsonObject, String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject jsonObject) {
                String serverinfo = jsonObject.optString("serverinfo");
                Log.v("test", serverinfo);

                if (number_lukou == 1) {
                    try {
                        jsonObject1 = new JSONObject(serverinfo);
                        yellow1 = jsonObject1.getInt("YellowTime");
                        red1 = jsonObject1.optInt("RedTime");
                        green1 = jsonObject1.optInt("GreenTime");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    nextDate();
                } else if (number_lukou == 2) {
                    try {
                        jsonObject1 = new JSONObject(serverinfo);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    yellow2 = jsonObject1.optInt("YellowTime");
                    red2 = jsonObject1.optInt("RedTime");
                    green2 = jsonObject1.optInt("GreenTime");
                    nextDate();
                } else if (number_lukou == 3) {
                    try {
                        jsonObject1 = new JSONObject(serverinfo);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    yellow3 = jsonObject1.optInt("YellowTime");
                    red3 = jsonObject1.optInt("RedTime");
                    green3 = jsonObject1.optInt("GreenTime");

                    yellow = new int[]{yellow1, yellow2, yellow3};
                    red = new int[]{red1, red2, red3};
                    green = new int[]{green1, green2, green3};


                    for (int i=0;i<3;i++){
                        Map<String,String> map = new HashMap<>();
                        map.put("red", String.valueOf(red[i]));
                        map.put("lukou", String.valueOf(i+1));
                        map.put("green", String.valueOf(green[i]));
                        map.put("ye", String.valueOf(yellow[i]));
                        list.add(map);
                    }

                    addItemView();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                Toast.makeText(getContext(), "链接服务器失败", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }


    private void paixu(int flag) {
        if (flag == 0) {
            Collections.sort(list, new Comparator<Map<String, String>>() {
                @Override
                public int compare(Map<String, String> stringStringMap, Map<String, String> t1) {
                    return stringStringMap.get("lukou").compareTo(t1.get("lukou"));
                }
            });

        } else if (flag == 1) {
            Collections.sort(list, new Comparator<Map<String, String>>() {
                @Override
                public int compare(Map<String, String> stringStringMap, Map<String, String> t1) {
                    return t1.get("lukou").compareTo(stringStringMap.get("lukou"));
                }
            });
        } else if (flag == 2) {
            Collections.sort(list, new Comparator<Map<String, String>>() {
                @Override
                public int compare(Map<String, String> stringStringMap, Map<String, String> t1) {
                    return stringStringMap.get("red").compareTo(t1.get("red"));
                }
            });
        } else if (flag == 3) {
            Collections.sort(list, new Comparator<Map<String, String>>() {
                @Override
                public int compare(Map<String, String> stringStringMap, Map<String, String> t1) {
                    return t1.get("red").compareTo(stringStringMap.get("red"));
                }
            });
        } else if (flag == 4) {
            Collections.sort(list, new Comparator<Map<String, String>>() {
                @Override
                public int compare(Map<String, String> stringStringMap, Map<String, String> t1) {
                    return stringStringMap.get("ye").compareTo(t1.get("ye"));
                }
            });
        } else if (flag == 5) {
            Collections.sort(list, new Comparator<Map<String, String>>() {
                @Override
                public int compare(Map<String, String> stringStringMap, Map<String, String> t1) {
                    return t1.get("ye").compareTo(stringStringMap.get("ye"));
                }
            });
        } else if (flag == 6) {
            Collections.sort(list, new Comparator<Map<String, String>>() {
                @Override
                public int compare(Map<String, String> stringStringMap, Map<String, String> t1) {
                    return stringStringMap.get("green").compareTo(t1.get("green"));
                }
            });
        } else if (flag == 7) {
            Collections.sort(list, new Comparator<Map<String, String>>() {
                @Override
                public int compare(Map<String, String> stringStringMap, Map<String, String> t1) {
                    return t1.get("green").compareTo(stringStringMap.get("green"));
                }
            });
        }

        addItemView();

    }

    private void nextDate() {
        number_lukou++;
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("TrafficLightId", number_lukou);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        getValue(jsonObject, url);
    }

    private void addItemView() {

        View view1 = View.inflate(getContext(), R.layout.fragment_item, null);

        lukou = (TextView) view1.findViewById(R.id.lukou);
        hongdeng = (TextView) view1.findViewById(R.id.hongdeng);
        huangdeng = (TextView) view1.findViewById(R.id.huangdeng);
        lvdeng = (TextView) view1.findViewById(R.id.lvdeng);

        lukou.setText("路口");
        hongdeng.setText("红灯时长");
        huangdeng.setText("黄灯时长");
        lvdeng.setText("绿灯时长");
        content.addView(view1);

        for (int i = 0; i < 3; i++) {
            final View view = View.inflate(getContext(), R.layout.fragment_item, null);
            lukou = (TextView) view.findViewById(R.id.lukou);
            hongdeng = (TextView) view.findViewById(R.id.hongdeng);
            huangdeng = (TextView) view.findViewById(R.id.huangdeng);
            lvdeng = (TextView) view.findViewById(R.id.lvdeng);

            lukou.setText(list.get(i).get("lukou").toString());
            hongdeng.setText(list.get(i).get("red").toString());
            huangdeng.setText(list.get(i).get("ye").toString());
            lvdeng.setText(list.get(i).get("green").toString());
            content.addView(view);



        }
    }

}
