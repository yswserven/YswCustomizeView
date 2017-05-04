package com.yswcustomizeview.mEdittextChanggeListener;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by: Ysw on 2016/10/31.
 */

public class EditTextChange implements TextWatcher {

    private EditText mEditText;
    private String mBeforeStr = "";
    private String mAfterStr = "";
    private String mChangeStr = "";
    private int mIndex = 0;
    private int mTypeNum;
    private boolean mChangeIndex = true;
    private int mNum = 1;
    private TextChangeListener mListener;

    public EditTextChange(EditText editText, int typeNum, TextChangeListener listener) {
        this.mEditText = editText;
        this.mTypeNum = typeNum;
        this.mListener = listener;
    }

    interface TextChangeListener {
        void onTextChangeCallback(String text);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        mBeforeStr = s.toString();
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        mAfterStr = s.toString();
        if (mChangeIndex)
            mIndex = mEditText.getSelectionStart();
    }

    @Override
    public void afterTextChanged(Editable s) {
        if ("".equals(s.toString()) || mBeforeStr.equals(mAfterStr)) {
            mChangeIndex = true;
            return;
        }
        mChangeIndex = false;
        char c[] = s.toString().replace(" ", "").toCharArray();
        mChangeStr = "";
        for (int i = 0; i < c.length; i++) {
            mChangeStr = mChangeStr + c[i] + ((i + 1) % 4 == 0 && i + 1 != c.length ? " " : "");
        }
        if (mAfterStr.length() > mBeforeStr.length()) {
            if (mChangeStr.length() == mIndex + 1) {
                mIndex = mChangeStr.length() - mAfterStr.length() + mIndex;
            }
            if (mIndex % 5 == 0 && mChangeStr.length() > mIndex + 1) {
                mIndex++;
            }
        } else if (mAfterStr.length() < mBeforeStr.length()) {
            if ((mIndex + 1) % 5 == 0 && mIndex > 0 && mChangeStr.length() > mIndex + 1) {
                mIndex--;
            } else {
                mIndex = mChangeStr.length() - mAfterStr.length() + mIndex;
                if (mAfterStr.length() % 5 == 0 && mChangeStr.length() > mIndex + 1) {
                    mIndex++;
                }
            }
        }
        if (mIndex == 12) {
            mNum++;
        }
        if (mTypeNum == 1) {
            if (mIndex == 12 && mNum % 2 == 0) {
                mListener.onTextChangeCallback(mChangeStr.trim().replace(" ", ""));
            } else {
                mListener.onTextChangeCallback(mChangeStr.trim().replace(" ", ""));
            }
        }
        mEditText.setText(mChangeStr);
        mEditText.setSelection(mIndex);
    }
}
