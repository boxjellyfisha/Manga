package com.example.deer.manga.Chose;


import android.animation.AnimatorSet;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.example.deer.manga.R;

/**
 * Created by deer on 2016/10/16.
 * 進門動畫
 */

public class SplashFragment extends Fragment{

    final public static int IMG_TOP=1;
    final public static int IMG_LEFT=2;
    final public static int IMG_RIGHT=3;
    final public static int IMG_BOTTOM=4;

    private ImageView tobiraRight;
    private ImageView tobiraLeft;
    private ImageView top;
    private ImageView bottom;
    private ChoseActivity callback;
    private AnimationSet anima_T;
    private AnimationSet anima_L;
    private AnimationSet anima_R;
    private AnimationSet anima_B;
    private AlphaAnimation alpha;
    private TranslateAnimation translate;
    private DisplayMetrics dm;
    public int vWidth;
    public int vHeight;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        callback=(ChoseActivity)getActivity();
        dm = new DisplayMetrics();
        callback.getWindowManager().getDefaultDisplay().getMetrics(dm);
        vWidth = dm.widthPixels;
        vHeight = dm.heightPixels;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.door_activity_view, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        tobiraRight = (ImageView) view.findViewById(R.id.door_right);
        tobiraLeft = (ImageView) view.findViewById(R.id.door_left);
        top = (ImageView) view.findViewById(R.id.top_black);
        bottom = (ImageView) view.findViewById(R.id.bottom_black);
    }

    public void alphaAnimate(){
        alpha=new AlphaAnimation(1,0);
        alpha.setDuration(2500);
    }
    public AnimationSet setAnimate(int type){

        AnimationSet anime=new AnimationSet(true);
        switch (type)
        {
            case IMG_TOP:
                anime.addAnimation(alpha);
                break;
            case IMG_LEFT:
                translate=new TranslateAnimation(0,-vWidth,0,0);
                translate.setDuration(2500);
                anime.addAnimation(translate);
                break;
            case IMG_RIGHT:
                translate=new TranslateAnimation(0,vWidth,0,0);
                translate.setDuration(2500);
                anime.addAnimation(translate);
                break;
            case IMG_BOTTOM:
                anime.addAnimation(alpha);
                break;
        }
        return anime;
    }
    public void animeRun()
    {
        alphaAnimate();
        anima_T=setAnimate(IMG_TOP);
        top.setAnimation(anima_T);
        anima_L=setAnimate(IMG_LEFT);
        tobiraLeft.setAnimation(anima_L);
        anima_R=setAnimate(IMG_RIGHT);
        tobiraRight.setAnimation(anima_R);

        anima_B=setAnimate(IMG_BOTTOM);
        bottom.setAnimation(anima_B);
        anima_B.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                callback.splashEnd();
            }
        });
    }

}