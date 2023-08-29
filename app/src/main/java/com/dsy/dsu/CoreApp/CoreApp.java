package com.dsy.dsu.CoreApp;


import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.dsy.dsu.AllDatabases.ROOM.CreateROOM;
import com.dsy.dsu.AllDatabases.ROOM.ROOMDatabase;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;

import java.util.List;
import java.util.concurrent.Executors;

import javax.inject.Singleton;


@Singleton
public class CoreApp extends Application {
    private static ROOMDatabase ROOM;
    @Override
    public void onCreate() {
        super.onCreate();
        try{
            // TODO: 29.08.2023  CREATE ROOM
        ROOM=   new ROOMCreiting(getApplicationContext()).метоInizROOM();
        // TODO: 17.04.2023
        Log.d(this.getClass().getName(),"\n" + " class FaceAPp " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  + " ROOM "+ROOM);
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



    public  static   ROOMDatabase getRoom() {
        return ROOM;
    }
}




// TODO: 29.08.2023  КЛАСС   БИЗНЕС ЛОГИКУ

class ROOMCreiting{


    private   ROOMDatabase ROOM;
    private Context context;

    public ROOMCreiting(@NonNull Context context) {
        this.context=context;
    }

    public ROOMDatabase метоInizROOM() {
        try{
            if (ROOM == null) {
                synchronized (this) {
                    if (ROOM == null) {
                        ROOM = Room.databaseBuilder(context,
                                        ROOMDatabase.class, "ROOM44.db")
                                .addMigrations(new  ClassMigrations().методMIGRATION_1_4)
                                .setQueryExecutor(Executors.newSingleThreadExecutor())
                                .setTransactionExecutor(Executors.newSingleThreadExecutor())
                                .allowMainThreadQueries()
                                .setQueryCallback(new RoomDatabase.QueryCallback() {
                                    @Override
                                    public void onQuery(@NonNull String sqlQuery, @NonNull List<Object> bindArgs) {
                                        Log.d(this.getClass().getName(),"\n" + " class FaceAPp " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+" ROOM.getQueryExecutor() " +ROOM.getQueryExecutor());
                                    }
                                },Executors.newSingleThreadExecutor())
                                .addCallback(new RoomDatabase.Callback() {
                                    @Override
                                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                        super.onCreate(db);
                                        Log.d(this.getClass().getName(),"\n" + " class FaceAPp " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+" ROOM.getQueryExecutor() " +ROOM.getQueryExecutor());
                                    }

                                    @Override
                                    public void onOpen(@NonNull SupportSQLiteDatabase db) {
                                        super.onOpen(db);
                                        Log.d(this.getClass().getName(),"\n" + " class FaceAPp " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+" ROOM.getQueryExecutor() " +ROOM.getQueryExecutor());

                                    }

                                    @Override
                                    public void onDestructiveMigration(@NonNull SupportSQLiteDatabase db) {
                                        super.onDestructiveMigration(db);
                                        Log.d(this.getClass().getName(),"\n" + " class FaceAPp " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+" ROOM.getQueryExecutor() " +ROOM.getQueryExecutor());

                                    }
                                })
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
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                    Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  ROOM;
    }


    // TODO: 28.08.2023  Class MIGRAZION
    class  ClassMigrations{
        private final Migration методMIGRATION_1_4 = new Migration(6,7) {
            @Override
            public void migrate(@NonNull SupportSQLiteDatabase database) {
                try{
                    // database.execSQL("ALTER TABLE Employee ADD COLUMN birthday INTEGER DEFAULT 0 NOT NULL");


                    //database.execSQL("ALTER TABLE Employee ADD COLUMN birthday INTEGER DEFAULT 0 NOT NULL");
                    database.execSQL(" drop TRIGGER  if exists   materials_databinary ");
                    database.execSQL("    CREATE TRIGGER  IF NOT EXISTS  materials_databinary AFTER INSERT \n" +
                            "ON EntityMaterialBinary \n" +
                            "BEGIN\n" +
                            " INSERT INTO Modif (task, nameyask ,finish_by,finished)\n" +
                            "VALUES('1Bud Powell-','2Bud Powel--','3Bud Powell---',8888888);" +
                            "END; ");

                    //database.execSQL("ALTER TABLE Employee ADD COLUMN birthday INTEGER DEFAULT 0 NOT NULL");
                    /*    database.execSQL("CREATE TRIGGER audit_log AFTER INSERT \n" +
                                "ON EntityMaterialBinary \n" +
                                "BEGIN\n" +
                                " INSERT INTO Modif (task, nameyask ,finish_by,finished)\n" +
                                "VALUES('2Bud Powell','2Bud Powell','2Bud Powell',9451);" +
                                "END; ");
*/
                    // TODO: 17.04.2023
                    Log.d(this.getClass().getName(),"\n" + " class FaceAPp " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }
        };
//TODO end  class  ClassMigrations
    }//TODO end  class  ClassMigrations






}




