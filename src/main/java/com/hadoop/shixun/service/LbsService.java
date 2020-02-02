package com.hadoop.shixun.service;

import com.hadoop.shixun.Dto.LbsDTO;
import com.hadoop.shixun.Dto.ProvinceDTO;
import com.hadoop.shixun.entity.Provinces;
import com.hadoop.shixun.mapper.LbsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author 736421789@qq.com
 * @date 2020/1/3 21:44
 */
@Service
public class LbsService {
    @Autowired
    private LbsMapper lbsMapper;


    public List<LbsDTO> getAllLbs() {
        List<LbsDTO> lbsDTOList = lbsMapper.getAllLbs();
        return lbsDTOList;
    }


    public List<LbsDTO> getLbsByprovince(String province) {
        List<LbsDTO> lbsDTOList = lbsMapper.getLbsByprovince(province);
        return lbsDTOList;
    }


    public void addProvinces(Provinces provinces) {
        lbsMapper.addProvinces(provinces);
    }


    public List<Provinces> GetAllProvinces() {
        return lbsMapper.GetAllProvinces();
    }


    public Map<String,Long> getNationwideByProvince() { return lbsMapper.getNationwideByProvince();
    }



    public Map<String, Long> provinceByCityStatistics(String provinceName) {
        return lbsMapper.provinceByCityStatistics(provinceName);
    }
}
