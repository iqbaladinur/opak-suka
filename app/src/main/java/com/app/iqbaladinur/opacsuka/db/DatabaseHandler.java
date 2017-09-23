package com.app.iqbaladinur.opacsuka.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.app.iqbaladinur.opacsuka.Model.SavedData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sanja on 02/06/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "cacheDb";
    private static final String TABLE_NAME = "recents_words";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "keywords";

    private static final String TABLE_TWO = "saved_data";
    private static final String ID_KEY = "id";
    private static final String JUDUL = "judul";
    private static final String DESKRIPSI = "des";
    private static final String LOKASI = "lokasi";

    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT"
                + " )";
        String CREATE_TABLE_TWO = "CREATE TABLE "+ TABLE_TWO + "("
                + ID_KEY + " INTEGER PRIMARY KEY, " + JUDUL+ " TEXT, "
                + DESKRIPSI + " TEXT, "+LOKASI+ " TEXT )";
        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_TABLE_TWO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TWO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //Table 1
    public void save(String recent_keywords){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, recent_keywords);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public String getKeywordsById(int id){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{KEY_ID, KEY_NAME},
                KEY_ID+" = ?", new String[]{java.lang.String.valueOf(id)}, null, null, null);
        if (cursor != null){
            cursor.moveToFirst();
        }
        return cursor.getString(1);
    }

    public boolean isKeywordExist(String keyword){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{KEY_ID, KEY_NAME},
                KEY_NAME+" = ?", new String[]{keyword}, null, null, null);
        if (cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }

    public List <String> getAll(){
        List return_value = new ArrayList<String>();
        String query = "SELECT * FROM "+ TABLE_NAME+ " ORDER BY "+KEY_ID+" DESC";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()){
            do {
                return_value.add(cursor.getString(1));
            }while (cursor.moveToNext());
        }

        return return_value;
    }

    private String getLastIndex(){
        String query = "SELECT "+KEY_ID+" FROM "+TABLE_NAME+ " ORDER BY "+KEY_ID+" ASC LIMIT 1";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        return cursor.getString(0);
    }
    public void  deleteLast(){

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID +" =?",new String[]{this.getLastIndex()});
    }

    //table 2
    public void saveDataStore(SavedData data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(JUDUL, data.getJudul());
        values.put(DESKRIPSI, data.getDes());
        values.put(LOKASI, data.getLokasi());
        db.insert(TABLE_TWO, null, values);
        db.close();
    }

    public List <SavedData> getAllSavedDataStore(){
        List return_value = new ArrayList<SavedData>();
        String query = "SELECT * FROM "+ TABLE_TWO+ " ORDER BY "+ID_KEY+" DESC";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()){
            do {
                return_value.add(
                        new SavedData(
                                cursor.getInt(0),
                                cursor.getString(1),
                                cursor.getString(2),
                                cursor.getString(3)
                        )
                );
            }while (cursor.moveToNext());
        }

        return return_value;
    }

    public boolean isDataExist(String judul){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_TWO, new String[]{ID_KEY, JUDUL, DESKRIPSI, LOKASI},
                JUDUL+" = ?", new String[]{judul}, null, null, null);
        if (cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }

    public void deleteSavedDataStore(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM "+ TABLE_TWO;
        db.execSQL(query);
    }
}
