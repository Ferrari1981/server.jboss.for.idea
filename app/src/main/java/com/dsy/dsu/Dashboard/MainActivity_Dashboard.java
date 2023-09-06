package com.dsy.dsu.Dashboard;

import android.Manifest;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleOwner;

import com.dsy.dsu.BusinessLogicAll.Class_Generation_Errors;
import com.dsy.dsu.Services.ServiceUpdatePoОбновлениеПО;
import com.dsy.dsu.Dashboard.Fragments.DashboardFragmentMaterialDesign;
import com.dsy.dsu.R;
import com.dsy.dsu.UnitTests.ClassAllTests;

/////////////////////////////////////////////////////////////////////////
public class MainActivity_Dashboard extends AppCompatActivity {

    private   Activity activity;
    private ScrollView scrollview_dashboard;
    //private  NavigationView navigator_dashboard;

    private  BuniccessLogicaActivityDashboard buniccessLogicaActivityDashboard;

    private  Handler handlerAsync;

    public static final int CAMERA_PERSSION_CODE=1;
    private ServiceUpdatePoОбновлениеПО.localBinderОбновлениеПО localBinderОбновлениеПО;//TODO новаЯ


    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private  LifecycleOwner lifecycleOwner;

    // TODO: 03.11.2022 FaceApp
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main_dashboard);

            scrollview_dashboard = (ScrollView) findViewById(R.id.scrollview_dashboard); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА

            activity = this;
            lifecycleOwner=this;
            getSupportActionBar().hide();
            fragmentManager =  getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            // TODO: 15.08.2023 Начинается Пользовательский КОд
            buniccessLogicaActivityDashboard=new BuniccessLogicaActivityDashboard();

            buniccessLogicaActivityDashboard. МетодБиндингаОбновлениеПО();

            // TODO: 18.02.2023 установки для Обновленеи ПО
            buniccessLogicaActivityDashboard.    МЕтодУстанавливаемРазрешенияДляОновлениеПО();

           // buniccessLogicaActivityDashboard.   МетодСитемныйНастройкиЭкран();
            buniccessLogicaActivityDashboard.     МетодИнициализацияHandler();

            // TODO: 06.04.2023  ТЕСТ КОД для 1С
            ///методДляТетсирования1С();

            ClassAllTests classAllTests =    new ClassAllTests(getApplicationContext());
            classAllTests.new ClassTestROOM().методТестRoom();
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
            buniccessLogicaActivityDashboard.     методStartingDashboardFragment();
            buniccessLogicaActivityDashboard.    методСлушательФрагментов(  );





                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );

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

    public class BuniccessLogicaActivityDashboard {


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


        public void МетодБиндингаОбновлениеПО() {
            try {
                ServiceConnection connectionОбновлениеПО = new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName name, IBinder service) {
                        try {
                            if (service.isBinderAlive()) {
                                localBinderОбновлениеПО = (ServiceUpdatePoОбновлениеПО.localBinderОбновлениеПО) service;
                                Bundle bundlebinder = new Bundle();
                                bundlebinder.putBinder("callbackbinderdashbord", localBinderОбновлениеПО);
                                fragmentManager.setFragmentResult("callbackbinderdashbord", bundlebinder);
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
                bindService(intentЗапускСлужбыОбновлениеПО, connectionОбновлениеПО, Context.BIND_AUTO_CREATE);

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


        void методStartingDashboardFragment() {
            try {
                // TODO Запусукаем Фргамент DdshBoard
                DashboardFragmentMaterialDesign dashboardFragmentHarmonyOS = DashboardFragmentMaterialDesign.newInstance();
                Bundle data = new Bundle();
                data.putBinder("callbackbinderdashbord", localBinderОбновлениеПО);
                dashboardFragmentHarmonyOS.setArguments(data);
                fragmentTransaction.remove(dashboardFragmentHarmonyOS);
                String fragmentNewImageNameaddToBackStack = dashboardFragmentHarmonyOS.getClass().getName();
                fragmentTransaction.addToBackStack(fragmentNewImageNameaddToBackStack)
                        .setPrimaryNavigationFragment(dashboardFragmentHarmonyOS)
                        .setReorderingAllowed(true);
                Fragment FragmentУжеЕСтьИлиНЕт = fragmentManager.findFragmentByTag(fragmentNewImageNameaddToBackStack);
                if (FragmentУжеЕСтьИлиНЕт == null) {
                    dashboardFragmentHarmonyOS.show(fragmentManager, "dashboardFragmentHarmonyOS");
                    // TODO: 01.08.2023
                }
                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " FragmentУжеЕСтьИлиНЕт " + FragmentУжеЕСтьИлиНЕт);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getApplicationContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }

        }
/*        void методStartingDashboardFragment(){
            try{
                // TODO Запусукаем Фргамент DdshBoard
                DashboardFragmentMaterialDesign dashboardFragmentHarmonyOS = new DashboardFragmentMaterialDesign();
                Bundle data=new Bundle();
                data.putBinder("callbackbinderdashbord",localBinderОбновлениеПО);
                dashboardFragmentHarmonyOS.setArguments(data);


                fragmentTransaction.remove(dashboardFragmentHarmonyOS);

                fragmentTransaction.replace(R.id.linearLayout_root_activity_dashboard, dashboardFragmentHarmonyOS)
                        .setPrimaryNavigationFragment(dashboardFragmentHarmonyOS)
                        .setReorderingAllowed(true).commit();//.layout.activity_for_fragemtb_history_tasks
                fragmentTransaction.show(dashboardFragmentHarmonyOS);

                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " dashboardFragmentHarmonyOS " +dashboardFragmentHarmonyOS );
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getApplicationContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }

        }*/

        private void методСлушательФрагментов() {
            try {
                fragmentManager.setFragmentResultListener("CallBackDashborndFragment", lifecycleOwner, new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        if (requestKey.equalsIgnoreCase("CallBackDashborndFragment")) {
                            try {
                                onBackPressed();
                                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e(getApplicationContext().getClass().getName(),
                                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }

                        }
                    }
                });
                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getApplicationContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }


        private void МетодИнициализацияHandler() {
try{
            handlerAsync = new Handler(Looper.getMainLooper()) {


                @Override
                public void handleMessage(@NonNull Message msg) {
                    super.handleMessage(msg);
                }

                @Override
                public void dispatchMessage(@NonNull Message msg) {
                    super.dispatchMessage(msg);

                    Bundle bundleCallsBackAsynsService = msg.getData();


                }
            };

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        }



        private void МетодСитемныйНастройкиЭкран() {
            try{
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
                getSupportActionBar().setHomeButtonEnabled(false);
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                getSupportActionBar().setHomeAsUpIndicator(null);

                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
                ((Activity) activity) .setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
                ((Activity) activity).setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
                ((Activity) activity) .setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

                // TODO: 25.03.2023
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }




























//TODO END BUNIVEESS Logic//TODO END BUNIVEESS Logic//TODO END BUNIVEESS Logic//TODO END BUNIVEESS Logic//TODO END BUNIVEESS Logic//TODO END BUNIVEESS Logic//TODO END BUNIVEESS Logic//TODO END BUNIVEESS Logic
    }//TODO END BUNIVEESS Logic //TODO END BUNIVEESS Logic//TODO END BUNIVEESS Logic//TODO END BUNIVEESS Logic//TODO END BUNIVEESS Logic//TODO END BUNIVEESS Logic//TODO END BUNIVEESS Logic




}


