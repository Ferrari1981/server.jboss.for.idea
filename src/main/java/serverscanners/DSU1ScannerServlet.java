package serverscanners;


import businesslogic.Filters.SubClassAllFilers;
import businesslogic.SubClassWriterErros;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet( value="/sous.jboss.scanner",asyncSupported = true)
public class DSU1ScannerServlet extends HttpServlet {
    private      ServletContext    ЛОГ;
    /*SessionFactory sessionSousJboss;*/
    @Inject
    SubClassWriterErros subClassWriterErros;
    DSU1ScannerServlet(){
        System.out.println(" class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // super.doGet(req, resp);
        ЛОГ = getServletContext();
        if(req.isAsyncSupported() && req.isAsyncStarted()) {
            // TODO: 22.05.2023 lister asynccontext
            new SubClassAllFilers().методСлушатель(     req.getAsyncContext(),ЛОГ);
            // TODO: 22.05.2023  start working...
            req.getAsyncContext().start(()->{
                try{
                    //TODO ЗАПУСКАЕМ КОДЕ МЕТОДА GET()
                    // СессионыйБинGET.МетодБинаGET(ЛОГ, req, resp,sessionSousJbossRuntime);


                    ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                            + " ((HttpServletRequest) req).getPathInfo() " + ((HttpServletRequest) req).getPathInfo()+
                            "  POOL  THREAD "+Thread.currentThread().getName());
        } catch (Exception e) {
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletScanner.txt");

        }
            });
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        ЛОГ = getServletContext();
        if(req.isAsyncSupported() && req.isAsyncStarted()) {
            // TODO: 22.05.2023 lister asynccontext
            new SubClassAllFilers().методСлушатель(     req.getAsyncContext(),ЛОГ);
            // TODO: 22.05.2023  start working...
            req.getAsyncContext().start(()->{
                try{
                //TODO ЗАПУСКАЕМ КОДЕ МЕТОДА POST()
                //СессионыйБинPOST.МетодБинаPOST(ЛОГ,req,resp,sessionSousJbossRuntime);
                ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()
                        + "\n" + ((HttpServletRequest) req).getPathInfo());
        } catch (Exception e) {
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletScanner.txt");

        }
            });
        }
    }

    public void destroy() {
        try{
          /*  if (sessionSousJboss!=null && sessionSousJboss.isOpen()) {
                sessionSousJboss.close();
            }*/
            ЛОГ.log("\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber());
        } catch (Exception e) {
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletScanner.txt");

        }

    }
}

































