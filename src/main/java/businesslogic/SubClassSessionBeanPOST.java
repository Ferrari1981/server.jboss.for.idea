package businesslogic;



import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import dsu1glassfishatomic.workinterfaces.ProducedCard;
import dsu1glassfishatomic.workinterfaces.ProducedJacson;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import model.Cfo;
import model.Settingtab;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.function.BiConsumer;
import java.util.stream.Stream;
import java.util.zip.GZIPInputStream;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.ServletContext;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Produces;

@RequestScoped
@Produces
public class SubClassSessionBeanPOST {//extends    DSU1JsonServlet


    @Inject
    private SubClassConnectionsSQLServer subClassConnectionsSQLServer;
    @Inject
    SubClassGenerateJson subClassGenerateJson;

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
    protected StringBuffer ГлавныйМетод_МетодаPOST(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull ServletContext ЛОГ) throws SecurityException {

        StringBuffer ОтветОтГлавного_МетодаPOSTДляОтправкиНААндройд = null;
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
            ///TODO ПАРАМЕНТ #1
            ПараметрИмяТаблицыОтАндройдаPost = Optional.ofNullable(request.getParameter("ИмяТаблицыОтАндройда")).map(String::trim).orElse("");
            ЛОГ.log("  ПараметрИмяТаблицыОтАндройдаPost " + ПараметрИмяТаблицыОтАндройдаPost);
            ///TODO ПАРАМЕНТ #2
            ///TODO ПАРАМЕНТ #4
            JobsServerСазаданиеДляСервера = Optional.ofNullable(request.getParameter("ЗаданиеДляСервлетаВнутриПотока")).map(String::trim).orElse("");
            //TODO post paramentes
            ЛОГ.log("  ПараметрФильтрПолучаемыхТаблицДляАндройда  " + JobsServerСазаданиеДляСервера);
            ///TODO ПАРАМЕНТ #5
            switch (JobsServerСазаданиеДляСервера.trim()) {
                case "Получение JSON файла от Андройда":
                    // ПРИШЛИ ДАННЫЕ
                    StringBuffer БуферJSONОтАндройда = МетодПолучениеJSONОтКлиента(request);
                    ЛОГ.log("  БуферJSONОтАндройда " + БуферJSONОтАндройда.toString());///// ПРИШЕДШИХ
                    ///// TODO --ПРИШЕЛ ФАЙЛ ОТ КЛИЕНТА JSON
                    if (БуферJSONОтАндройда.toString().toCharArray().length > 3) {///// ЗАХОДИМ											///// КОД
                        ЛОГ.log("  БуферJSONОтАндройда " + БуферJSONОтАндройда.toString());///// ПРИШЕДШИХ
                        ОтветОтГлавного_МетодаPOSTДляОтправкиНААндройд =
                                МетодПарсингаJSONФайлПришелОтКлиента(response,
                                        ПараметрИмяТаблицыОтАндройдаPost,
                                        БуферJSONОтАндройда);
                        ЛОГ.log( " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                + " ОтветОтГлавного_МетодаPOSTДляОтправкиНААндройд "+ОтветОтГлавного_МетодаPOSTДляОтправкиНААндройд.toString());
                    }
                    break;
            }
            ЛОГ.log("ОтветОтГлавного_МетодаPOSTДляОтправкиНААндройд  " + ОтветОтГлавного_МетодаPOSTДляОтправкиНААндройд);
        } catch (Exception e) {
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletDSU1.txt");
        }
        return ОтветОтГлавного_МетодаPOSTДляОтправкиНААндройд;//TODO return  new AsyncResult<StringBuffer>( ОтветОтГлавного_МетодаPOSTДляОтправкиНААндройд);

    }

    protected StringBuffer МетодПарсингаJSONФайлПришелОтКлиента(
            @NotNull HttpServletResponse response,
            @NotNull String ТаблицаPOST,
            @NotNull StringBuffer БуферJSONотAndroid)
            throws InterruptedException, SQLException, BrokenBarrierException, IOException {
        StringBuffer ОтветОтГлавного_МетодаPOSTДляОтправкиНААндройд = new StringBuffer();
        try {
            // convert JSON string to Map
         CopyOnWriteArrayList<Map<String, String>> БуферJSONJackson = getGeneratorJackson.readValue(БуферJSONотAndroid.toString(), new TypeReference<CopyOnWriteArrayList<Map<String, String>>>() {});

            //TODO ГЛАВНЫЙ МЕТОДА POST() КОТОРЫЙ ВСТАВЛЯЕТ  И/ИЛИ ОБНОВЛЕНИЯ ДАННЫХ
           ОтветОтГлавного_МетодаPOSTДляОтправкиНААндройд = subClassGenerateJson.МетодГенерацияJson(ЛОГ, БуферJSONJackson
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
        return ОтветОтГлавного_МетодаPOSTДляОтправкиНААндройд;
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


//функция получающая время операции ДАННАЯ ФУНКЦИЯ ВРЕМЯ ПРИМЕНЯЕТЬСЯ ВО
//ВСЕЙ
//ПРОГРАММЕ


/// TODO Записи ROllBACK

    void МетодЗаписиВЖУрналROLLBACK(Long ПараметрИмяТаблицыОтАндройдаPost, String queryДляОбновленияДанныхМетодPOST) {
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
                    new File("C:\\Program Files\\glassfish-4.1.2 dsu1glassfishatomic\\glassfish4\\ErrorServletDSU1.txt"), true));
            // PrintWriter pw = new PrintWriter(new FileOutputStream(new
            // File("C:\\Users\\User\\git\\dsu1glassfishatomic.glassfish\\src\\dsu1json\\com\\ErrorServletDSU1.txt"),true));
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
            pw.append(queryДляОбновленияДанныхМетодPOST);
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

}