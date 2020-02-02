package com.hadoop.shixun.entity;

import lombok.Data;

/**
 * @author 736421789@qq.com
 * @date 2020/1/3 21:35
 */

@Data
public class Taxi {
    private String id;
    private String number;//车辆编号
    private String worktime;//工作时间
    private String longitude;//经度
    private String latitude;//纬度
    private String info5;//未知
    private String info6;//未知
    private String workstate;//工作状态
}
