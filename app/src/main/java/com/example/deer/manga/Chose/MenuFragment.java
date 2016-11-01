package com.example.deer.manga.Chose;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.deer.manga.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by deer on 2016/10/16.
 * 選單畫面 : 正篇 番外 動畫
 */

public class MenuFragment extends Fragment {

    final public static int BTN_MANGA=0;
    final public static int BTN_OTHER=1;
    final public static int BTN_ANIME=2;
    final public static int BTN_MENU=3;
    final public static int IMG_BG=4;

    public FrameLayout fLayout;
    private ChoseActivity callback;
    private Button mangaBtn;
    private Button otherBtn;
    private Button animateBtn;
    private Button menuBtn;
    private ImageView imgBG;
    private AlphaAnimation alpha;
    private TranslateAnimation translate;
    private AnimationSet anime_BTN;
    private AnimationSet anime_BTN2;
    private AnimationSet anime_BTN3;
    private AnimationSet anime_IMG;
    private List<Button> list;
    private int flag=0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        callback=(ChoseActivity)getActivity();
        list=new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.btn_menu, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mangaBtn=(Button)view.findViewById(R.id.manga_btn);
        otherBtn=(Button)view.findViewById(R.id.other_btn);
        animateBtn=(Button)view.findViewById(R.id.animate_btn);
        menuBtn=(Button)view.findViewById(R.id.menu_button);
        fLayout=(FrameLayout)view.findViewById(R.id.frame_menu);
        imgBG=(ImageView)view.findViewById(R.id.menu_background);
        setting();
    }

    public void setting() {
        mangaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.menuClose();
            }
        });

        otherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.menuClose();
            }
        });

        animateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.menuClose();
            }
        });
        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.menuClose();
            }
        });
        list.add(BTN_MANGA,mangaBtn);
        list.add(BTN_OTHER,otherBtn);
        list.add(BTN_ANIME,animateBtn);
        list.add(BTN_MENU,menuBtn);
        setInVisible();
    }

    public void alphaAnimate(){
        alpha=new AlphaAnimation(0,1);
        alpha.setDuration(200);
    }

    public AnimationSet setAnimate(int type){

        AnimationSet anime=new AnimationSet(true);
        alphaAnimate();
        anime.addAnimation(alpha);
        switch (type)
        {
            case BTN_MANGA:
                translate=new TranslateAnimation(80,0,80,0);
                translate.setDuration(300);
                anime.addAnimation(translate);
                break;
            case BTN_OTHER:
                translate=new TranslateAnimation(80,0,80,0);
                translate.setDuration(300);
                anime.setStartOffset(200);
                anime.addAnimation(translate);
                break;
            case BTN_ANIME:
                translate=new TranslateAnimation(80,0,80,0);
                translate.setDuration(300);
                anime.setStartOffset(500);
                anime.addAnimation(translate);
                break;
            case IMG_BG:
                break;
        }
        return anime;
    }

    public void setVisible() {
        anime_BTN=setAnimate(BTN_MANGA);
        list.get(BTN_MANGA).setAnimation(anime_BTN);
        anime_BTN2=setAnimate(BTN_OTHER);
        list.get(BTN_OTHER).setAnimation(anime_BTN2);
        anime_BTN3=setAnimate(BTN_ANIME);
        list.get(BTN_ANIME).setAnimation(anime_BTN3);
        anime_IMG=setAnimate(IMG_BG);
        imgBG.setAnimation(anime_IMG);

        for(Button btn:list)
            btn.setVisibility(View.VISIBLE);
        imgBG.setVisibility(View.VISIBLE);

    }

    public void setInVisible() {
        flag=0;
        for(Button btn:list)
            btn.setVisibility(View.GONE);
        imgBG.setVisibility(View.GONE);
    }

}
