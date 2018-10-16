package com.wxstc.dl.mapper;

import io.renren.modules.roat.entity.Citymsq;
import io.renren.modules.roat.entity.CitymsqExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CitymsqMapper {
    int countByExample(CitymsqExample example);

    int deleteByExample(CitymsqExample example);

    int insert(Citymsq record);

    int insertSelective(Citymsq record);

    List<Citymsq> selectByExample(CitymsqExample example);

    int updateByExampleSelective(@Param("record") Citymsq record, @Param("example") CitymsqExample example);

    int updateByExample(@Param("record") Citymsq record, @Param("example") CitymsqExample example);
}