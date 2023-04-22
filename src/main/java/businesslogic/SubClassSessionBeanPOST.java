package businesslogic;



import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.zip.GZIPInputStream;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Produces;

@RequestScoped
@Produces
public class SubClassSessionBeanPOST {//extends    DSU1JsonServlet

    @Inject
    SubClassВставкаДанныхОтКлиентаPOST subClassВставкаДанныхОтКлиентаPOST;

    @SuppressWarnings("unused")
    protected ServletContext ЛОГ;
    //private	Connection conn; ////// общий коннект для всего севлтера
    @SuppressWarnings("unused")
    private String ПубличноеHeaderИмя = null;
    private Statement stmt;
    @SuppressWarnings("unused")
    private String ОшибкаВМетодеdoPOST = new String();
    @SuppressWarnings("unused")
    private int КоличествоСтрокКоторыеМыОтправимНаКлиент;
    @SuppressWarnings("unused")
    private Long РезультатОтАндройдаЕгоЛокальнаяВерсияЧата = 0l;
    @SuppressWarnings("unused")
    private HttpServletRequest request;
    @SuppressWarnings("unused")
    private HttpServletResponse response;
    @SuppressWarnings("unused")

    @Inject
    SubClassWriterErros subClassWriterErros;

    @Inject
    ObjectMapper getGeneratorJackson;

    public SubClassSessionBeanPOST() throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {
        System.out.println("Конструктор  SubClassМетодаBeanSessionPOST");
    }

    /**
     * @param request
     * @param response
     * @throws SecurityException
     */
    @SuppressWarnings("unused")
    protected StringBuffer МетодЗапускаPOST(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull ServletContext ЛОГ) throws SecurityException {

        StringBuffer БуферГлавныйГенерацииJSONДляAndroid = null;
        try {
            ЛОГ.log("Конструктор  ЗАПУСК МЕТОДА ИЗ POST ()  ГлавныйМетод_МетодаPOST()");
            this.ЛОГ = ЛОГ;
            this.request = request;
            this.response = response;
            ЛОГ.log("ЛОГ  POST() " + ЛОГ + " request " + request + " response "
                    + response);
            ЛОГ.log(" ОТРАБОТАЛ МЕТОД ИНИЦИАЛИЗАЦИИ ПЕРЕМЕННЫХ КОТОРЫ Е ПРИШЛИ  МетодПредворительногоПодключенияДляМетодаGETкодИзКонструктора   " +
                    stmt);
            // TODO ПРИШЛИ ПАРАМЕТРЫ В МЕТОДЕ POST
         String   NameTable = Optional.ofNullable(request.getParameter("NameTable")).map(String::trim).orElse("");
            ЛОГ.log("  ПараметрИмяТаблицыОтАндройдаPost " + NameTable);
            ///TODO ПАРАМЕНТ #2
            String    JobForServer = Optional.ofNullable(request.getParameter("JobForServer")).map(String::trim).orElse("");
            //TODO post paramentes
            ЛОГ.log("  ПараметрФильтрПолучаемыхТаблицДляАндройда  " + JobForServer);
            ///TODO ПАРАМЕНТ #5
            switch (JobForServer.trim()) {
                case "Получение JSON файла от Андройда":
                    StringBuffer БуферJSONОтАндройда = МетодПолучениеJSONОтКлиента(request);
                    ЛОГ.log("  БуферJSONОтАндройда " + БуферJSONОтАндройда.toString());///// ПРИШЕДШИХ
                    if (БуферJSONОтАндройда.toString().toCharArray().length > 3) {///// ЗАХОДИМ											///// КОД
                        ЛОГ.log("  БуферJSONОтАндройда " + БуферJSONОтАндройда.toString());///// ПРИШЕДШИХ
                        БуферГлавныйГенерацииJSONДляAndroid = МетодПарсингаJSONФайлПришелОтКлиента(response, NameTable, БуферJSONОтАндройда);
                        ЛОГ.log( " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                + " БуферГлавныйГенерацииJSONДляAndroid "+БуферГлавныйГенерацииJSONДляAndroid.toString());
                    }
                    break;
            }
            ЛОГ.log("БуферГлавныйГенерацииJSONДляAndroid  " + БуферГлавныйГенерацииJSONДляAndroid);
        } catch (Exception e) {
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletDSU1.txt");
        }
        return БуферГлавныйГенерацииJSONДляAndroid;//TODO return  new AsyncResult<StringBuffer>( БуферГлавныйГенерацииJSONДляAndroid);

    }

    protected StringBuffer МетодПарсингаJSONФайлПришелОтКлиента(
            @NotNull HttpServletResponse response,
            @NotNull String ТаблицаPOST,
            @NotNull StringBuffer БуферJSONотAndroid)
            throws InterruptedException, SQLException, BrokenBarrierException, IOException {
        StringBuffer БуферОтветаПослеОперацииДаннымиОтАндройда = new StringBuffer();
        try {
            // convert JSON string to Map
         CopyOnWriteArrayList<Map<String, String>> БуферJSONJackson = getGeneratorJackson.readValue(БуферJSONотAndroid.toString(),
                 new TypeReference<CopyOnWriteArrayList<Map<String, String>>>() {});

            CopyOnWriteArrayList<model.DataTabel> БуферJSONJackson1 = getGeneratorJackson.readValue(БуферJSONотAndroid.toString(),
                    new TypeReference<CopyOnWriteArrayList<model.DataTabel>>() {});

            //TODO ГЛАВНЫЙ МЕТОДА POST() КОТОРЫЙ ВСТАВЛЯЕТ  И/ИЛИ ОБНОВЛЕНИЯ ДАННЫХ
           БуферОтветаПослеОперацииДаннымиОтАндройда = subClassВставкаДанныхОтКлиентаPOST.методCompleteInsertorUpdateData(ЛОГ, БуферJSONJackson
                            , ТаблицаPOST,БуферJSONJackson1);
            ЛОГ.log( " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                    " БуферJSONJackson " + БуферJSONJackson.size()+ " БуферJSONJackson1 " +БуферJSONJackson1);
        } catch (Exception e) {
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletDSU1.txt");
        }
        return БуферОтветаПослеОперацииДаннымиОтАндройда;
    }

    protected StringBuffer МетодПолучениеJSONОтКлиента(@NotNull HttpServletRequest request)
            throws IOException, InterruptedException, ExecutionException {
        //TODO ПОЛУЧАЕМ ДАННЫЕ ОТ КЛИЕНТА
        StringBuffer buffer = new StringBuffer();
        try (ServletInputStream ОткрываемПотокДляПолученогоJSONотАндройда = request.getInputStream();) {
            ЛОГ.log("Выполяеться метод  МетодПолучениеJSONОтКлиента пришел JSON-поток от клитента на Сервера ");
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(new GZIPInputStream(ОткрываемПотокДляПолученогоJSONотАндройда), StandardCharsets.UTF_16));//// ПРИШЕЛ
            buffer = bufferedReader.lines().parallel().collect(StringBuffer::new, (sb, i) -> sb.append(i), StringBuffer::append);


       JsonNode jsonNodeParent= getGeneratorJackson.readTree(buffer.toString());

            jsonNodeParent.fields().forEachRemaining(new Consumer<Map.Entry<String, JsonNode>>() {
             @Override
             public void accept(Map.Entry<String, JsonNode> stringJsonNodeEntryOne) {
                 JsonNode jsonNodeChild = jsonNodeParent.get(stringJsonNodeEntryOne.getKey());
                 ЛОГ.log("stringJsonNodeEntryOne.getKey() "  + stringJsonNodeEntryOne.getKey() + " IstringJsonNodeEntryOne.getValue() " +stringJsonNodeEntryOne.getValue() );
                 ЛОГ.log("stringJsonNodeEntryOne.getKey() "  + stringJsonNodeEntryOne.getKey() + " IstringJsonNodeEntryOne.getValue() " +stringJsonNodeEntryOne.getValue() );
                 jsonNodeChild.fields().forEachRemaining(new Consumer<Map.Entry<String, JsonNode>>() {
                     @Override
                     public void accept(Map.Entry<String, JsonNode> stringJsonNodeEntryTwo) {
                         ЛОГ.log("stringJsonNodeEntryTwo.getKey() "  + stringJsonNodeEntryTwo.getKey() + " stringJsonNodeEntryTwo.getValue() " +stringJsonNodeEntryTwo.getValue() );
                         ЛОГ.log("stringJsonNodeEntryTwo.getKey() "  + stringJsonNodeEntryTwo.getKey() + " stringJsonNodeEntryTwo.getValue() " +stringJsonNodeEntryTwo.getValue() );
                     }
                 });

             }
         });

            ЛОГ.log("Выполяеться метод  МетодПолучениеJSONОтКлиента пришел JSON-поток от клитента на Сервера  + buffer.toString())"
                    + "" + buffer.toString() + " jsonNodeParent " + jsonNodeParent);

         //   bufferedReader.close();
        } catch (Exception e) {
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletDSU1.txt");
        }
        return buffer;
    }
}