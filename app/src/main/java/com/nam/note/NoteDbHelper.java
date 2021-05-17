package com.nam.note;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.text.CaseMap;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class NoteDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Note.db";
    private static final int DATABASE_VERSION = 1;

    private static NoteDbHelper instance;
    private SQLiteDatabase db;
    public NoteDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public static synchronized NoteDbHelper getInstance(Context context) {
        if (instance == null) {
            instance = new NoteDbHelper(context.getApplicationContext());
        }
        return instance;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        this.db = sqLiteDatabase;
        final String SQL_CREATE_TITLE_TABLE = "CREATE TABLE " +
                NoteContract.TitleTable.TABLE_NAME + " ( " +
                NoteContract.TitleTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NoteContract.TitleTable.COLUMN_TITLE + " TEXT ) ";
        db.execSQL(SQL_CREATE_TITLE_TABLE);
    }
    public void addTitle(Item content) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NoteContract.TitleTable.COLUMN_TITLE, content.getContent());
        db.insert(NoteContract.TitleTable.TABLE_NAME, null, cv);
    }
    public int updateTitle(String id, Item title) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NoteContract.TitleTable.COLUMN_TITLE, title.getContent());

        return db.update(NoteContract.TitleTable.TABLE_NAME, cv, NoteContract.TitleTable._ID + " = ? ", new String[]{String.valueOf(id)});
    }
    public void deleteOneTitle(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(NoteContract.TitleTable.TABLE_NAME, NoteContract.TitleTable._ID + " = ? ", new String[]{String.valueOf(id)});
    }
    public List<Item> getAllTitle() {
        List<Item> list = new ArrayList<>();
        db = getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM " + NoteContract.TitleTable.TABLE_NAME, null);
        if (c.moveToFirst()) {
            do {
                Item title = new Item();
                title.setId((c.getInt(0)));
                title.setContent(c.getString(1));
                list.add(title);
            } while (c.moveToNext());
        }
        c.close();
        return list;
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + NoteContract.TitleTable.TABLE_NAME);

        onCreate(db);
    }
    @Override
    public synchronized void close() {
        if (db != null) {
            db.close();
            super.close();
        }
    }
}
