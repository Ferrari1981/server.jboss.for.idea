package businesslogic;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Base64;
import java.util.Optional;

/**
 * Session Bean implementation class BeanAuntifications
 */
@Stateless(mappedName = "SessionBeanAynt")
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class BeanAuntifications {
    @Inject
    private	SubClassConnectionsSQLServer subClassConnectionsSQLServer;
    /**
     * Default constructor.
     */
    public BeanAuntifications() {
        // TODO Auto-generated constructor stub
    }

    @SuppressWarnings("unused")
    public Boolean МетодАунтификация(@NotNull ServletContext ЛОГ,
                                     @NotNull HttpServletRequest request,
                                     @NotNull HttpServletResponse response,
                                     @NotNull HttpSession session) {
        int РазрешонныеПрава = 2;
        Integer		IDПолученныйИзSQlServerПосик=0;/// вычисялем
        String	ИмяПолученныйИзSQlServerПосик = null ;/// вычисялем
        String		ПарольПолученныйИзSQlServerПосик = null ;/// вычисялем
        Boolean РезультатАунтификацииПользователя=false;
        try (Connection conn =subClassConnectionsSQLServer.МетодGetConnect(	ЛОГ);){
            ЛОГ.log("\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+ ЛОГ +" request " + request + " response " + response);
            //TODO
            Statement stmt =subClassConnectionsSQLServer.МетодGetSmtr(conn, ЛОГ);
            ЛОГ.log(" ОТРАБОТАЛ МЕТОД ИНИЦИАЛИЗАЦИИ ПЕРЕМЕННЫХ КОТОРЫ Е ПРИШЛИ  МетодПредворительногоПодключенияДляМетодаGETкодИзКонструктора   "+ stmt);

            ////// TODO СКАНИРУЕМ ПОЛУЧЕНЫЙ ЛОГИН
            String    ЛогинПолученныйОтКлиента =Optional.ofNullable(request.getHeader("identifier")).orElse("") ;/// содержимое
            ////// TODO СКАНИРУЕМ ПОЛУЧЕНЫЙ ПАРОЛЬ
            String	ПарольПолученныйОтКлиента = Optional.ofNullable(request.getHeader("p_identifier")).orElse("") ;/// содержимое
            String	ПубличныйIDАндройдКлиента = Optional.ofNullable(request.getHeader("public_clientandroid")).orElse("") ;/// содержимое

            ЛОГ.log(" ПарольПолученныйОтКлиента " +ПарольПолученныйОтКлиента+" ЛогинПолученныйОтКлиента " + ЛогинПолученныйОтКлиента+  " ПубличныйIDАндройдКлиента " +ПубличныйIDАндройдКлиента);

            String	ЛогинПолученныйОтКлиентаgetSession =  (String) session.getAttribute("ПарольПолученныйОтКлиента");
            ЛОГ.log("  ЛогинПолученныйОтКлиентаgetSession " + ЛогинПолученныйОтКлиентаgetSession);
            if (ЛогинПолученныйОтКлиентаgetSession !=null && ЛогинПолученныйОтКлиентаgetSession.equalsIgnoreCase(ЛогинПолученныйОтКлиента)) {
                //TODO меняем статут и пускак клиента на сервер
                РезультатАунтификацииПользователя=true;
            }else {
                /////// ПОЛУЧЕНИИ КОЛИЧЕСТВА
                /////// СТОЛБЦОВ В БАЗЕ
                String	queryСканируемИмяИпароль = "SELECT   id ,login,password  FROM    [storage].[dbo].[users]    "
                        + "          WHERE login  =  '" + ЛогинПолученныйОтКлиента
                        + "' AND rights= '" + РазрешонныеПрава + "'    ;";//// ЗАПРОС
                //////
                System.out.println(" queryСканируемИмяИпароль   " + queryСканируемИмяИпароль);
                // TODO получаем имя и пвроль
                //////// запрос вычисляет имя и пароль и id
                ResultSet РезультатСканированиеИмениИПароль = stmt.executeQuery(queryСканируемИмяИпароль);
                РезультатСканированиеИмениИПароль.last();
                // todo НАЛИЧИЕ ПОЛЬЗОВАТЕЛЯ В БАЕЗ
                int НаличиеХотябыОднуСТрочкуПользоватлеьВБАзеТакойВообщеЕсть = РезультатСканированиеИмениИПароль
                        .getRow();
                РезультатСканированиеИмениИПароль.beforeFirst();
                System.out.println(" protected void doGet НаличиеХотябыОднуСТрочкуПользоватлеьВБАзеТакойВообщеЕсть"
                        + НаличиеХотябыОднуСТрочкуПользоватлеьВБАзеТакойВообщеЕсть);
                // TODO ЕСЛИ У ТЕКУЩЕГО ПОЛЬЗОВАТЕЛЯ НЕТ ПРАВ НАПРИМЕР--2 ТО
                if (НаличиеХотябыОднуСТрочкуПользоватлеьВБАзеТакойВообщеЕсть > 0) {
                    //// TODO СЮДА ЗАХОДИМ КОГДА ПОЛЬЗОВАТЕЛЬ
                    while (РезультатСканированиеИмениИПароль.next()) { //// КОЛИЧЕСТВО
                        IDПолученныйИзSQlServerПосик = РезультатСканированиеИмениИПароль.getInt(1);/// вычисялем
                        ИмяПолученныйИзSQlServerПосик = РезультатСканированиеИмениИПароль.getString(2);/// вычисялем
                        ПарольПолученныйИзSQlServerПосик = РезультатСканированиеИмениИПароль.getString(3);/// вычисялем

                        ЛОГ.log("  ЛогинПолученныйОтКлиента  "
                                + ЛогинПолученныйОтКлиента +
                                " ИмяПолученныйИзSQlServerПосик " +ИмяПолученныйИзSQlServerПосик
                                + " ПарольПолученныйИзSQlServerПосик " +ПарольПолученныйИзSQlServerПосик +
                                " ПарольПолученныйОтКлиента " +ПарольПолученныйОтКлиента+" СколькСтрокРезультатЕслиТакойПользовательМетод_GET "
                                + НаличиеХотябыОднуСТрочкуПользоватлеьВБАзеТакойВообщеЕсть);
                        ////
                        if (ЛогинПолученныйОтКлиента.trim().equals(ИмяПолученныйИзSQlServerПосик)
                                &&  ПарольПолученныйИзSQlServerПосик.compareTo(ПарольПолученныйОтКлиента)==0 && IDПолученныйИзSQlServerПосик>0) { ///// TODO
                            //TODO меняем статут и пускак клиента на сервер
                            РезультатАунтификацииПользователя=true;

                            //TODO ЗАПЫИСЫВАМ ПУБЛИЧНЫЙ ID  ТЕКУЩЕГО ПОЛЗОВАТКЕЛЯ
                            ЛОГ.setAttribute("IDПолученныйИзSQlServerПосик", IDПолученныйИзSQlServerПосик);
                            ЛОГ.setAttribute("ЛогинПолученныйОтКлиента", ЛогинПолученныйОтКлиента);
                            ЛОГ.setAttribute("ПарольПолученныйОтКлиента", ПарольПолученныйОтКлиента);


                            session.setAttribute("ЛогинПолученныйОтКлиента", ЛогинПолученныйОтКлиента);
                            session.setAttribute("ПарольПолученныйОтКлиента", ПарольПолученныйОтКлиента);
                            session.setAttribute("IDПолученныйИзSQlServerПосик", IDПолученныйИзSQlServerПосик);

                            ЛОГ.log("  ЛогинПолученныйОтКлиента  "
                                    + ЛогинПолученныйОтКлиента +
                                    " ИмяПолученныйИзSQlServerПосик " +ИмяПолученныйИзSQlServerПосик
                                    + " ПарольПолученныйИзSQlServerПосик " +ПарольПолученныйИзSQlServerПосик +
                                    " ПарольПолученныйОтКлиента " +ПарольПолученныйОтКлиента);
                        }

                        break;
                        ///////////////////////HeaderСодержимоеРасшифрован
                    }
                }
                РезультатСканированиеИмениИПароль.close();
                stmt.close();
                conn.close();
                //TODO
                ЛОГ.log( " Класс"+Thread.currentThread().getStackTrace()[2].getClassName()
                        +"\n"+
                        " метод "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"
                        + "Строка " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "РезультатСканированиеИмениИПароль " +РезультатСканированиеИмениИПароль);
            }

        } catch (Exception e) {
            new SubClassWriterErros().МетодаЗаписиОшибкиВЛог(e, null,
                    "\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                            " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                            " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n",
                    Thread.currentThread().getStackTrace()[2],ЛОГ,ЛОГ.getServerInfo().toLowerCase());
        }
        return РезультатАунтификацииПользователя;
    }

}
