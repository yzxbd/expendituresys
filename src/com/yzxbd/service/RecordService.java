package com.yzxbd.service;

import com.yzxbd.dao.RecordDao;
import com.yzxbd.entity.Record;
import com.yzxbd.entity.Type;
import com.yzxbd.factory.DaoFactory;
import com.yzxbd.vo.RecordVO;

import javax.servlet.ServletContext;
import java.util.List;

public class RecordService {
    private RecordDao dao = DaoFactory.getInstance("RecordDao");

    public void insert(Record r) throws Exception {
        dao.insert(r);
    }

    public List<RecordVO> findAll() throws Exception {
        return dao.findAll();
    }

    public void del(int id, ServletContext sc) throws Exception {
        //根据id查询当前产品的图片与视频
        Record r = dao.findById(id);
        //删除表记录
        dao.del(id);
    }

    public  Record findById(int id) throws Exception {
        return dao.findById(id);
    }

    public List<Type> findAllTypeName() throws Exception {
        return dao.queryAllTypeName();
    }

    public int getTotalPages(int pageSize) throws Exception {
        return dao.getTotalPages(pageSize);
    }

    public List<RecordVO> findAllRecords(int page, int pageSize) throws Exception {
        return dao.queryAllRecords(page,pageSize);
    }

    public void update(Record record) throws Exception {
        dao.updateRecords(record);
    }
}
