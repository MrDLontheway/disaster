package io.renren.modules.roat.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.roat.entity.Roat;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoatMapper extends BaseMapper<Roat> {

    @Select(value = "select * from roat")
    public List<Roat> mySelectAll();
}
