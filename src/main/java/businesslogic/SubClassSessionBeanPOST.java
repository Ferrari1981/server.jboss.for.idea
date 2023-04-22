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
            @NotNull StringBuffer bufferОтКлиента)
            throws InterruptedException, SQLException, BrokenBarrierException, IOException {
        StringBuffer bufferCallsBackToServer = new StringBuffer();
        try {
            bufferCallsBackToServer = subClassВставкаДанныхОтКлиентаPOST.методCompleteInsertorUpdateData(ЛОГ, bufferОтКлиента, ТаблицаPOST);  //TODO Пришли ДАнные От  Клиента

            ЛОГ.log( " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                    " БуферJSONJackson " + bufferОтКлиента.length()+ " bufferCallsBackToServer " +bufferCallsBackToServer.toString());
        } catch (Exception e) {
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletDSU1.txt");
        }
        return bufferCallsBackToServer;
    }

    // TODO: 22.04.2023 ПОЛУЧАЕМ JSON ОТ КЛИЕНТА
    protected StringBuffer МетодПолучениеJSONОтКлиента(@NotNull HttpServletRequest request)
            throws IOException, InterruptedException, ExecutionException {
        StringBuffer buffer = new StringBuffer();
        try (ServletInputStream ОткрываемПотокДляПолученогоJSONотАндройда = request.getInputStream();
             BufferedReader bufferedReader = new BufferedReader(
                     new InputStreamReader(new GZIPInputStream(ОткрываемПотокДляПолученогоJSONотАндройда), StandardCharsets.UTF_16))) {
            buffer = bufferedReader.lines().parallel().collect(StringBuffer::new, (sb, i) -> sb.append(i), StringBuffer::append);            // TODO: 22.04.2023 ПОЛУЧАЕМ ДАННЫЕ ОТ КЛИЕНТА

            ЛОГ.log("\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+
                    " buffer "+buffer.toString());
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