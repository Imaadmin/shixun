package com.hadoop.shixun.controller.web;

import com.hadoop.shixun.controller.web.Api.BusApi;
import com.hadoop.shixun.service.BusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 公交车
 * @author 736421789@qq.com
 * @date 2020/1/4 15:38
 */
@Slf4j
@RestController
@RequestMapping("bus")
public class BusController extends BusApi {
    @Autowired
    private BusService busService;

    @Override
    @GetMapping("findCityBus")
    protected String findCityBus() {
        List<Map> dto = busService.findCityBus();
        return toJsonP(dto);
    }
}
