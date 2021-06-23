#### 基础依赖
pom.xml文件引入依赖文件spring-boot-starter-webflux，是整个项目webflux的支持
```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-webflux</artifactId>
    </dependency>

    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
    </dependency>
</dependencies>
```
#### 数据准备
定义学生实体类：包括姓名和年龄属性
```java

/**
 * @author 猿份哥
 * @description
 * @createTime 2019/1/29 22:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {

    private String name;
    private int age;
}
```

StuService.java定义了一个查询单个学生获取的方法selectSimple，和学生列表获取的方法selectList
```java
/**
 * @author 猿份哥
 * @description
 * @createTime 2019/1/29 22:36
 */
@Service
public class StuService {

    public Student selectSimple(){
        return Student.builder()
                .name("猿份哥")
                .age(20)
                .build();
    }

    public List<Student> selectList(){
        List<Student> datas=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Student item=Student.builder()
                    .name("猿份哥"+i)
                    .age(20+i)
                    .build();
            datas.add(item);
        }
        return datas;
    }
}
```
#### 按照传统方法的思路定义Controller直接调用
FluxController.java
```java

/**
 * @author 猿份哥
 * @description
 * @createTime 2019/1/29 22:34
 */
@RestController
@RequestMapping("/student")
public class FluxController {

    @Autowired
    private StuService stuService;

    @RequestMapping("/item")
    public Mono<Student> item(){
        return Mono.just(stuService.selectSimple());
    }

    @RequestMapping("/list")
    public Flux<Student> list(){
        return Flux.fromIterable(stuService.selectList());
    }
}
```
说明：item方法是调用业务层单个数据，使用Mono返回；list是调用多个数据，使用Flux返回

浏览器输入：http://localhost:8080/student/item

返回结果：单个学生信息
```json
{
    "name": "猿份哥",
    "age": 20
}
```
浏览器输入：http://localhost:8080/student/list
返回结果：学习列表
```json
[
    {
        "name": "猿份哥0",
        "age": 20
    },
    {
        "name": "猿份哥1",
        "age": 21
    }
]
```

#### 使用路由的方式调用
步骤：

1.定义handler

StudentHandler.java
```java
/**
 * @author 猿份哥
 * @description
 * @createTime 2019/1/29 22:57
 */
@Component
public class StudentHandler {
    @Autowired
    private StuService stuService;

    public Mono<ServerResponse> getStuSimple(ServerRequest serverRequest){
        return ok().contentType(MediaType.APPLICATION_JSON)
        .body(Mono.just(stuService.selectSimple()),Student.class);
    }

    public Mono<ServerResponse> getList(ServerRequest serverRequest){
        return ok().contentType(MediaType.APPLICATION_JSON)
                .body(Flux.fromIterable(stuService.selectList()),Student.class);
    }
}
```
说明：

ok()：是ServerResponse的静态方法需要导入import static org.springframework.web.reactive.function.server.ServerResponse.ok;
也可以直接ServerResponse.ok()方式调用

contentType:响应数据格式

Mono.just：返回单条数据信息

Flux.fromIterable：返回多条数据信息

2.定义router

RouteConfig.java
```java
/**
 * @author 猿份哥
 * @description
 * @createTime 2019/1/29 22:54
 */
@Configuration
public class RouteConfig {
    @Autowired
    private StudentHandler studentHandler;

    @Bean
    public RouterFunction<ServerResponse> stuRouter(){
        return route(GET("/stu"),serverRequest -> studentHandler.getStuSimple(serverRequest));
    }

    @Bean
    public RouterFunction<ServerResponse> listRouter(){
        return route(GET("/students"),serverRequest -> studentHandler.getList(serverRequest));
    }
}
```
说明：

route：静态方法导入import static org.springframework.web.reactive.function.server.RouterFunctions.route;

GET：接收GET请求和访问路径如："/stu"静态方法导入import static org.springframework.web.reactive.function.server.RequestPredicates.GET;


3.测试访问

浏览器输入：http://localhost:8080/stu

返回结果：单个学生信息
```json
{
    "name": "猿份哥",
    "age": 20
}
```
浏览器输入：http://localhost:8080/students

返回结果：学习列表
```json
[
    {
        "name": "猿份哥0",
        "age": 20
    },
    {
        "name": "猿份哥1",
        "age": 21
    }
]
```
[源码下载链接](https://github.com/tiankonglanlande/springboot)

[原文链接: https://www.lskyf.com/post/37](https://www.lskyf.com/post/37)

作者：猿份哥，版权所有，欢迎保留原文链接进行转载：)


