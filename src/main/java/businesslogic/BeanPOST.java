package businesslogic;



import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.istack.NotNull;
import dsu1.glassfish.atomic.SubClassWriterErros;

/**
 * Session Bean implementation class BeanPOST
 */
@Stateless(mappedName = "SessionBeanForPOST")
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class BeanPOST {
    @Inject
    SubClassSessionBeanPOST subClassSessionBeanPOST;
    @EJB
    BEANCallsBack bEANCallsBack;
    /**
     * Default constructor.
     */
    public BeanPOST() {
        // TODO Auto-generated constructor stub
        System.out.print("\n"+" Starting.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n");
    }



    @SuppressWarnings("unused")

    public void МетодБинаPOST( @NotNull javax.persistence.EntityManagerFactory ФабрикаДляМенеждера ,
                               @NotNull ServletContext ЛОГ,
                               @NotNull HttpServletRequest request,
                               @NotNull  HttpServletResponse response) throws InterruptedException, ExecutionException {
        Future<StringBuffer> БуферРезультатPOST=null;
        try {
            ЛОГ.log("\n"+" Starting.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n");
            EntityManager JPAМенеджер = ФабрикаДляМенеждера.createEntityManager();

            ///Todo  получаем данные от клиента
            БуферРезультатPOST= 	 АсинхронныйЗапускPOST(JPAМенеджер,ЛОГ,request,response);
            ЛОГ.log("  БуферРезультатGET  " +БуферРезультатPOST.get());


            ///Todo отправляем  клиенту ответ от сервера
            if (БуферРезультатPOST.isDone()) {
                bEANCallsBack.МетодГлавныйМетодПосылаемДанныеАндройду(response, БуферРезультатPOST.get(), ЛОГ, request);
                ЛОГ.log("  БуферРезультатGET  " + БуферРезультатPOST.get());
            }

        } catch (Exception e) {
            new SubClassWriterErros().МетодаЗаписиОшибкиВЛог(e, null,
                    "\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                            " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                            " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+БуферРезультатPOST.get().toString(),
                    Thread.currentThread().getStackTrace()[2],ЛОГ,ЛОГ.getServerInfo().toLowerCase());
        }
    }





    @SuppressWarnings("unused")
    @Asynchronous
    private Future<StringBuffer> АсинхронныйЗапускPOST( @NotNull   EntityManager JPAМенеджер  ,
                                                        @NotNull ServletContext ЛОГ,
                                                        @NotNull HttpServletRequest request,
                                                        @NotNull  HttpServletResponse response){
        StringBuffer БуферРезультатPOST=null;
        try {
            ЛОГ.log("\n"+" Starting.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n");
            БуферРезультатPOST=		subClassSessionBeanPOST.ГлавныйМетод_МетодаPOST(request, response, ЛОГ, JPAМенеджер);
            ЛОГ.log("\n"+" Starting.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+  "  БуферРезультатPOST " +БуферРезультатPOST);
            if(БуферРезультатPOST==null) {
                БуферРезультатPOST=new StringBuffer();
            }
        } catch (Exception e) {
            new SubClassWriterErros().МетодаЗаписиОшибкиВЛог(e, null,
                    "\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                            " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                            " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n",
                    Thread.currentThread().getStackTrace()[2],ЛОГ,ЛОГ.getServerInfo().toLowerCase());
        }
        return new AsyncResult<StringBuffer>(БуферРезультатPOST);
    }




}

