package com.example.texttrafficeapplication.cjass;

import android.support.annotation.IdRes;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioGroup;

/**
 * Created by 赵洪斌 on 2017/10/22.
 */

public abstract class CCC implements CompoundButton.OnCheckedChangeListener {
    protected abstract void ddddd(int pp, boolean bb);

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        ddddd((Integer) buttonView.getTag(),isChecked);
    }
}
