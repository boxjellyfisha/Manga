package com.example.deer.manga.Chose;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.deer.manga.Behavior.NewFAButton;
import com.example.deer.manga.Constans;
import com.example.deer.manga.Content.ContentActivity;
import com.example.deer.manga.Http.ConmuService;
import com.example.deer.manga.Http.ObjectRequest;
import com.example.deer.manga.R;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class ChoseActivity extends AppCompatActivity {
    private CollapsingToolbarLayout toolbar;
    private FloatingActionButton fab;
    private TextView stateTextView;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private ChoseFragment choseFragment;
    private SplashFragment splashFragment;
    private MenuFragment menuFragment;
    private Button menuBtn;
    private TextView mu_a;
    private ImageView mu_b;
    private ImageView mu_c;
    private ObjectAnimator ani;
    private ConmuService mRequest;
    private UpdateReceiver myUpdateReceiver;
    private Map<String,Object> mResponseMap;
    private boolean btnState=false;
    private static ChoseActivity mInstance;
    //SharedPreferences settings = getSharedPreferences("Preference", 0);
    public static synchronized ChoseActivity getInstance() {
        return mInstance;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chose_activity_view);
        mInstance = this;
        toolbar = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolbar.setCollapsedTitleTextColor(Color.WHITE);
        toolbar.setTitle(Constans.app_name);
        toolbar.setExpandedTitleColor(Color.TRANSPARENT);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        stateTextView=(TextView)findViewById(R.id.state_textView);
        menuBtn=(Button)findViewById(R.id.menu_button);
        mu_a=(TextView)findViewById(R.id.mu_a);
        mu_b=(ImageView)findViewById(R.id.mu_b);
        mu_c=(ImageView)findViewById(R.id.mu_c);
        mRequest = new ConmuService();
        setting();
    }
    public void setting()
    {
        CoordinatorLayout.LayoutParams p = (CoordinatorLayout.LayoutParams) fab.getLayoutParams();
        p.setBehavior(new NewFAButton(getApplicationContext()));
        fab.setLayoutParams(p);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(ChoseActivity.this, ContentActivity.class);
                startActivity(intent);
                Snackbar.make(view, "From FAB", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        choseFragment = new ChoseFragment();
        fragmentTransaction.add(R.id.chapterView, choseFragment);
        splashFragment=(SplashFragment)fragmentManager.findFragmentByTag(Constans.splash_tag);
        menuFragment=(MenuFragment)fragmentManager.findFragmentByTag(Constans.menu_tag);
        fragmentTransaction.hide(menuFragment);
        fragmentTransaction.commit();

        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choseFragment.setEnable(btnState);//false
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.show(menuFragment);
                fragmentTransaction.commit();
                menuFragment.setVisible();
                btnState = true;
                menuBtn.setVisibility(View.GONE);
                mu_a.setVisibility(View.GONE);
                mu_b.setVisibility(View.GONE);
                mu_c.setVisibility(View.GONE);
                animeRotateY(btnState);
                return;
            }
        });

        Intent serviceIntent = new Intent(this, ConmuService.class);
        bindService(serviceIntent, mServiceConnection, BIND_AUTO_CREATE);
        myUpdateReceiver = new UpdateReceiver();
    }
    /*連接服務*/
    public final ServiceConnection mServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            mRequest = ((ConmuService.LocalBinder) service).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mRequest = null;
        }
    };
    /*取得內部廣播 (來自服務的狀況回報)*/
    public class UpdateReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            Bundle b=intent.getExtras();
        }
    }
    public void menuClose(){

        choseFragment.setEnable(btnState);//true
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.hide(menuFragment);
        fragmentTransaction.commit();
        menuFragment.setInVisible();
        btnState= false;
        animeRotateY(btnState);
        menuBtn.setVisibility(View.VISIBLE);
        mu_a.setVisibility(View.VISIBLE);
        mu_b.setVisibility(View.VISIBLE);
        mu_c.setVisibility(View.VISIBLE);
    }
    @Override
    public void onStart(){
        super.onStart();
        splashFragment.animeRun();
        ObjectRequest b=new ObjectRequest();
        b.execute(Constans.M_ITEM_TYPE);
        ObjectRequest b2=new ObjectRequest();
        b2.execute(Constans.C_ITEM_TYPE);

    }
    public void splashEnd(){
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.hide(splashFragment);
        fragmentTransaction.commit();
        animeRotateY(btnState);
    }
    public void reSetting(Map<String,Object> map){
        switch((int)map.get(Constans.ITEM_TYPE))
        {
            case Constans.M_ITEM_TYPE:
                Log.d("TAG","one");
                toolbar.setTitle(map.get(Constans.M_NAME_KEY).toString());
                choseFragment.setIntroText(map.get(Constans.M_INTRO_KEY).toString()
                        + "\n 最近更新: " + map.get(Constans.M_LATEST_KEY).toString()
                        + "\n 連載狀態: " + map.get(Constans.M_CH_KEY).toString());
                break;
            case Constans.C_ITEM_TYPE:
                break;
            case Constans.P_ITEM_TYPE:
                break;
        }
    }
    @Override
    public void onResume(){
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed(){
        if(btnState){
            menuClose();
        }
    }
    //animation use  property animation
    public void animeRotateY(boolean state){
        if(ani==null) {
            ani = ObjectAnimator.ofFloat(findViewById(R.id.menu_button)  // 動畫目標
                    , "rotationY" // 動畫效果 (Y 軸翻轉)
                    , 0f          // 開始角度
                    , 180f        // 結束角度
            );
            ani.setDuration(1500);  // 動畫時間
            ani.setRepeatCount(ValueAnimator.INFINITE);
        }
        if(!state)
            ani.start();
        else
            ani.end();
    }

}
