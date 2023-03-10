package dsu1.glassfish.atomic;

import businesslogic.BEANCallsBack;
import businesslogic.BeanAuntifications;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;


@WebFilter(filterName = "FilterSousAvtodor",asyncSupported = true)
public class FilterSousAvtodor implements Filter {
    @EJB
    private BeanAuntifications beanAuntifications;
    @Inject
    private BEANCallsBack bEANCallsBack;
    private ServletContext ЛОГ;
    /**
     * Default constructor.
     */
    public FilterSousAvtodor() {
        // TODO Auto-generated constructor stub
        super();
        System.out.println(	"\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n");
    }
    /**
     * @see Filter#destroy()
     */
    public void destroy() {
        // TODO Auto-generated method stub
    }
    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    @SuppressWarnings("deprecation")
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // TODO Auto-generated method stub
        try {
            ЛОГ=request.getServletContext();
            HttpServletRequest requestФильтра=  (HttpServletRequest) request;
            HttpServletResponse responseОтветКлиенту=		(HttpServletResponse) response;		// place your code here
            requestФильтра.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
            responseОтветКлиенту.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
            // TODO: 10.03.2023  проверем статус логин и пароль  
            Boolean СтатусаАунтификацииПользователя= beanAuntifications.МетодАунтификация(ЛОГ, requestФильтра,  responseОтветКлиенту,requestФильтра.getSession());
            ЛОГ.log("   doFilter doFilter doFilter СтатусаАунтификацииПользователя " +СтатусаАунтификацииПользователя);
            if (СтатусаАунтификацииПользователя) { // pass the request along the filter
                chain.doFilter( (HttpServletRequest) request,(HttpServletResponse) response);
                ЛОГ.log(" Success    doFilter doFilter doFilter СтатусаАунтификацииПользователя " +СтатусаАунтификацииПользователя);
            }else {
                StringBuffer СерверРаботаетБезПараметров=new StringBuffer("Server Running...... Don't Login and Password"+new Date().toGMTString().toString());
                responseОтветКлиенту.addHeader("stream_size", String.valueOf(СерверРаботаетБезПараметров.length()));
                ////requestФильтра.getRequestDispatcher("/index.jsp").forward(requestФильтра, responseОтветКлиенту);
                // TODO: 10.03.2023 Ответ От Сервера
                bEANCallsBack.МетодГлавныйМетодПосылаемДанныеАндройду(	   responseОтветКлиенту, СерверРаботаетБезПараметров, ЛОГ,   requestФильтра);
                ЛОГ.log(" Error  doFilter doFilter doFilter СтатусаАунтификацииПользователя " +СтатусаАунтификацииПользователя);
                ЛОГ.log("\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                        " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                        " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n");
            }
        } catch (Exception e) {
            new SubClassWriterErros().МетодаЗаписиОшибкиВЛог(e, null,
                    "\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                            " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                            " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n",
                    Thread.currentThread().getStackTrace()[2],ЛОГ,ЛОГ.getServerInfo().toLowerCase());
        }
    }
    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        // TODO Auto-generated method stub
        System.out.println(	"\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n");
    }
}
