package com.yzxbd.dao.impl;

import com.yzxbd.dao.RecordDao;
import com.yzxbd.entity.Record;
import com.yzxbd.entity.Type;
import com.yzxbd.util.BeanUtil;
import com.yzxbd.util.DruidPoolUtil;
import com.yzxbd.util.FormatTime;
import com.yzxbd.vo.RecordVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class RecordDaoImpl implements RecordDao {

    @Override
    public void insert(Record p) throws Exception {
        Connection con = DruidPoolUtil.getConnection();
        String sql = "insert into record values (null,?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,p.getName());
        ps.setInt(2,p.getUserId());
        ps.setInt(3,p.getTypeId());
        ps.setString(4,p.getDescription());
        ps.setDouble(5,p.getPrice());
        String time = FormatTime.getFormatTime(new Date());
        ps.setString(6,time);
        ps.executeUpdate();
        DruidPoolUtil.close(ps,con);
    }

    @Override
    public List<RecordVO> findAll() throws Exception {
        Connection con = DruidPoolUtil.getConnection();
        //String sql = "select r.id,r.user_id as userId,r.description,r.type_id typeId,u.username as userName ,t.name as typeName,r.time,r.price from record r inner join type t on t.id=r.type_id inner join user u on u.id=r.user_id";
        String sql1 = "select r.id,r.user_id as userId,r.description,r.type_id typeId,r.time,r.price from record r inner join type t on t.id=r.type_id";
        String sql2 = "select u.username as userName ,t.name as typeName from record r inner join type t on t.id=r.type_id inner join user u on u.id=r.user_id";
        PreparedStatement ps1 = con.prepareStatement(sql1);
        PreparedStatement ps2 = con.prepareStatement(sql2);
        ResultSet rs1 = ps1.executeQuery();
        ResultSet rs2 = ps2.executeQuery();
        List<RecordVO> recordsVO = BeanUtil.resultToBeanManyVO(rs1, rs2, RecordVO.class);
        DruidPoolUtil.close(rs1,ps1,con);
        DruidPoolUtil.close(rs2,ps2,con);
        return recordsVO;
    }

    @Override
    public Record findById(int id) throws Exception {
        Connection con = DruidPoolUtil.getConnection();
        String sql = "select id,user_id as userId,type_id typeId,description,price,time from record where id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1,id);
        ResultSet rs = ps.executeQuery();
        Record record = BeanUtil.resultToBeanOne(rs, Record.class);
        DruidPoolUtil.close(rs,ps,con);
        return record;
    }

    @Override
    public void del(int id) throws Exception {
        Connection con = DruidPoolUtil.getConnection();
        String sql = "delete from record where id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1,id);
        ps.executeUpdate();
        DruidPoolUtil.close(ps,con);
    }

    @Override
    public List<Type> queryAllTypeName() throws Exception {
        Connection con = DruidPoolUtil.getConnection();
        String sql = "select * from type ";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<Type> types = BeanUtil.resultToBeanMany(rs, Type.class);
        DruidPoolUtil.close(rs,ps,con);
        return types;
    }

    @Override
    public int getTotalPages(int pageSize) throws Exception {
        //获取总记录数
        Connection con = DruidPoolUtil.getConnection();
        String sql = "select count(*) from record";
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
    public List<RecordVO> queryAllRecords(int page, int pageSize) throws Exception {
        Connection con = DruidPoolUtil.getConnection();
        //String sql = "select r.id,r.user_id as userId,r.description,r.type_id typeId,u.username as userName ,t.name as typeName,r.time,r.price from record r inner join type t on t.id=r.type_id inner join user u on u.id=r.user_id";
        String sql1 = "select r.id,r.user_id as userId,r.description,r.type_id typeId,r.time,r.price from record r inner join type t on t.id=r.type_id  order by r.id desc limit ?,?";
        String sql2 = "select u.username as userName ,t.name as typeName from record r inner join type t on t.id=r.type_id inner join user u on u.id=r.user_id  order by r.id desc limit ?,?";
        PreparedStatement ps1 = con.prepareStatement(sql1);
        PreparedStatement ps2 = con.prepareStatement(sql2);
        //给分页的参数赋值
        ps1.setInt(1,(page - 1) * pageSize);
        ps1.setInt(2,pageSize);
        ps2.setInt(1,(page - 1) * pageSize);
        ps2.setInt(2,pageSize);
        ResultSet rs1 = ps1.executeQuery();
        ResultSet rs2 = ps2.executeQuery();
        List<RecordVO> recordsVO = BeanUtil.resultToBeanManyVO(rs1, rs2, RecordVO.class);
        DruidPoolUtil.close(rs1,ps1,con);
        DruidPoolUtil.close(rs2,ps2,con);
        return recordsVO;

        /*Connection con = DruidPoolUtil.getConnection();
        String sql = "select id,user_id as userId,type_id typeId,description,price,time from record order by id desc limit ?,?";
        PreparedStatement ps = con.prepareStatement(sql);
        //给分页的参数赋值
        ps.setInt(1,(page - 1) * pageSize);
        ps.setInt(2,pageSize);
        ResultSet rs = ps.executeQuery();
        List<Record> list = getRecords(rs);
        DruidPoolUtil.close(rs,ps,con);
        return list;*/
    }

    @Override
    public void updateRecords(Record record) throws Exception {
        Connection con = DruidPoolUtil.getConnection();
        String sql = "update record set user_id=?,type_id=?,price=?,description=?,time=? where id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1,record.getUserId());
        ps.setInt(2,record.getTypeId());
        ps.setDouble(3,record.getPrice());
        ps.setString(4,record.getDescription());
        ps.setString(5,record.getTime());
        ps.setInt(6,record.getId());
        ps.executeUpdate();
        DruidPoolUtil.close(ps,con);
    }

    private List<Record> getRecords(ResultSet rs) {
        List<Record> records = BeanUtil.resultToBeanMany(rs, Record.class);
        //空判断，避免空指针异常
        if(records == null){
            return Collections.EMPTY_LIST;
        }
        return records;
    }
}

