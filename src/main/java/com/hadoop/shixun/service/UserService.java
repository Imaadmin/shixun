package com.hadoop.shixun.service;

import com.hadoop.shixun.Dto.UserDTO;
import com.hadoop.shixun.Vo.UserVO;
import com.hadoop.shixun.mapper.UserMapper;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 736421789@qq.com
 * @date 2020/1/3 20:39
 */

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public UserDTO userLogin(UserVO user){
        UserDTO userDTO = userMapper.selectByName(user);
        if(userDTO != null){
            if(userDTO.getPassword().equals(user.getPassword())){
                return userDTO;
            }else {
                return null;
            }
        }
        return  null;
}

    //通过用户名查找用户
    public UserDTO selectByName(UserVO userParams) {
        UserDTO userDTO = userMapper.selectByName(userParams);
        if(userDTO ==null){
            return null;
        }
        return userDTO;
    }

    public void insertUser(UserVO userParams) {
        userMapper.insertUser(userParams);
    }
}
