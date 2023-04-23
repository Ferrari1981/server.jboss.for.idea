package runtimes;



import businesslogic.BEANCallsBack;
import businesslogic.SubClassWriterErros;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.jetbrains.annotations.NotNull;
import dsu1glassfishatomic.workinterfaces.ProducedCard;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.ejb.*;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import javax.persistence.StoredProcedureQuery;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringWriter;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;
import java.util.concurrent.ExecutionException;


@Stateless(mappedName = "SessionBeanGETAuthentication")
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class test {// extends WITH

    private ServletContext ЛОГ;
    //private	Connection conn; ////// общий коннект для всего севлтера
    private Statement stmt;
    @SuppressWarnings("unused")
    private String ПубличноеHeaderИмя = null;
    @SuppressWarnings("unused")
    private String ОшибкаВМетодеdoPOST = new String();
    private int КоличествоСтрокКоторыеМыОтправимНаКлиент;
    private String ИмяПолученныйИзSQlServerПосик = null;
    @SuppressWarnings("unused")
    private String ПарольПолученныйОтКлиента = null;
    private String ЛогинПолученныйОтКлиента = null;
    @SuppressWarnings("unused")
    private HttpServletRequest request;
    private HttpServletResponse response;
    private StoredProcedureQuery queryprocedure = null;
    @Inject
    BEANCallsBack bEANCallsBack;

    @Inject
    SubClassWriterErros subClassWriterErros;

    @Inject @ProducedCard
    SessionFactory sessionSousJboss;

    private Session session;
    private Transaction sessionTransaction  ;


    public test() {

        System.out.println("Конструктор  SessionBeanGETAuthentication");

    }









    @SuppressWarnings({ "unused", "deprecation", "rawtypes", "unchecked" })
    protected StringBuffer  МетодЗапускаАунтификации(@NotNull HttpServletRequest request,
                                                     @NotNull ServletContext ЛОГ) throws SecurityException, SQLException {
        // TODO Auto-generated method stub
        StringBuffer БуферCallsBackДляAndroid = new StringBuffer();
        try  {
            this.ЛОГ = ЛОГ;
            // TODO
            this.request = request;
            /// TODO
            this.response = response;
            ЛОГ.log("ЛОГ  GET() " + ЛОГ + " request " + request + " response " + response);
            // TODO получаем session
            /// TODO ПАРАМЕНТ #1
            Integer IdUser = Optional.ofNullable(ЛОГ.getAttribute("IdUser").toString()).map(Integer::new).orElse(0);
            /// TODO ПАРАМЕНТ #2
            String     NameTable = Optional.ofNullable(request.getParameter("NameTable")).map(String::trim).orElse("");
            /// TODO ПАРАМЕНТ #3
            String      JobForServer = Optional.ofNullable(request.getParameter("JobForServer")).map(String::trim).orElse("");
            /// TODO ПАРАМЕНТ #4
            Long VersionData = Optional.ofNullable(request.getParameter("VersionData")).map(Long::new).orElse(0l);

            System.out.println("  IdUser " + IdUser);
            org.hibernate.Query queryДляHiberite = null;
            List<?> ЛистДанныеОтHibenide  = new ArrayList<>();

            if (IdUser>0) {
                // TODO: 10.03.2023 получение сессиии HIREBIANTE
                session=   sessionSousJboss.getCurrentSession();
                // TODO: 17.03.2023 ЗАПУСКАЕТ ТРАНЗАКЦИЮ BEGIN
                // TODO: 10.03.2023 получение сессиии Transaction
                sessionTransaction = session.getTransaction();
                if (sessionTransaction.getStatus()== TransactionStatus.NOT_ACTIVE) {
                    sessionTransaction.begin();
                }
                ЛОГ.log("\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                        " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                        " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+ " session " +session  + " sessionTransaction " +sessionTransaction);
                /// TODO КОНЕЦ  НОВЫЕ ПАРАМЕТРЫ HIREBIANTE
            }
            switch (JobForServer) {
                // TODO ЗАДАНИЕ ДЛЯ СЕРВЕР JOBSERVERTASK #1
                case "Хотим Получить Версию Данных Сервера":
                    ЛистДанныеОтHibenide = МетодДляКлиентаMODIFITATION_Server(session);
                    ЛОГ.log("Хотим Получить Версию Данных Сервера" + new Date() + " ПараметрФильтрЗадааниеДляСервлета "
                            + ЛистДанныеОтHibenide + "  ЛистДанныеОтHibenide ");
                    break;
                // TODO ЗАДАНИЕ ДЛЯ СЕРВЕР JOBSERVERTASK #3
                case "Хотим Получить ID для Генерации  UUID":
                    БуферCallsBackДляAndroid = Метод_МетодаGETОтпалавляемПубличныйIDПользователюАндройду(
                            response, IdUser);
                    ЛОГ.log(" БуферCallsBackДляAndroid "
                            + БуферCallsBackДляAndroid.toString());
                    break;
                // TODO ЗАДАНИЕ ДЛЯ СЕРВЕР JOBSERVERTASK #4
                case "Хотим Получить Статус Блокировки Пользователя по ID":
                    // TODO ОПРЕДЕЛЯЕМ СТАТУС ПОЛЬЗОВАТЕЛЯ
                    ЛистДанныеОтHibenide = Метод_МетодаСтатусЗаблорированогоКлиента( IdUser,session);
                    ЛОГ.log(" Отправили  Хотим Получить Статус Блокировки Пользователя по ID "
                            + JobForServer + " ЛистДанныеОтHibenide " + ЛистДанныеОтHibenide.size() + " IDПолученныйИзSQlServerПосик "+IdUser);
                    break;
                // TODO ЗАДАНИЯ ДЛЯ СЕРВЕРА НЕТУ
                default:
                    ЛОГ.log("\n"+"  default:  Starting.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                            " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                            " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+
                            "Метод_РеальнаяСтатусSqlServer  ЛистДанныеОтHibenide ");
                    break;
            }
            //// TODO ЗАКРЫЫВАЕМ КУРСОРЫ ПОСЛЕ ГЕНЕРАЦИИ JSON ДЛЯ КЛИЕНТА
            // TODO
            //TODO ГЕНЕРАЦИЯ JSON ПО НОВОМУ
            if (ЛистДанныеОтHibenide.size()>0) {
                БуферCallsBackДляAndroid = МетодГенерацияJSONJackson(ЛистДанныеОтHibenide);
            }
            // TODO: 02.04.2023 закрываем сессию
            МетодЗакрываемСессиюHibernate(ЛОГ);

            ЛОГ.log("БуферCallsBackДляAndroid.toString() " + "" + БуферCallsBackДляAndroid.toString()  + " ЛистДанныеОтHibenide " +ЛистДанныеОтHibenide);
            /////// ошибки метода doGET
        } catch (Exception e) {
            sessionTransaction.rollback();
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletAuntification.txt");
        }
        return  БуферCallsBackДляAndroid;
        // AsyncResult<StringBuffer>(БуферCallsBackДляAndroid);
    }




    // TODO еще один перенесенный в метод GEt метод


    protected StringBuffer Метод_МетодаGETОтпалавляемПубличныйIDПользователюАндройду(HttpServletResponse response,
                                                                                     Integer IDПолученныйИзSQlServerПосик) throws IOException {
        StringBuffer ПолученныйИзSqlServerПубличныйIDДляОтправкиНААндройд = new StringBuffer();
        try {
            System.out.println("ИмяПолученныйИзSQlServerПосик			 " + ИмяПолученныйИзSQlServerПосик);
            /// TODO проверяем если мся и пароль н
            ПолученныйИзSqlServerПубличныйIDДляОтправкиНААндройд.append(IDПолученныйИзSQlServerПосик);
            ЛОГ.log("ИмяПолученныйИзSQlServerПосик			 " + ИмяПолученныйИзSQlServerПосик
                    + " ПолученныйИзSqlServerПубличныйIDДляОтправкиНААндройд "
                    + ПолученныйИзSqlServerПубличныйIDДляОтправкиНААндройд.toString()
                    + " finalIDПолученныйИзSQlServerПосик " + IDПолученныйИзSQlServerПосик);
        } catch (Exception e) {
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletAuntification.txt");
        }
        return ПолученныйИзSqlServerПубличныйIDДляОтправкиНААндройд;
    }

    // TODO ГЕНЕРАЦИЯ JSON ПО  НОВОМУ Jackson
    StringBuffer МетодГенерацияJSONJackson(@javax.validation.constraints.NotNull List<?> listОтHiberideДляГенерации)
            throws SQLException, SecurityException {
        StringBuffer БуферСозданогоJSONJackson = new StringBuffer();
        try {
            ЛОГ.log(" listОтHiberideДляГенерации" + listОтHiberideДляГенерации );
            //TODO Jacson парсинг JSON
            JsonFactory factory = new JsonFactory();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", new Locale("ru"));
            final ObjectMapper mapperJackson = new ObjectMapper(factory);
            mapperJackson.setDateFormat(df);
            mapperJackson.setLocale(new Locale("ru"));
            mapperJackson.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            mapperJackson.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
            ObjectWriter writer = mapperJackson.writerWithDefaultPrettyPrinter();
            String Сгенерированыйjson = 	  writer.writeValueAsString(listОтHiberideДляГенерации);
            ЛОГ.  log(" Сгенерированыйjson "+Сгенерированыйjson.length());//gson
            БуферСозданогоJSONJackson.append(Сгенерированыйjson);
            ЛОГ.log(" заработал  Jackson ...  МетодГенерацияJSONJackson --->  БуферСозданогоJSONJackson " + БуферСозданогоJSONJackson.toString() );
            /*
             * // Create custom configuration JsonbConfig nillableConfig = new
             * JsonbConfig().withNullValues(true);
             * nillableConfig.withDateFormat("yyyy-MM-dd HH:mm:ss.SSS", new Locale("ru"));
             * Jsonb jsonb = JsonbBuilder.create(nillableConfig); String resultjsonb =
             * jsonb.toJson(listОтHiberideДляГенерации); ЛОГ.
             * log(" resultjsonb "+resultjsonb.length());//gson
             */
        } catch (Exception e) {
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletAuntification.txt");
        }
        return БуферСозданогоJSONJackson;
    }


    // todo МЕТОД генерируем для килента MODIFITATIONServer
    protected    List<model.ModificationServerEntity> МетодДляКлиентаMODIFITATION_Server(@javax.validation.constraints.NotNull Session session) {
        /////// ВЕРСИЮ ДАННЫХ НА СЕРВЕРЕ
        List<model.ModificationServerEntity> ЛистДанныеОтHibenide  = new ArrayList<>();
        try {
            org.hibernate.Query queryДляHiberite   = session.createQuery(
                    "SELECT vd FROM model.ModificationServerEntity  vd ");
            ЛистДанныеОтHibenide =( List<model.ModificationServerEntity>) queryДляHiberite.getResultList();

            ЛОГ.log("\n"+" Starting.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+
                    " ЛистДанныеОтHibenide " +ЛистДанныеОтHibenide.toString());
        } catch (Exception e) {
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletDSU1.txt");
        }
        return ЛистДанныеОтHibenide;

    }
    // TODO еще генерируем заблокирваный статус клиента
    protected List<model.UsersEntity> Метод_МетодаСтатусЗаблорированогоКлиента(@javax.validation.constraints.NotNull   Integer  IDПолученныйИзSQlServerПосик,
                                                                               @javax.validation.constraints.NotNull Session session ) {
        List<model.UsersEntity> ЛистДанныеОтHibenide  = new ArrayList<>();
        try {
            org.hibernate.Query queryДляHiberite   = session.createQuery("SELECT us  FROM model.UsersEntity  us WHERE us.id  = :id ");
            queryДляHiberite.setParameter("id",new Integer(IDПолученныйИзSQlServerПосик));//8641 8625
            ЛистДанныеОтHibenide =( List<model.UsersEntity>) queryДляHiberite.getResultList();
            ЛОГ.log("\n"+" Starting.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+
                    " ЛистДанныеОтHibenide " +ЛистДанныеОтHibenide.toString());
        } catch (Exception e) {
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletDSU1.txt");
        }
        return ЛистДанныеОтHibenide  ;

    }


    private void МетодЗакрываемСессиюHibernate(@javax.validation.constraints.NotNull ServletContext ЛОГ) {
        try{
            if (session!=null) {
                if (sessionTransaction.getStatus()== TransactionStatus.ACTIVE) {
                    sessionTransaction.commit();
                }

                if (session.isOpen()   || session.isConnected()) {
                    session.close();
                }
                ЛОГ.log("\n МетодЗакрываемСессиюHibernate "+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                        " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                        " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n" +  "session " +session);
            }
        } catch (Exception e) {
            ЛОГ.log( "ERROR class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()  + " e " +e.getMessage() );
            sessionTransaction.rollback();
            session.close();
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletDSU1.txt");
        }
    }
}
