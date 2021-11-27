package com.wangxq.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

import com.wangxq.mylibrary.dialog.DialogTools;
import com.wangxq.mylibrary.dialog.NoticeDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DialogTools.INSTANCE.showCommomDialog(getSupportFragmentManager(),new NoticeDialog.Builder());
        DialogTools.INSTANCE.showCommomDialog(getSupportFragmentManager(),new NoticeDialog.Builder());
        DialogTools.INSTANCE.showCommomDialog(getSupportFragmentManager(),new NoticeDialog.Builder());
        DialogTools.INSTANCE.showLoading(getSupportFragmentManager());
        DialogTools.INSTANCE.showLoading(getSupportFragmentManager());
        DialogTools.INSTANCE.showLoading(getSupportFragmentManager());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                DialogTools.INSTANCE.dismissLoading();
            }
        },5000);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                DialogTools.INSTANCE.dismissCommonDialog();
            }
        },10000);
    }
}