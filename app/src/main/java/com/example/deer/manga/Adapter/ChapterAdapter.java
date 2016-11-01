package com.example.deer.manga.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.deer.manga.Content.ContentActivity;
import com.example.deer.manga.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by deer on 2016/10/7.
 * 漫畫頁面下的內容 chose recyclerView adapter
 */

public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ViewHolder> {

    public static final int TYPE_HEADER_INTRO = 0;  //header intro
    public static final int TYPE_HEADER_TXT = 1;  //header txt
    public static final int TYPE_HEADER_LEAST = 2;  //header least
    public static final int TYPE_NORMAL = 3;  //item

    private View head_intro;
    private View head_text;
    private View head_least;
    private ViewHolder textHolder;

    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<Integer> mList;
    private Map<Integer,Boolean> map;
    public boolean clicked=false;
    int tmp;

    public ChapterAdapter(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("header may not be null");
        }
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mList=new ArrayList<>();
        map=new HashMap<>();
    }
    public void add(int b)
    {
        for(int i=0;i<b;i++){
            mList.add(i);
            //map.put(i,false);
        }
    }
    public void setTypeHeader(View view,int type)
    {
        switch(type){
            case TYPE_HEADER_INTRO:
                head_intro = view;
                //notifyItemInserted(0);
                break;

            case TYPE_HEADER_TXT:
                head_text = view;
                textHolder=new ViewHolder(view,1,mContext);
                break;

            case TYPE_HEADER_LEAST:
                head_least = view;
                //notifyItemInserted(1);
                break;
        }
    }
    public View getHeaderView(int type)
    {
        switch(type){
            case TYPE_HEADER_INTRO:
                return head_intro;

            case TYPE_HEADER_TXT:
                    return head_text;

            case TYPE_HEADER_LEAST:
                    return head_least;
        }
        return null;
    }
    public void introClicked(Button button)
    {
        if(clicked){
            clicked=false;
            button.setText(">故事簡介<");
            //notifyItemInserted(1);

        }
        else{
            clicked=true;
            button.setText("<故事簡介>");
            //notifyItemRemoved(1);
        }
    }
    public boolean isIntroClickerd(){
        return clicked;
    }
    public void read(int position){
        tmp=mList.get(position);
        map.remove(tmp);
        map.put(tmp,true);
    }
    public int clicked(int position){
        if(map.get(mList.get(position))==true)
            return 1;
        return 0;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(head_intro==null||head_least==null||head_text==null)
            return new ViewHolder(mLayoutInflater.inflate(R.layout.chapter_btn_center, parent, false),3,mContext);
        switch(viewType){
            case 0:
                return new ViewHolder(head_intro,0,mContext);
            case 1:
                return new ViewHolder(head_text,2,mContext);
            case 2:
                return new ViewHolder(head_least,2,mContext);
            case 3:
                return new ViewHolder(mLayoutInflater.inflate(R.layout.chapter_btn_left, parent, false),3,mContext);
            case 4:
                return new ViewHolder(mLayoutInflater.inflate(R.layout.chapter_btn_center, parent, false),4,mContext);
        }
        return new ViewHolder(mLayoutInflater.inflate(R.layout.chapter_btn_right, parent, false),5,mContext);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if(holder.getHolderType()<3)
            return;
        else
            tmp=mList.get(position)-2;
            holder.mBtn.setText("第"+tmp+"話");
        /*if(type==0)
            holder.mBtn.setAlpha(1);
        else
            holder.mBtn.setAlpha((float) 0.5);*/
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        //可能是header
        //position=position/3;
        if(position<3)
            return position;
        else if(position%3==0)
            return 3;
        else if(position%3==1)
            return 4;
        else
            return 5;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        Button mBtn;
        Context mContext;
        int type;
        ViewHolder(View view,int viewType,Context c) {
            super(view);
            mContext=c;
            type=viewType;
            if(viewType>2) {
                mBtn = (Button) view.findViewById(R.id.button);
                mBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        v.setAlpha((float) 0.5);
                        Log.d("ViewHolder", "onClick--> position = " + getPosition());
                        Intent intent = new Intent();
                        intent.setClass(mContext, ContentActivity.class);
                        mContext.startActivity(intent);
                    }
                });
            }
        }
        public int getHolderType()
        {
            return type;
        }
    }

}
