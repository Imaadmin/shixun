package com.hadoop.shixun.mapper;

import com.hadoop.shixun.Dto.LbsDTO;
import com.hadoop.shixun.Dto.LbstbDTO;
import com.hadoop.shixun.Dto.ProvinceDTO;
import com.hadoop.shixun.entity.Provinces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author 736421789@qq.com
 * @date 2020/1/3 21:45
 */
@Component
public class LbsMapper {
    @Autowired
    private MongoTemplate mongoTemplate;
    // 自定义连接名称
    public static final String COLLECTION_NAME = "lbs_tb";
    public static final String COLLECTION_pr = "Provinces";


    public List<LbsDTO> getAllLbs() {
        Query query = new Query();
        query.fields().include("info5").include("info6").include("info10").exclude("_id");
//        query.limit(1000);
//        System.out.println(mongoTemplate.count(query,COLLECTION_NAME));
        return mongoTemplate.find(query, LbsDTO.class,COLLECTION_NAME);
    }


    public List<LbsDTO> getLbsByprovince(String province) {
/*        //完全匹配
        Pattern pattern = Pattern.compile("^张$", Pattern.CASE_INSENSITIVE);
//右匹配
        Pattern pattern = Pattern.compile("^.*张$", Pattern.CASE_INSENSITIVE);
//左匹配
        Pattern pattern = Pattern.compile("^张.*$", Pattern.CASE_INSENSITIVE);*/
//模糊匹配
        Pattern pattern = Pattern.compile("^.*"+province+".*$", Pattern.CASE_INSENSITIVE);
        Query query = Query.query(Criteria.where("info10").regex(pattern));

        return mongoTemplate.find(query, LbsDTO.class,COLLECTION_NAME);
    }

    public void addProvinces(Provinces provinces) {
        mongoTemplate.save(provinces, COLLECTION_pr);
    }


    public List<Provinces> GetAllProvinces() {
        Query query = new Query();
        return mongoTemplate.find(query,Provinces.class, COLLECTION_pr);
    }


    public List<ProvinceDTO> getlbscountByProvince(long start,long end) {

        Query query = new Query();
        query.skip(0).limit(5000);
        mongoTemplate.find(query,Provinces.class, COLLECTION_pr);
        return null;
    }


    public Map<String,Long> getNationwideByProvince() {
        long startPage = 0L;
        long pageSize = 5000L;
        long totalPages = mongoTemplate.count(new Query(),COLLECTION_NAME);
        //long totalPages = 500L;
        Query query = new Query();
        Map<String,Long> datas = new HashMap<>();
        List<LbstbDTO> list;
        while (startPage<=totalPages){
            //投影
            query.fields().include("info10").exclude("_id");
            query.skip(startPage).limit((int)pageSize);
            list = mongoTemplate.find(query,LbstbDTO.class,COLLECTION_NAME);
            for(LbstbDTO item : list){
                String name = item.getInfo10();
                name = name.substring(0,name.indexOf("省")+1);
                if(name.isEmpty()){
                    continue;
                }
                if(datas.containsKey(name)){
                    datas.put(name,datas.get(name)+1);
                }else{
                    datas.put(name,1L);
                }
            }
            startPage += pageSize;
        }
        return datas;
    }


    public Map<String, Long> provinceByCityStatistics(String provinceName) {
        long startPage = 0L;
        long pageSize = 5000L;
        long totalPages;
        //long totalPages = 500L;
        Map<String,Long> datas = new HashMap<>();
        List<LbstbDTO> list = null;
        Pattern pattern = Pattern.compile((provinceName+".*$"), Pattern.CASE_INSENSITIVE);
        Query query = Query.query(Criteria.where("info10").regex(pattern));
        totalPages = mongoTemplate.count(query,COLLECTION_NAME);
        while (startPage<=totalPages){
            //投影
            query.fields().include("info10").exclude("_id");
            query.skip(startPage).limit((int)pageSize);
            list = mongoTemplate.find(query,LbstbDTO.class,COLLECTION_NAME);
            for(LbstbDTO item : list){
                String name = item.getInfo10();
                if(name.indexOf("市")<0){
                    name = "其它市";
                }else{
                    name = name.substring(name.indexOf("省")+1,name.indexOf("市")+1);
                }
                if(datas.containsKey(name)){
                    datas.put(name,datas.get(name)+1);
                }else{
                    datas.put(name,1L);
                }
            }
            startPage += pageSize;
        }
        return datas;
    }
}
