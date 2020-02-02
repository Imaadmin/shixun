package com.hadoop.shixun.controller.web;


import com.hadoop.shixun.Dto.UserDTO;
import com.hadoop.shixun.Vo.UserVO;
import com.hadoop.shixun.controller.web.Api.UserApi;
import com.hadoop.shixun.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequestMapping("user")
public class UserController extends UserApi {

    @Autowired
    private UserService userService;

    @Override
    @PostMapping("/Login")
    public String UserLogin(UserVO userParams, HttpServletRequest request) {

        //查询用户
        UserDTO user = userService.userLogin(userParams);
        if(user==null){
            return run_false("密码或账号错误");
        }
        HttpSession session = request.getSession();
        user.setPassword("");
        session.setAttribute("user",user);
        return toJsonP(user);
    }

    @Override
    @PostMapping("/Regist")
    protected String UserReigst(UserVO userParams, HttpServletRequest request) {
        //查询用户
        UserDTO user = userService.selectByName(userParams);
        if(user==null){
            userService.insertUser(userParams);
            return run_success("注册成功");
        }
        return run_false("用户名已存在");
    }

}
