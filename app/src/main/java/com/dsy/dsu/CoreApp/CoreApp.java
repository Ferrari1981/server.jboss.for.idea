package com.dsy.dsu.CoreApp;


import android.app.Application;
import android.util.Log;

import androidx.room.Room;

import com.dsy.dsu.AllDatabases.ORMSugar.AppDatabase;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;

import javax.inject.Singleton;


@Singleton
public class CoreApp extends Application {
    private static AppDatabase ROOM;

    @Override
    public void onCreate() {
        super.onCreate();
        new ClassSetGetROOM().метоInizROOM();
        // TODO: 17.04.2023
        Log.d(this.getClass().getName(),"\n" + " class FaceAPp " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
    }

    public  static AppDatabase getRoom() {
        return ROOM;
    }












    // TODO: 28.08.2023  Класс Бизнем ЛОгика Длябазы ROOM

    @Singleton
   private final class  ClassSetGetROOM{
        private void метоInizROOM() {
            try{
            if (ROOM == null) {
                synchronized (this) {
                    if (ROOM == null) {
                        ROOM = Room.databaseBuilder(getApplicationContext(),
                                        AppDatabase.class, "ROOOOOMMMM199.db")
                                .addMigrations(AppDatabase.MIGRATION_1_2)
                                .addMigrations(AppDatabase.MIGRATION_1_3)
                                .build();
                        // TODO: 17.04.2023
                        Log.d(this.getClass().getName(),"\n" + " class FaceAPp " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+" ROOM.getQueryExecutor() " +ROOM.getQueryExecutor());
                    }

                }
            }
                // TODO: 17.04.2023
                Log.d(this.getClass().getName(),"\n" + " class FaceAPp " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+" ROOM  " +ROOM );
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        }

    }
}


