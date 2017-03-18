package com.anwesome.games.leandrawer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by anweshmishra on 19/03/17.
 */
public class LeanHamburgIcon {
    private float x,y,size;
    private boolean opened = false;
    private LeanHamburgIcon(float x,float y,float size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }
    public static LeanHamburgIcon getInstance(float x,float y,float size) {
        return new LeanHamburgIcon(x,y,size);
    }
    public void draw(Canvas canvas, Paint paint) {
        paint.setStrokeWidth(8);
        paint.setColor(Color.WHITE);
        if(opened) {
            canvas.save();
            canvas.translate(x,y);
            for(int i=0;i<2;i++) {
                canvas.save();
                canvas.rotate((i*2-1)*45);
                canvas.drawLine(-size/2,0,size/2,0,paint);
                canvas.restore();
            }
            canvas.restore();
        }
        else {

            canvas.save();
            canvas.translate(x,y);

            for(int i=0;i<3;i++) {
                canvas.save();
                canvas.translate(0,(i-1)*size/2);
                canvas.drawLine(-size/2,0,size/2,0,paint);
                canvas.restore();
            }
            canvas.restore();
        }
    }
    public boolean handleTap(float x,float y) {
        boolean condition = (x>=this.x-size/2 && x<=this.x+size/2 && y>=this.y-size/2 && y<=this.y+size/2);
        opened = condition?!opened:opened;
        return condition;
    }
}
