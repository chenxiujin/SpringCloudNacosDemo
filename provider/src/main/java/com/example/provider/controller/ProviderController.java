package com.example.provider.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
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

    /**
     * 关于@SentinelResource:哨兵监控注解,有四个属性
     *  value：资源名称，必需项（不能为空）
     *  entryType：entry 类型，可选项（默认为 EntryType.OUT）
     *  blockHandler / blockHandlerClass: blockHandler 对应处理 BlockException 的函数名称，可选项
     *  fallback：fallback 函数名称，可选项，用于在抛出异常的时候提供 fallback 处理逻辑。
     * @param name
     * @return
     */
    @GetMapping(value = "/hello")
    @SentinelResource(value="hello")
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
