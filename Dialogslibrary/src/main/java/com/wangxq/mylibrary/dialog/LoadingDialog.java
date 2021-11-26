package com.wangxq.mylibrary.dialog;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.wangxq.mylibrary.R;


/**
 * copyright (C), 2021, 运达科技有限公司
 * fileName LoadingDialog
 *
 * @author 王玺权
 * date 2021-11-24 13:21
 * description 加载对话框
 * history
 */
public class LoadingDialog extends BaseDialogFragment{
    private String TAG = "LoadingDialog";
    private TextView content;
    private String title;
    @Override
    protected int getDialogLayoutResId() {
        return R.layout.dialog_loading;
    }

    @Override
    protected void onInflated(View container, Bundle savedInstanceState) {
        content=container.findViewById(R.id.content);
        content.setText(TextUtils.isEmpty(title)?"加载中...":title);
        setCancelable(false);
    }

    public void setContent(String title){
        this.title=title;
    }

    public LoadingDialog() {
    }



    @Override
    protected boolean getCanceledOnTouchOutside() {
        return false;
    }

    public void showLoading(@NonNull FragmentManager manager, @Nullable String tag, String...msg) {
        if(msg.length>0){
            this.title=msg[0];
        }
        FragmentTransaction ft = manager.beginTransaction();
        ft.add(this,tag);
        // 这里把原来的commit()方法换成了commitAllowingStateLoss()
        // 解决Can not perform this action after onSaveInstanceState with DialogFragment
        ft.commitAllowingStateLoss();

    }
    protected static class LoadingDialogHodler {
        private static LoadingDialog singletonIn = new LoadingDialog();
    }

    @Override
    public void dismiss() {
        if (getFragmentManager()==null){
            Log.w(TAG, "dismiss: "+this+" not associated with a fragment manager." );
        }else {
            super.dismiss();
        }
    }

    public static LoadingDialog getLoadingDialog() {
        return LoadingDialogHodler.singletonIn;
    }
}
