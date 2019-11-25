package com.example.consuer.controller;

import com.example.consuer.service.ProviderDemoClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author cxiujin
 * @date 2019/11/25 16:38
 */
@Slf4j
@RequestMapping(value = "/demo")
@RestController
public class ConsumerController {

    /**使用RestTemplate方式消费服务*/
    @Autowired
    private RestTemplate restTemplate;

    /**使用FeignClient方式消费服务*/
    @Autowired
    private ProviderDemoClient providerDemoClient;

    @GetMapping(value = "/hello")
    public String consumerHello(@RequestParam(value = "name", defaultValue = "Nacos", required = false) String name){
        log.info("----------------开始消费hello----------------");
        return restTemplate.getForObject("http://nacos-provider/demo/hello?name="+name, String.class);
    }

    @GetMapping(value = "/hello-by-feign")
    public String helloByFeign(String name){
        log.info("----------------开始消费hello----------------");
        return providerDemoClient.hello("cccc");
    }


}
