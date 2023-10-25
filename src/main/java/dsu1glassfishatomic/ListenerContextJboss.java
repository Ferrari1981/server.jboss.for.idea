package dsu1glassfishatomic;

import Filters.ClassListrerForAsyncProccer;
import businesslogic.SubClassWriterErros;
import com.sun.research.ws.wadl.Request;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


@WebListener
public class ListenerContextJboss implements ServletContextListener,ServletRequestListener, HttpSessionListener {
    ServletContext ЛОГ ;
    @Inject
    SubClassWriterErros subClassWriterErros;
    @Inject
    private ClassListrerForAsyncProccer classListrerForAsyncProccer;
    /**
     * Default constructor.
     */
    public ListenerContextJboss() {
        // TODO Auto-generated constructor stub
        System.out.print(" public ListenerJboss() {");
    }

    /**
     * @see ServletRequestListener#requestDestroyed(ServletRequestEvent)
     */
    public void requestDestroyed(ServletRequestEvent sre)  {
        // TODO Auto-generated method stub
        try {
            ЛОГ=sre.getServletContext();
            ЛОГ.log("\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n");
        } catch (Exception e)  {
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletDSU1.txt");
        }
    }


    /**
     * @see ServletRequestListener#requestInitialized(ServletRequestEvent)
     */
    public void requestInitialized(ServletRequestEvent sre)  {
        // TODO Auto-generated method stub
        try {
            ЛОГ = sre.getServletContext();

            ЛОГ.log("\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n");
        } catch (Exception e)  {
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletDSU1.txt");
        }
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        // TODO Auto-generated method stub
        try{
        HttpSessionListener.super.sessionCreated(se);
        System.out.println(" metod "+Thread.currentThread().getStackTrace()[2].getMethodName());
    } catch (Exception e)  {
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletDSU1.txt");
    }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        // TODO Auto-generated method stub
        try{
        HttpSessionListener.super.sessionDestroyed(se);
        System.out.println(" metod "+Thread.currentThread().getStackTrace()[2].getMethodName());
    } catch (Exception e)  {
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletDSU1.txt");
    }
    }

}
