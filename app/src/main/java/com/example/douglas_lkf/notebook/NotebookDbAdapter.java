package com.example.douglas_lkf.notebook;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.security.PrivateKey;

/**
 * Created by Douglas_lkf on 9/3/2016.
 */
public class NotebookDbAdapter {

    private static final String DATABASE_NAME = "notebook.db";
    private static final int DATABASE_VERSION = 1;

    public static final String NOTE_TABLE = "note";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_MESSAGE = "message";
    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_DATE = "date";

    private String[] allColumns = {COLUMN_ID, COLUMN_TITLE, COLUMN_MESSAGE, COLUMN_CATEGORY, COLUMN_DATE};

    public static final String CREATE_TABLE_NOTE  =
            "CREATE TABLE " + NOTE_TABLE + " ("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_TITLE + " text not null, "
            + COLUMN_MESSAGE + " text not null, "
            + COLUMN_CATEGORY + " integer not null, "
            + COLUMN_DATE + ");";



    private SQLiteDatabase sqLiteDB;
    private Context context;
    private NotebookDbHelper notebookDbHelper;

    public NotebookDbAdapter (Context ctx){

        this.context = ctx;


    }

    public NotebookDbAdapter open() throws android.database.SQLException{

        notebookDbHelper = new NotebookDbHelper(this.context);
        sqLiteDB = notebookDbHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        notebookDbHelper.close();
    }



    private static class NotebookDbHelper extends SQLiteOpenHelper{

        NotebookDbHelper(Context context){

            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate (SQLiteDatabase db){
            db.execSQL(CREATE_TABLE_NOTE);
        }

        @Override
        public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion){

            Log.w(NotebookDbHelper.class.getName(),
                    "Upgrading database from version " + oldVersion + " to " +
            newVersion + ", which will destroy all old data.");

            db.execSQL("DROP TABLE IF EXISTS " + NOTE_TABLE);
            onCreate(db);

        }


    }
}