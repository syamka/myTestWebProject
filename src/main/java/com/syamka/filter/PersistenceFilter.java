package com.syamka.filter;

import com.syamka.hibernate.Util;

import javax.servlet.*;
import java.io.IOException;

/**
 * Title:
 * Description:
 * <p/>
 * User: valentina
 * Date: 08.11.13
 * Time: 22:28
 */
public class PersistenceFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            Util.getEm().getTransaction().begin();
            filterChain.doFilter(servletRequest, servletResponse);
            Util.getEm().getTransaction().commit();
        } catch (RuntimeException e) {

            if ( Util.getEm() != null && Util.getEm().isOpen())
                Util.getEm().getTransaction().rollback();
            throw e;

        } finally {
            Util.closeEm();
        }
    }

    @Override
    public void destroy() {}
}
