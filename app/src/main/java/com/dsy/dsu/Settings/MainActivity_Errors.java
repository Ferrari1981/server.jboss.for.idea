package com.dsy.dsu.Settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.dsy.dsu.AllDatabases.SQLTE.GetSQLiteDatabase;
import com.dsy.dsu.BusinessLogicAll.Class_Generation_Errors;
import com.dsy.dsu.BusinessLogicAll.Class_Sendiing_Errors;
import com.dsy.dsu.Dashboard.Fragments.DashboardFragmentSettings;
import com.dsy.dsu.R;
import com.google.android.material.button.MaterialButton;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;


//вывод данных на Автивити
public class MainActivity_Errors extends AppCompatActivity  {
    private SQLiteDatabase sqLiteDatabase ;
    private  TextView КонтейнерКудаЗагружаеютьсяОшибкиПрилоджения;

    private  MaterialButton materialButtonОтправка;
    private SharedPreferences preferences;
    private        File file;

    private String fileName = "Sous-Avtodor-ERROR.txt";

    private   String patchFileName="SousAvtoFile";


    private MaterialButton imageViewBack;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
                super.onCreate(savedInstanceState);
            setContentView(R.layout.activitymain_errors); ///activitymain_viewlogin  /// fragment_dashboard
            getSupportActionBar().hide(); ///скрывать тул бар

            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();

            КонтейнерКудаЗагружаеютьсяОшибкиПрилоджения = (TextView) findViewById(R.id.textViewDATA);
            sqLiteDatabase=    GetSQLiteDatabase.SqliteDatabase();
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            getSupportActionBar().hide(); ///скрывать тул бар
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                    | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                    | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
            materialButtonОтправка = (MaterialButton) findViewById(R.id.materialButtonОтправка);
            preferences=   getSharedPreferences("sharedPreferencesХранилище", Context.MODE_MULTI_PROCESS);
            imageViewBack = (MaterialButton) findViewById(R.id.imageViewBack);
            методBackInError();

            materialButtonОтправка.setClickable(false);
            materialButtonОтправка.setFocusable(false);


            МетодПросмотраОшибокПриложения();

            // TODO: 17.04.2023
            Log.d(this.getClass().getName(),"\n" + " class FaceAPp " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName()
                    + " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
             // TODO: 01.09.2021 метод вызова
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }



    private void методBackInError() {
            imageViewBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try{

         /*               Intent Интент_BackВозвращаемАктивти = new Intent();
                        Интент_BackВозвращаемАктивти.setClass(getApplication(), MainActivity_Dashboard.class); // Т
                        Интент_BackВозвращаемАктивти.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity( Интент_BackВозвращаемАктивти);*/


                        методвыходизОшибок();


                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                    Log.d(this.getClass().getName(), " Ошибок Нет. время :   " +new Date().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                            + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    // TODO: 01.09.2021 метод вызова
                    new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                            this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                }
            });

    }

    private void методвыходизОшибок() {
        // TODO Запусукаем Фргамент НАстройки  dashbord
        DashboardFragmentSettings dashboardFragmentSettings = DashboardFragmentSettings.newInstance();
        Bundle data=new Bundle();
        dashboardFragmentSettings.setArguments(data);
        fragmentTransaction.remove(dashboardFragmentSettings);
        String fragmentNewImageNameaddToBackStack=   dashboardFragmentSettings.getClass().getName();
        fragmentTransaction.addToBackStack(fragmentNewImageNameaddToBackStack);
        Fragment FragmentУжеЕСтьИлиНЕт=     fragmentManager.findFragmentByTag(fragmentNewImageNameaddToBackStack);
        if (FragmentУжеЕСтьИлиНЕт==null) {
            dashboardFragmentSettings.show(fragmentManager, "DashboardFragmentSettings");
            // TODO: 01.08.2023

        }
    }


    protected void МетодПросмотраОшибокПриложения() {


            StringBuffer БуерДляОшибок =new StringBuffer();
            String ИнфоТелефон = Build.MANUFACTURER
                    + " " + Build.MODEL + " " + Build.VERSION.RELEASE
                    + " " + Build.VERSION_CODES.class.getFields()[android.os.Build.VERSION.SDK_INT].getName();


          file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), File.separator+patchFileName +File.separator+ fileName);


            if (file.isFile()) {
                BufferedReader newBufferedReader = null;
                try {
                    newBufferedReader = Files.newBufferedReader(Paths.get(file.getPath()), StandardCharsets.UTF_16);





                 String    lineErrorsAll=null;
                    while ((lineErrorsAll = newBufferedReader.readLine()) != null) {
                        БуерДляОшибок.append(lineErrorsAll);
                        БуерДляОшибок.append('\n');
                        Log.d(this.getClass().getName(), "line " +lineErrorsAll  );
                    }

                    if (lineErrorsAll!=null){
                        materialButtonОтправка.setClickable(true);
                        materialButtonОтправка.setFocusable(true);
                        materialButtonОтправка.setVisibility(View.VISIBLE);
                    }


/*
            StringBuffer     БуферОшибок = newBufferedReader.lines().collect(StringBuffer::new, (sb, i) -> sb.append(i),
                    StringBuffer::append);*/
                    //   Log.d(this.getClass().getName(), "БуферОшибок " +БуферОшибок +  " newBufferedReader " +newBufferedReader);

                    Log.d(this.getClass().getName(), "БуерДляОшибок "+БуерДляОшибок  );
                    // TODO: 02.08.2023  clear

                    newBufferedReader.close();


                    МетодЗапускаAsynTaskОшибки(  БуерДляОшибок,ИнфоТелефон);

                    методФинальйВставки(БуерДляОшибок, ИнфоТелефон);
                } catch (IOException e) {

                    методФинальйВставки(БуерДляОшибок, ИнфоТелефон);

                    throw new RuntimeException(e);


                }


            }else {

                методФинальйВставки(БуерДляОшибок, ИнфоТелефон);
            }




            Log.d(this.getClass().getName(),  " date " +new Date().toGMTString().toString() + " preferences " +preferences.getAll());

    }

    private void методФинальйВставки(StringBuffer БуерДляОшибок, String ИнфоТелефон) {
        методКогдаНетОшибок(БуерДляОшибок, ИнфоТелефон);
        // TODO: 07.07.2023  сама вставка ошибок
        КонтейнерКудаЗагружаеютьсяОшибкиПрилоджения.setText(БуерДляОшибок.toString());
    }


    private void методКогдаНетОшибок(StringBuffer БуерДляОшибок, String ИнфоТелефон) {
        try{
        БуерДляОшибок.append(    "---------------Ошибок Нет.-----------"+"\n"+"\n"+
                "   " + ИнфоТелефон + "  : " + "  Инфо. телефона " + "\n" + "\n" +
                "   " + Build.BRAND.toUpperCase() + "  : " + " Имя " + "\n" + "\n" +
                Build.VERSION.SDK_INT+ "  : " + " API ("+Build.VERSION.RELEASE+ ")"+ "\n" + "\n" +
                "- время : " +new Date().toString()+"-" + "\n"+  "\n"+
                "   " + "-----------------------------------------" + "\n"+  "\n" );

        Log.d(this.getClass().getName(), " Ошибок Нет. время :   " +new Date().toString());
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }


    protected void МетодЗапускаAsynTaskОшибки( @NonNull  StringBuffer БуерДляОшибок, String ИнфоТелефон) {
        try {
                materialButtonОтправка.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //TODO полывоаем ошибки на почту
                        Vibrator v2 = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            v2.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
                        } else {
                            //deprecated in API 26
                            v2.vibrate(50);
                        }
                        // TODO: 06.07.2023  оправлем ощибку на почту 
                        МетодПосылаемОшибкиНапочту(БуерДляОшибок);
                        Log.d(this.getClass().getName(), " Ошибок Нет. время :   " +new Date().toString());
                    }
                });

            Log.d(this.getClass().getName(), " БуерДляОшибок   " +БуерДляОшибок.toString());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                    Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), 
          this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
///////
        }
    }

    protected void МетодПосылаемОшибкиНапочту(@NonNull StringBuffer БуерДляОшибок) {
        try{
            Integer   ПубличноеID  = preferences.getInt("ПубличноеID",0);
                БуерДляОшибок.append("\n")
                        .append(" текущий пользователь : ").append("\n")
                        .append(ПубличноеID).append("\n")
                        .append(" время отправки: ").append("\n")
                        .append(new Date())
                        .append("\n");
            // TODO: 06.07.2023  оправлем ощибки на ПОЧТУ
            // TODO: 06.07.2023  оправлем ощибки на ПРЧТУ
                new Class_Sendiing_Errors(this)
                        .МетодПослываемОшибкиАдминистаторуПо(БуерДляОшибок,this,ПубличноеID,   sqLiteDatabase );


            Log.d(this.getClass().getName(), " Ошибок Нет. время :   " +new Date().toString());

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }


}//конец public class MainActivity_Recyclerview extends AppCompatActivity {
