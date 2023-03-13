package com.downloadpo;


import com.sun.istack.NotNull;
import dsu1.glassfish.atomic.SubClassWriterErros;
import org.hibernate.SessionFactory;

import javax.ejb.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.zip.GZIPOutputStream;

/**
 * Session Bean implementation class SesionBeanDownloadPO
 */
@Stateless(mappedName = "SesionBeanDownloadPO")
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class SesionBeanDownloadPO {
    public SesionBeanDownloadPO() {
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
            // TODO: 10.03.2023  данные от GET метода
            Future<File> ПолучаемJSONФайл= 	 МетодДляJSONФайла(ЛОГ,request,response);

            МетодBackДанныеКлиентуНовоеПО(response ,ПолучаемJSONФайл.get(),ЛОГ);

            ЛОГ.log("\n"+" Starting.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+  "  ПолучаемJSONФайл " +ПолучаемJSONФайл);
        } catch (Exception e) {
            new SubClassWriterErros().МетодаЗаписиОшибкиВЛог(e, null,
                    "\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                            " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                            " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n",
                    Thread.currentThread().getStackTrace()[2],ЛОГ,ЛОГ.getServerInfo().toLowerCase());
        }
    }

    public void  МетодЗапускаДляФайлаAPK(@NotNull ServletContext ЛОГ,
                                          @NotNull HttpServletRequest request,
                                          @NotNull HttpServletResponse response) throws InterruptedException, ExecutionException {
        try {
            // TODO: 10.03.2023  данные от GET метода
            Future<File> ПолучаемAPKФайл= 	 МетодДляAPKФайла(ЛОГ,request,response);

            МетодBackДанныеКлиентуНовоеПО(response ,ПолучаемAPKФайл.get(),ЛОГ);

            ЛОГ.log("\n"+" Starting.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+  "  ПолучаемAPKФайл " +ПолучаемAPKФайл);
        } catch (Exception e) {
            new SubClassWriterErros().МетодаЗаписиОшибкиВЛог(e, null,
                    "\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                            " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                            " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n",
                    Thread.currentThread().getStackTrace()[2],ЛОГ,ЛОГ.getServerInfo().toLowerCase());
        }
    }

    @Asynchronous
    private Future<File> МетодДляJSONФайла(@NotNull ServletContext ЛОГ, @NotNull HttpServletRequest request,HttpServletResponse response){
        File ФайлJSON=null;
        try {
            // TODO: 13.03.2023 ГЛАВНЫЙ КОД РАБОТА С ФАЙЛАМИ
            ЛОГ.log("\n"+" Starting.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+  "  ФайлJSON " +ФайлJSON);
        } catch (Exception e) {
            new SubClassWriterErros().МетодаЗаписиОшибкиВЛог(e, null,
                    "\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                            " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                            " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n",
                    Thread.currentThread().getStackTrace()[2],ЛОГ,ЛОГ.getServerInfo().toLowerCase());
        }
        return new AsyncResult<File>(ФайлJSON);
    }
    @Asynchronous
    private Future<File> МетодДляAPKФайла(@NotNull ServletContext ЛОГ,
                                                   @NotNull HttpServletRequest request,HttpServletResponse response){
        File ФайлAPK=null;
        try {
            // TODO: 13.03.2023 ГЛАВНЫЙ КОД РАБОТА С ФАЙЛАМИ
            ЛОГ.log("\n"+" Starting.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+  "  ФайлAPK " +ФайлAPK);
        } catch (Exception e) {
            new SubClassWriterErros().МетодаЗаписиОшибкиВЛог(e, null,
                    "\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                            " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                            " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n",
                    Thread.currentThread().getStackTrace()[2],ЛОГ,ЛОГ.getServerInfo().toLowerCase());
        }
        return new AsyncResult<File>(ФайлAPK);
    }




































    // TODO МетодКласса отправки данных андройду
    public void МетодBackДанныеКлиентуНовоеПО(@NotNull ServletResponse response,
                                       @NotNull File ОтправкаФайлаJsonAPK,
                                       @NotNull ServletContext ЛОГ) throws IOException, ServletException {

        if (  response.isCommitted()==false) {
            try  (BufferedWriter БуферДанныеДляКлиента = new BufferedWriter(
                    new OutputStreamWriter(new GZIPOutputStream(response.getOutputStream()), StandardCharsets.UTF_16));) {
                long ОбщийРазмерЗаписываемогоФайла = ОтправкаФайлаJsonAPK.length();
                ((HttpServletResponse) response).addHeader("stream_size", String.valueOf(ОбщийРазмерЗаписываемогоФайла));
                PrintWriter МеханизмОтправкиДанныхКлиенту = new PrintWriter(БуферДанныеДляКлиента, true);
               // МеханизмОтправкиДанныхКлиенту.write(ГлавныйБуферОтправкиДанныхНААндройд.toString());
                response.flushBuffer();
                while (!response.isCommitted()) ;
                МеханизмОтправкиДанныхКлиенту.close();
                ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " ОтправкаФайлаJsonAPK " + ОтправкаФайлаJsonAPK.length() + "  response.isCommitted() "
                        + response.isCommitted() + "   ((HttpServletResponse) response).getStatus() " +
                        ((HttpServletResponse) response).getStatus());

            } catch (IOException e) {
                new SubClassWriterErros().МетодаЗаписиОшибкиВЛог(e, ЛОГ.getServerInfo(),
                        this.getClass().getMethods().toString() + " " + this.getClass().getCanonicalName().toString() + " "
                                + this.getClass().getDeclaredMethods().toString(),
                        Thread.currentThread().getStackTrace()[2], ЛОГ, ОтправкаФайлаJsonAPK.toString());
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
