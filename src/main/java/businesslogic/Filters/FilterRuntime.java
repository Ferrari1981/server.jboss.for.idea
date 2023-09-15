package businesslogic.Filters;

import businesslogic.BEANCallsBack;
import businesslogic.SubClassWriterErros;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Optional;


@WebFilter(value={ "/sous.jboss.runtimejboss"},asyncSupported = true)
public class FilterRuntime implements Filter {
    @Inject
    private BEANCallsBack bEANCallsBack;
    private ServletContext ЛОГ;
    @Inject
    SubClassWriterErros subClassWriterErros;

    public void init(FilterConfig fConfig) throws ServletException {
        ЛОГ = fConfig.getServletContext();
        ЛОГ.log("\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n");
    }


    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // TODO Auto-generated method stub
        try {
            HttpServletRequest asyrequest = (HttpServletRequest) request;
            HttpServletResponse asyresponse = (HttpServletResponse) response;
            asyrequest.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
            asyresponse.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
                    // TODO: 10.03.2023  ТОЛЬКО ID DEVICE
            Object IDДевайсаКлиентаRuntime=
                    Optional.ofNullable(( asyrequest).getHeaders("id_device_androis").nextElement()).orElse("");
            ЛОГ.log("\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+
                    "  ЛогинОтAndroid    doFilter doFilter doFilter IDДевайсаКлиентаRuntime " +IDДевайсаКлиентаRuntime);
            if (IDДевайсаКлиентаRuntime.toString().length()>5) {
                    // TODO: 11.03.2023 ГЛАВНАЯ СТРОЧКА ПЕРЕНАРАВЛЕНИЕ НА СЕВРЕЛТЫ НА ГЛАВНЫЙ КОД
                    chain.doFilter(asyrequest, asyresponse);
                    ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                            " Success    doFilter doFilter doFilter IDДевайсаКлиента " + IDДевайсаКлиентаRuntime);
            }else{
                // TODO: 11.03.2023  нет не имени не пароля
                МетодФильтраНеПрошлаАунтификацию(asyrequest,asyresponse);

                // TODO: 11.03.2023  нет не имени не пароля
          /*      RequestDispatcher requestDispatcher = asyrequest.getRequestDispatcher("/indexfiltetuntime.jsp");
                requestDispatcher.forward(asyrequest, asyresponse);*/
           /*     asyresponse.sendRedirect("/indexfiltetuntime.jsp");*/

            }
            ЛОГ.log("\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+
                    " Success    doFilter doFilter doFilter IDДевайсаКлиентаRuntime " +IDДевайсаКлиентаRuntime+
                     " IDДевайсаКлиентаRuntime " +IDДевайсаКлиентаRuntime);
        } catch (Exception e) {
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletRuntime.txt");

        }
    }


    private void МетодФильтраНеПрошлаАунтификацию( HttpServletRequest asyrequest,HttpServletResponse asyresponse)
            throws IOException, ServletException {
        try {
        String  СерверРаботаетБезПараметров= "Server Running...... Don't Login and Password" ;
        // TODO: 10.03.2023 Ответ От Сервера
        bEANCallsBack.МетодBackДанныеКлиентуByte(asyresponse, СерверРаботаетБезПараметров.getBytes(StandardCharsets.UTF_8), ЛОГ  ,asyrequest);
    } catch (Exception e) {
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletRuntime.txt");

        }
    }


    public void destroy() {
        // TODO Auto-generated method stub
        // TODO Auto-generated method stub
        System.out.println(	"\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n");
    }
}
