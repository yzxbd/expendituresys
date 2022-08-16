package com.yzxbd.util;

import com.google.common.collect.Lists;
import com.yzxbd.entity.Record;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Collections;
import java.util.List;

public class BeanUtil {
    /**
     *
     * @param rs 查询之后返回的结果集
     * @param clazz 待封装的bean对象的类实例
     * @param <T>
     * @return
     */
    public static <T> List<T> resultToBeanMany(ResultSet rs,Class<T> clazz){
        if(rs == null){
            return Collections.EMPTY_LIST;
        }
        List<T> list = Lists.newArrayList();
        try {
            //元数据
            ResultSetMetaData metaData = rs.getMetaData();
            //列数
            int columns = metaData.getColumnCount();
            while(rs.next()){
                //创建对象
                T t = clazz.newInstance();
                //给对象的属性复制
                for(int i = 1;i <= columns;i++){
                    //获取列名
                    String fieldName = metaData.getColumnLabel(i);
                    //获取列值
                    Object val = rs.getObject(fieldName);
                    //获取属性
                    Field field = clazz.getDeclaredField(fieldName);
                    field.setAccessible(true);
                    field.set(t,val);
                }
                list.add(t);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }


    public static <T> T resultToBeanOne(ResultSet rs,Class<T> clazz){
        List<T> list = resultToBeanMany(rs, clazz);
        if(list == null){
            return null;
        }
        T t = null;
        if(list.size() > 0){
            t = list.get(0);
        }
        return t;
    }


    public static void main(String[] args) {
        int a = 10;
        a = a + 10;
    }

    public static <T> List<T> resultToBeanManyVO(ResultSet rs1,ResultSet rs2,Class<T> clazz){
        if(rs1 == null || rs2 == null){
            return Collections.EMPTY_LIST;
        }
        List<T> list = Lists.newArrayList();
        try {
            //元数据
            ResultSetMetaData metaData1 = rs1.getMetaData();
            ResultSetMetaData metaData2 = rs2.getMetaData();
            //列数
            int columns1 = metaData1.getColumnCount();
            int columns2 = metaData2.getColumnCount();
            while(rs1.next() && rs2.next()){
                //创建对象
                T t = clazz.newInstance();
                Record record = new Record();
                //给对象的属性复制
                for(int i = 1;i <= columns1;i++){
                    //获取列名
                    String fieldName1 = metaData1.getColumnLabel(i);
                    //获取列值
                    //Object val1 = rs1.getObject(fieldName1);
                    switch (fieldName1){
                        case "id" : {
                            record.setId(rs1.getInt(fieldName1));
                            break;
                        }
                        case "userId" : {
                            record.setUserId(rs1.getInt(fieldName1));
                            break;
                        }
                        case "typeId" : {
                            record.setTypeId(rs1.getInt(fieldName1));
                            break;
                        }
                        case "price" : {
                            record.setPrice(rs1.getDouble(fieldName1));
                            break;
                        }
                        case "description" : {
                            record.setDescription(rs1.getString(fieldName1));
                            break;
                        }
                        case "time" : {
                            record.setTime(rs1.getString(fieldName1));
                            break;
                        }
                    }
                }
                //获取属性
                Field field1 = clazz.getDeclaredField("record");
                field1.setAccessible(true);
                field1.set(t,record);
                for(int i = 1;i <= columns2;i++){
                    //获取列名
                    String fieldName2 = metaData2.getColumnLabel(i);
                    //获取列值
                    Object val2 = rs2.getObject(fieldName2);
                    //获取属性
                    Field field2 = clazz.getDeclaredField(fieldName2);
                    field2.setAccessible(true);
                    field2.set(t,val2);
                }
                list.add(t);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

}
