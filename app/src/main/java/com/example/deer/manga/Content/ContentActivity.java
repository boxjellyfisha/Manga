package com.example.deer.manga.Content;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.example.deer.manga.R;

/**
 * Created by deer on 2016/10/7.
 * 漫畫內容活動
 */

public class ContentActivity extends Activity {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private ContentFragment contentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_activity_view);

        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        ContentFragment fragment = new ContentFragment();
        fragmentTransaction.add(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
        contentFragment=(ContentFragment)fragmentManager.findFragmentById(R.id.fragment_container);

    }
}
