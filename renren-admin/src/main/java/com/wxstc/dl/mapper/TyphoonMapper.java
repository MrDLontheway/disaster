package com.wxstc.dl.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.roat.entity.Typhoon;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TyphoonMapper extends BaseMapper<Typhoon> {
    List selectAllInfo(@Param("search") String search, @Param("sort") String sort, @Param("order") String order);
}
