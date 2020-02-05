package com.heima.article.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


/**
 * 出现注解错误，造成无法扫描到对应的对象信息，导致无法成功创建mapper
 * @author: czx
 * @create: 2020-02-03 12:10
 */
@Configuration
@ComponentScan("com.heima.common.mysql.core")
public class MySqlConfig {
}
