package com.dsy.dsu.AllDatabases.ROOM;


import android.util.Log;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {EntityMaterialBinary.class, Entitymodifversions.class }, version = 1,exportSchema = true)
public abstract class ROOMDatabase extends RoomDatabase {
    public ROOMDatabase() {

        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );
    }

    public abstract DaoRoom daoROOM();



}












