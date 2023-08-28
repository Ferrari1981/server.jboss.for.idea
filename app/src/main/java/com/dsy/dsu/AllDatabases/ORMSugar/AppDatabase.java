package com.dsy.dsu.AllDatabases.ORMSugar;


import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Task.class,Modif.class}, version = 7)
public abstract class AppDatabase extends RoomDatabase {
    public AppDatabase() {

        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );
    }


    /*    public abstract DAO4 taskDao();*/
    public abstract TaskDao3 taskDao3();
    public abstract Dao1 taskdao1();


    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            try{
                



            //database.execSQL("ALTER TABLE Employee ADD COLUMN birthday INTEGER DEFAULT 0 NOT NULL");
      /*      database.execSQL("CREATE TRIGGER audit_log AFTER INSERT \n" +
                    "ON Task \n" +
                    "BEGIN\n" +
                    "   UPDATE leads\n" +
                    "SET \n" +
                    "   phone = '4089998888',\n" +
                    "   email = 'john.smith@sqlitetutorial.net'\n" +
                    "WHERE\n" +
                    "   id = 1;" +
                    "END; ");*/


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

    public static final Migration MIGRATION_2_3 = new Migration(8, 9) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            try{
               // database.execSQL("ALTER TABLE Employee ADD COLUMN birthday INTEGER DEFAULT 0 NOT NULL");


                //database.execSQL("ALTER TABLE Employee ADD COLUMN birthday INTEGER DEFAULT 0 NOT NULL");
            database.execSQL("CREATE TRIGGER audit_log AFTER INSERT \n" +
                    "ON Task \n" +
                    "BEGIN\n" +
                    " INSERT INTO Modif (task, nameyask ,finish_by,finished)\n" +
                    "VALUES('2Bud Powell','2Bud Powell','2Bud Powell',9451);" +
                    "END; ");


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
}












