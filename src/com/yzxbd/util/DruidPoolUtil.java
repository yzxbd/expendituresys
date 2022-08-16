package com.yzxbd.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @Author: julv
 * @Date: 2021/10/29 17:29
 *
 */
public class DruidPoolUtil {
    /**
     * 连接资源--连接池 存储连接对象(connection)
     */
    private static DataSource dataSource = null;
    private static Properties p = new Properties();
    static{
        try {
            InputStream ins = PropertiesUtil.class.getClassLoader().getResourceAsStream("db.properties");
            p.load(ins);
            dataSource = DruidDataSourceFactory.createDataSource(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static Connection getConnection(){
        Connection con = null;
        try {
            con = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public static void close(ResultSet rs, Statement statement, Connection con){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(statement!=null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(con!=null){
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(Statement statement,Connection con){
        close(null,statement,con);
    }

    public static void main(String[] args) {
        System.out.println(getConnection());
    }

}
