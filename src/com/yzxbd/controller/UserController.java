package com.yzxbd.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableMap;
import com.yzxbd.bean.ResponseBean;
import com.yzxbd.entity.User;
import com.yzxbd.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;


@WebServlet("/user/*")
public class UserController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();

        String uri = req.getRequestURI();
        uri = uri.substring(uri.lastIndexOf("/"));

        //创建模型
        UserService service = new UserService();

        if("/list".equals(uri)){
            //获取所有的管理员数据
            try {
                int pageSize = 2;
                //获取页码
                String pageStr = req.getParameter("page");
                //如果没有page的参数，默认跳转到第一页，否则，查询相应页的数据
                int page = pageStr == null ? 1 : Integer.parseInt(pageStr);

                List<User> list = service.findAll(page,pageSize);
                int totalPages = service.getTotalPages(pageSize);

                //将集合数据返回给ajax,list-->json字符串
                Map<String,Object> map = ImmutableMap.of("list",list,"totalPages",totalPages);
                ResponseBean bean = ResponseBean.success(map);
                out.print(JSON.toJSONString(bean));
            } catch (Exception e) {
                e.printStackTrace();
                out.print(JSON.toJSONString(ResponseBean.failure()));
            }
        }else if("/add".equals(uri)){
            try {
                String username = req.getParameter("username");
                String password = req.getParameter("password");
                User user = new User();
                user.setUsername(username);
                user.setPassword(password);
                service.add(user);
                out.print(JSON.toJSONString(ResponseBean.success()));
            } catch (Exception e) {
                e.printStackTrace();
                out.print(JSON.toJSONString(ResponseBean.failure()));
            }
        }else if("/load".equals(uri)){
            try {
                int id = Integer.parseInt(req.getParameter("id"));
                User user = service.findById(id);
                out.print(JSON.toJSONString(ResponseBean.success(user)));
            } catch (Exception e) {
                e.printStackTrace();
                out.print(JSON.toJSONString(ResponseBean.failure()));
            }
        }else if("/update".equals(uri)){
            try {
                int id = Integer.parseInt(req.getParameter("id"));
                String username = req.getParameter("username");
                String password = req.getParameter("password");
                User user = new User();
                user.setId(id);
                user.setUsername(username);
                user.setPassword(password);
                service.update(user);
                out.print(JSON.toJSONString(ResponseBean.success()));
            } catch (Exception e) {
                e.printStackTrace();
                out.print(JSON.toJSONString(ResponseBean.failure()));
            }
        }else if("/del".equals(uri)){
            try {
                String[] ids = req.getParameterValues("id");
                for (String id : ids) {
                    service.del(Integer.parseInt(id));
                }
                out.print(JSON.toJSONString(ResponseBean.success()));
            } catch (Exception e) {
                e.printStackTrace();
                out.print(JSON.toJSONString(ResponseBean.failure()));
            }
        }else if ("/getNames".equals(uri)){
            //获取所有的管理员数据
            try {
                List<User> list = service.findAllUserName();
                //将集合数据返回给ajax,list-->json字符串
                Map<String,Object> map = ImmutableMap.of("list",list);
                ResponseBean bean = ResponseBean.success(map);
                out.print(JSON.toJSONString(bean));
            } catch (Exception e) {
                e.printStackTrace();
                out.print(JSON.toJSONString(ResponseBean.failure()));
            }
        }else {
            req.getRequestDispatcher("/WEB-INF/jsp/" + uri + ".jsp").forward(req,resp);
        }
    }
}