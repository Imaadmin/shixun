package com.hadoop.shixun.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author 736421789@qq.com
 * @date 2020/1/4 15:39
 */
@Component
public class BusMapper {
    public static final String COLLECTION_NAME = "bus";

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Map> findCityBus() {
        Query query = new Query();
        query.fields().include("info7").include("info8").exclude("_id");
        query.skip(0).limit(20000);
        return mongoTemplate.find(query,Map.class,COLLECTION_NAME);
    }
}
