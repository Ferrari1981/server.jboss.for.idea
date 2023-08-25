package com.dsy.dsu.AllDatabases.ORMSugar;


import android.content.Context;

import androidx.room.Room;

public class roomCreate {
     private static RoomInisaziy INSTANCE;
     public static RoomInisaziy getDatabase(final Context context) {
         if (INSTANCE == null) {
             synchronized (RoomInisaziy.class) {
                 if (INSTANCE == null) {
                     INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                     RoomInisaziy.class, "appD444B")
                             .createFromAsset("database/appD444B.db")
                             .build();
                 }
             }
         }
         return INSTANCE;
     }


 }

