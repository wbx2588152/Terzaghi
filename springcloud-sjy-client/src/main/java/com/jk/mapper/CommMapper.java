package com.jk.mapper;

import com.jk.model.Comm;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommMapper {
    long queryComm(@Param("comm") Comm comm);

    List<Comm> queryCoomList(@Param("st")int start, @Param("end")int end, @Param("comm")Comm comm);

    void saveComm(@Param("comm") Comm comm);

    Comm queryCommById(@Param("id") String id);

    void updateComm(Comm comm);

    void deleteComm(@Param("id")String id);

    int deleteAllComm(@Param("ids")String[] split);


    void updateAudit2(Comm comm);

    void updateAudit1(Comm comm);
}
