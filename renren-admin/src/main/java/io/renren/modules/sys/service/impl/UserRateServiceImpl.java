package io.renren.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.UserRateDao;
import io.renren.modules.sys.entity.UserRateEntity;
import io.renren.modules.sys.service.UserRateService;


@Service("userRateService")
public class UserRateServiceImpl extends ServiceImpl<UserRateDao, UserRateEntity> implements UserRateService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<UserRateEntity> page = this.selectPage(
                new Query<UserRateEntity>(params).getPage(),
                new EntityWrapper<UserRateEntity>()
        );

        return new PageUtils(page);
    }

}
