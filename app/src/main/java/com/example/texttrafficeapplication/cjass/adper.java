package com.example.texttrafficeapplication.cjass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.texttrafficeapplication.R;

import java.util.List;
import java.util.Map;

/**
 * Created by 赵洪斌 on 2017/10/22.
 */

public class adper extends BaseAdapter {
    List<Map<String,Object>> list;
    LayoutInflater layoutInflater;
    But but;
    CCC ccc;
    public adper(Context context, List<Map<String, Object>> list,But but,CCC ccc){
        layoutInflater=LayoutInflater.from(context);
        this.list=list;
        this.but=but;this.ccc=ccc;

    }
  public  class   hjhjh{
        private TextView textView2;
        private ImageView imageView3;
        private TextView textView5;
        private TextView textView4;
        private CheckBox checkBox2;
        private Button button;
      private TextView textView6;

    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        hjhjh hjhjh=new hjhjh();
        convertView=layoutInflater.inflate(R.layout.adasd,null);
        hjhjh.textView2 = (TextView) convertView.findViewById(R.id.textView2);
        hjhjh.imageView3 = (ImageView) convertView.findViewById(R.id.imageView3);
        hjhjh.textView5 = (TextView) convertView.findViewById(R.id.textView5);
        hjhjh.textView4 = (TextView) convertView.findViewById(R.id.textView4);
        hjhjh.checkBox2 = (CheckBox) convertView.findViewById(R.id.checkBox2);
        hjhjh.textView6 = (TextView) convertView.findViewById(R.id.textView6);
        hjhjh.button = (Button) convertView.findViewById(R.id.button);
        hjhjh.textView2.setText(list.get(position).get("k1").toString());
        hjhjh.textView5.setText(list.get(position).get("k2").toString());
        hjhjh.textView4.setText(list.get(position).get("k3").toString());
        hjhjh.textView6.setText(list.get(position).get("k4").toString());
        hjhjh.checkBox2.setOnCheckedChangeListener(ccc);
        hjhjh.button.setOnClickListener(but);
        hjhjh.checkBox2.setTag(position);
        hjhjh.button.setTag(position);
        return convertView;
    }
}
