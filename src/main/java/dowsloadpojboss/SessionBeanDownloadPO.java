package dowsloadpojboss;




import businesslogic.SubClassWriterErros;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.sun.istack.NotNull;

import javax.ejb.*;
import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.TransactionScoped;
import java.io.*;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Session Bean implementation class SessionBeanDownloadPO
 */
@Singleton(mappedName = "SessionBeanDownloadPO")
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@AccessTimeout(value = 20, unit = TimeUnit.MINUTES)
@Lock(LockType.READ)
public class SessionBeanDownloadPO {
@Inject
    SubClassWriterErros subClassWriterErros;

    public SessionBeanDownloadPO() {
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

    public void  МетодЗапускаДляФайлаJSON(@NotNull ServletContext ЛОГ,
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
            try  (ServletOutputStream БуферДанныеДляОбновлениеПО = response.getOutputStream();) {
                Long ОбщийРазмерЗаписываемогоФайла = Long.valueOf(ОтправкаФайлаJsonAPK.length());
                response.addHeader("stream_size", String.valueOf(ОбщийРазмерЗаписываемогоФайла));
                response.addHeader("stream_status", String.valueOf( (  response).getStatus()));
                response.addHeader("pool", String.valueOf( Thread.currentThread().getName()));
                InputStream fis = new FileInputStream(ОтправкаФайлаJsonAPK);
                if (fis.available()>0) {
                    БуферДанныеДляОбновлениеПО.write(fis.readAllBytes());
                    // TODO: 18.07.2023 fulsh
                    БуферДанныеДляОбновлениеПО.flush();
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
                        + " ОтправкаФайлаJsonAPK " + ОтправкаФайлаJsonAPK + "  response.isCommitted() "
                        + response.isCommitted() + "   ((HttpServletResponse) response).getStatus() " +
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
                    + "  ОтправкаФайлаJsonAPK.length() " + ОтправкаФайлаJsonAPK.length() + "  response.isCommitted() " + response.isCommitted()
                    + "   ((HttpServletResponse) response).getStatus() " +
                    ((HttpServletResponse) response).getStatus());
        }
    }

}

