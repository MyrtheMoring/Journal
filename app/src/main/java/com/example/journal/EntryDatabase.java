package com.example.journal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EntryDatabase extends SQLiteOpenHelper {

    public static final String DBNAME = "entrytables";
    public static final String TITLE = "title";
    public static final String ID = "_id";
    public static final String CONTENT = "content";
    public static final String MOOD = "mood";
    public static final String DATETIME = "timestamp";

    private static EntryDatabase instance;

    private EntryDatabase(Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String s = "CREATE TABLE IF NOT EXISTS "
                + DBNAME + " (" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TITLE + " TEXT NOT NULL, " +
                CONTENT + " TEXT NOT NULL, " +
                DATETIME + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP ," +
                MOOD + " INTEGER DEFAULT 0 " +
                ");";
        db.execSQL(s);

//        Entry e1 = new Entry(1, "hallo", "hoe gaat het", "10 maart", 1);
//        Entry e2 = new Entry(2, "hallo", "hoe gaat het", "10 maart", 1);
//        Entry e3 = new Entry(3, "hallo", "hoe gaat het", "10 maart", 1);
//        db.insert(e1);
//        instance.insert(e2);
//        instance.insert(e3);
    }

    public static EntryDatabase getInstance(Context context){
        if (instance != null){
            return instance;
        }
        else {
            instance = new EntryDatabase(context);
            return instance;
        }
    }

    public Cursor selectAll() {
        SQLiteDatabase db = getReadableDatabase();
        String Query ="SELECT * FROM entrytables";
        Cursor cursor = db.rawQuery(Query, null);
        return cursor;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DBNAME);
        onCreate(db);
    }

    public void insert(Entry e){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TITLE, e.getTitle());
        cv.put(CONTENT, e.getContent());
        cv.put(MOOD, e.getMood());
        db.insert(DBNAME, null, cv);

    }

    public void delete(long id){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM "+DBNAME+" WHERE "+ID+"="+id);
    }
}
