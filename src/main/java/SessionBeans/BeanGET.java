package SessionBeans;

import businesslogic.BEANCallsBack;
import businesslogic.SubClassSessionBeanМетодаGET;
import businesslogic.SubClassWriterErros;
import com.sun.istack.NotNull;
import org.jboss.ejb3.annotation.TransactionTimeout;

import javax.ejb.*;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Session Bean implementation class BeanGET
 */
@Stateless(mappedName = "SessionBeanForGET")
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@TransactionTimeout(value = 1, unit = TimeUnit.HOURS)
@Asynchronous
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


    public void   МетодБинаGET(@NotNull ServletContext ЛОГ,
                              @NotNull HttpServletRequest request,
                              @NotNull  HttpServletResponse response) throws InterruptedException, ExecutionException {
        try {
            // TODO: 10.03.2023  данные от GET метода
            byte[]    БуферРезультатGETByte=new byte[0];

                    String      JobForServer = Optional.ofNullable(request.getParameter("JobForServer")).map(String::trim).orElse("");
      if(JobForServer.trim().equalsIgnoreCase("Хотим Получить  JSON")){
           БуферРезультатGETByte=		subClassSessionBeanМетодаGET.ГлавныйМетод_МетодаGETByte(request,  ЛОГ);

      }else {
          БуферРезультатGETByte=		subClassSessionBeanМетодаGET.ГлавныйМетод_МетодаGETService(request,  ЛОГ);
      }
            ///TODO ОТВЕТ КЛИЕНТУ ОТ СЕРВЕРА
            bEANCallsBack.МетодBackДанныеКлиентуByte(response, БуферРезультатGETByte, ЛОГ,request  );

            ЛОГ.log( " Класс"+Thread.currentThread().getStackTrace()[2].getClassName()
                    +"\n"+
                    " метод "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"
                    + "Строка " + Thread.currentThread().getStackTrace()[2].getLineNumber()  +  "БуферРезультатGETByte " +БуферРезультатGETByte+
            " JobForServer " +JobForServer);
        } catch (Exception e) {
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletDSU1.txt");
        }

}



}
