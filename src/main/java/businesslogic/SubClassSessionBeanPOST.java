package businesslogic;



import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.zip.GZIPInputStream;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Produces;

@RequestScoped
@Produces
public class SubClassSessionBeanPOST {//extends    DSU1JsonServlet

    @Inject
    SubClassВставкаДанныхОтКлиентаPOST subClassВставкаДанныхОтКлиентаPOST;

    @SuppressWarnings("unused")
    protected ServletContext ЛОГ;
    //private	Connection conn; ////// общий коннект для всего севлтера
    @SuppressWarnings("unused")
    private String ПубличноеHeaderИмя = null;
    private Statement stmt;
    @SuppressWarnings("unused")
    private String ОшибкаВМетодеdoPOST = new String();
    @SuppressWarnings("unused")
    private int КоличествоСтрокКоторыеМыОтправимНаКлиент;
    @SuppressWarnings("unused")
    private Long РезультатОтАндройдаЕгоЛокальнаяВерсияЧата = 0l;
    @SuppressWarnings("unused")
    private HttpServletRequest request;
    @SuppressWarnings("unused")
    private HttpServletResponse response;
    @SuppressWarnings("unused")
    private Integer ФлагСуществуетЛиВбазеТакойUUIDИеслиЕстьНоБольшеНуляПроизводимОбновлениеАЕслиНольТОВствка = 0;//// TODO
    int ИндексКоличествоПолейdХЭШ = 0;
    String NameTable = new String();
    boolean АутентификацияПользователяПрошлаУспешна = false;///// КОГДА
    String ТолькоДляАунтификацииИмяПолученныйИзSQlServerПосик = new String();
    int СколькСтрокРезультатЕслиТакойПользовательМетод_POST = 0;
    Object СодержимоеВставляемогоСтобцаДляПроверкиНаличиеUUIDОбьект;
    String СодержимоеВставляемогоСтобцаДляПроверкиНаличиеUUID = new String();
    int КоличествоСтолбцовВБАзеSQLSERVER = 0;
    String query = null;
    String JobForServer = null;
    String HeaderИмя = new String();
    String HeaderСодержимое = new String();
    ResultSet РезультатСканированиеИмениИПарольМетодPOST = null;
    int СколькСтрокРезультатЕслиТакойПользователь_post_метод = 0;
    String ПарольПолученныйИзSQlServerПосик_МетодPOST = null;

    @Inject
    SubClassWriterErros subClassWriterErros;

    @Inject
    ObjectMapper getGeneratorJackson;

    public SubClassSessionBeanPOST() throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {
        System.out.println("Конструктор  SubClassМетодаBeanSessionPOST");
    }

    /**
     * @param request
     * @param response
     * @throws SecurityException
     */
    @SuppressWarnings("unused")
    protected StringBuffer МетодЗапускаPOST(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull ServletContext ЛОГ) throws SecurityException {

        StringBuffer БуферГлавныйГенерацииJSONДляAndroid = null;
        try {
            ЛОГ.log("Конструктор  ЗАПУСК МЕТОДА ИЗ POST ()  ГлавныйМетод_МетодаPOST()");
            this.ЛОГ = ЛОГ;
            this.request = request;
            this.response = response;
            ЛОГ.log("ЛОГ  POST() " + ЛОГ + " request " + request + " response "
                    + response);
            ЛОГ.log(" ОТРАБОТАЛ МЕТОД ИНИЦИАЛИЗАЦИИ ПЕРЕМЕННЫХ КОТОРЫ Е ПРИШЛИ  МетодПредворительногоПодключенияДляМетодаGETкодИзКонструктора   " +
                    stmt);
            // TODO ПРИШЛИ ПАРАМЕТРЫ В МЕТОДЕ POST
            NameTable = Optional.ofNullable(request.getParameter("NameTable")).map(String::trim).orElse("");
            ЛОГ.log("  ПараметрИмяТаблицыОтАндройдаPost " + NameTable);
            ///TODO ПАРАМЕНТ #2
            JobForServer = Optional.ofNullable(request.getParameter("JobForServer")).map(String::trim).orElse("");
            //TODO post paramentes
            ЛОГ.log("  ПараметрФильтрПолучаемыхТаблицДляАндройда  " + JobForServer);
            ///TODO ПАРАМЕНТ #5
            switch (JobForServer.trim()) {
                case "Получение JSON файла от Андройда":
                    StringBuffer БуферJSONОтАндройда = МетодПолучениеJSONОтКлиента(request);
                    ЛОГ.log("  БуферJSONОтАндройда " + БуферJSONОтАндройда.toString());///// ПРИШЕДШИХ
                    if (БуферJSONОтАндройда.toString().toCharArray().length > 3) {///// ЗАХОДИМ											///// КОД
                        ЛОГ.log("  БуферJSONОтАндройда " + БуферJSONОтАндройда.toString());///// ПРИШЕДШИХ
                        БуферГлавныйГенерацииJSONДляAndroid = МетодПарсингаJSONФайлПришелОтКлиента(response, NameTable, БуферJSONОтАндройда);
                        ЛОГ.log( " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                + " БуферГлавныйГенерацииJSONДляAndroid "+БуферГлавныйГенерацииJSONДляAndroid.toString());
                    }
                    break;
            }
            ЛОГ.log("БуферГлавныйГенерацииJSONДляAndroid  " + БуферГлавныйГенерацииJSONДляAndroid);
        } catch (Exception e) {
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletDSU1.txt");
        }
        return БуферГлавныйГенерацииJSONДляAndroid;//TODO return  new AsyncResult<StringBuffer>( БуферГлавныйГенерацииJSONДляAndroid);

    }

    protected StringBuffer МетодПарсингаJSONФайлПришелОтКлиента(
            @NotNull HttpServletResponse response,
            @NotNull String ТаблицаPOST,
            @NotNull StringBuffer БуферJSONотAndroid)
            throws InterruptedException, SQLException, BrokenBarrierException, IOException {
        StringBuffer БуферОтветаПослеОперацииДаннымиОтАндройда = new StringBuffer();
        try {
            // convert JSON string to Map
         CopyOnWriteArrayList<Map<String, String>> БуферJSONJackson = getGeneratorJackson.readValue(БуферJSONотAndroid.toString(),
                 new TypeReference<CopyOnWriteArrayList<Map<String, String>>>() {});
            //TODO ГЛАВНЫЙ МЕТОДА POST() КОТОРЫЙ ВСТАВЛЯЕТ  И/ИЛИ ОБНОВЛЕНИЯ ДАННЫХ
           БуферОтветаПослеОперацииДаннымиОтАндройда = subClassВставкаДанныхОтКлиентаPOST.методВставкаИлиОбновлениеДаннымиОтАкдройда(ЛОГ, БуферJSONJackson
                            , ТаблицаPOST);
            ЛОГ.log( " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                    " БуферJSONJackson " + БуферJSONJackson.size());
        } catch (Exception e) {
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletDSU1.txt");
        }
        return БуферОтветаПослеОперацииДаннымиОтАндройда;
    }

    protected StringBuffer МетодПолучениеJSONОтКлиента(@NotNull HttpServletRequest request)
            throws IOException, InterruptedException, ExecutionException {
        //TODO ПОЛУЧАЕМ ДАННЫЕ ОТ КЛИЕНТА
        StringBuffer БуферJSONОтАндройда = new StringBuffer();
        try (ServletInputStream ОткрываемПотокДляПолученогоJSONотАндройда = request.getInputStream();) {
            ЛОГ.log("Выполяеться метод  МетодПолучениеJSONОтКлиента пришел JSON-поток от клитента на Сервера ");
            BufferedReader БуферJsonОтКлиента = new BufferedReader(
                    new InputStreamReader(new GZIPInputStream(ОткрываемПотокДляПолученогоJSONотАндройда), StandardCharsets.UTF_16));//// ПРИШЕЛ
            БуферJSONОтАндройда = БуферJsonОтКлиента.lines().parallel()
                    .collect(StringBuffer::new, (sb, i) -> sb.append(i), StringBuffer::append);
            int РазмерJSONФайлаПришедшегоОтАндройда = БуферJSONОтАндройда.toString().toCharArray().length;
            ЛОГ.log("Выполяеться метод  МетодПолучениеJSONОтКлиента пришел JSON-поток от клитента на Сервера  + БуферJSONОтАндройда.toString())"
                    + "" + БуферJSONОтАндройда.toString() + " РазмерJSONФайлаПришедшегоОтАндройда " + РазмерJSONФайлаПришедшегоОтАндройда);
            // TODO закрываем поторк
            БуферJsonОтКлиента.close();
        } catch (Exception e) {
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletDSU1.txt");
        }
        return БуферJSONОтАндройда;
    }
}