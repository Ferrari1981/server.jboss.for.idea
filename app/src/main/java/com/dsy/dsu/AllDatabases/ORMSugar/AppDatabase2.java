package com.dsy.dsu.AllDatabases.ORMSugar;


import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import org.joda.time.DateTimeConstants;

@Database(entities = {Task.class  }, version = 1)
public abstract class AppDatabase2 extends RoomDatabase {

public static  AppDatabase2 appDatabase2;

    public abstract TaskDao taskDao();








}












