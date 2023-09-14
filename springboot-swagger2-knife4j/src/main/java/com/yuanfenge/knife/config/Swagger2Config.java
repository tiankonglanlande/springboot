package com.yuanfenge.knife.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableKnife4j
public class Swagger2Config {


    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(true) //true-打开，false-关闭 如果有不同的环境在配置文件里控制然后将配置文件加载到这里
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.yuanfenge.knife.controller"))//swagger文档扫描包
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring boot2x快速整合swagger2（Open Api3注解版）")//文档标题
                .license("协议")
                .licenseUrl("https://www.lskyf.com")
                .contact(new Contact("猿份哥", "https://www.lskyf.com", "zswdxl_111@sina.com"))//文档联系人信息
                .version("1.0")//文档版本号
                .build();
    }
}
