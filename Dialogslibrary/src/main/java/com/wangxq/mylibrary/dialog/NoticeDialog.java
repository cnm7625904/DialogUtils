package com.wangxq.mylibrary.dialog;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;

import com.wangxq.mylibrary.R;


/**
 * copyright (C), 2021, 运达科技有限公司
 * fileName Dialogs
 *
 * @author 王玺权
 * date 2021-11-24 10:21
 * description 提示框
 * history
 */
public  class NoticeDialog extends BaseDialogFragment {
    /**
     * 简单提示框，没有任何按钮
     */
    public static final int EASY = 0;
    /**
     * 普通提示框，只有右边按钮
     */
    public static final int NORMAL = 1;
    /**
     * 重要提示框，有左右按钮
     */
    public static final int HARD = 2;
    private String TAG = "CommonDialog";
    private DialogButtonClickListener mListener;
    /**
     * title view
     */
    private TextView mTitle;

    /**
     * 内容文本
     */
    private TextView mContent;

    /**
     * 底部button layout
     */
    private LinearLayout mButtons;

    /**
     * 左边的text button
     */
    private TextView mLeft;

    /**
     * 分割线
     */
    private View divider;

    /**
     * 右边的text button
     */
    private TextView mRight;

    private View line;

    /**
     * 设置layout params
     */
    private WindowManager.LayoutParams mLayoutParams;

    private String mTitleText;

    private String mContentText;

    private String mLeftButtonText;

    private String mRightButtonText;

    /**
     * 底部button的显示状态，0，代表不显示，1代表显示一个，2代表显示两个。
     */
    private int mButtonState = HARD;

    private Builder builder;

    /**
     * 设置点击dialog外部是否会显示
     */
    private boolean mIsCanceledOnTouchOutside;

    /**
     * 设置点击dialog外部是否会显示
     */
    private boolean mCancelable;



    private NoticeDialog() {}

    @SuppressLint("ValidFragment")
    public NoticeDialog(Builder builder) {
        if (builder == null) {
            return;
        }
        mListener=builder.mListener;
        mLayoutParams = builder.mLayoutParams;
        mTitleText = builder.mTitleText;
        mContentText = builder.mContentText;
        mLeftButtonText = builder.mLeftButtonText;
        mRightButtonText = builder.mRightButtonText;
        mButtonState = builder.mButtonState;
        mIsCanceledOnTouchOutside = builder.mIsCanceledOnTouchOutside;
        mCancelable = builder.mCancelable;
    }

    public NoticeDialog setBuilder(Builder builder){
        this.builder=builder;
        mListener=builder.mListener;
        mLayoutParams = builder.mLayoutParams;
        mTitleText = builder.mTitleText;
        mContentText = builder.mContentText;
        mLeftButtonText = builder.mLeftButtonText;
        mRightButtonText = builder.mRightButtonText;
        mButtonState = builder.mButtonState;
        mIsCanceledOnTouchOutside = builder.mIsCanceledOnTouchOutside;
        mCancelable = builder.mCancelable;
        return this;
    }

    @Override
    protected int getDialogLayoutResId() {
        return R.layout.dialog_commom;
    }

    @Override
    protected void onInflated(View container, Bundle savedInstanceState) {
        findView(container);
        setView();
        setListener();
    }

    /**
     * 通过父view找到子view
     *
     * @param container 父view
     */
    private void findView(View container) {
        mTitle = container.findViewById(R.id.title);
        mContent = container.findViewById(R.id.content);
        mButtons = container.findViewById(R.id.operationLayout);
        mLeft = container.findViewById(R.id.cancel);
        mRight = container.findViewById(R.id.submit);
        line = container.findViewById(R.id.line);
        divider = container.findViewById(R.id.divider);
    }

    /**
     * 设置view内容及是否显示
     *
     */
    private void setView() {
        if (TextUtils.isEmpty(mTitleText)) {
            mTitle.setVisibility(View.GONE);
        } else {
            mTitle.setVisibility(View.VISIBLE);
            mTitle.setText(mTitleText);
        }

        mContent.setText(mContentText);

        setButtons();

    }

    /**
     * 设置点击事件
     *
     */
    private void setListener() {

        mLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.leftOnClick(NoticeDialog.this);
                }
                dismiss();
            }
        });

        mRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.rightOnClick(NoticeDialog.this);
                }
                dismiss();
            }
        });

    }

    private void setButtons() {
        switch (mButtonState) {
            case EASY:
                mButtons.setVisibility(View.GONE);
                break;
            case NORMAL:
                mLeft.setVisibility(View.GONE);
                line.setVisibility(View.GONE);
                mRight.setVisibility(View.VISIBLE);
                mRight.setText(mRightButtonText);
                break;
            default:
            case HARD:
                mLeft.setText(mLeftButtonText);
                mRight.setText(mRightButtonText);
                mLeft.setVisibility(TextUtils.isEmpty(mLeftButtonText)?View.GONE:View.VISIBLE);
                mRight.setVisibility(TextUtils.isEmpty(mRightButtonText)?View.GONE:View.VISIBLE);
                if(mLeft.getVisibility()==View.GONE&&mRight.getVisibility()==View.GONE){
                    mButtons.setVisibility(View.GONE);
                    divider.setVisibility(View.GONE);
                }else if(mLeft.getVisibility()==View.GONE||mRight.getVisibility()==View.GONE){
                    line.setVisibility(View.GONE);
                    divider.setVisibility(View.VISIBLE);
                }else {
                    line.setVisibility(View.VISIBLE);
                    mButtons.setVisibility(View.VISIBLE);
                    divider.setVisibility(View.VISIBLE);
                }
                break;
        }

    }

    @Override
    protected WindowManager.LayoutParams getLayoutParams(WindowManager.LayoutParams params) {
        if (mLayoutParams != null) {
            return mLayoutParams;
        }
        return super.getLayoutParams(params);
    }

    @Override
    protected boolean getCanceledOnTouchOutside() {
        if (mIsCanceledOnTouchOutside) {
            return true;
        }
        return super.getCanceledOnTouchOutside();
    }

    @Override
    protected boolean getCancelable() {
        if (mCancelable) {
            return true;
        }
        return super.getCancelable();
    }

    public void show(FragmentManager manager) {
        if (manager == null) {
            return;
        }
        try {
            //在每个add事务前增加一个remove事务，防止连续的add
            manager.beginTransaction().remove(this).commit();
            super.show(manager, "CommonDialog");
        } catch (Exception e) {
            //同一实例使用不同的tag会异常，这里捕获一下
            e.printStackTrace();
        }

    }

    /**
     * 点击事件的监听接口，默认情况不用复写方法，用户可根据实际需求复写。
     *
     */
    public interface DialogButtonClickListener {

        default void leftOnClick(NoticeDialog noticeDialog) {}
        default void rightOnClick(NoticeDialog noticeDialog) {}
    }



    /***
     * 静态内部类，用builder模式来设置该dialog的相关属性
     *
     */
    public static class Builder {
        /**
         * 点击事件的监听接口引用
         *
         */
        private DialogButtonClickListener mListener;
        /**
         * 设置dialog的layout params
         */
        private WindowManager.LayoutParams mLayoutParams;

        /**
         * 设置title的文本
         */
        private String mTitleText = "提示";

        /**
         * 设置dialog文本内容
         */
        private String mContentText = "温馨提示";

        /**
         * 设置dialog底部左边button的文本，默认显示cancle
         */
        private String mLeftButtonText  = "取消";

        /**
         * 设置dialog底部左边button的文本，默认显示ok
         */
        private String mRightButtonText = "确认";

        /**
         * 设置dialog底部三个button显示状态，1，2 ？
         */
        private int mButtonState = 2;

        /**
         * 设置点击dialog外部是否会显示
         */
        private boolean mIsCanceledOnTouchOutside;

        /**
         * 设置点击dialog外部是否会显示
         */
        private boolean mCancelable;

        public Builder setLayoutParams(WindowManager.LayoutParams layoutParams) {
            mLayoutParams = layoutParams;
            return this;
        }

        public Builder setTitleText(String titleText) {
            mTitleText = titleText;
            return this;
        }

        public Builder setContentText(String contentText) {
            mContentText = contentText;
            return this;
        }

        public Builder setLeftButtonText(String leftButtonText) {
            mLeftButtonText = leftButtonText;
            return this;
        }

        public Builder setRightButtonText(String rightButtonText) {
            mRightButtonText = rightButtonText;
            return this;
        }

        public Builder setButtonState(int buttonState) {
            mButtonState = buttonState;
            return this;
        }

        public Builder setCanceledOnTouchOutside(boolean isCanceledOnTouchOutside) {
            mIsCanceledOnTouchOutside = isCanceledOnTouchOutside;
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            mCancelable = cancelable;
            return this;
        }

        public NoticeDialog build() {
            return new NoticeDialog(this);
        }
        public Builder setDialogButtonListener(DialogButtonClickListener listener) {
            mListener = listener;
            return this;
        }
    }
    //    /**
//     * 设置按钮的点击事件监听
//     *
//     */
//    public void setDialogButtonListener(DialogButtonClickListener listener) {
//        mListener = listener;
//    }

    protected static class CommonDialogHodler {
        private static NoticeDialog singletonIn = new NoticeDialog();
    }

    @Override
    public void dismiss() {
        if (getFragmentManager()==null){
            Log.w(TAG, "dismiss: "+this+" not associated with a fragment manager." );
        }else {
            super.dismiss();
        }
    }

    public static NoticeDialog getCommonDialog() {
        return NoticeDialog.CommonDialogHodler.singletonIn;
    }
}
