package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.UserRateEntity;

import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-09-14 10:38:42
 */
public interface UserRateService extends IService<UserRateEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

