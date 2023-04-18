package businesslogic;


import java.sql.SQLException;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.servlet.ServletContext;

import com.sun.istack.NotNull;
import dsu1glassfishatomic.workinterfaces.ProducedCard;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Predicate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;

//TODO
@RequestScoped
public class SubClassВставкаДанныхОтКлиентаPOST {
    private   ServletContext ЛОГ;
    private Transaction sessionTransaction;
    @Inject @ProducedCard
    SessionFactory sessionSousJboss;
    Session    session;
    @Inject
    SubClassWriterErros subClassWriterErros;
    // TODO: 09.03.2023
    StringBuffer методВставкаИлиОбновлениеДаннымиОтАкдройда(
            @NotNull ServletContext ЛОГ,
            @NotNull CopyOnWriteArrayList<Map<String, String>> БуферJSONJackson
            , @NotNull String ПараметрИмяТаблицыОтАндройдаPost) throws SQLException {
        StringBuffer БуферОтветКлиентуОтСервера=new StringBuffer();
        try {
            this.ЛОГ=ЛОГ;
            // TODO: 11.03.2023  Получении Сесии Hiberrnate
            session =sessionSousJboss.getCurrentSession();
            // TODO: 14.03.2023  Запускает Транзакции
            // TODO: 14.03.2023  Запускаем Транзакцию
            sessionTransaction =session.getTransaction() ;
            if (sessionTransaction.getStatus()== TransactionStatus.NOT_ACTIVE) {
                sessionTransaction.begin();
            }
            ЛОГ.log("\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+
                    " БуферJSONJackson "+БуферJSONJackson.toString()  +
                    " session  " +session + " sessionSousJboss " +sessionSousJboss);
                //TODO определем если в таблицы есть поле  UUID или ID
                StoredProcedureQuery queryprocedure = МетодПолучениеХранимойПроцедуры(ЛОГ, ПараметрИмяТаблицыОтАндройдаPost);
                ЛОГ.log("\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                        " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                        " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+" queryprocedure " +queryprocedure);
                //TODO Заполения
                if(queryprocedure!=null) {
                    ЛОГ.log(" NOT NULL queryprocedure " + queryprocedure);
                    //TODO ГЛАВЕНЫЙ ЦИКЛ ОБРАБОТКИ ДАННЫХ В МЕТОДЕ  POST
                    final String[] UUIDСотсыковочныйХранимойПроцедуры = {null};
                    Flowable.fromIterable(БуферJSONJackson)
                            .onBackpressureBuffer(true)
                            .doOnNext(new Consumer<Map<String, String>>() {
                        @Override
                        public void accept(Map<String, String> stringStringMap) throws Throwable {
                            Flowable.fromIterable(stringStringMap.entrySet())
                                    .onBackpressureBuffer(true)
                                    .doOnNext(new Consumer<Entry<String, String>>() {
                                        @Override
                                        public void accept(Entry<String, String> stringStringEntry) throws Throwable {
                                            if (stringStringEntry.getKey().equalsIgnoreCase("uuid")) {
                                                UUIDСотсыковочныйХранимойПроцедуры[0] = stringStringEntry.getValue().trim();
                                            }
                                            // TODO заполенем JSonValue
                                            queryprocedure.registerStoredProcedureParameter(stringStringEntry.getKey(), String.class, ParameterMode.IN)
                                                    .setParameter(stringStringEntry.getKey(), stringStringEntry.getValue());

                                            ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                                                    " stringStringEntry.getKey() " + stringStringEntry.getKey() + " stringStringEntry.getValue() " + stringStringEntry.getValue()
                                                    + "  UUIDСотсыковочныйХранимойПроцедуры[0] " + UUIDСотсыковочныйХранимойПроцедуры[0]);
                                        }
                                    })
                                    .doOnError(new Consumer<Throwable>() {
                                        @Override
                                        public void accept(Throwable throwable) throws Throwable {
                                            ЛОГ.log("ERROR class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + " throwable " + throwable.getMessage());
                                        }
                                    })
                                    .doOnComplete(new Action() {
                                        @Override
                                        public void run() throws Throwable {
                                            queryprocedure.registerStoredProcedureParameter("SrabnitUUIDOrID", String.class, ParameterMode.IN).setParameter("SrabnitUUIDOrID", UUIDСотсыковочныйХранимойПроцедуры[0]);
                                            queryprocedure.registerStoredProcedureParameter("ResultatMERGE", String.class, ParameterMode.INOUT).setParameter("ResultatMERGE", "complete merge");

                                            Integer РезультатОперацииВставкииОбновлениея=  МетодСамогоВыполенияУдаленнойПроцедуры(queryprocedure,ЛОГ,ПараметрИмяТаблицыОтАндройдаPost);
                                            String РезультатСовершнойОперации= null;

                                            if (РезультатОперацииВставкииОбновлениея>0) {
                                                //TODO получаем ответный результат
                                                РезультатСовершнойОперации = (String) queryprocedure.getOutputParameterValue("ResultatMERGE");
                                                ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                                        + " РезультатОперацииВставкииОбновлениея " + РезультатОперацииВставкииОбновлениея + " РезультатСовершнойОперации " + РезультатСовершнойОперации);
                                                if (РезультатСовершнойОперации.matches("(.*)OriginalVesion(.*)")) {
                                                    РезультатСовершнойОперации=   РезультатСовершнойОперации.replaceAll("[^0-9]","");
                                                    // TODO: 18.04.2023  ответ клиенту
                                                    БуферОтветКлиентуОтСервера.append(РезультатСовершнойОперации);
                                                }else {
                                                    БуферОтветКлиентуОтСервера.append("0");
                                                }
                                            }
                                            ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                                                    "  UUIDСотсыковочныйХранимойПроцедуры[0] " + UUIDСотсыковочныйХранимойПроцедуры[0]  + " ЛОГИН "+ЛОГ.getAttribute("ЛогинПолученныйОтКлиента")+
                                                    " ID ТЕЛЕФОНА "+  ЛОГ.getAttribute("АдуДевайсяКлиента")+" +БуферОтветКлиентуОтСервера"+БуферОтветКлиентуОтСервера );
                                        }
                                    })
                                    .onErrorComplete(new Predicate<Throwable>() {
                                        @Override
                                        public boolean test(Throwable throwable) throws Throwable {
                                            ЛОГ.log("ERROR class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + " throwable " + throwable.getMessage());
                                            return false;
                                        }
                                    })
                                    .blockingSubscribe();
                        }
                    })
                            .onErrorComplete(new Predicate<Throwable>() {
                                @Override
                                public boolean test(Throwable throwable) throws Throwable {
                                    ЛОГ.log("ERROR class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + " throwable " + throwable.getMessage());
                                    return false;
                                }
                            })
                            .doOnComplete(new Action() {
                                @Override
                                public void run() throws Throwable {
                                    //TODO после цикла всех строк выключаем менеджеры сущностей  ПОСЛЕ ЦИКЛА С ДАННЫМИ
                                    МетодЗавершенияСеанса();
                                    ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                                }
                            })
                            .blockingSubscribe();

                    ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                            " БуферJSONJackson " + БуферJSONJackson.toString() +
                            " session  " + session + " sessionSousJboss " + sessionSousJboss);
                }
            //TODO
            ЛОГ.log("БуферОтветКлиентуОтСервера  "+ БуферОтветКлиентуОтСервера.toString());
        } catch (Exception   e) {
            sessionTransaction.rollback();
            session.close();
            ЛОГ.log( "ERROR class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()  + " e " +e.getMessage() );
            subClassWriterErros.МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletDSU1.txt");
        }
        return БуферОтветКлиентуОтСервера;
    }














    private StoredProcedureQuery МетодПолучениеХранимойПроцедуры(ServletContext ЛОГ, String ПараметрИмяТаблицыОтАндройдаPost) {
        //TODO ЗАПОЛЕНИЯ ТАБЛИЦ И  ОТПРАВКА ЗНАЧЕНИЙ В УДАЛЕННУЮ ПРОЦЕДУРУ
        StoredProcedureQuery	 queryprocedure = null;
        try{
        switch(ПараметрИмяТаблицыОтАндройдаPost) {
            case "settings_tabels":
                queryprocedure = session.createStoredProcedureQuery("ProcedureExistsMERGEsettings_tabels#2");//TODO ProcedureExistsMERGE_fio
                break;
            case "fio":
                queryprocedure = session.createStoredProcedureQuery( "ProcedureExistsMERGEfio#1");//TODO ProcedureExistsMERGE_fio
                break;
            case "data_tabels":
                queryprocedure = session.createStoredProcedureQuery( "ProcedureExistsMERGEdata_tabels#3");//TODO ProcedureExistsMERGE_fio
                break;
            case "tabel":
                queryprocedure = session.createStoredProcedureQuery( "ProcedureExistsMERGEtabel#4");//TODO ProcedureExistsMERGE_fio
                break;
            case "organization":
                queryprocedure = session.createStoredProcedureQuery( "ProcedureExistsMERGEorganization#5");//TODO ProcedureExistsMERGE_fio
                break;
            case "depatment":
                queryprocedure = session.createStoredProcedureQuery( "ProcedureExistsMERGEdepatment#6");//TODO ProcedureExistsMERGE_fio
                break;
            case "notifications":
                queryprocedure = session.createStoredProcedureQuery( "ProcedureExistsMERGEnotifications#7");//TODO ProcedureExistsMERGE_fio
                break;
            case "data_notification":
                queryprocedure = session.createStoredProcedureQuery( "ProcedureExistsMERGEdata_notification#8");//TODO ProcedureExistsMERGE_fio
                break;
            case "chats":
                queryprocedure = session.createStoredProcedureQuery( "ProcedureExistsMERGEchats#9");//TODO ProcedureExistsMERGE_fio
                break;
            case "data_chat":
                queryprocedure = session.createStoredProcedureQuery( "ProcedureExistsMERGEdata_chat#10");//TODO ProcedureExistsMERGE_fio
                break;
            case	"templates":
                queryprocedure = session.createStoredProcedureQuery( "ProcedureExistsMERGEtemplates#11");//TODO ProcedureExistsMERGE_fio
                break;
            case	"fio_template":
                queryprocedure = session.createStoredProcedureQuery( "ProcedureExistsMERGEfio_template#12");//TODO ProcedureExistsMERGE_fio
                break;
            case	"region":
                queryprocedure = session.createStoredProcedureQuery( "ProcedureExistsMERGEregion#13");//TODO ProcedureExistsMERGE_fio
                break;
            case	"cfo":
                queryprocedure = session.createStoredProcedureQuery( "ProcedureExistsMERGEcfo#14");//TODO ProcedureExistsMERGE_fio
                break;
            case	"get_materials_data":
                queryprocedure = session.createStoredProcedureQuery( "ProcedureExistsMERGEget_materials_data#17");//TODO ProcedureExistsMERGE_fio
                break;
            default:
                ЛОГ.log("\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                        " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                        " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+
                        " ПараметрИмяТаблицыОтАндройдаPost " + ПараметрИмяТаблицыОтАндройдаPost);
                break;
            ///TODO конец всех таблиц
        }
        ЛОГ.log("\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+
                " ПараметрИмяТаблицыОтАндройдаPost " + ПараметрИмяТаблицыОтАндройдаPost);
    } catch (Exception e) {
        subClassWriterErros.
                МетодаЗаписиОшибкиВЛог(e,
                        Thread.currentThread().
                                getStackTrace(),
                        ЛОГ,"ErrorsLogs/ErrorJbossServletDSU1.txt");
    }
        return queryprocedure;
    }

    // TODO: 09.03.2023  метод очистки Hirenate после операции
    private void МетодЗавершенияСеанса() {
        try{
            if (session!=null) {
                if (sessionTransaction.getStatus()== TransactionStatus.ACTIVE) {
                    sessionTransaction.commit();
                }

                if (session.isOpen() || session.isConnected() ) {
                    session.close();
                }
                ЛОГ.log("\n МетодЗакрываемСессиюHibernate "+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                        " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                        " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n" +  "session " +session);
            }
    } catch (Exception   e) {
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


    // TODO: 14.03.2023  Метод Самого Выполениея Операции POST EXE
    private Integer МетодСамогоВыполенияУдаленнойПроцедуры(@NotNull  StoredProcedureQuery queryprocedure,
                                                           @NotNull  ServletContext ЛОГ ,
                                                           @NotNull String ПараметрИмяТаблицыОтАндройдаPost) {
        Integer КоличестоУспешныхОперацийНаСервере=0;
        try {
            ЛОГ.log(" queryprocedure " +queryprocedure + "  ПараметрИмяТаблицыОтАндройдаPost " +ПараметрИмяТаблицыОтАндройдаPost);
            ////todo выполения .EXE
            queryprocedure.execute();
            // TODO: 14.03.2023  Подсчитиваем КОличество спрешных Операций 
        КоличестоУспешныхОперацийНаСервере=   queryprocedure.getUpdateCount();
            ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " queryprocedure "+queryprocedure  + " КоличестоУспешныхОперацийНаСервере " +КоличестоУспешныхОперацийНаСервере);
        } catch (Exception   e) {
            ЛОГ.log( "ERROR class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()  + " e " +e.getMessage() );
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletDSU1.txt");
        }
        return  КоличестоУспешныхОперацийНаСервере;
        //TODO
    }


}

