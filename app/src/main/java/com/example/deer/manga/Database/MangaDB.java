package com.example.deer.manga.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by deer on 2016/10/26.
 */

public class MangaDB extends SQLiteOpenHelper {

    // 資料庫名稱
    public static final String DATABASE_NAME = "myManga.db";

    //資料庫版本關係到App更新時，資料庫是否要調用onUpgrade()
    private static final int VERSION = 1;//資料庫版本

    // 資料庫物件，固定的欄位變數
    private static SQLiteDatabase database;

    //建構子
    public MangaDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public MangaDB(Context context, String name) {
        this(context, name, null, VERSION);
    }

    public MangaDB(Context context, String name, int version) {
        this(context, name, null, version);
    }

    // 需要資料庫的元件呼叫這個方法，這個方法在一般的應用都不需要修改
    public static SQLiteDatabase getDatabase(Context context) {
        if (database == null || !database.isOpen()) {
            database = new MangaDB(context, DATABASE_NAME,
                    null, VERSION).getWritableDatabase();
        }

        return database;
    }

    //輔助類建立時運行該方法
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MangaTBItemDAO.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //oldVersion=舊的資料庫版本；newVersion=新的資料庫版本
        db.execSQL("DROP TABLE IF EXISTS "+ MangaTBItemDAO.TABLE_NAME); //刪除舊有的資料表
        onCreate(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        // TODO 每次成功打開數據庫後首先被執行
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public synchronized void close() {
        super.close();
    }
    
}
