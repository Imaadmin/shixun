package com.hadoop.shixun.demo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 * @author 736421789@qq.com
 * @date 2020/1/3 14:47
 */
public class lianjieDemo {

    public static void main(String[] args) {
        MongoClient mongoClient = new MongoClient("49.235.151.230",27017);
        MongoDatabase mongoDatabase = mongoClient.getDatabase("taxi");
        System.out.println("成功");
    }

}
