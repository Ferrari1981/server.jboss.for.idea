package com.dsy.dsu.Code_For_AdmissionMaterials.Service;

import android.Manifest;
import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureFailure;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.media.Image;
import android.media.ImageReader;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.util.Size;
import android.view.Surface;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.R;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.core.BackpressureOverflowStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Predicate;

public class ServiceCameraTake extends Service {
    public LocalBinderCamera binder = new LocalBinderCamera();

    private Handler handlerbackgroupCamera;
    private Animation animationscroll;
    private CopyOnWriteArrayList<LinkedHashMap<Long, Bitmap>> copyOnWriteArrayListSuccessAddImages = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<ImageView> copyOnWriteArrayListGetImages = new CopyOnWriteArrayList<>();

    CopyOnWriteArrayList<ImageView> AfrerImageInProsseing = new CopyOnWriteArrayList<>();

    // TODO: 30.07.2023 new varivable


    @Override
    public void onCreate() {
        super.onCreate();
        try {
            // TODO: 28.07.2023
            animationscroll = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_scrolls);
            // TODO: 28.07.2023
            методHandlerCamera();
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
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
    public void onDestroy() {
        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        super.onDestroy();
    }

    public class LocalBinderCamera extends Binder {
        public ServiceCameraTake getService() {
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
            return ServiceCameraTake.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        return binder;
    }


    // TODO: 29.07.2023  main bound metod

    public CopyOnWriteArrayList<LinkedHashMap<Long, Bitmap>> метоСлужбыTakePhotos(@NonNull Intent intent,
                                                                                     @NonNull CopyOnWriteArrayList<ImageView> copyOnWriteArrayListGetImages,
                                                                                     @NonNull Activity activityNewImage) {
        try {
            this.copyOnWriteArrayListGetImages = copyOnWriteArrayListGetImages;
            //////////////////////TODO SERVICE
            // TODO: 29.07.2023 Up Photos
            if (intent.getAction().equalsIgnoreCase("ServiceCameraTake.UpImage")) {
                // TODO: 28.07.2023 вариан первый #1 UP Photo
                copyOnWriteArrayListSuccessAddImages = new SubClassCompleteNewImageUpAndCreate().методОбраобткиUPCompleteImages(intent);

                Log.d(getApplicationContext().getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        "  copyOnWriteArrayListSuccessAddImages " + copyOnWriteArrayListSuccessAddImages + " intent.getAction() " + intent.getAction());
            } else {
                if (intent.getAction().equalsIgnoreCase("ServiceCameraTake.NewFromCameraImage")) {
                    // TODO: 28.07.2023 вариан первый #1 NEw Image Photo
                    copyOnWriteArrayListSuccessAddImages = new SubClassCompleteNewImageUpAndCreate().методОбрабокаNewCreateImageComplete(intent);

                    Log.d(getApplicationContext().getClass().getName(), "\n"
                            + " время: " + new Date() + "\n+" +
                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            "  copyOnWriteArrayListSuccessAddImages " + copyOnWriteArrayListSuccessAddImages + " intent.getAction() " + intent.getAction());
                }


            }


        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return copyOnWriteArrayListSuccessAddImages;
    }


    public void методHandlerCamera() {
        try {
            handlerbackgroupCamera = new Handler(new Handler.Callback() {
                @Override
                public boolean handleMessage(@NonNull Message msg) {
                    Log.d(getApplicationContext().getClass().getName(), "\n"
                            + " время: " + new Date() + "\n+" +
                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
                    return true;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }


    // TODO: 28.07.2023 КЛАССЫ С БИЗЕНС ЛОГИКОЙ  КАМЕРА  // TODO: 28.07.2023 КЛАССЫ С БИЗЕНС ЛОГИКОЙ  КАМЕРА  // TODO: 28.07.2023 КЛАССЫ С БИЗЕНС ЛОГИКОЙ  КАМЕРА  // TODO: 28.07.2023 КЛАССЫ С БИЗЕНС ЛОГИКОЙ  КАМЕРА
    class SubClassCompleteNewImageUpAndCreate {
        CopyOnWriteArrayList<LinkedHashMap<Long, Bitmap>> методОбраобткиUPCompleteImages(@Nullable Intent intentUpPhotos) {
            try {
                // TODO: 24.07.2023
                if (intentUpPhotos != null) {
                    Uri selectedImage = intentUpPhotos.getData();
                    InputStream stream = getApplicationContext().getContentResolver().openInputStream(selectedImage);
                    BufferedInputStream bufferedInputStreamUpLoadImage = new BufferedInputStream(stream);
                    Bitmap bitmapUpImage = BitmapFactory.decodeStream(bufferedInputStreamUpLoadImage);
                    // TODO: 08.08.2023
                    Bundle bundleNewImageCamera=      intentUpPhotos.getExtras();
                    String bitmapPhotoNamegetUUID=    bundleNewImageCamera.getString( "bitmapPhotoNamegetUUID");
                    Long bitmapPhotoOnlyUUID=    bundleNewImageCamera.getLong( "bitmapPhotoOnlyUUID");
                    // TODO: 24.07.2023
                    copyOnWriteArrayListSuccessAddImages = методЗаполянемImageViewNewImage(bitmapUpImage ,bitmapPhotoOnlyUUID);
                    // TODO: 20.07.2023
                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()
                            + "\n" + "  bitmapUpImage " + bitmapUpImage + " copyOnWriteArrayListSuccessAddImages.size() " + copyOnWriteArrayListSuccessAddImages.size()  +
                            bitmapPhotoNamegetUUID +  "bitmapPhotoNamegetUUID ");
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getApplicationContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return copyOnWriteArrayListSuccessAddImages;
        }

        // TODO: 30.07.2023 NEW IMAGE FROM PHOTO

        CopyOnWriteArrayList<LinkedHashMap<Long, Bitmap>> методОбрабокаNewCreateImageComplete(@Nullable Intent intentNewImage) {
            try {
                // TODO: 24.07.2023
                if (intentNewImage != null) {
                    Bundle bundleNewCompleetImages = intentNewImage.getExtras();
                    Bitmap bitmapNewCompleteImage  = bundleNewCompleetImages.getParcelable("bitmapNewCompleteImage") ;
                    Long bitmapPhotoOnlyUUID  = bundleNewCompleetImages.getLong("bitmapPhotoOnlyUUID") ;
                    // TODO: 24.07.2023
                    copyOnWriteArrayListSuccessAddImages = методЗаполянемImageViewNewImage(bitmapNewCompleteImage,bitmapPhotoOnlyUUID);
                    // TODO: 20.07.2023
                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()
                            + "\n" + "  bitmapNewCompleteImage " + bitmapNewCompleteImage + " copyOnWriteArrayListSuccessAddImages.size() " + copyOnWriteArrayListSuccessAddImages.size()
                             +  " bitmapPhotoOnlyUUID " +bitmapPhotoOnlyUUID   + " bitmapNewCompleteImage " +bitmapNewCompleteImage);
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getApplicationContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return copyOnWriteArrayListSuccessAddImages;
        }

        void методОбраобткиUPCompleteImages(@NonNull Context context, @Nullable Uri uriNewImageCamera) {
            try {
                // TODO: 24.07.2023
                if (uriNewImageCamera != null) {
                    InputStream stream = getApplicationContext().getContentResolver().openInputStream(uriNewImageCamera);
                    BufferedInputStream bufferedInputStreamUpLoadImage = new BufferedInputStream(stream);
                    Bitmap bitmapUpImage = BitmapFactory.decodeStream(bufferedInputStreamUpLoadImage);
                    // TODO: 24.07.2023
                    // методЗаполянемImageViewNewImage(bitmapUpImage);
                    // TODO: 20.07.2023
                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()
                            + "\n" + "  bitmapUpImage " + bitmapUpImage);
                }
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

        // TODO: 24.07.2023  заполяем дааныых
        private CopyOnWriteArrayList<LinkedHashMap<Long, Bitmap>> методЗаполянемImageViewNewImage(@NonNull Bitmap bitmapUpImage,@NonNull  Long bitmapPhotoOnlyUUID) {
            try {
                final boolean[] ФлагЧтоУжеОднаВставкаУжеБыла = {false};
                Flowable.fromIterable(copyOnWriteArrayListGetImages)
                        .onBackpressureBuffer()
                        .takeWhile(new Predicate<ImageView>() {
                            @Override
                            public boolean test(ImageView imageView) throws Throwable {
                                if (ФлагЧтоУжеОднаВставкаУжеБыла[0] == false) {
                                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                                            "ФлагЧтоУжеОднаВставкаУжеБыла[0] " + ФлагЧтоУжеОднаВставкаУжеБыла[0]);
                                    return true;
                                } else {
                                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                                            "ФлагЧтоУжеОднаВставкаУжеБыла[0] " + ФлагЧтоУжеОднаВставкаУжеБыла[0]);
                                    return false;
                                }

                            }
                        })
                        .filter(image -> image.getDrawingCache() == null)
                        .doOnNext(new Consumer<ImageView>() {
                            @Override
                            public void accept(ImageView imageView) throws Throwable {
                                try {
                                    // TODO: 25.07.2023  добавление новго Image
                                    Long УжеЕслиТАкойIDImage =
                                            AfrerImageInProsseing.stream()
                                                    .filter(s -> s.getId()==imageView.getId()).collect(Collectors.counting());

                                    if (УжеЕслиТАкойIDImage == 0) {
                                        // TODO: 24.07.2023  set Image
                                        методВставкиImageGenerator(imageView, bitmapUpImage);
                                        // TODO: 25.07.2023 ставим флаг что вставка одна успешно стработал
                                        ФлагЧтоУжеОднаВставкаУжеБыла[0] = true;


                                        методЗапоелнияУжеДобавленыхImage(bitmapPhotoOnlyUUID, bitmapUpImage);


                                    }

                                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                            + " bitmapUpImage " + bitmapUpImage + " imageView.getId() " + imageView.getId() + " УжеЕслиТАкойIDImage " + УжеЕслиТАкойIDImage);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
                                }
                            }
                        })
                        .doOnError(new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Throwable {
                                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
                        })
                        .onErrorComplete(new Predicate<Throwable>() {
                            @Override
                            public boolean test(Throwable throwable) throws Throwable {
                                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                return false;
                            }
                        })
                        .doOnComplete(new Action() {
                            @Override
                            public void run() throws Throwable {
                                // TODO: 24.07.2023
                                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
                        })
                        .blockingSubscribe();


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
            return copyOnWriteArrayListSuccessAddImages;
        }


        private void методВставкиImageGenerator(@NonNull ImageView imageView, @NonNull Bitmap bitmapUpImage) {
            try {
                imageView.setImageBitmap(bitmapUpImage);
                imageView.startAnimation(animationscroll);
                imageView.refreshDrawableState();
                imageView.requestLayout();
                AfrerImageInProsseing.add(imageView);
                Log.d(getApplicationContext().getClass().getName(), "\n" + " imageView.getId() " + imageView.getId());
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }


        private void методЗапоелнияУжеДобавленыхImage(@NonNull Long bitmapPhotoOnlyUUID, @NonNull Bitmap bitmapUpImage) {
            try {
                LinkedHashMap<Long, Bitmap> linkedHashMapCompeleImage = new LinkedHashMap<>();
                linkedHashMapCompeleImage.put(bitmapPhotoOnlyUUID, bitmapUpImage);
                copyOnWriteArrayListSuccessAddImages.add(linkedHashMapCompeleImage);
                Log.d(getApplicationContext().getClass().getName(), "\n" + " copyOnWriteArrayListSuccessAddImages "
                        + copyOnWriteArrayListSuccessAddImages);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

    } // TODO: 24.07.2023  end SubClassCompleteNewImageUpAndCreate
}

    
    
    
    