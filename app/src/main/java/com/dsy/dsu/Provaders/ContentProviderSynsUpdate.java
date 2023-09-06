package com.dsy.dsu.Provaders;

import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.content.SharedPreferences;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import com.dsy.dsu.AllDatabases.SQLTE.GetSQLiteDatabase;
import com.dsy.dsu.BusinessLogicAll.Class_Generation_Errors;
import com.dsy.dsu.BusinessLogicAll.PUBLIC_CONTENT;
import com.dsy.dsu.BusinessLogicAll.SubClassCreatingMainAllTables;
import com.dsy.dsu.BusinessLogicAll.SubClassUpVersionDATA;
import com.fasterxml.jackson.databind.JsonNode;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Stream;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ContentProviderSynsUpdate extends ContentProvider {
  private   UriMatcher uriMatcherДЛяПровайдераКонтентБазаДанных;
    private SQLiteDatabase sqLiteDatabase ;
    private  PUBLIC_CONTENT public_contentМенеджерПотоковМассвойОперацииВставки;
    private AsyncTaskLoader<?> asyncTaskLoader;
    private Handler handler;
    private Integer ТекущаяСтрокаПриДОбавлениииURL=0;
    private SharedPreferences preferences;
    public ContentProviderSynsUpdate() throws InterruptedException {
        try{
            sqLiteDatabase=    GetSQLiteDatabase.SqliteDatabase();
        CopyOnWriteArrayList<String> ИменаТаблицыОтАндройда=
                new SubClassCreatingMainAllTables(getContext()).
                        методCreatingMainTabels(getContext());
        Log.d(this.getClass().getName(), " ИменаТаблицыОтАндройда "+ИменаТаблицыОтАндройда );
     uriMatcherДЛяПровайдераКонтентБазаДанных=new UriMatcher(ИменаТаблицыОтАндройда.size());
            ИменаТаблицыОтАндройда.forEach(new Stream.Builder() {
         @Override
         public void accept(Object ЭлементТаблица) {
             uriMatcherДЛяПровайдераКонтентБазаДанных.addURI("com.dsy.dsu.providerdatabasemirror",ЭлементТаблица.toString(),ТекущаяСтрокаПриДОбавлениииURL);
             Log.d(this.getClass().getName(), " ЭлементТаблица "+ЭлементТаблица + " ТекущаяСтрокаПриДОбавлениииURL " +ТекущаяСтрокаПриДОбавлениииURL);
             ТекущаяСтрокаПриДОбавлениииURL++;
         }
         @Override
         public Stream.Builder add(Object o) {
             return Stream.Builder.super.add(o);
         }

         @Override
         public Stream build() {
             return null;
         }
     });
        Log.d(this.getClass().getName(),  " uriMatcherДЛяПровайдераКонтентБазаДанных" +uriMatcherДЛяПровайдераКонтентБазаДанных );


        handler=          new Handler(Looper.getMainLooper(),new Handler.Callback(){
            @Override
            public boolean handleMessage(@NonNull android.os.Message  msg) {
                try{
                    Log.d(this.getClass().getName(), " msg  "+msg);
                    Bundle bundle=        msg.getData();
                    Log.d(this.getClass().getName(), " bundle  "+bundle);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.err.println("  Ошибка в самом классе записи ошибок нет КОНТЕКСТА Class_Generation_Errors");
                    ///метод запись ошибок в таблицу
                    Log.e(getContext().getClass().getName(),
                            "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                return true;
            }
        });
        // TODO: 04.10.2022
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }




    @Override
    public boolean onCreate() {
        try {
            sqLiteDatabase=    GetSQLiteDatabase.SqliteDatabase();
            Log.w(this.getClass().getName(), "sqLiteDatabase " + sqLiteDatabase + " getContext()) " +getContext());
            preferences =getContext(). getSharedPreferences("sharedPreferencesХранилище", Context.MODE_MULTI_PROCESS);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        return  sqLiteDatabase.isOpen();
    }



    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        Integer РезультатУдалениеСтатуса=0;
        try{
        Log.d(this.getClass().getName(), " uri"+uri );
            // TODO: 14.10.2022 метод определения текущней таблицы
            String table = МетодОпределяемТаблицу(uri);
            if (table!=null) {
                Integer РезультатУдаления  =  sqLiteDatabase.delete(table, selection, selectionArgs);
                // TODO: 30.10.2021
                Log.w(getContext().getClass().getName(), " РезультатУдаления  " + РезультатУдаления);/////
                Uri ОтветВставкиДанных  = Uri.parse("content://"+РезультатУдаления.toString());
               String ответОперцииВставки=    Optional.ofNullable(ОтветВставкиДанных).map(Emmeter->Emmeter.toString().replace("content://","")).get();
                РезультатУдалениеСтатуса= Integer.parseInt(ответОперцииВставки);
                if (РезультатУдаления> 0) {
                    getContext().getContentResolver().notifyChange(uri, null);
                }
            }else {
                Log.w(getContext().getClass().getName(), " table  " + table);/////
            }

    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
      return РезультатУдалениеСтатуса;
    }

    @NonNull
    private String МетодОпределяемТаблицу(Uri uri) {
        String table = new String();
        try{
        Log.d(this.getClass().getName(), " uri"+ uri);
            table=    Optional.ofNullable(uri).map(Emmeter->Emmeter.toString().replace("content://com.dsy.dsu.providerdatabasemirror/","")).get();
            Log.w(getContext().getClass().getName(),
                    " defaluit table  " + table  + " uri " + uri);/////
            Log.d(this.getClass().getName(), " table"+ table);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        return table;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        Uri  ОтветВставкиДанных = null;
        try {
            Log.d(this.getClass().getName(), " uri"+uri );
            // TODO: 14.10.2022 метод определения текущней таблицы
            String table = МетодОпределяемТаблицу(uri);
            Long   РезультатВставкиДанных  =  sqLiteDatabase.insertOrThrow(table, null, values);
            // TODO: 30.10.2021
            Log.w(getContext().getClass().getName(), " РезультатВставкиДанных  " + РезультатВставкиДанных);/////

            ОтветВставкиДанных  = Uri.parse("content://"+РезультатВставкиДанных.toString());
            if (РезультатВставкиДанных> 0) {

                getContext().getContentResolver().notifyChange(uri, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return ОтветВставкиДанных;
    }






    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable Bundle queryArgs, @Nullable CancellationSignal cancellationSignal) {
        Cursor cursor=null;
       try{
           asyncTaskLoader=new AsyncTaskLoader<Object>(getContext()) {
               @Nullable
               @Override
               public Object loadInBackground() {
                   Cursor cursor=null;
        //   Create_Database_СамаБАзаSQLite=new CREATE_DATABASE(getContext()).getССылкаНаСозданнуюБазуORM();
        Integer  ПубличныйIDДляФрагмента=queryArgs.getInt("ПубличныйIDДляФрагмента",0);
        Integer  ТекущаяЦФО=queryArgs.getInt("ТекущаяЦФО",0);
        Integer  ТекущаяНомерМатериала=queryArgs.getInt("ТекущаяНомерМатериала",0);
        String  Таблица=queryArgs.getString("Таблица","");
        String  ФлагКакиеДанныеНужныПолучениеМатериалов=queryArgs.getString("ФлагКакиеДанныеНужныПолучениеМатериалов","");
           SQLiteQueryBuilder          SQLBuilder_Для_GRUD_Операций =new SQLiteQueryBuilder();
           SQLBuilder_Для_GRUD_Операций.setTables(Таблица);
           // TODO: 07.11.2022  данные курсор для групировки
           switch (ФлагКакиеДанныеНужныПолучениеМатериалов.trim()) {
               case "ПолучениеНомерМатериала":
               cursor=     SQLBuilder_Для_GRUD_Операций.query(sqLiteDatabase,new String[]{"*"},
                       null,null
                       ,"nomenvesov_zifra, nomenvesov, moneys, kolichstvo, cfo", "cfo="+ТекущаяЦФО, null,null);
               break;
               case "ПолучениеСгрупированныеСамиДанные":
                   cursor=     SQLBuilder_Для_GRUD_Операций.query(sqLiteDatabase,new String[]{"*"},
                           null,null
                           ,"nomenvesov_zifra, nomenvesov, moneys, kolichstvo, cfo", "cfo="+ТекущаяЦФО+" AND nomenvesov_zifra="+ТекущаяНомерМатериала , null,null);
                   break;
           }

           Log.w(getContext().getClass().getName(), " Полученый для Получение Материалов cursor  " + cursor);/////
           commitContentChanged();
                   return cursor;
               }
           };
           asyncTaskLoader.startLoading();
           cursor= (Cursor) asyncTaskLoader.loadInBackground();
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        return cursor;
    }







    @NonNull
    @Override
    public ContentProviderResult[] applyBatch(@NonNull ArrayList<ContentProviderOperation> operations) throws OperationApplicationException {
        Log.w(getContext().getClass().getName(), " Полученый для Получение Материалов cursor  " );/////
        return super.applyBatch(operations);
    }

    @NonNull
    @Override
    public ContentProviderResult[] applyBatch(@NonNull String authority, @NonNull ArrayList<ContentProviderOperation> operations)
            throws OperationApplicationException {
        Log.w(getContext().getClass().getName(), " Полученый для Получение Материалов cursor  " );/////
        return super.applyBatch(authority, operations);
    }


    @Nullable
    @Override
    public Bundle call(@NonNull String method, @Nullable String БуферПолученныйJSON, @Nullable Bundle extras) {
        Bundle bundleОперацииUpdateOrinsert = null;
        try{
            CopyOnWriteArrayList<ContentValues> contentValuesCopyOnWriteArrayLis= (CopyOnWriteArrayList<ContentValues> ) extras.getSerializable("getjson");

            SubClassJsonTwoParserOtServer subClassJsonParserOtServer=new SubClassJsonTwoParserOtServer(contentValuesCopyOnWriteArrayLis,method);

        bundleОперацииUpdateOrinsert=     subClassJsonParserOtServer.методUpdateПарсингаJson();

        Log.d(this.getClass().getName(),"\n" + " class " +
                Thread.currentThread().getStackTrace()[2].getClassName()
                + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                " contentValuesCopyOnWriteArrayLis " +contentValuesCopyOnWriteArrayLis + " bundleОперацииUpdateOrinsert "+bundleОперацииUpdateOrinsert);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        return bundleОперацииUpdateOrinsert;
    }

    @Override
    public void shutdown() {
        super.shutdown();
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
     //   Create_Database_СамаБАзаSQLite=new CREATE_DATABASE(getContext()).getССылкаНаСозданнуюБазуORM();
        Log.w(this.getClass().getName(), "  КОНТЕНТ ПРОВАЙДЕР update  uri " +uri + " getContext()) " +getContext());
        return Integer.parseInt("1");
    }

    // TODO: 22.11.2022  UPDATE
// TODO: 22.11.2022  UPDATE
    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {
        ArrayList<Integer> РезультатОперацииBurkUPDATE = new ArrayList<>();
        try {
            LinkedBlockingQueue<ContentValues>      ДанныеДляОперацииUpdates=new LinkedBlockingQueue<ContentValues>(Arrays.asList(values));
            String     table = МетодОпределяемТаблицу(uri);
            String ФлагКакойСинхронизацияПерваяИлиНет=         preferences.getString("РежимЗапускаСинхронизации", "");
            if (ФлагКакойСинхронизацияПерваяИлиНет.equalsIgnoreCase("ПовторныйЗапускСинхронизации") ||
                    table.equalsIgnoreCase("settings_tabels") ||  table.equalsIgnoreCase("view_onesignal") ) {
                Log.w(this.getClass().getName(), "count  bulkInsert  values.length "
                        + values.length+"\n"+"bulkPOTOK "+Thread.currentThread().getName()+"\n"+
                        " FUTURE FUTURE SIZE  EntityMaterialBinary "+"\n"+
                        "  isParallel isParallel isParallel" );
                // TODO: 01.12.2022 тест код UPDATE
                // TODO: 08.12.2022 UPDATE
                Flowable.fromArray(values)
                        .onBackpressureBuffer()
                        .filter(filter->filter.size()>0)
                        .doOnError(new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Throwable {
                                Log.e(this.getClass().getName(), "Ошибка " + throwable + " Метод :" +
                                        Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new Class_Generation_Errors(getContext()).
                                        МетодЗаписиВЖурналНовойОшибки(throwable.toString(), this.getClass().getName(),
                                                Thread.currentThread().getStackTrace()[2].getMethodName(),
                                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
                        })
                        .doOnNext(new Consumer<ContentValues>() {
                            @Override
                            public void accept(ContentValues contentValuesInsert) throws Throwable {
                                try{
                                     String СтолбикСравнения="uuid";
                                    Integer     ОперацияUPDATE=0;
                                    Object UUID= contentValuesInsert.get(СтолбикСравнения);
                                    System.out.println("  ИзменяемыйСтлобикСравенения  " + СтолбикСравнения);
                                        ОперацияUPDATE  = sqLiteDatabase.update(table, contentValuesInsert,     СтолбикСравнения +"=?"
                                                ,new String[]{(String) UUID});
                                    Log.w(this.getClass().getName(), " Вставка массовая через contentValuesInsert  burkInsert   ОперацияUPDATE " +  ОперацияUPDATE);
                                    if (ОперацияUPDATE>0) {
                                        РезультатОперацииBurkUPDATE.add(Integer.parseInt(ОперацияUPDATE.toString()));
                                        // TODO: 24.11.2022  удаление с последующей вставкой
                                        ДанныеДляОперацииUpdates.remove(contentValuesInsert);
                                        // TODO: 20.03.2023 МЕняем Статуст УдалелитьсСервера на Удаленный
                                    }else{
                                           Cursor cursor=        sqLiteDatabase.rawQuery(" select "+
                                                    СтолбикСравнения+" from "+ table +" WHERE  "+СтолбикСравнения+" =?  ",new String[]{UUID.toString()});
                                        if (cursor!=null) {
                                            if ( cursor.getCount() > 0) {
                                                Log.d(this.getClass().getName(), "cursor.getCount()" + cursor.getCount());
                                                // TODO: 24.11.2022  удаление с последующей вставкой
                                                ДанныеДляОперацииUpdates.remove(contentValuesInsert);
                                            }
                                        }
                                        cursor.close();
                                    }//todo конец анализ
                                    Log.w(this.getClass().getName(), "count  bulkInsert  РезультатОперацииBurkUPDATE.size() "
                                            + РезультатОперацииBurkUPDATE.size()+"\n"+"bulkPOTOK "+Thread.currentThread().getName()+"\n"+
                                            " FUTURE FUTURE SIZE  EntityMaterialBinary "+"\n"+
                                            "  isParallel isParallel isParallel" + " ДанныеДляВторогоЭтапаBulkINSERT ДанныеДляВторогоЭтапаBulkINSERT.size()  "
                                            + ДанныеДляОперацииUpdates.size() );
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                                            Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                            Thread.currentThread().getStackTrace()[2].getMethodName(),
                                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                                }
                            }
                        })
                        .doOnComplete(new Action() {
                            @Override
                            public void run() throws Throwable {
                                // TODO: 19.11.2022 ПОДНИМАЕМ ВЕРИСЮ ДАННЫХ
                                Integer РезультатПовышенииВерсииДанных=0;
                                if(РезультатОперацииBurkUPDATE.size()>0 ){
                                    РезультатПовышенииВерсииДанных =
                                            new SubClassUpVersionDATA().МетодVesrionUPMODIFITATION_Client(table,getContext() );
                                    Log.d(this.getClass().getName(), " РезультатПовышенииВерсииДанных  " + РезультатПовышенииВерсииДанных);
                                }

                                    // TODO: 09.11.2022 закрывает ТРАНЗАКЦИИ ВНУТРИ
                                    if ( РезультатПовышенииВерсииДанных>0) {
                                    }
                            }
                        })
                        .onErrorComplete(new Predicate<Throwable>() {
                            @Override
                            public boolean test(Throwable throwable) throws Throwable {
                                Log.e(this.getClass().getName(), "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(throwable.toString(), this.getClass().getName(),
                                        Thread.currentThread().getStackTrace()[2].getMethodName(),
                                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                                return false;
                            }
                        })
                        .blockingSubscribe();
            }
            // TODO: 20.03.2023 ВТОРАЯ ОПЕРАЦИЯ вставка после обновления
            // TODO: 10.11.2022 ПЕРЕХОД КО ВТОРОМУ МЕТОДУ ПРОВАЙДЕРУ INSERT
            ContentValues[] ДляОперацииInsert=new ContentValues[ДанныеДляОперацииUpdates.size()];
            ДляОперацииInsert=ДанныеДляОперацииUpdates.toArray(ДляОперацииInsert);
            // TODO: 24.11.2022 вторая операция посл обновля пытаемся вставить данные
             uri = Uri.parse("content://com.dsy.dsu.providerdatabase/" + table + "");
            ContentResolver contentResolver = getContext().getContentResolver();
            int РезультатВставкиМассовой = contentResolver.bulkInsert(uri, ДляОперацииInsert);
            РезультатОперацииBurkUPDATE.add(РезультатВставкиМассовой);
            // TODO: 08.12.2022 финальный результат двух операций
            Integer ФинальныйРезультаUpdateAndInsert=  РезультатОперацииBurkUPDATE.stream().reduce(0, (a, b) -> a + b);
            // TODO: 10.11.2022 получаем ответ данные
            Log.w(this.getClass().getName(), " BULK insert updaet ibsetyРезультатВнутренаяbulkЗеркало.size()" + РезультатОперацииBurkUPDATE.size());
            Log.w(this.getClass().getName(), " BULK insert updaet ФинальныйРезультаUpdateAndInsert"
                    +ФинальныйРезультаUpdateAndInsert);
            // TODO: 20.03.2023 Смена Статуса С Сервера  По СТАТУСУ УДАЛАЛИТЬ НА УДАЛЕННЫЙ
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                    " table  " + table + "  РезультатОперацииBurkUPDATE.size() " + РезультатОперацииBurkUPDATE.size());


        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

        return    РезультатОперацииBurkUPDATE.stream().reduce(0, (a, b) -> a + b);
    }

    // TODO: 26.04.2023 класс парсинга json от сервера
class SubClassJsonParserOtServer{
      private   JsonNode jsonNodeParent;
      private ContentValues  ТекущийАдаптерДляВсего;
      private  String имяТаблицаAsync;
        CopyOnWriteArrayList<Integer> РезультатОперацииBurkUPDATE=new CopyOnWriteArrayList<>();
        public SubClassJsonParserOtServer(@NonNull  JsonNode jsonNodeParent,@NonNull String имяТаблицаAsync) {
            this.jsonNodeParent=jsonNodeParent;
            this.имяТаблицаAsync=имяТаблицаAsync;
        }

        Bundle методUpdateПарсингаJson() {
            Bundle bundleОперацииUpdateOrinsert=new Bundle();
            try{
            String ФлагКакойСинхронизацияПерваяИлиНет = preferences.getString("РежимЗапускаСинхронизации", "");
                                // TODO: 23.04.2023  ПЕРВЫЙ  Flowable
                                Flowable.fromIterable(jsonNodeParent)
                                        .onBackpressureBuffer()
                                        .subscribeOn(Schedulers.single())
                                        .buffer(100)
                                        .doOnError(new Consumer<Throwable>() {
                                            @Override
                                            public void accept(Throwable throwable) throws Throwable {
                                                Log.e(this.getClass().getName(), "Ошибка " + throwable + " Метод :" +
                                                        Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(throwable.toString(),
                                                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                                            }
                                        })
                                        .blockingForEach(new Consumer<List<JsonNode>>() {
                                            @Override
                                            public void accept(List<JsonNode> jsonNodesBuffer500) throws Throwable {
                                                // TODO: 13.01.2023  ВТОРОЙ  Flowable
                                                Flowable.fromIterable(jsonNodesBuffer500)
                                                        .onBackpressureBuffer()
                                                        .subscribeOn(Schedulers.single())
                                                        .doOnError(new Consumer<Throwable>() {
                                                            @Override
                                                            public void accept(Throwable throwable) throws Throwable {
                                                                Log.e(this.getClass().getName(), "Ошибка " + throwable + " Метод :" +
                                                                        Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                                                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(throwable.toString(),
                                                                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                                                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                                                            }
                                                        })
                                                        .blockingForEach(new Consumer<JsonNode>() {
                                                            @Override
                                                            public void accept(JsonNode jsonNodeOneRowBuffer) throws Throwable {
                                                                // TODO: 23.04.2023 Two
                                                                Integer ОперацияUPDATE = 0;
                                                                // TODO: 26.04.2023
                                                                ТекущийАдаптерДляВсего = new ContentValues();
                                                                jsonNodeOneRowBuffer.fields().forEachRemaining(
                                                                        new java.util.function.Consumer<Map.Entry<String, JsonNode>>() {
                                                                            @Override
                                                                            public void accept(Map.Entry<String, JsonNode> stringJsonNodeEntry) {
                                                                                // TODO: 06.10.2022  ВНУТрений СТрочка обработки данных сами Столбикки
                                                                                String ПолеОтJSONKEY = stringJsonNodeEntry.getKey().trim();
                                                                                switch (имяТаблицаAsync.trim().toLowerCase()) {
                                                                                    case "tabels":
                                                                                    case "chats":
                                                                                    case "data_chat":
                                                                                    case "chat_users":
                                                                                    case "fio":
                                                                                    case "tabel":
                                                                                    case "cfo":
                                                                                    case "data_tabels":
                                                                                    case "nomen_vesov":
                                                                                    case "type_materials":
                                                                                    case "company":
                                                                                    case "track":
                                                                                    case "prof":
                                                                                        System.out.println("  ПолеОтJSONKEY  " + ПолеОтJSONKEY);
                                                                                        if (stringJsonNodeEntry.getKey().contentEquals("id") == true) {
                                                                                            ПолеОтJSONKEY = "_id";
                                                                                        }
                                                                                        break;
                                                                                }
                                                                                // TODO: 27.10.2022 Дополнительна Обработка
                                                                                String ПолеЗначениеJson = stringJsonNodeEntry.getValue().asText()
                                                                                        .replace("\"", "").replace("\\n", "")
                                                                                        .replace("\\r", "").replace("\\", "")
                                                                                        .replace("\\t", "").trim();//todo .replaceAll("[^A-Za-zА-Яа-я0-9]", "")
                                                                                if (ПолеОтJSONKEY.equalsIgnoreCase("status_carried_out") ||
                                                                                        ПолеОтJSONKEY.equalsIgnoreCase("closed") ||
                                                                                        ПолеОтJSONKEY.equalsIgnoreCase("locked")) {
                                                                                    if (ПолеЗначениеJson.equalsIgnoreCase("false") ||
                                                                                            ПолеЗначениеJson.equalsIgnoreCase("0")) {
                                                                                        ПолеЗначениеJson = "False";
                                                                                    }
                                                                                    if (ПолеЗначениеJson.equalsIgnoreCase("true") ||
                                                                                            ПолеЗначениеJson.equalsIgnoreCase("1")) {
                                                                                        ПолеЗначениеJson = "True";
                                                                                    }
                                                                                }
                                                                                Log.d(this.getClass().getName(), " ПолеОтJSONKEY " + ПолеОтJSONKEY +
                                                                                        " ПолеЗначениеJson" + ПолеЗначениеJson);
                                                                                // TODO: 27.10.2022  UUID есть Обновление
                                                                                ТекущийАдаптерДляВсего.put(ПолеОтJSONKEY, ПолеЗначениеJson);//

                                                                                Log.d(this.getClass().getName(), "\n" + " class " +
                                                                                        Thread.currentThread().getStackTrace()[2].getClassName()
                                                                                        + "\n" +
                                                                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                                                                        + " ТекущийАдаптерДляВсего " + ТекущийАдаптерДляВсего.size());

                                                                                Log.d(this.getClass().getName(), " stringJsonNodeEntry.getKey() "
                                                                                        + stringJsonNodeEntry.getKey()
                                                                                        + " stringJsonNodeEntry.getValue()"
                                                                                        + stringJsonNodeEntry.getValue());
                                                                            }
                                                                        });
                                                                // TODO: 27.10.2022  UUID есть Обновление

                                                                if (ФлагКакойСинхронизацияПерваяИлиНет.equalsIgnoreCase("ПовторныйЗапускСинхронизации") ||
                                                                        имяТаблицаAsync.equalsIgnoreCase("settings_tabels") ||
                                                                        имяТаблицаAsync.equalsIgnoreCase("view_onesignal")) {
                                                                    ОперацияUPDATE = методUpdateCALL();

                                                                    // TODO: 27.04.2023  метод Вставки
                                                                    Long ОперацияInsert = 0l;
                                                                    if (ОперацияUPDATE == 0) {
                                                                        ОперацияInsert = методInsertCAll(ОперацияUPDATE);
                                                                    }
                                                                    // TODO: 27.04.2023
                                                                    Log.d(this.getClass().getName(), "\n" + " class " +
                                                                            Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                                                            + имяТаблицаAsync + " АдаптерДляВставкиИОбновления.size() "
                                                                            + ТекущийАдаптерДляВсего + " ОперацияUPDATE "
                                                                            + ОперацияUPDATE + " ОперацияInsert " + ОперацияInsert);

                                                                } else {
                                                                    // TODO: 27.04.2023  метод Вставки
                                                                    Long ОперацияInsert = методInsertCAll(ОперацияUPDATE);
                                                                    // TODO: 27.04.2023
                                                                    Log.d(this.getClass().getName(), "\n" + " class " +
                                                                            Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                                                            + имяТаблицаAsync + " АдаптерДляВставкиИОбновления.size() "
                                                                            + ТекущийАдаптерДляВсего + " ОперацияUPDATE "
                                                                            + ОперацияUPDATE + " ОперацияInsert " + ОперацияInsert);

                                                                }
                                                                // TODO: 27.04.2023  clears
                                                                ТекущийАдаптерДляВсего.clear();
                                                                // TODO: 25.04.2023  ПОСЛЕ ПРОХОДА ОБНУЛЯЕМ ДВА КОНТЕЙНЕРА
                                                                Log.d(this.getClass().getName(), "\n" + " class " +
                                                                        Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                                                        + имяТаблицаAsync + " АдаптерДляВставкиИОбновления.size() "
                                                                        + ТекущийАдаптерДляВсего + " РезультатОперацииBurkUPDATE "
                                                                        + РезультатОперацииBurkUPDATE + " ОперацияUPDATE " + ОперацияUPDATE);
                                                            }
                                                        },jsonNodesBuffer500.size());
                                                if (РезультатОперацииBurkUPDATE.size() > 0) {
                                                    Integer РезультатПовышенииВерсииДанных =
                                                            new SubClassUpVersionDATA().МетодVesrionUPMODIFITATION_Client(имяТаблицаAsync, getContext() );
                                                    Log.d(this.getClass().getName(), " РезультатПовышенииВерсииДанных  " + РезультатПовышенииВерсииДанных);
                                                }
                                                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                                        + имяТаблицаAsync + " АдаптерДляВставкиИОбновления.size() "
                                                        + ТекущийАдаптерДляВсего.size() + " РезультатОперацииBurkUPDATE "
                                                        + РезультатОперацииBurkUPDATE + "  имяТаблицаAsync " + имяТаблицаAsync
                                                        + " bundleОперацииUpdateOrinsert " + bundleОперацииUpdateOrinsert +
                                                        " РезультатОперацииBurkUPDATE.size() " + РезультатОперацииBurkUPDATE.size());
                                            }
                                        },jsonNodeParent.size());
                // TODO: 11.10.2022 ПОСЛЕ ОПЕРАЦИИ ВИЗАУЛИЗИРУЕМ КОНЕЦ ОПЕРАЦИИ ПОЛЬЗОВАТЕЛЮ
                if (РезультатОперацииBurkUPDATE.size() > 0) {
                    Integer РезультатПовышенииВерсииДанных =
                            new SubClassUpVersionDATA().МетодVesrionUPMODIFITATION_Client(имяТаблицаAsync, getContext() );
                    Log.d(this.getClass().getName(), " РезультатПовышенииВерсииДанных  " + РезультатПовышенииВерсииДанных);
                }
                // TODO: 27.04.2023  сохраняем количество операций
                bundleОперацииUpdateOrinsert.putLong("ResultAsync", РезультатОперацииBurkUPDATE.size());

                    if (РезультатОперацииBurkUPDATE.size() > 0) {
                    }

                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + имяТаблицаAsync + " АдаптерДляВставкиИОбновления.size() "
                        + ТекущийАдаптерДляВсего.size() + " РезультатОперацииBurkUPDATE "
                        + РезультатОперацииBurkUPDATE + "  имяТаблицаAsync " + имяТаблицаAsync
                        + " bundleОперацииUpdateOrinsert " + bundleОперацииUpdateOrinsert +
                        " РезультатОперацииBurkUPDATE.size() " + РезультатОперацииBurkUPDATE.size());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
            return bundleОперацииUpdateOrinsert;
        }
        @NonNull
        private Integer методUpdateCALL() {
            Integer ОперацияUPDATE=0;
            try{
                String СтолбикСравнения = "uuid";
                Object UUID = ТекущийАдаптерДляВсего.get(СтолбикСравнения);
                System.out.println("  ИзменяемыйСтлобикСравенения  " + СтолбикСравнения);
                ОперацияUPDATE =  sqLiteDatabase.update(имяТаблицаAsync, ТекущийАдаптерДляВсего,
                        СтолбикСравнения + "=?"
                        , new String[]{(String) UUID});
                Log.w(this.getClass().getName(), " Вставка массовая через contentValuesInsert  burkInsert   ОперацияUPDATE "
                        + ОперацияUPDATE);
                if (ОперацияUPDATE > 0) {
                    РезультатОперацииBurkUPDATE.add(Integer.parseInt(ОперацияUPDATE.toString()));
                    Log.d(this.getClass().getName(), "\n" + " class " +
                            Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                            + имяТаблицаAsync + " АдаптерДляВставкиИОбновления.size() "
                            + ТекущийАдаптерДляВсего + " РезультатОперацииBurkUPDATE "
                            + РезультатОперацииBurkUPDATE +
                            " ОперацияUPDATE " +ОперацияUPDATE);


                } else {
                    Cursor cursor =  sqLiteDatabase.rawQuery(" select " +
                                    СтолбикСравнения + " from " + имяТаблицаAsync + " WHERE  " + СтолбикСравнения + " =?  ",
                            new String[]{UUID.toString()});
                    if (cursor != null) {
                        if (cursor.getCount() > 0) {
                            Log.d(this.getClass().getName(), "cursor.getCount()" + cursor.getCount());
                            // TODO: 24.11.2022  удаление с последующей вставкой
                            ОперацияUPDATE=1;
                        }
                    }
                    cursor.close();
                    // TODO: 27.04.2023 ЕЩЕ РАЗ ПОПРОБУЕМ ВСТАВКУ
                }//todo конец анализ
                // TODO: 27.04.2023
                Log.d(this.getClass().getName(), "\n" + " class " +
                        Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + имяТаблицаAsync + " АдаптерДляВставкиИОбновления.size() "
                        + ТекущийАдаптерДляВсего + " ОперацияUPDATE "
                        + ОперацияUPDATE);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return ОперацияUPDATE;
        }

        private Long методInsertCAll(@NonNull  Integer ОперацияUPDATE) {
            // TODO: 26.04.2023 Insert
            Long      ОперацияInsert=0l;
            try{
                if(ОперацияUPDATE ==0 && ТекущийАдаптерДляВсего.size()>0){
                    ОперацияInsert =  sqLiteDatabase.insertOrThrow(имяТаблицаAsync,
                            null, ТекущийАдаптерДляВсего);
                    if (ОперацияInsert>0) {
                        РезультатОперацииBurkUPDATE.add(Integer.parseInt(ОперацияInsert.toString()));
                        // TODO: 27.04.2023  повышаем верисю данных
                        Log.d(this.getClass().getName(), "\n" + " class " +
                                Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                + имяТаблицаAsync + " АдаптерДляВставкиИОбновления.size() "
                                + ТекущийАдаптерДляВсего + " РезультатОперацииBurkUPDATE "
                                + РезультатОперацииBurkUPDATE +
                                " ОперацияInsert " +ОперацияInsert);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return  ОперацияInsert;
        }

}
// TODO: 27.04.2023  ВТОРОЙ ВАПРИАНТ ПАРСИНА КЛАССА
// TODO: 26.04.2023 класс парсинга json от сервера
class SubClassJsonTwoParserOtServer{
    private   JsonNode jsonNodeParent;
    private  String имяТаблицаAsync;
    CopyOnWriteArrayList<Integer> РезультатОперацииBurkUPDATE=new CopyOnWriteArrayList<>();
    CopyOnWriteArrayList<ContentValues>  contentValuesCopyOnWriteArrayList;
    public SubClassJsonTwoParserOtServer(@NonNull  CopyOnWriteArrayList<ContentValues> contentValuesCopyOnWriteArrayList,@NonNull String имяТаблицаAsync) {
        this.jsonNodeParent=jsonNodeParent;
        this.имяТаблицаAsync=имяТаблицаAsync;
        this.contentValuesCopyOnWriteArrayList=contentValuesCopyOnWriteArrayList;
    }

     Bundle  методUpdateПарсингаJson() {
        Bundle bundleОперацииUpdateOrinsert=new Bundle();
        try{
            String ФлагКакойСинхронизацияПерваяИлиНет = preferences.getString("РежимЗапускаСинхронизации", "");
            // TODO: 28.04.2023
                       Flowable.fromIterable(contentValuesCopyOnWriteArrayList)
                               .subscribeOn(Schedulers.single())
                               .onBackpressureBuffer()
                               .filter(filter->filter.size()>0)
                    .doOnNext(new Consumer<ContentValues>() {
                        @Override
                        public void accept(ContentValues ТекущийАдаптерДляВсего) throws Throwable {
                            // TODO: 27.04.2023  ПОсле заполенения строчки
                            Log.d(this.getClass().getName(), "BUffer " + " Метод :" +
                                    Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber()+ "ТекущийАдаптерДляВсего  "+ТекущийАдаптерДляВсего);
                            // TODO: 27.10.2022  UUID есть Обновление
                            Integer  ОперацияUPDATE=0;
                            if (ФлагКакойСинхронизацияПерваяИлиНет.equalsIgnoreCase("ПовторныйЗапускСинхронизации") ||
                                    имяТаблицаAsync.equalsIgnoreCase("settings_tabels") ||
                                    имяТаблицаAsync.equalsIgnoreCase("view_onesignal")) {
                                ОперацияUPDATE = методUpdateCALL(ТекущийАдаптерДляВсего);
                                if(ОперацияUPDATE>0){
                                    // TODO: 27.04.2023  результат
                                    РезультатОперацииBurkUPDATE.add(ОперацияUPDATE);
                                }

                                // TODO: 27.04.2023  метод Вставки
                                Long ОперацияInsert = 0l;
                               if (ОперацияUPDATE == 0) {
                                    ОперацияInsert = методInsertCAll(ОперацияUPDATE,ТекущийАдаптерДляВсего);
                                   // TODO: 27.04.2023  результат
                                   if (ОперацияInsert>0) {
                                       РезультатОперацииBurkUPDATE.add(ОперацияInsert.intValue());
                                   }
                               }
                                // TODO: 27.04.2023
                                Log.d(this.getClass().getName(), "\n" + " class " +
                                        Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                        + имяТаблицаAsync + " АдаптерДляВставкиИОбновления.size() "
                                        + ТекущийАдаптерДляВсего + " ОперацияUPDATE "
                                        + ОперацияUPDATE + " ОперацияInsert " + ОперацияInsert+
                                        "  РезультатОперацииBurkUPDATE " +РезультатОперацииBurkUPDATE);

                            } else {
                                // TODO: 27.04.2023  метод Вставки
                                Long ОперацияInsert = методInsertCAll(ОперацияUPDATE,ТекущийАдаптерДляВсего);
                                // TODO: 27.04.2023  результат
                                if (ОперацияInsert>0) {
                                    РезультатОперацииBurkUPDATE.add(ОперацияInsert.intValue());
                                }
                                Log.d(this.getClass().getName(), "\n" + " class " +
                                        Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                        + имяТаблицаAsync + " АдаптерДляВставкиИОбновления.size() "
                                        + ТекущийАдаптерДляВсего + " ОперацияUPDATE "
                                        + ОперацияUPDATE + " ОперацияInsert " + ОперацияInsert+
                                         " РезультатОперацииBurkUPDATE " +РезультатОперацииBurkUPDATE);

                            }
                            // TODO: 27.04.2023  clears
                            ТекущийАдаптерДляВсего.clear();
                            // TODO: 25.04.2023  ПОСЛЕ ПРОХОДА ОБНУЛЯЕМ ДВА КОНТЕЙНЕРА
                            Log.d(this.getClass().getName(), "\n" + " class " +
                                    Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                    + имяТаблицаAsync + " АдаптерДляВставкиИОбновления.size() "
                                    + ТекущийАдаптерДляВсего + " РезультатОперацииBurkUPDATE "
                                    + РезультатОперацииBurkUPDATE + " ОперацияUPDATE " + ОперацияUPDATE);
                            // TODO: 28.04.2023 end row
                            Log.d(this.getClass().getName(), "\n" + " class " +
                                    Thread.currentThread().getStackTrace()[2].getClassName()
                                    + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

                            // TODO: 28.04.2023 start Insert Update
                        }
                    })
                    .doOnError(new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Throwable {
                            throwable.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(throwable.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    })
                    .doOnComplete(new Action() {
                        @Override
                        public void run() throws Throwable {
                            // TODO: 27.04.2023  ПОСЛЕ ВСЕХ ОПЕРАЦИЙ ЗАКАНЧИВАЕМ ТРАНЗАКЦИЮ и Повышаем Версию
                            if (РезультатОперацииBurkUPDATE.size() > 0) {
                                Integer РезультатПовышенииВерсииДанных =
                                        new SubClassUpVersionDATA().МетодVesrionUPMODIFITATION_Client(имяТаблицаAsync, getContext());
                                Log.d(this.getClass().getName(), " РезультатПовышенииВерсииДанных  " + РезультатПовышенииВерсииДанных);
                            }
                            // TODO: 27.04.2023  сохраняем количество операций
                            bundleОперацииUpdateOrinsert.putLong("ResultAsync", РезультатОперацииBurkUPDATE.size());
                                if (РезультатОперацииBurkUPDATE.size() > 0) {
                                }
                            // TODO: 02.05.2023 clear
                            // TODO: 02.05.2023
                            РезультатОперацииBurkUPDATE.clear();
                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                    + имяТаблицаAsync + " АдаптерДляВставкиИОбновления.size() "+ " РезультатОперацииBurkUPDATE "
                                    + РезультатОперацииBurkUPDATE + "  имяТаблицаAsync " + имяТаблицаAsync
                                    + " bundleОперацииUpdateOrinsert " + bundleОперацииUpdateOrinsert +
                                    " РезультатОперацииBurkUPDATE.size() " + РезультатОперацииBurkUPDATE.size());
                        }
                    })
                               .onErrorComplete(new Predicate<Throwable>() {
                                   @Override
                                   public boolean test(Throwable throwable) throws Throwable {
                                       throwable.printStackTrace();
                                       Log.e(this.getClass().getName(), "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                               " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                       new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(throwable.toString(), this.getClass().getName(),
                                               Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                       return false;
                                   }
                               })
                    .blockingSubscribe();
            // TODO: 28.04.2023
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return bundleОперацииUpdateOrinsert;
    }
    @NonNull
    private Integer методUpdateCALL(@NonNull ContentValues ТекущийАдаптерДляВсего) {
        Integer ОперацияUPDATE=0;
        try{
            String СтолбикСравнения = "uuid";
            Object UUID = ТекущийАдаптерДляВсего.get(СтолбикСравнения);
            System.out.println("  ИзменяемыйСтлобикСравенения  " + СтолбикСравнения);
            ОперацияUPDATE =  sqLiteDatabase.update(имяТаблицаAsync, ТекущийАдаптерДляВсего,
                    СтолбикСравнения + "=?"
                    , new String[]{(String) UUID});
            Log.w(this.getClass().getName(), " Вставка массовая через contentValuesInsert  burkInsert   ОперацияUPDATE "
                    + ОперацияUPDATE);
            if (ОперацияUPDATE > 0) {
                РезультатОперацииBurkUPDATE.add(Integer.parseInt(ОперацияUPDATE.toString()));
                Log.d(this.getClass().getName(), "\n" + " class " +
                        Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + имяТаблицаAsync + " АдаптерДляВставкиИОбновления.size() "
                        + ТекущийАдаптерДляВсего + " РезультатОперацииBurkUPDATE "
                        + РезультатОперацииBurkUPDATE +
                        " ОперацияUPDATE " +ОперацияUPDATE);


            } else {
                Cursor cursor =  sqLiteDatabase.rawQuery(" select " +
                                СтолбикСравнения + " from " + имяТаблицаAsync + " WHERE  " + СтолбикСравнения + " =?  ",
                        new String[]{UUID.toString()});
                if (cursor != null) {
                    if (cursor.getCount() > 0) {
                        Log.d(this.getClass().getName(), "cursor.getCount()" + cursor.getCount());
                        // TODO: 24.11.2022  удаление с последующей вставкой
                        ОперацияUPDATE=1;
                    }
                }
                cursor.close();
                // TODO: 27.04.2023 ЕЩЕ РАЗ ПОПРОБУЕМ ВСТАВКУ
            }//todo конец анализ
            // TODO: 27.04.2023
            Log.d(this.getClass().getName(), "\n" + " class " +
                    Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + имяТаблицаAsync + " АдаптерДляВставкиИОбновления.size() "
                    + ТекущийАдаптерДляВсего + " ОперацияUPDATE "
                    + ОперацияUPDATE);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return ОперацияUPDATE;
    }

    private Long методInsertCAll(@NonNull  Integer ОперацияUPDATE,@NonNull ContentValues ТекущийАдаптерДляВсего) {
        // TODO: 26.04.2023 Insert
        Long      ОперацияInsert=0l;
        try{
            if(ОперацияUPDATE ==0 && ТекущийАдаптерДляВсего.size()>0){
                ОперацияInsert =  sqLiteDatabase.insert(имяТаблицаAsync, null, ТекущийАдаптерДляВсего);
                if (ОперацияInsert>0) {
                    РезультатОперацииBurkUPDATE.add(Integer.parseInt(ОперацияInsert.toString()));
                    // TODO: 27.04.2023  повышаем верисю данных
                    Log.d(this.getClass().getName(), "\n" + " class " +
                            Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                            + имяТаблицаAsync + " АдаптерДляВставкиИОбновления.size() "
                            + ТекущийАдаптерДляВсего + " РезультатОперацииBurkUPDATE "
                            + РезультатОперацииBurkUPDATE +
                            " ОперацияInsert " +ОперацияInsert);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  ОперацияInsert;
    }

}
}
