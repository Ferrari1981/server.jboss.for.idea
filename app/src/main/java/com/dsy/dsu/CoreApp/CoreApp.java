package com.dsy.dsu.CoreApp;


import android.app.Application;
import android.util.Log;


import com.dsy.dsu.AllDatabases.ROOM.CreateROOM;
import com.dsy.dsu.AllDatabases.SQLTE.GetSqlite;
import com.dsy.dsu.BusinessLogicAll.Class_Generation_Errors;

import javax.inject.Singleton;


@Singleton
public class CoreApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        try{
            // TODO: 02.09.2023  CREATE get SQLITE
            new GetSqlite().методGetSqlite(getApplicationContext());

            // TODO: 29.08.2023  CREATE ROOM
       new CreateROOM(getApplicationContext()).метоInizROOM();
        // TODO: 17.04.2023
        Log.d(this.getClass().getName(),"\n" + " class FaceAPp " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }



}




// TODO: 29.08.2023  КЛАСС   БИЗНЕС ЛОГИКУ




