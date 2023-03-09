package dsu1.glassfish.atomic;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.*;
import java.util.Date;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/dsu1.glassfish.atomic",asyncSupported = true)
public class DSU1JsonServlet extends HttpServlet {
    private String message;
    @Inject
    private  MyGetHibernate myHibernate;
    private     Session getSession;

    public void init() {
        message = "Hello World!";
         getSession=myHibernate.session(this.getServletContext()) ;//new myHibernate().session();///
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
            // response.setContentType("text/html");
        ServletContext    ЛОГ = getServletContext();
            PrintWriter out = resp.getWriter();
            out.println("<html><body>");
            out.println("<h1>" + message+" getSession " +getSession.getSession()+" время " +new Date().toLocaleString() + "</h1>");
            out.println("</body></html>");


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        ServletContext       ЛОГ = getServletContext();
    }

    public void destroy() {
    }
}

































