package jboss.scannerserver;


import businesslogic.BeanGET;
import businesslogic.BeanPOST;
import dsu1.glassfish.atomic.SubClassWriterErros;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet( value="/dsu1.glassfish.scanner",asyncSupported = true)
public class DSU1ScannerServlet extends HttpServlet {
    private     Session getSession;
    private      ServletContext    ЛОГ;
    @EJB
    private BeanGET СессионыйБинGET;
    @EJB
    private BeanPOST СессионыйБинPOST;
    @Inject
    SessionFactory sessionSousJboss;

    SessionFactory sessionSousJbossRuntimeScanner;
    public void init() {
        try{
        ЛОГ=this.getServletContext();
        // TODO: 10.03.2023 делаем лог
            sessionSousJbossRuntimeScanner =sessionSousJboss;
        ЛОГ.log("\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n" + " sessionSousJbossRuntime " + sessionSousJbossRuntimeScanner);
    } catch (Exception e) {
        new SubClassWriterErros().МетодаЗаписиОшибкиВЛог(e, null,
                "\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                        " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                        " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n",
                Thread.currentThread().getStackTrace()[2],ЛОГ,ЛОГ.getServerInfo().toLowerCase());

    }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     // super.doGet(req, resp);
        try{
          req.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
          resp.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
           ЛОГ = getServletContext();
                    //TODO ЗАПУСКАЕМ КОДЕ МЕТОДА GET()
                   // СессионыйБинGET.МетодБинаGET(ЛОГ, req, resp,sessionSousJbossRuntime);
                    ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                            + " ((HttpServletRequest) req).getPathInfo() " +((HttpServletRequest) req).getPathInfo()+
                             " sessionSousJbossRuntime " + sessionSousJbossRuntimeScanner);
        } catch (Exception e) {
            new SubClassWriterErros().МетодаЗаписиОшибкиВЛог(e, null,
                    "\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                            " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                            " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n",
                    Thread.currentThread().getStackTrace()[2],ЛОГ,ЛОГ.getServerInfo().toLowerCase());

        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        try{
           ЛОГ = getServletContext();
            req.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
            resp.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
            //TODO ЗАПУСКАЕМ КОДЕ МЕТОДА POST()
         	//СессионыйБинPOST.МетодБинаPOST(ЛОГ,req,resp,sessionSousJbossRuntime);
            ЛОГ.log("\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+((HttpServletRequest) req).getPathInfo()+
                    " sessionSousJbossRuntime " + sessionSousJbossRuntimeScanner);
    } catch (Exception e) {
        new SubClassWriterErros().МетодаЗаписиОшибкиВЛог(e, null,
                "\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                        " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                        " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n",
                Thread.currentThread().getStackTrace()[2],ЛОГ,ЛОГ.getServerInfo().toLowerCase());

    }
    }

    public void destroy() {
      try{
          if (sessionSousJbossRuntimeScanner !=null && sessionSousJbossRuntimeScanner.isOpen()) {
              sessionSousJbossRuntimeScanner.close();
          }
          if (sessionSousJboss!=null && sessionSousJboss.isOpen()) {
              sessionSousJboss.close();
          }
          ЛОГ.log("\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+
                " sessionSousJbossRuntime " + sessionSousJbossRuntimeScanner);
    } catch (Exception e) {
        new SubClassWriterErros().МетодаЗаписиОшибкиВЛог(e, null,
                "\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                        " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                        " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n",
                Thread.currentThread().getStackTrace()[2],ЛОГ,ЛОГ.getServerInfo().toLowerCase());

    }

    }
}

































