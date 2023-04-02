package businesslogic;

import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import javax.persistence.StoredProcedureQuery;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Produces;

import dsu1glassfishatomic.workinterfaces.ProducedCard;
import org.hibernate.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.sun.istack.NotNull;

@RequestScoped
@Produces
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
    private Long ПараметрВерсияДанных = 0l;//TOD ВЕРСИЯ ДАННЫХ
    private Integer ПараметрТекущийПользователь = 0;  //TODO ТЕКУЩИЙ ПОЛЬЗОВАТЕЛЬ
    @SuppressWarnings("unused")
    private HttpServletRequest request;
    private HttpServletResponse response;
    private StoredProcedureQuery queryprocedure = null;



    private    Session session;
    private    Transaction sessionTransaction  ;

    @Inject @ProducedCard
    SessionFactory sessionSousJboss;

    @Inject
    SubClassWriterErros subClassWriterErros;


    @Inject
    ObjectMapper getGeneratorJackson;

    public SubClassSessionBeanМетодаGET() {

        System.out.println("Конструктор  SubClassSessionBeanМетодаGET");

    }

    @SuppressWarnings({ "unused", "deprecation", "rawtypes", "unchecked" })
    protected StringBuffer ГлавныйМетод_МетодаGET(@NotNull HttpServletRequest request,
                                                  @NotNull ServletContext ЛОГ) throws SecurityException, SQLException {
        // TODO Auto-generated method stub
        System.out.println("Конструктор  ЗАПУСК МЕТОДА ИЗ GET ()  ГлавныйМетод_МетодаGET()");
        StringBuffer БуферCallsBackДляAndroid = new StringBuffer();;
        try   {
            this.ЛОГ = ЛОГ;
            // TODO
            this.request = request;
            /// TODO
            this.response = response;
            // TODO Коннектимся к Базе SQl Server
            ЛОГ.log("ЛОГ  GET() " + ЛОГ + " request " + request + " response " + response);
            // TODO получаем session
            ЛОГ.log("ЗАПУСКАЕТСЯ....... ГЛАВНЫЙ МЕТОД GET() СЕРВЛЕТА " + new Date() + "\n" + ЛОГ.getServerInfo()
                    + "  request " + request + " response " + response + " ЛОГ" + ЛОГ);
            // TODO ГАЛВНЫЙ МЕТОД GET НАЧИНАЕТ РАБОТАТЬ
            String JobsFroServerЗаданиеДляСервера = null;
            String ПараметрФильтрНаДанныеСервлета = null;
            String JobsServerСазаданиеДляСервера = null;
            String ПараметрКонкретнаяТаблицаВПотокеВнутриПотока = null;

            String результатшифрование;
            String РезультатОбновлениеДляОтправкиВАндройд;//// для понимания
            String пароль;
            int КоличествоСтрокВБАзеSQLSERVER = 0;

            int ДляПосикаКоличествоСтолбцовВБАзеSQLSERVER;
            String queryJSON = new String(); /// ПОЛУЧЕННЫЙ JSON САМО ТЕЛО ОТВЕТА
            String ПараметрФильтрНаДанныеСервлетаКонкретнаяТАблицаКоторойНУжноВерсиюУзнать;//// ;//////указываем
            int РазрешонныеПрава = 2;// TODO права для табельного учёта 2 два только
            // Количество колонок в результирующем запросе
            int СколькСтрокРезультатЕслиТакойПользовательМетод_GET = 0;
            ЛОГ.log("СТАРТ/START МЕТОД/METOD  protected void doGet  logger  ::: " + new Date() + "\n");
            /// TODO logginf info
            String ТолькоДляАунтификацииИмяПолученныйИзSQlServerПосик = new String();
            //////
            String ТаблицаGET = new String();/// ОПРЕДЕЛЯЕМ
            /////// НАЧАЛО КОД ДОСТУПА К СЕРВЛЕТУ
            String HeaderСодержимое = new String();


            ИмяПолученныйИзSQlServerПосик = new String();/// вычисялем
            String ПарольПолученныйИзSQlServerПосик = null;

            ResultSet РезультатСканированиеИмениИПароль = null;// ОЧИЩАЕМ
            String queryСканируемИмяИпароль;
            String HeaderСодержимоеРасшифрован = null;

            ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                    " БуферCallsBackДляAndroid " + БуферCallsBackДляAndroid.toString() +
                    " session  " + session + " sessionSousJboss " + sessionSousJboss
                    + " ЛОГИН "+ЛОГ.getAttribute("ЛогинПолученныйОтКлиента")+
                    " ID ТЕЛЕФОНА "+  ЛОГ.getAttribute("АдуДевайсяКлиента"));


            Integer IDПолученныйИзSQlServerПосик = Optional
                    .ofNullable(ЛОГ.getAttribute("IDПолученныйИзSQlServerПосик").toString()).map(Integer::new)
                    .orElse(0);
            System.out.println("  IDПолученныйИзSQlServerПосик " + IDПолученныйИзSQlServerПосик);
            org.hibernate.Query queryДляHiberite = null;
            List<?> ЛистДанныеОтHibenide  = new ArrayList<>();

            if (IDПолученныйИзSQlServerПосик>0) {
                // TODO: 10.03.2023 получение сессиии HIREBIANTE
                session=   sessionSousJboss.getCurrentSession();
                // TODO: 10.03.2023 получение сессиии Transaction
                sessionTransaction = session.getTransaction();
                ЛОГ.log("\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                        " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                        " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+ " session " +session);
                /// TODO КОНЕЦ  НОВЫЕ ПАРАМЕТРЫ HIREBIANTE
            }


            /// TODO ПАРАМЕНТ #1
            ТаблицаGET = Optional.ofNullable(request.getParameter("ИмяТаблицыОтАндройда"))
                    .map(String::trim).orElse("");
            System.out.println("  ТаблицаGET " + ТаблицаGET);
            /// TODO ПАРАМЕНТ #2
            ПараметрФильтрНаДанныеСервлета = Optional.ofNullable(request.getParameter("ФильтрДляДанныхСервлета"))
                    .map(String::trim).orElse("");
            System.out.println("  ПараметрФильтрНаДанныеСервлета  " + ПараметрФильтрНаДанныеСервлета);
            /// TODO ПАРАМЕНТ #3
            ПараметрФильтрНаДанныеСервлетаКонкретнаяТАблицаКоторойНУжноВерсиюУзнать = Optional
                    .ofNullable(request.getParameter("КонкретнаяТАблицаКоторойНУжноВерсиюУзнать")).map(String::trim)
                    .orElse("");
            System.out.println("  ПараметрФильтрНаДанныеСервлетаКонкретнаяТАблицаКоторойНУжноВерсиюУзнать  "
                    + ПараметрФильтрНаДанныеСервлетаКонкретнаяТАблицаКоторойНУжноВерсиюУзнать);
            /// TODO ПАРАМЕНТ #4
            JobsServerСазаданиеДляСервера = Optional.ofNullable(request.getParameter("ЗаданиеДляСервлетаВнутриПотока"))
                    .map(String::trim).orElse("");
            System.out.println("  ПараметрФильтрПолучаемыхТаблицДляАндройда  " + JobsServerСазаданиеДляСервера);
            /// TODO ПАРАМЕНТ #5
            ПараметрКонкретнаяТаблицаВПотокеВнутриПотока = Optional
                    .ofNullable(request.getParameter("КонкретнаяТаблицаВПотоке")).map(String::trim).orElse("");
            System.out.println(
                    "  ПараметрКонкретнаяТаблицаВПотокеВнутриПотока  " + ПараметрКонкретнаяТаблицаВПотокеВнутриПотока);

            // TODO: 02.04.2023  Задание на исполение Сервром  
            JobsFroServerЗаданиеДляСервера = Optional.ofNullable(request.getParameter("ЗаданиеДляСервлетаВнутриПотока"))
                    .orElse("");
// TODO: 17.03.2023 ЗАПУСКАЕТ ТРАНЗАКЦИЮ BEGIN
            sessionTransaction.begin();





            switch (JobsFroServerЗаданиеДляСервера) {
                // TODO ЗАДАНИЕ ДЛЯ СЕРВЕР JOBSERVERTASK #2
                case "Хотим Получить  JSON":
                    ЛОГ.log("Хотим Получить  JSON" + new Date() + " JobsServerСазаданиеДляСервера "
                            + JobsServerСазаданиеДляСервера+"  ПараметрВерсияДанных" + ПараметрВерсияДанных
                            + " ТаблицаGET " + ТаблицаGET);
                    ////////////// ГЕНЕРАЦИЯ JSON ДЛЯ ВСЕХ  ТАБЛИЦ
                    // TODO ГЛАВНЫЙ РАСПРЕДЕЛИТЕЛЬ КАКАЯ ТЕКУЩАЯ ТАБЛИЦА ОБРАБАТЫВАЕМСЯ
                    switch (ТаблицаGET.trim()) {
                        case "organization":
                            // TODO
                            queryДляHiberite  = session.createQuery(
                                    " SELECT o FROM Organization o WHERE o.currentTable > :id ");
                      
                            queryДляHiberite.setParameter("id",new BigDecimal(ПараметрВерсияДанных ));//
                            ЛистДанныеОтHibenide =( List<model.Organization>)  queryДляHiberite.getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite+ " ТаблицаGET " +ТаблицаGET);//gson Gson
                            break;
                        case "depatment":
                            // TODO
                            queryДляHiberite = session.createQuery(
                                    "SELECT d FROM Depatment d   WHERE d.currentTable > :id ");
                      
                            queryДляHiberite.setParameter("id",new BigDecimal(ПараметрВерсияДанных ));
                            ЛистДанныеОтHibenide =( List<model.Depatment>)  queryДляHiberite.getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;
                        case "fio":
                            // TODO
                            queryДляHiberite = session.createQuery(
                                    "SELECT f FROM Fio f   WHERE f.currentTable > :id ");
                      
                            queryДляHiberite.setParameter("id",new BigDecimal(ПараметрВерсияДанных ));//
                            ЛистДанныеОтHibenide =( List<model.Fio>)  queryДляHiberite.getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;
                        case "region":
                            // TODO
                            queryДляHiberite = session.createQuery(
                                    "SELECT r  FROM Region r   WHERE r.currentTable > :id");
                      
                            queryДляHiberite.setParameter("id",new BigDecimal(ПараметрВерсияДанных ));//
                            ЛистДанныеОтHibenide =( List<model.Region>)  queryДляHiberite.getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;
                        case "cfo":
                            // TODO
                            queryДляHiberite = session.createQuery(
                                    "SELECT c FROM Cfo  c  WHERE c.currentTable > :id ");
                      
                            queryДляHiberite.setParameter("id",new BigDecimal(ПараметрВерсияДанных ));//
                            ЛистДанныеОтHibenide =( List<model.Cfo>)  queryДляHiberite.getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;
                        case "settings_tabels":
                            // TODO
                            queryДляHiberite = session.createQuery(
                                    "SELECT st FROM Settingtab  st  WHERE st.currentTable > :id  AND  st.userUpdate=:user_update ");
                      
                            queryДляHiberite.setParameter("id",new BigDecimal(ПараметрВерсияДанных ));//
                            queryДляHiberite.setParameter("user_update",IDПолученныйИзSQlServerПосик);//8641 8625
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
                           
                            queryДляHiberite.setParameter("id",new BigDecimal(ПараметрВерсияДанных));//8641 8625
                            queryДляHiberite.setParameter("user_update",IDПолученныйИзSQlServerПосик);//8641 8625
                            queryДляHiberite.setParameter("id_user",IDПолученныйИзSQlServerПосик);//8641 8625
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
                      
                            queryДляHiberite.setParameter("id",new BigDecimal(ПараметрВерсияДанных));//8641 8625
                            queryДляHiberite.setParameter("user_update",IDПолученныйИзSQlServerПосик);//8641 8625
                            queryДляHiberite.setParameter("id_user",IDПолученныйИзSQlServerПосик);//8641 8625
                            ЛистДанныеОтHibenide =( List<model.DataNotification>)  queryДляHiberite.getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;
                        case "templates":
                            // TODO
                            queryДляHiberite = session.createQuery(
                                    " SELECT te FROM Template  te WHERE te.currentTable > :id  AND te.userUpdate=:user_update  ");
                      
                            queryДляHiberite.setParameter("id",new BigDecimal(ПараметрВерсияДанных));//8641 8625
                            queryДляHiberite.setParameter("user_update",IDПолученныйИзSQlServerПосик);//8641 8625
                            ЛистДанныеОтHibenide =( List<model.Template>) queryДляHiberite.getResultList();
                            ЛОГ. log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+
                                    " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;
                        case "fio_template":
                            // TODO
                            queryДляHiberite = session.createQuery(
                                    " SELECT fiot FROM FioTemplate  fiot  WHERE fiot.currentTable > :id   AND fiot.userUpdate=:user_update ");
                      
                            queryДляHiberite.setParameter("id",new BigDecimal(ПараметрВерсияДанных));//8641 8625
                            queryДляHiberite.setParameter("user_update",IDПолученныйИзSQlServerПосик);//8641 8625
                            ЛистДанныеОтHibenide =( List<model.FioTemplate>) queryДляHiberite.getResultList();
                            ЛОГ. log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+
                                    " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;

                        case "chat_users":
                            // TODO
                            queryДляHiberite = session.createQuery(
                                    " SELECT  ca FROM ChatUser ca  WHERE ca .currentTable > :id");
                            queryДляHiberite.setLockOptions(new LockOptions(LockMode.PESSIMISTIC_READ));
                            queryДляHiberite.setParameter("id",new BigDecimal(ПараметрВерсияДанных));//8641 8625
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
                      
                            queryДляHiberite.setParameter("id",new BigDecimal(ПараметрВерсияДанных));//8641 8625
                            queryДляHiberite.setParameter("user_update",IDПолученныйИзSQlServerПосик);//8641 8625
                            queryДляHiberite.setParameter("id_user",IDПолученныйИзSQlServerПосик);//8641 8625
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
                      
                            queryДляHiberite.setParameter("id",new BigDecimal(ПараметрВерсияДанных));//8641 8625
                            queryДляHiberite.setParameter("user_update",IDПолученныйИзSQlServerПосик);//8641 8625
                            queryДляHiberite.setParameter("id_user",IDПолученныйИзSQlServerПосик);//8641 8625
                            ЛистДанныеОтHibenide =( List<model.DataChat>)  queryДляHiberite.getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;
                        case "tabel":
                            // TODO
                            queryДляHiberite = session.createQuery(
                                    "SELECT  tab FROM Tabel tab  WHERE tab .currentTable > :id  AND tab.userUpdate=:user_update ");
                      
                            queryДляHiberite.setParameter("id",new BigDecimal(ПараметрВерсияДанных));//8641 8625
                            queryДляHiberite.setParameter("user_update",IDПолученныйИзSQlServerПосик);//8641 8625
                            ЛистДанныеОтHibenide =( List<model.Tabel>)  queryДляHiberite.getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;
                        case "data_tabels":
                            // TODO
                            queryДляHiberite = session.createQuery(
                                    "SELECT  dat FROM DataTabel dat WHERE dat .currentTable > :id  AND dat.userUpdate=:user_update  ");
                      
                            queryДляHiberite.setParameter("id",new BigDecimal(ПараметрВерсияДанных));//8641 8625
                            queryДляHiberite.setParameter("user_update",IDПолученныйИзSQlServerПосик);//8641 8625
                            ЛистДанныеОтHibenide =( List<model.DataTabel>)  queryДляHiberite.getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;
                        case "view_onesignal":
                            // TODO
                            queryДляHiberite = session.createQuery(
                                    " SELECT  viewone FROM ViewOnesignal viewone WHERE viewone .currentTable > :id");
                      
                            queryДляHiberite.setParameter("id",new BigDecimal(ПараметрВерсияДанных));//8641 8625
                            ЛистДанныеОтHibenide =( List<model.ViewOnesignal>)  queryДляHiberite.getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;
                        case "nomen_vesov":
                            // TODO
                            queryДляHiberite = session.createQuery(
                                    " SELECT  nome FROM NomenVesov nome WHERE nome .currentTable > :id");
                      
                            queryДляHiberite.setParameter("id",new BigDecimal(ПараметрВерсияДанных));//8641 8625
                            ЛистДанныеОтHibenide =( List<model.NomenVesov>)  queryДляHiberite.getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;
                        case "type_materials":
                            // TODO
                            queryДляHiberite = session.createQuery(
                                    " SELECT  typem FROM TypeMaterial typem  WHERE typem .currentTable > :id");
                      
                            queryДляHiberite.setParameter("id",new BigDecimal(ПараметрВерсияДанных));//8641 8625
                            ЛистДанныеОтHibenide =( List<model.TypeMaterial>)  queryДляHiberite.getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;
                        case "get_materials_data":
                            // TODO
                            queryДляHiberite = session.createQuery(
                                    " SELECT  getmat FROM GetMaterialsData  getmat  WHERE getmat .currentTable > :id  AND getmat.userUpdate=:user_update ");
                            queryДляHiberite.setParameter("id",new BigDecimal(ПараметрВерсияДанных));//8641 8625
                            queryДляHiberite.setParameter("user_update",IDПолученныйИзSQlServerПосик);//8641 8625
                            ЛистДанныеОтHibenide =( List<model.GetMaterialsData>)  queryДляHiberite.getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;
                        case "company":
                            // TODO
                            queryДляHiberite = session.createQuery(
                                    " SELECT  comp FROM Company  comp  WHERE comp .currentTable > :id");
                      
                            queryДляHiberite.setParameter("id",new BigDecimal(ПараметрВерсияДанных));//8641 8625
                            ЛистДанныеОтHibenide =( List<model.Company>)  queryДляHiberite.getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;
                        case "track":
                            // TODO
                            queryДляHiberite = session.createQuery(
                                    " SELECT  tr FROM Track tr  WHERE tr .currentTable > :id");
                      
                            queryДляHiberite.setParameter("id",new BigDecimal(ПараметрВерсияДанных));//8641 8625
                            ЛистДанныеОтHibenide =( List<model.Track>)  queryДляHiberite.getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;

                        case "prof":
                            // TODO
                            queryДляHiberite = session.createQuery(
                                    " SELECT  pr FROM Prof pr  WHERE pr .currentTable > :id");
                      
                            queryДляHiberite.setParameter("id",new BigDecimal(ПараметрВерсияДанных));//8641 8625
                            ЛистДанныеОтHibenide =( List<model.Prof>)  queryДляHiberite.getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;

                    }//TODO КОНЕЦ РАСПРЕДЕНИЕ ТАБЛИЦ 	switch (ТаблицаGET.trim()) {

                    ЛОГ.log(" КоличествоСтрокКоторыеМыОтправимНаКлиент  " + КоличествоСтрокКоторыеМыОтправимНаКлиент+
                            " ТаблицаGET " +ТаблицаGET);
                    //TODO ФИНАЛЬЯ СТАДИЯ ГЕНЕРИРУЕМ САМ JSON
                    ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                            "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                    // TODO конец МЕНЕДЖЕН ПОТОКА ДАННЫХ ПРИ
                    // ОТПРАВЛЕНИЕ ДАННЫ
                    // TODO ВЫХОД ИЗ КОНКРЕТНОГО УСЛОВИЯ
                    // ВЫПОЛЕННИЯ
                    break;
                // TODO ЗАДАНИЯ ДЛЯ СЕРВЕРА НЕТУ
                default:
                    ЛОГ.log("\n"+"  default:  Starting.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                            " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                            " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+
                            "Метод_РеальнаяСтатусSqlServer  ЛистДанныеОтHibenide " +ЛистДанныеОтHibenide.size());
                    break;
            }
            //TODO ГЕНЕРАЦИЯ JSON ПО НОВОМУ
            БуферCallsBackДляAndroid =МетодГенерацияJSONJackson(  ЛистДанныеОтHibenide);

            ЛОГ.log(" ОТВЕТ КЛИЕНТУ OTBEN LKIENTYY JobsServerСазаданиеДляСервера " + JobsServerСазаданиеДляСервера
                    + " БуферCallsBackДляAndroid " + БуферCallsBackДляAndroid.toString());
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
            /////// ошибки метода doGET
        } catch (Exception e) {
            ЛОГ.log("\n" + " ERROR class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
            sessionTransaction.rollback();
            session.close();
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
                if (    sessionTransaction.isActive()) {
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



    // TODO ГЕНЕРАЦИЯ JSON ПО  НОВОМУ Jackson
    StringBuffer МетодГенерацияJSONJackson(@javax.validation.constraints.NotNull List<?> listОтHiberideДляГенерации)
            throws SQLException, SecurityException {
        StringBuffer БуферСозданогоJSONJackson = new StringBuffer();
        try {
            ЛОГ.log(" listОтHiberideДляГенерации" + listОтHiberideДляГенерации );
            ObjectWriter writer = getGeneratorJackson.writerWithDefaultPrettyPrinter();
            String Сгенерированыйjson = 	  writer.writeValueAsString(listОтHiberideДляГенерации);
            ЛОГ.  log(" Сгенерированыйjson "+Сгенерированыйjson.length());//gson
            БуферСозданогоJSONJackson.append(Сгенерированыйjson);
         ЛОГ.log("\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+
            "БуферСозданогоJSONJackson " + БуферСозданогоJSONJackson.toString());
            /*
             * // Create custom configuration JsonbConfig nillableConfig = new
             * JsonbConfig().withNullValues(true);
             * nillableConfig.withDateFormat("yyyy-MM-dd HH:mm:ss.SSS", new Locale("ru"));
             * Jsonb jsonb = JsonbBuilder.create(nillableConfig); String resultjsonb =
             * jsonb.toJson(listОтHiberideДляГенерации); ЛОГ.
             * log(" resultjsonb "+resultjsonb.length());//gson
             *  //TODO Jacson парсинг JSON
            JsonFactory factory = new JsonFactory();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", new Locale("ru"));
            final ObjectMapper mapperJackson = new ObjectMapper(factory);
            mapperJackson.setDateFormat(df);
            mapperJackson.setLocale(new Locale("ru"));
            mapperJackson.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            mapperJackson.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
             */
        } catch (Exception e) {
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletDSU1.txt");
        }
        return БуферСозданогоJSONJackson;
    }




    StringBuffer МетодВторойПослеГенрацииJSONСтрочкеДляФорматируемЕгоВБуфер(
            @NotNull JsonObjectBuilder СгенерированныйJSONДЛяКонфертациивБуфер) {
        StringWriter stringWriterМассив = new StringWriter();
        StringBuffer БуферJSONДляКлиента = new StringBuffer();
        try {
            ЛОГ.log("СгенерированныйJSONДЛяКонфертациивБуфер " + СгенерированныйJSONДЛяКонфертациивБуфер.toString());
            JsonWriter jsonWriter = Json.createWriter(stringWriterМассив);/// ОТКРЫВАЕМ
            jsonWriter.writeObject(СгенерированныйJSONДЛяКонфертациивБуфер.build());// САМО
            БуферJSONДляКлиента.append(stringWriterМассив.getBuffer().toString()).append("\n");//// ПЕРЕВОДИТ
            ЛОГ.log(" БуферJSONДляКлиента  для Отправки Клиенту " + БуферJSONДляКлиента);

        } catch (Exception e) {
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletDSU1.txt");;
        }
        return БуферJSONДляКлиента;
    }

    // todo ЕЩЕ ОДИН КОД ПЕРЕНЕСЛИВ МЕТОД GET()


    JsonObjectBuilder МетодГенерацииJSONПОтокаДЛяОтправкиКлиенту(@NotNull int КоличествоСтолбцовВБАзеSQLSERVERРАзное,
                                                                 @NotNull ResultSet КурсорГенерацияJSONДляКлиента) throws SQLException, SecurityException {
        JsonObjectBuilder СгенерированныйJSONДляКлиента = Json.createObjectBuilder(); // TODO
        try {

            System.out.println("  ВЫПОЕЛНИЯ СтрокаКлиентJSON " + КурсорГенерацияJSONДляКлиента);
            while (КурсорГенерацияJSONДляКлиента.next()) {
                JsonObjectBuilder JsonгенерацияТекущейСтроки = Json.createObjectBuilder();// TODO стока одна
                String СодержимоеКолонкиВSqlServer = null;
                System.out.println(" количество стобцов  " + КоличествоСтолбцовВБАзеSQLSERVERРАзное);
                int ИндексПоКолонкам;
                String ИдиДляJSONПоле = null;
                for (ИндексПоКолонкам = 1; ИндексПоКолонкам <= КоличествоСтолбцовВБАзеSQLSERVERРАзное; ИндексПоКолонкам++) {
                    String НазваниеСтлбикаДляГенерацииJSON = КурсорГенерацияJSONДляКлиента.getMetaData()
                            .getColumnName(ИндексПоКолонкам);
                    СодержимоеКолонкиВSqlServer = КурсорГенерацияJSONДляКлиента.getString(ИндексПоКолонкам);
                    ИдиДляJSONПоле = String.valueOf(КурсорГенерацияJSONДляКлиента.getString(1)); /// данное

                    ЛОГ.log(" НазваниеСтлбикаДляГенерацииJSON " + НазваниеСтлбикаДляГенерацииJSON
                            + " СодержимоеКолонкиВSqlServer " + СодержимоеКолонкиВSqlServer + " ИдиДляJSONПоле "
                            + ИдиДляJSONПоле + " СтрочкагерериацииJSON " + КурсорГенерацияJSONДляКлиента);
                    if (СодержимоеКолонкиВSqlServer != null && НазваниеСтлбикаДляГенерацииJSON != null) {
                        ЛОГ.log(" НазваниеСтлбикаДляГенерацииJSON " + НазваниеСтлбикаДляГенерацииJSON
                                + " СодержимоеКолонкиВSqlServer " + СодержимоеКолонкиВSqlServer + " ИдиДляJSONПоле "
                                + ИдиДляJSONПоле);
                        JsonгенерацияТекущейСтроки.add(НазваниеСтлбикаДляГенерацииJSON, СодержимоеКолонкиВSqlServer);//// заполение
                        ЛОГ.log(" JsonгенерацияТекущейСтроки " + JsonгенерацияТекущейСтроки.toString()
                                + "   СодержимоеКолонкиВSqlServer " + СодержимоеКолонкиВSqlServer);
                    }
                    ЛОГ.log(" JsonгенерацияТекущейСтроки " + JsonгенерацияТекущейСтроки.toString()
                            + "   СодержимоеКолонкиВSqlServer " + СодержимоеКолонкиВSqlServer);
                }
                СгенерированныйJSONДляКлиента.add(ИдиДляJSONПоле, JsonгенерацияТекущейСтроки.build());
            }
            ;
            // TODO конец генерации полей JSON для отпарви
            if (!КурсорГенерацияJSONДляКлиента.isClosed()) {
                КурсорГенерацияJSONДляКлиента.close();
            }
            // TODO
        } catch (Exception e) {
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletDSU1.txt");
        }
        ЛОГ.log(" РЕЗУЛЬТАТ ОБРАБОКИ ГЕНЕРАЦИИ JSON-ПОЛЕЙ   ОТПРАВКИ НА АНДРОЙД  JSONВерхнийКлюч ");
        return СгенерированныйJSONДляКлиента;
    }
    // TODO Еще ОДИН КОД ПЕРЕНЕСЛИ В МЕТОД GET() МОЖЕТ ПОСЛЕДНИЙ А МОЖЕТ И НЕТ

    //// ТУТ---ГЕНРИРУЕМ JSON СПИСОК ТАБЛИЦ КОТОРЫХ НАДО ОТПАРВМТЬ КЛИЕНТУ


}
