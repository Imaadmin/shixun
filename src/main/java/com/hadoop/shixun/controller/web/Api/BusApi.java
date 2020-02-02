package com.hadoop.shixun.controller.web.Api;

import com.hadoop.shixun.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author 736421789@qq.com
 * @date 2020/1/4 15:38
 */
@Api(tags = "公交车相关接口")
public abstract class BusApi extends BaseController {

    @ApiOperation(value = "李磊   #2020年1月4日10:45:33#  查询某市所有公共车辆位置", protocols = "application/json")
    protected abstract String findCityBus();
}
