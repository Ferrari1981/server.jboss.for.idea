package dowsloadpojboss;




import businesslogic.SubClassWriterErros;
import com.sun.istack.NotNull;

import javax.ejb.*;
import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.zip.GZIPOutputStream;

/**
 * Session Bean implementation class BeanCallsBackDownloadPO
 */
@Singleton(mappedName = "BeanCallsBackDownloadPO")
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@AccessTimeout(value = 20, unit = TimeUnit.MINUTES)
@Lock(LockType.READ)
public class BeanCallsBackDownloadPO {


   @Inject
private   SubClassWriterErros subClassWriterErros;

    @EJB
    private BeanCallsBackDownloadPO beanCallsBackDownloadPO;

    public BeanCallsBackDownloadPO() {
        // TODO Auto-generated constructor stub
        try {
            System.out.print("\n"+" Starting.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.print("\n"+" Error.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+e.getMessage().toString());
        }
    }




    @Asynchronous
    public void МетодЗапускаОбновлениеПО(@NotNull ServletContext ЛОГ,
                                         @NotNull HttpServletRequest request,
                                         @NotNull HttpServletResponse response) throws InterruptedException, ExecutionException {
        try {
            Object ЗаданиеДляСервераЗагрузкиНовогоПо = request.getHeaders("task_downlonupdatepo").nextElement();
            ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                    "  ЛогинОтAndroid    ЗаданиеДляСервераЗагрузкиНовогоПо " + ЗаданиеДляСервераЗагрузкиНовогоПо
                    + " req.isAsyncStarted() " + request.isAsyncStarted()+"  POOL  THREAD "+Thread.currentThread().getName());
            switch (ЗаданиеДляСервераЗагрузкиНовогоПо.toString()) {
                case "FileJsonUpdatePO":
                    // TODO: 13.03.2023  запуск Кода пополучениею File JSON Для Обнолвенеи ПО
                    response.setContentType("application/json");
                    beanCallsBackDownloadPO.МетодЗапускаДляФайлаJSON(ЛОГ, request, response);
                    break;
                case "FileAPKUpdatePO":
                    // TODO: 13.03.2023  запуск Кода пополучениею File .APK Для Обнолвенеи ПО
                    response.setContentType("application/octet-stream");
                    beanCallsBackDownloadPO.МетодЗапускаДляФайлаAPK(ЛОГ, request, response);
                    break;
            }
            ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " ((HttpServletRequest) req).getPathInfo() " + ((HttpServletRequest) request).getPathInfo());
        } catch (Exception e) {
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ, "ErrorsLogs/ErrorJbossServletUpdatePO.txt");

        }
    }



    public void МетодЗапускаДляФайлаJSON(@NotNull ServletContext ЛОГ,
                                                 @NotNull HttpServletRequest request,
                                                 @NotNull HttpServletResponse response) throws InterruptedException, ExecutionException {
        try {
            // TODO: 10.03.2023  данные от JSON ANALIZE
          File ПолучаемJSONФайл= 	 МетодДляJSONФайла(ЛОГ,request,response);
            МетодBackДанныеКлиентуНовоеПО(response ,ПолучаемJSONФайл,ЛОГ ,request);
            ЛОГ.log("\n"+" Starting.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+  "  ПолучаемJSONФайл " +ПолучаемJSONФайл);
        } catch (Exception e) {
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletUpdatePO.txt");
        }
    }

    public void  МетодЗапускаДляФайлаAPK(@NotNull ServletContext ЛОГ,
                                         @NotNull HttpServletRequest request,
                                         @NotNull HttpServletResponse response) throws InterruptedException, ExecutionException {
        try {
            // TODO: 10.03.2023  данные от .APK Download
           File ПолучаемAPKФайл= 	 МетодДляAPKФайла(ЛОГ,request,response);
            МетодBackДанныеКлиентуНовоеПО(response ,ПолучаемAPKФайл,ЛОГ,request );
            ЛОГ.log("\n"+" Starting.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+  "  ПолучаемAPKФайл " +ПолучаемAPKФайл);
        } catch (Exception e) {
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletUpdatePO.txt");
        }
    }


    public File МетодДляJSONФайла(@NotNull ServletContext ЛОГ, @NotNull HttpServletRequest request,  @NotNull HttpServletResponse response){
        File fileJson = null;
        try{
            //String filepath ="C:\\Users\\moraru_pi\\AndroidStudioProjectsSERVER\\sous.jboss.idea\\src\\main\\webapp\\update_android_dsu1\\output-metadata.json";
            String filepath ="update_android_dsu1/output-metadata.json";
           // String filepath ="output-metadata.json";
            // TODO: 13.03.2023 ГЛАВНЫЙ КОД РАБОТА С ФАЙЛАМИ
            Path path = Paths.get(filepath);
            fileJson = Paths.get(filepath).toFile();
            ЛОГ.log("\n"+" Starting.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+  "  fileJson " +fileJson);
        } catch (Exception e) {
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletUpdatePO.txt");
        }
        return fileJson;
    }

    private File МетодДляAPKФайла(@NotNull ServletContext ЛОГ,
                                          @NotNull HttpServletRequest request,@NotNull HttpServletResponse response){
        File fileApk = null;
        try {
           // String filepath ="C:\\Users\\moraru_pi\\AndroidStudioProjectsSERVER\\sous.jboss.idea\\src\\main\\webapp\\update_android_dsu1\\app-release.apk";
           // String filepath ="src\\main\\webapp\\update_android_dsu1\\app-release.apk";
            String filepath ="update_android_dsu1/app-release.apk";
            // TODO: 13.03.2023 ГЛАВНЫЙ КОД РАБОТА С ФАЙЛАМИ
            Path path = Paths.get(filepath);
            fileApk = Paths.get(filepath).toFile();
            ЛОГ.log("\n"+" Starting.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+  "  fileApk " +fileApk);
        } catch (Exception e) {
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletUpdatePO.txt");
        }
        return fileApk;
    }




































    // TODO МетодКласса отправки данных андройду
    public void МетодBackДанныеКлиентуНовоеПО(@NotNull HttpServletResponse response,
                                              @NotNull File ОтправкаФайлаJsonAPK,
                                              @NotNull ServletContext ЛОГ,
                                              @NotNull HttpServletRequest request) throws IOException, ServletException {

        if (  response.isCommitted()==false && ОтправкаФайлаJsonAPK.isFile() &&
                response.getStatus()==HttpServletResponse.SC_OK) {
            try  (GZIPOutputStream БуферДанныеДляОбновлениеПО =new GZIPOutputStream( response.getOutputStream(),true)) {
           // try  (ServletOutputStream БуферДанныеДляОбновлениеПО =  ( response.getOutputStream() )) {            response.addHeader("GZIPOutputStream", String.valueOf("false"));
                Long ОбщийРазмерЗаписываемогоФайла = Long.valueOf(ОтправкаФайлаJsonAPK.length());
                response.addHeader("stream_size", String.valueOf(ОбщийРазмерЗаписываемогоФайла));
                response.addHeader("stream_status", String.valueOf( (  response).getStatus()));
                response.addHeader("pool", String.valueOf( Thread.currentThread().getName()));
                response.addHeader("GZIPOutputStream", String.valueOf("true"));
                InputStream fis = new FileInputStream(ОтправкаФайлаJsonAPK);
                if (fis.available()>0) {
                    // TODO: 19.07.2023  writing
                    БуферДанныеДляОбновлениеПО.write(fis.readAllBytes());
                    // TODO: 18.07.2023 fulsh
                    БуферДанныеДляОбновлениеПО.flush();
                    // TODO: 17.09.2023 finif
                    БуферДанныеДляОбновлениеПО.finish();
                    // TODO: 17.09.2023  close
                    БуферДанныеДляОбновлениеПО.close();
                }
                // TODO: 23.04.2023 exit asynccontext
                if(request.isAsyncStarted() && request.isAsyncSupported()){
                    request.getAsyncContext().complete();
                }
                // TODO: 23.04.2023 async compilte
                ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " ОтправкаФайлаJsonAPK " + ОтправкаФайлаJsonAPK + "  response.isCommitted() "+
                        ((HttpServletResponse) response).getStatus());

            } catch (IOException e) {
                subClassWriterErros.
                        МетодаЗаписиОшибкиВЛог(e,
                                Thread.currentThread().
                                        getStackTrace(),
                                ЛОГ,"ErrorsLogs/ErrorJbossServletUpdatePO.txt");
            }
        }else {
            ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + "  ОтправкаФайлаJsonAPK.length() " + ОтправкаФайлаJsonAPK.length()
                    + "   ((HttpServletResponse) response).getStatus() " +
                    ((HttpServletResponse) response).getStatus());
        }
    }

}

