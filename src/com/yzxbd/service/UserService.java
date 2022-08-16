package com.yzxbd.service;

import com.yzxbd.dao.UserDao;
import com.yzxbd.entity.User;
import com.yzxbd.exception.PasswordNotRightException;
import com.yzxbd.exception.UsernameNotRightException;
import com.yzxbd.factory.DaoFactory;

import java.util.List;

public class UserService {
    private UserDao dao = DaoFactory.getInstance("UserDao");

    public List<User> findAll(int page, int pageSize) throws Exception {
        return dao.findAll(page, pageSize);
    }

    public int getTotalPages(int pageSize) throws Exception {
        return dao.getTotalPages(pageSize);
    }

    public void add(User user) throws Exception {
        int adminId = dao.addUser(user);
    }

    public User findById(int id) throws Exception {
        return dao.findById(id);
    }

    public void update(User user) throws Exception {
        dao.updateUser(user);
    }

    public void del(int userId) throws Exception {
        dao.delUser(userId);
    }

    public User checkLogin(String username, String password) throws Exception {
        User user = dao.findByUsername(username);
        if(user==null){
            //账号错误
            throw new UsernameNotRightException("账号错误");
        }
        if(!user.getPassword().equals(password)){
            throw new PasswordNotRightException("密码错误");
        }
        return user;
    }

    public List<User> findAllUserName() throws Exception {
        List<User> users = dao.queryAllUserName();
        return users;
    }
}



