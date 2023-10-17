package Filters;

import businesslogic.SubClassWriterErros;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletContext;
import java.io.IOException;
import java.util.Optional;
import javax.validation.constraints.NotNull;
@Named("classListrerForAsyncProccer")
public class ClassListrerForAsyncProccer {

    @Inject
    private  SubClassWriterErros subClassWriterErros;

    private ServletContext ЛОГ;


    public void методСлушатель(@NotNull AsyncContext asy, @NotNull  ServletContext ЛОГ) {
        try {
            if (asy.getRequest().isAsyncStarted()) {
                asy.setTimeout(36000000);
                asy.addListener(new AsyncListener() {
                    @Override
                    public void onComplete(AsyncEvent asyncEvent) throws IOException {
                        Object CurrentUSers =   Optional.ofNullable(ЛОГ.getAttribute("IdUser") ).orElse("0");

                        ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                                " OTRABOTAL onComplete onComplete onComplete onComplete !!!!!!!!!!!!!!!  onComplete onComplete onComplete onComplete !!!!!!!!!!!!!!!  "+ "\n"+
                                " CurrentUSers " +CurrentUSers);
                    }

                    @Override
                    public void onTimeout(AsyncEvent asyncEvent) throws IOException {
                        Object CurrentUSers =   Optional.ofNullable(ЛОГ.getAttribute("IdUser") ).orElse("0");

                        ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                                " asyncEvent " + asyncEvent+ "\n" +
                                " OTRABOTAL onTimeout onTimeout onTimeout onTimeout !!!!!!!!!!!!!!!  onTimeout onTimeout onTimeout onTimeout !!!!!!!!!!!!!!!  "+ "\n"+
                                " CurrentUSers " +CurrentUSers);

                        asyncEvent.getAsyncContext().complete();

                    }

                    @Override
                    public void onError(AsyncEvent asyncEvent) throws IOException {
                        Object CurrentUSers =   Optional.ofNullable(ЛОГ.getAttribute("IdUser") ).orElse("0");

                        ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                                " asyncEvent " + asyncEvent+ "\n" +
                                " OTRABOTAL onError onError onError onError !!!!!!!!!!!!!!!   OTRABOTAL onError onError onError onError !!!!!!!!!!!!!!! "+ "\n"
                                +asyncEvent.getThrowable().getStackTrace()+ "\n"+
                                " CurrentUSers " +CurrentUSers);

                        asyncEvent.getAsyncContext().complete();

                    }

                    @Override
                    public void onStartAsync(AsyncEvent asyncEvent) throws IOException {

                        Object CurrentUSers =   Optional.ofNullable(ЛОГ.getAttribute("IdUser") ).orElse("0");

                        ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                                " Success    doFilter doFilter doFilter СтатусаАунтификацииПользователя " + "\n" +
                                " OTRABOTAL onStartAsync onStartAsync onStartAsync onStartAsync !!!!!!!!!!!!!!! onStartAsync onStartAsync onStartAsync onStartAsync !!!!!!!!!!!!!!!  "+ "\n" +
                                " CurrentUSers " +CurrentUSers);
                    }
                });
            }
            } catch(Exception e){
                subClassWriterErros.
                        МетодаЗаписиОшибкиВЛог(e,
                                Thread.currentThread().
                                        getStackTrace(),
                                ЛОГ, "ErrorsLogs/ErrorJbossServletDSU1.txt");
            }
        }

}
