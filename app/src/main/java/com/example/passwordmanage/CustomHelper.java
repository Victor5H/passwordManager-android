package com.example.passwordmanage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.passwordmanage.models.Entries_Model;

import java.util.ArrayList;
import java.util.List;


public class CustomHelper extends SQLiteOpenHelper {
    Context mCon;
    SQLiteDatabase sqldb;
    static final String DB_NAME = "pass";
    static final String TBL_NAME = "keys";
    static final String COL_1 = "Name";
    static final String COL_2 = "word";
    static final String COL_3 = "tag";
    static final String COL_4 = "Preword";
    static final int VER = 1;
    String TAG = "Database";


    public CustomHelper(@Nullable Context context) {
        super(context, DB_NAME, null, VER);
        mCon = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TBL_NAME + " (id integer primary key autoincrement, " + COL_1 + " text, " + COL_2 + " text, " + COL_3 + " text, " + COL_4 + " text );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insert(String name, String word, String tag) {
        sqldb = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_1, name);
        cv.put(COL_2, word);
//        cv.put(COL_3,tag);
        long ret = sqldb.insert(TBL_NAME, null, cv);
        sqldb.close();
        Log.d(TAG, "insert: " + ret);
        return ret;
    }


    public long add(Entries_Model entriesModel) {
        sqldb = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_1, entriesModel.getName());
        cv.put(COL_2, entriesModel.getWord());
        cv.put(COL_3, entriesModel.getTag());
        cv.put(COL_4, entriesModel.getPre_Word());
        return sqldb.insert(TBL_NAME, null, cv);

    }

    public int delete(int id) {
        sqldb = this.getWritableDatabase();
        int ret = sqldb.delete(TBL_NAME, "id=?", new String[]{Integer.toString(id)});
        sqldb.close();
        Log.d(TAG, "delete: " + ret);
        return ret;
    }

    public int update(Entries_Model entriesModel) {
        String word = "";
        ContentValues cv = new ContentValues();
        int id = entriesModel.getId();
        cv.put(COL_1, entriesModel.getName());
        cv.put(COL_2, entriesModel.getWord());
        cv.put(COL_3, entriesModel.getTag());
        sqldb = this.getReadableDatabase();
        Cursor c = sqldb.rawQuery("Select " + COL_2 + " from " + TBL_NAME + " where id = " + id + " ;", null);
        while (c.moveToNext()) {
            word = c.getString(0);
        }
        entriesModel.setPre_Word(word);
        if(!(entriesModel.getPre_Word().equals(entriesModel.getWord()))){
            cv.put(COL_4, entriesModel.getPre_Word());
        }
        sqldb.close();
        sqldb = this.getWritableDatabase();
        int ret = sqldb.update(TBL_NAME, cv, "id=?", new String[]{Integer.toString(id)});
        sqldb.close();
        Log.d(TAG, "update: " + ret);
        return ret;
    }

    public Cursor dis() {
        sqldb = this.getReadableDatabase();
        return sqldb.rawQuery("Select * from " + TBL_NAME, null);
    }

    public List<Entries_Model> get_All() {
        List<Entries_Model> ret_list = new ArrayList<>();
        sqldb = this.getReadableDatabase();
        Cursor cursor = sqldb.rawQuery("Select * from " + TBL_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String word = cursor.getString(2);
                String tag = cursor.getString(3);
                String preword = cursor.getString(4);
                Entries_Model em = new Entries_Model(id, name, word, tag, preword);
                ret_list.add(em);

            } while (cursor.moveToNext());
        } else {

        }
        cursor.close();
        return ret_list;
    }

    public List<Entries_Model> searchByTag(String t) {
        List<Entries_Model> ret_list = new ArrayList<>();
        sqldb = this.getReadableDatabase();
        t = "%" + t + "%";
        Cursor cursor = sqldb.rawQuery("Select * from " + TBL_NAME + " where " + COL_3 + " like ?", new String[]{t});
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String word = cursor.getString(2);
                String tag = cursor.getString(3);
                String preword = cursor.getString(4);
                Entries_Model em = new Entries_Model(id, name, word, tag, preword);
                ret_list.add(em);

            } while (cursor.moveToNext());
        } else {

        }
        cursor.close();
        return ret_list;
    }

    public List<Entries_Model> searchByCharTag(String search){
        List<Entries_Model> retList=new ArrayList<>();
        sqldb=this.getReadableDatabase();
        search="%"+search+"%";
        Cursor cursor = sqldb.rawQuery("Select * from "+TBL_NAME+" where "+COL_3+" like ?",new String[]{search});
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String word = cursor.getString(2);
                String tag = cursor.getString(3);
                String preword = cursor.getString(4);
                Entries_Model em = new Entries_Model(id, name, word, tag, preword);
                retList.add(em);

            } while (cursor.moveToNext());
        } else {

        }
        cursor.close();
        return retList;
    }
    public List<Entries_Model> searchByID(String t) {
        List<Entries_Model> ret_list = new ArrayList<>();
        sqldb = this.getReadableDatabase();
        t = "%" + t + "%";
        Cursor cursor = sqldb.rawQuery("Select * from " + TBL_NAME + " where " + COL_1 + " like ?", new String[]{t});
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String word = cursor.getString(2);
                String tag = cursor.getString(3);
                String preword = cursor.getString(4);
                Entries_Model em = new Entries_Model(id, name, word, tag, preword);
                ret_list.add(em);

            } while (cursor.moveToNext());
        } else {

        }
        cursor.close();
        return ret_list;
    }
    public List<Entries_Model> searchByCharID(String search){
        List<Entries_Model> retList=new ArrayList<>();
        sqldb=this.getReadableDatabase();
        search="%"+search+"%";
        Cursor cursor = sqldb.rawQuery("Select * from "+TBL_NAME+" where "+COL_1+" like ?",new String[]{search});
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String word = cursor.getString(2);
                String tag = cursor.getString(3);
                String preword = cursor.getString(4);
                Entries_Model em = new Entries_Model(id, name, word, tag, preword);
                retList.add(em);

            } while (cursor.moveToNext());
        } else {

        }
        cursor.close();
        return retList;
    }
}
