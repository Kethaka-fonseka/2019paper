package com.example.pastpaper2019;

import android.provider.BaseColumns;

public class DatabaseMaster {

    public DatabaseMaster() {
    }

    public class Users implements BaseColumns{
public static  final String TABLE_NAME="users";
public static  final  String COLUMN_NAME_USERNAME="username";
public static  final String COLUMN_NAME_PASSWORD="password";
    }

    public class Games implements  BaseColumns{
        public static final String TABLE_NAME="games";
        public static  final String COLUMN_NAME_GAME_NAME="game_name";
        public static  final String COLUMN_NAME_GAME_YEAR="game_year";

    }
    public class Comments implements  BaseColumns{
        public static  final String TABLE_NAME="comments";
        public static  final String COLUMN_NAME_GAME_NAME="game_name";
        public static  final String COLUMN_NAME_GAME_RATING="game_rating";
        public static  final String COLUMN_NAME_GAME_COMMENT="game_comment";

    }


}
