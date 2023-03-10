package dsu1.glassfish.atomic;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.servlet.ServletContext;
import java.net.InetAddress;

// TODO: 09.03.2023  Класс Получение Менеджера для Hibertire
@Named
@RequestScoped
public
class MyGetHibernate{
    public Session session(ServletContext ЛОГ){
        Session   session = null;
        try  {
            Configuration configuration=new Configuration();
                configuration.configure();
               SessionFactory sessionFactory = configuration.buildSessionFactory();
                session =sessionFactory.openSession();
            ЛОГ.log("\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"
                    +  "session " +session.isDirty() + " sessionFactory " +sessionFactory.isOpen());
        } catch (Exception e) {
            new SubClassWriterErros().МетодаЗаписиОшибкиВЛог(e, null,
                    "\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                            " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                            " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n",
                    Thread.currentThread().getStackTrace()[2],ЛОГ,ЛОГ.getServerInfo().toLowerCase());
        }
        return  session;
    }
}