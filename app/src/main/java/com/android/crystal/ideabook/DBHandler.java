package com.android.crystal.ideabook;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;
import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1; //whenever update the db, update the version here.
    private static final String DATABASE_NAME = "IdeaBookDB.db";

    public static final String TABLE_IDEAS = "ideas";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_IDEATITLE = "_ideaTitle";
    public static final String COLUMN_IDEACONTENT = "_ideaContent";

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, DATABASE_NAME,factory, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //will be called on the very first time
        //query to create the table
        String query = "CREATE TABLE " + TABLE_IDEAS + " ( " +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_IDEATITLE + " TEXT, " +
                COLUMN_IDEACONTENT + " TEXT); ";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_IDEAS);
        onCreate(db);
    }

    //add a new row
    public void addIdea(Ideas idea) {
        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_IDEATITLE, idea.get_ideaTitle());
            values.put(COLUMN_IDEACONTENT, idea.get_ideaContent());


            SQLiteDatabase db =  SQLiteDatabase.openDatabase("/data/user/0/com.android.crystal.ideabook/databases/IdeaBookDB.db",null,SQLiteDatabase.OPEN_READWRITE);
           // SQLiteDatabase db = getWritableDatabase();
            db.insert(TABLE_IDEAS, null, values);
            db.close();
        }catch(SQLiteException e){
            e.printStackTrace();
        }
    }

    //delete a row from the db table
//    public void deleteIdea(int ideaId){
//        SQLiteDatabase db = getWritableDatabase();
//        db.execSQL("DELETE FROM" + TABLE_IDEAS + "WHERE" + COLUMN_ID + "=" + ideaId + ";");
//    }

    public ArrayList<Ideas> getIdeasList(){
        ArrayList<Ideas> ideasList = new ArrayList<Ideas>();
        SQLiteDatabase db =  SQLiteDatabase.openDatabase("/data/user/0/com.android.crystal.ideabook/databases/IdeaBookDB.db",null,SQLiteDatabase.OPEN_READWRITE);
//        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_IDEAS + ";";
        Cursor c = db.rawQuery(query,null);
        try{
            if(c.moveToFirst()){
                do{
                    Ideas idea = new Ideas(Integer.parseInt(c.getString(0)),c.getString(1),c.getString(2));
                    ideasList.add(idea);
                }while(c.moveToNext());
            }
        }
        catch (SQLiteException e){
            //log exception here
            return null;
        }finally {
            c.close();
            db.close();
        }
        return ideasList;
    }

    public Ideas getIdeaFromId(int ideaId){
        Ideas idea = new Ideas();
        SQLiteDatabase db =  SQLiteDatabase.openDatabase("/data/user/0/com.android.crystal.ideabook/databases/IdeaBookDB.db",null,SQLiteDatabase.OPEN_READWRITE);
        String query = "SELECT * FROM " + TABLE_IDEAS + ";";
        //String query = "SELECT TOP 1 FROM " + TABLE_IDEAS + " WHERE" + COLUMN_ID + " =?";
        Cursor c = db.rawQuery(query,null);
        try{
            if(c.moveToFirst()){
                do{
                    if(Integer.parseInt(c.getString(0))==ideaId) {
                        idea.set_id(Integer.parseInt(c.getString(0)));
                        idea.set_ideaTitle(c.getString(1));
                        idea.set_ideaContent(c.getString(2));
                    }
                }while(c.moveToNext());

            }
        }catch(SQLiteException e){
            return null;
        }finally{
            c.close();
            db.close();
        }
        return idea;
    }
}


























