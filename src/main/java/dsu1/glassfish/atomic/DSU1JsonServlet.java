package dsu1.glassfish.atomic;


import businesslogic.BeanGET;
import businesslogic.BeanPOST;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Entity;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.validation.constraints.NotNull;

@WebServlet(name = "DSU1JsonServlet", value = "/dsu1.glassfish.atomic",asyncSupported = true)
public class DSU1JsonServlet extends HttpServlet {
    private     Session getSession;
    private      ServletContext    ЛОГ;
    @EJB
    private BeanGET СессионыйБинGET;
    @EJB
    private BeanPOST СессионыйБинPOST;
    @Inject
    private MyGetHibernate myHibernate;

    @Inject
    SessionFactory sessionSousJboss;

    public void init() {
        try{
        ЛОГ=this.getServletContext();
        // TODO: 10.03.2023 делаем лог
        ЛОГ.log("\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n");
            // TODO: 10.03.2023 получение сессиии
         Session sesion=   sessionSousJboss.openSession();
        ЛОГ.log("\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+ " sesion " +sesion);
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
        super.doGet(req, resp);
        try{
            req.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
             resp.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
           ЛОГ = getServletContext();

            switch (((HttpServletRequest) req).getPathInfo()) {
                case "/dsu1.glassfish.atomic":
                case "/dsu1.glassfish.atomic/DSU1JsonServlet":
                    //TODO ЗАПУСКАЕМ КОДЕ МЕТОДА GET()
                    СессионыйБинGET.МетодБинаGET(ЛОГ, req, resp);
                    ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " ((HttpServletRequest) req).getPathInfo() " +((HttpServletRequest) req).getPathInfo());
                    break;
            }

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
        super.doPost(req, resp);
        try{
           ЛОГ = getServletContext();
            req.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
            resp.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
            //TODO ЗАПУСКАЕМ КОДЕ МЕТОДА POST()
         	СессионыйБинPOST.МетодБинаPOST(ЛОГ,req,resp);
            ЛОГ.log("\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n");
    } catch (Exception e) {
        new SubClassWriterErros().МетодаЗаписиОшибкиВЛог(e, null,
                "\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                        " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                        " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n",
                Thread.currentThread().getStackTrace()[2],ЛОГ,ЛОГ.getServerInfo().toLowerCase());

    }
    }

    public void destroy() {
    }
}

































