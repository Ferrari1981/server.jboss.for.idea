package com.dsy.dsu.Dashboard;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleOwner;

import com.dsy.dsu.Business_logic_Only_Class.Class_Clears_Tables;
import com.dsy.dsu.Business_logic_Only_Class.Class_Connections_Server;
import com.dsy.dsu.Business_logic_Only_Class.Class_Find_Setting_User_Network;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generations_PUBLIC_CURRENT_ID;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generator_One_WORK_MANAGER;
import com.dsy.dsu.Business_logic_Only_Class.Class_MODEL_synchronized;
import com.dsy.dsu.Business_logic_Only_Class.PUBLIC_CONTENT;
import com.dsy.dsu.Business_logic_Only_Class.Websocet.WebSocketss;
import com.dsy.dsu.Code_ForTABEL.MainActivity_New_Templates;
import com.dsy.dsu.Code_For_AdmissionMaterials.Window.FragmentAdmissionMaterials;
import com.dsy.dsu.Code_For_AdmissionMaterials.Window.FragmentCamera;
import com.dsy.dsu.Code_For_Firebase_AndOneSignal_Здесь_КодДЛяСлужбыУведомленияFirebase.Class_Generation_SendBroadcastReceiver_And_Firebase_OneSignal;
import com.dsy.dsu.Code_For_Services.ServiceUpdatePoОбновлениеПО;
import com.dsy.dsu.Dashboard.Fragments.DashboardFragment;
import com.dsy.dsu.Dashboard.Fragments.DashboardFragmentTwo;
import com.dsy.dsu.For_Code_Settings_DSU1.MainActivity_Errors;
import com.dsy.dsu.For_Code_Settings_DSU1.MainActivity_Settings;
import com.dsy.dsu.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.navigation.NavigationView;

import java.util.Date;

/////////////////////////////////////////////////////////////////////////
public class MainActivity_Dashboard extends AppCompatActivity {

    private   Activity activity;
    private ScrollView scrollview_dashboard;
    //private  NavigationView navigator_dashboard;

    private  BuniccessLogicaActivityDashboard buniccessLogicaActivityDashboard;

    private  Handler handlerAsync;

    public static final int CAMERA_PERSSION_CODE=1;
    private ServiceUpdatePoОбновлениеПО.localBinderОбновлениеПО localBinderОбновлениеПО;//TODO новаЯ

    private      AlertDialog DialogBox=null;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private  LifecycleOwner lifecycleOwner;
    // TODO: 03.11.2022 FaceApp
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main_dashboard);


            // TODO: 15.08.2023 Инициализация перменных/////КНОПКА ТАБЕЛЬНОГО УЧЕТА
            scrollview_dashboard = (ScrollView) findViewById(R.id.scrollview_dashboard); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА
         //   navigator_dashboard = (NavigationView) findViewById(R.id.navigator_dashboard); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА
   
            activity = this;
            lifecycleOwner=this;
            getSupportActionBar().hide(); ///скрывать тул бар




            fragmentManager =  getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();



            // TODO: 15.08.2023 Начинается Пользовательский КОд
            buniccessLogicaActivityDashboard=new BuniccessLogicaActivityDashboard();


            buniccessLogicaActivityDashboard.  методСитсменыйхНастрокеДляActivity();
            buniccessLogicaActivityDashboard. МетодБиндингаОбновлениеПО();
            buniccessLogicaActivityDashboard.  МетодИнициализацияHandler();
            // TODO: 18.02.2023 установки для Обновленеи ПО
            buniccessLogicaActivityDashboard.    МЕтодУстанавливаемРазрешенияДляОновлениеПО();

           // buniccessLogicaActivityDashboard.       МетодДляСлушательБоковойПанелиFaceApp();

            // TODO: 16.11.2022  ПОСЛЕ УСТАНОВКИ РАБОТАЕТ ОДИН РАЗ ПРИ СТАРТЕ ЗАРУСК ОБЩЕГО WORK MANAGER
            new Class_Generation_SendBroadcastReceiver_And_Firebase_OneSignal(getApplicationContext()).МетодЗапускаетОБЩУЮСинхронизацию();


            // TODO: 06.04.2023  ТЕСТ КОД для 1С
            ///методДляТетсирования1С();

            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.d(this.getClass().getName(), "  Полусаем Ошибку e.toString() " + e.toString());
        }
    }





    @Override
    protected void onStart() {
        super.onStart();
        try {
          //  buniccessLogicaActivityDashboard.  МетодБоковаяПанельОткрытьЗАкрыть();

          //  buniccessLogicaActivityDashboard.     методStartingDashboardFragmentAleaiDialog();
            buniccessLogicaActivityDashboard.     методStartingDashboardFragment();

            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        try {

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }





    @Override
    public void onBackPressed() {
        try{
            int count = getSupportFragmentManager().getBackStackEntryCount();
   /*         int count = getSupportFragmentManager().getBackStackEntryCount();

            if (count == 0) {
                super.onBackPressed();
                //additional code
            } else {
                getSupportFragmentManager().popBackStack();
            }
*/

             finishAndRemoveTask();
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.d(this.getClass().getName(), "  Полусаем Ошибку e.toString() " + e.toString());
        }
    }

























    // TODO: 15.08.2023 НачинаетсяБизнеЛОгика Активтив Dashboard
    // TODO: 15.08.2023 НачинаетсяБизнеЛОгика Активтив Dashboard
    // TODO: 15.08.2023 НачинаетсяБизнеЛОгика Активтив Dashboard
    // TODO: 15.08.2023 НачинаетсяБизнеЛОгика Активтив Dashboard
    // TODO: 15.08.2023 НачинаетсяБизнеЛОгика Активтив Dashboard
    // TODO: 15.08.2023 НачинаетсяБизнеЛОгика Активтив Dashboard

    class BuniccessLogicaActivityDashboard {





        // TODO: 02.09.2021  метод Визуализация
        private void МетодИнициализацияHandler() {
            try{
                handlerAsync=new Handler(Looper.getMainLooper()){


                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        super.handleMessage(msg);
                    }

                    @Override
                    public void dispatchMessage(@NonNull Message msg) {
                        super.dispatchMessage(msg);
                        try {
                            Bundle bundleCallsBackAsynsService=msg.getData();
                            switch (msg.what){
                                case 20:// процеессе

                                    Toast toast = Toast.makeText(getApplicationContext(), "У Вас последняя версия ПО !!! ", Toast.LENGTH_LONG);
                                    toast.setGravity(Gravity.BOTTOM, 0, 40);
                                    toast.show();



                                    Log.d(this.getClass().getName(), "\n" + " class " +
                                            Thread.currentThread().getStackTrace()[2].getClassName()
                                            + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                                    break;

                                case 34:// процеессе

                                    Toast toast3 = Toast.makeText(getApplicationContext(), "Нет связи c Cервер ПО !!!", Toast.LENGTH_LONG);
                                    toast3.setGravity(Gravity.BOTTOM, 0, 40);
                                    toast3.show();

                                    Log.d(this.getClass().getName(), "\n" + " class " +
                                            Thread.currentThread().getStackTrace()[2].getClassName()
                                            + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                                    break;
                            }


                            Log.d(this.getClass().getName(), "\n" + " class " +
                                    Thread.currentThread().getStackTrace()[2].getClassName()
                                    + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }


                    }
                };

                Log.i(getApplicationContext().getClass().getName(),  " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }


        public void МЕтодУстанавливаемРазрешенияДляОновлениеПО() {
            try {
                //////////////////////TODO SERVICE
                String[] permissions = new String[]{
                        Manifest.permission.INTERNET,
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.VIBRATE,
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.REQUEST_INSTALL_PACKAGES,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS,
                        Manifest.permission.MANAGE_EXTERNAL_STORAGE,
                        Manifest.permission.ACCESS_BACKGROUND_LOCATION,
                        Manifest.permission.ACCESS_NETWORK_STATE,
                        Manifest.permission.ACCESS_MEDIA_LOCATION,
                        Manifest.permission.INSTALL_PACKAGES,
                        Manifest.permission.WRITE_SETTINGS,
                        Manifest.permission.WRITE_SECURE_SETTINGS
                };
                ActivityCompat.requestPermissions(activity, permissions, CAMERA_PERSSION_CODE);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());

            }
        }

        private void МетодЗапускаСинихрниазцииИзМенюНаАктивтиFACEAPP() {
            try {
                final Integer[] ФинальныйРезультатФоновойСинхронизации = {0};
                ProgressDialog progressDialogДляСинхронизации;
                progressDialogДляСинхронизации = new ProgressDialog(activity);
                progressDialogДляСинхронизации.setTitle("Синхронизация");
                progressDialogДляСинхронизации.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialogДляСинхронизации.setProgress(0);
                progressDialogДляСинхронизации.setCanceledOnTouchOutside(false);
                progressDialogДляСинхронизации.setMessage("Обмен данными ....");
                progressDialogДляСинхронизации.show();

                handlerAsync.post(() -> {
                    boolean СтатусСетиВыбранныйПользователем =
                            new Class_Find_Setting_User_Network(getApplicationContext()).МетодПроветяетКакуюУстановкуВыбралПользовательСети();
                    Log.d(this.getClass().getName(), "  РезультатПроВеркиУстановкиПользователяРежимРаботыСети "
                            + СтатусСетиВыбранныйПользователем);
                    Class_Connections_Server class_connections_serverПингаСерераИзАктивтиМеню = new Class_Connections_Server(getApplicationContext());
                    PUBLIC_CONTENT public_contentЗапусСинхрониазцииИМеню = new PUBLIC_CONTENT(getApplicationContext());

                    if (СтатусСетиВыбранныйПользователем == true) {
                        Boolean СтатусСервераСоюзаВключенИлиНЕт =
                                class_connections_serverПингаСерераИзАктивтиМеню.МетодПингаСервераРаботаетИлиНет(getApplicationContext());
                        if (СтатусСервераСоюзаВключенИлиНЕт == true) {
                            Integer ПубличныйIDДляОдноразовойСинхрониазции =
                                    new Class_Generations_PUBLIC_CURRENT_ID().ПолучениеПубличногоТекущегоПользователяID(getApplicationContext());

                            Bundle bundleДляПЕредачи = new Bundle();
                            bundleДляПЕредачи.putInt("IDПубличныйНеМойАСкемБылаПереписака", ПубличныйIDДляОдноразовойСинхрониазции);
                            bundleДляПЕредачи.putBoolean("StatusOneWokManagers", true);
                            Intent intentЗапускОднорworkanager = new Intent();
                            intentЗапускОднорworkanager.putExtras(bundleДляПЕредачи);
                            // TODO: 02.08.2022
                            // TODO: 02.08.2022
                            new Class_Generator_One_WORK_MANAGER(getApplicationContext()).МетодОдноразовыйЗапускВоерМенеджера(getApplicationContext(),intentЗапускОднорworkanager);
                            // TODO: 26.06.2022
                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                    + " ПубличныйIDДляОдноразовойСинхрониазции " + ПубличныйIDДляОдноразовойСинхрониазции);
                            handlerAsync.postDelayed(() -> {
                                progressDialogДляСинхронизации.dismiss();
                                progressDialogДляСинхронизации.cancel();
                            }, 3000);


                        } else {
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast toast = Toast.makeText(getApplicationContext(), "Сервер выкл. !!!", Toast.LENGTH_LONG);
                                    toast.setGravity(Gravity.BOTTOM, 0, 40);
                                    toast.show();
                                }
                            });
                        }
                    }
                });


            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());

            }
        }
        @UiThread
        protected void СообщениеДляСменыДанныхПользователя(String Самообщение) {
            try {
//////сам вид

                MaterialAlertDialogBuilder materialAlertDialogBuilder= new MaterialAlertDialogBuilder(activity)
                        .setTitle("Смена пользователя")
                        .setMessage(Самообщение)
                        .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent Интент_Меню = new Intent();
                                try {
                                    // TODO: 24.04.2023  запуск смены Пользоватедя Данные
                                    ProgressDialog prograssbarСменаДанныхПользователя;
                                    prograssbarСменаДанныхПользователя = new ProgressDialog(activity);
                                    prograssbarСменаДанныхПользователя.setTitle("Смена данных");
                                    prograssbarСменаДанныхПользователя.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                    prograssbarСменаДанныхПользователя.setProgress(0);
                                    prograssbarСменаДанныхПользователя.setCanceledOnTouchOutside(false);
                                    prograssbarСменаДанныхПользователя.setMessage("в процессе...");
                                    prograssbarСменаДанныхПользователя.show();

                                    try{
                                        PUBLIC_CONTENT Class_Engine_SQLГдеНаходитьсяМенеджерПотоков = new PUBLIC_CONTENT(activity);
                                        Class_Clears_Tables class_clears_tables=     new Class_Clears_Tables(activity,
                                                handlerAsync,
                                                prograssbarСменаДанныхПользователя);

                                        Integer    РезультатОчистикТАблицИДобалениеДаты = class_clears_tables
                                                .методСменаДанныхПользователя(activity,
                                                        Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,
                                                        activity);
                                        Log.d(this.getClass().getName(), "   ЗАПУСК ФОНРезультатОчистикТАблицИДобалениеДаты " +
                                                РезультатОчистикТАблицИДобалениеДаты);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName()
                                                + " Линия  :"
                                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                        new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    }
                                    Log.d(this.getClass().getName(), "\n" + " class " +
                                            Thread.currentThread().getStackTrace()[2].getClassName()
                                            + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName()
                                            + " Линия  :"
                                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                            this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                                }

                            }
                        })
                        .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.cancel();
                                dialog.dismiss();
                                Log.d(this.getClass().getName(), "\n" + " class " +
                                        Thread.currentThread().getStackTrace()[2].getClassName()
                                        + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                            }
                        })
                        .setIcon(R.drawable.icon_change_user1);


            if(    DialogBox==null ){
                DialogBox=  materialAlertDialogBuilder.show();
            }else {
                if(!DialogBox.isShowing()){
                    DialogBox=  materialAlertDialogBuilder.show();
                }
            }

                Log.d(this.getClass().getName(), "\n" + " class " +
                        Thread.currentThread().getStackTrace()[2].getClassName()
                        + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName()
                        + " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), 
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }







        protected void МетодЗапускаИзМенюНастроек() {
            try {
                Intent Интент_Меню = new Intent(getApplicationContext(), MainActivity_Settings.class);
                Интент_Меню.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//////FLAG_ACTIVITY_SINGLE_TOP
                Log.d(this.getClass().getName(), "" +
                        "          case R.id.ПунктМенюВторой:");
                activity.startActivity(Интент_Меню);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }


        // TODO: 15.08.2023 тест код для 1С
        private void методДляТетсирования1С() {
            try{
                Log.d(this.getClass().getName(), "   методДляТетсирования1С");
                WebSocketss webSocketListener=new WebSocketss();

/*    WebSocketss.ClassOkHttpОбычныйПинг classOkHttpОбычныйПинг= webSocketListener.new ClassOkHttpОбычныйПинг();

            classOkHttpОбычныйПинг .методОбычногоПодключениявOkHttp(this);*/


                // TODO: 06.06.2023

                WebSocketss.классИнициализацииКлиентаWebSocerts классИнициализацииКлиентаWebSocerts= webSocketListener.new классИнициализацииКлиентаWebSocerts();

                классИнициализацииКлиентаWebSocerts.методИнициализацииwebsocets(activity);


/*  new Class_Get_Json_1C(getApplicationContext(),"http://uat.dsu1.ru:55080/dds/hs/apidrp/objects")
          .МетодОтправляемJSONОт1СТестирование("userapi","User4321api",new StringBuffer("[ {  \"id\" : null,  \"chosen_organization\" : 0,  \"current_table\" : 273,  \"date_update\" : \"2022-11-27 10:33:20.737\",  \"fullname\" : \"Общество с ограниченной ответственностью СОЮЗ АВТОДОР\",  \"inn\" : \"3711025287\",  \"kpp\" : \"371101001\",  \"name\" : \"ООО СОЮЗ АВТОДОР\",  \"user_update\" : 8,  \"uuid\" : 2} ]"));
            Log.i(this.getClass().getName(), "    holder. ФдагЧтоУжеОдинРАзБылПервыйПроход ");*/

                Log.d(this.getClass().getName(), "   методДляТетсирования1С");
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());

            }
        }

        private void МетодБиндингаОбновлениеПО() {
            try {
                ServiceConnection       connectionОбновлениеПО = new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName name, IBinder service) {
                        try {
                            if (service.isBinderAlive()) {
                                localBinderОбновлениеПО = (ServiceUpdatePoОбновлениеПО.localBinderОбновлениеПО) service;
                                Log.i(getApplicationContext().getClass().getName(), "    onServiceConnected  service)"
                                        + service.isBinderAlive());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                    this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }
                    @Override
                    public void onServiceDisconnected(ComponentName name) {
                        try {
                            Log.i(getApplicationContext().getClass().getName(), "    onServiceDisconnected  binder.isBinderAlive()");
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                    this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }
                };
                Intent intentЗапускСлужбыОбновлениеПО = new Intent(getApplicationContext(), ServiceUpdatePoОбновлениеПО.class);
                intentЗапускСлужбыОбновлениеПО.setAction("com.ServiceUpdatePoОбновлениеПО");
                bindService(intentЗапускСлужбыОбновлениеПО ,  connectionОбновлениеПО,Context.BIND_AUTO_CREATE );

            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                Log.d(this.getClass().getName(), "  Полусаем Ошибку e.toString() " + e.toString());
            }

        }












        void методStartingDashboardFragment(){
            try{
                // TODO Запусукаем Фргамент DdshBoard
                DashboardFragmentTwo dashboardFragmentTwo= DashboardFragmentTwo.newInstance();
                Bundle data=new Bundle();
                data.putBinder("binder",localBinderОбновлениеПО);
                dashboardFragmentTwo.setArguments(data);
                String fragmentNewImageNameaddToBackStack=   dashboardFragmentTwo.getClass().getName();
                Fragment FragmentУжеЕСтьИлиНЕт=     fragmentManager.findFragmentByTag(fragmentNewImageNameaddToBackStack);
                if (FragmentУжеЕСтьИлиНЕт==null) {
                    fragmentTransaction.disallowAddToBackStack();
                    fragmentManager.popBackStackImmediate();

                    dashboardFragmentTwo.show(getSupportFragmentManager(), "dashboardFragmentTwo");
                    // TODO: 01.08.2023
                    fragmentManager.setFragmentResultListener("CallBackDashborndFragment", lifecycleOwner, new FragmentResultListener() {
                        @Override
                        public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                            if (requestKey.equalsIgnoreCase("CallBackDashborndFragment")) {
                                try{
                                    onBackPressed();
                                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                            + " FragmentУжеЕСтьИлиНЕт " +FragmentУжеЕСтьИлиНЕт );
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Log.e(getApplicationContext().getClass().getName(),
                                            "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                            this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                                }

                            }
                        }
                    });
                }
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " FragmentУжеЕСтьИлиНЕт " +FragmentУжеЕСтьИлиНЕт );
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getApplicationContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }

        }








        void методStartingDashboardFragmentAleaiDialog(){
            try{
                // TODO Запусукаем Фргамент DdshBoard
                DashboardFragment dashboardFragment= DashboardFragment.newInstance();
                Bundle data=new Bundle();
                data.putBinder("binder",localBinderОбновлениеПО);
                dashboardFragment.setArguments(data);
                String fragmentNewImageNameaddToBackStack=   dashboardFragment.getClass().getName();
                Fragment FragmentУжеЕСтьИлиНЕт=     fragmentManager.findFragmentByTag(fragmentNewImageNameaddToBackStack);
                if (FragmentУжеЕСтьИлиНЕт==null) {
                    fragmentTransaction.disallowAddToBackStack();

                    dashboardFragment.show(getSupportFragmentManager(), "dashboardFragment");
                    // TODO: 01.08.2023
                    fragmentManager.setFragmentResultListener("CallBackDashborndFragment", lifecycleOwner, new FragmentResultListener() {
                        @Override
                        public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                            if (requestKey.equalsIgnoreCase("CallBackDashborndFragment")) {
                                try{
                                onBackPressed();
                                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                        + " FragmentУжеЕСтьИлиНЕт " +FragmentУжеЕСтьИлиНЕт );
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e(getApplicationContext().getClass().getName(),
                                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }

                            }
                        }
                    });
                }
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " FragmentУжеЕСтьИлиНЕт " +FragmentУжеЕСтьИлиНЕт );
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getApplicationContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }

        }




        public void методСитсменыйхНастрокеДляActivity() {

            try{

          //      getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

          /*  getSupportActionBar().setHomeButtonEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setHomeAsUpIndicator(null);*/

                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
                ((Activity) activity) .setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
                ((Activity) activity).setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
                ((Activity) activity) .setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

                getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                        | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                        | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
                //////todo настрока экрана
                getWindow().getDecorView().setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getApplicationContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }



















//TODO END BUNIVEESS Logic//TODO END BUNIVEESS Logic//TODO END BUNIVEESS Logic//TODO END BUNIVEESS Logic//TODO END BUNIVEESS Logic//TODO END BUNIVEESS Logic//TODO END BUNIVEESS Logic//TODO END BUNIVEESS Logic
    }//TODO END BUNIVEESS Logic //TODO END BUNIVEESS Logic//TODO END BUNIVEESS Logic//TODO END BUNIVEESS Logic//TODO END BUNIVEESS Logic//TODO END BUNIVEESS Logic//TODO END BUNIVEESS Logic












}


