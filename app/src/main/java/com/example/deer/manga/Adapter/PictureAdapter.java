package com.example.deer.manga.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.deer.manga.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by deer on 2016/10/7.
 * 讀取內容時用 content recyclerView adapter
 */

public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.ViewHolder> {
    private List<Integer> mList;
    private LayoutInflater mInflater;
    private Context mContext;

    public PictureAdapter(Context context)
    {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mList=new ArrayList<>();
    }

    public void add(int b)
    {
        for(int i=0;i<b;i++){
            mList.add(i);
        }
    }
    @Override
    public PictureAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PictureAdapter.ViewHolder(mInflater.inflate(R.layout.content_pic, parent, false));
    }

    @Override
    public void onBindViewHolder(PictureAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;

        ViewHolder(View view) {
            super(view);
            img=(ImageView)view.findViewById(R.id.imageView);
        }
    }
}
