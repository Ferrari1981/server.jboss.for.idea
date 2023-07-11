package runtimejboss;

import businesslogic.SubClassWriterErros;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import dsu1glassfishatomic.workinterfaces.ProducedCard;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;

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
 * Session Bean implementation class BeanGetLoginAndPasswords
 */
@Stateless(mappedName = "SessionBeanAynt")
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class BeanGetLoginAndPasswords {

    @Inject
    SubClassWriterErros subClassWriterErros;
    private Session session;
    @Inject @ProducedCard
    SessionFactory sessionSousJboss;
    @Inject
    ObjectMapper getGeneratorJackson;
    /**
     * Default constructor.
     */
    public BeanGetLoginAndPasswords() {
        // TODO Auto-generated constructor stub
    }


    @SuppressWarnings("unused")
    public Boolean МетодGetLoginAndPassword(@NotNull ServletContext ЛОГ,
                                            @NotNull HttpServletRequest request,
                                            @NotNull HttpSession sessionEJB) {
        int РазрешонныеПрава = 2;
        Boolean РезультатАунтификацииПользователя=false;
        try {
            ЛОГ.log("\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+ ЛОГ +" request " + request );

            String    ЛогинОтКлиента =  Optional.ofNullable(((HttpServletRequest) request).getHeader("identifier") ).orElse("")     ;
            String ПарольОтКлиента =  Optional.ofNullable(((HttpServletRequest) request).getHeader("p_identifier") ).orElse("");
            String IDДевайсаКлиента =  Optional.ofNullable(((HttpServletRequest) request).getHeader("id_device_androis") ).orElse("");
            ЛОГ.log(" ЛогинОтКлиента " +ЛогинОтКлиента+" ЛогинОтКлиента " + ЛогинОтКлиента );
            ////// TODO полученный нданные от Клиента
            if (ЛогинОтКлиента.trim().length()>3 && ПарольОтКлиента.trim().length()>3 ) {
                // TODO: 10.03.2023 получение сессиии HIREBIANTE
                session = sessionSousJboss.getCurrentSession();
                // TODO: 10.03.2023 получение сессиии Transaction
                if (session.getTransaction().getStatus()== TransactionStatus.NOT_ACTIVE) {
                    session.getTransaction().begin();
                }
                // TODO: 02.04.2023 Проводим Аунтификаций через пароли логин
                org.hibernate.Query queryДляHiberite   = session.createQuery("SELECT " +
                        " us FROM model.UsersEntitySuccess us WHERE us.rights =:rights  AND us.login=:login AND us.password=:password ");

                queryДляHiberite.setParameter("rights",new Integer(2));//8641 8625
                queryДляHiberite.setParameter("login",new String(ЛогинОтКлиента));//8641 8625
                queryДляHiberite.setParameter("password",new String(ПарольОтКлиента));//8641 8625
           List<model.UsersEntitySuccess>    ЛистДанныеОтHibenide =( List<model.UsersEntitySuccess>) queryДляHiberite.setMaxResults(1).getResultList();

                // TODO: 11.07.2023 ЕСЛИ ЕСТь ЧТо то создаем JSON
                if (ЛистДанныеОтHibenide !=null && ЛистДанныеОтHibenide.size() > 0) {
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


                    //// TODO СЮДА ЗАХОДИМ КОГДА ПОЛЬЗОВАТЕЛЬ
                        if (ЛогинОтКлиента.compareTo(ЛогинОтКлиентаИзSQlServer.toString())==0
                                &&  ПарольОтКлиента.compareTo(ПарольИзSQlServer.toString())==0
                                && Integer.parseInt(IDПолученныйИзSQlServer.toString())>0
                                && IDДевайсаКлиента.toString().length()>5) { ///// TODO
                            //TODO ЗАПЫИСЫВАМ ПУБЛИЧНЫЙ В ЛОГ
                            ЛОГ.setAttribute("IdUser", IDПолученныйИзSQlServer);
                            ЛОГ.setAttribute("ЛогинПолученныйОтКлиента", ЛогинОтКлиента.trim());
                            ЛОГ.setAttribute("АдуДевайсяКлиента", IDДевайсаКлиента.trim());
                            //TODO ЗАПЫИСЫВАМ ПУБЛИЧНЫЙ В Session
                            sessionEJB.setAttribute("IDПолученныйИзSQlServerПосик", IDПолученныйИзSQlServer);
                            sessionEJB.setAttribute("ЛогинПолученныйОтКлиента", ЛогинОтКлиента);
                            sessionEJB.setAttribute("АдуДевайсяКлиента", IDДевайсаКлиента);

                            //TODO меняем статут и пускак клиента на сервер ВАЖНО
                            РезультатАунтификацииПользователя=true;
                            ЛОГ.log("  ЛогинОтКлиента  "
                                    + ЛогинОтКлиента +
                                    " IDПолученныйИзSQlServer " +IDПолученныйИзSQlServer
                                    + " ПарольОтКлиента " +ПарольОтКлиента +
                                    " ЛогинОтКлиента " +ЛогинОтКлиента+ " IDДевайсаКлиента "+IDДевайсаКлиента);
                        }
                }else {
                    //TODO меняем статут и пускак клиента на сервер
                    РезультатАунтификацииПользователя=false;
                    ЛОГ.log( " Класс"+Thread.currentThread().getStackTrace()[2].getClassName()
                            +"\n"+
                            " метод "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"
                            + "Строка " + Thread.currentThread().getStackTrace()[2].getLineNumber()+  "РезультатАунтификацииПользователя " +РезультатАунтификацииПользователя);
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
            session.getTransaction().rollback();
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
            session.getTransaction().rollback();
            session.close();
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
            session.getTransaction().rollback();
            session.close();
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletDSU1.txt");
        }
    }
}
