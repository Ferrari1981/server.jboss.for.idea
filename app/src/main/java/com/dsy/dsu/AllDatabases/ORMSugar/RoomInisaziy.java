package com.dsy.dsu.AllDatabases.ORMSugar;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Task.class  }, version = 1)
public abstract class RoomInisaziy extends RoomDatabase {
    public abstract TaskDao taskDao();
}












