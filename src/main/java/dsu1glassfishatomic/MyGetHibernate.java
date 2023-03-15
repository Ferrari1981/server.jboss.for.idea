package dsu1glassfishatomic;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import java.util.Date;

// TODO: 09.03.2023  Класс Получение Менеджера для Hibertire
@ApplicationScoped
public class MyGetHibernate{
    public SessionFactory sessionSousJboss(){
        SessionFactory   sessionFactoryJboss = null;
        try  {
            Configuration configuration=new Configuration();
                configuration.configure();
            sessionFactoryJboss = configuration.buildSessionFactory();
            System.out.println("\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+" sessionFactory " +sessionFactoryJboss.isOpen()
                    + " время" +new Date().toLocaleString() + "  sessionFactoryJboss sessionFactoryJboss sessionFactoryJboss " +sessionFactoryJboss );
        } catch (Exception e) {
            new SubClassWriterErros().МетодаЗаписиОшибкиВЛог(e, null,
                    "\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                            " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                            " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n",
                    Thread.currentThread().getStackTrace()[2],null,null);
        }
        return  sessionFactoryJboss;
    }
}