package dowsloadpojboss;


import businesslogic.Filters.SubClassAllFilers;
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

@WebServlet( value="/sous.jboss.download",asyncSupported = true)
public class DSU1DonwloadsServlet extends HttpServlet {
    private      ServletContext    ЛОГ;
    @EJB
    private BeanCallsBackDownloadPO beanCallsBackDownloadPO;
    @Inject
    private  SubClassWriterErros subClassWriterErros;

    private AsyncContext asyncContext;
    private  SubClassAllFilers subClassAllFilers;

    DSU1DonwloadsServlet(){
        System.out.println(" class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
        subClassAllFilers=  new SubClassAllFilers();

    }




    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     // super.doGet(req, resp);
                    ЛОГ = getServletContext();
                    if (asyncContext==null) {
                        asyncContext=req.getAsyncContext();
                        // TODO: 22.05.2023 lister asynccontext
                        subClassAllFilers.методСлушатель(    asyncContext,ЛОГ);
                    }
                    asyncContext.start(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Object ЗаданиеДляСервераЗагрузкиНовогоПо = req.getHeaders("task_downlonupdatepo").nextElement();
                                ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                                        "  ЛогинОтAndroid    ЗаданиеДляСервераЗагрузкиНовогоПо " + ЗаданиеДляСервераЗагрузкиНовогоПо
                                        + " req.isAsyncStarted() " + req.isAsyncStarted()+"  POOL  THREAD "+Thread.currentThread().getName());
                                switch (ЗаданиеДляСервераЗагрузкиНовогоПо.toString()) {
                                    case "FileJsonUpdatePO":
                                        // TODO: 13.03.2023  запуск Кода пополучениею File JSON Для Обнолвенеи ПО
                                        resp.setContentType("application/json");
                                        beanCallsBackDownloadPO.МетодЗапускаДляФайлаJSON(ЛОГ, req, resp);
                                        break;
                                    case "FileAPKUpdatePO":
                                        // TODO: 13.03.2023  запуск Кода пополучениею File .APK Для Обнолвенеи ПО
                                        resp.setContentType("application/octet-stream");
                                        beanCallsBackDownloadPO.МетодЗапускаДляФайлаAPK(ЛОГ, req, resp);
                                        break;
                                }
                                ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                        + " ((HttpServletRequest) req).getPathInfo() " + ((HttpServletRequest) req).getPathInfo());
                            } catch (Exception e) {
                                subClassWriterErros.
                                        МетодаЗаписиОшибкиВЛог(e,
                                                Thread.currentThread().
                                                        getStackTrace(),
                                                ЛОГ, "ErrorsLogs/ErrorJbossServletUpdatePO.txt");

                            }
                        }
                    });

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
                          ЛОГ,"ErrorsLogs/ErrorJbossServletUpdatePO.txt");

    }

    }
}

































