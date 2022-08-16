package com.yzxbd.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableMap;
import com.yzxbd.bean.ResponseBean;
import com.yzxbd.bean.SearchCondition;
import com.yzxbd.entity.Record;
import com.yzxbd.entity.Type;
import com.yzxbd.entity.User;
import com.yzxbd.service.RecordService;
import com.yzxbd.vo.RecordVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@WebServlet("/record/*")
public class RecordController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();

        String uri = req.getRequestURI();
        uri = uri.substring(uri.lastIndexOf("/"));

        RecordService service = new RecordService();
        if("/loadCourse".equals(uri)){
            try {
               /* List<Course> courses = service.loadCourse();
                out.print(JSON.toJSONString(ResponseBean.success(courses)));*/
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if("/loadContent".equals(uri)){
            try {
                int courseId = Integer.parseInt(req.getParameter("courseId"));
                /*List<Content> contents = service.loadContent(courseId);
                out.print(JSON.toJSONString(ResponseBean.success(contents)));*/
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if("/add".equals(uri)){
            try {
                Record product = genProduct(req);
                //调用模型处理数据
                service.insert(product);
                //响应
                out.print(JSON.toJSONString(ResponseBean.success()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if("/list".equals(uri)){
            try {
                List<RecordVO> list = service.findAll();
                out.print(JSON.toJSONString(ResponseBean.success(list)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if("/update".equals(uri)){
            try {
                int id = Integer.parseInt(req.getParameter("id"));
                String typeId = req.getParameter("type_id");
                String description = req.getParameter("description");
                String price = req.getParameter("price");
                String userId = req.getParameter("user_id");
                String time = req.getParameter("time");
                Record record = new Record();
                record.setId(id);
                record.setTypeId(Integer.parseInt(typeId));
                record.setUserId(Integer.parseInt(userId));
                record.setPrice(Double.parseDouble(price));
                record.setTime(time);
                record.setDescription(description);
                service.update(record);
                out.print(JSON.toJSONString(ResponseBean.success()));
            } catch (Exception e) {
                e.printStackTrace();
                out.print(JSON.toJSONString(ResponseBean.failure()));
            }
        }else if("/del".equals(uri)){
            try {
                int id = Integer.parseInt(req.getParameter("id"));
                service.del(id,getServletContext());
                out.print(JSON.toJSONString(ResponseBean.success()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if("/loadProduct".equals(uri)){
            try {
                int id = Integer.parseInt(req.getParameter("id"));
                Record r = service.findById(id);
                out.print(JSON.toJSONString(ResponseBean.success(r)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if("/getNames".equals(uri)){
            try {
                List<Type> list = service.findAllTypeName();
                out.print(JSON.toJSONString(ResponseBean.success(list)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if("/listForPage".equals(uri)){
            //获取所有的管理员数据
            try {
                int pageSize = 2;
                //获取页码
                String pageStr = req.getParameter("page");
                //如果没有page的参数，默认跳转到第一页，否则，查询相应页的数据
                int page = pageStr == null ? 1 : Integer.parseInt(pageStr);

                List<RecordVO> list = service.findAllRecords(page,pageSize);
                int totalPages = service.getTotalPages(pageSize);

                //将集合数据返回给ajax,list-->json字符串
                Map<String,Object> map = ImmutableMap.of("list",list,"totalPages",totalPages);
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


    private Record genProduct(HttpServletRequest req){
        String name = req.getParameter("name");
        int userId = Integer.parseInt(req.getParameter("user_id"));
        int typeId = Integer.parseInt(req.getParameter("type_id"));
        String description = req.getParameter("description");
        double price = Double.parseDouble(req.getParameter("price"));
        Record r = new Record();
        r.setName(name);
        r.setUserId(userId);
        r.setTypeId(typeId);
        r.setDescription(description);
        r.setPrice(price);
        return r;
    }
}
