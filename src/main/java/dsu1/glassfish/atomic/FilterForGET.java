package dsu1.glassfish.atomic;

import businesslogic.BEANCallsBack;
import businesslogic.BeanAuntifications;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.mail.Header;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;


@WebFilter(value="/dsu1.glassfish.atomic",asyncSupported = true)
public class FilterForGET implements Filter {
    @EJB
    private BeanAuntifications beanAuntifications;
    @Inject
    private BEANCallsBack bEANCallsBack;
    private ServletContext ЛОГ;

    public void init(FilterConfig fConfig) throws ServletException {
        // TODO Auto-generated method stub
        ЛОГ=fConfig.getServletContext();
        System.out.println(	"\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n");
    }


    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // TODO Auto-generated method stub
        try {
            Boolean СтатусаАунтификацииПользователя= false;
            request.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
            response.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
                    // TODO: 10.03.2023  проверем статус логин и пароль
            Object ЛогинОтКлиентаВнутриHeadle2=      ((HttpServletRequest)request).getHeaders("identifier").nextElement();
         Object ЛогинОтКлиентаВнутриHeadler=((HttpServletRequest)request).getHeader("identifier");
            if (ЛогинОтКлиентаВнутриHeadler!=null) {
                if (ЛогинОтКлиентаВнутриHeadler.toString().length()>5) {
                    СтатусаАунтификацииПользователя = beanAuntifications.МетодАунтификация(ЛОГ, ((HttpServletRequest)request),  ((HttpServletRequest)request) .getSession());
                }
                if (СтатусаАунтификацииПользователя==true) { // pass the request along the filter
                    // TODO: 11.03.2023 ГЛАВНАЯ СТРОЧКА ПЕРЕНАРАВЛЕНИЕ НА СЕВРЕЛТЫ НА ГЛАВНЫЙ КОД
                    chain.doFilter(request,response);
                    ЛОГ.log("\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                            " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                            " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+
                            " Success    doFilter doFilter doFilter СтатусаАунтификацииПользователя " +СтатусаАунтификацииПользователя);
                }else {
                    МетодФильтраНеПрошлаАунтификацию(response, СтатусаАунтификацииПользователя);
                }
                
            }else{
                // TODO: 11.03.2023  нет не имени не пароля
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
                requestDispatcher.forward(request, response);
                /// requestФильтра.getRequestDispatcher("/index.jsp").forward(requestФильтра, responseОтветКлиенту);
              //  МетодФильтраНеПрошлаАунтификацию(response, СтатусаАунтификацииПользователя);
            }
            ЛОГ.log("\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+
                    " Success    doFilter doFilter doFilter СтатусаАунтификацииПользователя " +СтатусаАунтификацииПользователя+
                     " ЛогинОтКлиентаВнутриHeadler " +ЛогинОтКлиентаВнутриHeadler);
        } catch (Exception e) {
            new SubClassWriterErros().МетодаЗаписиОшибкиВЛог(e, null,
                    "\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                            " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                            " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n",
                    Thread.currentThread().getStackTrace()[2],ЛОГ,ЛОГ.getServerInfo().toLowerCase());
        }
    }

    private void МетодФильтраНеПрошлаАунтификацию(ServletResponse response, Boolean СтатусаАунтификацииПользователя)
            throws IOException, ServletException {
        try {
        StringBuffer СерверРаботаетБезПараметров=new StringBuffer("Server Running...... Don't Login and Password"+new Date().toGMTString().toString());
        ((HttpServletResponse) response)  .addHeader("stream_size", String.valueOf(СерверРаботаетБезПараметров.length()));
        // TODO: 10.03.2023 Ответ От Сервера
        bEANCallsBack.МетодBackДанныеКлиенту(response, СерверРаботаетБезПараметров, ЛОГ);
        ЛОГ.log("\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+
                " Error  doFilter doFilter doFilter СтатусаАунтификацииПользователя " + СтатусаАунтификацииПользователя);
    } catch (Exception e) {
        new SubClassWriterErros().МетодаЗаписиОшибкиВЛог(e, null,
                "\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                        " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                        " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n",
                Thread.currentThread().getStackTrace()[2],ЛОГ,ЛОГ.getServerInfo().toLowerCase());
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
