package dsu1glassfishatomic;


import SessionBeans.BeanGET;
import SessionBeans.BeanPOST;
import Filters.SubClassAllFilers;
import businesslogic.SubClassWriterErros;
import dsu1glassfishatomic.workinterfaces.ProducedCard;
import org.hibernate.SessionFactory;

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

@WebServlet( value="/sous.jboss.tabel",asyncSupported = true)
public class DSU1JsonServlet extends HttpServlet {

    @EJB
    private BeanGET СессионыйБинGET;
    @EJB
    private BeanPOST СессионыйБинPOST;
    @Inject  @ProducedCard
    private  SessionFactory sessionSousJboss;
    @Inject
    private   SubClassWriterErros subClassWriterErros;

    private  SubClassAllFilers subClassAllFilers;

    private  ServletContext   ЛОГ;


    DSU1JsonServlet(){
        // TODO: 10.10.2023 ihit
        subClassAllFilers=  new SubClassAllFilers();
        //sessionSousJboss.openSession();
        System.out.println(" class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());


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
                try{
                //TODO ЗАПУСКАЕМ КОДЕ МЕТОДА GET()
                СессионыйБинGET.МетодБинаGET(ЛОГ, (HttpServletRequest) asyncContext.getRequest(),  (HttpServletResponse) asyncContext.getResponse());
                ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " ((HttpServletRequest) req).getPathInfo() " +((HttpServletRequest) req).getPathInfo()+
                        " POOL CURRENT  "+Thread.currentThread().getName()+ " req.isAsyncStarted() " +req.isAsyncStarted()
                        +"  POOL  THREAD "+Thread.currentThread().getName());
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
                // TODO: 12.06.2023 POTOR 
            }
        });

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
           ЛОГ = getServletContext();
        final AsyncContext     asyncContext=req.getAsyncContext();
            // TODO: 22.05.2023 lister asynccontext
            subClassAllFilers.методСлушатель(    asyncContext,ЛОГ);
            //TODO ПОТОК ДЛЯ МЕТОДА POST
        asyncContext.start(new Runnable() {
            @Override
            public void run() {
                try {
                //TODO ЗАПУСКАЕМ КОДЕ МЕТОДА POST()
                СессионыйБинPOST.МетодБинаPOST(ЛОГ, (HttpServletRequest) asyncContext.getRequest(),  (HttpServletResponse) asyncContext.getResponse());
                ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + ((HttpServletRequest) req).getPathInfo() +
                        " POOL CURRENT  " + Thread.currentThread().getName()+ " req.isAsyncStarted() " +req.isAsyncStarted());
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
                          ЛОГ,"ErrorsLogs/ErrorJbossServletDSU1.txt");


      }

    }

}

































