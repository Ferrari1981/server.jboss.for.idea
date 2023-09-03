package com.dsy.dsu.AllDatabases.ROOM;


// TODO: 28.08.2023  Класс Бизнем ЛОгика Длябазы ROOM

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;

import java.util.List;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

@Singleton
public class CreateROOM {

    private static volatile    ROOMDatabase ROOM;
    private  Context context;

    public CreateROOM(@NonNull Context context) {

        this.context=context;
    }

    public void метоInizROOM() {
        try{
            if (ROOM == null) {
                synchronized (this) {
                    if (ROOM == null) {
                        ROOM = Room.databaseBuilder(context,
                                        ROOMDatabase.class, "ROOM.db")
                               .addMigrations(new ClassMigrations ().методMIGRATION_ModificationVersion)
                               .addMigrations(new ClassMigrations ().методMIGRATION_ModificationNewTrigger)
                                .setQueryExecutor(Executors.newSingleThreadExecutor())
                                .setTransactionExecutor(Executors.newSingleThreadExecutor())
                                //.fallbackToDestructiveMigration()
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

    }


    public  static     ROOMDatabase getRoom() {
        return ROOM;
    }
    // TODO: 28.08.2023  Class MIGRAZION
    class  ClassMigrations{
        private final Migration методMIGRATION_1_4 = new Migration(1,2) {
            @Override
            public void migrate(@NonNull SupportSQLiteDatabase database) {
                try{
                    // database.execSQL("ALTER TABLE Employee ADD COLUMN birthday INTEGER DEFAULT 0 NOT NULL");



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

        // TODO: 29.08.2023  START Migrazio ++ VERSION
        private final Migration методMIGRATION_ModificationVersion = new Migration(2,3) {
            @Override
            public void migrate(@NonNull SupportSQLiteDatabase database) {
                try{



                    database.execSQL("  INSERT INTO MODIFITATION_Client_ROOM  (name) VALUES( 'materials_databinary');");
                    // database.execSQL("ALTER TABLE Employee ADD COLUMN birthday INTEGER DEFAULT 0 NOT NULL");


                    //database.execSQL("ALTER TABLE Employee ADD COLUMN birthday INTEGER DEFAULT 0 NOT NULL");
                  /*  database.execSQL(" drop TRIGGER  if exists   materials_databinary ");
                    database.execSQL("    CREATE TRIGGER  IF NOT EXISTS  materials_databinary AFTER INSERT \n" +
                            "ON materials_databinary \n" +
                            "BEGIN\n" +
                            " INSERT INTO Modif (task, nameyask ,finish_by,finished)\n" +
                            "VALUES('1Bud Powell-','2Bud Powel--','3Bud Powell---',8888888);" +
                            "END; ");*/

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
// TODO: 29.08.2023  NEW TRIGGER

        private final Migration методMIGRATION_ModificationNewTrigger = new Migration(4,5) {
            @Override
            public void migrate(@NonNull SupportSQLiteDatabase database) {
                try{






                    //database.execSQL("ALTER TABLE Employee ADD COLUMN birthday INTEGER DEFAULT 0 NOT NULL");
                    database.execSQL(" drop TRIGGER  if exists   trigger_mat_binary_ints ");
                    database.execSQL("    CREATE TRIGGER  IF NOT EXISTS  trigger_mat_binary_ints AFTER INSERT  \n" +
                            "ON materials_databinary \n" +
                            "BEGIN\n" +
                            "  UPDATE MODIFITATION_Client_ROOM " +
                                    "  SET  localversionandroid_version=(SELECT MAX(current_table) " +
                                    "  FROM materials_databinary ),localversionandroid= datetime() WHERE name = trigger_mat_binary_ints ;"+
                            "END; ");
// TODO: 29.08.2023 UPDATE TRIGGER
                    database.execSQL(" drop TRIGGER  if exists   trigger_mat_binary_update ");
                    database.execSQL("    CREATE TRIGGER  IF NOT EXISTS  trigger_mat_binary_update AFTER UPDATE  \n" +
                            "ON materials_databinary \n" +
                            "BEGIN\n" +
                            "  UPDATE MODIFITATION_Client_ROOM " +
                            "  SET  localversionandroid_version=(SELECT MAX(current_table) " +
                            "  FROM materials_databinary ),localversionandroid= datetime() WHERE name = trigger_mat_binary_ints ;"+
                            "END; ");








             /*       database.execSQL("  INSERT INTO MODIFITATION_Client_ROOM  (name) VALUES( 'materials_databinary');");
                    // database.execSQL("ALTER TABLE Employee ADD COLUMN birthday INTEGER DEFAULT 0 NOT NULL");*/

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



//TODO END class  ClassSetGetROOM
}//TODO END class  ClassSetGetROOM
