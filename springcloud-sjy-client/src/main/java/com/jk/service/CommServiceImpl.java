package com.jk.service;

import com.jk.mapper.CommMapper;
import com.jk.model.Comm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CommServiceImpl implements UserServiceApi {

    @Autowired
    private CommMapper commDao;

    @Override
    public Map<String, Object> queryComm(int page, int rows, @RequestBody Comm comm) {
        HashMap<String, Object> map = new HashMap<>();
        long count = commDao.queryComm(comm);
        int start = (page - 1)*rows;
        int end = start + rows;
        List<Comm> commList = commDao.queryCoomList(start,end,comm);
        map.put("total",count);
        map.put("rows",commList);
        return map;
    }

    @Override
    public void saveComm(@RequestBody Comm comm) {
        commDao.saveComm(comm);
    }

    @Override
    public Comm queryCommById(String id) {

        return commDao.queryCommById(id);
    }

    @Override
    public void updateComm(@RequestBody Comm comm) {

        commDao.updateComm(comm);
    }

    @Override
    public void deleteComm(String id) {

         commDao.deleteComm(id);
    }

    @Override
    public void updateAudit2(@RequestBody Comm comm) {
        commDao.updateAudit2(comm);
    }

    @Override
    public void updateAudit1(Comm comm) {
        commDao.updateAudit1(comm);
    }


    @Override
    public int deleteAllComm(String ids) {
        String[] split = ids.split(",");
        return commDao.deleteAllComm(split);
    }


}