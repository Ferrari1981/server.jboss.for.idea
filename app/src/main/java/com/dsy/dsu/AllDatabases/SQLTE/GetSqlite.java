package com.dsy.dsu.AllDatabases.SQLTE;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;


import com.dsy.dsu.BusinessLogicAll.Class_Generation_Errors;

import javax.inject.Singleton;


@Singleton
public class GetSqlite {
    private Context context;


    public void методGetSqlite(@NonNull Context context) {
        try{
            GetSQLiteDatabase getSQLites=       new GetSQLiteDatabase(context);
                        // TODO: 17.04.2023
                        Log.d(this.getClass().getName(),"\n" + " class FaceAPp " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );
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

}