package com.example.deer.manga.Content;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.deer.manga.Adapter.PictureAdapter;
import com.example.deer.manga.R;

/**
 * Created by deer on 2016/10/7.
 * 漫畫內容布局
 */

public class ContentFragment extends Fragment{
    private RecyclerView recyclerView;
    private PictureAdapter mAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.content_fragment_view, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        recyclerView=(RecyclerView)view.findViewById(R.id.pic_recycler);
        mAdapter=new PictureAdapter(getActivity());
        mAdapter.add(20);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(mAdapter);
    }
    @Override
    public void onStart() {
        super.onStart();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    @Override
    public void onResume() {
        super.onResume();
    }
}
