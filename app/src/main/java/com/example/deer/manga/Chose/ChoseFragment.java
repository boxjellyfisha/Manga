package com.example.deer.manga.Chose;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.deer.manga.Adapter.ChapterAdapter;
import com.example.deer.manga.Content.ContentActivity;
import com.example.deer.manga.R;

/**
 * Created by deer on 2016/10/7.
 * 漫畫選擇頁面布局
 */

public class ChoseFragment extends Fragment{

    private RecyclerView.OnItemTouchListener disabler;
    private RecyclerView mReView;
    private GridLayoutManager mLayoutManager;
    private Button introButton;
    private TextView introText;
    private ImageButton leastButton;
    private ChapterAdapter mAdapter;
    private View head_intro;
    private View head_text;
    private View head_least;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        disabler = new RecyclerViewDisabler();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.recycle_scrolling, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        mReView=(RecyclerView)view.findViewById(R.id.chapterView);
        mAdapter=new ChapterAdapter(getActivity());
        mLayoutManager=new GridLayoutManager(getActivity(), 7);
    }

    public void setting(){

        mAdapter.add(20);
        //mReView.setLayoutManager(new LinearLayoutManager(this));
        mReView.setLayoutManager(mLayoutManager);
        mReView.setAdapter(mAdapter);
        //mReView.setItemAnimator(new DefaultItemAnimator());

        head_intro = LayoutInflater.from(getActivity()).inflate(R.layout.header_btn, mReView, false);
        head_text = LayoutInflater.from(getActivity()).inflate(R.layout.header_text, mReView, false);
        head_least = LayoutInflater.from(getActivity()).inflate(R.layout.header_img, mReView, false);

        introButton=(Button)head_intro.findViewById(R.id.story_intro);
        introText=(TextView)head_text.findViewById(R.id.intro_text);
        leastButton=(ImageButton) head_least.findViewById(R.id.leastButton);

        introButton.setText(">故事簡介<");
        introButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                mAdapter.introClicked(introButton);
                    if(mAdapter.isIntroClickerd())
                        introText.setVisibility(View.INVISIBLE);
                    else
                         introText.setVisibility(View.VISIBLE);
                //mAdapter.notifyDataSetChanged();
            }
        });

        leastButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), ContentActivity.class);
                startActivity(intent);
                Snackbar.make(view, "From BTN", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        mReView.offsetChildrenHorizontal(0);
        mAdapter.setTypeHeader(head_intro,0);
        mAdapter.setTypeHeader(head_text,1);
        mAdapter.setTypeHeader(head_least,2);
        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {

                if(position<3)
                    return 7;
                if(position%3==0)
                    return 3;
                else
                    return 2;
            }
        });

    }
    public void setIntroText(String s){
        introText.setText(s);
    }
    @Override
    public void onStart() {
        super.onStart();
        setting();
    }
    public void setEnable(boolean enble)
    {
        if(!enble)
        mReView.addOnItemTouchListener(disabler);
        else
        mReView.removeOnItemTouchListener(disabler);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    @Override
    public void onResume() {
        super.onResume();
    }

    public class RecyclerViewDisabler implements RecyclerView.OnItemTouchListener {

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            return true;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

}
