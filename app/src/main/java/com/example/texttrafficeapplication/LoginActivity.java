package com.example.texttrafficeapplication;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {

    EditText editText_username, editText_userpwd;
    TextView textView_tile;
    CheckBox checkBox_r;
    LinearLayout linearLayout_net_setting;
    String userInfo = "userInfo";
    String uName = "uName";
    String uPwd = "uPwd";
    UrlBean urlBean;
    private String urlHttp;
    private String urlPort = "80";
    String d="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        urlBean = Util.loadSetting(LoginActivity.this);
    }

    private void initView() {
        linearLayout_net_setting = (LinearLayout) findViewById(R.id.net_setting);
        editText_username = (EditText) findViewById(R.id.editText_username);
        editText_userpwd = (EditText) findViewById(R.id.editText_userpwd);
        textView_tile = (TextView) findViewById(R.id.textView_title);
        textView_tile.setText("登录页面");
        checkBox_r = (CheckBox) findViewById(R.id.checkBox);

        Button button_ok = (Button) findViewById(R.id.button_ok);
        button_ok.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View view) {
                System.out.println("button Click OK");

//                String stUsername = editText_username.getText().toString();
//                String stUserpwd = editText_userpwd.getText().toString();

//                if (stUsername.equals("")){
//                    Toast.makeText(LoginActivity.this,"请输入用户名",Toast.LENGTH_LONG).show();
//                    return;
//                }else if(stUserpwd.equals("")){
//                    Toast.makeText(LoginActivity.this,"请输入密码",Toast.LENGTH_LONG).show();
//                    return;
//                }
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

//                queryVolley(stUsername,stUserpwd);
            }
        });


        linearLayout_net_setting.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                System.out.println("linearLayout_net_setting Click OK");
                final Dialog urlSettingDialog = new Dialog(LoginActivity.this);
                urlSettingDialog.setTitle("Setting");
                urlSettingDialog.getWindow().setContentView(R.layout.login_setting);
                final EditText edit_urlHttp = (EditText) urlSettingDialog.getWindow().findViewById(R.id.edit_setting_url);
                final EditText edit_urlPort = (EditText) urlSettingDialog.getWindow().findViewById(R.id.edit_setting_port);
                edit_urlHttp.setText(urlBean.getUrl());
                edit_urlPort.setText(urlBean.getPort());
                Button save = (Button) urlSettingDialog.getWindow().findViewById(R.id.button_save);
                Button cancel = (Button) urlSettingDialog.getWindow().findViewById(R.id.button_cancle);
                save.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        urlHttp = edit_urlHttp.getText().toString();
                        urlPort = edit_urlPort.getText().toString();
                        if (urlHttp == null || urlHttp.equals("")) {
                            Toast.makeText(LoginActivity.this, R.string.error_ip, Toast.LENGTH_LONG).show();
                        } else {
                            Util.saveSetting(urlHttp, urlPort, LoginActivity.this);
                            urlSettingDialog.dismiss();
                        }
                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        urlSettingDialog.dismiss();
                    }
                });
                urlSettingDialog.show();
            }
        });
    }
}
