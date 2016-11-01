package com.example.deer.manga.Behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import static java.security.AccessController.getContext;

/**
 * Created by deer on 2016/10/19.
 * 尚未用到
 */

public class NewFAButton extends FloatingActionButton.Behavior {
    private Context mContext;
    public NewFAButton(Context context){
        super();
        mContext=context;
    }
    @Override
    public boolean onDependentViewChanged (CoordinatorLayout parent, FloatingActionButton child, View dependency){
        super.onDependentViewChanged(parent,child,dependency);
        if (dependency.getHeight() <= dpToPx(70)) {
            // 滾到頂顯示
            child.show();
        }
        return true;
    }
    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = mContext.getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }
    public int pxToDp(int px) {
        DisplayMetrics displayMetrics = mContext.getResources().getDisplayMetrics();
        int dp = Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return dp;
    }
}
