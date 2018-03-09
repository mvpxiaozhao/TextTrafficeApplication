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
    List<Map<String, Object>> list;
    LayoutInflater layoutInflater;
    But but;
    CCC ccc;

    public adper(Context context, List<Map<String, Object>> list, But but, CCC ccc) {
        layoutInflater = LayoutInflater.from(context);
        this.list = list;
        this.but = but;
        this.ccc = ccc;

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
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.adasd, null);
            viewHolder.id = (TextView) convertView.findViewById(R.id.id);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.image);
            viewHolder.plateNamber = (TextView) convertView.findViewById(R.id.plateNamber);
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.money = (TextView) convertView.findViewById(R.id.money);
            viewHolder.xuzhong = (CheckBox) convertView.findViewById(R.id.xuzhong);
            viewHolder.chongzhi = (Button) convertView.findViewById(R.id.chongzhi);
            convertView.setTag(viewHolder);
        }


        viewHolder.id.setText(position + 1);
        viewHolder.plateNamber.setText(list.get(position).get("plateNamber").toString());
        viewHolder.name.setText(list.get(position).get("name").toString());
        viewHolder.money.setText(list.get(position).get("balance").toString());

        viewHolder.xuzhong.setOnCheckedChangeListener(ccc);
        viewHolder.chongzhi.setOnClickListener(but);
        viewHolder.xuzhong.setTag(position);
        viewHolder.chongzhi.setTag(position);
        return convertView;
    }

    class ViewHolder {
        private TextView id;
        private ImageView image;
        private TextView plateNamber;
        private TextView name;
        private TextView money;
        private CheckBox xuzhong;
        private Button chongzhi;
    }
}
