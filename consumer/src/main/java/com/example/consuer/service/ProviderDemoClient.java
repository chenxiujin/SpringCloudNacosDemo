package com.example.consuer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author cxiujin
 * @date 2019/11/25 17:00
 */
@FeignClient(value = "nacos-provider")
public interface ProviderDemoClient {

    @GetMapping(value = "/demo/hello")
    String hello(@RequestParam(value = "name", defaultValue = "Nacos", required = false) String name);
}
