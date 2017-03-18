package com.anwesome.games.leandrawer;

import android.animation.ValueAnimator;

/**
 * Created by anweshmishra on 19/03/17.
 */
public class LeanDrawerAnimationController {
    private ValueAnimator openAnimator,closeAnimator;
    private LeanDrawer leanDrawer;
    public void initAnimators(LeanDrawer leanDrawer,float w) {
        this.leanDrawer = leanDrawer;
        openAnimator = ValueAnimator.ofFloat(-w,0);
        closeAnimator = ValueAnimator.ofFloat(0,-w);
        openAnimator.addUpdateListener(new AnimationController());
        closeAnimator.addUpdateListener(new AnimationController());
        openAnimator.setDuration(100);
        closeAnimator.setDuration(100);
    }
    public void open() {
        openAnimator.start();
    }
    public void close() {
        closeAnimator.start();
    }
    private class AnimationController implements ValueAnimator.AnimatorUpdateListener {
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            leanDrawer.setX(valueAnimator.getAnimatedFraction());
        }

    }
}
