package com.example.deer.manga;

/**
 * Created by deer on 2016/10/7.
 * 用來放置常用的固定字串
 */

public class Constans {

    public final static String app_name="不機嫌なモノノケ庵";
    public final static String splash_tag="splashfragment_tag";
    public final static String menu_tag="menufragment_tag";

    /*ABOUT HTTP REQUEST*/
    public final static String M_REQUEST_TAG="getManagItem Request";
    public final static String C_REQUEST_TAG="getChgItem Request";
    public final static String P_REQUEST_TAG="getPicItem url Request";

    //URL
    public final static String H_REQUEST_URL="http://192.168.1.111:5000";
    public final static String M_REQUEST_URL="/GetMangaItem";
    public final static String C_REQUEST_URL="/GetChItem";
    public final static String P_REQUEST_URL="/GetPicItem";

    public final static String ITEM_TYPE="http response";
    public final static int M_ITEM_TYPE=0;
    public final static int C_ITEM_TYPE=1;
    public final static int P_ITEM_TYPE=2;

    //response json key
    public final static String M_NAME_KEY="name";
    public final static String M_INTRO_KEY="intro";
    public final static String M_ID_KEY="id";
    public final static String M_LATEST_KEY="latest";
    public final static String M_CH_KEY="chapter";
    public final static String M_IMG_KEY="image";

    public final static String C_NAME_KEY="name";
    public final static String C_NAME_PAGE="page";
    public final static String C_NAME_TYPE="type";
    public final static String C_NAME_ID="id";

    public final static String P_NAME_KEY="name";
    public final static String P_URL_KEY="url";

}
