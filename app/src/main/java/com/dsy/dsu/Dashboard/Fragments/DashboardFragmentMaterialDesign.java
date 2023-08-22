package com.dsy.dsu.Dashboard.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleOwner;

import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.CodeOrdersAnTransports.Window.MainActivityOrdersTransports;
import com.dsy.dsu.Code_ForTABEL.MainActivity_List_Tabels;
import com.dsy.dsu.Code_For_Commit_Payments_КодДля_Согласование.MainActivity_CommitPay;
import com.dsy.dsu.Code_For_Firebase_AndOneSignal_Здесь_КодДЛяСлужбыУведомленияFirebase.Class_Generation_SendBroadcastReceiver_And_Firebase_OneSignal;
import com.dsy.dsu.Code_For_Services.ServiceUpdatePoОбновлениеПО;
import com.dsy.dsu.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DashboardFragmentMaterialDesign#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DashboardFragmentMaterialDesign extends  Fragment {
    // TODO: Rename parameter arguments, choose names that match

    private  BuniccessLogicFra4gmentDashboard buniccessLogicFra4gmentDashboard;
    private MaterialCardView materialcardview_dashboard=null;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private          View ViewDashboart=null;

    private   RelativeLayout relativelayout_dashboard;

    private Animation animation1,animation2,animation3,animation4,animation5;

    private MaterialButton КнопкаТабель, КнопкаСогласование ,  КнопкаПоступлениеМатериалов,КнопкаЗаявкаНаТранспорт;

        private  TextView TextViewLogo;
    private LifecycleOwner lifecycleOwner;

    private ServiceUpdatePoОбновлениеПО.localBinderОбновлениеПО localBinderОбновлениеПО;//TODO новаЯ

    private AppCompatImageButton imageview_to_settings ;


    public DashboardFragmentMaterialDesign() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static DashboardFragmentMaterialDesign newInstance( ) {
        DashboardFragmentMaterialDesign fragment = new DashboardFragmentMaterialDesign();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
            fragmentManager = getActivity(). getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();

              buniccessLogicFra4gmentDashboard=new BuniccessLogicFra4gmentDashboard();
            // TODO: 17.08.2023 inizial message
            lifecycleOwner=getActivity();
            buniccessLogicFra4gmentDashboard. методСлушательФрагментовBinder( );

            buniccessLogicFra4gmentDashboard.методGetBinder(getArguments());

            new Class_Generation_SendBroadcastReceiver_And_Firebase_OneSignal(getContext()).МетодЗапускаетОБЩУЮСинхронизацию();

            // TODO: 21.08.2023 насйтироки Разые дизайна Фрагмента
            /*setStyle(DialogFragment.STYLE_NORMAL,android.R.style.Theme_Material_Dialog_Alert);//Theme_Dialog*/
            // setStyle(DialogFragment.STYLE_NORMAL,android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);//Theme_Dialog
            //  setStyle(DialogFragment.STYLE_NORMAL,android.R.style.Theme_DeviceDefault_Light_NoActionBar_TranslucentDecor);//Theme_Dialog
            // setStyle(DialogFragment.STYLE_NORMAL,android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);//Theme_Dialog
            //  setStyle(DialogFragment.STYLE_NORMAL,android.R.style.Theme_DeviceDefault_Dialog_Alert);//Theme_Dialog
            // setStyle(DialogFragment.STYLE_NORMAL,android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar_MinWidth);//Theme_Dialog
            // setStyle(DialogFragment.STYLE_NO_INPUT,android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar_MinWidth);//Theme_Dialog
            //setStyle(DialogFragment.STYLE_NO_INPUT,android.R.style.Theme_DeviceDefault_Light_Panel);//Theme_Dialog
            //  setStyle(DialogFragment.STYLE_NO_INPUT,android.R.style.Theme_DeviceDefault_InputMethod);//Theme_Dialog
            // setStyle(DialogFragment.STYLE_NO_INPUT,android.R.style.Theme_DeviceDefault_Light_Panel);//Theme_Dialog
            // setStyle(DialogFragment.STYLE_NO_INPUT,android.R.style.Theme_DeviceDefault_Panel);//Theme_Dialog
            // setStyle(DialogFragment.STYLE_NO_INPUT,android.R.style.Theme_DeviceDefault_Settings);//Theme_Dialog
            //setStyle(DialogFragment.STYLE_NO_TITLE,android.R.style.Theme_DeviceDefault_Wallpaper_NoTitleBar);//Theme_Dialog
            // setStyle(DialogFragment.STYLE_NO_FRAME | DialogFragment.STYLE_NO_INPUT,android.R.style.Theme_DeviceDefault_Wallpaper_NoTitleBar);//Theme_Dialog
            //  setStyle(  DialogFragment.STYLE_NO_FRAME | DialogFragment.STYLE_NO_TITLE,android.R.style.Theme_DeviceDefault_Wallpaper_NoTitleBar);//Theme_Dialog
            //    setStyle(   DialogFragment.STYLE_NO_TITLE,android.R.style.Theme_DeviceDefault_Wallpaper_NoTitleBar);//Theme_Dialog
            //  setStyle(   DialogFragment.STYLE_NO_INPUT ,android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);//Theme_Dialog
            // setStyle(   DialogFragment.STYLE_NO_FRAME ,android.R.style.Theme_DeviceDefault_Light_NoActionBar_Overscan);//Theme_Dialog
            // setStyle(   DialogFragment.STYLE_NO_FRAME ,android.R.style.Theme_DeviceDefault_Light_NoActionBar_Overscan);//Theme_Dialog
            //setCancelable(false);

            //setStyle(DialogFragment.STYLE_NO_FRAME,android.R.style.Theme_Material_Dialog);
            // setStyle(   DialogFragment.STYLE_NORMAL ,android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);//Theme_Dialog
            // setStyle(   DialogFragment.STYLE_NORMAL ,android.R.style.Theme_Material_Dialog_MinWidth);//Theme_Dialog
            // setStyle(   DialogFragment.STYLE_NO_TITLE ,android.R.style.Theme_Material_Light);//Theme_Dialog
           // setStyle(   DialogFragment.STYLE_NO_TITLE ,android.R.style.Theme_Material_Light_NoActionBar_Overscan);//Theme_Dialog
        /*    setStyle(   DialogFragment.STYLE_NO_TITLE ,android.R.style.Theme_Material_Light_NoActionBar_Overscan);//Theme_Dialog
            setShowsDialog(true);*/

          getActivity().getTheme().applyStyle(android.R.style.Theme_Material_Dialog, true);

        // TODO: 15.08.2023
        Log.d(this.getClass().getName(),"\n" + " class "
                + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(getContext().getClass().getName(),
                "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        try{
        fragmentManager.setFragmentResult(     "CallBackDashborndFragment",new Bundle());

        Log.d(this.getClass().getName(),"\n" + " class "
                + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(getContext().getClass().getName(),
                "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=null;
        try{
       /*     ViewDashboart= inflater.inflate(R.layout.simple_dashbord_fragment_grey, container, false);
            ViewDashboart= inflater.inflate(R.layout.simple_dashbord_fragment_grey, container, false);
            ViewDashboart= inflater.inflate(R.layout.simple_dashbord_fragment_grey, container, false);*/
           // ViewDashboart= inflater.inflate(R.layout.simple_dashbord_fragment_grey, container, false);
           // ViewDashboart= inflater.inflate(R.layout.simple_dashbord_fragment_blue, container, false);
            view= inflater.inflate(R.layout.simple_dashbord_fragment_grey_materialdisign_3, container, false);
           // view= inflater.inflate(R.layout.simple_dashbord_fragment_green, container, false);
            // TODO: 21.06.2023
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                    + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  view; //TODO inflater.inflate(R.layout.activity_main__tabel_four_colums, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        try{
            super.onViewCreated(view, savedInstanceState);



            ViewDashboart=view;
            relativelayout_dashboard         = (RelativeLayout) view.findViewById(R.id.relativelayout_dashboard); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА



            КнопкаЗаявкаНаТранспорт   = (MaterialButton) view.findViewById(R.id.КнопкаЗаявкаНаТранспорт); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА
            КнопкаСогласование         = (MaterialButton) view.findViewById(R.id.КнопкаСогласование); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА
            КнопкаПоступлениеМатериалов         = (MaterialButton) view.findViewById(R.id.КнопкаПоступлениеМатериалов); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА
            КнопкаТабель          = (MaterialButton) view.findViewById(R.id.КнопкаТабель); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА


            TextViewLogo      = (TextView) view.findViewById(R.id.TextViewLogo); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА

            imageview_to_settings      = (AppCompatImageButton) view.findViewById(R.id.imageview_to_settings); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА

            // TODO: 22.08.2023  анимауия
             animation1= AnimationUtils.loadAnimation(getContext(),R.anim.slide_in_row3);
            animation2= AnimationUtils.loadAnimation(getContext(),R.anim.slide_in_row5);
            animation3 = AnimationUtils.loadAnimation(getContext(),R.anim.slide_in_row6);
            animation4 = AnimationUtils.loadAnimation(getContext(),R.anim.slide_in_row7);
            animation5 = AnimationUtils.loadAnimation(getContext(),R.anim.slide_in_row8);



            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                    + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }





    @Override
    public void onStart() {
        super.onStart();
        try{

            buniccessLogicFra4gmentDashboard.new ClassButtonsApp().методStartingAllButtonApp();


            buniccessLogicFra4gmentDashboard.new ClassAnimatilBackButton().методToSettingsFragment();

        // TODO: 20.07.2023
        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(getContext().getClass().getName(),
                "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }

    @Override
    public void onResume() {
        super.onResume();
        try{

            buniccessLogicFra4gmentDashboard.методНастройкиВнешнегоВида();
            // TODO: 20.072023
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(getContext().getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }


    // TODO: 15.08.2023  Бизнес ЛОгика Bunecees Logic Dashboard Fragment

    class BuniccessLogicFra4gmentDashboard{


        private void методНастройкиВнешнегоВида() {
            try{
                WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                layoutParams.copyFrom(    getActivity().getWindow().getAttributes());
                layoutParams.gravity = Gravity.CENTER;
                layoutParams.dimAmount=0.0f;

                layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
                layoutParams.height =WindowManager.LayoutParams.WRAP_CONTENT;

                getActivity(). getWindow().setAttributes(layoutParams);
                getActivity().  getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);





                // TODO: 21.08.2023  ЛОГОТИП
                TextViewLogo.startAnimation(animation5);
                TextViewLogo.refreshDrawableState();

                КнопкаЗаявкаНаТранспорт.startAnimation(animation4);
                КнопкаЗаявкаНаТранспорт.refreshDrawableState();


                КнопкаСогласование.startAnimation(animation3);
                КнопкаСогласование.refreshDrawableState();



                КнопкаПоступлениеМатериалов.startAnimation(animation2);
                КнопкаПоступлениеМатериалов.refreshDrawableState();


                КнопкаТабель.startAnimation(animation1);
                КнопкаТабель.refreshDrawableState();


                // TODO: 21.08.2023
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }


        // TODO: 17.08.2023  Класс Для Компонента Табель








        private void методСлушательФрагментовBinder( ) {
            try{
                fragmentManager.setFragmentResultListener("callbackbinderdashbord", lifecycleOwner, new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        if (requestKey.equalsIgnoreCase("callbackbinderdashbord")) {
                            try{
                                localBinderОбновлениеПО=(ServiceUpdatePoОбновлениеПО.localBinderОбновлениеПО)       result.getBinder("callbackbinderdashbord");
                                // TODO: 21.08.2023

                                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  +
                                        "  localBinderОбновлениеПО " +localBinderОбновлениеПО);
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e(getContext().getClass().getName(),
                                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }

                        }
                    }
                });
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }


        // TODO: 21.08.2023 Класс с бизнес логикой Кнопки

            class ClassButtonsApp {
                void методStartingAllButtonApp() {


                    // TODO: 14.04.2023 Запускаем Заявка НА Транспорт
                    КнопкаЗаявкаНаТранспорт.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                Log.d(this.getClass().getName(), "Запускает Согласния   ");
                                Intent ИнтентЗаявкаНаТранспорт = new Intent();
                                Bundle data = new Bundle();
                                ИнтентЗаявкаНаТранспорт.putExtras(data);
                                ИнтентЗаявкаНаТранспорт.setClass(getContext(), MainActivityOrdersTransports.class);//рабочий
                                ИнтентЗаявкаНаТранспорт.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(ИнтентЗаявкаНаТранспорт);
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                        Thread.currentThread().getStackTrace()[2].getMethodName(),
                                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }


                        }
                    });
// TODO: 21.08.2023 tretiy button
                    КнопкаСогласование.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                Log.d(this.getClass().getName(), "Запускает Согласния   ");
                                Intent intentЗапускСогласования1C = new Intent();
                                Bundle data = new Bundle();
                                intentЗапускСогласования1C.putExtras(data);
                                intentЗапускСогласования1C.setClass(getContext(), MainActivity_CommitPay.class);//рабочий
                                intentЗапускСогласования1C.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intentЗапускСогласования1C);
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                        Thread.currentThread().getStackTrace()[2].getMethodName(),
                                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
                        }


                    });
                    // TODO: 21.08.2023 hetbertay button

                    // TODO: 14.04.2023 Запускаем Заявка НА Транспорт
                    КнопкаПоступлениеМатериалов.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                Log.d(this.getClass().getName(), "Запускает Согласния   ");
                                Intent ИнтентЗаявкаНаТранспорт = new Intent();
                                Bundle data = new Bundle();
                                ИнтентЗаявкаНаТранспорт.putExtras(data);
                                ИнтентЗаявкаНаТранспорт.setClass(getContext(), MainActivityOrdersTransports.class);//рабочий
                                ИнтентЗаявкаНаТранспорт.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(ИнтентЗаявкаНаТранспорт);
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                        Thread.currentThread().getStackTrace()[2].getMethodName(),
                                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }


                        }
                    });

                    // TODO: 21.08.2023 pyty abutton
                    КнопкаТабель.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                Intent Интент_ЗапускТабельногоУчётаПервыйШаг = new Intent();
                                Bundle data = new Bundle();
                                Интент_ЗапускТабельногоУчётаПервыйШаг.putExtras(data);
                                Интент_ЗапускТабельногоУчётаПервыйШаг.setClass(getContext(), MainActivity_List_Tabels.class); //  ТЕСТ КОД КОТОРЫЙ ЗАПУСКАЕТ ACTIVITY VIEWDATA  ПРОВЕРИТЬ ОБМЕН
                                Интент_ЗапускТабельногоУчётаПервыйШаг.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                Log.d(this.getClass().getName(), "" + "    КнопкаТабельныйУчёт.setOnClickListener(new View.OnClickListener() {");
                               startActivity(Интент_ЗапускТабельногоУчётаПервыйШаг);
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                                        Thread.currentThread().getStackTrace()[2].getMethodName(),
                                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
                        }
                    });

                    // TODO: 21.08.2023  teo button




                }
            }



        // TODO: 22.08.2023  классс который отвечает за Переход на Фрагмент НАстройки
        class ClassAnimatilBackButton{
            void методToSettingsFragment(){
                imageview_to_settings.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try{

                            // TODO Запусукаем Фргамент DdshBoard
                            DashboardFragmentSettings    dashboardFragmentSettings = DashboardFragmentSettings.newInstance();
                            Bundle data=new Bundle();
                            data.putBinder("callbackbinderdashbord",localBinderОбновлениеПО);
                            dashboardFragmentSettings.setArguments(data);
                            String fragmentNewImageNameaddToBackStack=   dashboardFragmentSettings.getClass().getName();
                            fragmentTransaction.addToBackStack(fragmentNewImageNameaddToBackStack);
                            Fragment FragmentУжеЕСтьИлиНЕт=     fragmentManager.findFragmentByTag(fragmentNewImageNameaddToBackStack);
                            if (FragmentУжеЕСтьИлиНЕт==null) {
                                dashboardFragmentSettings.show(fragmentManager, "DashboardFragmentSettings");
                                // TODO: 01.08.2023

                            }
                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                    + " FragmentУжеЕСтьИлиНЕт " +FragmentУжеЕСтьИлиНЕт );




                            Log.i(getContext().getClass().getName(),  " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }
                });




            }


        }



        // TODO: 22.08.2023 МЕТОД ПОЛУЧЕНИЕ ДАННЫХ binder
        void методGetBinder (@NonNull Bundle bundleGetSettings){
            try{

                if (localBinderОбновлениеПО==null) {
                    localBinderОбновлениеПО =(ServiceUpdatePoОбновлениеПО.localBinderОбновлениеПО)  bundleGetSettings.getBinder("callbackbinderdashbord");
                }

                Log.i(getContext().getClass().getName(),  " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  + " bundleGetSettings  " +bundleGetSettings +
                        " localBinderОбновлениеПО " +localBinderОбновлениеПО);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }














        //TODO end Buniceess Lofic for Activity//TODO end Buniceess Lofic for Activity//TODO end Buniceess Lofic for Activity//TODO end Buniceess Lofic for Activity//TODO end Buniceess Lofic for Activity

    }//TODO end Buniceess Lofic for Activity



}