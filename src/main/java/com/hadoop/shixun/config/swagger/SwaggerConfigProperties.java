package com.hadoop.shixun.config.swagger;


import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Data
@ToString
@Validated
@ConfigurationProperties(prefix = "lb.swagger")
public class SwaggerConfigProperties {

    private boolean enabled = false;
    private String pathRegex = "/.*";
    private String pathMapping = "/";
    private String basePackage = "com.school";

    private String termsOfServiceUrl="no content";       // 隐私条款说明URL地址
    private String contactName="school";             // 联系人姓名
    private String contactUrl="no url";              // 联系人URL
    private String contactEmail="736421789@qq.com";            // 联系人Email地址
    private String license="license info";                 // License协议
    private String licenseUrl=" http://google.com/ncr";              // License协议地址

    private String version="1.0.0";     // API 版本号
    private String title="learn swagger";       // API 标题
    private String description="shixun description"; // API 详细描述信息



}
