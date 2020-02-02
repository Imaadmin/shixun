package com.hadoop.shixun.service;

import com.hadoop.shixun.mapper.BusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author 736421789@qq.com
 * @date 2020/1/4 15:39
 */
@Service
public class BusService {
    @Autowired
    private BusMapper busMapper;

    public List<Map> findCityBus() { return busMapper.findCityBus();
    }
}
