package com.yzxbd.dao.impl;

import com.yzxbd.dao.UserDao;
import com.yzxbd.entity.User;
import com.yzxbd.util.BeanUtil;
import com.yzxbd.util.DruidPoolUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserDaoImpl implements UserDao {

    @Override
    public List<User> findAll(int page,int pageSize) throws Exception {
        Connection con = DruidPoolUtil.getConnection();
        String sql = "select id,username,password from user order by id desc limit ?,?";
        PreparedStatement ps = con.prepareStatement(sql);
        //给分页的参数赋值
        ps.setInt(1,(page-1)*pageSize);
        ps.setInt(2,pageSize);
        ResultSet rs = ps.executeQuery();
        List<User> list = getUsers(rs,con);
        DruidPoolUtil.close(rs,ps,con);
        return list;
    }

    @Override
    public int getTotalPages(int pageSize) throws Exception {
        //获取总记录数
        Connection con = DruidPoolUtil.getConnection();
        String sql = "select count(*) from user";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        //总记录数 3
        int rows = 0;
        if(rs.next()){
            rows = rs.getInt(1);
        }
        DruidPoolUtil.close(rs,ps,con);
        if(rows%pageSize == 0){
            return rows / pageSize;
        }else{
            return rows / pageSize + 1;
        }
    }

    @Override
    public int addUser(User user) throws Exception {
        Connection con = DruidPoolUtil.getConnection();
        String sql = "insert into user values (null,?,?)";
        PreparedStatement ps = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setString(1,user.getUsername());
        ps.setString(2,user.getPassword());
        ps.executeUpdate();
        //获取主键值
        ResultSet rs = ps.getGeneratedKeys();
        int key = 0 ;
        if(rs.next()){
            key = rs.getInt(1);
        }
        DruidPoolUtil.close(ps,con);
        return key;
    }

    @Override
    public User findById(int id) throws Exception {
        Connection con = DruidPoolUtil.getConnection();
        String sql = "select id,username,password from user where id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1,id);
        ResultSet rs = ps.executeQuery();
        List<User> users = getUsers(rs, con);
        //关闭连接
        DruidPoolUtil.close(rs,ps,con);
        //取第一个元素返回
        if(users == null || users.size()==0){
            return null;
        }
        return users.get(0);
    }

    @Override
    public void updateUser(User user) throws Exception {
        Connection con = DruidPoolUtil.getConnection();
        String sql = "update user set username=?,password=? where id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,user.getUsername());
        ps.setString(2,user.getPassword());
        ps.setInt(3,user.getId());
        ps.executeUpdate();
        DruidPoolUtil.close(ps,con);
    }

    @Override
    public void delUser(int id) throws Exception {
        Connection con = DruidPoolUtil.getConnection();
        String sql = "delete from user where id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1,id);
        ps.executeUpdate();
        DruidPoolUtil.close(ps,con);
    }

    @Override
    public User findByUsername(String username) throws Exception {
        Connection con = DruidPoolUtil.getConnection();
        String sql = "select * from user where username=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,username);
        ResultSet rs = ps.executeQuery();
        User user = BeanUtil.resultToBeanOne(rs, User.class);
        DruidPoolUtil.close(rs,ps,con);
        return user;
    }

    @Override
    public List<User> queryAllUserName() throws Exception {
        Connection con = DruidPoolUtil.getConnection();
        String sql = "select * from user";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<User> users = BeanUtil.resultToBeanMany(rs, User.class);
        DruidPoolUtil.close(rs,ps,con);
        return users;
    }

    private List<User> getUsers(ResultSet rs,Connection con) {
        List<User> user = BeanUtil.resultToBeanMany(rs, User.class);
        //空判断，避免空指针异常
        if(user==null){
            return Collections.EMPTY_LIST;
        }
        return user;
    }

}

