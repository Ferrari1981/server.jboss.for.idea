package com.dsy.dsu.CoreApp;


import android.app.Application;
import android.util.Log;

import com.dsy.dsu.AllDatabases.ROOM.CreateROOM;
import com.dsy.dsu.AllDatabases.ROOM.ROOMDatabase;

import javax.inject.Singleton;


@Singleton
public class CoreApp extends Application {
    private static ROOMDatabase ROOM;
    @Override
    public void onCreate() {
        super.onCreate();
        ROOM=   new CreateROOM(getApplicationContext()).метоInizROOM();
        // TODO: 17.04.2023
        Log.d(this.getClass().getName(),"\n" + " class FaceAPp " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
    }



    public  static   ROOMDatabase getRoom() {
        return ROOM;
    }
}


