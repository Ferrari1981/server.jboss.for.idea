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
import java.util.Date;

// TODO: 09.03.2023  Класс Получение Менеджера для Hibertire
@Named
@RequestScoped
public
class MyGetHibernate{
    public SessionFactory session(ServletContext ЛОГ){
        SessionFactory   session = null;
        try  {
            Configuration configuration=new Configuration();
                configuration.configure();
               SessionFactory sessionFactory = configuration.buildSessionFactory();
            ЛОГ.log("\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+" sessionFactory " +sessionFactory.isOpen()+ " время" +new Date().toLocaleString());
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