package businesslogic;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.istack.NotNull;
import dsu1glassfishatomic.workinterfaces.ProducedCard;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import javax.inject.Inject;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.servlet.ServletContext;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map.Entry;



//TODO
public class SubClassВставкаДанныхОтКлиентаPOST {
    private   ServletContext ЛОГ;
    @Inject @ProducedCard
    private  SessionFactory sessionSousJboss;
    private  Session    session;
    @Inject
    private   SubClassWriterErros subClassWriterErros;
    @Inject
    private  ObjectMapper getGeneratorJackson;
    private         Integer MaxOperations=0;
    private StoredProcedureQuery queryprocedure=null;

    // TODO: 09.03.2023
    StringBuffer методCompleteInsertorUpdateData(
            @NotNull ServletContext ЛОГ,
            @NotNull InputStream requestInputStream
            , @NotNull String ТаблицаPOST) throws SQLException {
        // TODO: 06.08.2023  вставкялем POST на сервер  
        StringBuffer bufferCallsBackToAndroid=new StringBuffer();
      
        try {
            ArrayList<Integer> arrayListMaxBackOperation=new ArrayList();
            this.ЛОГ=ЛОГ;
            session =sessionSousJboss.getCurrentSession();      // TODO: 11.03.2023  Получении Сесии Hiberrnate
            if (session.getTransaction().getStatus()== TransactionStatus.NOT_ACTIVE) {
                session.getTransaction().begin();
            }
            // TODO: 23.04.2023 ЗапускТарнзакции
            методЗапускТранзакции(ЛОГ );
            // TODO: 22.04.2023 Новый ПАРСИНГ ОТ JAKSON JSON
            JsonNode jsonNodeParent= getGeneratorJackson.readTree(requestInputStream);
            // TODO: 06.08.2023 ГЛАВНЫЙ ЦЦМИКЛ РАСПАРСИВАНИЯ
            jsonNodeParent.fields().forEachRemaining(new java.util.function.Consumer<Entry<String, JsonNode>>() {
                @Override
                public void accept(Entry<String, JsonNode> stringJsonNodeEntryOne) {
                    String Key=stringJsonNodeEntryOne.getKey().trim();
                    JsonNode jsonNodeChild = jsonNodeParent.get(Key);
                    // TODO: 22.04.2023 КАКАЯ ТАБЛИЦА
                      queryprocedure = методgetStoredProcedure(ЛОГ, ТаблицаPOST);
                    // TODO: 22.04.2023 LOCK TIMEOUT
                    queryprocedure.setHint("javax.persistence.lock.timeout",20000);//TODO Опередяем Хранимая ПРоцедура
                    ЛОГ.log("\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                            " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                            " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+" queryprocedure   " + queryprocedure);

                    // TODO: 27.04.2023  заплянем ДАННЫМИ
                    ЛОГ.log("stringJsonNodeEntryOne.getKey() "  + stringJsonNodeEntryOne.getKey() + " stringJsonNodeEntryOne.getValue() " 
                            +stringJsonNodeEntryOne.getValue()+  "Key "+Key );
                    jsonNodeChild.fields().forEachRemaining(new java.util.function.Consumer<Entry<String, JsonNode>>() {
                        @Override
                        public void accept(Entry<String, JsonNode> stringJsonNodeEntryTwo) {
                            // TODO: 22.04.2023  Парсинг Rows JSON
                            ЛОГ.log("stringJsonNodeEntryTwo.getKey() "  + stringJsonNodeEntryTwo.getKey() + " stringJsonNodeEntryTwo.getValue() "
                                    +stringJsonNodeEntryTwo.getValue() +
                                    "   queryprocedure  " +  queryprocedure + " ТаблицаPOST " +ТаблицаPOST);
                            switch (ТаблицаPOST.trim()){
                                case  "materials_databinary":
                                    queryprocedure =      методFillingBinaryValuRows(stringJsonNodeEntryTwo, queryprocedure,ТаблицаPOST);
                                    break;
                                default:
                                    queryprocedure =      методFillingValuesRows(stringJsonNodeEntryTwo, queryprocedure,ТаблицаPOST);
                                    break;

                            }
                            ЛОГ.log("stringJsonNodeEntryTwo.getKey() "  + stringJsonNodeEntryTwo.getKey() + " stringJsonNodeEntryTwo.getValue() "
                                    +stringJsonNodeEntryTwo.getValue() +
                                    "   queryprocedure[0]  " +  queryprocedure+ " ТаблицаPOST " +ТаблицаPOST);
                        }
                    });
                    // TODO: 22.04.2023  После Запоеление ПОЛНОЙ СТРОЧКИ ROW JSON
                    queryprocedure.registerStoredProcedureParameter("SrabnitUUIDOrID", String.class, ParameterMode.IN)
                            .setParameter("SrabnitUUIDOrID", Key);
                    queryprocedure.registerStoredProcedureParameter("ResultatMERGE", String.class, ParameterMode.INOUT)
                            .setParameter("ResultatMERGE", "complete merge");

                    // TODO: 22.04.2023 ВЫПОЛЕНИЕ САМОЙ ОПЕРАЦИИ MERGE
                    Integer РезультатОперацииВставкииОбновлениея=  МетодВыполениеУдаленнойПроцедуры(queryprocedure,ЛОГ);

                    
                    Integer РезультатСовершнойОперацииФинал = 0;
                    if (РезультатОперацииВставкииОбновлениея>0) {
                        String     РезультатСовершнойОперации = (String) queryprocedure.getOutputParameterValue("ResultatMERGE");
                        // TODO: 22.04.2023 clear
                        if (РезультатСовершнойОперации!=null  ){
                            // TODO: 21.07.2023 после успешно обнолвние вставки
                           Integer РезультатБезОшибок= РезультатСовершнойОперации.length();
                            if (РезультатБезОшибок< 150) {
                                РезультатСовершнойОперацииФинал=Integer.parseInt(РезультатСовершнойОперации);
                                // TODO: 20.07.2023
                                ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                        + " РезультатСовершнойОперацииФинал " + РезультатСовершнойОперацииФинал);
                            }


                        }
                        ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                + " РезультатОперацииВставкииОбновлениея " + РезультатОперацииВставкииОбновлениея 
                                + " РезультатСовершнойОперации " + РезультатСовершнойОперации);
                    }
                    // TODO: 22.04.2023 записываем новую версию после успешной вставки
                    arrayListMaxBackOperation.add(РезультатСовершнойОперацииФинал);
                    ЛОГ.log("\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                            " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                            " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n" + " arrayListMaxBackOperation" + arrayListMaxBackOperation.size());
                }
            });
            // TODO: 22.04.2023
            MaxOperations = arrayListMaxBackOperation
                    .stream()
                    .mapToInt(v -> v)
                    .max().orElse(0);
            // TODO: 22.04.2023 ОТВЕТ
            bufferCallsBackToAndroid.append(MaxOperations.toString());
            // TODO: 18.04.2023 ЗАВЕРШЕНИ СЕАНСА ПОСЛЕ ВЫПОЛЕНИЕ
            МетодЗавершенияСеанса();
            ЛОГ.log("\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+
                    " arrayListMaxBackOperation "+ arrayListMaxBackOperation);
        } catch (Exception   e) {
            if (session!=null) {
                session.getTransaction().rollback();
                session.close();
            }
            ЛОГ.log( "ERROR class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()  + " e " +e.getMessage() );
            subClassWriterErros.МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletDSU1.txt");
        }
        return bufferCallsBackToAndroid;
    }

    // TODO: 23.04.2023  запуск ТРАНЗАКЦИИ

    private void методЗапускТранзакции(ServletContext ЛОГ) {
        try{
        if (session.getTransaction().getStatus()== TransactionStatus.NOT_ACTIVE) {
            session.getTransaction().begin();   // TODO: 14.03.2023  Запускаем Транзакцию
        }
        // TODO: 22.04.2023  Логирование
        ЛОГ.log("\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+
                " session  " +session + " session.getTransaction().getStatus() "+session.getTransaction().getStatus());
    } catch (Exception   e) {
            if (session!=null) {
                session.getTransaction().rollback();
                session.close();
            }
            ЛОГ.log( "ERROR class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()  + " e " +e.getMessage() );
        subClassWriterErros.МетодаЗаписиОшибкиВЛог(e,
                Thread.currentThread().
                        getStackTrace(),
                ЛОГ,"ErrorsLogs/ErrorJbossServletDSU1.txt");
    }
    }


    // TODO: 22.04.2023  парсинг ROWs
    private StoredProcedureQuery методFillingValuesRows(@NotNull Entry<String, JsonNode> stringJsonNodeEntryTwo
            ,@NotNull  StoredProcedureQuery       queryprocedure, @NotNull String ТаблицаPOST) {
        try{
            ЛОГ.log("stringJsonNodeEntryTwo.getKey() "  + stringJsonNodeEntryTwo.getKey()
                    + " stringJsonNodeEntryTwo.getValue() " +stringJsonNodeEntryTwo.getValue()  );
            String  getKey=   stringJsonNodeEntryTwo.getKey().trim();
            String  getvalue=     stringJsonNodeEntryTwo.getValue().asText().trim();
            // TODO заполенем JSonValue ДАННЫМИ
            queryprocedure.registerStoredProcedureParameter(getKey, String.class, ParameterMode.IN)
                    .setParameter(getKey,getvalue);

            ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                    " getvalue" + getvalue + " getKey " + getKey  );
        } catch (Exception   e) {
            if (session!=null) {
                session.getTransaction().rollback();
                session.close();
            }
            ЛОГ.log( "ERROR class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()  + " e " +e.getMessage() );
            subClassWriterErros.МетодаЗаписиОшибкиВЛог(e,
                    Thread.currentThread().
                            getStackTrace(),
                    ЛОГ,"ErrorsLogs/ErrorJbossServletDSU1.txt");
        }
        return queryprocedure;
    }


    // TODO: 05.08.2023 FOR METODONLY BINARY DATA
    private StoredProcedureQuery методFillingBinaryValuRows(@NotNull Entry<String, JsonNode> stringJsonNodeEntryTwo
            ,@NotNull  StoredProcedureQuery       queryprocedure, @NotNull String ТаблицаPOST) {
        try{
            ЛОГ.log("stringJsonNodeEntryTwo.getKey() "  + stringJsonNodeEntryTwo.getKey()
                    + " stringJsonNodeEntryTwo.getValue() " +stringJsonNodeEntryTwo.getValue()  );
            String  getKey=   stringJsonNodeEntryTwo.getKey().trim();

            // TODO заполенем JSonValue ДАННЫМИ


                if (getKey.equalsIgnoreCase("image") || getKey.equalsIgnoreCase("files")) {
                       byte[]  getvalue=     stringJsonNodeEntryTwo.getValue().binaryValue();
                       /* byte[]  getvalue=     null;*/
                        queryprocedure.registerStoredProcedureParameter(getKey, byte[].class, ParameterMode.IN)
                                .setParameter(getKey,getvalue);
                        ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                                " getvalue" + getvalue + " getKey " + getKey  );
                    } else {
                        String  getvalue=     stringJsonNodeEntryTwo.getValue().asText().trim();
                        queryprocedure.registerStoredProcedureParameter(getKey, String.class, ParameterMode.IN)
                                .setParameter(getKey,getvalue);
                        ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                                " getvalue" + getvalue + " getKey " + getKey  );
                    }



            ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );
        } catch (Exception   e) {
            if (session!=null) {
                session.getTransaction().rollback();
                session.close();
            }
            ЛОГ.log( "ERROR class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()  + " e " +e.getMessage() );
            subClassWriterErros.МетодаЗаписиОшибкиВЛог(e,
                    Thread.currentThread().
                            getStackTrace(),
                    ЛОГ,"ErrorsLogs/ErrorJbossServletDSU1.txt");
        }
        return queryprocedure;
    }
    
    
    
    
    
    


    private StoredProcedureQuery методgetStoredProcedure(ServletContext ЛОГ, String ПараметрИмяТаблицыОтАндройдаPost) {
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
            case	"order_tc":
                queryprocedure = session.createStoredProcedureQuery( "ProcedureExistsMERGEorder_tc#31");//TODO ProcedureExistsMERGE_fio
                break;
            case	"errordsu1":
                queryprocedure = session.createStoredProcedureQuery( "ProcedureExistsMERGEerrordsu1#37");//TODO ProcedureExistsMERGE_fio
                break;
            case	"materials_databinary":
                queryprocedure = session.createStoredProcedureQuery( "ProcedureMaterials_databinary#38");//TODO ProcedureMaterials_databinary#38
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
                if (session.getTransaction().getStatus()== TransactionStatus.ACTIVE) {
                    session.getTransaction().commit();
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


    // TODO: 14.03.2023  Метод Самого Выполениея Операции POST EXE
    private Integer МетодВыполениеУдаленнойПроцедуры(@NotNull  StoredProcedureQuery queryprocedure,
                                                     @NotNull  ServletContext ЛОГ) {
        Integer КоличестоУспешныхОперацийНаСервере=0;
        try {
            ЛОГ.log(" queryprocedure " +queryprocedure );
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

