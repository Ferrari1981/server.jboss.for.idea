package com.dsy.dsu.Dashboard.Fragments;

import android.app.ProgressDialog;
import android.content.DialogInterface;
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
import androidx.annotation.UiThread;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleOwner;

import com.dsy.dsu.Business_logic_Only_Class.Class_Clears_Tables;
import com.dsy.dsu.Business_logic_Only_Class.Class_Connections_Server;
import com.dsy.dsu.Business_logic_Only_Class.Class_Find_Setting_User_Network;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generations_PUBLIC_CURRENT_ID;
import com.dsy.dsu.Business_logic_Only_Class.Class_Generator_One_WORK_MANAGER;
import com.dsy.dsu.Business_logic_Only_Class.PUBLIC_CONTENT;
import com.dsy.dsu.Business_logic_Only_Class.Websocet.WebSocketss;
import com.dsy.dsu.Code_For_Services.ServiceUpdatePoОбновлениеПО;
import com.dsy.dsu.For_Code_Settings_DSU1.MainActivity_Settings;
import com.dsy.dsu.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textview.MaterialTextView;
import com.google.common.util.concurrent.AtomicDouble;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DashboardFragmentSettings#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DashboardFragmentSettings extends DialogFragment {
    // TODO: Rename parameter arguments, choose names that match

    private  BuniccessLogicFra4gmentDashboard buniccessLogicFra4gmentDashboard;
    private MaterialCardView materialcardview_dashboard=null;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private          View ViewDashboart=null;

    private RelativeLayout relativelayout_settings;

    private AlertDialog DialogBox=null;
    private  Handler handlerAsync;
    private MaterialButton КнопкаоСистеме, КнопкаПользователи ,  КнопкаОбменДанными,Кнопкаобновление,КнопкаОшибки;
    private Animation animation1,animation2,animation3,animation4,animation5,animation6;
    private TextView TextViewLogo;
    private LifecycleOwner lifecycleOwner;
    private AppCompatImageButton imageview_back_dashboard ;

    private ServiceUpdatePoОбновлениеПО.localBinderОбновлениеПО localBinderОбновлениеПО;//TODO новаЯ



    public DashboardFragmentSettings() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static DashboardFragmentSettings newInstance( ) {
        DashboardFragmentSettings fragment = new DashboardFragmentSettings();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{

        buniccessLogicFra4gmentDashboard=new BuniccessLogicFra4gmentDashboard();

            fragmentManager = getActivity(). getSupportFragmentManager();

            fragmentTransaction = fragmentManager.beginTransaction();

            // TODO: 17.08.2023 inizial message
            buniccessLogicFra4gmentDashboard.  МетодИнициализацияHandler();

            buniccessLogicFra4gmentDashboard.методGetBinder(getArguments());
            

            //  setStyle(DialogFragment.STYLE_NORMAL,android.R.style.Theme_Material_Dialog_Alert);//Theme_Dialog
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
        setStyle(   DialogFragment.STYLE_NO_FRAME ,android.R.style.Theme_DeviceDefault_Light_NoActionBar_Overscan);//Theme_Dialog
            //setCancelable(false);
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=null;
        try{
       /*     ViewDashboart= inflater.inflate(R.layout.simple_dashbord_fragment_grey, container, false);
            ViewDashboart= inflater.inflate(R.layout.simple_dashbord_fragment_grey, container, false);
            ViewDashboart= inflater.inflate(R.layout.simple_dashbord_fragment_grey, container, false);*/
           // ViewDashboart= inflater.inflate(R.layout.simple_dashbord_fragment_grey, container, false);
           // ViewDashboart= inflater.inflate(R.layout.simple_dashbord_fragment_blue, container, false);
          //  view= inflater.inflate(R.layout.simple_dashbord_fragment_grey, container, false);
            view= inflater.inflate(R.layout.simple_dashbord_fragment_grey_materialdisign_4, container, false);

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

            relativelayout_settings         = (RelativeLayout) view.findViewById(R.id.relativelayout_settings); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА

            КнопкаоСистеме   = (MaterialButton) view.findViewById(R.id.КнопкаоСистеме); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА
            КнопкаПользователи         = (MaterialButton) view.findViewById(R.id.КнопкаПользователи); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА
            КнопкаОбменДанными         = (MaterialButton) view.findViewById(R.id.КнопкаОбменДанными); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА
            Кнопкаобновление          = (MaterialButton) view.findViewById(R.id.Кнопкаобновление); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА
            КнопкаОшибки          = (MaterialButton) view.findViewById(R.id.КнопкаОшибки); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА


            TextViewLogo      = (TextView) view.findViewById(R.id.TextViewLogo); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА
            imageview_back_dashboard      = (AppCompatImageButton) view.findViewById(R.id.imageButton_back_in_settings); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА


            // TODO: 22.08.2023  настйроки анимацуии

            animation1= AnimationUtils.loadAnimation(getContext(),R.anim.slide_in_row3);
            animation2= AnimationUtils.loadAnimation(getContext(),R.anim.slide_in_row5);
            animation3 = AnimationUtils.loadAnimation(getContext(),R.anim.slide_in_row6);
            animation4 = AnimationUtils.loadAnimation(getContext(),R.anim.slide_in_row7);
            animation5 = AnimationUtils.loadAnimation(getContext(),R.anim.slide_in_row8);
            animation6 = AnimationUtils.loadAnimation(getContext(),R.anim.slide_in_row9);



            // TODO: 17.08.2023
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

       buniccessLogicFra4gmentDashboard.методНастройкиВнешнегоВида();

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


    // TODO: 15.08.2023  Бизнес ЛОгика Bunecees Logic Dashboard Fragment

    class BuniccessLogicFra4gmentDashboard{





        private void МетодЗапускаСинихрниазцииИзМенюНаАктивтиFACEAPP() {
            try {
                final Integer[] ФинальныйРезультатФоновойСинхронизации = {0};
                ProgressDialog progressDialogДляСинхронизации;
                progressDialogДляСинхронизации = new ProgressDialog(getActivity());
                progressDialogДляСинхронизации.setTitle("Синхронизация");
                progressDialogДляСинхронизации.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialogДляСинхронизации.setProgress(0);
                progressDialogДляСинхронизации.setCanceledOnTouchOutside(false);
                progressDialogДляСинхронизации.setMessage("Обмен данными ....");
                progressDialogДляСинхронизации.show();

                handlerAsync.post(() -> {
                    boolean СтатусСетиВыбранныйПользователем =
                            new Class_Find_Setting_User_Network(getContext()).МетодПроветяетКакуюУстановкуВыбралПользовательСети();
                    Log.d(this.getClass().getName(), "  РезультатПроВеркиУстановкиПользователяРежимРаботыСети "
                            + СтатусСетиВыбранныйПользователем);
                    Class_Connections_Server class_connections_serverПингаСерераИзАктивтиМеню = new Class_Connections_Server(getActivity());
                    PUBLIC_CONTENT public_contentЗапусСинхрониазцииИМеню = new PUBLIC_CONTENT(getContext());

                    if (СтатусСетиВыбранныйПользователем == true) {
                        Boolean СтатусСервераСоюзаВключенИлиНЕт =
                                class_connections_serverПингаСерераИзАктивтиМеню.МетодПингаСервераРаботаетИлиНет(getContext());
                        if (СтатусСервераСоюзаВключенИлиНЕт == true) {
                            Integer ПубличныйIDДляОдноразовойСинхрониазции =
                                    new Class_Generations_PUBLIC_CURRENT_ID().ПолучениеПубличногоТекущегоПользователяID(getActivity());

                            Bundle bundleДляПЕредачи = new Bundle();
                            bundleДляПЕредачи.putInt("IDПубличныйНеМойАСкемБылаПереписака", ПубличныйIDДляОдноразовойСинхрониазции);
                            bundleДляПЕредачи.putBoolean("StatusOneWokManagers", true);
                            Intent intentЗапускОднорworkanager = new Intent();
                            intentЗапускОднорworkanager.putExtras(bundleДляПЕредачи);
                            // TODO: 02.08.2022
                            // TODO: 02.08.2022
                            new Class_Generator_One_WORK_MANAGER(getContext()).МетодОдноразовыйЗапускВоерМенеджера(getContext(),intentЗапускОднорworkanager);
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
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast toast = Toast.makeText(getContext(), "Сервер выкл. !!!", Toast.LENGTH_LONG);
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
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());

            }
        }
        @UiThread
        protected void СообщениеДляСменыДанныхПользователя(String Самообщение) {
            try {
//////сам вид

                MaterialAlertDialogBuilder materialAlertDialogBuilder= new MaterialAlertDialogBuilder(getActivity())
                        .setTitle("Смена пользователя")
                        .setMessage(Самообщение)
                        .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent Интент_Меню = new Intent();
                                try {
                                    // TODO: 24.04.2023  запуск смены Пользоватедя Данные
                                    ProgressDialog prograssbarСменаДанныхПользователя;
                                    prograssbarСменаДанныхПользователя = new ProgressDialog(getActivity());
                                    prograssbarСменаДанныхПользователя.setTitle("Смена данных");
                                    prograssbarСменаДанныхПользователя.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                    prograssbarСменаДанныхПользователя.setProgress(0);
                                    prograssbarСменаДанныхПользователя.setCanceledOnTouchOutside(false);
                                    prograssbarСменаДанныхПользователя.setMessage("в процессе...");
                                    prograssbarСменаДанныхПользователя.show();

                                    try{
                                        PUBLIC_CONTENT Class_Engine_SQLГдеНаходитьсяМенеджерПотоков = new PUBLIC_CONTENT(getActivity());
                                        Class_Clears_Tables class_clears_tables=     new Class_Clears_Tables(getActivity(),
                                                handlerAsync,
                                                prograssbarСменаДанныхПользователя);

                                        Integer    РезультатОчистикТАблицИДобалениеДаты = class_clears_tables
                                                .методСменаДанныхПользователя(getActivity(),
                                                        Class_Engine_SQLГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,
                                                        getActivity());
                                        Log.d(this.getClass().getName(), "   ЗАПУСК ФОНРезультатОчистикТАблицИДобалениеДаты " +
                                                РезультатОчистикТАблицИДобалениеДаты);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName()
                                                + " Линия  :"
                                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                        new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
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
                                    new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
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
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getActivity()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }







        protected void МетодЗапускаИзМенюНастроек() {
            try {
                Intent Интент_Меню = new Intent(getContext(), MainActivity_Settings.class);
                Интент_Меню.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//////FLAG_ACTIVITY_SINGLE_TOP
                Log.d(this.getClass().getName(), "" +
                        "          case R.id.ПунктМенюВторой:");
                getActivity().startActivity(Интент_Меню);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getActivity()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
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

                классИнициализацииКлиентаWebSocerts.методИнициализацииwebsocets(getActivity());


/*  new Class_Get_Json_1C(getApplicationContext(),"http://uat.dsu1.ru:55080/dds/hs/apidrp/objects")
          .МетодОтправляемJSONОт1СТестирование("userapi","User4321api",new StringBuffer("[ {  \"id\" : null,  \"chosen_organization\" : 0,  \"current_table\" : 273,  \"date_update\" : \"2022-11-27 10:33:20.737\",  \"fullname\" : \"Общество с ограниченной ответственностью СОЮЗ АВТОДОР\",  \"inn\" : \"3711025287\",  \"kpp\" : \"371101001\",  \"name\" : \"ООО СОЮЗ АВТОДОР\",  \"user_update\" : 8,  \"uuid\" : 2} ]"));
            Log.i(this.getClass().getName(), "    holder. ФдагЧтоУжеОдинРАзБылПервыйПроход ");*/

                Log.d(this.getClass().getName(), "   методДляТетсирования1С");
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());

            }
        }













        private void методНастройкиВнешнегоВида() {
            try{


                WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                layoutParams.copyFrom(    getDialog().getWindow().getAttributes());
                layoutParams.gravity = Gravity.CENTER;
                layoutParams.dimAmount=0.0f;

                layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
                layoutParams.height =WindowManager.LayoutParams.WRAP_CONTENT;


                getDialog().getWindow().setAttributes(layoutParams);
                getDialog().getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);




                // TODO: 21.08.2023  ЛОГОТИП
                TextViewLogo.startAnimation(animation6);
                TextViewLogo.refreshDrawableState();

                КнопкаоСистеме.startAnimation(animation5);
                КнопкаоСистеме.refreshDrawableState();


                КнопкаПользователи.startAnimation(animation4);
                КнопкаПользователи.refreshDrawableState();



                КнопкаОбменДанными.startAnimation(animation3);
                КнопкаОбменДанными.refreshDrawableState();


                Кнопкаобновление.startAnimation(animation2);
                Кнопкаобновление.refreshDrawableState();

                КнопкаОшибки.startAnimation(animation1);
                КнопкаОшибки.refreshDrawableState();

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

                                    Toast toast = Toast.makeText(getContext(), "У Вас последняя версия ПО !!! ", Toast.LENGTH_LONG);
                                    toast.setGravity(Gravity.BOTTOM, 0, 40);
                                    toast.show();



                                    Log.d(this.getClass().getName(), "\n" + " class " +
                                            Thread.currentThread().getStackTrace()[2].getClassName()
                                            + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                                    break;

                                case 34:// процеессе

                                    Toast toast3 = Toast.makeText(getContext(), "Нет связи c Cервер ПО !!!", Toast.LENGTH_LONG);
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
                            new   Class_Generation_Errors(getContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),
                                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }


                    }
                };

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


        // TODO: 22.08.2023  классс который отвечает за Переход на Фрагмент НАстройки
        class ClassAnimatilBackButton{
            void методToSettingsFragment(){
                imageview_back_dashboard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try{
                            // TODO Запусукаем Фргамент DdshBoard
                            DashboardFragmentMaterialDesign dashboardFragmentHarmonyOS = new DashboardFragmentMaterialDesign();
                            Bundle data=new Bundle();
                            data.putBinder("binder",localBinderОбновлениеПО);
                            dashboardFragmentHarmonyOS.setArguments(data);
                            fragmentTransaction.replace(R.id.linearLayout_root_activity_dashboard, dashboardFragmentHarmonyOS)
                                    .setPrimaryNavigationFragment(dashboardFragmentHarmonyOS)
                                    .setReorderingAllowed(true).commit();//.layout.activity_for_fragemtb_history_tasks
                            fragmentTransaction.show(dashboardFragmentHarmonyOS);

                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                    + " dashboardFragmentHarmonyOS " +dashboardFragmentHarmonyOS );
                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                    + " dashboardFragmentHarmonyOS " +dashboardFragmentHarmonyOS );


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
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }//TODO end Buniceess Lofic for Activity



}