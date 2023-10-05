package businesslogic;

import com.fasterxml.jackson.databind.util.ByteBufferBackedInputStream;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPOutputStream;


@Named
@RequestScoped
public class BEANCallsBack {
    @Inject
    SubClassWriterErros subClassWriterErros;
    /**
     * Default constructor.
     */
    public BEANCallsBack() {
        // TODO Auto-generated constructor stub
        System.out.print("\n"+" Starting.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n");
    }
    // TODO МетодКласса отправки данных андройду

    // TODO: 06.09.2023  buyte отвкет От Сервера

    // TODO МетодКласса отправки данных андройду
    public void МетодBackДанныеКлиентуByte(@NotNull  HttpServletResponse response,
                                       @NotNull byte[] ГлавныйБуферОтправкиДанныхНААндройд,
                                       @NotNull ServletContext ЛОГ,
                                       @NotNull  HttpServletRequest request) throws IOException, ServletException {

        if (  response.isCommitted() ==false &&
                response.getStatus()==HttpServletResponse.SC_OK ) {
            try  (
                    GZIPOutputStream gzipOutputStream=      new GZIPOutputStream(response.getOutputStream(),true);
                    InputStream targetStream = new ByteArrayInputStream(ГлавныйБуферОтправкиДанныхНААндройд);) {
                // TODO: 18.07.2023 send
                Long ОбщийРазмерЗаписываемогоФайла = Long.valueOf(ГлавныйБуферОтправкиДанныхНААндройд.toString().toCharArray().length);
                response.addHeader("stream_size", String.valueOf(ОбщийРазмерЗаписываемогоФайла));
                response.addHeader("stream_status", String.valueOf(response.getStatus()));
                response.addHeader("pool", String.valueOf( Thread.currentThread().getName()));
                response.addHeader("getcharsets", String.valueOf( "8"));

                    final int EOF = -1;
                    byte[] buffer = new byte[2048];
                    while (EOF != ( targetStream.read(buffer))) {
                        gzipOutputStream.write(buffer, 0,buffer.length);
                        // TODO: 21.09.2023
                        gzipOutputStream.flush();
                    }
                    gzipOutputStream.finish();
                    gzipOutputStream.close();

                // TODO: 25.09.2023 Clear LOG CONTEXT
                //TODO ЗАПЫИСЫВАМ ПУБЛИЧНЫЙ В ЛОГ
                ЛОГ.removeAttribute("IdUser" );
                ЛОГ.removeAttribute("АдуДевайсяКлиента" );
                //TODO ЗАПЫИСЫВАМ ПУБЛИЧНЫЙ В Session
                // TODO: 23.04.2023 exit asynccontext
                if(request.isAsyncStarted() && request.isAsyncSupported()){
                    request.getAsyncContext().complete();

                }
                ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " ГлавныйБуферОтправкиДанныхНААндройд " + ГлавныйБуферОтправкиДанныхНААндройд.toString()
                        + "   ((HttpServletResponse) response).getStatus() " +
                        ((HttpServletResponse) response).getStatus()+"POOL CURRENT Thread "+Thread.currentThread().getName());

            } catch (IOException e) {
                // TODO: 27.04.2023
                subClassWriterErros.
                        МетодаЗаписиОшибкиВЛог(e,
                                Thread.currentThread().
                                        getStackTrace(),
                                ЛОГ,"ErrorsLogs/ErrorJbossServletDSU1.txt");
            }
        }else {
            ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " ГлавныйБуферОтправкиДанныхНААндройд " + ГлавныйБуферОтправкиДанныхНААндройд.toString()
                    + "   ((HttpServletResponse) response).getStatus() " +
                    ((HttpServletResponse) response).getStatus());
        }
    }
    // TODO МетодКласса отправки данных андройду


    // TODO: 20.09.2023  Отправка ДАнных Клиенту СЕВРИС
    

}
