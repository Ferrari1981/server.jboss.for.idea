package runtimes;


import Filters.ClassListrerForAsyncProccer;
import businesslogic.SubClassWriterErros;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.AsyncContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet( value="/sous.jboss.runtimejboss",asyncSupported = true)
public class DSU1ServletRuntimeJboss extends HttpServlet {
    @EJB
    private SessionBeanGETRuntimeJboss sessionBeanGETRuntimeJboss;
    @Inject
    private SubClassWriterErros subClassWriterErros;

    @Inject
    private ClassListrerForAsyncProccer classListrerForAsyncProccer;

    private  ServletContext   ЛОГ;

    private AsyncContext asyncContext;


    DSU1ServletRuntimeJboss(){
        System.out.println(" class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
        classListrerForAsyncProccer =  new ClassListrerForAsyncProccer();//
    }





    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     // super.doGet(req, resp);
           ЛОГ = getServletContext();
         asyncContext=req.getAsyncContext();
        // TODO: 22.05.2023 lister asynccontext
        classListrerForAsyncProccer.методСлушатель(    asyncContext,ЛОГ);
        asyncContext.start(new Runnable() {
            @Override
            public void run() {
                try {
                    //TODO ЗАПУСКАЕМ КОДЕ МЕТОДА GET()
                    sessionBeanGETRuntimeJboss.МетодГлавныйRuntimeJboss(ЛОГ,  (HttpServletRequest) asyncContext.getRequest(),  (HttpServletResponse) asyncContext.getResponse());
                    ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                            + " ((HttpServletRequest) req).getPathInfo() " + ((HttpServletRequest) req).getPathInfo()
                            + "  POOL  THREAD " + Thread.currentThread().getName());
                    // TODO: 23.04.2023 clears Async
                } catch (Exception e) {
                    subClassWriterErros.
                            МетодаЗаписиОшибкиВЛог(e,
                                    Thread.currentThread().
                                            getStackTrace(),
                                    ЛОГ, "ErrorsLogs/ErrorJbossServletRuntime.txt");
                }

            }
            });

                // TODO: 23.04.2023 exit
    }


    public void destroy() {
      try{
          System.out.println(" class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                  " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                  " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
    } catch (Exception e) {
          subClassWriterErros.
                  МетодаЗаписиОшибкиВЛог(e,
                          Thread.currentThread().
                                  getStackTrace(),
                          ЛОГ,"ErrorsLogs/ErrorJbossServletRuntime.txt");

    }

    }
}

































