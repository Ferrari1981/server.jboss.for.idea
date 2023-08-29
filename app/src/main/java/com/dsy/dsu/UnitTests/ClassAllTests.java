package com.dsy.dsu.UnitTests;


// TODO: 29.08.2023  класс для любого тестирования

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.sqlite.db.SimpleSQLiteQuery;

import com.dsy.dsu.AllDatabases.ROOM.EntityMaterialBinary;
import com.dsy.dsu.AllDatabases.ROOM.ROOMDatabase;
import com.dsy.dsu.AllDatabases.ROOM.Task;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.CoreApp.CoreApp;

import java.util.Date;
import java.util.List;

import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ClassAllTests {
    private  Context context;
    public ClassAllTests(@NonNull Context context) {
        this.context=context;
        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );
    }
    // TODO: 29.08.2023  класс для любого тестирования





    public class  ClassTestROOM{

        public void методТестRoom(){

            try{


// TODO: 28.08.2023 ПОЛУЧЕАМ КОМПОНЕТ ROOM
              ROOMDatabase GetROOM = CoreApp.getRoom();
                   //adding to database
            SimpleSQLiteQuery query = new SimpleSQLiteQuery("SELECT * FROM Task WHERE id=?",
                    new Object[]{1});

            SimpleSQLiteQuery query2 = new SimpleSQLiteQuery("  drop TRIGGER  if exists UPDATES  EntityMaterialBinary     ");
            SimpleSQLiteQuery query3 = new SimpleSQLiteQuery("  CREATE TRIGGER IF NOT EXISTS UPDATES EntityMaterialBinary   AFTER UPDATE   ON    BEGIN " +
                    " UPDATE EntityMaterialBinary  SET  task='xyu' WHERE id='1'      END ;  ");

       GetROOM.daoROOM().getRawMatbin(query).subscribeOn(Schedulers.single()).blockingSubscribe(new MaybeObserver<List<Task>>() {
           @Override
           public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
               Log.d(this.getClass().getName(), "\n" + " class "
                       + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                       " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                       " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
           }

           @Override
           public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull List<Task> tasks) {
               Log.d(this.getClass().getName(), "\n" + " class "
                       + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                       " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                       " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
           }

           @Override
           public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
               Log.d(this.getClass().getName(), "\n" + " class "
                       + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                       " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                       " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
           }

           @Override
           public void onComplete() {
               Log.d(this.getClass().getName(), "\n" + " class "
                       + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                       " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                       " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
           }
       });
            Log.d(this.getClass().getName(), "\n" + " class "
                    + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");




/*

            EntityMaterialBinary entityMaterialBinary2 =   new EntityMaterialBinary();
            entityMaterialBinary2.setTask("roomПавел01 " + new Date().toLocaleString());
            entityMaterialBinary2.setDesc("roomИванович02" + new Date().toLocaleString());
            entityMaterialBinary2.setFinishBy("roomМорару03" + new Date().toLocaleString());
            entityMaterialBinary2.setFinished(true);
            //adding to database


            GetROOM.getQueryExecutor().execute(new Runnable() {
                @Override
                public void run() {
                    GetROOM.runInTransaction(new Runnable() {
                        @Override
                        public void run() {
                            GetROOM.taskdao1().insert(entityMaterialBinary2);
                            Log.d(this.getClass().getName(), "\n" + " class "
                                    + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                        }
                    });
                }
            });
*/



            //  GetROOM.taskdao1().insert(entityMaterialBinary2);
            Log.d(this.getClass().getName(), "\n" + " class "
                    + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");


      /*      Maybe.fromAction(new Action() {
                @Override
                public void run() throws Throwable {
                    GetROOM.taskdao1().insert(entityMaterialBinary2);
                    Log.d(this.getClass().getName(), "\n" + " class "
                            + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                }
            }).subscribeOn(Schedulers.single()).blockingSubscribe(new MaybeObserver<Object>() {
                @Override
                public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
                    Log.d(this.getClass().getName(), "\n" + " class "
                            + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

                }

                @Override
                public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull Object o) {
                    Log.d(this.getClass().getName(), "\n" + " class "
                            + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

                }

                @Override
                public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                    Log.d(this.getClass().getName(), "\n" + " class "
                            + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

                }

                @Override
                public void onComplete() {
                    Log.d(this.getClass().getName(), "\n" + " class "
                            + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

                }
            });
*/





            //    GetROOM.close();

            Log.d(this.getClass().getName(), "\n" + " class "
                    + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");


        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

        }


    }



//TODO end public class ClassAllTests //TODO end public class ClassAllTests
}//TODO end public class ClassAllTests //TODO end public class ClassAllTests
