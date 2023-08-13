package com.dsy.dsu.For_Code_Settings_DSU1;

import android.Manifest;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;

import com.dsy.dsu.AllDatabases.CREATE_DATABASE;

import com.dsy.dsu.Business_logic_Only_Class.Class_Connections_Server;
import com.dsy.dsu.Business_logic_Only_Class.Class_GRUD_SQL_Operations;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.Business_logic_Only_Class.DATE.SubClassCursorLoader;
import com.dsy.dsu.Business_logic_Only_Class.PUBLIC_CONTENT;
import com.dsy.dsu.R;
import com.google.type.TimeOfDayOrBuilder;


import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;


public class MainActivity_Face_Start extends AppCompatActivity {

    private  String success_users =         new String();
    private  String success_login =         new String();

    private  String date_update;
    private  CREATE_DATABASE   Create_Database_СсылкаНАБазовыйКласс;
    private  Context КонтекстДляFAceapp;
    private  Activity activity;
    // TODO: 24.02.202
    private  Boolean СтатусРаботыСервера =false;
    private SharedPreferences preferences;
    Integer ФиналПолучаемРазницуМеждуДатами =0;
    public static final int CAMERA_PERSSION_CODE=1;
    private  ImageView img_animation;
    private Animation animation3;
    private ProgressBar progressBarFace;

   private Message message;

   private  LogicBinessMainActivity_Face_Start logicBinessMainActivity_face_startl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
try{
    setContentView(R.layout.activity_main__face);
    // TODO: 26.06.2023 БАЗА ДАННЫХ ОСНОВНАЯ
    Create_Database_СсылкаНАБазовыйКласс=new CREATE_DATABASE(getApplicationContext());
    // TODO: 26.06.2023 БАЗА ДАННЫХ ВСТМПОГАТЕЛЬЫНЕ
    img_animation = (ImageView) findViewById(R.id.imageviewstart);
    progressBarFace = (ProgressBar) findViewById(R.id.progressBarFace);
    progressBarFace.setProgress(View.VISIBLE);

        activity=this;
        КонтекстДляFAceapp=this;
        ((Activity) КонтекстДляFAceapp) .setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //////todo настрока экрана
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(null);
        ///TODO попытка открыть экран как full screan
        getSupportActionBar().hide(); ///скрывать тул бар
      /////todo данная настрока запрещает при запуке активти подскаваать клавиатуре вверх на компонеты eedittext
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        ((Activity) КонтекстДляFAceapp) .setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
    preferences = getSharedPreferences("sharedPreferencesХранилище", Context.MODE_MULTI_PROCESS);
    animation3 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_singletable2);





    logicBinessMainActivity_face_startl=new LogicBinessMainActivity_Face_Start();
    // TODO: 24.02.2022
    new Class_Generation_Errors(getApplicationContext()).методСозданиеФайлаДляОшибки();//
    logicBinessMainActivity_face_startl. методMessage();
    logicBinessMainActivity_face_startl. МетодДополнительнойНастрокиАвтоЗапуска();
    logicBinessMainActivity_face_startl.   методДаемПраваНаCameraPermissions(this);
    logicBinessMainActivity_face_startl. методАнимацииЗагрузкиЗначка();

    Log.d(this.getClass().getName(),
            " date " +new Date().toGMTString().toString()
                    + " ФиналПолучаемРазницуМеждуДатами " +ФиналПолучаемРазницуМеждуДатами);


    Log.d(this.getClass().getName(),  " date " +new Date().toGMTString().toString() + " preferences " +preferences.getAll());
    // TODO: 07.12.2022  test code
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


    @Override
    protected void onStart() {
        super.onStart();
        message.getTarget().post(new Runnable() {
            @Override
            public void run() {
                try{//
                    logicBinessMainActivity_face_startl.  МетодПингаКСереруЗапущенЛиСерерИлиНет();

                    logicBinessMainActivity_face_startl. МетодОпределениеКогдаПоследнийРазЗаходилПользователь();////ЗАПУСКАЕМ


                    Log.d(this.getClass().getName(),
                            " date " +new Date().toGMTString().toString()
                                    + " ФиналПолучаемРазницуМеждуДатами " +ФиналПолучаемРазницуМеждуДатами);
                    // TODO: 07.12.2022  test code
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                            + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber() +
                            " ФиналПолучаемРазницуМеждуДатами " + ФиналПолучаемРазницуМеждуДатами);
                    // TODO: 01.09.2021 метод вызова
                    new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }
        });
        Log.d(this.getClass().getName(),  " date " +new Date().toGMTString().toString() + " preferences " +preferences.getAll());
        // TODO: 07.12.2022  test code
    }















    // TODO: 13.08.2023 Class BinucessLogic

    class LogicBinessMainActivity_Face_Start{


        private void методMessage() {
            message= Message.obtain(new Handler(Looper.myLooper()),()->{
                try{
                    Bundle bundle=   message.getData();
                    методЗапускаАктивитиПоРезультатамДат( );

                    Log.i(this.getClass().getName(),  " Атоманически установкаОбновление ПО "+
                            Thread.currentThread().getStackTrace()[2].getMethodName()+
                            " время " +new Date().toLocaleString() + " message " +message );
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                            + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber() +
                            " ФиналПолучаемРазницуМеждуДатами " + ФиналПолучаемРазницуМеждуДатами);
                    // TODO: 01.09.2021 метод вызова
                    new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            });
        }





        void  методАнимацииЗагрузкиЗначка(){
            try{
                img_animation.startAnimation(animation3);
                progressBarFace.startAnimation(animation3);
                Log.d(this.getClass().getName(),  " date " +new Date().toGMTString().toString() + " preferences " +preferences.getAll());
                // TODO: 07.12.2022  test code
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





        //TODO метод пользовательской дополнительной настройки автозапуса

        private void МетодДополнительнойНастрокиАвтоЗапуска() {
            try{
                final Intent[] AUTO_START_INTENTS = {
                        new Intent().setComponent(new ComponentName("com.samsung.android.lool",
                                "com.samsung.android.sm.ui.battery.BatteryActivity")),
                        new Intent("miui.intent.action.OP_AUTO_START").addCategory(Intent.CATEGORY_DEFAULT),
                        new Intent().setComponent(new ComponentName("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity")),
                        new Intent().setComponent(new ComponentName("com.letv.android.letvsafe", "com.letv.android.letvsafe.AutobootManageActivity")),
                        new Intent().setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.optimize.process.ProtectActivity")),
                        new Intent().setComponent(new ComponentName("com.coloros.safecenter", "com.coloros.safecenter.permission.startup.StartupAppListActivity")),
                        new Intent().setComponent(new ComponentName("com.coloros.safecenter", "com.coloros.safecenter.startupapp.StartupAppListActivity")),
                        new Intent().setComponent(new ComponentName("com.oppo.safe", "com.oppo.safe.permission.startup.StartupAppListActivity")),
                        new Intent().setComponent(new ComponentName("com.iqoo.secure", "com.iqoo.secure.ui.phoneoptimize.AddWhiteListActivity")),
                        new Intent().setComponent(new ComponentName("com.iqoo.secure", "com.iqoo.secure.ui.phoneoptimize.BgStartUpManager")),
                        new Intent().setComponent(new ComponentName("com.vivo.permissionmanager", "com.vivo.permissionmanager.activity.BgStartUpManagerActivity")),
                        new Intent().setComponent(new ComponentName("com.asus.mobilemanager", "com.asus.mobilemanager.entry.FunctionActivity")).setData(
                                Uri.parse("mobilemanager://function/entry/AutoStart"))
                };
                for (Intent intent : AUTO_START_INTENTS) {
                    if (getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY) != null) {
                        startActivity(intent);
                        // break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                // TODO: 01.09.2021 метод вызова
                new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }







        public  void методДаемПраваНаCameraPermissions(Activity activity){
            if (ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {
                // Permission is not granted
                Log.d("checkCameraPermissions", "No Camera Permissions");
                //////////////////////TODO SERVICE
                String[] permissions = new String[]{
                        Manifest.permission.CAMERA,
                        Manifest.permission.RECORD_AUDIO,
                        android.Manifest.permission.INTERNET,
                        android.Manifest.permission.READ_PHONE_STATE,
                        android.Manifest.permission.READ_EXTERNAL_STORAGE,
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        android.Manifest.permission.VIBRATE,
                        android.Manifest.permission.RECORD_AUDIO,
                        android.Manifest.permission.RECORD_AUDIO,
                        android.Manifest.permission.REQUEST_INSTALL_PACKAGES,
                        android.Manifest.permission.ACCESS_FINE_LOCATION,
                        android.Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS,
                        android.Manifest.permission.MANAGE_EXTERNAL_STORAGE,
                        android.Manifest.permission.ACCESS_BACKGROUND_LOCATION,
                        android.Manifest.permission.ACCESS_NETWORK_STATE,
                        android.Manifest.permission.ACCESS_MEDIA_LOCATION,
                        android.Manifest.permission.INSTALL_PACKAGES,
                        android.Manifest.permission.WRITE_SETTINGS,
                        android. Manifest.permission.WRITE_SECURE_SETTINGS
                };
                ActivityCompat.requestPermissions(activity, permissions,CAMERA_PERSSION_CODE );


            }else{
                // Permission is not granted
                Log.d("checkCameraPermissions", "Success YRA  Camera Permissions  !!!!");
            }
        }



// TODO: 24.02.2022




        private Boolean МетодПингаКСереруЗапущенЛиСерерИлиНет() {
            try{
                // TODO: 16.12.2021 НЕПОСРЕДСТВЕННЫЙ ПИНГ СИСТЕНМ ИНТРЕНАТ НА НАЛИЧЕНИ СВАЗИ С БАЗОЙ SQL SERVER
                СтатусРаботыСервера =
                        new Class_Connections_Server(getApplicationContext()).
                                МетодПингаСервераРаботаетИлиНет(getApplicationContext());
                Log.d(this.getClass().getName(), "  РезультатЕслиСвязьСерверомПередНачаломВизуальнойСинхронизции " + СтатусРаботыСервера);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }

            return СтатусРаботыСервера;
        }





















        /////// МЕТОД КОГДА ЗАХОДИЛ ПОСЛЬДНИЙ РАЗ ПОЛЬЗОВАТЛЬ
        private void  МетодОпределениеКогдаПоследнийРазЗаходилПользователь() throws ExecutionException, InterruptedException {
            Cursor Курсор_7ДнейЗаходаПользователя = null;
            try{
                Bundle bundleПосикФИО=new Bundle();
                bundleПосикФИО.putString("СамЗапрос","  SELECT *  FROM    successlogin   ORDER BY id  LIMIT   1  ");
                bundleПосикФИО.putStringArray("УсловияВыборки" ,new String[]{});
                bundleПосикФИО.putString("Таблица","successlogin");
                Курсор_7ДнейЗаходаПользователя=
                        (Cursor)    new SubClassCursorLoader(). CursorLoaders(getApplicationContext(), bundleПосикФИО);
                Log.d(this.getClass().getName(), "Курсор_7ДнейЗаходаПользователя "+Курсор_7ДнейЗаходаПользователя  );

                if (Курсор_7ДнейЗаходаПользователя.getCount() > 0) {/////ПРОВЕРЯЕМ ЕСЛИ ПО ДАННОМУ ID UUID ЗАПОЛНЕ ЛИ ОН
                    Курсор_7ДнейЗаходаПользователя.moveToFirst();
                    success_users =
                            Курсор_7ДнейЗаходаПользователя.getString(Курсор_7ДнейЗаходаПользователя.getColumnIndex("success_users")).trim();
                    success_login =
                            Курсор_7ДнейЗаходаПользователя.getString(Курсор_7ДнейЗаходаПользователя.getColumnIndex("success_login")).trim();
                    date_update =
                            Курсор_7ДнейЗаходаПользователя.getString(Курсор_7ДнейЗаходаПользователя.getColumnIndex("date_update")).trim();

                    Log.d(this.getClass().getName(), "  success_users  " + success_users + "  " +
                            "    success_login  " + success_login + " date_update " + date_update);

                    // TODO: 13.08.2023 дата из табции
                    Date ДатаSucceslogin =
                            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS",
                                    new Locale("ru")).parse(date_update);//TODO "2023-08-01 19:00:59.781"

                    Log.d(this.getClass().getName(), "  ДатаSucceslogin  " + ДатаSucceslogin);


                    // TODO: 13.08.2023 Дата NOW !!!!!
                    Date ДатаNOW = Calendar.getInstance().getTime();
                    DateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", new Locale("ru"));//"yyyy-MM-dd'T'HH:mm:ss'Z'
                    String ДатСегодняДатаNOW = dateFormat.format(ДатаNOW);
                    Log.d(this.getClass().getName(), "  ДатСегодняДатаNOW  " + ДатСегодняДатаNOW);


                    ////TODO само сравнивание дат на 7 дней назад
                    long РазницаМеждуДатамиNowИДатыИзБазы =
                            ДатаNOW.getTime()
                                    - ДатаSucceslogin.getTime(); //локальное сравнение дата из базы андройда и дат сегодня
                    ///////////
                    ФиналПолучаемРазницуМеждуДатами = Integer.parseInt("" + (TimeUnit.DAYS.convert(РазницаМеждуДатамиNowИДатыИзБазы, TimeUnit.MILLISECONDS)));

                    Log.d(this.getClass().getName(), "  ФиналПолучаемРазницуМеждуДатами  " + ФиналПолучаемРазницуМеждуДатами);

                }
                // TODO: 13.08.2023
                if (Курсор_7ДнейЗаходаПользователя!=null) {
                    Курсор_7ДнейЗаходаПользователя.close();///
                }
                message.sendToTarget();
                Log.d(this.getClass().getName(), "  ФиналПолучаемРазницуМеждуДатами  " + ФиналПолучаемРазницуМеждуДатами);

            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }


        }

        ///////todo ФИНАЛЬНЫЙ МЕТОД КТО ВХОДИЛ ДО 7 ДНЕЙ ИЛИ ПОСЫЛАЕМ НА АУНТИФИКАЦИЮ
        private void методЗапускаАктивитиПоРезультатамДат( ) {
            Intent Интент_ЗапускаетFaceApp= new Intent();
            try{
                Интент_ЗапускаетFaceApp.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);//////FLAG_ACTIVITY_SINGLE_TOP

                if ( ФиналПолучаемРазницуМеждуДатами <20 && date_update!=null && success_users!=null  && success_login!=null  ) {

                    if (СтатусРаботыСервера ==true) {
                        Интент_ЗапускаетFaceApp.setClass(getApplicationContext(),  MainActivity_Visible_Async.class); //MainActivity_Visible_Async //MainActivity_Face_App
                        Интент_ЗапускаетFaceApp.setAction("MainActivity_Visible_Async.class");

                    } else {
                        Интент_ЗапускаетFaceApp.setClass(getApplicationContext(),  MainActivity_Face_App.class);
                        МетодСообщениеПользоватлюЧтоНЕтИнтренета("Режим: (офлайн)");
                        Интент_ЗапускаетFaceApp.setAction("MainActivity_Face_App.class");
                    }

                }else{
                    Интент_ЗапускаетFaceApp.setClass(getApplicationContext(), MainActivity_Tabels_Users_And_Passwords.class);
                    Интент_ЗапускаетFaceApp.setAction("MainActivity_Tabels_Users_And_Passwords.class");
                }
                startActivity(Интент_ЗапускаетFaceApp);//tso
                finishAffinity();

                Log.d(this.getClass().getName(), "  ФиналПолучаемРазницуМеждуДатами  " + ФиналПолучаемРазницуМеждуДатами);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

        private void МетодСообщениеПользоватлюЧтоНЕтИнтренета(String КакойРежимОтоброжать) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast toast=       Toast.makeText(getApplicationContext(),КакойРежимОтоброжать , Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.BOTTOM,0,40);
                    toast.show();
                }
            });
        }



    }






}


