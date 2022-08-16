package com.yzxbd.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
    static Properties p = new Properties();
    static{
        //读取db文件中数据，并保存到p对象中
        try {
            PropertyFileEnum[] values = PropertyFileEnum.values();
            for(PropertyFileEnum value :values){
                InputStream ins = PropertiesUtil.class.getClassLoader().getResourceAsStream(value.getFileName());
                p.load(ins);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getVal(String key){
        return p.getProperty(key);
    }

    public static void main(String[] args) {
        System.out.println(getVal("name"));
    }

    enum PropertyFileEnum{
        DB("db.properties"),
        DAO("dao.properties");
        private String fileName;
        PropertyFileEnum(String fileName){
            this.fileName = fileName;
        }

        public String getFileName() {
            return fileName;
        }
    }

}
