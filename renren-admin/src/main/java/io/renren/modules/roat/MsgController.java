package io.renren.modules.roat;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.pagination.PageHelper;
import com.github.pagehelper.PageInfo;
import io.renren.modules.roat.entity.Roat;
import io.renren.modules.roat.dao.RoatMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

@Controller
public class MsgController {
    @Autowired
    private RoatMapper roatMapper;

    @RequestMapping("/msg/getAllMsgs")
    @ResponseBody
    public HashMap test(@RequestParam(defaultValue = "0") int offset,
                        @RequestParam(defaultValue = "10") int limit,
                        @RequestParam(defaultValue = "")String search,
                        @RequestParam(defaultValue = "")String order,
                        @RequestParam(defaultValue = "")String sort){
        HashMap result = new HashMap();
        try {
            PageHelper.startPage(offset/limit+1, limit);
            EntityWrapper<Roat> entityWrapper = new EntityWrapper<>();
            List<Roat> roats = roatMapper.mySelectAll();
            PageInfo pageInfo = new PageInfo(roats);
            result.put("total",pageInfo.getTotal());
            result.put("rows",pageInfo.getList());
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
