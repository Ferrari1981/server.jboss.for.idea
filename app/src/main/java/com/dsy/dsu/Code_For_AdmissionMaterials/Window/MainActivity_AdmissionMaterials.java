package com.dsy.dsu.Code_For_AdmissionMaterials.Window;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.ImageFormat;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.media.Image;
import android.media.ImageReader;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Surface;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.Code_For_Services.Service_for_AdminissionMaterial;
import com.dsy.dsu.R;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity_AdmissionMaterials extends AppCompatActivity implements CameraXInterface {
    private Activity activity;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment fragment_ДляПолучениеМатериалов;
    private LinearLayout activity_admissionmaterias_face ;

    public   Bitmap bitmapNewPhotoFromCameraX;
    public static final int CAMERA_PERSSION_CODE=1;

    private  Service_for_AdminissionMaterial.LocalBinderДляПолучениеМатериалов binderДляПолучениеМатериалов;

    private   BusinessLogic businessLogic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
        setContentView(R.layout.activity_main_admission_materials);
        activity=this;
        fragmentManager = getSupportFragmentManager();
        getSupportActionBar().hide(); ///скрывать тул бар
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
           activity_admissionmaterias_face =  (LinearLayout) findViewById(R.id.activity_admissionmaterias_mainface);
            ViewGroup.LayoutParams params = activity_admissionmaterias_face.getLayoutParams();
            params.height= ViewGroup.LayoutParams.WRAP_CONTENT;
            activity_admissionmaterias_face.setLayoutParams(params);
            Log.d(this.getClass().getName(), "  onViewCreated  FragmentAdmissionMaterials");



            // TODO: 06.08.2023  Start Бизнес ЛОгикуу
            businessLogic=new BusinessLogic();

            businessLogic. методБиндингСлужбы();
            businessLogic. методДаемПраваНаCameraPermissions(this);
            businessLogic.МетодЗапускФрагментаПриемМатериалов();


            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                + Thread.currentThread().getStackTrace()[2].getMethodName().toString() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getApplicationContext()).
                МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(),
                        Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==CAMERA_PERSSION_CODE){
            Log.d("checkCameraPermissions", "requestCode "+requestCode +  " permissions "+  permissions  +" grantResults " +grantResults);
        }
    }

    @Override
    public Bitmap onGetFinishEditDialogNewPhotos( ) {
        try{

            Log.d(this.getClass().getName(), "   bitmapNewPhotoFromCameraX " + bitmapNewPhotoFromCameraX);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  bitmapNewPhotoFromCameraX;
    }

    @Override
    public void onSEtFinishEditDialogNewPhotos(@NonNull Bitmap bitmap) {
        try{
        bitmapNewPhotoFromCameraX=bitmap;

   /*         Fragment fragmentListAdd = (FragmentMaretialNew)   fragmentManager.findFragmentById(R.layout.fragment_admission_new_material);
            ((FragmentMaretialNew)fragmentListAdd).методCallsBackNewImageFromCameraActivityResult(bitmapNewPhotoFromCameraX);*/


            Log.d(this.getClass().getName(), "   bitmapNewPhotoFromCameraX " + bitmapNewPhotoFromCameraX+ " bitmap " +bitmap);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }


    // TODO: 27.07.2023 class business logoc

    class BusinessLogic{
        protected void МетодЗапускФрагментаПриемМатериалов() {
            try{
                fragmentManager.clearBackStack(null);
                fragmentTransaction = fragmentManager.beginTransaction();

               // fragmentTransaction.setCustomAnimations(android.R.anim.slide_out_right, android.R.anim.slide_in_left);

                fragment_ДляПолучениеМатериалов = new FragmentAdmissionMaterials();
                String FragmentMainМатериалы=   fragment_ДляПолучениеМатериалов.getClass().getName();

                Fragment    FragmentУжеЕСтьИлиНЕт=     fragmentManager.findFragmentByTag(FragmentMainМатериалы);
                if (FragmentУжеЕСтьИлиНЕт==null) {
                fragmentTransaction.add(R.id.activity_admissionmaterias_mainface,
                                fragment_ДляПолучениеМатериалов)
                        .setPrimaryNavigationFragment(fragment_ДляПолучениеМатериалов)
                        .setReorderingAllowed(true).commit();//.layout.activity_for_fragemtb_history_tasks//.layout.activity_for_fragemtb_history_tasks
                fragmentTransaction.show(fragment_ДляПолучениеМатериалов);
                }
                Log.d(this.getClass().getName(), " fragment_ДляПолучениеМатериалов "
                        + fragment_ДляПолучениеМатериалов);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                        + Thread.currentThread().getStackTrace()[2].getMethodName().toString() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getApplicationContext()).
                        МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                this.getClass().getName().toString(),
                                Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

        // TODO: 06.08.2023  Биндинг Службы
        private void методБиндингСлужбы() {
            try {
           ServiceConnection       serviceConnectionМатериалы = new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName name, IBinder service) {
                        try {
                            if (service.isBinderAlive()) {
                                // TODO: 30.06.2023
                     Boolean ПингаСлужбы=           service.pingBinder();

                                if (ПингаСлужбы==true) {
                                    binderДляПолучениеМатериалов = (Service_for_AdminissionMaterial.LocalBinderДляПолучениеМатериалов) service;


                                    Bundle bundlebinderДляПолучениеМатериалов=new Bundle();
                                    bundlebinderДляПолучениеМатериалов.putBinder("binderДляПолучениеМатериалов",binderДляПолучениеМатериалов);

                                    fragmentManager.setFragmentResult("BackFromActivityBinder",bundlebinderДляПолучениеМатериалов);
                                }

                                Log.d(getApplicationContext().getClass().getName(), "\n"
                                        + " время: " + new Date() + "\n+" +
                                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                                        " onServiceConnected  метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                        + "    onServiceDisconnected  Service_for_AdminissionMaterial" + " service "
                                        + service.isBinderAlive()  + " binderДляПолучениеМатериалов "+binderДляПолучениеМатериалов);

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }
                    @Override
                    public void onServiceDisconnected(ComponentName name) {
                        try {
                            Log.d(getApplicationContext().getClass().getName(), "\n"
                                    + " время: " + new Date() + "\n+" +
                                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                                    "  onServiceDisconnected метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                    + "    onServiceDisconnected  bibinderСогласованияbinderМатериалыnder");
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }
                };
                Intent intentЗапускБиндингаМатериалы = new Intent(getApplicationContext(), Service_for_AdminissionMaterial.class);
                intentЗапускБиндингаМатериалы.setAction("com.Service_for_AdminissionMaterial");
             Boolean asBoeen=   bindService(intentЗапускБиндингаМатериалы, serviceConnectionМатериалы, Context.BIND_AUTO_CREATE);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }


        public  void методДаемПраваНаCameraPermissions(Activity activity){
            if (ContextCompat.checkSelfPermission(activity,android. Manifest.permission.CAMERA ) != PackageManager.PERMISSION_GRANTED) {
                // Permission is not granted
                Log.d("checkCameraPermissions", "No Camera Permissions");
                //////////////////////TODO SERVICE
                String[] permissions = new String[]{
                        android. Manifest.permission.CAMERA,
                        android. Manifest.permission.RECORD_AUDIO,
                        android.Manifest.permission.INTERNET,
                        android.Manifest.permission.READ_PHONE_STATE,
                        android.Manifest.permission.READ_EXTERNAL_STORAGE,
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        android.Manifest.permission.VIBRATE,
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
                ActivityCompat.requestPermissions(MainActivity_AdmissionMaterials.this, permissions, CAMERA_PERSSION_CODE);


            }else{
                // Permission is not granted
                Log.d("checkCameraPermissions", "Success YRA  Camera Permissions  !!!!");
            }
        }



































    }//TODO end BusinessLogic

}  // TODO: 31.07.2023  END ACTIVITY