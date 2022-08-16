package com.yzxbd.factory;

import com.yzxbd.util.PropertiesUtil;

public class DaoFactory {
    /**
     * 返回符合type接口要求的对象
     * @param type 接口类型
     * @return
     */
    public static <T> T getInstance(String type){
        T obj = null;
        //根据type查找properties文件中value值(实现类)
        String className = PropertiesUtil.getVal(type);
        try {
            obj = (T) Class.forName(className).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
}
