# Spring boot2x快速整合swagger2（Open Api3注解版）

###  前言：为什么要使用swagger

传统的web开发，前端和后端的HTTP接口文档交互都是使用word文档记录，存在不仅限于这些问题;不能时时更新，不易于传输etc.
swagger2可以使用配置文件的方式和注解的方式生成HTTP请求文档代码各有优缺点；可根据各自的情况选择。此处demo使用注解的方式


### 工程maven的pom文件引入swagger2的配置
```xml
     <!-- https://mvnrepository.com/artifact/io.springfox/springfox-swagger2 -->
     <dependency>
         <groupId>io.springfox</groupId>
         <artifactId>springfox-swagger2</artifactId>
         <version>2.9.2</version>
     </dependency>

     <!-- https://mvnrepository.com/artifact/io.springfox/springfox-swagger-ui -->
     <dependency>
         <groupId>io.springfox</groupId>
         <artifactId>springfox-swagger-ui</artifactId>
         <version>2.9.2</version>
     </dependency>



```
说明：springfox-swagger2是swagger2的基础API；springfox-swagger-ui是swagger的图形界面支撑API
###   
配置SwaggerConfig.java装载配置
```java

@Configuration
@EnableSwagger2
public class Swagger2Config {


    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.yuanfenge.demo.controller"))//swagger文档扫描包
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("Spring boot2x快速整合swagger2（Open Api3注解版）")//文档标题
                .license("协议")
                .licenseUrl("https://www.lskyf.com")
                .contact(new Contact("猿份哥","https://www.lskyf.com","zswdxl_111@sina.com"))//文档联系人信息
                .version("1.0")//文档版本号
                .build();
    }
}
```

### 用户请求参数类

```java
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value="用户model",description="请求参数类" )
public class User implements Serializable {

   @ApiParam("用户ID编号")
   private Integer id;
   @ApiParam("用户名")
   private String name;
   @ApiParam("年龄")
   private int age;
}

```
说明：@ApiModel 标注请求参数类
      @ApiParam 标注单个请求的参数
      @ApiImplicitParam：  标注一个请求参数
      @ApiImplicitParams： 标注多个请求参数
      @ApiOperation： 标注接口名称
### 测试controller
```java

/**
 * @auth 猿份哥
 * @description
 * @createTime 2020 - 8 - 2 14:436
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class TestController {

    @ApiOperation(value="根据id查询用户信息",notes = "id必需是数字")
    @ApiResponses({@ApiResponse(code=400,message="id不能为空")})
    @GetMapping("/info")
    public ResponseEntity info(@ApiParam(value = "ID编号",required = true) @RequestParam("id") Integer id){
        User user=User.builder()
                .id(id)
                .name("猿份哥")
                .age(18)
                .build();

        return  ResponseEntity.ok(user );
    }
    @ApiOperation("新增用户信息")
    @PostMapping("/add")
    public ResponseEntity user(User user){
        return ResponseEntity.ok(user);
    }

    @ApiOperation("根据id删除用户信息")
    @DeleteMapping("/del")
    public ResponseEntity<String> user(@ApiParam("ID编号") @RequestParam("id") Integer id){
        String message="删除id为"+id+"的用户";
        return ResponseEntity.ok(message);
    }

    @ApiOperation("根据id更新用户信息")
    @DeleteMapping("/update")
    public Object update(@ApiParam("ID编号") @RequestParam("id") Integer id){
        String message="删除id为"+id+"的用户";
        return ResponseEntity.ok(message);
    }

}
```

### 访问接口文档位置
默认的boot访问路径是：http://ip:端口/swagger-ui.html 如：http://localhost:8080/swagger-ui.html

### 总结

很多新东西都是其实已经不是新东西了，不仅官网存在demo特别是网上的活雷锋已经帮我们避免了许多坑。多向别人学习是多么重要啊！

[源码下载链接](https://github.com/tiankonglanlande/springboot)

[原文链接: https://www.lskyf.com/post/161](https://www.lskyf.com/post/161)

作者：猿份哥，版权所有，欢迎保留原文链接进行转载：)
