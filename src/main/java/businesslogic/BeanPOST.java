package businesslogic;



import java.util.concurrent.ExecutionException;

import javax.ejb.*;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.TransactionScoped;

import com.sun.istack.NotNull;

/**
 * Session Bean implementation class BeanPOST
 */
@Stateless(mappedName = "SessionBeanForPOST")
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class BeanPOST {
    @Inject
    SubClassSessionBeanPOST subClassSessionBeanPOST;
    @Inject
    BEANCallsBack bEANCallsBack;
    @Inject
    SubClassWriterErros subClassWriterErros;
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

    public void МетодБинаPOST(@NotNull ServletContext ЛОГ,
                               @NotNull HttpServletRequest request,
                               @NotNull  HttpServletResponse response) throws InterruptedException, ExecutionException {;
        try {
            ///Todo  получаем данные от клиента
          StringBuffer  БуферРезультатPOST=		subClassSessionBeanPOST.МетодЗапускаPOST(request, response, ЛОГ);
            ///Todo получаем данные от Клиента на Сервер
                bEANCallsBack.МетодBackДанныеКлиенту(response, БуферРезультатPOST, ЛОГ);
            ЛОГ.log("\n"+" Starting.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n");
        } catch (Exception e) {
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletDSU1.txt");
        }
    }


}

