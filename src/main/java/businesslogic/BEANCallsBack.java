package businesslogic;

import dsu1.glassfish.atomic.SubClassWriterErros;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
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
@ApplicationScoped
public class BEANCallsBack {

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
    public void МетодГлавныйМетодПосылаемДанныеАндройду(@NotNull HttpServletResponse response,
                                                        @NotNull StringBuffer ГлавныйБуферОтправкиДанныхНААндройд,
                                                        @NotNull ServletContext ЛОГ,
                                                        @NotNull HttpServletRequest request) throws IOException, ServletException {
        try (BufferedWriter БуферДанныеДляКлиента = new BufferedWriter(
                new OutputStreamWriter(new GZIPOutputStream(response.getOutputStream()), StandardCharsets.UTF_16));) {
            ЛОГ.log("Data SEND FOR CLIENT ANDROID ГлавныйБуферОтправкиДанныхНААндройд=============================>>>>>>>>>>>"+ГлавныйБуферОтправкиДанныхНААндройд);
            int ОбщийРазмерЗаписываемогоФайла = ГлавныйБуферОтправкиДанныхНААндройд.toString().toCharArray().length;
            response.addHeader("stream_size", String.valueOf(ОбщийРазмерЗаписываемогоФайла));
            PrintWriter МеханизмОтправкиДанныхКлиенту = new PrintWriter(БуферДанныеДляКлиента, true);
            МеханизмОтправкиДанныхКлиенту.write(ГлавныйБуферОтправкиДанныхНААндройд.toString());
            МеханизмОтправкиДанныхКлиенту.flush();
            System.out.print("\n"+" Ending... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n");
        } catch (IOException e) {
            new SubClassWriterErros().МетодаЗаписиОшибкиВЛог(e, ЛОГ.getServerInfo(),
                    this.getClass().getMethods().toString() + " " + this.getClass().getCanonicalName().toString() + " "
                            + this.getClass().getDeclaredMethods().toString(),
                    Thread.currentThread().getStackTrace()[2], ЛОГ, ГлавныйБуферОтправкиДанныхНААндройд.toString());
        }
    }
}
