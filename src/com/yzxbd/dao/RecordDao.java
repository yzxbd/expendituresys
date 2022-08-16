package com.yzxbd.dao;

import com.yzxbd.entity.Record;
import com.yzxbd.entity.Type;
import com.yzxbd.vo.RecordVO;

import java.sql.SQLException;
import java.util.List;

public interface RecordDao {
    void insert(Record p) throws  Exception;
    List<RecordVO> findAll() throws Exception;
    Record findById(int id) throws Exception;
    void del(int id) throws  Exception;

    List<Type> queryAllTypeName() throws Exception;

    int getTotalPages(int pageSize) throws Exception;

    List<RecordVO> queryAllRecords(int page, int pageSize) throws Exception;

    void updateRecords(Record record) throws Exception;
}
