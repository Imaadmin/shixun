package com.hadoop.shixun.controller.web.Api;


import com.hadoop.shixun.Vo.UserVO;
import com.hadoop.shixun.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

@Api(tags = "用户相关接口")
public abstract class UserApi extends BaseController {


    @ApiOperation(value = "王馨萍 #2020年1月3日10:45:33#  用户登录", notes = "用户登录", protocols = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", dataType = "String", paramType = "query"),
    })
    protected abstract String UserLogin(@RequestBody(required = false) UserVO userParams, HttpServletRequest request);


    @ApiOperation(value = "王馨萍 #2020年1月4日11:17:21#  用户注册", notes = "用户注册", protocols = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", dataType = "String", paramType = "query"),
    })
    protected abstract String UserReigst(@RequestBody(required = false) UserVO userParams, HttpServletRequest request);
}
