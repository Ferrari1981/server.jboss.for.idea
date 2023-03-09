package com.jboss.jboss;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;
    @Inject
    private  myHibernate myHibernate;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        Map<String ,Object> MapРезульатат=myHibernate.session() ;//new myHibernate().session();///
        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message+" hashSetРезультат " +MapРезульатат.values().toString()+" время " +new Date().toLocaleString() + "</h1>");
        out.println("</body></html>");

    }

    public void destroy() {
    }
}
@Named
class myHibernate{
    Map<String ,Object>  session(){
        Map<String ,Object>  sessionproperti = new HashMap<>();
        try  {
            Configuration configuration=new Configuration();
            configuration.configure();
            try ( SessionFactory sessionFactory = configuration.buildSessionFactory()) {
                Session   session =sessionFactory.openSession();
                Map<String ,Object> getJdbcSize=    session.getProperties();
                sessionproperti.putAll(getJdbcSize);
            } catch (HibernateException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  sessionproperti;
    }
}