package com.dsy.dsu.AllDatabases.ORMSugar;


import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Task.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public AppDatabase() {

        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );
    }
    public abstract TaskDao taskDao();


    public void  Room(Context context){

        AppDatabase appDatabase=       Room.databaseBuilder( context,
                        AppDatabase.class, "NewRoom8.db")
                .allowMainThreadQueries()
                .build();


    }

}












