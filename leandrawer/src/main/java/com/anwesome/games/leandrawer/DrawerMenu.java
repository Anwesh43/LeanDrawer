package com.anwesome.games.leandrawer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by anweshmishra on 19/03/17.
 */
public class DrawerMenu {
    private Bitmap bitmap;
    private String text;
    private float x,y,w,h,textW;
    public interface OnClickListener {
        void onClick();
    }
    private OnClickListener onClickListener;
    private DrawerMenu(Bitmap bitmap,String text){
        this.bitmap = bitmap;
        this.text = text;
    }
    public void setDimensions(float x,float y,float w,float h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
    public void draw(Canvas canvas, Paint paint) {
        float size = w;
        canvas.save();
        if(bitmap!=null) {
            bitmap = Bitmap.createScaledBitmap(bitmap,(int)size/8,(int)size/8,true);
            canvas.drawBitmap(bitmap,size/8,size/8,paint);
        }
        if(text!=null) {
            paint.setColor(Color.BLACK);
            paint.setTextSize(size/8);
            setTextSize(paint,size);
            canvas.drawText(text,size/3,size/8,paint);
            textW = paint.measureText(text);
        }
    }
    private void setTextSize(Paint paint,float fullSize) {
        if(!(paint.measureText(text)<fullSize/3)) {
            paint.setTextSize((paint.getTextSize()*9)/10);
            setTextSize(paint,fullSize);
        }
    }
    public boolean handleTap(float x,float y,float size) {
        boolean condition = x>=this.x+size/3 && x<=this.x+size/3+textW && y>=this.y+size/8 && y<=this.y+size/4;
        if(condition && onClickListener!=null) {
            onClickListener.onClick();
        }
        return condition;
    }
    public int hashCode() {
        return bitmap.hashCode()+text.hashCode()+(int)(31*x+31*y);
    }
}
