package com.dsy.dsu.AllDatabases.ORMSugar;


import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Task.class,Modif.class}, version = 7,exportSchema = true)
public abstract class AppDatabase extends RoomDatabase {
    public AppDatabase() {

        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );
    }


    /*    public abstract DAO4 taskDao();*/
    public abstract TaskDao3 taskDao3();
    public abstract Dao1 taskdao1();


}












