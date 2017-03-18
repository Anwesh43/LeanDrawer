package com.anwesome.games.leandrawer;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by anweshmishra on 19/03/17.
 */
public class LeanBar {
    private Activity activity;
    private LeanHamburgIcon leanHamburgIcon;
    private LeanBarView leanBarView;
    private LeanDrawer leanDrawer;
    private LeanDrawerAnimationController leanDrawerAnimationController = new LeanDrawerAnimationController();
    private LeanBar(Activity activity) {
        this.activity = activity;
    }
    public static LeanBar newInstance(Activity activity) {
        return new LeanBar(activity);
    }
    public void show() {
        if(leanBarView==null) {
            leanBarView = new LeanBarView(activity);
            Point size = DimensionsUtil.getDimensions(activity);
            leanDrawer = new LeanDrawer(activity);
            leanHamburgIcon = LeanHamburgIcon.getInstance(size.x/10,size.y/20,Math.min(size.x,size.y)/12);
            leanDrawerAnimationController.initAnimators(leanDrawer,(2*size.x)/3);
            activity.addContentView(leanBarView,new ViewGroup.LayoutParams(size.x,size.y/10));
            activity.addContentView(leanDrawer,new ViewGroup.LayoutParams((2*size.x)/3,size.y));
            leanDrawer.setX(-(2*size.x)/3);
            leanDrawer.setY(size.y/10);
        }
        leanBarView.setX(0);
        leanBarView.setY(0);
    }
    private class LeanBarView extends View {
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        public LeanBarView(Context context){
            super(context);
        }
        public void onDraw(Canvas canvas) {
            int w = canvas.getWidth(),h = canvas.getHeight();
            paint.setColor(Color.parseColor("#3F51B5"));
            canvas.drawRect(new RectF(0,0,w,h),paint);
            leanHamburgIcon.draw(canvas,paint);
        }
        public boolean onTouchEvent(MotionEvent event) {
            float x = event.getX(),y = event.getY();
            if(event.getAction() == MotionEvent.ACTION_DOWN) {
                if(leanHamburgIcon.handleTap(x,y)) {
                    if(leanHamburgIcon.isOpened()) {
                        leanDrawerAnimationController.open();
                    }
                    else {
                        leanDrawerAnimationController.close();
                    }
                    postInvalidate();
                }
            }
            return true;
        }
    }
}
