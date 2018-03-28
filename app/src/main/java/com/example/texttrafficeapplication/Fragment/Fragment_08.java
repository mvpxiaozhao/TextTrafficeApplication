package com.example.texttrafficeapplication.Fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.texttrafficeapplication.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by 赵洪斌 on 2017/10/16.
 */

public class Fragment_08 extends Fragment {
    private VideoView vv;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_08, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        vv = (VideoView) getActivity().findViewById(R.id.vv);
        String uri = "android.resource://" + getActivity().getPackageName() + "/" + R.raw.aa;
        MediaController mediaController = new MediaController(getContext());
        mediaController.setMediaPlayer(vv);
        vv.setMediaController(mediaController);
        vv.setVideoURI(Uri.parse(uri));
        vv.start();


        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                vv.resume();
            }
        },5000);









    }
}

