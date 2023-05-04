package businesslogic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.sun.istack.NotNull;
import dsu1glassfishatomic.workinterfaces.ProducedCard;
import model.OrderTc;
import org.hibernate.*;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Produces;
import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


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
                // TODO: 10.03.2023 получение сессиии Transaction
                sessionTransaction = session.getTransaction();
                // TODO: 17.03.2023 ЗАПУСКАЕТ ТРАНЗАКЦИЮ BEGIN
                if (sessionTransaction.getStatus()== TransactionStatus.NOT_ACTIVE) {
                    sessionTransaction.begin();
                }
                ЛОГ.log("\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                        " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                        " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+ " session " +session  + " sessionTransaction " +sessionTransaction);
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
                                    "SELECT c FROM Cfo  c  WHERE c.currentTable > :id ");
                            queryДляHiberite.setParameter("id",new BigDecimal(VersionData ));//
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
                            queryДляHiberite.setLockOptions(new LockOptions(LockMode.PESSIMISTIC_READ));
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
                                    " SELECT  getmat FROM  GetMaterialsData  getmat  WHERE getmat .currentTable > :id  AND getmat.userUpdate=:user_update ");
                            queryДляHiberite.setParameter("id",new BigDecimal(VersionData));//8641 8625
                            queryДляHiberite.setParameter("user_update",IdUser);//8641 8625
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


                        /*            org.hibernate.Criteria query = session.createCriteria(OrderTransport.class);
                           // query.add(org.hibernate.criterion.Restrictions.gt("ort.currentTable", new BigDecimal(0)));
                           List  ЛистДанныеОтHibenide2 =   query.list();*/

                            CriteriaBuilder cb = session.getCriteriaBuilder();
                            CriteriaQuery<OrderTc> criteria = cb.createQuery( OrderTc.class);
                            Root<OrderTc> i = criteria.from( OrderTc.class);
                            criteria.select(i).where(cb.gt(i.get("currentTable"), new BigDecimal(0)));
                            TypedQuery<OrderTc> query = session.createQuery(criteria);
                            ЛистДанныеОтHibenide = query.getResultList();

                       /*       cb = session.getCriteriaBuilder();
                            CriteriaQuery<OrderTransport> criteria2 = cb.createQuery(OrderTransport.class);
                            Root<OrderTransport> i2 = criteria2.from(OrderTransport.class);
                            criteria.select(i).where(cb.gt(i2.get("currentTable"), new BigDecimal(0)));
                            TypedQuery<OrderTransport> query2 = session.createQuery(criteria2);
                            ЛистДанныеОтHibenide = query2.getResultList();


                            ЛистДанныеОтHibenide =( List<model.OrderTransport>)  session.createQuery(" SELECT  ort FROM  model.OrderTransport   ort    ", OrderTransport.class).getResultList();

                            ЛистДанныеОтHibenide =( List<model.Prof>)  session.createQuery(" SELECT  ort FROM  model.Prof   ort    ", Prof.class).getResultList();

                           // queryДляHiberite.setParameter("version",new BigDecimal(0));//8641 8625VersionData
                            ЛистДанныеОтHibenide =( List<model.OrderTransport>)  queryДляHiberite.getResultList();*/
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;

                  /*      case "vid_tc":
                            // TODO
                            queryДляHiberite = session.createQuery(
                                    " SELECT  vt FROM model.VidTansport  vt  WHERE vt.currentTable > :version");

                            queryДляHiberite.setParameter("version",new BigDecimal(0));//8641 8625 VersionData
                            ЛистДанныеОтHibenide =( List<model.VidTansport>)  queryДляHiberite.getResultList();
                            ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                                    "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                            break;*/

                    }//TODO КОНЕЦ РАСПРЕДЕНИЕ ТАБЛИЦ 	switch (NameTable.trim()) {

                    ЛОГ.log(" КоличествоСтрокКоторыеМыОтправимНаКлиент  " + КоличествоСтрокКоторыеМыОтправимНаКлиент+
                            " NameTable " +NameTable);
                    //TODO ФИНАЛЬЯ СТАДИЯ ГЕНЕРИРУЕМ САМ JSON
                    ЛОГ.  log(" ЛистДанныеОтHibenide "+ЛистДанныеОтHibenide+ " ЛистДанныеОтHibenide.size() " +ЛистДанныеОтHibenide.size()+
                            "  queryДляHiberite  " +queryДляHiberite);//gson Gson
                    // TODO конец МЕНЕДЖЕН ПОТОКА ДАННЫХ ПРИ
                    // ОТПРАВЛЕНИЕ ДАННЫ
                    // TODO ВЫХОД ИЗ КОНКРЕТНОГО УСЛОВИЯ
                    // ВЫПОЛЕННИЯ
                    break;
                // TODO ЗАДАНИЯ ДЛЯ СЕРВЕРА НЕТУ
                // TODO ЗАДАНИЕ ДЛЯ СЕРВЕР JOBSERVERTASK #5
                case "Хотим Получить Статус Реальной Работы SQL SERVER":
                    // TODO РЕАЛЬНЫЙ СТАТУС РАБОТЫ SQL SERVER
                    ЛистДанныеОтHibenide = Метод_РеальнаяСтатусSqlServer();
                    ЛОГ.log(" Отправили Хотим Получить Статус Реальной Работы SQL SERVER  JobForServer " + JobForServer
                            + " ЛистДанныеОтHibenide "
                            + ЛистДанныеОтHibenide.size());
                    break;
                // TODO ЗАДАНИЯ ДЛЯ СЕРВЕРА НЕТУ
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
                            "Метод_РеальнаяСтатусSqlServer  ЛистДанныеОтHibenide " +ЛистДанныеОтHibenide.size());
                    break;
            }


            ЛОГ.log(" ОТВЕТ КЛИЕНТУ OTBEN LKIENTYY JobForServer " + JobForServer
                    + " БуферCallsBackДляAndroid " + БуферCallsBackДляAndroid.toString()+  "ЛистДанныеОтHibenide "+ ЛистДанныеОтHibenide);
            // TODO КОГДА ЛОГИН И ПАРОЛЬ НЕТ ДОСТУПА
            МетодЗакрываемСессиюHibernate();


            //TODO ГЕНЕРАЦИЯ JSON ПО НОВОМУ
            if (ЛистДанныеОтHibenide.size()>0) {
                БуферCallsBackДляAndroid = МетодГенерацияJSONJackson(ЛистДанныеОтHibenide);
            }


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
// TODO реальный статус POST SQl Servera
    protected List<model.UsersEntity> Метод_РеальнаяСтатусSqlServer() {
        List<model.UsersEntity> ЛистДанныеОтHibenide  = new ArrayList<>();
        try {
            org.hibernate.Query queryДляHiberite   = session.createQuery("SELECT  us as id FROM model.UsersEntity us WHERE us.rights =:rights   ");
            queryДляHiberite.setParameter("rights",new Integer(2));//8641 8625
            ЛистДанныеОтHibenide =( List<model.UsersEntity>) queryДляHiberite.setMaxResults(1).getResultList();
            ЛОГ.log("\n"+" Starting.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+
                    "Метод_РеальнаяСтатусSqlServer  ЛистДанныеОтHibenide " +ЛистДанныеОтHibenide.size());
        } catch (Exception e) {
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletDSU1.txt");
        }
        return ЛистДанныеОтHibenide;
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
