package dsu1.glassfish.atomic;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/dsu1.glassfish.atomic")
public class HelloServlet extends HttpServlet {
    private String message;
    @Inject
    private  MyHibernate myHibernate;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
       Session getSession=myHibernate.session() ;//new myHibernate().session();///
        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message+" getSession " +getSession.getSession()+" время " +new Date().toLocaleString() + "</h1>");
        out.println("</body></html>");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    public void destroy() {
    }
}

































// TODO: 09.03.2023  Класс Получение Менеджера для Hibertire
@Named
@ApplicationScoped
 class MyHibernate{
    Session  session(){
        Session   session = null;
        try  {
            Configuration configuration=new Configuration();
            configuration.configure();
            try ( SessionFactory sessionFactory = configuration.buildSessionFactory()) {
                  session =sessionFactory.openSession();;
                System.out.println(" session " +session);
            } catch (HibernateException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(" session " +e.getMessage());
        }
        return  session;
    }
}