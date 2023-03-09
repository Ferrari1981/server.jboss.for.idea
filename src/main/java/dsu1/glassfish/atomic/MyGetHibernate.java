package dsu1.glassfish.atomic;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.servlet.ServletContext;
import java.net.InetAddress;

// TODO: 09.03.2023  Класс Получение Менеджера для Hibertire
@Named
@ApplicationScoped
public
class MyGetHibernate{
    Session session( ServletContext ЛОГ){
        Session   session = null;
        try  {
            Configuration configuration=new Configuration();
            InetAddress ip = InetAddress.getLocalHost();
            String  Name = ip.getHostName();
            if(	      Name.equalsIgnoreCase("PcProgram")){
                configuration.configure();
            }else {
                configuration.configure("");
            }


            try ( SessionFactory sessionFactory = configuration.buildSessionFactory()) {
                session =sessionFactory.openSession();
                System.out.println(" session " +session);
            } catch (HibernateException e) {
                new SubClassWriterErros().МетодаЗаписиОшибкиВЛог(e, null,
                        "\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                                " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                                " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n",
                        Thread.currentThread().getStackTrace()[2],ЛОГ,ЛОГ.getServerInfo().toLowerCase());
            }
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