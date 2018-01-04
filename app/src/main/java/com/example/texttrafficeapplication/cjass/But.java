package com.example.texttrafficeapplication.cjass;

import android.view.View;

/**
 * Created by 赵洪斌 on 2017/10/22.
 */

public abstract class But implements View.OnClickListener {
    public  abstract void adsas(int p,View v);
    @Override
    public void onClick(View v) {
        adsas((Integer) v.getTag(),v);
    }
}
