package dowsloadpojboss;


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
import java.nio.charset.StandardCharsets;

@WebServlet( value="/sous.jboss.download",asyncSupported = true)
public class DSU1DonwloadsServlet extends HttpServlet {
    private      ServletContext    ЛОГ;
    @EJB
    SessionBeanDownloadPO sessionBeanDownloadPO;
    @Inject
    SubClassWriterErros subClassWriterErros;

    DSU1DonwloadsServlet(){
        System.out.println(" class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());

    }




    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     // super.doGet(req, resp);
          req.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
          resp.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
           ЛОГ = getServletContext();
        if(req.isAsyncStarted()) {
            req.getAsyncContext().start(() -> {
                try {
                    Object ЗаданиеДляСервераЗагрузкиНовогоПо = ((HttpServletRequest) req).getHeaders("task_downlonupdatepo").nextElement();
                    ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                            "  ЛогинОтAndroid    ЗаданиеДляСервераЗагрузкиНовогоПо " + ЗаданиеДляСервераЗагрузкиНовогоПо + " req.isAsyncStarted() " + req.isAsyncStarted());
                    switch (ЗаданиеДляСервераЗагрузкиНовогоПо.toString()) {
                        case "FileJsonUpdatePO":
                            // TODO: 13.03.2023  запуск Кода пополучениею File JSON Для Обнолвенеи ПО
                            sessionBeanDownloadPO.МетодЗапускаДляФайлаJSON(ЛОГ, req, resp);
                            break;
                        case "FileAPKUpdatePO":
                            // TODO: 13.03.2023  запуск Кода пополучениею File .APK Для Обнолвенеи ПО
                            sessionBeanDownloadPO.МетодЗапускаДляФайлаAPK(ЛОГ, req, resp);
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
                // TODO: 23.04.2023 clears Async
                req.getAsyncContext().dispatch();
            });
        }
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

































