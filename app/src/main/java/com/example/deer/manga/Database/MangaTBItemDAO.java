package com.example.deer.manga.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.deer.manga.Constans;

/**
 * Created by deer on 2016/10/26.
 */

public class MangaTBItemDAO {
    // 表格名稱   
    public static final String TABLE_NAME = "mangatable";
    // 編號表格欄位名稱，固定不變
    public static final String KEY_ID = "_id";
    // 其它表格欄位名稱  定義在Constans中 與Json key相同

    // 使用上面宣告的變數建立表格的SQL指令
    public static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                    KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    Constans.M_NAME_KEY + " TEXT NOT NULL, " +
                    Constans.M_INTRO_KEY + " TEXT, " +
                    Constans.M_LATEST_KEY+ "INTEGER, " +
                    Constans.M_CH_KEY+ " INTEGER NOT NULL, " +
                    Constans.M_IMG_KEY + " TEXT)";

    // 資料庫物件   
    private SQLiteDatabase db;
    // 建構子
    public MangaTBItemDAO(Context context) {
        db = MangaDB.getDatabase(context);
        db.execSQL(MangaTBItemDAO.CREATE_TABLE);
    }

    public void dropTable(String name)
    {
        db.execSQL("DROP TABLE IF EXISTS "+name);
    }
    // 關閉資料庫
    public void close() {
        db.close();
    }

}
