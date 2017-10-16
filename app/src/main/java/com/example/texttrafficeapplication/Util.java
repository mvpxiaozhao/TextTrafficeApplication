package com.example.texttrafficeapplication;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class Util {
    public static String urlHttpbao = "setting";
    public static String urlHttp = "ipUrl";
    public static String urlPort = "ipPort";
    public static String time = "time";
    public static String timebao = "timebao";
    public static int timedd;

    public static void saveSetting(String ipUrl, String ipPort, Context context) {
        SharedPreferences spSettingSave = context.getSharedPreferences(urlHttpbao, MODE_PRIVATE);// 将需要记录的数据保存在setting.xml文件中
        SharedPreferences.Editor editor = spSettingSave.edit();
        editor.putString(urlHttp, ipUrl);
        editor.putString(urlPort, ipPort);
        editor.commit();
    }

    public static void saveSettingtime(int ipPort, Context context) {
        SharedPreferences spSettingSave = context.getSharedPreferences(timebao, MODE_PRIVATE);// 将需要记录的数据保存在setting.xml文件中
        SharedPreferences.Editor editor = spSettingSave.edit();
        editor.putInt(time, ipPort);
        editor.commit();
    }

    public static UrlBean loadSetting(Context context) {
        UrlBean urlBean = new UrlBean();
        SharedPreferences loadSettingLoad = context.getSharedPreferences(urlHttpbao, MODE_PRIVATE);
        urlBean.setUrl(loadSettingLoad.getString(urlHttp, ""));
        urlBean.setPort(loadSettingLoad.getString(urlPort, ""));
        return urlBean;
    }

    public static int loadSettingtime(Context context) {
        SharedPreferences loadSettingLoad = context.getSharedPreferences(timebao, MODE_PRIVATE);
        timedd = loadSettingLoad.getInt(time, 0);
        return timedd;
    }
}
