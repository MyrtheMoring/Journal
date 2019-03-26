package com.example.journal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/** The class EntryDatabase that extends the SQL helper to make SQL queries. */
public class EntryDatabase extends SQLiteOpenHelper {

    public static final String DBNAME = "entrytables";
    public static final String TITLE = "title";
    public static final String ID = "_id";
    public static final String CONTENT = "content";
    public static final String MOOD = "mood";
    public static final String DATETIME = "timestamp";

    private static EntryDatabase instance;

    /** The constructor for the EntryDatabase. */
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
    }

    /** Returns the value of instance if available. Otherwise, it will call the constructor. */
    public static EntryDatabase getInstance(Context context){
        if (instance != null){
            return instance;
        }
        else {
            instance = new EntryDatabase(context);
            return instance;
        }
    }

    /** SQL query to select all entries. */
    public Cursor selectAll() {
        SQLiteDatabase db = getReadableDatabase();
        String Query ="SELECT * FROM entrytables";
        Cursor cursor = db.rawQuery(Query, null);
        return cursor;
    }

    /** Drops the entries table (if it exists) and recreates it by calling onCreate(). */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DBNAME);
        onCreate(db);
    }

    /** Opens a connection to the database and creates a new ContentValues object.
     * It will add the values for the title, content and mood and call the insert method. */
    public void insert(Entry e){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TITLE, e.getTitle());
        cv.put(CONTENT, e.getContent());
        cv.put(MOOD, e.getMood());
        db.insert(DBNAME, null, cv);

    }

    /** In order to delete an entry. */
    public void delete(long id){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM "+DBNAME+" WHERE "+ID+"="+id);
    }
}
