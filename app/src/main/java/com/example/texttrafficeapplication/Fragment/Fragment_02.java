package com.example.texttrafficeapplication.Fragment;

import android.app.Dialog;
        import android.app.NotificationManager;
        import android.app.PendingIntent;
        import android.content.Context;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.os.Bundle;
        import android.os.Looper;
        import android.support.annotation.Nullable;
        import android.support.v4.app.Fragment;
        import android.support.v4.app.NotificationCompat;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.SimpleAdapter;
        import android.widget.Spinner;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.android.volley.Request;
        import com.android.volley.RequestQueue;
        import com.android.volley.Response;
        import com.android.volley.VolleyError;
        import com.android.volley.toolbox.JsonObjectRequest;
        import com.android.volley.toolbox.Volley;
import com.example.texttrafficeapplication.MainActivity;
import com.example.texttrafficeapplication.R;

import org.json.JSONException;
        import org.json.JSONObject;

        import java.text.SimpleDateFormat;
        import java.util.ArrayList;
        import java.util.Date;
        import java.util.List;
        import java.util.Timer;
        import java.util.TimerTask;

        import static android.content.Context.NOTIFICATION_SERVICE;

public class Fragment_02 extends Fragment {
    private TextView zhuang;
    private Spinner sppin;
    private Button chaxun;
    private Button chngzhi;
    List<String> listss=new ArrayList<>();
    String dddd[]={"1","2","3","4"};
    private EditText ededd;
    int eeeeeeee=0;
    DBAdapter dbAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        zhuang = (TextView) getActivity().findViewById(R.id.zhuang);
        sppin = (Spinner) getActivity().findViewById(R.id.sppin);
        chaxun = (Button) getActivity().findViewById(R.id.chaxun);
        chngzhi = (Button) getActivity().findViewById(R.id.chngzhi);
        ededd = (EditText) getActivity().findViewById(R.id.ededd);
        listss.add("1");
        listss.add("2");
        listss.add("3");
        listss.add("4");
        dbAdapter=new DBAdapter(getContext());
        dbAdapter.openDB();
        ArrayAdapter arrayAdapter=new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,listss);
        sppin.setAdapter(arrayAdapter);
        final Dialog  dialog=new Dialog(getContext());
        dialog.getWindow().setContentView(R.layout.fragment_blankdddd);
        final EditText setttteeee = (EditText) dialog.getWindow().findViewById(R.id.setttteeee);
        Button  settt = (Button) dialog.getWindow().findViewById(R.id.settt);
        settt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dddd=setttteeee.getText().toString();
                if(!dddd.equals("")){
                    Log.i("sdfdfdf",dddd);
                    SharedPreferences sharedPreferences=getContext().getSharedPreferences("zhaohog", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putInt("eeeee", Integer.parseInt(dddd));
                    editor.commit();
                    dialog.dismiss();
                }else{
                    Toast.makeText(getContext(),"不能为空", Toast.LENGTH_SHORT).show();
                }

            }
        });
        dialog.show();


        chaxun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject jsonObject=new JSONObject();
                SharedPreferences sharedPreferences=getContext().getSharedPreferences("zhaohog", Context.MODE_PRIVATE);
                eeeeeeee=sharedPreferences.getInt("eeeee",0);
                int eee= Integer.parseInt(sppin.getSelectedItem().toString());
                try {
                    jsonObject.put("CarId",eee);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String ssss="http://192.168.1.102:8080/transportservice/type/jason/action/GetCarAccountBalance.do";
                RequestQueue requestQueue= Volley.newRequestQueue(getContext());
                JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, ssss, jsonObject, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Log.i("sdfdfdf", String.valueOf(jsonObject));
                        String fsdfsdf= String.valueOf(jsonObject);
                        try {
                            JSONObject jdd=new JSONObject(fsdfsdf);
                            String dddd=jdd.getString("serverinfo");
                            JSONObject www=new JSONObject(dddd);
                            int ddddee=www.getInt("Balance");
                            if(ddddee>eeeeeeee){
                            }else{
                                Log.i("sfdsdfsdf", String.valueOf(eeeeeeee));
                                Intent i = new Intent(getContext(), MainActivity.class);
                                PendingIntent pendingIntent = PendingIntent.getActivity(getContext(),0, i, 0);
                                NotificationManager nm = (NotificationManager) getActivity().getSystemService(NOTIFICATION_SERVICE);
                                NotificationCompat.Builder notifBuilder = new NotificationCompat.Builder(getContext())
                                        .setSmallIcon(R.mipmap.ic_launcher)
                                        .setContentTitle("余额不足")
                                        .setContentText("余额以低于"+eeeeeeee+"元").setAutoCancel(true)
                                        .addAction(R.mipmap.ic_launcher, "Notzuonotdied",pendingIntent);
                                assert nm != null;
                                nm.notify(0, notifBuilder.build());
                            }
                            zhuang.setText(ddddee+"元");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                });
                requestQueue.add(jsonObjectRequest);
            }
        });
        chngzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String ddddddd=ededd.getText().toString();

                if(!ddddddd.equals("")){
                    int rwer= Integer.parseInt(ddddddd);
                    if(rwer==0||rwer>999){
                        Toast.makeText(getContext(), "不能大于999", Toast.LENGTH_SHORT).show();

                    }else{
                        JSONObject jsonObject=new JSONObject();
                        final int eee= Integer.parseInt(sppin.getSelectedItem().toString());
                        try {
                            jsonObject.put("CarId",eee);
                            jsonObject.put("Money",rwer);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        String ssss="http://192.168.1.102:8080/transportservice/type/jason/action/SetCarAccountRecharge.do";
                        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
                        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, ssss, jsonObject, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject jsonObject) {
                                Log.i("sdfdfdf", String.valueOf(jsonObject));
                                long time =System.currentTimeMillis();
                                Date date=new Date(time);
                                SimpleDateFormat dateFormat=new SimpleDateFormat("HH.mm.ss");
                                String dddd=dateFormat.format(date);
                                rrrrtt urlBean=new rrrrtt(String.valueOf(eee),dddd,ddddddd);
                                dbAdapter.Insert(urlBean);
                                Toast.makeText(getContext(),"成功", Toast.LENGTH_SHORT).show();


                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                            }
                        });
                        requestQueue.add(jsonObjectRequest);
                    }

                }else{

                }

            }
        });
    }
}
