package com.dsy.dsu.AllDatabases.JsonDeserializer;

import android.content.Context;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.fasterxml.jackson.databind.JsonNode;

import org.checkerframework.checker.units.qual.C;

import java.util.Map;

public class BunessLogicAllInsertAllUpdateBinds {

    private  Context context;

    public BunessLogicAllInsertAllUpdateBinds(@NonNull Context context) {
        this.context=context;
    }

    // TODO: 24.08.2023  метод  UPDATE
     void методЗаполненияBindingUpdate(@NonNull JsonNode jsonNodeParentMAP, @NonNull SQLiteStatement sqLiteStatementUpdate) {
        final Integer[] Индексbindis = {1};
        try{
            // sqLiteStatementInsert.bindLong(1, jsonNodeParentMAP.get("id").intValue());//"id""
            jsonNodeParentMAP.fields().forEachRemaining(new java.util.function.Consumer<Map.Entry<String, JsonNode>>() {
                @Override
                public void accept(Map.Entry<String, JsonNode> stringJsonNodeEntry) {
                    try{
                        if(!stringJsonNodeEntry.getKey().equalsIgnoreCase("id")){

                            if (stringJsonNodeEntry.getValue().isNull()) {
// TODO: 24.08.2023 is null
                                sqLiteStatementUpdate.bindNull(Индексbindis[0]);
                                Log.d(this.getClass().getName(), "\n" + " class " +
                                        Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                        + sqLiteStatementUpdate  + "sqLiteStatementUpdate");
                            }else {
                                if (stringJsonNodeEntry.getValue().isTextual()) {
                                    sqLiteStatementUpdate.bindString(Индексbindis[0], stringJsonNodeEntry.getValue().asText().trim());
                                }

                                if (stringJsonNodeEntry.getValue().isLong()) {
                                    sqLiteStatementUpdate.bindLong(Индексbindis[0], stringJsonNodeEntry.getValue().longValue());
                                }
                                if (stringJsonNodeEntry.getValue().isInt()) {
                                    sqLiteStatementUpdate.bindLong(Индексbindis[0], stringJsonNodeEntry.getValue().intValue());
                                }
                                if (stringJsonNodeEntry.getValue().isBigInteger()) {
                                    sqLiteStatementUpdate.bindLong(Индексbindis[0], stringJsonNodeEntry.getValue().bigIntegerValue().longValue());
                                }

                                if (stringJsonNodeEntry.getValue().isBinary()) {
                                    sqLiteStatementUpdate.bindBlob(Индексbindis[0], stringJsonNodeEntry.getValue().binaryValue());
                                }

                            }
                            // TODO: 24.08.2023  end loop
                            Индексbindis[0]++;
                            Log.d(this.getClass().getName(), "\n" + " class " +
                                    Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                    + sqLiteStatementUpdate  + "sqLiteStatementUpdate"  + " Индексbindis " + Индексbindis[0]);
                            //TODO end  public void accept(Map.Entry<String, JsonNode> stringJsonNodeEntry)
                        }//TODO end  public void accept(Map.Entry<String, JsonNode> stringJsonNodeEntry)
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                }

            });
            // TODO: 05.07.2023  Для ДОПОЛНИТЕЛЬНОЕ ЗАПОЛДЕНИЕ UUID ПОЛЕ ДЛЯ СОСТЫКОВКИ ТОЛЬКО ДЛЯ ОБНОВЛЕНИЕ
            sqLiteStatementUpdate.bindLong(Индексbindis[0],jsonNodeParentMAP.get("uuid").longValue());

            Log.d(this.getClass().getName(), "\n" + " class " +
                    Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + sqLiteStatementUpdate  + "sqLiteStatementUpdate");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        //TODO end   // TODO: 24.08.2023  метод  UPDATE
    }//TODO end   // TODO: 24.08.2023  метод  UPDATE









    // TODO: 24.08.2023  метод  INSERT
     void методЗаполненияBindingInsert(@NonNull JsonNode jsonNodeParentMAP, @NonNull SQLiteStatement sqLiteStatementInsert) {
        final Integer[] Индексbindis = {1};
        try{
            // sqLiteStatementInsert.bindLong(1, jsonNodeParentMAP.get("id").intValue());//"id""
            jsonNodeParentMAP.fields().forEachRemaining(new java.util.function.Consumer<Map.Entry<String, JsonNode>>() {
                @Override
                public void accept(Map.Entry<String, JsonNode> stringJsonNodeEntry) {
                    try{
                        if (stringJsonNodeEntry.getValue().isNull()) {
                            // TODO: 24.08.2023 is null
                            sqLiteStatementInsert.bindNull(Индексbindis[0]);
                            Log.d(this.getClass().getName(), "\n" + " class " +
                                    Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                    + sqLiteStatementInsert  + "sqLiteStatementInsert");
                        }else {
                            if (stringJsonNodeEntry.getValue().isTextual()) {
                                sqLiteStatementInsert.bindString(Индексbindis[0], stringJsonNodeEntry.getValue().asText().trim());
                            }

                            if (stringJsonNodeEntry.getValue().isLong()) {
                                sqLiteStatementInsert.bindLong(Индексbindis[0], stringJsonNodeEntry.getValue().longValue());
                            }
                            if (stringJsonNodeEntry.getValue().isInt()) {
                                sqLiteStatementInsert.bindLong(Индексbindis[0], stringJsonNodeEntry.getValue().intValue());
                            }
                            if (stringJsonNodeEntry.getValue().isBigInteger()) {
                                sqLiteStatementInsert.bindLong(Индексbindis[0], stringJsonNodeEntry.getValue().bigIntegerValue().longValue());
                            }

                            if (stringJsonNodeEntry.getValue().isBinary()) {
                                sqLiteStatementInsert.bindBlob(Индексbindis[0], stringJsonNodeEntry.getValue().binaryValue());
                            }

                        }
                        // TODO: 24.08.2023  end loop
                        Индексbindis[0]++;
                        Log.d(this.getClass().getName(), "\n" + " class " +
                                Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                + sqLiteStatementInsert  + "sqLiteStatementInsert"  + " Индексbindis " + Индексbindis[0]);
                        //TODO end  public void accept(Map.Entry<String, JsonNode> stringJsonNodeEntry)
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                }

            });
            Log.d(this.getClass().getName(), "\n" + " class " +
                    Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + sqLiteStatementInsert  + "sqLiteStatementInsert");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        //TODO end   // TODO: 24.08.2023  метод  INSERT
    }//TODO end   // TODO: 24.08.2023  метод  INSERT



}
