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
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Entity;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "DSU1JsonServlet", value = "/dsu1.glassfish.atomic",asyncSupported = true )//,urlPatterns ={"/DSU1JsonServlet"}
public class DSU1JsonServlet extends HttpServlet {
    private     Session getSession;
    private      ServletContext    ЛОГ;
    @EJB
    private BeanGET СессионыйБинGET;
    @EJB
    private BeanPOST СессионыйБинPOST;

    public void init() {
        ЛОГ=this.getServletContext();
        ЛОГ.log("\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n");
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        try{
            req.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
             resp.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
           ЛОГ = getServletContext();
            //TODO ЗАПУСКАЕМ КОДЕ МЕТОДА GET()
            СессионыйБинGET.МетодБинаGET(ЛОГ,req,resp);
            ЛОГ.log("\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n");

/*
            PrintWriter out = resp.getWriter();
            out.println("<html><body>");
            out.println("<h1>" + message+" getSession " +getSession.getSession()+" время " +new Date().toLocaleString() + "</h1>");
            out.println("</body></html>");*/

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

































