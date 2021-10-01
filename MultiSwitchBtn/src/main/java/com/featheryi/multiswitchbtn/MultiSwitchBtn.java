package com.featheryi.multiswitchbtn;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class MultiSwitchBtn extends View {
    public MultiSwitchBtn(Context context) {
        this(context, null);
    }

    public MultiSwitchBtn(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MultiSwitchBtn(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
