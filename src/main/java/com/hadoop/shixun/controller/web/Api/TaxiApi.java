package com.hadoop.shixun.controller.web.Api;

import com.hadoop.shixun.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @author 736421789@qq.com
 * @date 2020/1/3 21:38
 */

@Api(tags = "出租车相关接口")
public abstract class TaxiApi extends BaseController {

    @ApiOperation(value = "查询所有出租车数据 #2020年1月3日21:41:44#", notes = "查询所有出租车数据", protocols = "application/json")
    protected abstract String get();

    @ApiOperation(value = "李磊 #2020年1月4日10:45:33# 查询某市所有车辆位置", protocols = "application/json")
    protected abstract String findCityTaxi();

}
