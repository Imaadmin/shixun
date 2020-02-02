package com.hadoop.shixun.controller.web;

import com.hadoop.shixun.Dto.LbsDTO;
import com.hadoop.shixun.Dto.ProvinceDTO;
import com.hadoop.shixun.controller.web.Api.LbsAip;
import com.hadoop.shixun.entity.Provinces;
import com.hadoop.shixun.service.LbsService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 全国基站
 * @author 736421789@qq.com
 * @date 2020/1/3 21:42
 */
@Slf4j
@RestController
@RequestMapping("lbs")
public class LbsController extends LbsAip {
    @Autowired
    private LbsService lbsService;

    @Override
    @GetMapping("/getAllLbs")
    protected String getAllLbs() {
        List<LbsDTO> lbsDTOList = lbsService.getAllLbs();
        return toJsonP(lbsDTOList);
    }

    @Override
    @GetMapping("/getLbsByprovince")
    protected String getLbsByprovince(String province) {
        if(isEmpty(province)){
            return run_false();
        }
        List<LbsDTO> lbsDTOList = lbsService.getLbsByprovince(province);
        return toJsonP(lbsDTOList);
    }

    @Override
    @GetMapping("/getlbscountByProvince")
    protected String getNationwideByProvince() {
        Map<String,Long> lbsByprovince = lbsService.getNationwideByProvince();
        return toJsonP(lbsByprovince);
    }


    @Override
    @GetMapping("/getProvinceByCity")
    protected String getProvinceByCity(String provinceName) {
        if(isEmpty(provinceName)){return run_false();}
        Map<String,Long> datas = lbsService.provinceByCityStatistics(provinceName);
        return toJsonP(datas);
    }


 /*   @Override
    @GetMapping("/getNationwideByProvince2")
    protected String getNationwideByProvince2(String province) {
        List<LbsDTO> lbsByprovince = lbsService.getLbsByprovince(province);
        for (LbsDTO lbsDTO:lbsByprovince) {
            System.out.println(lbsDTO.toString());
            lbsDTO.getInfo10();
        }
        return null;
    }
*/

    @Override
    @PostMapping("/addProvinces")
    protected String addProvinces(Provinces provinces) {
        lbsService.addProvinces(provinces);
        return run_success("添加成功");
    }

    @Override
    @GetMapping("/GetAllProvinces")
    protected String GetAllProvinces() {
        List<Provinces> list = lbsService.GetAllProvinces();

        return toJsonP(list);
    }
}
