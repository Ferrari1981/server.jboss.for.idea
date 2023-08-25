package com.dsy.dsu.AllDatabases.ORMSugar;


import android.util.Log;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Task.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public AppDatabase() {
        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );
    }

    public abstract TaskDao taskDao();
    public abstract TaskDao3 taskDao3();
}












