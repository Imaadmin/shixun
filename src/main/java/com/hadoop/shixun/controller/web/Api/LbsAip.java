package com.hadoop.shixun.controller.web.Api;

import com.hadoop.shixun.Vo.UserVO;
import com.hadoop.shixun.controller.BaseController;
import com.hadoop.shixun.entity.Provinces;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 736421789@qq.com
 * @date 2020/1/3 21:42
 */
@Api(tags = "全国基站相关接口")
public abstract class LbsAip extends BaseController {


    @ApiOperation(value = "郭建伟 #2020年1月3日21:44:12#   获取所有基站", notes = "获取所有基站", protocols = "application/json")
    protected abstract String getAllLbs();


    @ApiOperation(value = "郭建伟 #2020年1月4日10:26:28#   按地区获取某省市基站", notes = "按地区获取某省市基站", protocols = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "province", value = "省市", dataType = "String", paramType = "query"),
    })
    protected abstract String getLbsByprovince(@RequestParam String province);


    @ApiOperation(value = "刘平 #2020年1月4日10:26:28#   统计指定省各市的数据", notes = "统计指定省各市的数据", protocols = "application/json")
    protected abstract String getNationwideByProvince();


/*    @ApiOperation(value = "刘平 #2020年1月4日10:26:28#   全市的基站个数", notes = "全市基站个数", protocols = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "province", value = "省市", dataType = "String", paramType = "query"),
    })
    protected abstract String getNationwideByProvince2(@RequestParam String province);*/


    @ApiOperation(value = "刘平 #2020年1月4日10:26:28#   根据省查询全市的基站个数", notes = "根据省查询全市的基站个数", protocols = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "provinceName", value = "省名", dataType = "String", paramType = "query"),
    })
    protected abstract String getProvinceByCity(@RequestParam String provinceName);

    @ApiOperation(value = "郭建伟 #2020年1月4日10:26:28#  增加省" , notes = "增加省", protocols = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "province", value = "省名", dataType = "String", paramType = "query"),
    })
    protected abstract String addProvinces(@RequestBody Provinces provinces);


    @ApiOperation(value = "郭建伟 #2020年1月4日10:26:28#  查询所有省", notes = "查询所有省", protocols = "application/json")
    protected abstract String GetAllProvinces();









}
