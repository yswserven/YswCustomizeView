package com.yswcustomizeview.mDialogFragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yswcustomizeview.R;


public class UpdataNoticeDialog extends DialogFragment implements
        OnClickListener, OnKeyListener {
    private Context mContext;
    private TextView tv_title;
    private RelativeLayout rl_cancel;
    private RelativeLayout rl_sure;
    private TextView tv_message;

    public UpdataNoticeDialog(Context context) {
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
        //DisplayMetrics dm = new DisplayMetrics();
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
        // setStyle(DialogFragment.STYLE_NORMAL,
        // android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.notice_dialog_updata, null);
        initUI(view);
        Dialog dialog = new Dialog(mContext, R.style.Dialog_Fullscreen);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setOnKeyListener(this);
        dialog.setContentView(view);
        return dialog;
    }

    private void initUI(View view) {
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        tv_message = (TextView) view.findViewById(R.id.tv_message);
        rl_cancel = (RelativeLayout) view.findViewById(R.id.rl_cancel);
        rl_sure = (RelativeLayout) view.findViewById(R.id.rl_sure);
        tv_title.setText("版本升级");
        // 设置可滚动
        tv_message.setMovementMethod(ScrollingMovementMethod.getInstance());
        // 设置超链接可以打开网页
        tv_message.setMovementMethod(LinkMovementMethod.getInstance());
        tv_message.setText(Html.fromHtml("", null, null));
        rl_cancel.setOnClickListener(this);
        rl_sure.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_cancel:
                this.dismiss();
                break;
            case R.id.rl_sure:
                this.dismiss();
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        } else {
            this.dismiss();
            return false;
        }
    }
}
