package runtimes;

import businesslogic.BEANCallsBack;
import businesslogic.StreamJSONJacksons;
import businesslogic.SubClassWriterErros;
import com.sun.istack.NotNull;
import dsu1glassfishatomic.workinterfaces.ProducedCard;
import org.hibernate.*;
import javax.ejb.*;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringWriter;
import java.sql.*;
import java.util.Date;
import java.util.*;
import java.util.concurrent.ExecutionException;

@Stateless(mappedName = "SessionBeanGETRuntimeJboss")
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class SessionBeanGETRuntimeJboss {// extends WITH

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


    @Inject
    BEANCallsBack bEANCallsBack;
    @Inject
    SubClassWriterErros subClassWriterErros;

    @Inject @ProducedCard
    SessionFactory sessionSousJboss;

    private Session session;
    private Transaction sessionTransaction  ;

    @Inject
    StreamJSONJacksons streamJSONJacksons ;

    public SessionBeanGETRuntimeJboss() {

        System.out.println("Конструктор  SessionBeanGETRuntimeJboss");

    }


    public void МетодГлавныйRuntimeJboss(@NotNull ServletContext ЛОГ,
                                         @NotNull HttpServletRequest request,
                                         @NotNull  HttpServletResponse response) throws InterruptedException, ExecutionException {;
        try {
            ///Todo  получаем данные от клиента
           StringBuffer БуферРезультатRuntime= 	 МетодЗапускаRuntime(request,ЛОГ,response);
            ///Todo получаем данные от Клиента на Сервер
            bEANCallsBack.МетодBackДанныеКлиенту(response, БуферРезультатRuntime, ЛОГ);
            ЛОГ.log("\n"+" Starting.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+"  БуферРезультатRuntime  " + БуферРезультатRuntime );
        } catch (Exception e) {
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletRuntime.txt");
        }
    }







    @SuppressWarnings({ "unused", "deprecation", "rawtypes", "unchecked" })
    public StringBuffer МетодЗапускаRuntime(@NotNull HttpServletRequest request,
                                                    @NotNull ServletContext ЛОГ,
                                                    @NotNull  HttpServletResponse response) throws SecurityException, SQLException {
        // TODO Auto-generated method stub
        StringBuffer БуферCallsBackДляAndroid = null;
        try  {
            List<?>         ЛистДанныеОтHibenide = null;
            this.ЛОГ = ЛОГ;
            // TODO
            this.request = request;
            /// TODO
            this.response = response;
            // TODO Коннектимся к Базе SQl Server
            ЛОГ.log("ЛОГ  GET() " + ЛОГ + " request " + request + " response " + response);
            ////
            БуферCallsBackДляAndroid = new StringBuffer();
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
            ЛОГ.log("СТАРТ/START МЕТОД/METOD  protected void doGet  logger  ::: " + "\n");
            ///// TODO создание клуча
            String ТолькоДляАунтификацииИмяПолученныйИзSQlServerПосик = new String();
            //////
            String ПараметрИмяТаблицыОтАндройдаGET = new String();/// ОПРЕДЕЛЯЕМ
            /////// НАЧАЛО КОД ДОСТУПА К СЕРВЛЕТУ
            String HeaderСодержимое = new String();
            ИмяПолученныйИзSQlServerПосик = new String();/// вычисялем
            String ПарольПолученныйИзSQlServerПосик = null;

            String queryСканируемИмяИпароль;
            String HeaderСодержимоеРасшифрован = null;

            // TODO: 10.03.2023 получение сессиии HIREBIANTE
            session=   sessionSousJboss.getCurrentSession();
            // TODO: 10.03.2023 получение сессиии Transaction
            sessionTransaction = session.getTransaction();
            sessionTransaction.begin();

            /// TODO ПАРАМЕНТ #1
            ПараметрИмяТаблицыОтАндройдаGET = Optional.ofNullable(request.getParameter("ИмяТаблицыОтАндройда"))
                    .map(String::trim).orElse("");
            System.out.println("  ПараметрИмяТаблицыОтАндройдаGET " + ПараметрИмяТаблицыОтАндройдаGET);
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

            /// TODO ПАРАМЕНТ #7
            String ПараметрПользовательФильтр=Optional.ofNullable(request.getParameter("IDДляПолучениеКонткртнойНабораТаблиц")).map(String::new).orElse("");
            if(ПараметрПользовательФильтр.length()>0) {
                ПараметрТекущийПользователь = Optional.ofNullable(ПараметрПользовательФильтр).map(Integer::new).orElse(0);
                ЛОГ.log("  ПараметрФильтрПолучаемыхТаблицДляАндройда  "+ ПараметрТекущийПользователь); //setParameter
            }


            /// TODO ПАРАМЕНТ #8
            String ПараметрВерсияДанныхФильтр=	 Optional.ofNullable(request.getParameter("РезультаПолученаяЛокальнаяВерсияДанныхДляОтправкиНаСервер")).map(String::new).orElse("");
            if (ПараметрВерсияДанныхФильтр.length()>0) {
                ПараметрВерсияДанных= Optional.ofNullable(ПараметрВерсияДанныхФильтр).map(Long::new).orElse(0l);
                ЛОГ.log("  РезультатОтАндройдаЕгоЛокальнаяВерсияЧата  "	+ ПараметрВерсияДанных); //setParameter
            }

            // TODO ТРЕТИЙ ДЕЙСТВИЕ САМА РАБОТА КОТОРУЮ НУЖНО СЕРВЕРУ ВЫПОЛНИТЬ

            ЛОГ.log("request.getParameter(\"ЗаданиеДляСервлетаВнутриПотока\")+ " + request.getParameter("ЗаданиеДляСервлетаВнутриПотока"));

            JobsFroServerЗаданиеДляСервера = Optional.ofNullable(request.getParameter("ЗаданиеДляСервлетаВнутриПотока"))
                    .orElse("");

            switch (JobsFroServerЗаданиеДляСервера) {
                // TODO ЗАДАНИЕ ДЛЯ СЕРВЕР JOBSERVERTASK #5
                case "Хотим Получить Статус Реальной Работы SQL SERVER":
                    // TODO РЕАЛЬНЫЙ СТАТУС РАБОТЫ SQL SERVER
                       ЛистДанныеОтHibenide = Метод_РеальнаяСтатусSqlServer();
                    ЛОГ.log(" Отправили Хотим Получить Статус Реальной Работы SQL SERVER " + JobsServerСазаданиеДляСервера
                            + " БуферCallsBackДляAndroid "
                            + БуферCallsBackДляAndroid.toString());
                    break;
                // TODO ЗАДАНИЯ ДЛЯ СЕРВЕРА НЕТУ
                default:
                    break;
            }
            StringBuffer БуферДоОбработкиСтатуса = streamJSONJacksons.getStreamJacksons(ЛистДанныеОтHibenide);
            // TODO
            БуферCallsBackДляAndroid.append(БуферДоОбработкиСтатуса.toString().trim().replaceAll("[^\\da-zA-Zа-яёА-ЯЁ ]","")) ;
            ЛОГ.log("БуферCallsBackДляAndroid.toString() " + "" + БуферCallsBackДляAndroid.toString());

            МетодЗакрываемСессиюHibernate(ЛОГ);
            /////// ошибки метода doGET
        } catch (Exception e) {
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletRuntime.txt");

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
                            ЛОГ,"ErrorsLogs/ErrorJbossServletRuntime.txt");
        }
        return ПолученныйИзSqlServerПубличныйIDДляОтправкиНААндройд;
    }

    // TODO еще ОДНИ метод перенесенный в метод GET


    protected StringBuffer Метод_МетодаGETОтправляемБлокировкуПользователюID(HttpServletResponse response,
                                                                             String ПараметрФильтрЗадааниеДляСервлета, Integer IDПолученныйИзSQlServerПосик, Connection conn) {
        StringBuffer ПолучаемОтSqlServerСтатусНАПользователяАндройдНЕЗАпблорированЛиОн = new StringBuffer();
        // TODO ЗАПУСКАЕМ МЕНЕДЖЕР ПОТОКОВ ДЯЛ ПЕРВОГО ЗАДАНИЕ ВЕРСИЮ ДАННЫХ
        boolean РезультатЗаблокированПользовательИлиНЕТ = false;
        try {
            ЛОГ.log(" Хотим Получить Статус Блокировки Пользователя по ID " + ПараметрФильтрЗадааниеДляСервлета
                    + " IDПолученныйИзSQlServerПосик " + IDПолученныйИзSQlServerПосик);
            String queryJSON = null;
            String ФиналРезультатЗаблокированПользовательИлиНЕТ = null;
            queryJSON = " SELECT locked FROM     [storage].[dbo].[users]        WHERE id = "
                    + IDПолученныйИзSQlServerПосик + "   AND date_update IS NOT NULL   ;"; // цифра
            PreparedStatement РезультатПолученияСтатусаЗаблокированогоПользователя = conn.prepareStatement(queryJSON,
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSetРезультатПолученияСтатусаЗаблокированогоПользователя = РезультатПолученияСтатусаЗаблокированогоПользователя
                    .executeQuery();
            resultSetРезультатПолученияСтатусаЗаблокированогоПользователя.last();
            int ФлагХотеЕстьОднаСтрокаДляЦиклаДва = resultSetРезультатПолученияСтатусаЗаблокированогоПользователя
                    .getRow();
            resultSetРезультатПолученияСтатусаЗаблокированогоПользователя.beforeFirst();
            if (ФлагХотеЕстьОднаСтрокаДляЦиклаДва > 0) {
                while (resultSetРезультатПолученияСтатусаЗаблокированогоПользователя.next()) {
                    @SuppressWarnings("unused")
                    int id = resultSetРезультатПолученияСтатусаЗаблокированогоПользователя.getInt("locked");
                    РезультатЗаблокированПользовательИлиНЕТ = resultSetРезультатПолученияСтатусаЗаблокированогоПользователя
                            .getBoolean("locked");
                    /////////////////////////////
                    ФиналРезультатЗаблокированПользовательИлиНЕТ = String
                            .valueOf(РезультатЗаблокированПользовательИлиНЕТ);
                }
            }
            ПолучаемОтSqlServerСтатусНАПользователяАндройдНЕЗАпблорированЛиОн
                    .append(ФиналРезультатЗаблокированПользовательИлиНЕТ);
            ////
            if (РезультатПолученияСтатусаЗаблокированогоПользователя != null) {
                if (!РезультатПолученияСтатусаЗаблокированогоПользователя.isClosed()) {
                    РезультатПолученияСтатусаЗаблокированогоПользователя.close();
                }
            }
            ЛОГ.log(" ПолучаемОтSqlServerСтатусНАПользователяАндройдНЕЗАпблорированЛиОн"
                    + ПолучаемОтSqlServerСтатусНАПользователяАндройдНЕЗАпблорированЛиОн);
        } catch (Exception e) {
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletRuntime.txt");
        }
        return ПолучаемОтSqlServerСтатусНАПользователяАндройдНЕЗАпблорированЛиОн;

    }











    // TODO реальный статус POST SQl Servera
    protected List<model.UsersEntity> Метод_РеальнаяСтатусSqlServer() {
        List<model.UsersEntity> ЛистДанныеОтHibenide  = new ArrayList<>();
        try {
            org.hibernate.Query queryДляHiberite   = session.createQuery("SELECT  us FROM model.UsersEntity us WHERE us.rights =:rights   ");
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
}
