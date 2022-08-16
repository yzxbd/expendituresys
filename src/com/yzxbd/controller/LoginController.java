package com.yzxbd.controller;

import com.yzxbd.entity.User;
import com.yzxbd.exception.PasswordNotRightException;
import com.yzxbd.exception.UsernameNotRightException;
import com.yzxbd.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login/*")
public class LoginController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();

        String uri = req.getRequestURI();
        uri = uri.substring(uri.lastIndexOf("/"));

        UserService service = new UserService();
        if("/toLogin".equals(uri)){
            req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req,resp);
        }else if("/login".equals(uri)){
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            //调用模型校验账号与密码
            try {
                //登录成功，将admin对象绑定到session上，便于后期做登录拦截的验证
                User user = service.checkLogin(username,password);
                HttpSession session = req.getSession();
                session.setAttribute("user",user);

                //根据模型处理的结果跳转到不同的视图
                //跳转到主页面
                //resp.sendRedirect("/expendituresys/main/index");
                req.getRequestDispatcher("/WEB-INF/jsp/videos.jsp").forward(req,resp);

            } catch (UsernameNotRightException | PasswordNotRightException e) {
                //跳转到登录页面
                req.setAttribute("loginMsg",e.getMessage());
                req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req,resp);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
