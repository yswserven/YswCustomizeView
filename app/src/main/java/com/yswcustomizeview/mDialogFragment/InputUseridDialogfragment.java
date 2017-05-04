package com.yswcustomizeview.mDialogFragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.yswcustomizeview.R;

/**
 * Created by: Ysw on 2016/10/26.
 */

public class InputUseridDialogfragment extends DialogFragment implements DialogInterface.OnKeyListener {
    private Context mContext;

    public InputUseridDialogfragment(Context context) {
        this.mContext = context;
    }

    @Override
    public void onStart() {
        super.onStart();
        // 设置宽度布满的设置 @author Ysw created at 2016/10/26 10:37
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        getDialog().getWindow().setLayout(dm.widthPixels, getDialog().getWindow().getAttributes().height);

        // 设置布局显示在底部 @author Ysw created at 2016/10/26 10:37
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        final WindowManager.LayoutParams layoutParams = getDialog().getWindow().getAttributes();
        layoutParams.width = dm.widthPixels;
        layoutParams.gravity = Gravity.BOTTOM;
        getDialog().getWindow().setAttributes(layoutParams);

        // 设置背景透明度 @author Ysw created at 2016/10/26 10:36
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams windowParams = window.getAttributes();
        windowParams.dimAmount = 0.0f;
        window.setAttributes(windowParams);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = LayoutInflater.from(mContext);// 获取 LayoutInflater
        View view = inflater.inflate(R.layout.notice_dialog_updata, null);// 自定义布局
        initUI(view); // 此处是自己定义的方法
        Dialog dialog = new Dialog(mContext, R.style.Dialog_Fullscreen); // 创建 Dianlog 并设置DialogFragment 的风格
        dialog.setCanceledOnTouchOutside(false);// 设置点击周边是否可以取消显示此 DialogFragment
        dialog.setOnKeyListener(this);// 设置 DialogFragment 显示时返回键的点击监听
        dialog.setContentView(view);// 设置 DialogFragment 自定义布局
        return dialog;// 返回其本身，完成 DialogFragment 的创建
    }

    private void initUI(View view) {

    }


    @Override
    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
        return false;
    }
}