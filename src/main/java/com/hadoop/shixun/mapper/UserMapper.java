package com.hadoop.shixun.mapper;

import com.hadoop.shixun.Dto.UserDTO;
import com.hadoop.shixun.Vo.UserVO;
import com.hadoop.shixun.entity.Lbs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 736421789@qq.com
 * @date 2020/1/3 20:39
 */
@Component
public class UserMapper {
    @Autowired
    private MongoTemplate mongoTemplate;
    // 自定义连接名称
    public static final String COLLECTION_NAME = "admin";


    //通过用户名查找用户
    public UserDTO selectByName(UserVO userParams) {
        Query query = new Query(Criteria.where("username").is(userParams.getUsername()));
        return mongoTemplate.findOne(query,UserDTO.class,COLLECTION_NAME);
    }


    //新增用户
    public void insertUser(UserVO userParams) {
        mongoTemplate.save(userParams, COLLECTION_NAME);
    }
}
