package co.chagas.chewsv2.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.SyncStateContract;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

import android.util.Log;

import java.sql.SQLData;

/**
 * Created by andrealimachagas on 16/04/2015.
 */
public class MyDBhelper extends SQLiteOpenHelper{
    private static final String CREATE_TABLE = "create table " +
            Constants.TABLE_NAME + " (" +
            Constants.KEY_ID + " integer primary key autoincrement, " +
            Constants.TITLE_NAME + " text not null, " +
            Constants.TYPE_NAME + " text, " +
            Constants.ADDRESS_NAME + " text, " +
            Constants.PRICE_NAME + " text, " +
            Constants.DRINK_NAME + " text, " +
            Constants.COMMENT_NAME + " text);";

    public MyDBhelper(Context context, String name, CursorFactory factory,
                      int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.v("MyDBhelper onCreate", "Creating all the tables");
        try {
            db.execSQL(CREATE_TABLE);
        } catch(SQLiteException ex) {
            Log.v("Create table exception", ex.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.v("TaskDBAdapter", "Upgrading from version " + oldVersion + " to " + newVersion +
                ", which will destroy all old data");
        db.execSQL("drop table if exists " + Constants.TABLE_NAME);
        onCreate(db);
    }
}
