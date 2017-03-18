package com.anwesome.games.leandrawer;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by anweshmishra on 19/03/17.
 */
public class LeanBar {
    private Activity activity;
    private LeanBarView leanBarView;
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
            activity.addContentView(leanBarView,new ViewGroup.LayoutParams(size.x,size.y/10));
        }
        leanBarView.setX(0);
        leanBarView.setY(0);
    }
    private class LeanBarView extends View {
        public LeanBarView(Context context){
            super(context);
        }
        public void onDraw(Canvas canvas) {
            canvas.getWidth();
        }
        public boolean onTouchEvent(MotionEvent event) {
            return true;
        }
    }
}
