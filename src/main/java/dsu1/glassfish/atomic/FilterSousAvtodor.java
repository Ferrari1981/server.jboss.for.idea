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
import java.util.Date;


@WebFilter()//servletNames = "dsu1JsonServlet",urlPatterns = {"/FilterSousAvtodor"}
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
            Boolean СтатусаАунтификацииПользователя=
                    beanAuntifications.МетодЗапускаетАунтифиувциюПользователяПриВходе(ЛОГ, requestФильтра,  responseОтветКлиенту,requestФильтра.getSession());
            ЛОГ.log("   doFilter doFilter doFilter СтатусаАунтификацииПользователя " +СтатусаАунтификацииПользователя);
            if (СтатусаАунтификацииПользователя) { // pass the request along the filter
                chain.doFilter( (HttpServletRequest) request,(HttpServletResponse) response);
            }else {
                StringBuffer СерверРаботаетБезПараметров=new StringBuffer("Server Running...... Don't Login and Password"+new Date().toGMTString().toString());
                responseОтветКлиенту.addHeader("stream_size", String.valueOf(СерверРаботаетБезПараметров.length()));
               bEANCallsBack.МетодГлавныйМетодПосылаемДанныеАндройду(	   responseОтветКлиенту, СерверРаботаетБезПараметров, ЛОГ,   requestФильтра);
                ЛОГ.log("   doFilter doFilter doFilter СтатусаАунтификацииПользователя " +СтатусаАунтификацииПользователя);
                /*
                 * HttpServletRequest requestКлиента= (HttpServletRequest) request;
                 * requestКлиента.getRequestDispatcher("/jboss.jsp").forward(request, response);
                 */
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
