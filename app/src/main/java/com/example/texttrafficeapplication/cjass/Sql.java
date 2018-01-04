package com.example.texttrafficeapplication.cjass;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 赵洪斌 on 2017/10/22.
 */

public class Sql {
    private static final String DB_TABLE ="assadasd" ;
    private static final String KEY_ID ="id" ;
    private static final String KEY_NAME ="name" ;
    private static final String KEY_Methy ="myth" ;
    private static final String KEY_Time ="time" ;
    private static final String KEY_car ="car" ;
    private  String dp="asdsdasd_dp";
    SQLiteDatabase sql;
    Context context;
    public  Sql(Context con){
        context=con;
    }
    asffad asss;
    class  asffad extends SQLiteOpenHelper {
        private static final String DB_CREATE = "create table " + DB_TABLE + "(" + KEY_ID + " integer primary key autoincrement, "
                + KEY_NAME + " text not null, " + KEY_Methy + " text," + KEY_Time + " text," + KEY_car + " text);";
        public asffad(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DB_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.close();
            onCreate(db);
        }
    }
    public void openDB() throws SQLiteException {
     //   asss = new asffad(context, dp, null, 1);
        try {
            sql = asss.getWritableDatabase();
        } catch (SQLiteException ex) {
            sql = asss.getReadableDatabase();
        }
    }
    public long Insert(Carinformation people) {
        ContentValues newValue = new ContentValues();
        newValue.put(KEY_NAME, people.Name);
        newValue.put(KEY_Methy, people.Money);
        newValue.put(KEY_Time, people.Time);
        newValue.put(KEY_car, people.Shusi);
        return sql.insert(DB_TABLE, null, newValue);
    }
    public Carinformation[] queryByName(String name) {
        Cursor result = sql.query(DB_TABLE, new String[]{KEY_ID, KEY_NAME, KEY_Methy, KEY_Time, KEY_car}, KEY_NAME + "=" + "'" + name + "'", null, null, null, null);
        return ConvertToPeopleInfo(result);
    }

    private Carinformation[] ConvertToPeopleInfo(Cursor cursor) {
        int resultCounts = cursor.getCount();
        if (resultCounts == 0 || !cursor.moveToFirst()) {
            return null;
        }
        Carinformation[] peoples = new Carinformation[resultCounts];
        for (int i = 0; i < resultCounts; i++) {
            peoples[i] = new Carinformation(cursor.getString(cursor.getColumnIndex(KEY_NAME)),
                    cursor.getString(cursor.getColumnIndex(KEY_Methy)),
                    cursor.getString(cursor.getColumnIndex(KEY_Time)),
                    cursor.getString(cursor.getColumnIndex(KEY_car)));
            peoples[i].ID = cursor.getInt(cursor.getColumnIndex(KEY_ID));
            cursor.moveToNext();
        }
        int[] dd=new int[5];
        dd[0]=4;

        return peoples;
    }
}
