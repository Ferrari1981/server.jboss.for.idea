package dsu1glassfishatomic;

import businesslogic.BEANCallsBack;
import runtimejboss.BeanGetLoginAndPasswords;
import businesslogic.SubClassWriterErros;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Optional;


@WebFilter(value={ "/sous.jboss.tabel", "/sous.jboss.download" ,"/sous.jboss.scanner"},asyncSupported = true)
public class FilterPublic implements Filter {
    @EJB
    private BeanGetLoginAndPasswords beanGetLoginAndPasswords;
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
        final AsyncContext     asy = request.startAsync(request, response);
        HttpServletRequest asyrequest = (HttpServletRequest) asy.getRequest();
        HttpServletResponse asyresponse = (HttpServletResponse) asy.getResponse();
        try {
            Boolean СтатусаАунтификацииПользователя= false;
            asyrequest.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
            asyresponse.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
                    // TODO: 10.03.2023  проверем статус логин и пароль
            //Object ЛогинОтAndroid=      ((HttpServletRequest)request).getHeaders("identifier").nextElement()
            Object IDДевайсаКлиентаPUBLIC=        Optional.ofNullable(((HttpServletRequest)asyrequest).getHeaders("id_device_androis").nextElement()).orElse("");
            ЛОГ.log("\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+
                    "  ЛогинОтAndroid    doFilter doFilter doFilter IDДевайсаКлиентаPUBLIC " +IDДевайсаКлиентаPUBLIC);
            if (IDДевайсаКлиентаPUBLIC.toString().length()>5) {

                    СтатусаАунтификацииПользователя = beanGetLoginAndPasswords.МетодGetLoginAndPassword(ЛОГ, ((HttpServletRequest)asyrequest),
                            ((HttpServletRequest)asyrequest) .getSession());

                if (СтатусаАунтификацииПользователя==true) { // pass the request along the filter
                    // TODO: 11.03.2023 ГЛАВНАЯ СТРОЧКА ПЕРЕНАРАВЛЕНИЕ НА СЕВРЕЛТЫ НА ГЛАВНЫЙ КОД
                    chain.doFilter(asyrequest,asyresponse);
                    ЛОГ.log("\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                            " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                            " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+
                            " Success    doFilter doFilter doFilter СтатусаАунтификацииПользователя " +СтатусаАунтификацииПользователя);
                }else {
                    // TODO: 11.03.2023 ИМя и Пароль не Правильный
                    МетодФильтраНеПрошлаАунтификацию(asyresponse,asyrequest);
                    ЛОГ.log("\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                            " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                            " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+
                            " Error  doFilter doFilter doFilter СтатусаАунтификацииПользователя "+СтатусаАунтификацииПользователя );
                }
                
            }else{
                // TODO: 11.03.2023  нет не имени не пароля
                RequestDispatcher requestDispatcher = asyrequest.getRequestDispatcher("/indexfilterpublic.jsp");
                requestDispatcher.forward(asyrequest, asyresponse);
                /// requestФильтра.getRequestDispatcher("/index.jsp").forward(requestФильтра, responseОтветКлиенту);
              //  МетодФильтраНеПрошлаАунтификацию(response, СтатусаАунтификацииПользователя);
            }
            ЛОГ.log("\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+
                    " Success    doFilter doFilter doFilter СтатусаАунтификацииПользователя " +СтатусаАунтификацииПользователя+
                     " IDДевайсаКлиентаPUBLIC " +IDДевайсаКлиентаPUBLIC);
        } catch (Exception e) {
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletDSU1.txt");
        }
            // TODO: 23.04.2023  end
    }

    private void МетодФильтраНеПрошлаАунтификацию(HttpServletResponse asyresponse,  HttpServletRequest asyrequest)
            throws IOException, ServletException {
        try {
        StringBuffer СерверРаботаетБезПараметров=new StringBuffer("Server Running...... Don't Login and Password"+new Date().toGMTString().toString());
        ((HttpServletResponse) asyresponse)  .addHeader("stream_size", String.valueOf(СерверРаботаетБезПараметров.length()));
        // TODO: 10.03.2023 Ответ От Сервера
        bEANCallsBack.МетодBackДанныеКлиенту(asyresponse, СерверРаботаетБезПараметров, ЛОГ );
    } catch (Exception e) {
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletDSU1.txt");
    }
            // TODO: 23.04.2023
    }



    public void destroy() {
        // TODO Auto-generated method stub
        // TODO Auto-generated method stub
        System.out.println(	"\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n");
    }
}
