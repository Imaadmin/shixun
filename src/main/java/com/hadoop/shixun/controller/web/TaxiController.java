package com.hadoop.shixun.controller.web;

import com.hadoop.shixun.controller.web.Api.TaxiApi;
import com.hadoop.shixun.service.TaxiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author 736421789@qq.com
 * @date 2020/1/3 21:38
 */
@Slf4j
@RestController
@RequestMapping("taxi")
public class TaxiController extends TaxiApi {
    @Autowired
    private TaxiService taxiService;



    @Override
    protected String get() {
        return null;
    }

    @Override
    @GetMapping("/findCityTaxi")
    protected String findCityTaxi() {
        List<Map> dto =  taxiService.getAllCityTaxi();
        return toJsonP(dto);
    }


}
