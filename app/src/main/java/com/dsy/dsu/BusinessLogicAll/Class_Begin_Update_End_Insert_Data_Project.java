package com.dsy.dsu.BusinessLogicAll;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.dsy.dsu.AllDatabases.SQLTE.GetSQLiteDatabase;
import com.dsy.dsu.BusinessLogicAll.DATE.Class_Generation_Data;


import java.util.concurrent.ExecutionException;

public class Class_Begin_Update_End_Insert_Data_Project {

    Context context;
    //todo

    PUBLIC_CONTENT public_contentcГдеНаходитьсяМенеджерПотоков=null;

    private SQLiteDatabase sqLiteDatabase ;

    public Class_Begin_Update_End_Insert_Data_Project(Context context) {

        this.context = context;
        // TODO: 13.10.2021
        public_contentcГдеНаходитьсяМенеджерПотоков = new PUBLIC_CONTENT(this.context);
        //

        sqLiteDatabase=    GetSQLiteDatabase.SqliteDatabase();

    }

    //функция получающая время операции ДАННАЯ ФУНКЦИЯ ВРЕМЯ ПРИМЕНЯЕТЬСЯ ВО ВСЕЙ ПРОГРАММЕ
    /////TODO МЕТОД ВСТАВКИ ИМЕНИ И ПАРОЛЯ АУНТИФИКАЦИИ БОЛЕЕ 7  ДНЕЙ
    public Object МетодСначалоПытаемсяОбновлитьЕслиНеВышлоВставляемДанныхПроекта(String ОбработываемаяТаблица,
                                                                                 ContentValues КонтейнерДляВставкиИлиОбвноления,
                                                                                 Integer ЗначениеДляОбновления,
                                                                                 String ПолеЧерезКотороеНужноОбновлять) {//"SuccessLogin"/////МЕТОД ВСТАВКИ ИМЕНИ И ПАРОЛЯ АУНТИФИКАЦИИ БОЛЕЕ 7  ДНЕЙ
        ///
        Class_GRUD_SQL_Operations concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций = null;

        Object Результат_АдаптерДляДобавлениеПолученогоУспешноИмяиПарольДляСемидневнойАунтификации = 0;

        try {
            ////НАЧАЛО ВСТКИ И ОЧИСТКИ ДАННЫХ ПО ВСТКАЕ ИМЕНИ  И ПАРОЛЯ ПРИ АУНТИФИКАЦИИ ПОЛЬЗОВАТЕЛЯ БОЛЕЕ 7 ДНЕЙ
            ////

            ///
            // /// для очистки

            ////TODO ДАТА
            String ДатаДЛяОчисткиИВстсвкиИмениИПароль=
                    new Class_Generation_Data(context).ГлавнаяДатаИВремяОперацийСБазойДанных();
            //////////////////////////////////////////////////////////////

            // TODO: 30.08.2021    КОД ВСТАВКИ  ДАННЫХ   ЧЕРЕЗ

            concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций =
                    new Class_GRUD_SQL_Operations(context);


            ///
            concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.
                    concurrentHashMapНабор.put("НазваниеОбрабоатываемойТаблицы",ОбработываемаяТаблица );////"SuccessLogin"
            ///
            ///
            concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.
                    concurrentHashMapНабор.put("Флаг_ЧерезКакоеПолеОбновлением", ПолеЧерезКотороеНужноОбновлять);//"id"
            ///
            ///
            concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.
                    concurrentHashMapНабор.put("ЗначениеФлагОбновления", ЗначениеДляОбновления);
            ///

            //

            concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.
                    concurrentHashMapНабор.put("ЗнакФлагОбновления","="); //или =   или <   >



            //TODO коййтенер


            ///

            concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.
                    contentValuesДляSQLBuilder_Для_GRUD_Операций.putAll(КонтейнерДляВставкиИлиОбвноления);


       /*     ///TODO РЕЗУЛЬТАТ ВСТАВКИ ДАННЫХ
            Результат_АдаптерДляДобавлениеПолученогоУспешноИмяиПарольДляСемидневнойАунтификации = (Integer) concurrentHashMapНабор.
                    new UpdateData(context).updatedata(concurrentHashMapНабор.concurrentHashMapНабор,
                    concurrentHashMapНабор.contentValuesДляSQLBuilder_Для_GRUD_Операций,
                    public_contentcГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,
                    Create_Database_СсылкаНАБазовыйКласс.getССылкаНаСозданнуюБазуORM());
//

            Log.d(this.getClass().getName(), "        Результат_АдаптерДляДобавлениеПолученогоУспешноИмяиПарольДляСемидневнойАунтификации=0L; " +
                    Результат_АдаптерДляДобавлениеПолученогоУспешноИмяиПарольДляСемидневнойАунтификации);


            ////
            if (Результат_АдаптерДляДобавлениеПолученогоУспешноИмяиПарольДляСемидневнойАунтификации == null) {
                ////
                Результат_АдаптерДляДобавлениеПолученогоУспешноИмяиПарольДляСемидневнойАунтификации = 0;
            }
            /////


            // TODO: 02.09.2021если успешно прошла обновление если нет то ниже вроизвонитм вставку
            if (  (Integer) Результат_АдаптерДляДобавлениеПолученогоУспешноИмяиПарольДляСемидневнойАунтификации  > 0) { //ПОДТЕРЖДАЕМ ТОЛЬКО ВСТАВКУ НОВГО ИМЕНИ И ПАРОЛЯ
                // ССылкаНаСозданнуюБазу.

                // TODO: 02.09.2021  успешная обновление данных
                Log.d(this.getClass().getName(), "Запущен.... метод  onCreate в классе Результат_АдаптерДляДобавлениеПолученогоУспешноИмяиПарольДляСемидневнойАунтификации  ; " +
                        Результат_АдаптерДляДобавлениеПолученогоУспешноИмяиПарольДляСемидневнойАунтификации);


                //TODO  ессли не удалось обновить то идем вставлять
            } else {*/




                ///TODO РЕЗУЛЬТАТ ВСТАВКИ ДАННЫХ
                Результат_АдаптерДляДобавлениеПолученогоУспешноИмяиПарольДляСемидневнойАунтификации =
                        (Long) concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.
                        new InsertData(context)
                        .insertdata(concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.concurrentHashMapНабор,
                        concurrentHashMapНаборПараментовSQLBuilder_Для_GRUD_Операций.contentValuesДляSQLBuilder_Для_GRUD_Операций,
                        public_contentcГдеНаходитьсяМенеджерПотоков.МенеджерПотоков,sqLiteDatabase);
//

                Log.d(this.getClass().getName(), "Результат_АдаптерДляДобавлениеПолученогоУспешноИмяиПарольДляСемидневнойАунтификации "
                        + Результат_АдаптерДляДобавлениеПолученогоУспешноИмяиПарольДляСемидневнойАунтификации);


                ////
                if (Результат_АдаптерДляДобавлениеПолученогоУспешноИмяиПарольДляСемидневнойАунтификации == null) {
                    ////
                    Результат_АдаптерДляДобавлениеПолученогоУспешноИмяиПарольДляСемидневнойАунтификации = 0;
                }
                /////

                // TODO: 02.09.2021  успешная вставки данных



        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new Class_Generation_Errors(context).
                    МетодЗаписиВЖурналНовойОшибки(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());

        }

        return Результат_АдаптерДляДобавлениеПолученогоУспешноИмяиПарольДляСемидневнойАунтификации;
        ////// конец записываем полученный имя и пароль во временную таблицу ДЛЯ ТОГО ЧТОБЫ ЕСЛИ НУЖНО ЧЕРЕЗ  7 ДНЕЙ ПОТРЕБУЕТЬСЯ
    }
}