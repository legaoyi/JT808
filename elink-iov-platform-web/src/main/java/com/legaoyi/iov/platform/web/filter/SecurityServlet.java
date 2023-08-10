package com.legaoyi.iov.platform.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

/**
 * @author gaoshengbo
 */
@WebFilter(filterName = "securityServlet", urlPatterns = "*.do")
public class SecurityServlet implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filter) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession(true);
//        Object user = session.getAttribute("user");// 登录人角色
//        String url = request.getRequestURI();
//        // 判断获取的路径不为空且不是访问登录页面或执行登录操作时跳转
//        if (user == null) {
//            if (StringUtils.isNotEmpty(url) && !(url.endsWith("login.do") || url.endsWith("register.do"))) {
//                //response.sendRedirect(request.getContextPath() + "/login.do");
//                java.io.PrintWriter out = response.getWriter();
//                out.println("<html>");
//                out.println("<script>");
//                out.println("window.open ('" + request.getContextPath() + "/login.do','_top')");
//                out.println("</script>");
//                out.println("</html>");
//                return;
//            }
//        }
//        req.setAttribute("user", user);
        filter.doFilter(req, resp);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }
}
