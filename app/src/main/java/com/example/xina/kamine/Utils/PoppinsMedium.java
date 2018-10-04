package com.example.xina.kamine.Utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class PoppinsMedium extends AppCompatTextView{
    private static final String FONT_NAME = "Poppins-Medium.otf";

    private static final String TAG = "CustomTextView";
    public PoppinsMedium(Context context) {
        super(context);
        Typeface face=Typeface.createFromAsset(context.getAssets(), FONT_NAME);
        this.setTypeface(face);
    }

    public PoppinsMedium(Context context, AttributeSet attrs) {
        super(context, attrs);
        Typeface face=Typeface.createFromAsset(context.getAssets(), FONT_NAME);
        this.setTypeface(face);
    }

    public PoppinsMedium(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        Typeface face=Typeface.createFromAsset(context.getAssets(), FONT_NAME);
        this.setTypeface(face);
    }

    protected void onDraw (Canvas canvas) {
        super.onDraw(canvas);


    }
}
