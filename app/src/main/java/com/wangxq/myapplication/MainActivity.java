package com.wangxq.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.wangxq.mylibrary.dialog.DialogTools;
import com.wangxq.mylibrary.dialog.NoticeDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DialogTools.INSTANCE.showLoading(getSupportFragmentManager(),"加载中...");


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                DialogTools.INSTANCE.dismissLoading();
//                DialogTools.INSTANCE.showLoading(getSupportFragmentManager(),"xxxxxxxxxx");
//                DialogTools.INSTANCE.updateLoadingContent("xxxxxxxxxx");
                Boolean aBoolean = DialogTools.INSTANCE.loadingDialogIsShowing();
                if(aBoolean){
                    DialogTools.INSTANCE.updateLoadingContent("xxxxxxxxxx");
                }else {

                }
                Log.e("update",aBoolean+"不更新吗");
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