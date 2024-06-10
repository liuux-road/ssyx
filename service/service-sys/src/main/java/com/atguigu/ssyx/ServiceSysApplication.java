package com.atguigu.ssyx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * ClassName: ServiceSysApplication
 * Package: com.atguigu.ssyx.sys
 * Description:
 *
 * @Author liuux
 * @Create 2024/3/4 15:12
 * @Version 1.0
 */
@SpringBootApplication //        (scanBasePackages = "com.atguigu.ssyx") // 问题：自定义GlobalExceptionHandler异常拦截器一直不生效，添加扫描地址后，生效
@EnableDiscoveryClient //@EnableSwagger2WebMvc // Knife4j文档请求异常。。。网页查找结果
public class ServiceSysApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceSysApplication.class, args);
    }
}
