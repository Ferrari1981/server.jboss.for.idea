package dsu1glassfishatomic;


import businesslogic.BeanGET;
import businesslogic.BeanPOST;
import businesslogic.SubClassWriterErros;
import dsu1glassfishatomic.workinterfaces.ProducedCard;
import org.hibernate.SessionFactory;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.servlet.AsyncContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet( value="/sous.jboss.tabel",asyncSupported = true)
public class DSU1JsonServlet extends HttpServlet {
    private      ServletContext    ЛОГ;
    @EJB
    private BeanGET СессионыйБинGET;
    @EJB
    private BeanPOST СессионыйБинPOST;
    @Inject  @ProducedCard
    SessionFactory sessionSousJboss;
    @Inject
    SubClassWriterErros subClassWriterErros;

    DSU1JsonServlet(){
        System.out.println(" class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     // super.doGet(req, resp);
        ЛОГ = getServletContext();
          req.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
          resp.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
        if(req.isAsyncStarted()){
        req.getAsyncContext().start(()->{
                try{
                //TODO ЗАПУСКАЕМ КОДЕ МЕТОДА GET()
                СессионыйБинGET.МетодБинаGET(ЛОГ, req, resp);
                ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " ((HttpServletRequest) req).getPathInfo() " +((HttpServletRequest) req).getPathInfo()+
                        " POOL CURRENT  "+Thread.currentThread().getName()+ " req.isAsyncStarted() " +req.isAsyncStarted());

                    // TODO: 23.04.2023 clears Async
                        req.getAsyncContext().dispatch();
            } catch (Exception e) {
                ЛОГ.log( "ERROR class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() );
                subClassWriterErros.
                        МетодаЗаписиОшибкиВЛог(e,
                                Thread.currentThread().
                                        getStackTrace(),
                                ЛОГ,"ErrorsLogs/ErrorJbossServletDSU1.txt");
            }
        });
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
           ЛОГ = getServletContext();
            req.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
            resp.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
        if(req.isAsyncStarted()){
        req.getAsyncContext().start(()-> {
            try {
                //TODO ЗАПУСКАЕМ КОДЕ МЕТОДА POST()
                СессионыйБинPOST.МетодБинаPOST(ЛОГ, req, resp);
                ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + ((HttpServletRequest) req).getPathInfo() +
                        " POOL CURRENT  " + Thread.currentThread().getName()+ " req.isAsyncStarted() " +req.isAsyncStarted());

                // TODO: 23.04.2023 clears Async
                    req.getAsyncContext().dispatch();
            } catch (Exception e) {
                ЛОГ.log("ERROR class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
                subClassWriterErros.
                        МетодаЗаписиОшибкиВЛог(e,
                                Thread.currentThread().
                                        getStackTrace(),
                                ЛОГ, "ErrorsLogs/ErrorJbossServletDSU1.txt");

            }
        });
        }
        }

    @Override
    public void init() throws ServletException {
        super.init();
        try{
            ЛОГ = getServletContext();
            ЛОГ.log("\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+"   sessionSousJboss.isOpen() " +  sessionSousJboss.isOpen());
    } catch (Exception e) {
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletDSU1.txt");


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
                          ЛОГ,"ErrorsLogs/ErrorJbossServletDSU1.txt");


      }

    }

}

































