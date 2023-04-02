package businesslogic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import dsu1glassfishatomic.workinterfaces.ProducedCard;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * Session Bean implementation class BeanAuntifications
 */
@Stateless(mappedName = "SessionBeanAynt")
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class BeanAuntifications {

    @Inject
    SubClassWriterErros subClassWriterErros;
    private Session session;
    private Transaction sessionTransaction  ;
    @Inject @ProducedCard
    SessionFactory sessionSousJboss;
    @Inject
    ObjectMapper getGeneratorJackson;
    /**
     * Default constructor.
     */
    public BeanAuntifications() {
        // TODO Auto-generated constructor stub
    }


    @SuppressWarnings("unused")
    public Boolean МетодАунтификация(@NotNull ServletContext ЛОГ,
                                     @NotNull HttpServletRequest request,
                                     @NotNull HttpSession sessionEJB) {
        int РазрешонныеПрава = 2;
        Boolean РезультатАунтификацииПользователя=false;
        try {
            ЛОГ.log("\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+ ЛОГ +" request " + request );

            String    ЛогинОтКлиента =((HttpServletRequest) request).getHeader("identifier");
            String ПарольОтКлиента =((HttpServletRequest) request).getHeader("p_identifier");
            String ИдиДевайсаПолученный =((HttpServletRequest) request).getHeader("id_device_androis");
            ЛОГ.log(" ЛогинОтКлиента " +ЛогинОтКлиента+" ЛогинОтКлиента " + ЛогинОтКлиента +  " ИдиДевайсаПолученный " +ИдиДевайсаПолученный);
            ////// TODO полученный нданные от Клиента
            if (ЛогинОтКлиента.trim().length()>0 && ПарольОтКлиента.trim().length()>0 && ИдиДевайсаПолученный.trim().length()>0) {
                // TODO: 10.03.2023 получение сессиии HIREBIANTE
                session = sessionSousJboss.getCurrentSession();
                // TODO: 10.03.2023 получение сессиии Transaction
                sessionTransaction = session.getTransaction();
                sessionTransaction.begin();
                // TODO: 02.04.2023 Проводим Аунтификаций через пароли логин
                org.hibernate.Query queryДляHiberite   = session.createQuery("SELECT  us FROM model.UsersEntity us WHERE us.rights =:rights  AND us.login=:login AND us.password=:password ");

                queryДляHiberite.setParameter("rights",new Integer(2));//8641 8625
                queryДляHiberite.setParameter("login",new String(ЛогинОтКлиента));//8641 8625
                queryДляHiberite.setParameter("password",new String(ПарольОтКлиента));//8641 8625
           List<model.UsersEntity>    ЛистДанныеОтHibenide =( List<model.UsersEntity>) queryДляHiberite.setMaxResults(1).getResultList();
                // TODO: 02.04.2023 Вытаскиваем Из ПРишедзиъ данных логин и пароль
                StringBuffer БуферСозданогоJSONJacksonАунтификация = МетодГенерацияJSONJackson(ЛОГ, ЛистДанныеОтHibenide);


                Integer IDПолученныйИзSQlServer = ЛистДанныеОтHibenide.get(0).getId();
                String ЛогинОтКлиентаИзSQlServer= ЛистДанныеОтHibenide.get(0).getLogin();
                String ПарольИзSQlServer= ЛистДанныеОтHibenide.get(0).getPassword();


                ЛОГ.log("\n"+" Starting.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                        " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                        " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+
                        " ЛистДанныеОтHibenide " +ЛистДанныеОтHibenide.toString()  +  "ЛогинОтКлиентаИзSQlServer "+ЛогинОтКлиентаИзSQlServer +
                        "ПарольИзSQlServer " +ПарольИзSQlServer  + "IDПолученныйИзSQlServer " +IDПолученныйИзSQlServer);

                if (ЛистДанныеОтHibenide.size() > 0) {
                    //// TODO СЮДА ЗАХОДИМ КОГДА ПОЛЬЗОВАТЕЛЬ
                        if (ЛогинОтКлиента.compareTo(ЛогинОтКлиентаИзSQlServer.toString())==0
                                &&  ПарольОтКлиента.compareTo(ПарольИзSQlServer.toString())==0
                                && Integer.parseInt(IDПолученныйИзSQlServer.toString())>0
                                && ИдиДевайсаПолученный.toString().length()>0) { ///// TODO
                            //TODO ЗАПЫИСЫВАМ ПУБЛИЧНЫЙ ID  ТЕКУЩЕГО ПОЛЗОВАТКЕЛЯ
                            ЛОГ.setAttribute("IDПолученныйИзSQlServerПосик", IDПолученныйИзSQlServer);
                            ЛОГ.setAttribute("ЛогинПолученныйОтКлиента", ЛогинОтКлиента);
                            ЛОГ.setAttribute("ПарольПолученныйОтКлиента", ПарольОтКлиента);
                            ЛОГ.setAttribute("АдуДевайсяКлиента", ИдиДевайсаПолученный);


                            sessionEJB.setAttribute("IDПолученныйИзSQlServerПосик", IDПолученныйИзSQlServer);
                            sessionEJB.setAttribute("ЛогинПолученныйОтКлиента", ЛогинОтКлиента);
                            sessionEJB.setAttribute("ПарольПолученныйОтКлиента", ПарольОтКлиента);
                            sessionEJB.setAttribute("АдуДевайсяКлиента", ИдиДевайсаПолученный);

                            //TODO меняем статут и пускак клиента на сервер ВАЖНО
                            РезультатАунтификацииПользователя=true;
                            ЛОГ.log("  ЛогинОтКлиента  "
                                    + ЛогинОтКлиента +
                                    " IDПолученныйИзSQlServer " +IDПолученныйИзSQlServer
                                    + " ПарольОтКлиента " +ПарольОтКлиента +
                                    " ЛогинОтКлиента " +ЛогинОтКлиента+ " ИдиДевайсаПолученный "+ИдиДевайсаПолученный);
                        }
                }
                //TODO
                ЛОГ.log( " Класс"+Thread.currentThread().getStackTrace()[2].getClassName()
                        +"\n"+
                        " метод "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"
                        + "Строка " + Thread.currentThread().getStackTrace()[2].getLineNumber());
            }else {
                //TODO
                //TODO меняем статут и пускак клиента на сервер
                РезультатАунтификацииПользователя=false;
                ЛОГ.log( " Класс"+Thread.currentThread().getStackTrace()[2].getClassName()
                        +"\n"+
                        " метод "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"
                        + "Строка " + Thread.currentThread().getStackTrace()[2].getLineNumber()+  "РезультатАунтификацииПользователя " +РезультатАунтификацииПользователя);
            }
            // TODO: 02.04.2023 закрываем сессию
            МетодЗакрываемСессиюHibernate(ЛОГ);
        } catch (Exception e) {
            sessionTransaction.rollback();
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletDSU1.txt");
        }
        return РезультатАунтификацииПользователя;
    }
    public StringBuffer МетодГенерацияJSONJackson(@NotNull ServletContext ЛОГ,
                                             @javax.validation.constraints.NotNull List<?> listОтHiberideДляГенерации) {
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

        } catch (Exception e) {
            sessionTransaction.rollback();
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletDSU1.txt");
        }
        return БуферСозданогоJSONJackson;
    }
    private void МетодЗакрываемСессиюHibernate(@NotNull ServletContext ЛОГ) {
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
