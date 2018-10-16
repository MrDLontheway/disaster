package io.renren.modules.sys.controller;

import java.util.Arrays;
import java.util.Map;

import io.renren.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.sys.entity.UserRateEntity;
import io.renren.modules.sys.service.UserRateService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-09-14 10:38:42
 */
@RestController
@RequestMapping("sys/userrate")
public class UserRateController {
    @Autowired
    private UserRateService userRateService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:userrate:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = userRateService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{die}")
    @RequiresPermissions("sys:userrate:info")
    public R info(@PathVariable("die") Long die){
        UserRateEntity userRate = userRateService.selectById(die);

        return R.ok().put("userRate", userRate);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:userrate:save")
    public R save(@RequestBody UserRateEntity userRate){
        userRateService.insert(userRate);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:userrate:update")
    public R update(@RequestBody UserRateEntity userRate){
        ValidatorUtils.validateEntity(userRate);
        userRateService.updateAllColumnById(userRate);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:userrate:delete")
    public R delete(@RequestBody Long[] dies){
        userRateService.deleteBatchIds(Arrays.asList(dies));

        return R.ok();
    }

}
