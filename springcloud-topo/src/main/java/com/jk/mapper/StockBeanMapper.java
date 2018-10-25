package com.jk.mapper;


import com.jk.model.ProbabilityBean;
import com.jk.model.StockBean;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface StockBeanMapper {



@Select(" SELECT * FROM  t_stock   limit #{page},#{limit} ")
    List<StockBean> getUserList(@Param("name")String name, @Param("page")int page, @Param("limit")int limit);

    @Select(" select count(stockid) count from t_stock ")
    int getUserCount();

    @Delete("  DELETE FROM t_stock WHERE  stockid =#{stockid} ")
    int delete(@Param("stockid") Integer stockid);

    @Select(" select * from t_probability  ")
    List<ProbabilityBean> suiji();

    @Select(" select count(id) count from t_probability ")
    int getlunpanCount();

    @Select(" SELECT * FROM  t_probability   limit #{page},#{limit} ")
    List<ProbabilityBean> getLunpanList(@Param("name")String name, @Param("page")int page, @Param("limit") int limit);

    @Select(" select * from t_probability WHERE  id = ${id} ")
    ProbabilityBean getUserById(@Param("id")Integer id);

    @Update(" UPDATE t_probability  SET    name = #{name} ,probability = #{probability}  WHERE  id = #{id} ")
    int update(ProbabilityBean probabilityBean);


}