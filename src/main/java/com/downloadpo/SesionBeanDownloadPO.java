package com.downloadpo;


import com.sun.istack.NotNull;
import dsu1.glassfish.atomic.SubClassWriterErros;
import org.hibernate.SessionFactory;

import javax.ejb.*;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

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
}
