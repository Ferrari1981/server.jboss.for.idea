package com.dsy.dsu.AllDatabases.ORMSugar;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;

import javax.inject.Singleton;


@Singleton
public  class RoomCreate {
  public  static   AppDatabase ROOM;

    public  AppDatabase GetROOM(@NonNull Context context) {
        if (ROOM == null) {
            synchronized (this) {
                if (ROOM == null) {
                    ROOM = Room.databaseBuilder(context,
                                    AppDatabase.class, "ROOOOOMMMM18.db")
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build();
                }


            }
        }
       return ROOM;
    }
}
