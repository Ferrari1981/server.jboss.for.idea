package businesslogic;



import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.ejb.*;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.TransactionScoped;

import com.sun.istack.NotNull;
import jakarta.transaction.Transactional;

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




    @Asynchronous
    public void МетодБинаPOST(@NotNull ServletContext ЛОГ,
                                      @NotNull HttpServletRequest request,
                                      @NotNull  HttpServletResponse response) throws InterruptedException, ExecutionException {;
        try {
            ///Todo  получаем данные от клиента
          byte[]  БуферРезультатPOST=		subClassSessionBeanPOST.МетодЗапускаPOST(request, response, ЛОГ);
            ///Todo получаем данные от Клиента на Сервер
                bEANCallsBack.МетодBackДанныеКлиентуByte(response, БуферРезультатPOST, ЛОГ,request  );
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

