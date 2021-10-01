package com.featheryi.multiswitchbtn;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class MultiSwitchBtn extends View {

    private static final String TAG = "MultiSwitchBtn";
    /*default value*/
    private String[] mTabTexts = {"L", "R"};
    private int mTabNum = mTabTexts.length;
    private static final float STROKE_RADIUS = 0;
    private static final float STROKE_WIDTH = 0;
    private static final float TEXT_SIZE = 14;
    private static final float INDICATOR_HEIGHT = 10;
    private static final float INDICATOR_WIDTH = 100;
    private static final int SELECTED_COLOR = 0xffeb7b00;
    private static final int DISABLE_COLOR = 0xff000000;
    private static final int ENABLE_COLOR = 0xffffffff;
    private static final int SELECTED_TAB = 0;
    private static final String FONTS_DIR = "fonts/";

    /*other*/
    private Paint mStrokePaint;
    private Paint mFillPaint;
    private int mWidth;
    private int mHeight;
    private TextPaint mSelectedTextPaint;
    private TextPaint mUnselectedTextPaint;
    private float mStrokeRadius;
    private float mStrokeWidth;
    private float mIndicatorHeight;
    private float mIndicatorWidth;
    private int mSelectedColor;
    private int mUnSelectedColor;
    private int mDisableColor;
    private int mSelectedTextColor;
    private int mUnSelectedTextColor;
    private int mDisableTextColor;
    private float mTextSize;
    private int mSelectedTab;
    private float perWidth;
    private float mTextHeightOffset;
    private Paint.FontMetrics mFontMetrics;
    private Typeface typeface;
    private boolean mEnable = true;

    private float mOffset; //滑动偏移量
    private int lastValue = -1;

    public MultiSwitchBtn(Context context) {
        this(context, null);
    }

    public MultiSwitchBtn(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MultiSwitchBtn(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
    }

    /**
     * get the values of attributes
     *
     * @param context
     * @param attrs
     */
    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MultiSwitchBtn);
        mStrokeRadius = typedArray.getDimension(R.styleable.MultiSwitchBtn_strokeRadius, STROKE_RADIUS);
        mStrokeWidth = typedArray.getDimension(R.styleable.MultiSwitchBtn_strokeWidth, STROKE_WIDTH);
        mIndicatorHeight = typedArray.getDimension(R.styleable.MultiSwitchBtn_indicatorHeight, INDICATOR_HEIGHT);
        mIndicatorWidth = typedArray.getDimension(R.styleable.MultiSwitchBtn_indicatorWidth, INDICATOR_WIDTH);
        mTextSize = typedArray.getDimension(R.styleable.MultiSwitchBtn_textSize, TEXT_SIZE);
        mSelectedColor = typedArray.getColor(R.styleable.MultiSwitchBtn_selectedColor, SELECTED_COLOR);
        mUnSelectedColor = typedArray.getColor(R.styleable.MultiSwitchBtn_unselectedColor, DISABLE_COLOR);
        mDisableColor = typedArray.getColor(R.styleable.MultiSwitchBtn_disableColor, DISABLE_COLOR);
        mSelectedTextColor = typedArray.getColor(R.styleable.MultiSwitchBtn_selectedTextColor, SELECTED_COLOR);
        mUnSelectedTextColor = typedArray.getColor(R.styleable.MultiSwitchBtn_unselectedTextColor, DISABLE_COLOR);
        mDisableTextColor = typedArray.getColor(R.styleable.MultiSwitchBtn_disableTextColor, DISABLE_COLOR);
        mSelectedTab = typedArray.getInteger(R.styleable.MultiSwitchBtn_selectedTab, SELECTED_TAB);
        String mTypeface = typedArray.getString(R.styleable.MultiSwitchBtn_typeface);
        int mSwitchTabsResId = typedArray.getResourceId(R.styleable.MultiSwitchBtn_switchTabs, 0);
        if (mSwitchTabsResId != 0) {
            mTabTexts = getResources().getStringArray(mSwitchTabsResId);
            mTabNum = mTabTexts.length;
        }
        if (!TextUtils.isEmpty(mTypeface)) {
            typeface = Typeface.createFromAsset(context.getAssets(), FONTS_DIR + mTypeface);
        }
        typedArray.recycle();
    }

    /*======================================save and restore======================================*/

    @Override
    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("View", super.onSaveInstanceState());
        bundle.putFloat("StrokeRadius", mStrokeRadius);
        bundle.putFloat("StrokeWidth", mStrokeWidth);
        bundle.putFloat("IndicatorHeight", mIndicatorHeight);
        bundle.putFloat("IndicatorWidth", mIndicatorWidth);
        bundle.putFloat("TextSize", mTextSize);
        bundle.putInt("SelectedColor", mSelectedColor);
        bundle.putInt("UnSelectedColor", mUnSelectedColor);
        bundle.putInt("DisableColor", mDisableColor);
        bundle.putInt("SelectedTextColor", mSelectedTextColor);
        bundle.putInt("UnSelectedTextColor", mUnSelectedTextColor);
        bundle.putInt("DisableTextColor", mDisableTextColor);
        bundle.putInt("SelectedTab", mSelectedTab);
        bundle.putBoolean("Enable", mEnable);
        return bundle;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            mStrokeRadius = bundle.getFloat("StrokeRadius");
            mStrokeWidth = bundle.getFloat("StrokeWidth");
            mIndicatorHeight = bundle.getFloat("IndicatorHeight");
            mIndicatorWidth = bundle.getFloat("IndicatorWidth");
            mTextSize = bundle.getFloat("TextSize");
            mSelectedColor = bundle.getInt("SelectedColor");
            mUnSelectedColor = bundle.getInt("UnSelectedColor");
            mDisableColor = bundle.getInt("DisableColor");
            mSelectedTextColor = bundle.getInt("SelectedTextColor");
            mUnSelectedTextColor = bundle.getInt("UnSelectedTextColor");
            mDisableTextColor = bundle.getInt("DisableTextColor");
            mSelectedTab = bundle.getInt("SelectedTab");
            mEnable = bundle.getBoolean("Enable");
            super.onRestoreInstanceState(bundle.getParcelable("View"));
        } else {
            super.onRestoreInstanceState(state);
        }
    }
}
