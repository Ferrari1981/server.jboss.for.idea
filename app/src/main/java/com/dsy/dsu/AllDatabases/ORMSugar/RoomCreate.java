package com.dsy.dsu.AllDatabases.ORMSugar;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;

public class RoomCreate {
  public  static   AppDatabase appDatabas;

    public  AppDatabase RoomCreate(@NonNull Context context) {
        if (appDatabas == null) {
            synchronized (this) {
                if (appDatabas == null) {
                    appDatabas = Room.databaseBuilder(context,
                                    AppDatabase.class, "ROOOOOMMMM18.db")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
       return appDatabas;
    }
}
