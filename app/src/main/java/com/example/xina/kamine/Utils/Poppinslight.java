package com.example.xina.kamine.Utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.util.AttributeSet;

public class Poppinslight extends android.support.v7.widget.AppCompatTextView {

    // Can also be replaced with EditText or Button
    private static final String FONT_NAME = "Poppins-Thin.otf";

    private static final String TAG = "CustomTextView";
    public Poppinslight(Context context) {
        super(context);
        Typeface face=Typeface.createFromAsset(context.getAssets(), FONT_NAME);
        this.setTypeface(face);
    }

    public Poppinslight(Context context, AttributeSet attrs) {
        super(context, attrs);
        Typeface face=Typeface.createFromAsset(context.getAssets(), FONT_NAME);
        this.setTypeface(face);
    }

    public Poppinslight(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        Typeface face=Typeface.createFromAsset(context.getAssets(), FONT_NAME);
        this.setTypeface(face);
    }

    protected void onDraw (Canvas canvas) {
        super.onDraw(canvas);


    }
}
