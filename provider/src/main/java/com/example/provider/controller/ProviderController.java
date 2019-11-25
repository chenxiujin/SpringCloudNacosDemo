package com.example.provider.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *  使用nacos作为分布式配置中心 @RefreshScope实现热部署
 * @author cxiujin
 * @date 2019/11/25 16:17
 */
@Slf4j
@RequestMapping(value = "/demo")
@RestController
@RefreshScope
public class ProviderController {

    @Value(value = "${name:default-nacos}")
    private String name;

    @GetMapping(value = "/hello")
    public String helloNacos(@RequestParam(value = "name", defaultValue = "Nacos", required = false) String name){
        log.info("-------------------------nacos服务提供者生效------------------------");
        return "Hello "+name;
    }

    @GetMapping(value = "/get-name-yaml")
    public String getNameYaml(){
        log.info("-------------------------nacos配置中心生效------------------------");
        return name;
    }
}
