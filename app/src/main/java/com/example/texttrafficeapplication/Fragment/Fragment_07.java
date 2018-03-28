package com.example.texttrafficeapplication.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.texttrafficeapplication.R;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by Administrator on 2018/3/22.
 */
public class Fragment_07 extends Fragment {
    private TextView tvFrag7Title;
    private ViewPager viewPagerFrag7;
    private TextView tvFrag7Yuan01;
    private TextView tvFrag7Yuan02;
    private TextView tvFrag7Yuan03;
    List<Fragment> fragmentLists = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment7,container,false);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tvFrag7Title = (TextView) getActivity().findViewById(R.id.tv_frag7_title);
        viewPagerFrag7 = (ViewPager) getActivity().findViewById(R.id.viewPager_frag7);
        tvFrag7Yuan01 = (TextView) getActivity().findViewById(R.id.tv_frag7_yuan01);
        tvFrag7Yuan02 = (TextView) getActivity().findViewById(R.id.tv_frag7_yuan02);
        tvFrag7Yuan03 = (TextView) getActivity().findViewById(R.id.tv_frag7_yuan03);
        fragmentLists.add(new FragChart01());
        fragmentLists.add(new FragChart02());
        fragmentLists.add(new FragChart03());
        myAdpter adpter = new myAdpter(getFragmentManager(),fragmentLists);
        viewPagerFrag7.setAdapter(adpter);
        tvFrag7Yuan01.setBackgroundResource(R.drawable.text_yuan);
        tvFrag7Yuan02.setBackgroundResource(R.drawable.text_yuan_0);
        tvFrag7Yuan03.setBackgroundResource(R.drawable.text_yuan_0);
        viewPagerFrag7.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int  sss=viewPagerFrag7.getCurrentItem();
                switch (sss){
                    case 0:
                        tvFrag7Yuan01.setBackgroundResource(R.drawable.text_yuan);
                        tvFrag7Title.setText("pm.2.5");
                        tvFrag7Yuan02.setBackgroundResource(R.drawable.text_yuan_0);
                        tvFrag7Yuan03.setBackgroundResource(R.drawable.text_yuan_0);
                        break;
                    case 1:
                        tvFrag7Title.setText("LightIntensity");
                        tvFrag7Yuan02.setBackgroundResource(R.drawable.text_yuan);
                        tvFrag7Yuan01.setBackgroundResource(R.drawable.text_yuan_0);
                        tvFrag7Yuan03.setBackgroundResource(R.drawable.text_yuan_0);
                        break;
                    case 2:
                        tvFrag7Title.setText("temperature");
                        tvFrag7Yuan03.setBackgroundResource(R.drawable.text_yuan);
                        tvFrag7Yuan02.setBackgroundResource(R.drawable.text_yuan_0);
                        tvFrag7Yuan01.setBackgroundResource(R.drawable.text_yuan_0);
                        break;

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    class myAdpter extends FragmentPagerAdapter{

        public myAdpter(FragmentManager fm,List<Fragment> list) {
            super(fm);
            list = fragmentLists;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentLists.get(position);
        }

        @Override
        public int getCount() {
            return fragmentLists.size();
        }
    }
}

