package businesslogic;



import dsu1.glassfish.atomic.SubClassWriterErros;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ExecutionException;
import java.util.zip.GZIPInputStream;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.persistence.EntityManager;
import javax.servlet.ServletContext;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Produces;

@RequestScoped
@Produces
@Named
public class SubClassSessionBeanPOST   {//extends    DSU1JsonServlet



    @Inject
    private SubClassConnectionsSQLServer subClassConnectionsSQLServer;
    @Inject
    SubClassJSONp_Stremming_ОбработкаJSON_FOR_POST subClassJSONp_Stremming_ОбработкаJSON_FOR_POST;

    @SuppressWarnings("unused")
    protected	ServletContext ЛОГ;
    //private	Connection conn; ////// общий коннект для всего севлтера
    @SuppressWarnings("unused")
    private	String ПубличноеHeaderИмя = null;
    private	Statement stmt;
    @SuppressWarnings("unused")
    private	String ОшибкаВМетодеdoPOST = new String();
    @SuppressWarnings("unused")
    private	int КоличествоСтрокКоторыеМыОтправимНаКлиент;
    @SuppressWarnings("unused")
    private	Long РезультатОтАндройдаЕгоЛокальнаяВерсияЧата = 0l;
    @SuppressWarnings("unused")
    private	HttpServletRequest request;
    @SuppressWarnings("unused")
    private	HttpServletResponse response;
    @SuppressWarnings("unused")
    private	Integer ФлагСуществуетЛиВбазеТакойUUIDИеслиЕстьНоБольшеНуляПроизводимОбновлениеАЕслиНольТОВствка = 0;//// TODO
    int ИндексКоличествоПолейdХЭШ = 0;
    String ПараметрИмяТаблицыОтАндройдаPost = new String();
    boolean АутентификацияПользователяПрошлаУспешна = false;///// КОГДА
    String ТолькоДляАунтификацииИмяПолученныйИзSQlServerПосик = new String();
    int СколькСтрокРезультатЕслиТакойПользовательМетод_POST = 0;
    Object СодержимоеВставляемогоСтобцаДляПроверкиНаличиеUUIDОбьект;
    String СодержимоеВставляемогоСтобцаДляПроверкиНаличиеUUID = new String();
    int КоличествоСтолбцовВБАзеSQLSERVER = 0;
    String query = null;
    String JobsServerСазаданиеДляСервера = null;
    String HeaderИмя = new String();
    String HeaderСодержимое = new String();
    ResultSet РезультатСканированиеИмениИПарольМетодPOST = null;
    int СколькСтрокРезультатЕслиТакойПользователь_post_метод = 0;
    String ПарольПолученныйИзSQlServerПосик_МетодPOST = null;


    public SubClassSessionBeanPOST() throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {
        System.out.println("Конструктор  SubClassМетодаBeanSessionPOST");
    }

    /**
     * @param request
     * @param response
     * @throws SecurityException
     */
    @SuppressWarnings("unused")
    protected  StringBuffer  ГлавныйМетод_МетодаPOST(
            @NotNull	  HttpServletRequest request,
            @NotNull 	HttpServletResponse response,
            @NotNull	ServletContext ЛОГ) throws SecurityException {

        StringBuffer	ОтветОтГлавного_МетодаPOSTДляОтправкиНААндройд=null;
        try (Connection  conn =subClassConnectionsSQLServer.МетодПредворительногоПодключенияДляМетодаGETкодИзConnection(	ЛОГ);) {
            ЛОГ.log("Конструктор  ЗАПУСК МЕТОДА ИЗ POST ()  ГлавныйМетод_МетодаPOST()");
            this.ЛОГ = ЛОГ;
            this.request = request;
            this.response = response;
            ЛОГ.log("ЛОГ  POST() " + ЛОГ +  " request " + request + " response "
                    + response );

            ЛОГ.log(" ОТРАБОТАЛ МЕТОД ИНИЦИАЛИЗАЦИИ ПЕРЕМЕННЫХ КОТОРЫ Е ПРИШЛИ  МетодПредворительногоПодключенияДляМетодаGETкодИзStatement    conn"+
                    conn);
            stmt =subClassConnectionsSQLServer.	МетодПредворительногоПодключенияДляМетодаGETкодИзStatement(
                    conn,
                    ЛОГ);
            ЛОГ.log(" ОТРАБОТАЛ МЕТОД ИНИЦИАЛИЗАЦИИ ПЕРЕМЕННЫХ КОТОРЫ Е ПРИШЛИ  МетодПредворительногоПодключенияДляМетодаGETкодИзКонструктора   "+
                    stmt);
            // TODO ПРИШЛИ ПАРАМЕТРЫ В МЕТОДЕ POST
            ///TODO ПАРАМЕНТ #1
            ПараметрИмяТаблицыОтАндройдаPost = Optional.ofNullable( request.getParameter("ИмяТаблицыОтАндройда")).map(String::trim).orElse("");
            ЛОГ.log("  ПараметрИмяТаблицыОтАндройдаPost "+ ПараметрИмяТаблицыОтАндройдаPost);
            ///TODO ПАРАМЕНТ #2
            ///TODO ПАРАМЕНТ #4
            JobsServerСазаданиеДляСервера = Optional.ofNullable( request.getParameter("ЗаданиеДляСервлетаВнутриПотока")).map(String::trim).orElse("");
            //TODO post paramentes
            ЛОГ.log("  ПараметрФильтрПолучаемыхТаблицДляАндройда  "+ JobsServerСазаданиеДляСервера);
            ///TODO ПАРАМЕНТ #5
            switch (JobsServerСазаданиеДляСервера.trim()) {
                case "Получение JSON файла от Андройда":
                    // ПРИШЛИ ДАННЫЕ
                    StringBuffer			БуферJSONОтАндройда = МетодПолучениеJSONОтКлиента(request);
                    ЛОГ.log("  БуферJSONОтАндройда " + БуферJSONОтАндройда.toString());///// ПРИШЕДШИХ
                    ///// TODO --ПРИШЕЛ ФАЙЛ ОТ КЛИЕНТА JSON
                    if (БуферJSONОтАндройда.toString().toCharArray().length > 3) {///// ЗАХОДИМ											///// КОД
                        ЛОГ.log("  БуферJSONОтАндройда " + БуферJSONОтАндройда.toString());///// ПРИШЕДШИХ
                        // Read back
                        JsonReader jsonReaderПришеоОтКлиентаJSON_P = Json.createReader(new StringReader(БуферJSONОтАндройда.toString()));
                        ЛОГ.log(" response "+response.toString()+" ПараметрИмяТаблицыОтАндройдаPost "+ПараметрИмяТаблицыОтАндройдаPost+
                                " jsonReaderПришеоОтКлиентаJSON_P " +jsonReaderПришеоОтКлиентаJSON_P);

                        ОтветОтГлавного_МетодаPOSTДляОтправкиНААндройд=
                                МетодПарсингаJSONФайлПришелОтКлиента(response,
                                        ПараметрИмяТаблицыОтАндройдаPost,
                                        jsonReaderПришеоОтКлиентаJSON_P);
                        ЛОГ.log(" responОтветОтГлавного_МетодаPOSTДляОтправкиНААндройдse "+ОтветОтГлавного_МетодаPOSTДляОтправкиНААндройд);
                    }
                    break;
                default:
                    break;
            }


            ЛОГ.log("ОтветОтГлавного_МетодаPOSTДляОтправкиНААндройд  " + ОтветОтГлавного_МетодаPOSTДляОтправкиНААндройд);
        } catch (Exception e) {
            new SubClassWriterErros().МетодаЗаписиОшибкиВЛог(e,
                    null,
                    "\n"+" Error.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                            " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n" ,
                    Thread.currentThread().getStackTrace()[2],ЛОГ,ОтветОтГлавного_МетодаPOSTДляОтправкиНААндройд.toString());
        }
        return  ОтветОтГлавного_МетодаPOSTДляОтправкиНААндройд;//TODO return  new AsyncResult<StringBuffer>( ОтветОтГлавного_МетодаPOSTДляОтправкиНААндройд);

    }
    /**
     * @param МЕТОД ПАРСИНГ JSON ОТ КЛИЕНТА

     */



    protected
    StringBuffer МетодПарсингаJSONФайлПришелОтКлиента(
            @NotNull	HttpServletResponse response,
            @NotNull	String ПараметрИмяТаблицыОтАндройдаPost,
            @NotNull JsonReader jsonReaderПришеоОтКлиентаJSON_P)
            throws InterruptedException, SQLException, BrokenBarrierException, IOException {
        StringBuffer	ОтветОтГлавного_МетодаPOSTДляОтправкиНААндройд=new StringBuffer();
        try {
            ЛОГ.log(" jsonReaderПришеоОтКлиентаJSON_P "+jsonReaderПришеоОтКлиентаJSON_P.toString());
            JsonObject JSONОБьектjsonReaderПришеоОтКлиентаJSON_P = jsonReaderПришеоОтКлиентаJSON_P.readObject();
            ЛОГ.log(" jsonReaderПришеоОтКлиентаJSON_P "+jsonReaderПришеоОтКлиентаJSON_P.toString()+
                    " JSONОБьектjsonReaderПришеоОтКлиентаJSON_P.getJsonNumber(\"uuid\")"
                    + "  JSONОБьектjsonReaderПришеоОтКлиентаJSON_P.getJsonNumber(\"uuid\") "
                    + "" +JSONОБьектjsonReaderПришеоОтКлиентаJSON_P.getJsonNumber("uuid")+
                    " JSONОБьектjsonReaderПришеоОтКлиентаJSON_P.size()"
                    +JSONОБьектjsonReaderПришеоОтКлиентаJSON_P.size()+
                    " JSONОБьектjsonReaderПришеоОтКлиентаJSON_P.get(\"id\") "+
                    JSONОБьектjsonReaderПришеоОтКлиентаJSON_P.entrySet().parallelStream().findFirst().get().getValue()+
                    "  ПараметрИмяТаблицыОтАндройдаPost " +ПараметрИмяТаблицыОтАндройдаPost);
            //TODO ГЛАВНЫЙ МЕТОДА POST() КОТОРЫЙ ВСТАВЛЯЕТ  И/ИЛИ ОБНОВЛЕНИЯ ДАННЫХ
            ОтветОтГлавного_МетодаPOSTДляОтправкиНААндройд =
                    subClassJSONp_Stremming_ОбработкаJSON_FOR_POST.МетодОбработкиJSONPСтиминг(ЛОГ, JSONОБьектjsonReaderПришеоОтКлиентаJSON_P
                            ,ПараметрИмяТаблицыОтАндройдаPost);
            ЛОГ.log(" jsonReaderПришеоОтКлиентаJSON_P "+jsonReaderПришеоОтКлиентаJSON_P.toString());
        } catch (Exception e) {
            // TODO: handle exception
            new SubClassWriterErros().МетодаЗаписиОшибкиВЛог(e,
                    null,
                    "\n"+" Error.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                            " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n" ,
                    Thread.currentThread().getStackTrace()[2],ЛОГ,ПараметрИмяТаблицыОтАндройдаPost);
        }
        return ОтветОтГлавного_МетодаPOSTДляОтправкиНААндройд;
    }

    protected StringBuffer МетодПолучениеJSONОтКлиента(@NotNull HttpServletRequest request)
            throws IOException, InterruptedException, ExecutionException {
        //TODO ПОЛУЧАЕМ ДАННЫЕ ОТ КЛИЕНТА
        StringBuffer	БуферJSONОтАндройда =new StringBuffer();
        try (		ServletInputStream ОткрываемПотокДляПолученогоJSONотАндройда = request.getInputStream();) {
            ЛОГ.log("Выполяеться метод  МетодПолучениеJSONОтКлиента пришел JSON-поток от клитента на Сервера ");
            BufferedReader БуферJsonОтКлиента = new BufferedReader(
                    new InputStreamReader(new GZIPInputStream(ОткрываемПотокДляПолученогоJSONотАндройда), StandardCharsets.UTF_16));//// ПРИШЕЛ
            БуферJSONОтАндройда = БуферJsonОтКлиента.lines().parallel()
                    .collect(StringBuffer::new, (sb, i) -> sb.append(i), StringBuffer::append);
            int РазмерJSONФайлаПришедшегоОтАндройда = БуферJSONОтАндройда.toString().toCharArray().length;
            ЛОГ.log("Выполяеться метод  МетодПолучениеJSONОтКлиента пришел JSON-поток от клитента на Сервера  + БуферJSONОтАндройда.toString())"
                    + "" + БуферJSONОтАндройда.toString()+  " РазмерJSONФайлаПришедшегоОтАндройда " +РазмерJSONФайлаПришедшегоОтАндройда);
            // TODO закрываем поторк
            БуферJsonОтКлиента.close();
        } catch (Exception e) {
            new SubClassWriterErros().МетодаЗаписиОшибкиВЛог(e,
                    null,
                    "\n"+" Error.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                            " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n" ,
                    Thread.currentThread().getStackTrace()[2],ЛОГ,БуферJSONОтАндройда.toString());
        }
        return БуферJSONОтАндройда;
    }







/// МЕТОД ОБНОВЛЕНИЯ

    @SuppressWarnings("unused")
    private long МетодФиксацииОбновлениеЗапускаетьсяПослеFor(Statement stmtВнутри,
                                                             String ПараметрИмяТаблицыОтАндройдаPostВнутри, StringBuffer БуферСтолбиковиЗначенийДляОбновленияВнутри,
                                                             Long СодержимоеВставляемогоСтобцаДляПроверкиНаличиеUUIDВнутриВнутри,
                                                             String СтильСостыковкиЗаписейЧерезIDилиUUIDВнутри, StringBuffer БуферСколькоУспешногоОбновлений,
                                                             HttpServletResponse response) throws SQLException {

        ////
        long РезультатОбновлениеSQlServerДаннымиОтАндройда = 0l;
        //

        ////
        try {
            //

            ////////

            System.out.println(" БуферСтолбиковиЗначений   результирующий "
                    + БуферСтолбиковиЗначенийДляОбновленияВнутри.toString());
            ///// ЕСЛИ НЕ ПУСТОЙ обновление тут от клиента

            // TODO елси состоялос вставка или обновленеи то запускаем ответ от
            // сервера

            if (БуферСтолбиковиЗначенийДляОбновленияВнутри.toString().toCharArray().length > 0
                    && СтильСостыковкиЗаписейЧерезIDилиUUIDВнутри.length() > 0
                    && СодержимоеВставляемогоСтобцаДляПроверкиНаличиеUUIDВнутриВнутри > 0
                    && ПараметрИмяТаблицыОтАндройдаPostВнутри.length() > 0) {

                //////////
                БуферСтолбиковиЗначенийДляОбновленияВнутри
                        .deleteCharAt(БуферСтолбиковиЗначенийДляОбновленияВнутри.length() - 1); //// ВАЖНО
                //// ПОСЛЕ
                //// FOR
                //// #
                //// ВСТАВКИ
                //// ВСЕХ
                //// ПОЛЕЙ
                //// УДАЛЯЕМ
                //// ПОСЛЕДНИЮ
                //// ЗАПЯТУЮ
                //// В
                //// БУФЕР
                System.out.println(" БуферСтолбиковиЗначений   результирующий "
                        + БуферСтолбиковиЗначенийДляОбновленияВнутри.toString());

                //// ГЛАВНАЯ ОПЕРАЦИЯ САМООБНОВЛЕНИЕ UPDATE SQL SERVER ВСТВКА
                //// ТОЛЬКО ДАТЫ

                /// TODO данная проверка нужна только для таблицы табель
                /// проверяем еслив с нем ХОТЬ ОДИН ДЕНЬЬ ПРИМЕРD1-D31 И ЕСЛИ
                /// НЕТ ТО НЕ ПРОИЗВОДИМ ВСТАВКУ ДАННЫХ НА СЕРВЕР
                boolean ЕслиТакойДень = false;

                ArrayList<String> АрайЛистДниТабеля = new ArrayList<String>(Arrays.asList("d1", "d2", "d3", "d4", "d5",
                        "d6", "d7", "d8", "d9", "d10", "d11", "d12", "d13", "d14", "d15", "d16", "d17", "d18", "d19",
                        "d20", "d21", "d22", "d23", "d24", "d25", "d26", "d27", "d28", "d29", "d30", "d31"));

                if (ПараметрИмяТаблицыОтАндройдаPostВнутри.equalsIgnoreCase("data_tabels")) {
                    //////////
                    String ЗначенияИБуфераДляПосикаДней = БуферСтолбиковиЗначенийДляОбновленияВнутри.toString();

                    for (String КлючИзАрайЛиста : АрайЛистДниТабеля) {

                        System.out.println(КлючИзАрайЛиста);

                        //
                        ЕслиТакойДень = ЗначенияИБуфераДляПосикаДней.contains(КлючИзАрайЛиста);
                        /// TODO ЕСЛИ СТРАБОТАЛО ТО ВЫХОДИМ ИЗ ЦИКЛА
                        if (ЕслиТакойДень == true) {

                            System.out.println("   ЕслиТакойДень " + ЕслиТакойДень);
                            /////////////
                            break;

                        }

                    }

                }

                //// todo

                if (!ПараметрИмяТаблицыОтАндройдаPostВнутри.equalsIgnoreCase("data_tabels")) {

                    //

                    ЕслиТакойДень = true;

                }

                ///////
                String queryДляОбновленияДанныхМетодPOST = null;//

                ////////////////////

                queryДляОбновленияДанныхМетодPOST = "UPDATE    [storage].[dbo].["
                        + ПараметрИмяТаблицыОтАндройдаPostВнутри + "]  SET  "
                        + БуферСтолбиковиЗначенийДляОбновленияВнутри + "  WHERE "
                        + СтильСостыковкиЗаписейЧерезIDилиUUIDВнутри + "='"
                        + СодержимоеВставляемогоСтобцаДляПроверкиНаличиеUUIDВнутриВнутри + "'" + ";"; //// /////ГЛАВНАЯ
                //// СТРОЧКА
                //// МЕТОДА
                //// ОБНОВЛНИЕ ДАННЫХ
                //// обнуляем

                /// БуферСтолбиковиЗначенийДляОбновления=new StringBuffer();
                /// ///после успешной
                /// вставк

                ///// САМА ОПЕРАЦИЯ UPDATE ТАБЛИЦ SQL SERVER ПРИЩЁЛ ИЗ АНДРОЙДА

                /// TODO
                int РезультатОбновлениеОтАндройда = 0;

                // TODO ДАННОЕ УСЛОВИЕ НУЖНО ТОЛЬОКО ДОЛЯ ТАБЛИЦЫ TABELS ДЛЯ
                // ВСЕХ ДРУГИХ ТАБЛИЦ НЕТ ПРОВЕРЯЕМ ЕЛСИВ ТАБЕЛЬЕ ХОТЬ ОДИН ДЕНЬ
                // В1-В31
                // TODO

                if (ЕслиТакойДень == true) {

                    //
                    // TODO БАРЬЕР В РАОТЕ

                    System.out.print(" РезультатОбновлениеSQlServerДаннымиОтАндройда"
                            + РезультатОбновлениеSQlServerДаннымиОтАндройда + " queryДляОбновленияДанныхМетодPOST "
                            + queryДляОбновленияДанныхМетодPOST);

                    ///////////////////////////////

                    final String finalqueryДляОбновленияДанныхМетодPOST = queryДляОбновленияДанныхМетодPOST;
                    ////
                    final Long finalСодержимоеВставляемогоСтобцаДляПроверкиНаличиеUUIDВнутриВнутри = СодержимоеВставляемогоСтобцаДляПроверкиНаличиеUUIDВнутриВнутри;
                    //

                    // TODO

                    System.out.print(" finalСодержимоеВставляемогоСтобцаДляПроверкиНаличиеUUIDВнутриВнутри"
                            + finalСодержимоеВставляемогоСтобцаДляПроверкиНаличиеUUIDВнутриВнутри
                            + " finalqueryДляОбновленияДанныхМетодPOST " + finalqueryДляОбновленияДанныхМетодPOST);

                    РезультатОбновлениеSQlServerДаннымиОтАндройда = 0;

                    try {

                        ////

                        ////

                        // TODO ГЛАВНЯ СТРОКА МЕТОДА САМА ВСТАВКА В АЗУ

                        РезультатОбновлениеSQlServerДаннымиОтАндройда = stmt
                                .executeUpdate(finalqueryДляОбновленияДанныхМетодPOST);//// вставляем
                        //// обновдени
                        //// в
                        //// базу
                        //// sql
                        //// server

                        System.out.print("РезультатОбновлениеSQlServerДаннымиОтАндройда  "
                                + РезультатОбновлениеSQlServerДаннымиОтАндройда
                                + " finalqueryДляОбновленияДанныхМетодPOST " + finalqueryДляОбновленияДанныхМетодPOST);

                    } catch (Exception e) {
                        // TODO: handle exception
                        new SubClassWriterErros().МетодаЗаписиОшибкиВЛог(e,
                                null,
                                "РезультатОбновлениеSQlServerДаннымиОтАндройда = stmt "
                                        + 	"\n"+" Error.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                                        " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                                        " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n" ,
                                Thread.currentThread().getStackTrace()[2],ЛОГ,finalqueryДляОбновленияДанныхМетодPOST);

                    }

                    System.out.print("РезультатОбновлениеSQlServerДаннымиОтАндройда  "
                            + РезультатОбновлениеSQlServerДаннымиОтАндройда + " queryДляОбновленияДанныхМетодPOST "
                            + queryДляОбновленияДанныхМетодPOST);

                    /////////////////////////////

                    /////// todo отпускаем блок

                    //// ГЛАВНАЯ КОМАНД

                    /////// ПОКАЗЫВАЕТ ПРОИЗОШЛА ОБНОЛЕНИЕ SQL SERVER ДАННЫМИ ОТ
                    /////// АНДРОЙДА
                    РезультатОбновлениеОтАндройда = stmt.getUpdateCount();

                    // TODO

                    /////// АНДРОЙДА
                    Long РезультатОбновлениеОтАндройдаДополнительный = stmt.getLargeUpdateCount();

                    System.out.print("РезультатОбновлениеОтАндройда  " + РезультатОбновлениеОтАндройда
                            + " РезультатОбновлениеОтАндройдаДополнительный "
                            + РезультатОбновлениеОтАндройдаДополнительный);

                    //// ИЗУЧАЕМ ПРОИЗОШДА ЛИ ОБНОВЛЕНИЕ ДАННЫХ ЕСЛИ БОЛЬШЕ 0 ТО
                    //// ВСЕ ХОРОШО > 0

                    if (РезультатОбновлениеОтАндройда > 0 || РезультатОбновлениеОтАндройдаДополнительный > 0) { //// проверяем
                        ////// ЗАПОЛНЯЕМ ОБНОВЛЕНИЕ БУФЕР
                        БуферСколькоУспешногоОбновлений.append(" ").append("Обновление ::")
                                .append(СодержимоеВставляемогоСтобцаДляПроверкиНаличиеUUIDВнутриВнутри);/// вствка
                        /// успешного
                        /// для
                        /// перердачи на
                        /// андройд

                        System.out.println(
                                "   БуферСколькоУспешногоОбновлений " + БуферСколькоУспешногоОбновлений.toString());

                        ///
                        System.out.println(
                                "SUCCESS SUCCESS    UPDATE  FOR TABLE :::     " + ПараметрИмяТаблицыОтАндройдаPostВнутри
                                        + " " + СодержимоеВставляемогоСтобцаДляПроверкиНаличиеUUIDВнутриВнутри);

                        /*
                         * //todo ОТПРАЯЛЕМ КЛИНЕТУ СООБЩЕЕНИ CAllbask с
                         * ОБНОВЛЕННЫМИ ДАННЫМ НА СЕРВЕРЕ
                         *
                         * String ОтправляемКлиентуРезультаУспешныхОбновлений =
                         *
                         * МетодРеспонсаОтСервлетаОтправляемАндройдуРезультатыПОСЛЕУСПЕШНОГООБНОВЛЕНИЯЗАПИСЕЙДанныхОтАндройда(
                         * response, БуферСколькоУспешногоОбновлений); ///////
                         * МЕТОД /////// КОТОРЫЙ
                         *
                         *
                         *
                         * /// андройд
                         *
                         * System.out.println(
                         * "   ОтправляемКлиентуРезультаУспешныхОбновлений " +
                         * ОтправляемКлиентуРезультаУспешныхОбновлений); // TODO
                         * КОГДА ДАННЫЕ НЕ ВСТАЛИ
                         */
                    } else {

                        ///// #4
                        /// conn.rollback(); //// нет вставки делаем ROLLBACK
                        //// ТРАНЗАКЦИЮ
                        /////
                        System.out.println(
                                " Ошибка НЕТ обновление  базы данных от Андройда  " + РезультатОбновлениеОтАндройда);
                        // TODO ЗАПИСЬ ROLLBACK
                        МетодЗаписиВЖУрналROLLBACK(СодержимоеВставляемогоСтобцаДляПроверкиНаличиеUUIDВнутриВнутри,queryДляОбновленияДанныхМетодPOST);
                    }

                    // TODO обнуляем после успешной онлвления

                    БуферСтолбиковиЗначенийДляОбновленияВнутри = new StringBuffer();

                    СодержимоеВставляемогоСтобцаДляПроверкиНаличиеUUIDВнутриВнутри = 0l;

                    //// ---СТРОГО КАК ТОЛЬКО МЫ ЗАПОЛНИЛИ В КОНТЕНЕР ДАННЫХ
                    //// СТРОЧКУ JSON ИЗ СЕРВЕРА

                    /////
                    // TODO ОБНУЛЯЕМ ПОСЛЕ УСПЕШНОГО ОБНОВЛЕНИЯ

                    ///////// НЕТ ВСТАВКИ ROLLBACK

                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            new SubClassWriterErros().МетодаЗаписиОшибкиВЛог(e,
                    null,
                    " if (БуферСтолбиковиЗначенийДляОбновленияВнутри.toString().toCharArray().length > 0 "
                            + 	"\n"+" Error.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                            " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                            " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n" ,
                    Thread.currentThread().getStackTrace()[2],ЛОГ,БуферСколькоУспешногоОбновлений.toString());
        }
        ////////
        return РезультатОбновлениеSQlServerДаннымиОтАндройда;
    }





    @SuppressWarnings("unused")
    private long МетодФиксацииВставкиЗапускаетьсяПослеFor(Statement stmtВнутри,
                                                          String ПараметрИмяТаблицыОтАндройдаPostВнутри, StringBuffer БуферСтолбикиДляВставкиВнутриВнутри,
                                                          StringBuffer БуферСамиЗначенияДляВставкиВнутриВнутри,
                                                          Long ЗначениеДляСостыковкиВставкиВнутри,
                                                          Long ПосылаемПослеУсешнойВствкиДанныхНаСерврерКлиентуЕгоUUIDИлиIDВнутри,
                                                          StringBuffer БуферСколькоУспешнойВставки, HttpServletResponse response) throws SQLException {

        // TODO
        int РезультатВставкиОтАндройда = 0;

        long РезультатВставкиSQlServerДаннымиОтАндройда = 0;

        //////

        // Cоздание ТАблиц

        try {

            //////

            System.out.println("   БуферСтолбикиДляВставкиДляВсавки " + БуферСтолбикиДляВставкиВнутриВнутри.toString()
                    + " БуферСамиЗначенияДляВставкиДляВсавки " + БуферСамиЗначенияДляВставкиВнутриВнутри.toString());

            ///// ЕСЛИ НЕ ПУСТОЙ вставка данных тут от клиента
            if (БуферСтолбикиДляВставкиВнутриВнутри.toString().toCharArray().length > 0
                    && БуферСамиЗначенияДляВставкиВнутриВнутри.toString().toCharArray().length > 0
                    && ПараметрИмяТаблицыОтАндройдаPostВнутри.length() > 0) {

                ///// УДАЛЯЕМ ПОСЛЕДНИЙ СИМВОЛ
                БуферСтолбикиДляВставкиВнутриВнутри.setLength(БуферСтолбикиДляВставкиВнутриВнутри.length() - 1); //// ВАЖНО
                //// ПОСЛЕ
                //// FOR
                //// #
                //// ВСТАВКИ
                //// ВСЕХ ПОЛЕЙ УДАЛЯЕМ
                //// ПОСЛЕДНИЮ ЗАПЯТУЮ В
                //// БУФЕР
                ///////
                БуферСамиЗначенияДляВставкиВнутриВнутри.setLength(БуферСамиЗначенияДляВставкиВнутриВнутри.length() - 1); //// ВАЖНО
                //// ПОСЛЕ
                //// FOR
                //// #
                //// ВСТАВКИ ВСЕХ
                //// ПОЛЕЙ УДАЛЯЕМ
                //// ПОСЛЕДНИЮ
                //// ЗАПЯТУЮ В БУФЕР
                //////////

                ////////

                System.out.println("   БуферСтолбикиДляВставкиДляВсавки "
                        + БуферСтолбикиДляВставкиВнутриВнутри.toString() + " БуферСамиЗначенияДляВставкиДляВсавки "
                        + БуферСамиЗначенияДляВставкиВнутриВнутри.toString());

                //// ГЛАВНАЯ ОПЕРАЦИЯ ВСТАВКИ INSERT SQL SERVER ВСТВКА ТОЛЬКО
                //// ДАТЫ

                /// TODO данная проверка нужна только для таблицы табель
                /// проверяем еслив с нем ХОТЬ ОДИН ДЕНЬЬ ПРИМЕРD1-D31 И ЕСЛИ
                /// НЕТ ТО НЕ ПРОИЗВОДИМ ВСТАВКУ ДАННЫХ НА СЕРВЕР
                boolean ЕслиТакойДень = false;

                ArrayList<String> АрайЛистДниТабеля = new ArrayList<String>(Arrays.asList("d1", "d2", "d3", "d4", "d5",
                        "d6", "d7", "d8", "d9", "d10", "d11", "d12", "d13", "d14", "d15", "d16", "d17", "d18", "d19",
                        "d20", "d21", "d22", "d23", "d24", "d25", "d26", "d27", "d28", "d29", "d30", "d31"));

                if (ПараметрИмяТаблицыОтАндройдаPostВнутри.equalsIgnoreCase("data_tabels")) {
                    //////////
                    String ЗначенияИБуфераДляПосикаДней = БуферСтолбикиДляВставкиВнутриВнутри.toString();

                    for (String КлючИзАрайЛиста : АрайЛистДниТабеля) {

                        System.out.println(КлючИзАрайЛиста);

                        //
                        ЕслиТакойДень = ЗначенияИБуфераДляПосикаДней.contains(КлючИзАрайЛиста);
                        /// TODO ЕСЛИ СТРАБОТАЛО ТО ВЫХОДИМ ИЗ ЦИКЛА
                        if (ЕслиТакойДень == true) {

                            System.out.println("   ЕслиТакойДень " + ЕслиТакойДень);
                            /////////////
                            break;

                        }

                    }

                    //// TODO ДЛЯ ВСЕХ ДРУГИХ ТАБЛИЦ НЕ ЯВЛЮЩТИХЯС ТАБЕЛЕМ
                    //// ПРОВЕРКА ЕСЛИ D1,D2,DN НЕ НУЖНО
                } else {
                    //// TODO ДЛЯ ВСЕХ ДРУГИХ ТАБЛИЦ НЕ ЯВЛЮЩТИХЯС ТАБЕЛЕМ
                    //// ПРОВЕРКА ЕСЛИ D1,D2,DN НЕ НУЖНО

                    ///////

                    //

                    ЕслиТакойДень = true;
                }

                System.out.println(" ЕслиТакойДень " + ЕслиТакойДень);

                // TODO САМА ВСТАВКА

                String queryДляВставкиДанныхМетодPOST = null;

                queryДляВставкиДанныхМетодPOST = "INSERT INTO   [storage].[dbo].["
                        + ПараметрИмяТаблицыОтАндройдаPostВнутри + "]   ( " + БуферСтолбикиДляВставкиВнутриВнутри
                        + ")  VALUES ( " + БуферСамиЗначенияДляВставкиВнутриВнутри + ")"; ///// ГЛАВНАЯ
                ///// СТРОЧКА
                ///// МЕТОДА
                ///// ВСТВКИ
                ///// ДАННЫХ
                //////
                ///// ХЭШ ДЛЯ ПЕРЕДАЧИ АНДРОЙД ПОСЛЕ УСПЕШНОГО ВСТАВКИ В SQL
                ///// SERVER

                /*
                 * ПРИМЕР ОБНОВЛЕНИЕ query = "UPDATE [dbo].["
                 * +ПараметрИмяТаблицыОтАндройдаPost +"]  SET  " +
                 * БуферСтолбиковиЗначенийДляВставки + "  WHERE " +
                 * ИмяДляСостыковкиОбновления + "=" +
                 * ЗначениеДляСостыковкиОбновления + ""; ////ГЛАВНАЯ ОПЕРАЦИЯ
                 * ОБНОВЛЕНИЯ
                 */
                ///// САМА ОПЕРАЦИЯ UPDATE ТАБЛИЦ SQL SERVER ПРИЩЁЛ ИЗ АНДРОЙДА

                final String finalqueryДляВставкиДанныхМетодPOST = queryДляВставкиДанныхМетодPOST;

                ///// Если ТакоеДень есть от Д1 до Д31

                // TODO ДАННОЕ УСЛОВИЕ НУЖНО ТОЛЬОКО ДОЛЯ ТАБЛИЦЫ TABELS ДЛЯ
                // ВСЕХ ДРУГИХ ТАБЛИЦ НЕТ ПРОВЕРЯЕМ ЕЛСИВ ТАБЕЛЬЕ ХОТЬ ОДИН ДЕНЬ
                // В1-В31
                if (ЕслиТакойДень == true) {

                    // TODO главная строчка метда вставка данных
                    // непострещственна на севре от клиента

                    РезультатВставкиSQlServerДаннымиОтАндройда = 0l;

                    try {

                        ////
                        РезультатВставкиSQlServerДаннымиОтАндройда = (long) stmt
                                .executeUpdate(finalqueryДляВставкиДанныхМетодPOST);//// вставляем////
                        //// вставляем
                        //// обновдени
                        //// в
                        //// базу
                        //// sql
                        //// server

                        System.out.print("РезультатВставкиSQlServerДаннымиОтАндройда  "
                                + РезультатВставкиSQlServerДаннымиОтАндройда + " finalqueryДляВставкиДанныхМетодPOST "
                                + finalqueryДляВставкиДанныхМетодPOST);

                    } catch (Exception e) {
                        new SubClassWriterErros().МетодаЗаписиОшибкиВЛог(e,
                                null,
                                "\n"+" Error.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                                        " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n" ,
                                Thread.currentThread().getStackTrace()[2],ЛОГ,queryДляВставкиДанныхМетодPOST.toString());
                    }

                    System.out.print(
                            "РезультатВставкиSQlServerДаннымиОтАндройда  " + РезультатВставкиSQlServerДаннымиОтАндройда
                                    + " finalqueryДляВставкиДанныхМетодPOST " + finalqueryДляВставкиДанныхМетодPOST);

                    /////// todo отпускаем блок

                    //// обновдени
                    //// в
                    //// базу
                    //// sql
                    //// server
                    //// ГЛАВНАЯ
                    //// КОМАНДА
                    //// ОБНОВЛЕНИЕ

                    /////// ПОКАЗЫВАЕТ ПРОИЗОШЛА ОБНОЛЕНИЕ SQL SERVER ДАННЫМИ ОТ
                    /////// АНДРОЙДА
                    РезультатВставкиОтАндройда = stmt.getUpdateCount();

                    // TODO

                    System.out.print("РезультатВставкиОтАндройда  " + РезультатВставкиОтАндройда);

                    /////// ПОКАЗЫВАЕТ ПРОИЗОШЛА ОБНОЛЕНИЕ SQL SERVER ДАННЫМИ ОТ
                    /////// АНДРОЙДА
                    Long РезультатВставкиОтАндройдаДополнительное = stmt.getLargeUpdateCount();

                    // TODO

                    System.out.print(
                            "РезультатВставкиОтАндройдаДополнительное  " + РезультатВставкиОтАндройдаДополнительное);

                    //// ИЗУЧАЕМ ПРОИЗОШДА ЛИ ОБНОВЛЕНИЕ ДАННЫХ ЕСЛИ БОЛЬШЕ 0 ТО
                    //// ВСЕ ХОРОШО > 0
                    if (РезультатВставкиОтАндройда > 0 || РезультатВставкиОтАндройдаДополнительное > 0) { //// проверяем
                        //// ЕСЛИ
                        //// ОБНОВЛЕНИЕ
                        //// ПОЛОЖИТЕЛЬНОЕ
                        //// ТО
                        //// БУДЕТ ЦИФРА
                        //// БОЛЬШЕ НУЛЯ > 0
                        ///// #3
                        ///////
                        /*
                         * try { /////// conn.commit(); //// успешная вставка
                         * commit ТРАНЗАКЦИЮ /////// ДАННЫЙ РЕЗУЛЬТАТ ПОСЫЛАЕМ В
                         * АНДРОЙД } catch (SQLException e) { // TODO
                         * Auto-generated catch block e.printStackTrace(); }
                         * //// успешная вставка commit ТРАНЗАКЦИЮ ///////
                         * ДАННЫЙ РЕЗУЛЬТАТ ПОСЫЛАЕМ В АНДРОЙД
                         */
                        /////
                        ////////
                        ////////

                        ///// ЗАПОЛНЯЕМ ВСТАВКОЙ БУФЕР
                        БуферСколькоУспешнойВставки.append(" ").append("Вставка ::")
                                .append(ПосылаемПослеУсешнойВствкиДанныхНаСерврерКлиентуЕгоUUIDИлиIDВнутри);/// вствка
                        /// успешного
                        /// для перердачи на
                        /// андройд

                        // TODO обнуляем пр=осле успешной встаувки ОБНУЛЯЕМ
                        // ПРЕМЕННЫЕ БУФЕРЫ В МЕТОДЕ ВСТАВКИ ДАННЫХ ПОСЛЕ
                        // УСПЕШНОЙ ВСТАВКИ

                        System.out.println("   БуферСколькоУспешнойВставки " + БуферСколькоУспешнойВставки.toString());

                        ///
                        System.out.println("SUCCESS SUCCESS    INSERT  FOR TABLE :::       "
                                + ПараметрИмяТаблицыОтАндройдаPostВнутри + " "
                                + ПосылаемПослеУсешнойВствкиДанныхНаСерврерКлиентуЕгоUUIDИлиIDВнутри);

                        /////// ОТПРАВЛЯЕТ
                        /////// ДАННЫЕ ОБ
                        /////// УСПЕШНОЙ ВСТАВКИ
                        /////// И ОБНОВЕНИЕ
                        /////// ДАННЫЕ НА SQL
                        /////// SERVER И
                        /////// РЕЗУЛЬТАТ
                        /////// ОТПРВЛЯЕМ НА
                        /////// АНДРОЙД

                        ///////////////////

                        /*
                         * //TODO ОТПРАВЛЯЕМ КЛИЕНТУ ОТВЕТ CALLBACK ПОСЛЕ
                         * УСПЕШНОЙ ВСТАВКИ НОВЫХ СТРОЧК В БАЗУ SQL SERVER ОТ
                         * КЛИЕНТА
                         *
                         * String ОтправляемКлиентуРезультаУспешныхВстаок =
                         * МетодРеспонсаОтСервлетаОтправляемАндройдуРезультатПОСЛЕУСПЕШНОЙВСТАВКИВСЕХЗАПИСЕЙОтАндройда(
                         * response,БуферСколькоУспешнойВставки); /////// МЕТОД
                         * /////// КОТОРЫЙ
                         *
                         * // TODO КОДГА ДАННЫЕ НЕ ВСТАВЛИ И МЫ ПОЫЛАЕМ РОЛЛ
                         * БАККК
                         *
                         * ///// System.out.println(
                         * " ОтправляемКлиентуРезультаУспешныхВстаок "
                         * +ОтправляемКлиентуРезультаУспешныхВстаок);
                         */

                    } else {

                        ///// #4
                        /// conn.rollback(); //// нет вставки делаем ROLLBACK
                        //// ТРАНЗАКЦИЮ РезультатВставкиОтАндройда > 0 ||
                        ///// РезультатВставкиОтАндройдаДополнительное>0
                        /////
                        System.out.println(
                                " Ошибка НЕТ обновление  базы данных от Андройда " + "РезультатВставкиОтАндройда "
                                        + РезультатВставкиОтАндройда + " РезультатВставкиОтАндройдаДополнительное "
                                        + РезультатВставкиОтАндройдаДополнительное+ " finalqueryДляВставкиДанныхМетодPOST " +finalqueryДляВставкиДанныхМетодPOST);

                        // TODO ЗАПИСЬ ROLLBACK
                        МетодЗаписиВЖУрналROLLBACK(ПосылаемПослеУсешнойВствкиДанныхНаСерврерКлиентуЕгоUUIDИлиIDВнутри,finalqueryДляВставкиДанныхМетодPOST);
                    }

                    /// TODO обнуление после успешной вставки
                    БуферСтолбикиДляВставкиВнутриВнутри = new StringBuffer();

                    БуферСамиЗначенияДляВставкиВнутриВнутри = new StringBuffer();

                    /////

                    ///////// НЕТ ВСТАВКИ ROLLBACK

                }
            }

        } catch (Exception e) {
            // TODO: handle exception

            new SubClassWriterErros().МетодаЗаписиОшибкиВЛог(e,
                    null,
                    "		if (БуферСтолбикиДляВставкиВнутриВнутри.toString().toCharArray().length > 0 "
                            + "{ //// КОЛИЧЕСТВО СТРОЧЕК" ,
                    Thread.currentThread().getStackTrace()[2],ЛОГ,БуферСколькоУспешнойВставки.toString());
        }

        return РезультатВставкиSQlServerДаннымиОтАндройда;
    }

//функция получающая время операции ДАННАЯ ФУНКЦИЯ ВРЕМЯ ПРИМЕНЯЕТЬСЯ ВО
//ВСЕЙ
//ПРОГРАММЕ


/// TODO Записи ROllBACK

    void МетодЗаписиВЖУрналROLLBACK(Long ПараметрИмяТаблицыОтАндройдаPost,String queryДляОбновленияДанныхМетодPOST) {
        // TODO

        String САМАОШИБКАДЛЯЗАПИСИ = null;
        try {

            САМАОШИБКАДЛЯЗАПИСИ = "Ошибка При Выполнении............ ........ " + "\n" + " Класс :"
                    + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" + " Метод :"
                    + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " Файл  :"
                    + Thread.currentThread().getStackTrace()[2].getFileName();

            String ДатаСервлетаPOSt = new SubClassGeneratorDate().ДатаВремяОперациисБезКовычекЗаписямиСервлета()
                    .toString();

            //
            System.err.println("- Erros   Error Metod POST()->  INSERT/UPDATE EVENTS ROLLBACK  " + ДатаСервлетаPOSt);

            PrintWriter pw = new PrintWriter(new FileOutputStream(
                    new File("C:\\Program Files\\glassfish-4.1.2 dsu1\\glassfish4\\ErrorServletDSU1.txt"), true));
            // PrintWriter pw = new PrintWriter(new FileOutputStream(new
            // File("C:\\Users\\User\\git\\dsu1.glassfish\\src\\dsu1json\\com\\ErrorServletDSU1.txt"),true));
            // перевод строки в байты
            pw.println("- Erros   Error Metod POST()->  INSERT/UPDATE EVENTS ---ROLLBACK--- OPERATIONS-");
            pw.append(" Пользователь protected void doPost  ");
            pw.append(" " + null + "  ");
            pw.append("Дата Роллбэка");
            pw.append(" " + ДатаСервлетаPOSt + "  ");
            pw.append(" Таблица Ошибки ПРИ ROLLBACK");
            pw.append(" " + ПараметрИмяТаблицыОтАндройдаPost + "  ");
            pw.append("\n");
            pw.append("\n");
            pw.append("SQL --запрос QUERY ERROR !!! ");
            pw.append(	queryДляОбновленияДанныхМетодPOST);
            pw.append("\n");
            pw.append(САМАОШИБКАДЛЯЗАПИСИ);
            pw.append("\n");
            pw.append("\n");
            pw.append("\n");
            pw.append("\n");
            pw.flush();
            pw.close();
        } catch (IOException ex) {
            // System.out.println("Ошибка в методе doPOST" + e.toString());

            System.err.println("  Erros   Error Metod POST()->  INSERT/UPDATE EVENTS ROLLBACK " + САМАОШИБКАДЛЯЗАПИСИ);

        }

    }

    protected class SubClassFingUUIDForStream{


        Long  МетодКоторыйВХЭШНадодимUUIDЧерезStream(
                Map<String, Object> ХэшРасперсенныйJSON, String ПолеПоКоторамуНужноИскатьUUID) {
            ////// TODO  начало НАЧИНАЕМ ВСАТВЛЯТЬ НА СЕРВЕР ДАННЫХ ОТ  ХэшРасперсенныйJSON.entrySet()
            //  .filter( КлючХэша -> КлючХэша.getKey() == ПолеПоКоторамуНужноИскатьUUID)
            Long СодержимоеВставляемогоСтобцаДляПроверкиНаличиеUUID=0l;
            try{

	/* Long[] ХэшСодержимоеВставляемогоСтобцаДляПроверкиНаличиеUUID = new Long[1];


	 //TODO
	  ХэшРасперсенныйJSON.keySet().forEach(new java.util.function.Consumer<String>() {
          @Override
          public void accept(String uuidХЭШ) {

        	  ЛОГ.log( " vХэшСодержимоеВставляемогоСтобцаДляПроверкиНаличиеUUID "
        				+ ХэшСодержимоеВставляемогоСтобцаДляПроверкиНаличиеUUID);


        	  if (uuidХЭШ.equalsIgnoreCase(ПолеПоКоторамуНужноИскатьUUID)) {
        	  		//TODO
				ХэшСодержимоеВставляемогоСтобцаДляПроверкиНаличиеUUID[0] = (Long) ХэшРасперсенныйJSON.get(uuidХЭШ);
			}
							ЛОГ.log( " ХэшСодержимоеВставляемогоСтобцаДляПроверкиНаличиеUUID "
        				+ ХэшСодержимоеВставляемогоСтобцаДляПроверкиНаличиеUUID);
          }
      });


	 СодержимоеВставляемогоСтобцаДляПроверкиНаличиеUUID= ХэшСодержимоеВставляемогоСтобцаДляПроверкиНаличиеUUID[0] ;*/


                Object РезультатПолученныйUUID=     ХэшРасперсенныйJSON.get(ПолеПоКоторамуНужноИскатьUUID);

                //TODO

                ЛОГ.log( " РезультатПолученныйUUID "
                        + РезультатПолученныйUUID+ " ПолеПоКоторамуНужноИскатьUUID " +ПолеПоКоторамуНужноИскатьUUID);



                //TODO

                ЛОГ.log( " РезультатПолученныйUUID "
                        + РезультатПолученныйUUID+ " ПолеПоКоторамуНужноИскатьUUID " +ПолеПоКоторамуНужноИскатьUUID);

                //TODO

                if (РезультатПолученныйUUID!=null ) {




                    BigInteger bigСодержимоеВставляемогоСтобцаДляПроверкиНаличиеUUID = new BigInteger(
                            String.valueOf(РезультатПолученныйUUID));
                    //TODO
                    СодержимоеВставляемогоСтобцаДляПроверкиНаличиеUUID = bigСодержимоеВставляемогоСтобцаДляПроверкиНаличиеUUID
                            .longValue();
                    ЛОГ.log(" СодержимоеВставляемогоСтобцаДляПроверкиНаличиеUUID "
                            + СодержимоеВставляемогоСтобцаДляПроверкиНаличиеUUID + " ПолеПоКоторамуНужноИскатьUUID "
                            + ПолеПоКоторамуНужноИскатьUUID);
                }




                //TODO


/*
	  Long   ПосылаемПослеУсешнойВствкиДанныхНаСерврерКлиентуЕгоUUIDИлиIDОбьект=
              ХэшРасперсенныйJSON.entrySet().stream().filter( КлючХэша -> КлючХэша.getKey()
            		  == ПолеПоКоторамуНужноИскатьUUID  )
                      .mapToLong(num -> Long.parseLong(num.getValue().toString()))
                      .findAny().getAsLong();

		ЛОГ.log( " ПосылаемПослеУсешнойВствкиДанныхНаСерврерКлиентуЕгоUUIDИлиIDОбьект "
                + ПосылаемПослеУсешнойВствкиДанныхНаСерврерКлиентуЕгоUUIDИлиIDОбьект);

         Long sum=ХэшРасперсенныйJSON.entrySet()
                 .stream().filter( КлючХэша -> КлючХэша.getKey()
                		 == ПолеПоКоторамуНужноИскатьUUID).mapToLong(num -> Long.parseLong(num.getValue().toString())).findAny().getAsLong();




     	ЛОГ.log( " sum "
                + sum);*/

            } catch (Exception e) {
                new SubClassWriterErros().МетодаЗаписиОшибкиВЛог(e,
                        null,
                        "\n"+" Error.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                                " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n" ,
                        Thread.currentThread().getStackTrace()[2],ЛОГ,
                        "\n"+" Error.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                                " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                                " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"
                                + ""+СодержимоеВставляемогоСтобцаДляПроверкиНаличиеUUID);
            }

            return СодержимоеВставляемогоСтобцаДляПроверкиНаличиеUUID;

        }




    }
}
//todo






//TODO  end subclass metod POST()