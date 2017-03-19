package com.anwesome.games.leandrawer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 19/03/17.
 */
public class LeanDrawer extends View{
    private String profileText = "Profile Name";
    private Bitmap profileBitmap;
    private float prevX;
    private OnToggleListener onToggleListener;
    private boolean isDown = false;
    public void setHeaderColor(int headerColor) {
        this.headerColor = headerColor;
    }

    private int headerColor = Color.parseColor("#8E24AA");
    private int time = 0,w,h;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public void setProfileText(String profileText) {
        this.profileText = profileText;
    }

    public void setProfileBitmap(Bitmap profileBitmap) {
        this.profileBitmap = profileBitmap;
    }

    public LeanDrawer(Context context) {
        super(context);
        profileBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.profile_avt);
    }
    public void setOnToggleListener(OnToggleListener onToggleListener) {
        this.onToggleListener = onToggleListener;
    }
    public void onDraw(Canvas canvas) {
        if(time == 0) {
            w = canvas.getWidth();
            h = canvas.getHeight();
            paint.setTextSize(w/10);
            profileBitmap = Bitmap.createScaledBitmap(profileBitmap,Math.min(w,h)/3,Math.min(w,h)/3,true);
        }
        paint.setColor(Color.parseColor("#E0E0E0"));
        canvas.drawRect(new RectF(0,0,w,h),paint);
        float headerHeight = canvas.getHeight()/10+(profileBitmap.getHeight()*12)/10+(paint.getTextSize()*3)/2;
        paint.setColor(headerColor);
        canvas.drawRect(new RectF(0,0,w,headerHeight),paint);
        drawProfile(canvas,paint);
        time++;
    }
    public boolean isOpened() {
        return (getX()>=0);
    }
    public boolean isClosed() {
        return (getX()<=-w);
    }
    private void drawProfile(Canvas canvas,Paint paint) {
        canvas.save();
        canvas.translate(canvas.getWidth()/2,canvas.getHeight()/10+profileBitmap.getHeight()/2);
        Path path = new Path();
        paint.setColor(Color.parseColor("#E0E0E0"));
        path.addCircle(0,0,profileBitmap.getWidth()/2, Path.Direction.CCW);
        canvas.clipPath(path);
        canvas.drawPath(path,paint);
        canvas.drawBitmap(profileBitmap,-profileBitmap.getWidth()/2,-profileBitmap.getHeight()/2,paint);
        canvas.restore();
        setProfileTextSize();
        paint.setColor(Color.WHITE);
        canvas.drawText(profileText,w/2-paint.measureText(profileText)/2,canvas.getHeight()/10+(profileBitmap.getHeight()*13)/10,paint);
    }
    private void setProfileTextSize() {
        if(paint.measureText(profileText) > w) {
            paint.setStrokeWidth(paint.getStrokeWidth()*9/10);
            setProfileTextSize();
        }
    }
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if(!isDown) {
                    isDown = true;
                    prevX = x;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if(isDown) {
                    float diff = x-prevX;
                    if((diff <0 && getX()>-(w)) || (diff>0 && getX()<0)) {
                        setX(getX()+diff);
                        if(getX()>0) {
                            setX(0);
                        }
                        if(getX()<(-w)) {
                            setX(-w);
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                if(isDown) {
                    isDown = false;
                    if(getX()<-((3*w)/4)) {
                        setX(-w);
                        if(onToggleListener!=null) {
                            onToggleListener.onClose();
                        }
                    }
                    else {
                        setX(0);
                        if(onToggleListener!=null) {
                            onToggleListener.onOpen();
                        }
                    }
                }
                break;
        }
        return true;
    }
    public interface OnToggleListener {
        void onClose();
        void onOpen();
    }
}
