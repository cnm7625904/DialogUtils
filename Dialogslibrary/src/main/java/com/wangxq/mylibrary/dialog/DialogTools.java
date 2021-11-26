package com.wangxq.mylibrary.dialog;

import androidx.fragment.app.FragmentManager;

/**
 * copyright (C), 2021, 运达科技有限公司
 * fileName DialogUtils
 *
 * @author 王玺权
 * date 2021-11-25 11:37
 * description
 * history
 */
public enum DialogTools {
    /**
     * 单例
     */
    INSTANCE;

    /**
     * 展示加载框
     * @param manager
     */
    public void showLoading(FragmentManager manager, String...msg){
        LoadingDialog.getLoadingDialog().showLoading(manager,"loading",msg);
    }

    /**
     * 关闭加载框
     */
    public void dismissLoading(){
        LoadingDialog.getLoadingDialog().dismiss();
    }

    /**
     * 展示普通对话框
     * @param manager
     */
    public void showCommomDialog(FragmentManager manager, NoticeDialog.Builder builder){
        NoticeDialog.getCommonDialog().setBuilder(builder).show(manager);
    }

    /**
     * 关闭加载框
     */
    public void dismissCommonDialog(){
        NoticeDialog.getCommonDialog().dismiss();
    }

}
