package dsu1.glassfish.atomic;

import businesslogic.BEANCallsBack;
import businesslogic.BeanAuntifications;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Enumeration;


@WebFilter(filterName = "FilterSousAvtodor",asyncSupported = false)
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
            Boolean СтатусаАунтификацииПользователя= false;
            ЛОГ=request.getServletContext();
            HttpServletRequest requestФильтра=  (HttpServletRequest) request;
            HttpServletResponse responseОтветКлиенту=		(HttpServletResponse) response;		// place your code here
            request.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
            response.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
            switch (((HttpServletRequest) request).getPathInfo()){
                case "/dsu1.glassfish.atomic":
                case "/dsu1.glassfish.atomic/DSU1JsonServlet":
                    // TODO: 10.03.2023  проверем статус логин и пароль
                 Object ЛогинОтКлиентаВнутриHeadler =((HttpServletRequest) request).getHeader("identifier");
                    if (ЛогинОтКлиентаВнутриHeadler.toString().length()>5) {
                        СтатусаАунтификацииПользователя = beanAuntifications.МетодАунтификация(ЛОГ, requestФильтра,  requestФильтра.getSession());
                    }
                    ЛОГ.log("   doFilter doFilter doFilter СтатусаАунтификацииПользователя " +СтатусаАунтификацииПользователя  + "  ЛогинОтКлиентаВнутриHeadler " +ЛогинОтКлиентаВнутриHeadler);
                    if (СтатусаАунтификацииПользователя==true) { // pass the request along the filter
                        chain.doFilter( request, response);
                        ЛОГ.log("\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                                " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                                " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+
                                " Success    doFilter doFilter doFilter СтатусаАунтификацииПользователя " +СтатусаАунтификацииПользователя);
                    }else {
                        StringBuffer СерверРаботаетБезПараметров=new StringBuffer("Server Running...... Don't Login and Password"+new Date().toGMTString().toString());
                        responseОтветКлиенту.addHeader("stream_size", String.valueOf(СерверРаботаетБезПараметров.length()));
                        ////requestФильтра.getRequestDispatcher("/index.jsp").forward(requestФильтра, responseОтветКлиенту);
                        // TODO: 10.03.2023 Ответ От Сервера
                        bEANCallsBack.МетодBackДанныеКлиенту(	   responseОтветКлиенту, СерверРаботаетБезПараметров, ЛОГ,   requestФильтра);
                        ЛОГ.log("\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                                " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                                " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+
                                " Error  doFilter doFilter doFilter СтатусаАунтификацииПользователя " +СтатусаАунтификацииПользователя);
                    }
                break;
                default:
                    RequestDispatcher requestDispatcher = ЛОГ.getRequestDispatcher("/dsu1.glassfish.atomic/index.jsp");
                    requestDispatcher.forward(request, response);
                   /// requestФильтра.getRequestDispatcher("/index.jsp").forward(requestФильтра, responseОтветКлиенту);
                    ЛОГ.log("\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                            " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                            " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n"+
                            " Error  doFilter doFilter doFilter СтатусаАунтификацииПользователя " );
                break;

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
