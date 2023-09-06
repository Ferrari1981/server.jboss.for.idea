package com.dsy.dsu.BroadcastRecievers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.work.Constraints;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.dsy.dsu.BusinessLogicAll.Class_Generation_Errors;
import com.dsy.dsu.WorkManagers.MyWork_ScannerBluetooth_ДляПосикаДевайсов;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class BroadcastReceiver_ScannerBluetooth extends BroadcastReceiver {
    public BroadcastReceiver_ScannerBluetooth() {
        super();
        Log.i(this.getClass().getName(), " ЗАПУСК  КОНСТРКТОР BroadcastReceiver_Sous_Asyns_Glassfish   " +
                " public void onReceive(Context context, Intent intent) ........ СНАРУЖИ BroadcastReceiver_Sous_Asyns_Glassfish   (intent.getAction()   СЛУЖБА " + new Date());
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        try {
                Log.i(this.getClass().getName(), " Внутри Broadcatrecever (intent.getAction()   СЛУЖБА кто ЗАПУСТИЛ САМ bRODCAST ? :::" +(intent.getAction().toString())+"\n"+
                        " Build.BRAND.toString() Название Телефона " +Build.BRAND.toString());

         String ИмяСлужбыСинхронизации = "WorkManager ScannerBluetooth";
            try{

                Constraints constraintsСканированиеBluetooth= new Constraints.Builder()
                        .setRequiredNetworkType(NetworkType.CONNECTED)
                        .setRequiresBatteryNotLow(false)
                        .setRequiresStorageNotLow(false)
                        .build();
                PeriodicWorkRequest periodicWorkRequestСинхронизация = new PeriodicWorkRequest.Builder(MyWork_ScannerBluetooth_ДляПосикаДевайсов.class,
                        4, TimeUnit.HOURS)//MIN_PERIODIC_FLEX_MILLIS////  PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS, TimeUnit.MILLISECONDS
                        .addTag(ИмяСлужбыСинхронизации)
                        .setConstraints(constraintsСканированиеBluetooth)
                        .build();
                WorkManager.getInstance(context.getApplicationContext()).enqueueUniquePeriodicWork(ИмяСлужбыСинхронизации,
                        ExistingPeriodicWorkPolicy.KEEP, periodicWorkRequestСинхронизация);
                Log.w(context.getClass().getName(), " ПОСЛЕ ОТРАБОТКИ МЕТОДА ....Внутри BroadcastReceiver_ScannerBluetooth"
                        + periodicWorkRequestСинхронизация.getId() + "\n");
            } catch (Exception e) {
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(context.getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                Log.e(context.getClass().getName(), " BroadcastReceiver_ScannerBluetooth МетодЗапускаСинхоронизацииИзШироковещательногоПриёмника "+e.toString());
            }

                        Log.w(context.getClass().getName(), " BroadcastReceiver_ScannerBluetooth  запуск сканера BlueScanner ");

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(context.getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());;
            Log.e(context.getClass().getName(), " BroadcastReceiver_ScannerBluetooth " + " ОШИБКА ::" + e.toString());

        }
    }
}