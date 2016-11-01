package com.example.deer.manga.Http;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.deer.manga.Constans;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by deer on 2016/10/24.
 * 取得伺服器資源請求
 */

public class ConmuService extends Service{

    private final static String TAG = ConmuService.class.getSimpleName();

    /*綁定服務基本設置*/
    /*-------------------------------------------------------------------------------------------------------------------------------------------*/
    public class LocalBinder extends Binder {
        public ConmuService getService() {
            return ConmuService.this;
        }
    }
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }
    public final IBinder mBinder = new LocalBinder();
    /*-------------------------------------------------------------------------------------------------------------------------------------------*/


    @Override
    public void onCreate() {
        super.onCreate();
    }
}
