package com.wangxq.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.wangxq.mylibrary.dialog.DialogTools;
import com.wangxq.mylibrary.dialog.NoticeDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DialogTools.INSTANCE.showCommomDialog(getSupportFragmentManager(),new NoticeDialog.Builder());
    }
}