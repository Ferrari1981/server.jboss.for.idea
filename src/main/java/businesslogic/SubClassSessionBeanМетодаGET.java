package businesslogic;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.sun.istack.NotNull;
import dsu1glassfishatomic.workinterfaces.ProducedCard;
import jakarta.transaction.Transactional;
import model.*;
import org.hibernate.*;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import javax.persistence.StoredProcedureQuery;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Produces;
import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;



@Transactional
@RequestScoped
public class SubClassSessionBeanМетодаGET {// extends WITH

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
 
    private Integer ПараметрТекущийПользователь = 0;  //TODO ТЕКУЩИЙ ПОЛЬЗОВАТЕЛЬ
    @SuppressWarnings("unused")
    private HttpServletRequest request;
    private HttpServletResponse response;
    private StoredProcedureQuery queryprocedure = null;



    private    Session session;
    @Inject @ProducedCard
    SessionFactory sessionSousJboss;

    @Inject
    SubClassWriterErros subClassWriterErros;


    @Inject
    ObjectMapper getGeneratorJacksonCbor;

    public SubClassSessionBeanМетодаGET() {

        System.out.println("Конструктор  SubClassSessionBeanМетодаGET");

    }

    @SuppressWarnings({ "unused", "deprecation", "rawtypes", "unchecked" })
    protected byte[] ГлавныйМетод_МетодаGETService(@NotNull HttpServletRequest request,
                                                  @NotNull ServletContext ЛОГ) throws SecurityException, SQLException {
        // TODO Auto-generated method stub
        System.out.println("Конструктор  ЗАПУСК МЕТОДА ИЗ GET ()  ГлавныйМетод_МетодаGET()");
        byte[] БуферCallsBackДляAndroid = new byte[0];
        try   {
            this.ЛОГ = ЛОГ;
            // TODO
            this.request = request;
            /// TODO
            this.response = response;
            // TODO получаем session
            ЛОГ.log("ЗАПУСКАЕТСЯ....... ГЛАВНЫЙ МЕТОД GET() СЕРВЛЕТА " + new Date() + "\n" + ЛОГ.getServerInfo()
                    + "  request " + request + " response " + response + " ЛОГ" + ЛОГ);
            // TODO ГАЛВНЫЙ МЕТОД GET НАЧИНАЕТ РАБОТАТЬ
            ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                    " БуферCallsBackДляAndroid " + БуферCallsBackДляAndroid.toString() +
                    " session  " + session + " sessionSousJboss " + sessionSousJboss
                    + " ЛОГИН "+ЛОГ.getAttribute("ЛогинПолученныйОтКлиента")+
                    " ID ТЕЛЕФОНА "+  ЛОГ.getAttribute("АдуДевайсяКлиента"));
            /// TODO ПАРАМЕНТ #1
            Integer IdUser = Optional.ofNullable( Optional.ofNullable(ЛОГ.getAttribute("IdUser").toString() )
                    .map(String::new ).orElse("0")).stream().mapToInt(Integer::new).findFirst().getAsInt();
            /// TODO ПАРАМЕНТ #2
           String     NameTable = Optional.ofNullable(request.getParameter("NameTable")).map(String::trim).orElse("");
            /// TODO ПАРАМЕНТ #3
             String      JobForServer = Optional.ofNullable(request.getParameter("JobForServer")).map(String::trim).orElse("");
            /// TODO ПАРАМЕНТ #4
            Long VersionData = Optional.ofNullable(request.getParameter("VersionData")).map(Long::new).orElse(0l);


            System.out.println("  IdUser " + IdUser);
            org.hibernate.Query queryДляHiberite = null;
            org.hibernate.Criteria criteriaquery=null;
            List<?> ЛистДанныеОтHibenide  = new ArrayList<>();

            if (IdUser>0) {
                // TODO: 10.03.2023 получение сессиии HIREBIANTE
                session=   sessionSousJboss.getCurrentSession();
                // TODO: 17.03.2023 ЗАПУСКАЕТ ТРАНЗАКЦИЮ BEGIN
                if (session.getTransaction().getStatus()== TransactionStatus.NOT_ACTIVE) {
                    session.getTransaction().begin();
                }
                ЛОГ.log("\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                        " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                        " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+ " session " +session  + " session.getTransaction() " +session.getTransaction());
                /// TODO КОНЕЦ  НОВЫЕ ПАРАМЕТРЫ HIREBIANTE
            }

            switch (JobForServer) {
                // TODO ЗАДАНИЯ ДЛЯ СЕРВЕРА НЕТУ
                // TODO ЗАДАНИЕ ДЛЯ СЕРВЕР JOBSERVERTASK #1
                case "Хотим Получить Версию Данных Сервера":
                    ЛистДанныеОтHibenide = МетодДляКлиентаMODIFITATION_Server(session);
                    ЛОГ.log("Хотим Получить Версию Данных Сервера" + new Date() + " ПараметрФильтрЗадааниеДляСервлета "
                            + ЛистДанныеОтHibenide + "  ЛистДанныеОтHibenide ");
                    break;
                // TODO ЗАДАНИЕ ДЛЯ СЕРВЕР JOBSERVERTASK #3
                case "Хотим Получить ID для Генерации  UUID":
                    ЛистДанныеОтHibenide = Метод_МетодаGETОтпалавляемПубличныйIDПользователюАндройду();
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
                            "Метод_РеальнаяСтатусSqlServer  ЛистДанныеОтHibenide " +ЛистДанныеОтHibenide.size());
                    break;
            }


            ЛОГ.log(" ОТВЕТ КЛИЕНТУ OTBEN LKIENTYY JobForServer " + JobForServer
                    + " БуферCallsBackДляAndroid " + БуферCallsBackДляAndroid.toString()+  "ЛистДанныеОтHibenide "+ ЛистДанныеОтHibenide);



            //TODO ГЕНЕРАЦИЯ JSON ПО НОВОМУ
            if (ЛистДанныеОтHibenide!=null && ЛистДанныеОтHibenide.size()>0) {
                БуферCallsBackДляAndroid = МетодГенерацияJSONJacksonByte(ЛистДанныеОтHibenide);
            }

            // TODO КОГДА ЛОГИН И ПАРОЛЬ НЕТ ДОСТУПА
            МетодЗакрываемСессиюHibernate();
            //// TODO ЗАКРЫЫВАЕМ КУРСОРЫ ПОСЛЕ ГЕНЕРАЦИИ JSON ДЛЯ КЛИЕНТА
            // TODO
            ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                    " БуферCallsBackДляAndroid " + БуферCallsBackДляAndroid.toString() +
                    " session  " + session + " sessionSousJboss " + sessionSousJboss
                    + " ЛОГИН "+ЛОГ.getAttribute("ЛогинПолученныйОтКлиента")+
                    " ID ТЕЛЕФОНА "+  ЛОГ.getAttribute("АдуДевайсяКлиента"));
        } catch (Exception e) {
            ЛОГ.log("\n" + " ERROR class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
            if (session!=null) {
                session.getTransaction().rollback();
                session.close();
            }
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletDSU1.txt");
        }
        return БуферCallsBackДляAndroid; // TODO return new
        // AsyncResult<StringBuffer>(БуферCallsBackДляAndroid);
    }


    // TODO: 06.09.2023  ТОЛЬКО JSON JAJKSON
    @SuppressWarnings({ "unused", "deprecation", "rawtypes", "unchecked" })
    protected byte[] ГлавныйМетод_МетодаGETByte(@NotNull HttpServletRequest request,
                                                  @NotNull ServletContext ЛОГ) throws SecurityException, SQLException {
        // TODO Auto-generated method stub
        System.out.println("Конструктор  ЗАПУСК МЕТОДА ИЗ GET ()  ГлавныйМетод_МетодаGET()");
        byte[] БуферCallsBackДляAndroid = new byte[0];
        try   {
            this.ЛОГ = ЛОГ;
            // TODO
            this.request = request;
            /// TODO
            this.response = response;
            // TODO получаем session
            ЛОГ.log("ЗАПУСКАЕТСЯ....... ГЛАВНЫЙ МЕТОД GET() СЕРВЛЕТА " + new Date() + "\n" + ЛОГ.getServerInfo()
                    + "  request " + request + " response " + response + " ЛОГ" + ЛОГ);
            // TODO ГАЛВНЫЙ МЕТОД GET НАЧИНАЕТ РАБОТАТЬ
            ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                    " session  " + session + " sessionSousJboss " + sessionSousJboss
                    + " ЛОГИН "+ЛОГ.getAttribute("ЛогинПолученныйОтКлиента")+
                    " ID ТЕЛЕФОНА "+  ЛОГ.getAttribute("АдуДевайсяКлиента"));
            /// TODO ПАРАМЕНТ #1
            Integer IdUser = Optional.ofNullable( Optional.ofNullable(ЛОГ.getAttribute("IdUser").toString() )
                    .map(String::new ).orElse("0")).stream().mapToInt(Integer::new).findFirst().getAsInt();
            /// TODO ПАРАМЕНТ #2
            String     NameTable = Optional.ofNullable(request.getParameter("NameTable")).map(String::trim).orElse("");
            /// TODO ПАРАМЕНТ #3
            String      JobForServer = Optional.ofNullable(request.getParameter("JobForServer")).map(String::trim).orElse("");
            /// TODO ПАРАМЕНТ #4
            Long VersionData = Optional.ofNullable(request.getParameter("VersionData")).map(Long::new).orElse(0l);


            System.out.println("  IdUser " + IdUser);
            org.hibernate.Query queryДляHiberite = null;
            org.hibernate.Criteria criteriaquery=null;
            List<?> ЛистДанныеОтHibenide  = new ArrayList<>();

            if (IdUser>0) {
                // TODO: 10.03.2023 получение сессиии HIREBIANTE
                session=   sessionSousJboss.getCurrentSession();
                // TODO: 17.03.2023 ЗАПУСКАЕТ ТРАНЗАКЦИЮ BEGIN
                if (session.getTransaction().getStatus()== TransactionStatus.NOT_ACTIVE) {
                    session.getTransaction().begin();
                }
                ЛОГ.log("\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                        " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                        " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+ " session " +session  + " session.getTransaction() " +session.getTransaction());
                /// TODO КОНЕЦ  НОВЫЕ ПАРАМЕТРЫ HIREBIANTE
            }

            switch (JobForServer) {
                // TODO ЗАДАНИЕ ДЛЯ СЕРВЕР JOBSERVERTASK #2
                case "Хотим Получить  JSON":
                    ЛОГ.log("Хотим Получить  JSON" + new Date() + " JobForServer "
                            + JobForServer+"  VersionData" + VersionData
                            + " NameTable " + NameTable);
                    ////////////// ГЕНЕРАЦИЯ JSON ДЛЯ ВСЕХ  ТАБЛИЦ
                    // TODO ГЛАВНЫЙ РАСПРЕДЕЛИТЕЛЬ КАКАЯ ТЕКУЩАЯ ТАБЛИЦА ОБРАБАТЫВАЕМСЯ
                    switch (NameTable.trim()) {
                        case "organization":
                            // TODO
                            queryДляHiberite  = session.createQuery(
                                    " SELECT o FROM  Organization o WHERE o.currentTable > :id ");
                            queryДляHiberite.setParameter("id",new BigDecimal(VersionData ));//
                            ЛистДанныеОтHibenide =( List<model.Organization>)  queryДляHiberite.getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite+ " NameTable " +NameTable);//gson Gson
                            break;
                        case "depatment":
                            // TODO
                            queryДляHiberite = session.createQuery(
                                    "SELECT d FROM  Depatment d   WHERE d.currentTable > :id ");

                            queryДляHiberite.setParameter("id",new BigDecimal(VersionData ));
                            ЛистДанныеОтHibenide =( List<model.Depatment>)  queryДляHiberite.getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;
                        case "fio":
                            // TODO
                            queryДляHiberite = session.createQuery(
                                    "SELECT f FROM Fio f   WHERE f.currentTable > :id ");
                            queryДляHiberite.setParameter("id",new BigDecimal(VersionData ));//
                            ЛистДанныеОтHibenide =( List<model.Fio>)  queryДляHiberite.getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;
                        case "region":
                            // TODO
                            queryДляHiberite = session.createQuery(
                                    "SELECT r  FROM Region r   WHERE r.currentTable > :id");
                            queryДляHiberite.setParameter("id",new BigDecimal(VersionData ));//
                            ЛистДанныеОтHibenide =( List<model.Region>)  queryДляHiberite.getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;
                        case "cfo":
                            // TODO
                            queryДляHiberite = session.createQuery(
                                    "SELECT c FROM Cfo  c  WHERE c.currentTable > :id  AND c.closed=:closed");
                            queryДляHiberite.setParameter("id",new BigDecimal(VersionData ));//
                            queryДляHiberite.setParameter("closed",   new Boolean(false));//
                            ЛистДанныеОтHibenide =( List<model.Cfo>)  queryДляHiberite.getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;
                        case "settings_tabels":
                            // TODO
                            queryДляHiberite = session.createQuery(
                                    "SELECT st FROM Settingtab  st  WHERE st.currentTable > :id  AND  st.userUpdate=:user_update ");
                            queryДляHiberite.setParameter("id",new BigDecimal(VersionData ));//
                            queryДляHiberite.setParameter("user_update",IdUser);//8641 8625
                            ЛистДанныеОтHibenide =( List<model.Settingtab>) queryДляHiberite.getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;
                        case "notifications":
                            // TODO
                            queryДляHiberite = session.createQuery(
                                    " SELECT notif FROM Notification  notif  WHERE notif.currentTable > :id "
                                            + " AND   notif.userUpdate=:user_update   "
                                            + "  OR notif.currentTable > :id  AND     notif.idUser=:id_user ");
                            queryДляHiberite.setParameter("id",new BigDecimal(VersionData));//8641 8625
                            queryДляHiberite.setParameter("user_update",IdUser);//8641 8625
                            queryДляHiberite.setParameter("id_user",IdUser);//8641 8625
                            ЛистДанныеОтHibenide =( List<model.Notification>)  queryДляHiberite.getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;
                        /// TODO
                        case "data_notification":
                            // TODO
                            queryДляHiberite = session.createQuery(
                                    " SELECT da  FROM  DataNotification da WHERE"
                                            + "  da. currentTable > :id "
                                            + "  AND da.uuidNotifications "
                                            + " IN (SELECT     no.uuid FROM    Notification no  WHERE   no.userUpdate=:user_update   OR  no .idUser=:id_user ) ");
                            queryДляHiberite.setParameter("id",new BigDecimal(VersionData));//8641 8625
                            queryДляHiberite.setParameter("user_update",IdUser);//8641 8625
                            queryДляHiberite.setParameter("id_user",IdUser);//8641 8625
                            ЛистДанныеОтHibenide =( List<model.DataNotification>)  queryДляHiberite.getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;
                        case "templates":
                            // TODO
                            queryДляHiberite = session.createQuery(
                                    " SELECT te FROM Template  te WHERE te.currentTable > :id  AND te.userUpdate=:user_update  ");
                            queryДляHiberite.setParameter("id",new BigDecimal(VersionData));//8641 8625
                            queryДляHiberite.setParameter("user_update",IdUser);//8641 8625
                            ЛистДанныеОтHibenide =( List<model.Template>) queryДляHiberite.getResultList();
                            ЛОГ. log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+
                                    " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;
                        case "fio_template":
                            // TODO
                            queryДляHiberite = session.createQuery(
                                    " SELECT fiot FROM FioTemplate  fiot  WHERE fiot.currentTable > :id   AND fiot.userUpdate=:user_update ");
                            queryДляHiberite.setParameter("id",new BigDecimal(VersionData));//8641 8625
                            queryДляHiberite.setParameter("user_update",IdUser);//8641 8625
                            ЛистДанныеОтHibenide =( List<model.FioTemplate>) queryДляHiberite.getResultList();
                            ЛОГ. log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+
                                    " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;

                        case "chat_users":
                            // TODO
                            queryДляHiberite = session.createQuery(
                                    " SELECT  ca FROM ChatUser ca  WHERE ca .currentTable > :id");
                            queryДляHiberite.setParameter("id",new BigDecimal(VersionData));//8641 8625
                            ЛистДанныеОтHibenide =( List<model.ChatUser>)  queryДляHiberite.getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;
                        case "chats":
                            // TODO
                            queryДляHiberite = session.createQuery(
                                    " SELECT    cat FROM Chat  cat WHERE cat .currentTable > :id "
                                            + " AND   cat .userUpdate=:user_update"
                                            + " OR "
                                            + " cat .currentTable > :id AND   cat .idUser=:id_user  ");
                            queryДляHiberite.setParameter("id",new BigDecimal(VersionData));//8641 8625
                            queryДляHiberite.setParameter("user_update",IdUser);//8641 8625
                            queryДляHiberite.setParameter("id_user",IdUser);//8641 8625
                            ЛистДанныеОтHibenide =( List<model.Chat>)  queryДляHiberite.getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;
                        case "data_chat":
                            // TODO
                            queryДляHiberite = session.createQuery( " SELECT    da FROM  DataChat da WHERE  da. currentTable > :id "
                                    + "							  AND da.chatUuid "
                                    + "							 IN (SELECT    ch.uuid FROM    Chat  ch"
                                    + "  WHERE  ch.userUpdate=:user_update  OR ch.idUser=:id_user )   ");
                            queryДляHiberite.setParameter("id",new BigDecimal(VersionData));//8641 8625
                            queryДляHiberite.setParameter("user_update",IdUser);//8641 8625
                            queryДляHiberite.setParameter("id_user",IdUser);//8641 8625
                            ЛистДанныеОтHibenide =( List<model.DataChat>)  queryДляHiberite.getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;
                        case "tabel":
                            // TODO
                            queryДляHiberite = session.createQuery(
                                    "SELECT  tab FROM Tabel tab  WHERE tab .currentTable > :id  AND tab.userUpdate=:user_update AND tab.statusSend!=:statusSend ");
                            queryДляHiberite.setParameter("id",new BigDecimal(VersionData));//8641 8625
                            queryДляHiberite.setParameter("user_update",IdUser);//8641 8625
                            queryДляHiberite.setParameter("statusSend","Удаленная");//8641 8625
                            ЛистДанныеОтHibenide =( List<model.Tabel>)  queryДляHiberite.getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;
                        case "data_tabels":
                            // TODO
                            queryДляHiberite = session.createQuery(
                                    "SELECT  dat FROM DataTabel dat WHERE dat .currentTable > :id  AND dat.userUpdate=:user_update" +
                                            "  AND dat.statusSend!=:statusSend  ");
                            queryДляHiberite.setParameter("id",new BigDecimal(VersionData));//8641 8625
                            queryДляHiberite.setParameter("user_update",IdUser);//8641 8625
                            queryДляHiberite.setParameter("statusSend","Удаленная");//8641 8625
                            ЛистДанныеОтHibenide =( List<model.DataTabel>)  queryДляHiberite.getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;
                        case "view_onesignal":
                            // TODO
                            queryДляHiberite = session.createQuery(
                                    " SELECT  viewone FROM ViewOnesignal viewone WHERE viewone .currentTable > :id");
                            queryДляHiberite.setParameter("id",new BigDecimal(VersionData));//8641 8625
                            ЛистДанныеОтHibenide =( List<model.ViewOnesignal>)  queryДляHiberite.getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;
                        case "nomen_vesov":
                            // TODO
                            queryДляHiberite = session.createQuery(
                                    " SELECT  nome FROM NomenVesov nome WHERE nome .currentTable > :id");
                            queryДляHiberite.setParameter("id",new BigDecimal(VersionData));//8641 8625
                            ЛистДанныеОтHibenide =( List<model.NomenVesov>)  queryДляHiberite.getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;
                        case "type_materials":
                            // TODO
                            queryДляHiberite = session.createQuery(
                                    " SELECT  typem FROM TypeMaterial typem  WHERE typem .currentTable > :id");

                            queryДляHiberite.setParameter("id",new BigDecimal(VersionData));//8641 8625
                            ЛистДанныеОтHibenide =( List<model.TypeMaterial>)  queryДляHiberite.getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;
                        case "get_materials_data":
                            // TODO
                            queryДляHiberite = session.createQuery(
                                    " SELECT  getmat FROM  GetMaterialsData  getmat  WHERE getmat .currentTable > :id  AND getmat.userUpdate=:user_update  " +
                                            " AND getmat.statusSend!=:statusSend ");
                            queryДляHiberite.setParameter("id",new BigDecimal(VersionData));//8641 8625
                            queryДляHiberite.setParameter("user_update",IdUser);//8641 8625
                            queryДляHiberite.setParameter("statusSend","Удаленная");//8641 8625
                            ЛистДанныеОтHibenide =( List<model.GetMaterialsData>)  queryДляHiberite.getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;
                        case "company":
                            // TODO
                            queryДляHiberite = session.createQuery(
                                    " SELECT  comp FROM  Company  comp  WHERE comp .currentTable > :id");

                            queryДляHiberite.setParameter("id",new BigDecimal(VersionData));//8641 8625
                            ЛистДанныеОтHibenide =( List<model.Company>)  queryДляHiberite.getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;
                        case "track":
                            // TODO
                            queryДляHiberite = session.createQuery(
                                    " SELECT  tr FROM  Track tr  WHERE tr .currentTable > :id");

                            queryДляHiberite.setParameter("id",new BigDecimal(VersionData));//8641 8625
                            ЛистДанныеОтHibenide =( List<model.Track>)  queryДляHiberite.getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;

                        case "prof":
                            // TODO
                            queryДляHiberite = session.createQuery(
                                    " SELECT  pr FROM Prof pr  WHERE pr .currentTable > :id");

                            queryДляHiberite.setParameter("id",new BigDecimal(VersionData));//8641 8625
                            ЛистДанныеОтHibenide =( List<model.Prof>)  queryДляHiberite.getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;

                        case "order_tc":
                            // TODO
                            criteriaquery = session.createCriteria(OrderTc.class);
                            criteriaquery.add(org.hibernate.criterion.Restrictions.gt("currentTable", new BigDecimal(VersionData)));
                            criteriaquery.add(org.hibernate.criterion.Restrictions.eq("userUpdate", IdUser));
                            criteriaquery.add(org.hibernate.criterion.Restrictions.ne("status", 5));
                            ЛистДанныеОтHibenide = ( List<model.OrderTc>)   criteriaquery.list();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;

                        case "vid_tc":
                            // TODO
                            criteriaquery= session.createCriteria(VidTc.class);
                            criteriaquery.add(org.hibernate.criterion.Restrictions.gt("currentTable", new BigDecimal(VersionData)));
                            ЛистДанныеОтHibenide =  ( List<model.VidTc>)     criteriaquery.list();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;

                        case "materials_databinary":
                            // TODO  byte
                            criteriaquery = session.createCriteria(MateriBinary.class);
                            criteriaquery.add(org.hibernate.criterion.Restrictions.gt("currentTable", new BigDecimal(VersionData)));
                            criteriaquery.add(org.hibernate.criterion.Restrictions.eq("userUpdate", IdUser));
                            ЛистДанныеОтHibenide = ( List<MateriBinary>)   criteriaquery.list();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                    /*        // TODO: 14.07.2023 TEST code
                            DecodeByteArray_Image decodeByteArray_image=new DecodeByteArray_Image();
                            decodeByteArray_image.методDecodeByteImage(ЛОГ,ЛистДанныеОтHibenide);*/

                            break;


                    }//TODO КОНЕЦ РАСПРЕДЕНИЕ ТАБЛИЦ 	switch (NameTable.trim()) {
                    ЛОГ.log(" КоличествоСтрокКоторыеМыОтправимНаКлиент  " + КоличествоСтрокКоторыеМыОтправимНаКлиент+
                            " NameTable " +NameTable);
                default:
                    ЛОГ.log("\n"+"  default:  Starting.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                            " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                            " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+
                            "Метод_РеальнаяСтатусSqlServer  ЛистДанныеОтHibenide " +ЛистДанныеОтHibenide.size());
                    break;
            }


            ЛОГ.log(" ОТВЕТ КЛИЕНТУ OTBEN LKIENTYY JobForServer " + JobForServer
                    + " БуферCallsBackДляAndroid " + БуферCallsBackДляAndroid+  "ЛистДанныеОтHibenide "+ ЛистДанныеОтHibenide);



            //TODO ГЕНЕРАЦИЯ JSON ПО НОВОМУ
            if (ЛистДанныеОтHibenide!=null && ЛистДанныеОтHibenide.size()>0) {
                БуферCallsBackДляAndroid = МетодГенерацияJSONJacksonByte(ЛистДанныеОтHibenide);
            }

            // TODO КОГДА ЛОГИН И ПАРОЛЬ НЕТ ДОСТУПА
            МетодЗакрываемСессиюHibernate();
            //// TODO ЗАКРЫЫВАЕМ КУРСОРЫ ПОСЛЕ ГЕНЕРАЦИИ JSON ДЛЯ КЛИЕНТА
            // TODO
            ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                    " БуферCallsBackДляAndroid " + БуферCallsBackДляAndroid.toString() +
                    " session  " + session + " sessionSousJboss " + sessionSousJboss
                    + " ЛОГИН "+ЛОГ.getAttribute("ЛогинПолученныйОтКлиента")+
                    " ID ТЕЛЕФОНА "+  ЛОГ.getAttribute("АдуДевайсяКлиента"));
        } catch (Exception e) {
            ЛОГ.log("\n" + " ERROR class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
            if (session!=null) {
                session.getTransaction().rollback();
                session.close();
            }
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletDSU1.txt");
        }
        return БуферCallsBackДляAndroid; // TODO return new
        // AsyncResult<StringBuffer>(БуферCallsBackДляAndroid);
    }




    private void МетодЗакрываемСессиюHibernate() {
        try{
            if (session!=null) {
                if (session.getTransaction().getStatus()== TransactionStatus.ACTIVE) {
                    session.getTransaction().commit();
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
            if (session!=null) {
                session.getTransaction().rollback();
                session.close();
            }
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletDSU1.txt");
    }
    }





    // TODO ГЕНЕРАЦИЯ JSON ПО  НОВОМУ Jackson
    byte[] МетодГенерацияJSONJacksonByte(@javax.validation.constraints.NotNull List<?> listОтHiberideДляГенерации)
            throws SQLException, SecurityException {
        byte[] БуферСозданогоJSONJackson = new byte[0];
        try {
            ЛОГ.log(" listОтHiberideДляГенерации" + listОтHiberideДляГенерации );
            ObjectWriter writer = getGeneratorJacksonCbor.writerWithDefaultPrettyPrinter();
            //String Сгенерированыйjson = 	  writer.writeValueAsString(listОтHiberideДляГенерации);

            БуферСозданогоJSONJackson=   writer.writeValueAsBytes(listОтHiberideДляГенерации);

            ЛОГ.log("\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+
                    "БуферСозданогоJSONJackson " + БуферСозданогоJSONJackson.toString());
        } catch (Exception e) {
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletDSU1.txt");
        }
        return БуферСозданогоJSONJackson;
    }











    // todo ЕЩЕ ОДИН КОД ПЕРЕНЕСЛИВ МЕТОД GET()


    protected     List<model.UsersEntitySuccess>  Метод_МетодаGETОтпалавляемПубличныйIDПользователюАндройду() throws IOException {
        List<model.UsersEntitySuccess> ЛистДанныеОтHibenide  = new ArrayList<>();
        try {
                org.hibernate.Query queryДляHiberite   = session.createQuery("SELECT   usersentity. id FROM model.UsersEntitySuccess" +
                        "  as  usersentity WHERE usersentity.id =:id   ");

            Integer IdUser = Optional.ofNullable( Optional.ofNullable(ЛОГ.getAttribute("IdUser").toString() )
                    .map(String::new ).orElse("0")).stream().mapToInt(Integer::new).findFirst().getAsInt();
            queryДляHiberite.setParameter("id",IdUser);//8641 8625

            ЛистДанныеОтHibenide =( List<model.UsersEntitySuccess>) queryДляHiberite.setMaxResults(1).getResultList();

                ЛОГ.log("\n"+" Starting.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                        " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                        " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+
                        " ЛистДанныеОтHibenide " +ЛистДанныеОтHibenide.toString());

        } catch (Exception e) {
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletAuntification.txt");
        }
        return ЛистДанныеОтHibenide;
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

}
