package com.example.xina.kamine.Utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

public class PoppinsEditText extends android.support.v7.widget.AppCompatEditText {
    private static final String FONT_NAME = "Poppins-Regular.otf";

    private static final String TAG = "CustomTextView";

    public PoppinsEditText(Context context) {
        super(context);
        Typeface face=Typeface.createFromAsset(context.getAssets(), FONT_NAME);
        this.setTypeface(face);
    }

    public PoppinsEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        Typeface face=Typeface.createFromAsset(context.getAssets(), FONT_NAME);
        this.setTypeface(face);
    }

    public PoppinsEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Typeface face=Typeface.createFromAsset(context.getAssets(), FONT_NAME);
        this.setTypeface(face);
    }

   /* public GothamLightEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }*/

    protected void onDraw (Canvas canvas) {
        super.onDraw(canvas);


    }
}
