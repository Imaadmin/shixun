package com.hadoop.shixun.service;



import com.hadoop.shixun.Dto.UserDTO;
import com.hadoop.shixun.entity.Lbs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class UserServicedemo {
    // 自定义连接名称
    public static final String COLLECTION_NAME = "user";
    // 自定义连接名称
    public static final String COLLECTION_NAME2 = "lbs_tb";

    @Autowired
    private MongoTemplate mongoTemplate;

    public void add(UserDTO dto) {
        mongoTemplate.save(dto, COLLECTION_NAME);
    }

    public List<Lbs> get() {
        Query query = new Query();

        return mongoTemplate.find(query,Lbs.class,COLLECTION_NAME2);
    }

    public UserDTO all() {
        Query query = new Query();
        return mongoTemplate.findOne(query, UserDTO.class, COLLECTION_NAME);
    }


    public void update(UserDTO dto) {
        Query query = new Query(Criteria.where("id").is(dto.getId()));
        mongoTemplate.findAndReplace(query, dto, COLLECTION_NAME);
    }

    public void delete(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query, UserDTO.class, COLLECTION_NAME);
    }
}

