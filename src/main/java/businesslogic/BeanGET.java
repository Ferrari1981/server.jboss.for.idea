package businesslogic;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.istack.NotNull;

/**
 * Session Bean implementation class BeanGET
 */
@Stateless(mappedName = "SessionBeanForGET")
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class BeanGET {
    /**
     * Default constructor.
     */
    @Inject
    SubClassSessionBeanМетодаGET subClassSessionBeanМетодаGET;
    @Inject
    BEANCallsBack bEANCallsBack;
    @Inject
    SubClassWriterErros subClassWriterErros;
    public BeanGET(	  ) {
        // TODO Auto-generated constructor stub
        try {
            System.out.print("\n"+" Starting.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.print("\n"+" Error.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+e.getMessage().toString());
        }
    }
    @SuppressWarnings("unused")
    public void  МетодБинаGET(@NotNull ServletContext ЛОГ,
                              @NotNull HttpServletRequest request,
                              @NotNull  HttpServletResponse response) throws InterruptedException, ExecutionException {
        try {
            // TODO: 10.03.2023  данные от GET метода
            Future<StringBuffer>       БуферРезультатGET= 	 АсинхронныйЗапускGET(ЛОГ,request);
           // ЛОГ.log( "  БуферРезультатGET  " + БуферРезультатGET.get());
            ///Todo отправляем  клиенту ответ от серверац
                bEANCallsBack.МетодBackДанныеКлиенту(response, БуферРезультатGET.get(), ЛОГ);
            ЛОГ.log( " Класс"+Thread.currentThread().getStackTrace()[2].getClassName()
                    +"\n"+
                    " метод "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"
                    + "Строка " + Thread.currentThread().getStackTrace()[2].getLineNumber());
        } catch (Exception e) {
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletDSU1.txt");
        }
    }




    @SuppressWarnings("unused")
    @Asynchronous
    private Future<StringBuffer> АсинхронныйЗапускGET(@NotNull ServletContext ЛОГ,
                                                       @NotNull HttpServletRequest request){
        StringBuffer БуферРезультатGET=null;
        try {
            БуферРезультатGET=		subClassSessionBeanМетодаGET.ГлавныйМетод_МетодаGET(request,  ЛОГ);
            if(БуферРезультатGET==null) {
                БуферРезультатGET=new StringBuffer();
            }
            ЛОГ.log("\n"+" Starting.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+  "  БуферРезультатGET " +БуферРезультатGET);
        } catch (Exception e) {
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletDSU1.txt");
        }
        return new AsyncResult<StringBuffer>(БуферРезультатGET);
    }


}
