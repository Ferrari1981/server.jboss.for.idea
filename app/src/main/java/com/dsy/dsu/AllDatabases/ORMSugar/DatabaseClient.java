package com.dsy.dsu.AllDatabases.ORMSugar;

import android.content.Context;

import androidx.room.Room;

import java.io.File;

public class DatabaseClient {

    private Context mCtx;
    private static DatabaseClient mInstance;

    //our app database object
    private AppDatabase appDatabase;

    private DatabaseClient(Context mCtx) {
        this.mCtx = mCtx;

        //creating the app database with Room database builder
        //MyToDos is the name of the database
        appDatabase =
                Room.databaseBuilder(mCtx, AppDatabase.class, "ROOM#3.db")
                        .createFromFile(new File("data/data/com.dsy.dsu/databases/ROOM#3.db"))
                        .allowMainThreadQueries()
                        .build();
    }

    public static synchronized DatabaseClient getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new DatabaseClient(mCtx);
        }
        return mInstance;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}