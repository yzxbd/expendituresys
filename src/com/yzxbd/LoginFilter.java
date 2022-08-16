package com.yzxbd;

import com.yzxbd.dao.UserDao;
import com.yzxbd.factory.DaoFactory;
import com.yzxbd.util.CookieUtil;
import org.apache.commons.lang.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String uri = req.getRequestURI();
        if(uri.contains("login") || uri.endsWith("css") || uri.endsWith("js") || uri.contains("images")){
            filterChain.doFilter(req,resp);
            return;
        }

        //判断session中是否包含用户的信息
        HttpSession session = req.getSession();
        Object obj = session.getAttribute("user");

        if(obj!=null){
            //登录成功
            filterChain.doFilter(req,resp);
            return;
        }

        //session中没有用户的信息，还需要继续校验cookie中是否包含用户的信息
        String username = CookieUtil.findCookie("user", req);
        if(StringUtils.isBlank(username)){
            //登录失败，重定向到登陆页面
            resp.sendRedirect("/expendituresys/login/toLogin");
        }else{
            //将用户的信息备份到session中，实现自动续时
            UserDao dao = DaoFactory.getInstance("UserDao");
            try {
                session.setAttribute("user",dao.findByUsername(username));
            } catch (Exception e) {
                e.printStackTrace();
            }
            filterChain.doFilter(req,resp);
        }
    }

    @Override
    public void destroy() {

    }
}
