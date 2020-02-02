package com.hadoop.shixun.service;

import com.hadoop.shixun.mapper.TaxiMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author 736421789@qq.com
 * @date 2020/1/3 21:46
 */
@Service
public class TaxiService {
    @Autowired
    private TaxiMapper taxiMapper;

    public List<Map> getAllCityTaxi() {
        List<Map> list= taxiMapper.getAllCityTaxi();
        return list;
    }
}
