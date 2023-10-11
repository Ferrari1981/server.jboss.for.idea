package dowsloadpojboss;


import Filters.SubClassAllFilers;
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
    @Inject
    private  SubClassWriterErros subClassWriterErros;
    private  SubClassAllFilers subClassAllFilers;
    @EJB
    private BeanCallsBackDownloadPO beanCallsBackDownloadPO;

    private  ServletContext   ЛОГ;

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
        final AsyncContext     asyncContext=req.getAsyncContext();
                        // TODO: 22.05.2023 lister asynccontext
                        subClassAllFilers.методСлушатель(    asyncContext,ЛОГ);
                    asyncContext.start(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                // TODO: 24.07.2023 запуск обновение ПО
                            beanCallsBackDownloadPO.     МетодЗапускаОбновлениеПО(ЛОГ, (HttpServletRequest) asyncContext.getRequest(),  (HttpServletResponse) asyncContext.getResponse());
                          //  beanCallsBackDownloadPO.     МетодЗапускаОбновлениеПО(ЛОГ, req,  resp);
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

































