package com.dsy.dsu.Services;

import android.app.IntentService;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.BinderThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.dsy.dsu.AllDatabases.SQLTE.GetSQLiteDatabase;
import com.dsy.dsu.BusinessLogicAll.Class_Connections_Server;
import com.dsy.dsu.BusinessLogicAll.Class_GRUD_SQL_Operations;
import com.dsy.dsu.BusinessLogicAll.Class_Generation_Errors;
import com.dsy.dsu.BusinessLogicAll.Class_Generations_PUBLIC_CURRENT_ID;
import com.dsy.dsu.BusinessLogicAll.Class_MODEL_synchronized;
import com.dsy.dsu.BusinessLogicAll.Class_Visible_Processing_Async;
import com.dsy.dsu.BusinessLogicAll.Jakson.GeneratorJSONSerializer;
import com.dsy.dsu.BusinessLogicAll.PUBLIC_CONTENT;
import com.dsy.dsu.BusinessLogicAll.SubClassUpVersionDATA;
import com.dsy.dsu.BusinessLogicAll.SubClass_Connection_BroadcastReceiver_Sous_Asyns_Glassfish;
import com.dsy.dsu.OneSignals.Class_Generation_SendBroadcastReceiver_And_Firebase_OneSignal;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.firebase.annotations.concurrent.Background;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;

import java.io.Serializable;
import java.io.StringWriter;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import javax.crypto.NoSuchPaddingException;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class Service_For_Remote_Async_Binary extends IntentService {
    protected LocalBinderAsync binderBinderRemoteAsync = new LocalBinderAsync();
    private Context context;
    private  Integer МаксималноеКоличествоСтрочекJSON;
    private SharedPreferences preferences;
    private   int Проценты;
    private Integer ИндексВизуальнойДляPrograssBar=0;
    private      Integer ПубличныйIDДляФрагмента=0;

    private Service_For_Public.LocalBinderОбщий localBinderОбщий;
    private      ServiceConnection connectionPUBLIC;
    private                 Integer ПозицияТекущейТаблицы=1;

    private     String НазваниеСервернойТаблицы=new String();

    private  String РежимПерваяЗапускПослеPasswordИлиПовторная;

private  Handler handlerAsync;
    private  String КлючДляFirebaseNotification = "2a1819db-60c8-4ca3-a752-1b6cd9cadfa1";


    private     CopyOnWriteArrayList<Integer>  ЛистТаблицыОбмена;
    public Service_For_Remote_Async_Binary() {
        super("Service_For_Remote_Async");
    }
    @Override
    public void onCreate() {
        super.onCreate();
        try{
            МетодБиндинuCлужбыPublic(getApplicationContext());
       ПубличныйIDДляФрагмента = new SubClass_Connection_BroadcastReceiver_Sous_Asyns_Glassfish().МетодПолучениеяПубличногоID(context);
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date() + "\n+" +
                " Класс в процессе... " + this.getClass().getName() + "\n" +
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() +  "  ПубличныйIDДляФрагмента " +ПубличныйIDДляФрагмента);
     context .getSharedPreferences("sharedPreferencesХранилище", Context.MODE_MULTI_PROCESS);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
    }
    }


    /**
     * Class used for the client Binder.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */
   public class LocalBinderAsync extends Binder {
        public Service_For_Remote_Async_Binary getService() {
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
            return Service_For_Remote_Async_Binary.this;
        }

        @Override
        protected boolean onTransact(int code, @NonNull Parcel data, @Nullable Parcel reply, int flags) throws RemoteException {
  try{
            data = Parcel.obtain();
            reply = Parcel.obtain();
      reply.writeSerializable( (Serializable) new Handler(getMainLooper()));
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "  handlerAsync " +handlerAsync);
            ///return super.onTransact(code, data, reply, flags);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
        }
            return   true;
        }

        @Override
        public void linkToDeath(@NonNull DeathRecipient recipient, int flags) {
            super.linkToDeath(recipient, flags);
        }

        @Override
        public boolean unlinkToDeath(@NonNull DeathRecipient recipient, int flags) {
            return super.unlinkToDeath(recipient, flags);
        }
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        try{
            Log.d(context.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
        }
        return binderBinderRemoteAsync;
    }








    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
try{
        metodStartingSync(getApplicationContext(),null );
        Log.d(getApplicationContext().getClass().getName(), "\n"
                + " время: " + new Date() + "\n+" +
                " Класс в процессе... " + this.getClass().getName() + "\n" +
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
    }
    }

    @Override
    public void onDestroy() {
        try{
        super.onDestroy();
            if (connectionPUBLIC!=null) {
                getApplicationContext(). unbindService(connectionPUBLIC);
            }
            stopSelf();
        Log.d(context.getClass().getName(), "\n"
                + " время: " + new Date() + "\n+" +
                " Класс в процессе... " + this.getClass().getName() + "\n" +
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        Log.d(newBase.getClass().getName(), "\n"
                + " время: " + new Date() + "\n+" +
                " Класс в процессе... " + newBase.getClass().getName() + "\n" +
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        this.context = newBase;
        super.attachBaseContext(newBase);
    }




@BinderThread
@Background
    public Integer metodStartingSync(@NonNull Context context , @NonNull Handler handlerAsync ) {
    Integer       ФинальныйРезультатAsyncBackgroud=0;
    try{
            if (handlerAsync!=null) {
                this.handlerAsync=handlerAsync;
            }
            // TODO: 25.03.2023 ДОПОЛНИТЕОТНЕ УДЛАНИЕ СТАТУСА УДАЛЕНИЕ ПОСЛЕ СИНХРОНИАЗЦИИ
                ФинальныйРезультатAsyncBackgroud  = new Class_Engine_SQL(context).МетодЗАпускаФоновойСинхронизации(context);

            МетодПослеAsyncTaskЗавершающий( context,ФинальныйРезультатAsyncBackgroud);

            Log.d(context.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                    + " ФинальныйРезультатAsyncBackgroud " +ФинальныйРезультатAsyncBackgroud);

            // TODO: 26.03.2023 дополнительное удаление после Удаление статсу удалнеенон
            if (ФинальныйРезультатAsyncBackgroud>0  && ФинальныйРезультатAsyncBackgroud!=null) {
                МетодПослеСинхрониазцииУдалениеСтатусаУдаленный(context);
            }
            Log.d(context.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                    + " ФинальныйРезультатAsyncBackgroud " +ФинальныйРезультатAsyncBackgroud);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    return ФинальныйРезультатAsyncBackgroud;
    }


    @BinderThread
    public Integer metodStartingSyncWorkNamager(@NonNull Context context) {
        Integer       ФинальныйРезультатAsyncBackgroud=0;
        try{
            // TODO: 25.03.2023 ДОПОЛНИТЕОТНЕ УДЛАНИЕ СТАТУСА УДАЛЕНИЕ ПОСЛЕ СИНХРОНИАЗЦИИ
            ФинальныйРезультатAsyncBackgroud  = new Class_Engine_SQL(context).МетодЗАпускаФоновойСинхронизации(context);

            Log.d(context.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                    + "    ФинальныйРезультатAsyncBackgroud "+ФинальныйРезультатAsyncBackgroud);

            МетодПослеAsyncTaskЗавершающий( context,ФинальныйРезультатAsyncBackgroud);
            Log.d(context.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + " ФинальныйРезультатAsyncBackgroud " +ФинальныйРезультатAsyncBackgroud);

            if (ФинальныйРезультатAsyncBackgroud>0) {
                МетодПослеСинхрониазцииУдалениеСтатусаУдаленный(context);
            }
            Log.d(context.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + " ФинальныйРезультатAsyncBackgroud " +ФинальныйРезультатAsyncBackgroud);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  ФинальныйРезультатAsyncBackgroud;
    }


    // TODO: 26.12.2021  метод регистации на СЕРВЕРА ONESIGNAL
    @BinderThread
    public void МетодРегистрацииУстройсвоНАFirebaseAndOneSignal() {
        try{
            Integer  ПубличныйIDДляФрагмента=   new Class_Generations_PUBLIC_CURRENT_ID()
                    .ПолучениеПубличногоТекущегоПользователяID(getApplicationContext());
            new Class_Generation_SendBroadcastReceiver_And_Firebase_OneSignal(getApplicationContext()).
                    МетодПовторногоЗапускаFacebaseCloud_And_OndeSignal(КлючДляFirebaseNotification,ПубличныйIDДляФрагмента);
            //TODO ФУТУРЕ ЗАВЕРШАЕМ
            Log.d(this.getClass().getName(), "  МетодПовторногоЗапускаFacebaseCloud_And_OndeSignal(КлючДляFirebaseNotification,0); " +
                    " РезультатЗаписиНовогоIDОтСервреаOneSignal  " );
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }







    private void МетодПослеСинхрониазцииУдалениеСтатусаУдаленный(@NonNull Context context) {
        try {
            Intent intentПослеСинхроницииРегламентаняРаботаУдалениеДанных=new Intent();
            intentПослеСинхроницииРегламентаняРаботаУдалениеДанных.setClass(context, Service_For_Public.class);
            intentПослеСинхроницииРегламентаняРаботаУдалениеДанных.setAction("ЗапускУдалениеСтатусаУдаленияСтрок");
            // TODO: 25.03.2023 дополнительное удаление после синхрониазции статус Удаленныц
            if (localBinderОбщий!=null) {
                localBinderОбщий.getService().МетодГлавныйPublicPO(context,intentПослеСинхроницииРегламентаняРаботаУдалениеДанных,null);
            }
            Log.d(context.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +   "  localBinderОбщий  " +localBinderОбщий);

        } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
    }
    }

    private void МетодПослеAsyncTaskЗавершающий( @NonNull Context context,@NonNull Integer ФинальныйРезультатAsyncBackgroud) {
        try{
        // TODO: 05.11.2022  после  ВЫПОЛЕНИЯ СИНЗХРОНИАЗИИ СООБЩАЕМ ОБ ОКОНЧАТИИ СИХРОНИАЗЦИИ ВИЗУАЛЬТА
        new Class_Engine_SQL(context).   методCallBackPrograssBars(3, Проценты,new String(),0);
            Log.i(context.getClass().getName(),  " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " Проценты " + Проценты  );
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
    }
    }


































// TODO: 10.10.2022 КЛАСС ГЛАВНОЙ СИНХРОНИАЗЦИИИ

//////-------TODO  ЭТО ПЕРВЫЙ КОНТРОЛЛЕР КОТОРЫЙ ВИЗУАЛИЗИРУЕТ СИНХРОНИЗАЦИЮ С ПРОГРАССБАРОМ---------------------------------------------------------------


///TODO------------------------------------------------------ ЭТО  ВТОРОЙ КОНТРОЛЛЕР КОТОРЫЙ ЗАПУСКАЕТ СИНХРОНИЗАЦИЮ В ФОНЕ  (ВНУТРИ ПРИЛОЖЕНИЕ)---------------------------------------------------


    protected class Class_Engine_SQL extends Class_MODEL_synchronized {
        // TODO: 28.07.2022  переменые
        public    Context context;
        private CopyOnWriteArrayList<String> ГлавныеТаблицыСинхронизации =new CopyOnWriteArrayList();

        private   SQLiteDatabase sqLiteDatabase ;
        private  PUBLIC_CONTENT public_contentДатыДляГлавныхТаблицСинхронизации;
        private boolean ФлагУказываетЧтоТОлькоОбработкаТаблицДляЧАТА = false;
        private  String ФлагКакуюЧастьСинхронизацииЗапускаем =new String();

        private  Integer ИндексТекущейОперацииРеальногРезультатОбработкиАтблицы=0;
        // TODO: 28.07.2022
        public Class_Engine_SQL(@NotNull Context context) {
            super(context);
            this.context=context;
            public_contentДатыДляГлавныхТаблицСинхронизации=new PUBLIC_CONTENT(context);
            sqLiteDatabase=    GetSQLiteDatabase.SqliteDatabase();
            Log.w(context.getClass().getName(), "sqLiteDatabase" + sqLiteDatabase);
        }

        // TODO метод запуска СИНХРОНИЗАЦИИ  в фоне
        public Integer МетодЗАпускаФоновойСинхронизации(@NotNull Context context)   {
            Integer      РезультатаСинхронизации = 0;
            try{this.context=context;
                ГлавныеТаблицыСинхронизации = new PUBLIC_CONTENT(context).методCreatingMainTabels(context);
                Log.d(this.getClass().getName(), "  ГлавныеТаблицыСинхрониазции " + ГлавныеТаблицыСинхронизации.size());
                // TODO: 28.09.2022 запускам синхрогниазцию
                РезультатаСинхронизации=         МетодСамогоФоновойСинхронизации(ГлавныеТаблицыСинхронизации);
                Log.w(this.getClass().getName(), " ФОНОВАЯ СИНХОРОНИЗАЦИИИ ИДЁТ... СЛУЖБА "+РезультатаСинхронизации);

            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                // TODO: 01.09.2021 метод вызова
                new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return РезультатаСинхронизации;
        }
        // TODO: 25.09.2021 ВТОРАЯ ВЕСРИЯ ЗАПУСКА СИНХРОНМИАЗЦИИИ С ТАБЕЛЯ


// TODO: 19.08.2021  ДАННЫЙ МЕТОД ЗАПУСКАЕТ СИНХРОНИЗЦИ ДЛЯ ЧАТА

// TODO: 08.09.2021 НИЖЕ НАЧИНАЮТЬСЯ МЕТОДЫ САМОЙ ФОНОВОЙ СИНХРОНИАЗЦИИ ВНИМАНИЕ !!!!!!!
// TODO: 08.09.2021 НИЖЕ НАЧИНАЮТЬСЯ МЕТОДЫ САМОЙ ФОНОВОЙ СИНХРОНИАЗЦИИ ВНИМАНИЕ !!!!!!!
// TODO: 08.09.2021 НИЖЕ НАЧИНАЮТЬСЯ МЕТОДЫ САМОЙ ФОНОВОЙ СИНХРОНИАЗЦИИ ВНИМАНИЕ !!!!!!!
        // TODO: 08.09.2021 НИЖЕ НАЧИНАЮТЬСЯ МЕТОДЫ САМОЙ ФОНОВОЙ СИНХРОНИАЗЦИИ ВНИМАНИЕ !!!!!!!
// TODO: 08.09.2021 НИЖЕ НАЧИНАЮТЬСЯ МЕТОДЫ САМОЙ ФОНОВОЙ СИНХРОНИАЗЦИИ ВНИМАНИЕ !!!!!!!
        // TODO: 08.09.2021 НИЖЕ НАЧИНАЮТЬСЯ МЕТОДЫ САМОЙ ФОНОВОЙ СИНХРОНИАЗЦИИ ВНИМАНИЕ !!!!!!!
















        ///TODO САМ ФОНОВЫЙ ПОТОК МЕТОД

        Integer МетодСамогоФоновойСинхронизации(@NonNull  CopyOnWriteArrayList ФлагОбработкаЧастиТаблицНеВсехЗависимостиОбщаяСинхронизацияЗапущенаИлиТолькоЧат) {
            String ТекущаяТаблицаДляОБменаДанными = null;
            Integer ФинальныйРезультаФоновойСинхрониазции=0;
            Class_GRUD_SQL_Operations class_grud_sql_operationsМетодСамогоФоновойСинхронизации=new Class_GRUD_SQL_Operations(context);
            try {
                Log.d(this.getClass().getName(), "ЗАПУСК СЛУЖБА ВНУТРИ startService   Вещятеля BroadcastReceiver  Synchronizasiy_Data " + new Date() +
                        "\n" + " Build.BRAND " + Build.BRAND.toString()+" СколькоСтрочекJSON " );
                //////TODO ШАГ ТРЕТИЙ  ЗАПУСКАЕМ САМУ СИНХРОНИЗАЦИЮ  сама синхронизация в фоне
                ФинальныйРезультаФоновойСинхрониазции =            МетодНачалоСихронизациивФоне(context); ////Получение Версии Данных Сервера для дальнейшего анализа

                Log.d(this.getClass().getName(), " ФОНОВАЯ СЛУЖБА КОЛИЧЕСТВО УСПЕШНЫХ ВСТАКОВ ИЛИ/И ОБНОВЛЕНИЙ  ФинальныйРезультаФоновойСинхрониазции " +
                         "  ФинальныйРезультаФоновойСинхрониазции " +ФинальныйРезультаФоновойСинхрониазции);
                Log.d(this.getClass().getName(), " ФОНОВАЯ СЛУЖБА КОЛИЧЕСТВО УСПЕШНЫХ ВСТАКОВ ИЛИ/И ОБНОВЛЕНИЙ  ФинальныйРезультаФоновойСинхрониазции " +
                        ФинальныйРезультаФоновойСинхрониазции);
            } catch (Exception e) {
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                // TODO: 01.09.2021 метод вызова
                new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return ФинальныйРезультаФоновойСинхрониазции;
        }


























//////ПЕРВЫЙ МЕТОД ОБМЕНА ДАННЫМИ С СЕРВЕРОМ МЕТОД GET

        Integer МетодНачалоСихронизациивФоне(@NotNull Context context) throws InterruptedException, ExecutionException, TimeoutException, JSONException {
            Integer результатСинхрониазции=0;
            try {
                результатСинхрониазции=     МетодПолучениеIDотСервераДляГеренированиеUUID(); ////САМАЯ ПЕРВАЯ КОМАНДА НАЧАЛА ОБМНЕНА ДАННЫМИ///// TODO ГЛАВНЫЙ МЕТОД ОБМЕНА ДАНЫМИ  НА АКТИВИТИ FACE_APP
                Log.d(this.getClass().getName(), " результатСинхрониазции " + результатСинхрониазции);
                if(результатСинхрониазции==null){
                    результатСинхрониазции=0;
                }
                Log.d(this.getClass().getName(), " результатСинхрониазции" + результатСинхрониазции);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(this.context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return    результатСинхрониазции;
        }













        // TODO: 13.10.2021 нАЧАЛО сИНХРОНИАЗЦИ
        Integer МетодПолучениеIDотСервераДляГеренированиеUUID() throws JSONException, InterruptedException, ExecutionException, TimeoutException {
            String ДанныеПришёлЛиIDДЛяГенерацииUUID = new String();
            Integer РезультатСинхрониазции=0;
            ID =0;
            try {
                Log.d(this.getClass().getName(), " public   void МетодПолучениеIDОтСервераДляГеренированиеUUID ()" + " ДанныеПришёлЛиIDДЛяГенерацииUUID "
                        + ДанныеПришёлЛиIDДЛяГенерацииUUID +
                        " ДанныеПришёлЛиIDДЛяГенерацииUUID.length()  " + ДанныеПришёлЛиIDДЛяГенерацииUUID.length());
                Class_GRUD_SQL_Operations   class_grud_sql_operationsПолучаемПубличныйIDЛокальноИеСЛИЕгоНЕтНАчинаемЕгоИСктьВНИтренете=new Class_GRUD_SQL_Operations(context);
                PUBLIC_CONTENT public_contentменеджер=new PUBLIC_CONTENT(context);
                class_grud_sql_operationsПолучаемПубличныйIDЛокальноИеСЛИЕгоНЕтНАчинаемЕгоИСктьВНИтренете.
                        concurrentHashMapНабор.put("НазваниеОбрабоатываемойТаблицы","SuccessLogin");
                class_grud_sql_operationsПолучаемПубличныйIDЛокальноИеСЛИЕгоНЕтНАчинаемЕгоИСктьВНИтренете.concurrentHashMapНабор.put("СтолбцыОбработки","id");
                class_grud_sql_operationsПолучаемПубличныйIDЛокальноИеСЛИЕгоНЕтНАчинаемЕгоИСктьВНИтренете.concurrentHashMapНабор.put("УсловиеЛимита","1");
                // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИ
                SQLiteCursor     Курсор_ВычисляемПУбличныйID= (SQLiteCursor)  class_grud_sql_operationsПолучаемПубличныйIDЛокальноИеСЛИЕгоНЕтНАчинаемЕгоИСктьВНИтренете.
                        new GetData(context).getdata(class_grud_sql_operationsПолучаемПубличныйIDЛокальноИеСЛИЕгоНЕтНАчинаемЕгоИСктьВНИтренете.
                        concurrentHashMapНабор,public_contentменеджер.МенеджерПотоков, sqLiteDatabase);
                Log.d(this.getClass().getName(), "GetData "+Курсор_ВычисляемПУбличныйID  );
                StringBuffer БуферПолучениеДанных = new StringBuffer();
                if (Курсор_ВычисляемПУбличныйID.getCount() > 0) {
                    Курсор_ВычисляемПУбличныйID.moveToFirst();
                    ID =Курсор_ВычисляемПУбличныйID.getInt(0);
                    Log.w(this.getClass().getName(), "  ID " + ID);
                }
                Log.w(this.getClass().getName(), "  ID  " + ID);
                Курсор_ВычисляемПУбличныйID.close();
                // TODO: 09.09.2022  запускаем синхрониазцию
                if (ID > 0) {
                    ////TODO создаем списко таблиц запускаем слуд.ющий метод получение версии базы данных
                    РезультатСинхрониазции = МетодПолучениеСпискаТаблицДляОбменаДанными(ID);//получаем ID для генерирования UUID
                    if (РезультатСинхрониазции == null) {
                        РезультатСинхрониазции = 0;
                    }
                    Log.d(this.getClass().getName(), " Результат  РезультатСинхрониазции  " + РезультатСинхрониазции);
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                // TODO: 01.09.2021 метод вызова
                new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return  РезультатСинхрониазции;
        }

        ///////////////////метод получение ОТ СЕРВЕРА КОНКРЕТНЫЙ СПИСОК ТАДОИЦЦ ДЛЯ ОБМЕНА

























        ////////////МЕТОД ПОЛУЧЕНИЕ  ВЕРСИИ ДАННЫХ
        Integer МетодПолучениеСпискаТаблицДляОбменаДанными(@NonNull Integer   ID)
                throws JSONException, InterruptedException, ExecutionException, TimeoutException {///второй метод получаем версию данных на СЕРВЕР ЧТОБЫ СОПОЧТАВИТЬ ДАТЫ
            Log.d(this.getClass().getName(), "   ID  ID" +   ID);
            String ДанныеПришлаСпискаТаблицДляОбмена = new String();
            StringBuffer BufferGetVersionData = new StringBuffer();
            final Integer[] РЕЗУЛЬТАТГЛАВНОЙСИНХРОНИАЗЦИИПОТАБЛИЦАМ = {0};
            try {
                preferences=   context .getSharedPreferences("sharedPreferencesХранилище", Context.MODE_MULTI_PROCESS);
                if (preferences==null) {
                    Boolean РезультатЕслиСвязьСерверомПередНачаломВизуальнойСинхронизцииПередСменыДанных =
                            new Class_Connections_Server(context).         МетодПингаСервераРаботаетИлиНет(context);
                    preferences=   context .getSharedPreferences("sharedPreferencesХранилище", Context.MODE_MULTI_PROCESS);
                }
                PUBLIC_CONTENT public_content=   new PUBLIC_CONTENT(context);
                String   ИмяСерверИзХранилица = preferences.getString("ИмяСервера","");
                Integer    ПортСерверИзХранилица = preferences.getInt("ИмяПорта",0);
                // TODO: 10.11.2022 Получение Список Таблиц
                BufferGetVersionData = МетодУниверсальныйСервернаяВерсияДанныхДанныесСервера(
                        "view_data_modification",
                        "application/gzip",
                        "Хотим Получить Версию Данных Сервера",
                        0l,
                        ID,
                        ИмяСерверИзХранилица ,
                        ПортСерверИзХранилица);
                Log.d(this.getClass().getName(), " BufferGetVersionData.toString().toCharArray().length "
                        + BufferGetVersionData.toString().toCharArray().length);
                // TODO: 03.09.2021
                if (BufferGetVersionData != null) {
                    if (BufferGetVersionData.toString().toCharArray().length > 3
                    && ! BufferGetVersionData.toString().matches("(.*)Server Running...... Don't Login(.*)")) {
                        Log.d(this.getClass().getName(), "  ID  " + this.ID +
                                " BufferGetVersionData " + BufferGetVersionData.toString());
                        //TODO БУфер JSON от Сервера
                        CopyOnWriteArrayList<Map<String, String>> БуферJsonОтСервераmodification_server = new PUBLIC_CONTENT(context).getGeneratorJackson().readValue(BufferGetVersionData.toString(),
                                new TypeReference<CopyOnWriteArrayList<Map<String, String>>>() {
                                });
                        ///упаковываем в j
                        Log.d(this.getClass().getName(), "  БуферJsonОтСервераmodification_server  " + БуферJsonОтСервераmodification_server);
                        public_contentДатыДляГлавныхТаблицСинхронизации.ВерсииВсехСерверныхТаблиц = new LinkedHashMap<String, Long>();
                        public_contentДатыДляГлавныхТаблицСинхронизации.ИменаТаблицыОтАндройда.clear();
                        // TODO: 09.08.2023  бежим по данным версии сервера
                        Flowable.fromIterable(БуферJsonОтСервераmodification_server)
                                .onBackpressureBuffer()
                                        .blockingIterable()
                                .forEach(new Consumer<Map<String, String>>() {
                                    @Override
                                    public void accept(Map<String, String> stringStringMap) {
                                        stringStringMap.forEach(new BiConsumer<String, String>() {
                                            @Override
                                            public void accept(String НазваниеТаблицыСервера, String ВерсияДанныхСервернойТаблицы) {
                                                if (НазваниеТаблицыСервера.equalsIgnoreCase("name")) {
                                                    public_contentДатыДляГлавныхТаблицСинхронизации.ИменаТаблицыОтАндройда.add(ВерсияДанныхСервернойТаблицы.trim());
                                                    НазваниеСервернойТаблицы =ВерсияДанныхСервернойТаблицы.trim();
                                                }
                                                if (НазваниеТаблицыСервера.equalsIgnoreCase("versionserverversion")) {
                                                    public_contentДатыДляГлавныхТаблицСинхронизации.ВерсииВсехСерверныхТаблиц.put(НазваниеСервернойТаблицы.trim(),
                                                            Long.valueOf(ВерсияДанныхСервернойТаблицы));

                                                }
                                                if (НазваниеТаблицыСервера.equalsIgnoreCase("versionserver")) {
                                                    // TODO: 09.08.2023  даты заполяем таблиц с серверар
                                                    public_contentДатыДляГлавныхТаблицСинхронизации.  ВерсииДатыСерверныхТаблиц.put(НазваниеСервернойТаблицы.trim(), ВерсияДанныхСервернойТаблицы);

                                                }



                                                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                                        + " НазваниеТаблицыСервера " + НазваниеТаблицыСервера + " ВерсияДанныхСервернойТаблицы " + ВерсияДанныхСервернойТаблицы+
                                                        " НазваниеСервернойТаблицы[0] " +НазваниеСервернойТаблицы);
                                            }
                                        });
                                    }
                                });
                 
                        РЕЗУЛЬТАТГЛАВНОЙСИНХРОНИАЗЦИИПОТАБЛИЦАМ[0] = МетодГлавныхЦиклТаблицДляСинхронизации(ID);

                        if (РЕЗУЛЬТАТГЛАВНОЙСИНХРОНИАЗЦИИПОТАБЛИЦАМ[0] == null) {
                            РЕЗУЛЬТАТГЛАВНОЙСИНХРОНИАЗЦИИПОТАБЛИЦАМ[0] = 0;
                        }
                        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                + " РЕЗУЛЬТАТГЛАВНОЙСИНХРОНИАЗЦИИПОТАБЛИЦАМ " + РЕЗУЛЬТАТГЛАВНОЙСИНХРОНИАЗЦИИПОТАБЛИЦАМ);

                        Log.i(this.getClass().getName(), " ИменаТаблицыОтАндройда "
                                + public_contentДатыДляГлавныхТаблицСинхронизации.ИменаТаблицыОтАндройда.toString() +
                                " ВерсииВсехСерверныхТаблиц " + public_contentДатыДляГлавныхТаблицСинхронизации.toString() +
                                "  ДанныеПришлаСпискаТаблицДляОбмена " + ДанныеПришлаСпискаТаблицДляОбмена);
                    }
                }else {
                    Log.i(this.getClass().getName(), " НЕт данных с сервера  BufferGetVersionData " + BufferGetVersionData );

                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            Log.i(this.getClass().getName(), " РЕЗУЛЬТАТГЛАВНОЙСИНХРОНИАЗЦИИПОТАБЛИЦАМ " + РЕЗУЛЬТАТГЛАВНОЙСИНХРОНИАЗЦИИПОТАБЛИЦАМ[0]);
            return РЕЗУЛЬТАТГЛАВНОЙСИНХРОНИАЗЦИИПОТАБЛИЦАМ[0];
        }

// TODO: 10.09.2021  запускаем метод обработки по таблицам

























        ////////////////////////////ДАННЫЙ МЕТОД ПОСЛЕ ВЫШЕ СТОЯШЕГО ВЫРАВНИЯНИЯ НАЗВАНИЙ ТАБЛИЦ ПРИСТУПАЕТ К САМОМУ АНАЛИЗУ ДАННЫХ ВЕРСИИ ДАННЫХ НАХОДЯЩИХСЯ НА АНДРОЙДЕ
        Integer МетодАнализаВресииДАнныхКлиента(String ИмяТаблицыОтАндройда_Локальноая,
                                                Long Полученная_ВерсияДанныхсSqlServer,
                                                Integer ID) {

            Log.d(this.getClass().getName(), " Полученная_ВерсияДанныхсSqlServer " +Полученная_ВерсияДанныхсSqlServer);
            SQLiteCursor КурсорДляАнализаВерсииДанныхАндройда = null;
            Long ВерсииДанныхНаАндройдеЛокальнаяЛокальная = 0l;
            Long ВерсииДанныхНаАндройдеСерверная = 0l;
            Integer РезультатУспешнойВсатвкиИлиОбвовлениясСервера=0;
            Class_GRUD_SQL_Operations class_grud_sql_operationsАнализаВресииДАнныхКлиента;
            try {
                class_grud_sql_operationsАнализаВресииДАнныхКлиента=new Class_GRUD_SQL_Operations(context);
                Class_GRUD_SQL_Operations.GetData class_grud_sql_operationsgetdata=class_grud_sql_operationsАнализаВресииДАнныхКлиента.new GetData(context);
                class_grud_sql_operationsАнализаВресииДАнныхКлиента.concurrentHashMapНабор.put("НазваниеОбрабоатываемойТаблицы","MODIFITATION_Client");
                class_grud_sql_operationsАнализаВресииДАнныхКлиента.concurrentHashMapНабор.put("СтолбцыОбработки","name,localversionandroid_version, versionserveraandroid_version");
                class_grud_sql_operationsАнализаВресииДАнныхКлиента.concurrentHashMapНабор.put("ФорматПосика","name=? ");
                ///"_id > ?   AND _id< ?"
                //////
                class_grud_sql_operationsАнализаВресииДАнныхКлиента.concurrentHashMapНабор.put("УсловиеПоиска1",ИмяТаблицыОтАндройда_Локальноая);
                // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ
                КурсорДляАнализаВерсииДанныхАндройда= (SQLiteCursor)  class_grud_sql_operationsgetdata
                        .getdata(class_grud_sql_operationsАнализаВресииДАнныхКлиента.concurrentHashMapНабор,new PUBLIC_CONTENT(context).МенеджерПотоков,
                                sqLiteDatabase);
                Log.d(this.getClass().getName(), "GetData "+КурсорДляАнализаВерсииДанныхАндройда  );
                /////
                if (КурсорДляАнализаВерсииДанныхАндройда.getCount() > 0) {////ВЫЖНОЕ УСЛОВИЕ ЕСЛИ КУРСОР ВЕРНУЛ БОЛЬШЕ НУЛЯ  ДАННАЕ ТОЛЬКО ТОГДА НАЧИНАЕМ АНАЛИЗ ВЕРСИИ ДАННЫХ НА АНДРОЙДЕ
                    КурсорДляАнализаВерсииДанныхАндройда.moveToFirst();
                    Log.d(this.getClass().getName(), "  Курсор_УзнаемВерсиюБазыНаАдройде.getCount() " + КурсорДляАнализаВерсииДанныхАндройда.getCount());
                    // TODO: 05.10.2021  получаем верию данных лолькано    --- локльную
                    ВерсииДанныхНаАндройдеЛокальнаяЛокальная = КурсорДляАнализаВерсииДанныхАндройда.getLong(КурсорДляАнализаВерсииДанныхАндройда.getColumnIndex("localversionandroid_version"));
                    Log.d(this.getClass().getName(), "   ВерсииДанныхНаАндройдеЛокальнаяЛокальная " + ВерсииДанныхНаАндройдеЛокальнаяЛокальная+" ИмяТаблицыОтАндройда_Локальноая " +ИмяТаблицыОтАндройда_Локальноая);
                    // TODO: 05.10.2021  получаем верию данных лолькано  - ерверную
                    ВерсииДанныхНаАндройдеСерверная = КурсорДляАнализаВерсииДанныхАндройда.getLong(КурсорДляАнализаВерсииДанныхАндройда.getColumnIndex("versionserveraandroid_version"));
                    Log.d(this.getClass().getName(), "   ВерсииДанныхНаАндройдеСерверная " +ВерсииДанныхНаАндройдеСерверная+"  ИмяТаблицыОтАндройда_Локальноая  "+ИмяТаблицыОтАндройда_Локальноая);
                    ///////////ОПРЕДЕЛЯЕМ ДАТУ АНДРОЙДА ДЛЯ СОСТЫКОВКИ С ДАТОЙ SQ; SERVER//// ПОЛУЧАЕМ ДАТУ НА АНДРОЙДЕ ПОЛСЕДНЕЕ ИЗМЕНЕНИЯ ПРИШЕДЩИЕ ДАННЫЕ С СЕРВЕРА
                } else {
                    Log.d(this.getClass().getName(), "  НЕт такой таблицы и нет Данных КурсорДляАнализаВерсииДанныхАндройда.getCount()" + КурсорДляАнализаВерсииДанныхАндройда.getCount());
                }
                // TODO: 06.07.2023 clear
                if(КурсорДляАнализаВерсииДанныхАндройда!=null){
                    // TODO: 06.07.2023 clear
                    КурсорДляАнализаВерсииДанныхАндройда.close();
                }
                // TODO: 05.10.2021  КОГДА ВСЕ ДАННЫЕ ЕСТЬ ТРИ ПЕРЕМЕННЫЕ ПОЛУЧЕНИЕ ПЕРЕХОИМ ДАЛЬШЕ ПОЛЯ ЛОКАЛЬНАЯ ВЕРСИЯ ДАННЫХ, СЕРВЕНАЯ ВЕРСИЯ ДАННЫХ, И ТЕРТЬЯ ВЕРИСЯ С СЕРВЕРА ПО ДАННОЙ ТАБЕЛИЦВ
                Log.d(this.getClass().getName(), "   ВерсииДанныхНаАндройдеСерверная " +ВерсииДанныхНаАндройдеСерверная+
                        "   ВерсииДанныхНаАндройдеЛокальнаяЛокальная " + ВерсииДанныхНаАндройдеЛокальнаяЛокальная
                        +"   Полученная_ВерсияДанныхсSqlServer " +Полученная_ВерсияДанныхсSqlServer);
                // TODO: 05.10.2021 ПРИ НАЛИЧИИ ВСЕХ ТРЕХ ПОЗИЦИЙ ЛОКАЛЬНАЯ ВЕРСИЯ С АНДРОЙДА   И СЕРВРНАЯ ВЕРСИЯ С АНДРОЙДА И  ПРИШЕДШЕЯ ВЕРСИЯ С СЕРВЕРА
                ///
                if (ВерсииДанныхНаАндройдеЛокальнаяЛокальная !=null  && ВерсииДанныхНаАндройдеСерверная!=null && Полученная_ВерсияДанныхсSqlServer!=null) {
                    //TODO СЛЕДУЮЩИЙ ЭТАМ РАБОТЫ ОПРЕДЕЛЯЕМ ЧТО МЫ ДЕЛАЕМ ПОЛУЧАЕМ ДАННЫЕ С СЕВРЕРА ИЛИ НА ОБОРОТ  ОТПРАВЛЯЕМ ДАННЫЕ НА СЕРВЕР
                    РезультатУспешнойВсатвкиИлиОбвовлениясСервера=       методПринятияРешенияПолучаемИлиОтправляемДанные(
                            Полученная_ВерсияДанныхсSqlServer,
                            ИмяТаблицыОтАндройда_Локальноая,
                            ID);///СЛЕДУЮЩИЙ ЭТАМ РАБОТЫ ОПРЕДЕЛЯЕМ ЧТО МЫ ДЕЛАЕМ ПОЛУЧАЕМ ДАННЫЕ С СЕВРЕРА ИЛИ НА ОБОРОТ  ОТПРАВЛЯЕМ ДАННЫЕ НА СЕРВЕР
                    Log.d(this.getClass().getName(), "   РезультатУспешнойВсатвкиИлиОбвовлениясСервера " +РезультатУспешнойВсатвкиИлиОбвовлениясСервера);
                }else{

                    // TODO: 15.02.2022  НЕТ ДАННЫ Х ДЛЯ ОДМЕНА ПО ТАБЛИЦЫЕ ТЕКУЩЕЙ

                    new Handler(     context.getMainLooper()).post(()->{

                        Toast.makeText(context, "Нет данных для обмена текущие таблицы:  "+ИмяТаблицыОтАндройда_Локальноая , Toast.LENGTH_LONG).show();


                    });

                    Log.e(this.getClass().getName(), "   Нет данных для обмена текущие таблицы " +ИмяТаблицыОтАндройда_Локальноая);


                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                // TODO: 01.09.2021 метод вызова
                new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                ////// начало запись в файл

            }
            return  РезультатУспешнойВсатвкиИлиОбвовлениясСервера;
        }














        //TODO СЛЕДУЮЩИЙ ЭТАМ РАБОТЫ ОПРЕДЕЛЯЕМ ЧТО МЫ ДЕЛАЕМ ПОЛУЧАЕМ ДАННЫЕ С СЕВРЕРА ИЛИ НА ОБОРОТ  ОТПРАВЛЯЕМ ДАННЫЕ НА СЕРВЕР
        Integer методПринятияРешенияПолучаемИлиОтправляемДанные(Long ВерсияДанныхсСамогоSqlServer,
                                                                String ИмяТаблицыОтАндройда_Локальноая,
                                                                Integer ID) {
           Integer РезультатОтправкиПолучниеДанныхНаСервер=0;
            try {
                    Log.d(this.getClass().getName(), " ФлагУказываетЧтоТОлькоОбработкаТаблицДляЧАТА"
                            + ФлагУказываетЧтоТОлькоОбработкаТаблицДляЧАТА
                            + " ИмяТаблицыОтАндройда_Локальноая " + ИмяТаблицыОтАндройда_Локальноая);
                    // TODO: 12.08.2021 СЕРВЕРАНАЯ ДАТА ЛОКАЛЬНАЯ
                    Long    ВерсияДанныхЛокальнаяСерверная =
                            МетодПолученияЛокальнойВерсииДаныхЧатаДляОтправкиЕгоНАСервер("MODIFITATION_Client", "versionserveraandroid_version",
                                    context, ИмяТаблицыОтАндройда_Локальноая);
                    ВерсияДанныхЛокальнаяСерверная=      Optional.ofNullable(ВерсияДанныхЛокальнаяСерверная).map(Long::new).orElse(0l);
                    Log.d(this.getClass().getName(),
                            " ВерсияДанныхЛокальнаяСерверная" + ВерсияДанныхЛокальнаяСерверная);
                    // TODO: 12.08.2021 ЛОКАЛЬНАЯ ДАТА ЛОКАЛЬНАЯ
                    Long          ВерсияДанныхЛокальноЛокальная =
                            МетодПолученияЛокальнойВерсииДаныхЧатаДляОтправкиЕгоНАСервер("MODIFITATION_Client", "localversionandroid_version",
                                    context, ИмяТаблицыОтАндройда_Локальноая);
                    ВерсияДанныхЛокальноЛокальная=      Optional.ofNullable(ВерсияДанныхЛокальноЛокальная).map(Long::new).orElse(0l);
                    Log.d(this.getClass().getName(), " РезультаПолученаяЛокальнаяВерсияДанныхКогдаПользовательСоздалНовыеДаннее"
                                    + ВерсияДанныхЛокальноЛокальная+ " ИмяТаблицыОтАндройда_Локальноая " +ИмяТаблицыОтАндройда_Локальноая);

                    // TODO: 17.03.2023 Вресия Данных с Самого Сервера
                    ВерсияДанныхсСамогоSqlServer =      Optional.ofNullable(ВерсияДанныхсСамогоSqlServer).map(Long::new).orElse(0l);
                    Log.d(this.getClass().getName(), " ВерсияДанныхсСамогоSqlServer" + ВерсияДанныхсСамогоSqlServer);
                    // TODO: 05.10.2021  POST()-->
                    if (ВерсияДанныхЛокальноЛокальная > ВерсияДанныхЛокальнаяСерверная ) {
                        Log.d(this.getClass().getName(),
                                " ВерсияДанныхЛокальноЛокальная  " + ВерсияДанныхЛокальноЛокальная +
                                        "  ВерсияДанныхЛокальнаяСерверная " + ВерсияДанныхЛокальнаяСерверная
                                        + " ФлагКакуюЧастьСинхронизацииЗапускаем " + ФлагКакуюЧастьСинхронизацииЗапускаем+ " ИмяТаблицыОтАндройда_Локальноая "
                                        +ИмяТаблицыОтАндройда_Локальноая);
                        // TODO: 30.06.2022  конец встаялеммого кода с задержкой
                        Integer    ДанныеПосылаемНаСервер = МетодОбменаЗаданиеДляСервера_ПосылаемНа_Сервер(ИмяТаблицыОтАндройда_Локальноая,
                         ВерсияДанныхЛокальнаяСерверная);

                        // TODO: 28.10.2021 ПЕРЕРДАЕМ ВОЗМОЖНЫЙ ОТВЕТ
                        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                                " ВерсияДанныхсСамогоSqlServer  " + ВерсияДанныхсСамогоSqlServer +
                                "  ВерсияДанныхЛокальнаяСерверная "
                                + ВерсияДанныхЛокальнаяСерверная
                                + " ФлагКакуюЧастьСинхронизацииЗапускаем " + ФлагКакуюЧастьСинхронизацииЗапускаем+
                                "  ДанныеПосылаемНаСервер " +ДанныеПосылаемНаСервер);


                        if (ДанныеПосылаемНаСервер>0) {
                            // TODO: 01.07.2023 После Успешно Посылании Данных На Сервер Повышаем Верисю Данных
                            методПослеОтпаркиУспешноНаСерверПовышаемВерсию(ИмяТаблицыОтАндройда_Локальноая );
                            // TODO: 16.07.2023 Результат
                            РезультатОтправкиПолучниеДанныхНаСервер=ДанныеПосылаемНаСервер;
                        }


                        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                                " ВерсияДанныхсСамогоSqlServer  " + ВерсияДанныхсСамогоSqlServer +
                                "  ВерсияДанныхЛокальнаяСерверная "
                                + ВерсияДанныхЛокальнаяСерверная+
                                "  ДанныеПосылаемНаСервер " +ДанныеПосылаемНаСервер);
                        // TODO: 28.10.2021 ПЕРЕРДАЕМ ВОЗМОЖНЫЙ ОТВЕТ
                    } else {
                        // TODO: 19.10.2021   GET()->
                        if (Math.abs(ВерсияДанныхсСамогоSqlServer-ВерсияДанныхЛокальнаяСерверная)>=1) {
                            // TODO: 05.10.2021  ДЕЙСТИВЕ ВТОРОЕ ПОЛУЧАЕМ ДАННЫЕ ОТ СЕРВЕРА ДЛЯ ТЕКЦЩЕЙ ТАБЛИЦЫ
                            Integer         ДанныесСервера =
                                    МетодОбменаЗаданиеСервера_сервераПолучаем_Сервер(ВерсияДанныхЛокальноЛокальная,
                                    ИмяТаблицыОтАндройда_Локальноая, ID);
                            if (ДанныесСервера>0) {
                                // TODO: 01.07.2023 После Успешно Посылании Данных На Сервер Повышаем Верисю Данных
                                методПослеОтпаркиУспешноНаСерверПовышаемВерсию(ИмяТаблицыОтАндройда_Локальноая );
                                // TODO: 16.07.2023 Результат
                                РезультатОтправкиПолучниеДанныхНаСервер=ДанныесСервера;
                            }
                            // TODO: 28.10.2021 ПЕРЕРДАЕМ ВОЗМОЖНЫЙ ОТВЕТ
                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                                    " ВерсияДанныхсСамогоSqlServer  " + ВерсияДанныхсСамогоSqlServer +
                                    "  ВерсияДанныхЛокальнаяСерверная "
                                    + ВерсияДанныхЛокальнаяСерверная
                                    + " ФлагКакуюЧастьСинхронизацииЗапускаем " + ФлагКакуюЧастьСинхронизацииЗапускаем+
                                    "  ДанныесСервера " +ДанныесСервера);

                        }
                    }
                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                        " ВерсияДанныхсСамогоSqlServer  " + ВерсияДанныхсСамогоSqlServer
                        + " ФлагКакуюЧастьСинхронизацииЗапускаем " + ФлагКакуюЧастьСинхронизацииЗапускаем);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return  РезультатОтправкиПолучниеДанныхНаСервер;
        }

        private void методПослеОтпаркиУспешноНаСерверПовышаемВерсию(@NonNull String ИмяТаблицыОтАндройда_Локальноая) {
            try{
                // TODO: 19.11.2022 ПОДНИМАЕМ ВЕРИСЮ ДАННЫХ
              Integer РезультатПовышенииВерсииДанных =
                      new SubClassUpVersionDATA().МетодVesrionUPMODIFITATION_Client(ИмяТаблицыОтАндройда_Локальноая,context);
                Log.d(this.getClass().getName(), " РезультатПовышенииВерсииДанных  " + РезультатПовышенииВерсииДанных);

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                    " ВерсияДанныхсСамогоSqlServer  ");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        }


        private Integer МетодОбменаЗаданиеДляСервера_ПосылаемНа_Сервер(String ИмяТаблицыОтАндройда_Локальноая, Long ВерсияДанныхПришлаПослеУспешнойСинхронизации) {
            Integer РезультатОтправкиДанныхНаСервер=0;
            try{
                Log.d(this.getClass().getName(), "  ВерсияДанныхПришлаПослеУспешнойСинхронизации   " + ВерсияДанныхПришлаПослеУспешнойСинхронизации );
                ////// todo МЕТОД POST() в фоне    ////// todo МЕТОД POST
                РезультатОтправкиДанныхНаСервер =
                        МетодПосылаемДанныеНаСервервФоне(ИмяТаблицыОтАндройда_Локальноая, ВерсияДанныхПришлаПослеУспешнойСинхронизации);
                Log.i(this.getClass().getName(), "   РезультатОтправкиДанныхНаСервер" + РезультатОтправкиДанныхНаСервер+
                        " ВерсияДанныхПришлаПослеУспешнойСинхронизации "+ВерсияДанныхПришлаПослеУспешнойСинхронизации);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return РезультатОтправкиДанныхНаСервер;
        }

        @NonNull
        private Integer МетодОбменаЗаданиеСервера_сервераПолучаем_Сервер(@NonNull  Long ВерсияДанных,
                                                                         @NonNull String ИмяТаблицы,
                                                                         @NonNull  Integer ID) {
            Integer  РезультатДанныесСервера=0;
            try{
                Log.d(this.getClass().getName(), " ВерсияДанных" + ВерсияДанных+" ID "   + ID + "ИмяТаблицы"  + ИмяТаблицы);

                //////////TODO МЕТОД get
                  РезультатДанныесСервера =
                        МетодПолучаемДаннныесСервера(ИмяТаблицы,
                                ID,
                                ВерсияДанных );

                Log.d(this.getClass().getName(),  "РезультатДанныесСервера" + РезультатДанныесСервера);

            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return РезультатДанныесСервера;
        }

        // TODO: 19.08.2021   КОнец Класс ВЫЧИСЛЯЕТ ЕЩЕ НЕ ОТРРВЛЕННЫЕ СООБЩЕНИЯ НА СЕРВЕР ИЗ ЧАТА
        /////МЕТОД КОГДА НА СЕРВЕРЕ ВЕРСИЯ ДАННЫХ ВЫШЕ И МЫ ПОЛУЧАЕМ ДАННЫЕ С СЕРВРА
        Integer МетодПолучаемДаннныесСервера(String ИмяТаблицы,
                                              Integer ID
                                              ,Long  ВерсияДанных) {

            Integer РезультатФоновнойСинхронизации=0;
            StringBuffer BufferGetData = null;
            try {
                Log.d(this.getClass().getName(), "  МетодПолучаемДаннныесСервера" + "  имяТаблицыОтАндройда_локальноая" + ИмяТаблицы);
                // TODO: 02.05.2023  Ответ Обратно ПрограссБару
                    PUBLIC_CONTENT public_content=   new PUBLIC_CONTENT(context);
                    String   ИмяСерверИзХранилица = preferences.getString("ИмяСервера","");
                    Integer    ПортСерверИзХранилица = preferences.getInt("ИмяПорта",0);
                    // TODO: 10.11.2022  Получение JSON-потока
                BufferGetData = МетодУниверсальныйДанныесСервера(
                            ИмяТаблицы,
                          "application/gzip",
                            "Хотим Получить  JSON"
                            ,ВерсияДанных,
                            ID,
                            ИмяСерверИзХранилица
                            ,ПортСерверИзХранилица);
                    Log.d(this.getClass().getName(), "  БУФЕР получаем даннные BufferGetData.toString() " + BufferGetData.toString());
                    if(BufferGetData==null){
                        BufferGetData = new StringBuffer();
                    }
                    Log.d(this.getClass().getName(), "  BufferGetData.length()" + BufferGetData.length());

                if ( BufferGetData.toString().toCharArray().length > 3) {
                    Log.d(this.getClass().getName(), "  BufferGetData.toString()) " + BufferGetData.toString() + " ИмяТаблицы " +ИмяТаблицы);

                    //////TODO запускаем метод распарстивая JSON
                    РезультатФоновнойСинхронизации=        МетодПарсингJSONФайлаОтСервреравФоне(BufferGetData, ИмяТаблицы);
                    Log.i(this.getClass().getName(), " РезультатФоновнойСинхронизации  "  +РезультатФоновнойСинхронизации);
                } else {////ОШИБКА В ПОЛУЧЕНИИ С СЕРВЕРА ТАБЛИУЦЫ МОДИФИКАЦИИ ДАННЫХ СЕРВЕРА
                    Log.d(this.getClass().getName(), "  BufferGetData.toString()) " + BufferGetData.toString() + " ИмяТаблицы " +ИмяТаблицы);
                }
                Log.i(this.getClass().getName(), " РезультатФоновнойСинхронизации "+РезультатФоновнойСинхронизации);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                ////// начало запись в файл
            }
            return РезультатФоновнойСинхронизации;
        }
        /////// TODO МЕТОД ПАСРИНГА ПРИШЕДШЕГО  С СЕРВЕРА ВНУТРИ ASYNSTASK В ФОНЕ
        Integer МетодПарсингJSONФайлаОтСервреравФоне(@NonNull  StringBuffer БуферПолученныйJSON,
                                                   @NonNull  String имяТаблицаAsync) throws InterruptedException, JSONException {
            // TODO: 05.07.2023 result suync
            Integer РезультСинхрониазции=0;
            try {
                Log.d(this.getClass().getName(), " имяТаблицаAsync " + имяТаблицаAsync + " БуферПолученныйJSON " +БуферПолученныйJSON.toString() );
                    //TODO БУфер JSON от Сервера
                  /*   TypeReference< CopyOnWriteArrayList<Map<String,String>>> typeReference=   new TypeReference< CopyOnWriteArrayList<Map<String,String>>>() {};
               CopyOnWriteArrayList<Map<String,String>> jsonNodeParentMAP= jsonGenerator.readValue(БуферПолученныйJSON.toString(), typeReference);*/
               /*     CopyOnWriteArrayList<Organization>  jsonNodeParentMAP =
                        jsonGenerator.readValue(БуферПолученныйJSON.toString(), new TypeReference<CopyOnWriteArrayList<Organization>>() {});*/
                 /* SimpleModule module = new SimpleModule();
                module.addDeserializer(  Organization .class , new GeneratorJSONDeserializer(context));
                jsonGenerator.registerModule(module);*/
                ObjectMapper jsonGenerator = new PUBLIC_CONTENT(context).getGeneratorJackson();
                JsonNode jsonNodeParentMAP=   jsonGenerator.readTree(БуферПолученныйJSON.toString());

                Log.d(this.getClass().getName(),"\n" + " class " +
                        Thread.currentThread().getStackTrace()[2].getClassName()
                        + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                        " БуферПолученныйJSON " +БуферПолученныйJSON + " jsonNodeParentMAP " +jsonNodeParentMAP);
                // TODO: 26.03.2023  Количество Максимальное СТРОК
              //  МаксималноеКоличествоСтрочекJSON = jsonNodeParentMAP.size();
                // TODO: 11.10.2022 callback метод обратно в актвити #1
                ИндексВизуальнойДляPrograssBar=0;

                Uri uri = Uri.parse("content://com.dsy.dsu.providerdatabasemirrorbinary/" + имяТаблицаAsync + "");

                ContentResolver resolver = context.getContentResolver();
                Bundle bundle=new Bundle();
                bundle.putSerializable("jsonNodeParentMAP", (Serializable) jsonNodeParentMAP);
                bundle.putString("nametable",имяТаблицаAsync);




                Проценты = new Class_Visible_Processing_Async(context).ГенерируемПРОЦЕНТЫДляAsync(ПозицияТекущейТаблицы, ГлавныеТаблицыСинхронизации.size());

                методCallBackPrograssBars(7, Проценты,имяТаблицаAsync,ПозицияТекущейТаблицы);


                Bundle bundleРезультатОбновлениеМассовой =resolver.call(uri,имяТаблицаAsync,БуферПолученныйJSON.toString(),bundle);

                РезультСинхрониазции=bundleРезультатОбновлениеМассовой.getInt("completeasync",0)   ;


                Log.d(this.getClass().getName(),"\n" + " class " +
                        Thread.currentThread().getStackTrace()[2].getClassName()
                        + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                        " РезультСинхрониазции " + РезультСинхрониазции);

                // TODO: 01.05.2023 clear
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return   РезультСинхрониазции;
        }

















//todo МЕТОД ВИЗУАЛЬНОГО ОТВЕТА ИЗ СЛУЖБЫ ОБРАБТНО В activity async
        private void методCallBackPrograssBars(@NonNull  Integer CurrentProssesing,
                                               @NonNull  int Проценны
                                               ,@NonNull String имяТаблицаAsync,
                                               @NonNull Integer ПозицияТекущейТаблицы)  {
            try {
                if (handlerAsync!=null) {
                    Bundle bundleCallsBackAsync=new Bundle();
                    bundleCallsBackAsync.putInt("Проценны" ,Проценны);
                    bundleCallsBackAsync.putString("имятаблицы" ,имяТаблицаAsync);
                    bundleCallsBackAsync.putInt("maxtables" ,public_contentДатыДляГлавныхТаблицСинхронизации.ИменаТаблицыОтАндройда.size());
                    bundleCallsBackAsync.putInt("currentposition" ,ПозицияТекущейТаблицы);

                    Message message = handlerAsync.obtainMessage();
                    message.what=CurrentProssesing;
                    message.setData(bundleCallsBackAsync);
                    message.sendToTarget();
                }
                Log.d(this.getClass().getName(), "\n" + " class " +
                            Thread.currentThread().getStackTrace()[2].getClassName()
                            + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                            + " handlerAsync " +handlerAsync);
            } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        }





        ///----------- ТУТ КОД УЖЕ ПОСЫЛАНИЕ ДАННЫХ НА СЕРВЕР МЕТОДУ POST (данные андройда посылаються на сервер)


        /////todo POST МЕТОД КОГДА НА АНДРОЙДЕ ВЕРСИЯ ДАННЫХ ВЫШЕ ЧЕМ НА СЕРВРЕР И МЫ  JSON ФАЙЛ ТУДА МЕТОД POST
        Integer МетодПосылаемДанныеНаСервервФоне(String имяТаблицыОтАндройда_локальноая,
                                                 Long ВерсияДанныхОсноваСозданиеДанныхОтправки) {

            Integer РезультатОтправкиДанныхНасервер=0;
            try {
                // TODO: 15.02.2022  ДАННЫЕ ДЛЯ ОТПРАВКИ НА СЕРВЕР 
            Cursor   cursorForSendServer= методГлавныйGetDataForAsync(имяТаблицыОтАндройда_локальноая ,ВерсияДанныхОсноваСозданиеДанныхОтправки );
                /////TODO результаты   количество отправляемой информации на сервера
                if (cursorForSendServer!=null && cursorForSendServer.getCount() > 0) {
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                            + " cursorForSendServer "+cursorForSendServer.getCount() );

                    //////// todo упаковываем в  json ПЕРЕХОДИМ НА СЛЕДУЩИМ МЕТОД для отрправки на сервер метод POST() POST() POST() POST() POST() POST()POST()
                        РезультатОтправкиДанныхНасервер = МетодГенерацииJSON(cursorForSendServer, имяТаблицыОтАндройда_локальноая );
                    // TODO: 04.08.2023  
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                            + " РезультатОтправкиДанныхНасервер "+РезультатОтправкиДанныхНасервер );

                }
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " РезультатОтправкиДанныхНасервер "+РезультатОтправкиДанныхНасервер );
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return  РезультатОтправкиДанныхНасервер;
        }


        // TODO: 15.02.2022 синхрогниазции таблиц
        @NonNull
        private Cursor методГлавныйGetDataForAsync( @NonNull  String имяТаблицыОтАндройда_локальноая,
                                                                      @NonNull Long ВерсияДанныхДляСравения) {
            Cursor  cursor=null;
            try{
                ПубличныйIDДляФрагмента = new Class_Generations_PUBLIC_CURRENT_ID().ПолучениеПубличногоТекущегоПользователяID(context);

                Uri uri = Uri.parse("content://com.dsy.dsu.providerdatabaseonlyasync/" + имяТаблицыОтАндройда_локальноая.trim() + "");
                ContentResolver resolver = context.getContentResolver();
                Bundle data=null;

                switch (имяТаблицыОтАндройда_локальноая.trim()) {
                    // TODO: 23.03.2023 ТАБЛИЦЫ С ПОЛЕМ ____ID    // TODO: 23.03.2023 ТАБЛИЦЫ С ПОЛЕМ ____ID    // TODO: 23.03.2023 ТАБЛИЦЫ С ПОЛЕМ ____ID
                    case "settings_tabels":
                        data=new Bundle();
                        data.putString("query","" +
                                "  SELECT DISTINCT  * FROM " +имяТаблицыОтАндройда_локальноая+"   as gett " +
                                "  WHERE   gett.current_table > "+ВерсияДанныхДляСравения+" AND gett. onesignal IS NOT NULL " +
                                " AND gett.user_update IN ( SELECT DISTINCT getin.user_update " +
                                "  FROM " +имяТаблицыОтАндройда_локальноая+" as getin " +
                                " WHERE   getin.user_update="+ПубличныйIDДляФрагмента+" ) "+"" );

                        Log.d(this.getClass().getName(), " имяТаблицыОтАндройда_локальноая Все остальные  _id " + имяТаблицыОтАндройда_локальноая);
                        break;
                    case "chat_users":
                    case "view_onesignal":
                    case "prof":
                        data=new Bundle();
                       /* data.putString("query"," SELECT DISTINCT  * FROM " +имяТаблицыОтАндройда_локальноая+" as gett" +
                                " WHERE   gett.current_table >  "+ВерсияДанныхДляСравения+"" );
*/
                        Log.d(this.getClass().getName(), " имяТаблицыОтАндройда_локальноая Все остальные  _id " + имяТаблицыОтАндройда_локальноая);
                        break;
                    case "data_notification":
                        data=new Bundle();
                        data.putString("query"," SELECT DISTINCT  * FROM " +имяТаблицыОтАндройда_локальноая+" as gett" +
                                " WHERE   gett.current_table >  "+ВерсияДанныхДляСравения+"" );
                        Log.d(this.getClass().getName(), " имяТаблицыОтАндройда_локальноая Все остальные  _id " + имяТаблицыОтАндройда_локальноая);
                        break;
                    // TODO: 23.03.2023 ТАБЛИЦЫ С ПОЛЕМ ID   // TODO: 23.03.2023 ТАБЛИЦЫ С ПОЛЕМ ID // TODO: 23.03.2023 ТАБЛИЦЫ С ПОЛЕМ ID // TODO: 23.03.2023 ТАБЛИЦЫ С ПОЛЕМ ID
                    // TODO: 23.03.2023 ТАБЛИЦЫ С ПОЛЕМ ID // TODO: 23.03.2023 ТАБЛИЦЫ С ПОЛЕМ ID // TODO: 23.03.2023 ТАБЛИЦЫ С ПОЛЕМ ID // TODO: 23.03.2023 ТАБЛИЦЫ С ПОЛЕМ ID
                    default:
                        data=new Bundle();
                        data.putString("query"," SELECT DISTINCT  * FROM " +имяТаблицыОтАндройда_локальноая+" as gett" +
                                " WHERE   gett.current_table >  "+ВерсияДанныхДляСравения+
                                " AND gett.user_update IN ( SELECT DISTINCT getin.user_update " +
                                " FROM get_materials_data as getin " +
                                "WHERE   getin.user_update="+ПубличныйIDДляФрагмента+" ) "+"" );
                        break;
                }
                // TODO: 08.08.2023 ГЛАВНОЕ ПОЛУЧЕНИЕ ДАННЫХ  ДЛя ОТПРАВКИ НА СЕРВЕР
                // TODO: 16.05.2023
                if (data.size()>0) {
                    cursor = resolver.query(uri,new String[]{"*"},data,null);// TODO: 13.10.2022 ,"Удаленная"
                }
                Log.d(this.getClass().getName(), "cursor   " + cursor  + "  имяТаблицыОтАндройда_локальноая " +имяТаблицыОтАндройда_локальноая
                + " data.size() " +data.size());

            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                // TODO: 01.09.2021 метод вызова
                new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return cursor;
        }

        private Integer getInteger(String имяТаблицыОтАндройда_локальноая, Long РезультаПолученаяЛокальнаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера,
                                   Class_GRUD_SQL_Operations class_grud_sql_operationsПосылаемДанныеНаСервервФоне) {
            Integer ПубличныйIDДляФрагмента;
            // TODO: 11.01.2022 ПУБЛИЧНЫЙ ID ТЕКУЩЕГО ПОЛЬЗОВТЕЛЯ

//////TODO dверсия данных для ВСЕХ ТАБЛИЦ КРОМЕ , ТАБЛИЦ ЧАТА
            class_grud_sql_operationsПосылаемДанныеНаСервервФоне.
                    concurrentHashMapНабор.put("УсловиеПоиска1", РезультаПолученаяЛокальнаяСервернуюВерсиюДанныхКогдаПоследнийРазПришлиДанныесСерера);

            //////TODO dверсия данных для ВСЕХ ТАБЛИЦ КРОМЕ , ТАБЛИЦ ЧАТА  old version

            ПубличныйIDДляФрагмента = new Class_Generations_PUBLIC_CURRENT_ID().ПолучениеПубличногоТекущегоПользователяID(context);

            Log.d(this.getClass().getName(), " имяТаблицыОтАндройда_локальноая  Для Чата с _id " + имяТаблицыОтАндройда_локальноая);

            //////TODO dверсия данных для ВСЕХ ТАБЛИЦ КРОМЕ , ТАБЛИЦ ЧАТА
            class_grud_sql_operationsПосылаемДанныеНаСервервФоне.
                    concurrentHashMapНабор.put("УсловиеПоиска2",ПубличныйIDДляФрагмента);
            return ПубличныйIDДляФрагмента;
        }


        // TODO: 04.11.2021  метод ПОСЫЛАЕМ ТОЛЬКО NULL В ПОЛЕ ID  НА СЕРВЕР






















        ////////TODO     МЕТОД ГЕНЕРИРОУЕМ JSON ПОЛЯ НА ОСНОВАНИЕ НАШИХ ДАННЫХ ДЛЯ ПОСЛЕДЖУЮЩЕ ОТПРАВКИ  POST()->
        ////////TODO     МЕТОД ГЕНЕРИРОУЕМ JSON ПОЛЯ НА ОСНОВАНИЕ НАШИХ ДАННЫХ ДЛЯ ПОСЛЕДЖУЮЩЕ ОТПРАВКИ  POST()->
        ////////TODO     МЕТОД ГЕНЕРИРОУЕМ JSON ПОЛЯ НА ОСНОВАНИЕ НАШИХ ДАННЫХ ДЛЯ ПОСЛЕДЖУЮЩЕ ОТПРАВКИ  POST()->

        ////////TODO     МЕТОД ГЕНЕРИРОУЕМ JSON ПОЛЯ НА ОСНОВАНИЕ НАШИХ ДАННЫХ ДЛЯ ПОСЛЕДЖУЮЩЕ ОТПРАВКИ  POST()->
        ////////TODO     МЕТОД ГЕНЕРИРОУЕМ JSON ПОЛЯ НА ОСНОВАНИЕ НАШИХ ДАННЫХ ДЛЯ ПОСЛЕДЖУЮЩЕ ОТПРАВКИ  POST()->
        ////////TODO     МЕТОД ГЕНЕРИРОУЕМ JSON ПОЛЯ НА ОСНОВАНИЕ НАШИХ ДАННЫХ ДЛЯ ПОСЛЕДЖУЮЩЕ ОТПРАВКИ  POST()->

        Integer МетодГенерацииJSON(@NonNull  Cursor КурсорДляОтправкиДанныхНаСерверОтАндройда,
                                   @NonNull String имяТаблицыОтАндройда_локальноая) {
            Integer РезультатОтветаОтСервреУспешнаяВставкаИлиОбновления = 0;
            try {
                if (КурсорДляОтправкиДанныхНаСерверОтАндройда.getCount()>0) {
                    КурсорДляОтправкиДанныхНаСерверОтАндройда.moveToFirst();
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " КурсорДляОтправкиДанныхНаСерверОтАндройда "+КурсорДляОтправкиДанныхНаСерверОтАндройда.getCount() );
                    StringWriter stringWriterJSONAndroid=    new StringWriter();
                    ObjectMapper jsonGenerator = new PUBLIC_CONTENT(context).getGeneratorJackson();
                    SimpleModule module = new SimpleModule();
                    module.addSerializer(Cursor.class, new GeneratorJSONSerializer(context));
                    jsonGenerator.registerModule(module);
                    jsonGenerator.getFactory().createGenerator( stringWriterJSONAndroid).useDefaultPrettyPrinter();
                 String BufferJsonForSendServer=  jsonGenerator.writeValueAsString(КурсорДляОтправкиДанныхНаСерверОтАндройда);
                    // TODO: 23.03.2023 ID ПРОФЕСИИ
                    КурсорДляОтправкиДанныхНаСерверОтАндройда.close();
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                            + " BufferJsonForSendServer"+BufferJsonForSendServer );

                    // TODO: 14.03.2023 ПОСЫЛАЕМ ДАННЫЕ СГЕНЕРИРОНГО JSON НА СЕРВЕР ---->SERVER
                    РезультатОтветаОтСервреУспешнаяВставкаИлиОбновления = new SubClass_SendToServer(context)
                                    .МетодПосылаетНаСерверСозданныйJSONФайлвФоне(BufferJsonForSendServer, имяТаблицыОтАндройда_локальноая ); ////СГЕНЕРИРОВАНЫЙ JSON ФАЙЛ ЕСЛИ БОЛЬШЕ 2 ССИМВОЛОМ В НЕМ ТО ОТПРАВЛЯЕМ
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                            + " РезультатОтветаОтСервреУспешнаяВставкаИлиОбновления " +РезультатОтветаОтСервреУспешнаяВставкаИлиОбновления );
                }else{
                    Log.d(this.getClass().getName(), " НЕ т данных  "+"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                            + " РезультатОтветаОтСервреУспешнаяВставкаИлиОбновления " +РезультатОтветаОтСервреУспешнаяВставкаИлиОбновления +
                             " КурсорДляОтправкиДанныхНаСерверОтАндройда " +КурсорДляОтправкиДанныхНаСерверОтАндройда.getCount());
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return  РезультатОтветаОтСервреУспешнаяВставкаИлиОбновления;
        }





        //////ТУТ БУДЕТ ЗАПИСЫВАТЬСЯ УСПЕШНОЕ ОБНЛВДЕНИ И ВСТАВКИ ДАННЫХ НА СЕРВЕРЕ ДЛЯ КЛИЕНТА


        /////TODO ЛОКАЛЬНАЯ ОБНОВЛЕНИЕ ВНУТРИ ТАБЕЛЯ
        public Long МетодЛокальноеОбновлениеВТабеле(ContentValues КонтейнерЗаполненияДаннымиПриЛокальномОбновлении,
                                                    String ПолучениеЗначениеСтолбикUUID,
                                                    Context КонтексДляЛокальногоОбновления,
                                                    String таблицаДляЛокальногоОбонвления) throws InterruptedException, ExecutionException, TimeoutException {
            Integer результатОбновлениеЧерезКонтрейнер = 0;
            try {
                ///////TODO САМ ВЫЗОВ МЕТОДА ОБНОВЛЕНИЕ ЛОКАЛЬНОГО обновление uuid
                результатОбновлениеЧерезКонтрейнер = ЛокальногоОбновлениеДанныхЧерезКонтейнерУниверсальная(таблицаДляЛокальногоОбонвления,
                                КонтейнерЗаполненияДаннымиПриЛокальномОбновлении,
                                Long.parseLong(ПолучениеЗначениеСтолбикUUID), "uuid");
                Log.d(this.getClass().getName(),
                        "  результатОбновлениеЧерезКонтрейнер[0] " + результатОбновлениеЧерезКонтрейнер);

            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName()
                        + " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                // TODO: 01.09.2021 метод вызова
                new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return Long.parseLong(String.valueOf(результатОбновлениеЧерезКонтрейнер));//5,TimeUnit.SECONDS

        }

//todo  ПОД КЛАСС  С ГЛАВНМ ЦИКЛОМ ОБМЕНА ДАННЫМИ ТАБЛИ
            Integer МетодГлавныхЦиклТаблицДляСинхронизации(@NonNull Integer ID)
                    throws ExecutionException, InterruptedException {//КонтекстСинхроДляКонтроллера
                 ЛистТаблицыОбмена = new CopyOnWriteArrayList<>();
                try {
                    Log.i(this.getClass().getName(), " ИменаТаблицыОтАндройда "
                            + public_contentДатыДляГлавныхТаблицСинхронизации.ИменаТаблицыОтАндройда.toString()
                            + " ВерсииВсехСерверныхТаблиц "
                            + public_contentДатыДляГлавныхТаблицСинхронизации.ВерсииВсехСерверныхТаблиц.toString());


// TODO: 21.08.2023 ГЛАВНЫЙ ЦИКЛ СИХРОНИАЗЦИИ--многопоточный
                    РежимПерваяЗапускПослеPasswordИлиПовторная = preferences.getString("РежимЗапускаСинхронизации","СамыйПервыйЗапускСинхронизации");

                    if (РежимПерваяЗапускПослеPasswordИлиПовторная.equalsIgnoreCase("СамыйПервыйЗапускСинхронизации") ) {
                    // TODO: 21.08.2023  -многопоточный
                         Flowable.fromIterable( public_contentДатыДляГлавныхТаблицСинхронизации.ВерсииВсехСерверныхТаблиц.keySet())
                                .parallel()
                                .runOn(Schedulers.computation())
                                .doOnNext(new io.reactivex.rxjava3.functions.Consumer<String>() {
                                    @Override
                                    public void accept(String ИмяТаблицыоТВерсияДанныхОтSqlServer) throws Throwable {
                                        методГлавнойСинхрониазцииПоТаблицам(ИмяТаблицыоТВерсияДанныхОтSqlServer);

                                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                                                " ИмяТаблицыоТВерсияДанныхОтSqlServer"
                                                +ИмяТаблицыоТВерсияДанныхОтSqlServer );
                                    }
                                })
                                .doOnError(new io.reactivex.rxjava3.functions.Consumer<Throwable>() {
                                    @Override
                                    public void accept(Throwable throwable) throws Throwable {
                                        throwable.printStackTrace();
                                        Log.e(this.getClass().getName(), "Ошибка " +throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                        new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(throwable.toString(),
                                                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    }
                                })
                                .doOnComplete(new Action() {
                                    @Override
                                    public void run() throws Throwable {
                                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                                    }
                                })
                                .sequentialDelayError().blockingSubscribe();


                    }else{
// TODO: 21.08.2023 ГЛАВНЫЙ ЦИКЛ СИХРОНИАЗЦИИ --Однопоточный
                        Flowable.fromIterable( public_contentДатыДляГлавныхТаблицСинхронизации.ВерсииВсехСерверныхТаблиц.keySet())
                                .onBackpressureBuffer()
                                .onErrorComplete(new Predicate<Throwable>() {
                                    @Override
                                    public boolean test(Throwable throwable) throws Throwable {
                                        throwable.printStackTrace();
                                        Log.e(this.getClass().getName(), "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                        // TODO: 01.09.2021 метод вызова
                                        new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(throwable.toString(), this.getClass().getName(),
                                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());

                                        return false;
                                    }
                                })
                                .blockingIterable()
                                .forEach(new Consumer<String>() {
                                    @Override
                                    public void accept(String ИмяТаблицыоТВерсияДанныхОтSqlServer) {
                                        // TODO: 22.08.2023 главный метод синхрониазции по таблицам
                                        методГлавнойСинхрониазцииПоТаблицам(ИмяТаблицыоТВерсияДанныхОтSqlServer);

                                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                                                " ИмяТаблицыоТВерсияДанныхОтSqlServer"
                                                +ИмяТаблицыоТВерсияДанныхОтSqlServer );
                                    }


                                });

                    }

                    Log.w(this.getClass().getName(), " doOnTerminate ОБРАБОТКА ВСЕХ ТАБЛИЦ ЛистТаблицыОбмена.stream().reduce(0, (a, b) -> a + b).intValue()"
                            + ЛистТаблицыОбмена.stream().reduce(0, (a, b) -> a + b).intValue()+ " sqLiteDatabase.isOpen() "
                            +sqLiteDatabase.isOpen()+ " РежимПерваяЗапускПослеPasswordИлиПовторная " +РежимПерваяЗапускПослеPasswordИлиПовторная);

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                            this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber()  );
                }
                return ЛистТаблицыОбмена.stream().reduce(0, (a, b) -> a + b).intValue();
            }
        // TODO: 22.03.2022  ДЛЯ ОТПРАВКИ ДАННЫХ НА СЕРВЕР









        private void методГлавнойСинхрониазцииПоТаблицам(String ИмяТаблицыоТВерсияДанныхОтSqlServer) {
            try{
                // TODO: 21.08.2023 Запуск Синхронизации после получение Версии
                Long     ВерсияДанныхОтSqlServer = public_contentДатыДляГлавныхТаблицСинхронизации.ВерсииВсехСерверныхТаблиц.get(ИмяТаблицыоТВерсияДанныхОтSqlServer);

                /////////////TODO ИДЕМ ПО ШАГАМ К ЗАПУСКИ СИНХРОГНИАЗЦИИ
                Integer   РезультатТаблицыОбмена=
                        МетодАнализаВресииДАнныхКлиента(ИмяТаблицыоТВерсияДанныхОтSqlServer,
                                ВерсияДанныхОтSqlServer, ID);



                Проценты = new Class_Visible_Processing_Async(context).ГенерируемПРОЦЕНТЫДляAsync(ПозицияТекущейТаблицы, ГлавныеТаблицыСинхронизации.size());
                // TODO: 02.05.2023  Ответ Обратно ПрограссБару
                методCallBackPrograssBars(2, Проценты, ИмяТаблицыоТВерсияДанныхОтSqlServer,ПозицияТекущейТаблицы);

                ЛистТаблицыОбмена.add(РезультатТаблицыОбмена);
                // TODO: 12.07.2023
                ПозицияТекущейТаблицы++;
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                        " ВерсияДанныхОтSqlServer " +ВерсияДанныхОтSqlServer+ " ИмяТаблицыоТВерсияДанныхОтSqlServer "
                        + ИмяТаблицыоТВерсияДанныхОтSqlServer +
                        "   РезультатТаблицыОбмена " + РезультатТаблицыОбмена);

            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                // TODO: 01.09.2021 метод вызова
                new   Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }



        private class SubClass_SendToServer  {
            public SubClass_SendToServer(@NotNull Context context) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
            }
            // TODO: 22.03.2022

            //////todo МЕТОД НЕПОСТРЕДСТВЕННО ОТПРАВЛЯЕМ ДАННЫЕ НА СЕРВЕР МЕТОД POST
            Integer МетодПосылаетНаСерверСозданныйJSONФайлвФоне(@NonNull String ГенерацияJSONОтAndroida,
                                                                @NonNull String Таблицы) {
                /////
                Integer РезультатУспешнойВставкиИлиОбновлениеCallBacksОтСервера = 0;
                String ДанныеПришёлВОтветОтМетодаPOST = new String();
                StringBuffer BufferSetDataServer = new StringBuffer();
                try {
                    Log.d(this.getClass().getName(), "  МЕТОД НЕПОСТРЕДСТВЕННО ОТПРАВЛЯЕМ ДАННЫЕ НА СЕРВЕР МЕТОД POST ");
                    // TODO: 15.06.2021 проверяем если таблица табель то еси в нутри потока отпралеемого хоть один день d1,d2,d3 защита от пустого траыфика\
                    Log.d(this.getClass().getName(), " ГенерацияJSONОтAndroida.toString() "
                            + ГенерацияJSONОтAndroida.toString() +
                            " ГенерацияJSONОтAndroida.toString().toCharArray().length  "
                            + ГенерацияJSONОтAndroida.toString().toCharArray().length +
                            " Таблицы " + Таблицы);
                    PUBLIC_CONTENT public_content=   new PUBLIC_CONTENT(context);
                    String   ИмяСерверИзХранилица = preferences.getString("ИмяСервера","");
                    Integer    ПортСерверИзХранилица = preferences.getInt("ИмяПорта",0);
                    // TODO: 21.09.2022 ОТПРАВЯЛЕТ ДАННЫЕ НА СЕРВЕР
                    BufferSetDataServer = УниверсальныйБуферОтпFравкиДанныхНаСервера(
                            ГенерацияJSONОтAndroida,
                            ID,
                            Таблицы,
                            "Получение JSON файла от Андройда",
                             ИмяСерверИзХранилица ,ПортСерверИзХранилица);
                    ///БУФЕР ОТПРАВКИ ДАННЫХ НА СЕРВЕР  //TODO original "tabel.dsu1.ru", 8888        //TODO "192.168.254.40", 8080
                    Log.d(this.getClass().getName(), "  СЛУЖБА ВЕРНУЛЬСЯ ОТВЕТ ОТ СЕРВЕРА ОБРАТНО АНДРОЙДУ  БуферОтправкаДанных.toString() " + BufferSetDataServer.toString());
                    if (BufferSetDataServer == null) {
                        BufferSetDataServer = new StringBuffer();
                    }
                    if (BufferSetDataServer.length() > 0) {
                        if (!BufferSetDataServer.toString().matches("(.*)[Don't Login and Password](.*)")){
                            РезультатУспешнойВставкиИлиОбновлениеCallBacksОтСервера=Integer.parseInt(BufferSetDataServer.toString());
                        }
                    }
                    Log.d(this.getClass().getName(), "BufferSetDataServer.length() " + BufferSetDataServer.length()+
                            " РезультатУспешнойВставкиИлиОбновлениеCallBacksОтСервера " +РезультатУспешнойВставкиИлиОбновлениеCallBacksОтСервера);

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                return РезультатУспешнойВставкиИлиОбновлениеCallBacksОтСервера;
            }

        }
    }



    public void МетодБиндинuCлужбыPublic(@NonNull Context context) {
        try {
            Intent intentЗапускPublicService = new Intent(context, Service_For_Public.class);
            intentЗапускPublicService.setAction("ЗапускУдалениеСтатусаУдаленияСтрок");
                connectionPUBLIC=     new ServiceConnection() {
                     @Override
                     public void onServiceConnected(ComponentName name, IBinder service) {
                         try {
                             if (service.isBinderAlive()) {
                                 localBinderОбщий = (Service_For_Public.LocalBinderОбщий) service;
                                 // TODO: 16.11.2022
                                 Log.d(context.getClass().getName(), "\n"
                                         + " время: " + new Date() + "\n+" +
                                         " Класс в процессе... " + this.getClass().getName() + "\n" +
                                         " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                         + "   service.pingBinder() " + service.pingBinder());
                             }
                         } catch (Exception e) {
                             e.printStackTrace();
                             Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                     + Thread.currentThread().getStackTrace()[2].getLineNumber());
                             new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                     Thread.currentThread().getStackTrace()[2].getLineNumber());
                         }
                     }

                     @Override
                     public void onServiceDisconnected(ComponentName name) {
                         try {
                             Log.d(context.getClass().getName(), "\n"
                                     + " время: " + new Date() + "\n+" +
                                     " Класс в процессе... " + this.getClass().getName() + "\n" +
                                     " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                     + "    onServiceDisconnected  localBinderОбщий" + localBinderОбщий);
                         } catch (Exception e) {
                             e.printStackTrace();
                             Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                     + Thread.currentThread().getStackTrace()[2].getLineNumber());
                             new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                     Thread.currentThread().getStackTrace()[2].getMethodName(),
                                     Thread.currentThread().getStackTrace()[2].getLineNumber());
                             // TODO: 11.05.2021 запись ошибок

                         }
                     }
                 };
           context. bindService(intentЗапускPublicService, connectionPUBLIC ,Context.BIND_AUTO_CREATE);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

}