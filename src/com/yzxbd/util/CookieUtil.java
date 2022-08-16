package com.yzxbd.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;


public class CookieUtil {
    public static final String PATH = "/expendituresys";
    public static void addCookie(String name, String value, int age, HttpServletResponse resp){
        try {
            Cookie cookie = new Cookie(name, URLEncoder.encode(value,"UTF-8"));
            cookie.setMaxAge(age);
            cookie.setPath(PATH);
            resp.addCookie(cookie);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据cookie的名称查找cookie的值
     * @param name
     * @param req
     * @return
     */
    public static String findCookie(String name, HttpServletRequest req){
        String value = null;
        Cookie[] cookies = req.getCookies();
        if(cookies == null){
            return value;
        }

        for (Cookie cookie : cookies) {
            if(cookie.getName().equals(name)){
                try {
                    value = URLDecoder.decode(cookie.getValue(),"UTF-8");
                    break;
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        return value;
    }

    /**
     * 删除cookie
     * @param name
     * @param resp
     */
    public static void delCookie(String name, HttpServletResponse resp){
//        Cookie cookie = new Cookie(name,"");
//        cookie.setPath(PATH);
//        cookie.setMaxAge(0);
//        resp.addCookie(cookie);
        addCookie(name,"",0,resp);
    }


}
