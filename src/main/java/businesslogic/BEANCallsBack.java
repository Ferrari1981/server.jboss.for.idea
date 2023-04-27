package businesslogic;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.AsyncContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
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
    public void МетодBackДанныеКлиенту(@NotNull  HttpServletResponse response,
                                       @NotNull StringBuffer ГлавныйБуферОтправкиДанныхНААндройд,
                                       @NotNull ServletContext ЛОГ) throws IOException, ServletException {

        if (  response.isCommitted() ==false &&
                response.getStatus()==HttpServletResponse.SC_OK ) {
        try  ( BufferedWriter БуферДанныеДляКлиента = new BufferedWriter(
                new OutputStreamWriter(new GZIPOutputStream(response.getOutputStream()), StandardCharsets.UTF_16));) {
            int ОбщийРазмерЗаписываемогоФайла = ГлавныйБуферОтправкиДанныхНААндройд.toString().toCharArray().length;
             response.addHeader("stream_size", String.valueOf(ОбщийРазмерЗаписываемогоФайла));
             response.addHeader("stream_status", String.valueOf(response.getStatus()));
            PrintWriter МеханизмОтправкиДанныхКлиенту = new PrintWriter(БуферДанныеДляКлиента, true);
            МеханизмОтправкиДанныхКлиенту.write(ГлавныйБуферОтправкиДанныхНААндройд.toString());
            // TODO: 26.04.2023 fluf
            МеханизмОтправкиДанныхКлиенту.flush();
            response.flushBuffer();
            // TODO: 23.04.2023
            ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " ГлавныйБуферОтправкиДанныхНААндройд " + ГлавныйБуферОтправкиДанныхНААндройд.toString() + "  response.isCommitted() "
                    + response.isCommitted() + "   ((HttpServletResponse) response).getStatus() " +
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
                    + " ГлавныйБуферОтправкиДанныхНААндройд " + ГлавныйБуферОтправкиДанныхНААндройд.toString() + "  response.isCommitted() " + response.isCommitted()
                    + "   ((HttpServletResponse) response).getStatus() " +
                    ((HttpServletResponse) response).getStatus());
        }
    }
    // TODO МетодКласса отправки данных андройду

}
