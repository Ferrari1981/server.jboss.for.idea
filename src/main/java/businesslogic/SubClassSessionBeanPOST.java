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
                    ServletInputStream requestInputStream = request.getInputStream();
                    ЛОГ.log("  requestInputStream.isReady() " + requestInputStream.isReady());///// ПРИШЕДШИХ
                    if (requestInputStream.available()>0) {///// ЗАХОДИМ											///// КО
                        БуферГлавныйГенерацииJSONДляAndroid = МетодПарсингаJSONФайлПришелОтКлиента(response, NameTable, requestInputStream );
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
            @NotNull ServletInputStream requestInputStream)
            throws InterruptedException, SQLException, BrokenBarrierException, IOException {
        StringBuffer bufferCallsBackToServer = new StringBuffer();
        try {
            bufferCallsBackToServer = subClassВставкаДанныхОтКлиентаPOST.методCompleteInsertorUpdateData(ЛОГ, requestInputStream, ТаблицаPOST);  //TODO Пришли ДАнные От  Клиента

            ЛОГ.log( " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                    " БуферJSONJackson " + requestInputStream.available()+ " bufferCallsBackToServer " +bufferCallsBackToServer.toString());
        } catch (Exception e) {
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletDSU1.txt");
        }
        return bufferCallsBackToServer;
    }


}