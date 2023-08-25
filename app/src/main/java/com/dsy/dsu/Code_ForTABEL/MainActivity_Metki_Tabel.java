package com.dsy.dsu.Code_ForTABEL;import android.app.Activity;import android.content.Context;import android.content.Intent;import android.content.SharedPreferences;import android.content.pm.ActivityInfo;import android.content.res.Configuration;import android.database.Cursor;import android.database.sqlite.SQLiteCursor;import android.graphics.Typeface;import android.os.Bundle;import android.os.Handler;import android.os.Looper;import android.os.Message;import android.util.Log;import android.view.View;import android.view.WindowManager;import android.view.animation.Animation;import android.view.animation.AnimationUtils;import android.widget.ArrayAdapter;import android.widget.Button;import android.widget.EditText;import android.widget.ListView;import android.widget.ScrollView;import android.widget.TextView;import androidx.annotation.NonNull;import androidx.appcompat.app.AppCompatActivity;import androidx.cursoradapter.widget.CursorAdapter;import androidx.cursoradapter.widget.SimpleCursorAdapter;import androidx.fragment.app.Fragment;import androidx.fragment.app.FragmentManager;import androidx.fragment.app.FragmentTransaction;import com.dsy.dsu.Business_logic_Only_Class.CELLUPDATE.SubClassUpdatesCELL;import com.dsy.dsu.AllDatabases.CREATE_DATABASE;import com.dsy.dsu.Business_logic_Only_Class.Class_GRUD_SQL_Operations;import com.dsy.dsu.Business_logic_Only_Class.Class_Generation_Errors;import com.dsy.dsu.Business_logic_Only_Class.PUBLIC_CONTENT;import com.dsy.dsu.Code_ForTABEL.viewpagers.FragmentSingleTabelOneSwipe;import com.dsy.dsu.Code_ForTABEL.viewpagers.MainActivity_Tabel_Single_PeopleOneSwipe;import com.dsy.dsu.R;import com.google.android.material.textview.MaterialTextView;import java.util.Locale;import java.util.concurrent.CompletionService;public class MainActivity_Metki_Tabel extends AppCompatActivity {    private ScrollView scrollViewMetkiTabels; ////главный linelayuout    private   View КонтентТабеляКоторыйМыИБудемЗаполнятьВнутриЦиклаДляПоиска;    private  EditText   ЗначениеУстановкиМеткиТАбеляИзменяетПользоатель ;    private  ListView ЛистСДаннымиМеткиТаебля=null ;    private Button КнопкаНазадМеткаТабеля;    private  Configuration config;    private  Integer   ПубличноеIDПолученныйИзСервлетаДляUUID=0;    private   CREATE_DATABASE Create_Database_СсылкаНАБазовыйКласс;    private PUBLIC_CONTENT publiccon =null;    private  ArrayAdapter<String> АдаптерДляСпинераДата;    private    String ФИО;    private String МЕсяцДляКурсораТабелей;    private  String ГодДляКурсораТабелей;    private long  РезультатВставкиНовогоТабеляЧерезКонтрейнерТаблицыТабельОбновление = 0;    private long  РезультатВставкиНовогоТабеляЧерезКонтрейнерТаблицыТабель=0;    private  Context context;    private Activity activity;    private  int IDЧьиДанныеДляСотрудников;    private Integer DigitalNameCFO;    private  String ПолноеИмяТабеляПослеСозданиеНовогоСотрудника;    private      Long    UUIDТабеляПослеУспешногоСозданиеСотрудникаВсехСотридников;    private SharedPreferences sharedPreferencesХранилище;    private Bundle bundleДляMainActibvityMetkiTabel;    Animation animationVibr1;    Animation  animationVibr2;    private Message message;    private FragmentManager fragmentManager;    private FragmentTransaction fragmentTransaction;    @Override    protected void onCreate(Bundle savedInstanceState) {        try{            super.onCreate(savedInstanceState);            setContentView(R.layout.activity_main__status_tables);            context =this;            activity=this;            publiccon =new PUBLIC_CONTENT(getApplicationContext());            getSupportActionBar().hide(); ///скрывать тул бар            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);            Create_Database_СсылкаНАБазовыйКласс=new CREATE_DATABASE(getApplicationContext());            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD                    | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON                    | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);            Log.d(this.getClass().getName(), "   ");            // Locale locale = Locale.ROOT;            Locale locale = new Locale("rus");            Locale.setDefault(locale);            config =                    getBaseContext().getResources().getConfiguration();            config.setLocale(locale);            createConfigurationContext(config);            fragmentManager = getSupportFragmentManager();            fragmentTransaction = fragmentManager.beginTransaction();            Log.d(  this.getClass().getName(), " метод посика уже существующего сотрудника в базе андройжа");            scrollViewMetkiTabels = (ScrollView) findViewById(R.id.ГлавныйКойтейнерМетокТабеляСотрудника);            ЛистСДаннымиМеткиТаебля = (ListView) findViewById(R.id.ЛистДляУстановкиМеткиТАбеляРодительская);            КнопкаНазадМеткаТабеля= findViewById(R.id.СтрелкаНазадУстановкиМеткиТабеля);// TODO: 14.10.2022  получением данные из хранилища            sharedPreferencesХранилище=getApplicationContext().getSharedPreferences("sharedPreferencesХранилище", Context.MODE_MULTI_PROCESS);            animationVibr1 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_singletable);            animationVibr2 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_singletable2);            // TODO: 13.04.2023 курсоор            методGetMessage();            методПеременныеFromMainAcivitySingleTabel();            Cursor cursor=методGetCursor( publiccon.МенеджерПотоков);            МетодПриНАжатииНаКнопкуBACK();            методЗаполениеДАнными(cursor);            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+                    "sharedPreferencesХранилище"+  sharedPreferencesХранилище.getString("sharedPreferencesХранилищеkey","") );        } catch (Exception e) {            e.printStackTrace();            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());            new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),                    this.getClass().getName(),                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());        }    }    @Override    protected void onStart() {        super.onStart();        try{            // TODO: 13.04.2023            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+                    "sharedPreferencesХранилище"+  sharedPreferencesХранилище.getString("sharedPreferencesХранилищеkey","") );    } catch (Exception e) {        e.printStackTrace();        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());        new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),                this.getClass().getName(),                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());    }}    //todo метод возврата к предыдущему активти    private void МетодПриНАжатииНаКнопкуBACK() {        try{            КнопкаНазадМеткаТабеля.setOnClickListener(new View.OnClickListener() {                @Override                public void onClick(View v) {                    Log.d(this.getClass().getName(), " кликнем для созданни новго сотрдника при нажатии  ");                    ///todo код которыц возврящет предыдущий актвитики кнопка back                    МетодBackToMainActivityListPeoples();                }            });    } catch (Exception e) {        e.printStackTrace();        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),           this.getClass().getName(),                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());    }    }    private void методПеременныеFromMainAcivitySingleTabel() {        try{            Intent Интент_МеткиТАбеля= getIntent();            bundleДляMainActibvityMetkiTabel=Интент_МеткиТАбеля.getExtras();            // TODO: 10.04.2023            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"                   +" bundleДляMainActibvityMetkiTabel " +bundleДляMainActibvityMetkiTabel);        } catch (Exception e) {            e.printStackTrace();            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());                   new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(), this.getClass().getName(),                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());        }    }    protected Cursor методGetCursor(CompletionService МенеджерПотоковВнутри) {        Cursor cursor=null;        try {            Class_GRUD_SQL_Operations metkitabelgrud =new Class_GRUD_SQL_Operations(getApplicationContext());            metkitabelgrud.concurrentHashMapНабор.put("НазваниеОбрабоатываемойТаблицы","metki_tabel");            metkitabelgrud.concurrentHashMapНабор.put("СтолбцыОбработки","*");            metkitabelgrud.concurrentHashMapНабор.put("УсловиеСортировки","date_update DESC");// TODO: 13.04.2023           cursor= (SQLiteCursor)  metkitabelgrud.                    new GetData(getApplicationContext()).getdata(metkitabelgrud.                           concurrentHashMapНабор,                    МенеджерПотоковВнутри,Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазу());            if (cursor.getCount()>0){                cursor.moveToFirst();                Log.d(this.getClass().getName(), " cursor.getCount()  " +cursor.getCount() );            }else{                Log.d(this.getClass().getName()," cursor"+cursor.getCount() );            }        } catch (Exception e) {            e.printStackTrace();            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),           this.getClass().getName(),                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());        }        return cursor;    }    private void методЗаполениеДАнными(@NonNull Cursor cursor) {        try{            try{                scrollViewMetkiTabels.removeAllViews();            } catch (Exception e) {            }            SimpleCursorAdapter simpleCursorAdapterМеткиТАбеля= new SimpleCursorAdapter(getApplicationContext(),                    R.layout.simple_metki_tabels1, cursor,                    new String[]{ "metka","fullname_metka"},                    new int[]{android.R.id.text1,android.R.id.text2}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);//R.layout.simple_newspinner_dwonload_cfo2            SimpleCursorAdapter.ViewBinder БиндингМеткиТабеля = new SimpleCursorAdapter.ViewBinder(){                @Override                public boolean setViewValue(View view, Cursor cursor, int columnIndex) {                    switch (view.getId()) {                        case android.R.id.text1:                                try{                                    String Метка=    cursor.getString(cursor.getColumnIndex("metka"));                                    bundleДляMainActibvityMetkiTabel.putString("После",Метка.trim());                                    ((TextView)view).setTag(bundleДляMainActibvityMetkiTabel);                                    ((MaterialTextView)view).setText(Метка.trim() );                                    ((MaterialTextView)view).setTypeface(((MaterialTextView) view).getTypeface(), Typeface.BOLD);                                    ((MaterialTextView)view).setOnClickListener(new View.OnClickListener() {                                        @Override                                        public void onClick(View v) {                                            try{                                                SubClassUpdatesCELL subClassUpdateSingletabel=new SubClassUpdatesCELL(getApplicationContext());                                                MaterialTextView materialTextViewМеткаТабеля=(MaterialTextView) v;                                          String НоваяМетка =materialTextViewМеткаТабеля.getText().toString();                                                Integer РезультатОбновлениеЯчейки = subClassUpdateSingletabel.МетодВалидацияЯчеекSaveCellМеткиТАбеля(materialTextViewМеткаТабеля,НоваяМетка);                                                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +                                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +                                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"                                                        + "  РезультатОбновлениеЯчейки"+ РезультатОбновлениеЯчейки);                                                // TODO: 13.04.2023 Обновление Метки Табеля                                                if (РезультатОбновлениеЯчейки>0){                                                    materialTextViewМеткаТабеля.startAnimation(animationVibr2);                                                        ///todo код которыц возврящет предыдущий актвитики кнопка back                                                        МетодBackToMainActivityListPeoples();                                                }                                        } catch (Exception e) {                                            e.printStackTrace();                                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +                                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());                                            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),                                                    this.getClass().getName(),                                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());                                        }                                        }                                    });                                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"                                            + "  ((MaterialTextView)view) "+ ((MaterialTextView)view).getTag());                                    // TODO: 13.12.2022 филь                                } catch (Exception e) {                                    e.printStackTrace();                                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());                                    new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),                                            this.getClass().getName(),                                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());                                }                                return true;                        // TODO: 13.04.2023  text2                        case android.R.id.text2:                    try{                        String ПолнаяМетка=    cursor.getString(cursor.getColumnIndex("fullname_metka"));                        ((MaterialTextView)view).setText(ПолнаяМетка.trim() );                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"                                + "  ((MaterialTextView)view) "+ ((MaterialTextView)view).getTag());                            // TODO: 13.12.2022 филь                    } catch (Exception e) {                        e.printStackTrace();                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());                        new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),                                this.getClass().getName(),                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());                    }                            return true;                    }                    return false;                }            };            simpleCursorAdapterМеткиТАбеля.setViewBinder(БиндингМеткиТабеля);            ЛистСДаннымиМеткиТаебля.setAdapter(simpleCursorAdapterМеткиТАбеля);            ЛистСДаннымиМеткиТаебля.requestLayout();            scrollViewMetkiTabels.requestLayout();            ЛистСДаннымиМеткиТаебля.refreshDrawableState();            scrollViewMetkiTabels.refreshDrawableState();            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"                    + " scrollViewMetkiTabels "+ scrollViewMetkiTabels);        } catch (Exception e) {            e.printStackTrace();            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),           this.getClass().getName(),                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());        }    }    ///todo сообщениеvoid методGetMessage(){try {    message=new Handler(Looper.getMainLooper(), new Handler.Callback() {        @Override        public boolean handleMessage(@NonNull Message msg) {            try{                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");            } catch (Exception e) {                e.printStackTrace();                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());                new Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),                        Thread.currentThread().getStackTrace()[2].getLineNumber());            }            return true;        }    }).obtainMessage();} catch (Exception e) {        e.printStackTrace();        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());        new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),                this.getClass().getName(),                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());    }}    ///todo финалный метод еотровый другому активти посылает данные    private void МетодBackToMainActivityListPeoples() {        try{            Intent intentBackSingleTabel = new Intent();            intentBackSingleTabel.setClass(getApplicationContext(), MainActivity_Tabel_Single_PeopleOneSwipe.class); // Т    MainActivity_List_Peoples.class            intentBackSingleTabel.putExtras(bundleДляMainActibvityMetkiTabel);            startActivity(intentBackSingleTabel);            finish();            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"                    + " bundleДляMainActibvityMetkiTabel "+bundleДляMainActibvityMetkiTabel);        } catch (Exception e) {            e.printStackTrace();            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());            new   Class_Generation_Errors(getApplicationContext()).МетодЗаписиВЖурналНовойОшибки(e.toString(),           this.getClass().getName(),                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());        }    }}