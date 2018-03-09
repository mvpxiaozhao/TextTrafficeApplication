package com.example.texttrafficeapplication.Fragment;


import android.app.Dialog;
import android.os.Bundle;
import android.service.voice.VoiceInteractionSession;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.texttrafficeapplication.R;
import com.example.texttrafficeapplication.UrlBean;
import com.example.texttrafficeapplication.Util;
import com.example.texttrafficeapplication.cjass.But;
import com.example.texttrafficeapplication.cjass.CCC;
import com.example.texttrafficeapplication.cjass.Carinformation;
import com.example.texttrafficeapplication.cjass.Sql;
import com.example.texttrafficeapplication.cjass.adper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 赵洪斌 on 2017/10/16.
 */

public class Fragment_01 extends Fragment  implements View.OnClickListener{
    private Button plcz;
    private Button czjl;
    private ListView lineF1;
    Sql sql;
    UrlBean urlBean;
    String Http1 = "";
    String Http2 = "";
    int carhao = 1;
    int biao = 1;
    List<Map<String, Object>> list = new ArrayList<>();
    private boolean number1;
    private boolean number2;
    private boolean nubmer3;
    private boolean number4;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_01, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        plcz = (Button) getActivity().findViewById(R.id.button1);
        czjl = (Button) getActivity().findViewById(R.id.button2);
        lineF1 = (ListView) getActivity().findViewById(R.id.line_f1);
        urlBean = Util.loadSetting(getActivity());
        Http1 = "http://" + urlBean.getUrl() + ":" + urlBean.getPort() + "/transportservice/type/jason/action/GetCarAccountBalance.do";
        Http2 = "http://" + urlBean.getUrl() + ":" + urlBean.getPort() + "/transportservice/type/jason/action/SetCarAccountRecharge.do";
        sql = new Sql(getActivity());
        sql.openDB();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("CarId", carhao);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        biao = 1;
        Log.i("asfdasdad", Http1 + jsonObject);
        intent(Http1, jsonObject);
        plcz.setOnClickListener(this);
        czjl.setOnClickListener(this);
    }

    private void intent(String http1, JSONObject jsonObject) {
        RequestQueue re = Volley.newRequestQueue(getActivity());
        JsonObjectRequest js = new JsonObjectRequest(Request.Method.POST, http1, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject object) {
                String sdf = String.valueOf(object);
                Log.i("asfdasdad", sdf);
                if (biao == 1) {
                    shuju(sdf);
                } else if (biao == 2) {
                    Toast.makeText(getContext(),"充值成功",Toast.LENGTH_LONG).show();
                    getFragmentManager().beginTransaction().replace(R.id.maincontent, new Fragment_01()).commit();
                }else if (biao == 3){
                    Toast.makeText(getContext(),"批量充值成功",Toast.LENGTH_LONG).show();
                    getFragmentManager().beginTransaction().replace(R.id.maincontent, new Fragment_01()).commit();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.i("asfdasdad", "asdasd");
            }
        });
        re.add(js);
    }

    private void shuju(String js) {

        try {
            JSONObject jsonObject = new JSONObject(js);
            String bb = jsonObject.getString("serverinfo");
            JSONObject jsonObjecttt = new JSONObject(bb);
            int balance = jsonObjecttt.getInt("Balance");
            Log.i("asfdasdad", Http1);
            Map<String, Object> map = new HashMap<>();
            map.put("palateNamber", carhao);
            map.put("name", "张三");
            map.put("image", R.drawable.change);
            map.put("balance", balance);
            list.add(map);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        carhao++;
        if (carhao <= 4) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("CarId", carhao);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            biao = 1;
            Log.i("asfdasdad", String.valueOf(jsonObject));
            intent(Http1, jsonObject);
        } else {

            adper aedper = new adper(getActivity(), list, but, ccc);
            lineF1.setAdapter(aedper);
        }
    }

    But but = new But() {
        @Override
        public void adsas(int p, View v) {
            die(p + 1,0);

        }
    };

    private void die(final int i,int money) {
        final Dialog di = new Dialog(getActivity());
        di.setTitle("ccccc");
        di.getWindow().setContentView(R.layout.dasd);
        TextView textView8 = (TextView) di.getWindow().findViewById(R.id.textView8);
        TextView textView7 = (TextView) di.getWindow().findViewById(R.id.textView7);
        final EditText editText = (EditText) di.getWindow().findViewById(R.id.editText);
        Button buttonb = (Button) di.getWindow().findViewById(R.id.buttonb);
        Button buttont = (Button) di.getWindow().findViewById(R.id.buttont);
        buttonb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int ssdd = Integer.parseInt(editText.getText().toString());
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("CarId", i);
                    jsonObject.put("Money", ssdd);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                biao = 2;
                Log.i("asfdasdad", Http2 + jsonObject);
                intent(Http2, jsonObject);
                Carinformation ccc = new Carinformation("sdad", "asdasd", "ssadasd", "sadasd");
                sql.Insert(ccc);
                di.dismiss();
            }
        });
        di.show();
    }

    CCC ccc = new CCC() {
        @Override
        protected void ddddd(int pp, boolean bb) {
            switch (pp){
                case 0:
                    number1 = bb;
                    break;
                case 1:
                    number2 = bb;
                    break;
                case 2:
                    nubmer3 = bb;
                    break;
                case 3:
                    number4 = bb;
                    break;
            }

        }
    };

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.plcz:
                plczMethod();
                
        }
    }

    private void plczMethod() {
        Dialog dialog = new Dialog(getContext());
        dialog.getWindow().setContentView(R.layout.dasd);
        dialog.setTitle("批量充值");
        dialog.show();
        final EditText editText = (EditText) dialog.getWindow().findViewById(R.id.editText);
        Button chongzhi = (Button) dialog.getWindow().findViewById(R.id.buttonb);
        Button cancle = (Button) dialog.getWindow().findViewById(R.id.buttont);
        chongzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int money = Integer.parseInt(editText.getText().toString().trim());
                if (number1){
                    sql(1,money);
                }
                if (number2){
                    sql(2,money);
                }
                if (nubmer3){
                    sql(3,money);
                }
                if (number4){
                    sql(4,money);
                }
            }
        });

    }

    private void sql(int i, int money) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("CarId",i);
            jsonObject.put("CarId",money);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        biao = 3;
        intent(Http2,jsonObject);
    }
}
