package com.example.pastpaper2019;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.strictmode.SqliteObjectLeakedViolation;

import androidx.annotation.Nullable;

import org.w3c.dom.Comment;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static  final String DATABASE_NAME="gamesInfo.db";
    public DBHelper(Context context) {
        super(context,DATABASE_NAME,null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE=
                " CREATE TABLE " + DatabaseMaster.Users.TABLE_NAME+" (" +
                        DatabaseMaster.Users._ID +" INTEGER PRIMARY KEY," +
                        DatabaseMaster.Users.COLUMN_NAME_USERNAME +" TEXT," +
                        DatabaseMaster.Users.COLUMN_NAME_PASSWORD +" TEXT)";

        String CREATE_GAMES_TABLE=
                " CREATE TABLE " + DatabaseMaster.Games.TABLE_NAME+" (" +
                        DatabaseMaster.Games._ID +" INTEGER PRIMARY KEY," +
                        DatabaseMaster.Games.COLUMN_NAME_GAME_NAME +" TEXT,"+
                        DatabaseMaster.Games.COLUMN_NAME_GAME_YEAR +" INTEGER)";
        String CREATE_COMMENTS_TABLE=
                " CREATE TABLE "+ DatabaseMaster.Comments.TABLE_NAME+" (" +
                        DatabaseMaster.Comments._ID+" INTEGER PRIMARY KEY," +
                        DatabaseMaster.Comments.COLUMN_NAME_GAME_NAME +" TEXT," +
                        DatabaseMaster.Comments.COLUMN_NAME_GAME_RATING +" INTEGER," +
                        DatabaseMaster.Comments.COLUMN_NAME_GAME_COMMENT +" TEXT)";


        db.execSQL(CREATE_GAMES_TABLE);
        db.execSQL(CREATE_COMMENTS_TABLE);
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long egisterUser(String username,String password){
    SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(DatabaseMaster.Users.COLUMN_NAME_USERNAME,username);
        values.put(DatabaseMaster.Users.COLUMN_NAME_PASSWORD,password);

        long val=db.insert(DatabaseMaster.Users.TABLE_NAME,null,values);
        return val;
    }


    public int loginUser(String username,String password){
        SQLiteDatabase db=getReadableDatabase();
        String [] pro={
                DatabaseMaster.Users._ID,
                DatabaseMaster.Users.COLUMN_NAME_USERNAME,
                DatabaseMaster.Users.COLUMN_NAME_PASSWORD
        };
        String sortBy= DatabaseMaster.Users.COLUMN_NAME_USERNAME+ " DESC";
        Cursor cursor=db.query(
                DatabaseMaster.Users.TABLE_NAME,
                pro,
                DatabaseMaster.Users.COLUMN_NAME_USERNAME+" =?",
                new String[]{ username},
                null,
                null,
                sortBy

        );
        ArrayList usernames=new ArrayList();
        ArrayList passwords=new ArrayList();

        while(cursor.moveToNext()){
            String uname=cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Users.COLUMN_NAME_USERNAME));
            String pwd=cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Users.COLUMN_NAME_PASSWORD));
            usernames.add(uname);
            passwords.add(pwd);
        }
        cursor.close();
        if(usernames.indexOf(username)>=0){
         if (passwords.get(usernames.indexOf(username)).equals(password)){
             return 1;
         }
         else {return 0;}
        }
        else{
            return 0;
        }

    }

    public boolean addGame(String name,int year){
    SQLiteDatabase db=getWritableDatabase();
    ContentValues values=new ContentValues();

    values.put(DatabaseMaster.Games.COLUMN_NAME_GAME_NAME,name);
        values.put(DatabaseMaster.Games.COLUMN_NAME_GAME_YEAR,year);

        long val=db.insert(DatabaseMaster.Games.TABLE_NAME,null,values);
        if(val>0){
            return true;
        }
        else{
            return false;
        }

    }

    public ArrayList viewGames(String req){
        SQLiteDatabase db=getReadableDatabase();
        String [] projection={
                DatabaseMaster.Games._ID,
                DatabaseMaster.Games.COLUMN_NAME_GAME_NAME,
                DatabaseMaster.Games.COLUMN_NAME_GAME_YEAR

        };

        String sortBy= DatabaseMaster.Games.COLUMN_NAME_GAME_NAME+ " DESC";
        Cursor cursor=db.query(
                DatabaseMaster.Games.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortBy
        );
        ArrayList names=new ArrayList();
        ArrayList years=new ArrayList();

        while (cursor.moveToNext()){
            String name=cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Games.COLUMN_NAME_GAME_NAME));
            String year=cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Games.COLUMN_NAME_GAME_YEAR));
            names.add(name);
            years.add(year);
        }
        cursor.close();
        if (req=="game"){
            return names;
        }
        else if(req=="year"){
            return years;
        }
        else{
            return  null;
        }

    }

    public long insertComments(String game,int rating,String comment){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(DatabaseMaster.Comments.COLUMN_NAME_GAME_NAME,game);
        values.put(DatabaseMaster.Comments.COLUMN_NAME_GAME_RATING,rating);
        values.put(DatabaseMaster.Comments.COLUMN_NAME_GAME_COMMENT,comment);
        long val=db.insert(DatabaseMaster.Comments.TABLE_NAME,null,values);
        return  val;
    }

    public ArrayList viewComments(String game){
        SQLiteDatabase db=getReadableDatabase();
        String [] projection={
                DatabaseMaster.Comments._ID,
                DatabaseMaster.Comments.COLUMN_NAME_GAME_NAME,
                DatabaseMaster.Comments.COLUMN_NAME_GAME_RATING,
                DatabaseMaster.Comments.COLUMN_NAME_GAME_COMMENT
        };
        String sortBy= DatabaseMaster.Comments.COLUMN_NAME_GAME_NAME+" DESC";

        Cursor cursor=db.query(
                DatabaseMaster.Comments.TABLE_NAME,
                projection,
                DatabaseMaster.Comments.COLUMN_NAME_GAME_NAME+" =?",
                new String[]{ game },
                null,
                null,
                sortBy
        );
        ArrayList comments=new ArrayList();

        while (cursor.moveToNext()){
            String comment=cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Comments.COLUMN_NAME_GAME_COMMENT));
            comments.add(comment);
        }
cursor.close();
        return  comments;
    }

    public float getAvg(String game){
        SQLiteDatabase db=getReadableDatabase();
        String [] projection={
                "AVG("+DatabaseMaster.Comments.COLUMN_NAME_GAME_RATING+ " ) as p"
        };
        String sortBy= DatabaseMaster.Comments.COLUMN_NAME_GAME_NAME+" DESC";

        Cursor cursor=db.query(
                DatabaseMaster.Comments.TABLE_NAME,
                projection,
                DatabaseMaster.Comments.COLUMN_NAME_GAME_NAME+" =?",
                new String[]{ game },
                null,
                null,
                sortBy
        );
       cursor.moveToFirst();
       float val=cursor.getFloat(cursor.getColumnIndexOrThrow("p"));
        cursor.close();
        return  val;
    }





}
