package com.luseen.introlib;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import static com.luseen.introlib.BaseIntroActivity.DEFAULT_ANIMATION_DURATION;

/**
 * Created by Chatikyan on 10.09.2016-11:17.
 */

class Utils {

    static void showView(View... views) {
        for (View view : views) {
            if (view != null && view.getVisibility() == View.GONE) {
                ViewCompat.animate(view)
                        .setDuration(DEFAULT_ANIMATION_DURATION)
                        .alpha(1)
                        .setListener(new ViewPropertyAnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(View view) {
                                view.setVisibility(View.VISIBLE);
                            }
                        })
                        .start();
            }
        }
    }

    static void hideView(View... views) {
        for (View view : views) {
            if (view != null && view.getVisibility() == View.VISIBLE) {
                ViewCompat.animate(view)
                        .setDuration(DEFAULT_ANIMATION_DURATION)
                        .alpha(0)
                        .setListener(new ViewPropertyAnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(View view) {
                                view.setVisibility(View.GONE);
                            }
                        })
                        .start();
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    static int getNavBarWidth(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return resources.getDimensionPixelSize(resourceId);
        }
        return 0;
    }

    static void animateViewWithScale(View view, float scaleFactor) {
        ViewCompat.animate(view)
                .setDuration(DEFAULT_ANIMATION_DURATION)
                .scaleX(scaleFactor)
                .scaleY(scaleFactor)
                .start();
    }

    static void changeViewScale(View view, float scaleFactor) {
        view.setScaleX(scaleFactor);
        view.setScaleY(scaleFactor);
    }

    /**
     * Change given image view tint with animation
     *
     * @param image     target image view
     * @param fromColor start animation from color
     * @param toColor   final color
     */
    static void changeImageViewTintWithAnimation(final ImageView image, int fromColor, int toColor) {
        ValueAnimator imageTintChangeAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), fromColor, toColor);
        imageTintChangeAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                image.setColorFilter((Integer) animator.getAnimatedValue(), PorterDuff.Mode.SRC_IN);
            }
        });
        imageTintChangeAnimation.setDuration(DEFAULT_ANIMATION_DURATION);
        imageTintChangeAnimation.start();
    }
}