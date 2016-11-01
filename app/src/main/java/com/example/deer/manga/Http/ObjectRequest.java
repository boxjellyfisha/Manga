package com.example.deer.manga.Http;

import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.deer.manga.Chose.ChoseActivity;
import com.example.deer.manga.Constans;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by deer on 2016/10/24.
 */

public class ObjectRequest extends AsyncTask<Integer, Void, Map<String,Object>> {


    private final static String TAG = ObjectRequest.class.getSimpleName();
    private Map<String,Object> map;
    private ControllerQueue mQueue;
    private ChoseActivity mActivity;
    private boolean state;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mQueue=ControllerQueue.getInstance();
        map=new HashMap<>();
        mActivity=ChoseActivity.getInstance();
    }

    @Override
    protected Map<String,Object> doInBackground(Integer... which) {
        int count = which.length;
        long totalSize = 0;
        Log.d("TAG","doinbg");
        switch(which[0]) {
            case Constans.M_ITEM_TYPE:
                getMangaItem();
                break;
            case Constans.C_ITEM_TYPE:
                getChItem();
                break;
            case Constans.P_ITEM_TYPE:
                getPicItem();
                break;
        }
            //publishProgress((int) ((i / (float) count) * 100));
            // Escape early if cancel() is called

        if (isCancelled())return null;

        return map;
    }

    protected void onProgressUpdate() {
        //Integer... progress
    }

    protected void onPostExecute(Map<String,Object> result) {
        Log.d("TAG","finish");
    }

    /*---API------------------------------------------------------------------------------------------------------------------------------------*/
    //取得漫畫細節
    private boolean getMangaItem(){
        map.clear();
        JsonObjectRequest jsonObjectRequest = setting(Request.Method.GET,Constans.M_REQUEST_URL);
        map.put(Constans.ITEM_TYPE,Constans.M_ITEM_TYPE);
        mQueue.addToRequestQueue(jsonObjectRequest, Constans.M_REQUEST_TAG);
        return false;
    }

    //取得漫畫章節數
    private boolean getChItem(){
        map.clear();
        JsonObjectRequest jsonObjectRequest = setting(Request.Method.POST,Constans.C_REQUEST_URL);
        map.put(Constans.ITEM_TYPE,Constans.C_ITEM_TYPE);
        mQueue.addToRequestQueue(jsonObjectRequest, Constans.C_REQUEST_TAG);
        return false;
    }

    //JSON取得
    private JsonObjectRequest setting(int type,String s){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(type,
                Constans.H_REQUEST_URL+s, //target url
                null, //json request GET=null , POST=other
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("TAG","response");
                        try {
                            JSONArray res=response.getJSONArray("Items");
                            JSONObject tmp;
                            JSONArray tmpArray=new JSONArray();
                            for(int j=0;j<res.length();j++){
                                tmp=(JSONObject) res.get(j);
                                if(tmpArray.length()==0)tmpArray=tmp.names();
                                for(int i=0;i<tmpArray.length();i++) {
                                    String s=tmpArray.get(i).toString();
                                    Object o=tmp.get(tmpArray.get(i).toString());
                                    Log.d("TAG", s+" : "+o);
                                    map.put(s,o);
                                }
                                mActivity.reSetting(map);
                            }
                        } catch (JSONException e) {
                            //some exception handler code.
                            Log.e("TAG","error:json",e);
                        }
                    }
                },//if success
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("TAG", error.getMessage(), error);
                    }
                }//if error
        );
        return jsonObjectRequest;
    }

    //取得回述內容 圖片url
    private Map<String,Object> getPicItem(){

        return map;
    }

}
