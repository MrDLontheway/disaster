package io.renren.modules.api;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.renren.common.utils.R;
import io.renren.modules.roat.dao.RoatMapper;
import io.renren.modules.roat.entity.Roat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private RoatMapper roatMapper;

    @RequestMapping("/restapi/getRoats")
    @ResponseBody
    public R getRoats(){
        EntityWrapper<Roat> wrapper = new EntityWrapper<>();
        wrapper.orderBy("sort");
        List<Roat> roats = roatMapper.selectList(wrapper);
        return R.ok().put("roats",roats);
    }
}
