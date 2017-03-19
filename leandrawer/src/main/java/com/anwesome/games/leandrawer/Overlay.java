package com.anwesome.games.leandrawer;

import android.content.Context;
import android.graphics.*;
import android.view.View;

/**
 * Created by anweshmishra on 19/03/17.
 */
public class Overlay extends View{
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public Overlay(Context context) {
        super(context);
    }
    public void onDraw(Canvas canvas) {
        paint.setColor(Color.parseColor("#55000000"));
        canvas.drawRect(new RectF(0,0,canvas.getWidth(),canvas.getHeight()),paint);
    }

}
