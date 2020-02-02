package com.hadoop.shixun.demo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.val;
import org.bson.Document;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 736421789@qq.com
 * @date 2020/1/3 15:17
 */
public class daoru {
    public static void main(String[] args)   {
        String url ="C:\\Users\\73642\\Desktop\\shuju\\1\\12.txt";
        MongoClient mongoClient = new MongoClient("49.235.151.230", 27017);
        MongoDatabase mongoDatabase = mongoClient.getDatabase("lbs");
        MongoCollection<Document> mongoCollection = mongoDatabase.getCollection("taxi");

        //文件转换流
        File file = new File(url);
        BufferedReader bufferedInputStream = null;
        try {
            bufferedInputStream = new BufferedReader(new FileReader(file));
            //定义表头
            List<String> head = new ArrayList<>();

            head.add("number");
            head.add("worktime");
            head.add("longitude");
            head.add("latitude");
            head.add("info5");
            head.add("info6");
            head.add("workstate");

            List<Document> docs = new ArrayList<>();
            //解析数据并导入
            String tempString =null;
            while ((tempString = bufferedInputStream.readLine())!=null){
                String[] split = tempString.split(",");
                Document doc = new Document();
                for (int i=0;i<head.size();i++){
//                    System.out.println(split[i]);
                    doc.put(head.get(i),split[i]);
                }
                //将数据加入到文本中
                docs.add(doc);
            }
            //关闭流
            bufferedInputStream.close();
            //把数据一次性插入mangdb
            mongoCollection.insertMany(docs);
        }catch (FileNotFoundException e){

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
