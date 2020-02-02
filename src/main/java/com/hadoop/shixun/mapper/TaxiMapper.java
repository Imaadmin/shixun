package com.hadoop.shixun.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author 736421789@qq.com
 * @date 2020/1/3 21:45
 */

@Component
public class TaxiMapper {
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * taxi容器
     */
    public static final String COLLECTION_NAME = "taxi";

    public List<Map> getAllCityTaxi() {
        Query query = new Query();
        query.fields().include("longitude").include("latitude").exclude("_id");
        query.skip(0).limit(20000);
        return mongoTemplate.find(query,Map.class,COLLECTION_NAME);
    }
}
