package businesslogic;

import org.hibernate.Session;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.validation.constraints.NotNull;

public class CommitSessionHibernate {

    @Inject
    SubClassWriterErros subClassWriterErros;

    public void МетодЗакрываемСессиюHibernate(@javax.validation.constraints.NotNull ServletContext ЛОГ,
                                              @NotNull Session session) {
        try{
            if (session!=null) {
                // TODO: 26.09.2023 transaction
                if (session.getTransaction().getStatus()== TransactionStatus.ACTIVE) {
                    session.getTransaction().commit();
                }else{
                    session.getTransaction().rollback();
                }
                //todo  session clear
                if (  session.isOpen()) {
                    session.close();
                }

            }
            ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + "    session.getTransaction().getStatus() " +  session.getTransaction().getStatus());

        } catch (Exception e) {
            ЛОГ.log( "ERROR class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()  + " e " +e.getMessage() );
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletDSU1.txt");
        }
    }


    public void методЗапускТранзакции(ServletContext ЛОГ, @NotNull Session session) {
        try{
            ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + "    session.getTransaction().getStatus() " +  session.getTransaction().getStatus());

            // TODO: 17.03.2023 ЗАПУСКАЕТ ТРАНЗАКЦИЮ BEGIN
            // TODO: 17.03.2023 ЗАПУСКАЕТ ТРАНЗАКЦИЮ BEGIN
            if (session.getTransaction().getStatus()==TransactionStatus.ACTIVE) {
                session.getTransaction().rollback();
            }
            session.getTransaction().setTimeout(1800000);
            session.getTransaction().begin();
            // TODO: 22.04.2023  Логирование
            ЛОГ.log("\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+
                    " session  " +session + " session.getTransaction().getStatus() "+session.getTransaction().getStatus());
        } catch (Exception   e) {
            ЛОГ.log( "ERROR class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()  + " e " +e.getMessage() );
            subClassWriterErros.МетодаЗаписиОшибкиВЛог(e,
                    Thread.currentThread().
                            getStackTrace(),
                    ЛОГ,"ErrorsLogs/ErrorJbossServletDSU1.txt");
        }
    }






}
