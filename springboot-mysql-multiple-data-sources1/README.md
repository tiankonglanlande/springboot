# 多数据源分包加载，此demo还没有验证通过，请不要使用！

###  新建数据库test1和表tbl_user
```sql
CREATE TABLE `tbl_user` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
```
###  新建数据库test2和表tbl_order
```sql
CREATE TABLE `tbl_order` (
  `id` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
```

### 数据源配置application.properties文件
```properties
## test1 database 配置
spring.datasource.test1.jdbc-url=jdbc:mysql://localhost:3306/test1?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false
spring.datasource.test1.username=root
spring.datasource.test1.password=root
spring.datasource.test1.driver-class-name=com.mysql.jdbc.Driver
## test2 database 配置
spring.datasource.test2.jdbc-url=jdbc:mysql://localhost:3306/test2?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false
spring.datasource.test2.username=root
spring.datasource.test2.password=root
spring.datasource.test2.driver-class-name=com.mysql.jdbc.Driver

```

###  加载配置
主库配置DataSourceConfig1.java
```java

//表示这个类为一个配置类
@Configuration
// 配置mybatis的接口类放的地方
@MapperScan(basePackages = "com.yuanfenge.demo.dao.test01", sqlSessionFactoryRef = "test1SqlSessionFactory")
public class DataSourceConfig1 {
    // 将这个对象放入Spring容器中
    @Bean(name = "test1DataSource")
    // 表示这个数据源是默认数据源
    @Primary
    // 读取application.properties中的配置参数映射成为一个对象
    // prefix表示参数的前缀
    @ConfigurationProperties(prefix = "spring.datasource.test1")
    public DataSource getDateSource1() {
        return DataSourceBuilder.create().build();
    }
    @Bean(name = "test1SqlSessionFactory")
    // 表示这个数据源是默认数据源
    @Primary
    // @Qualifier表示查找Spring容器中名字为test1DataSource的对象
    public SqlSessionFactory test1SqlSessionFactory(@Qualifier("test1DataSource") DataSource datasource)
        throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(datasource);
        bean.setMapperLocations(
            // 设置mybatis的xml所在位置
            new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/test01/*.xml"));
        return bean.getObject();
    }
    @Bean("test1SqlSessionTemplate")
    // 表示这个数据源是默认数据源
    @Primary
    public SqlSessionTemplate test1sqlsessiontemplate(
        @Qualifier("test1SqlSessionFactory") SqlSessionFactory sessionfactory) {
        return new SqlSessionTemplate(sessionfactory);
    }
}
```
从库配置DataSourceConfig2.java
```java

@Configuration
@MapperScan(basePackages = "com.yuanfenge.demo.dao.test02", sqlSessionFactoryRef = "test2SqlSessionFactory")
public class DataSourceConfig2 {
    @Bean(name = "test2DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.test2")
    public DataSource getDateSource2() {
        return DataSourceBuilder.create().build();
    }
    @Bean(name = "test2SqlSessionFactory")
    public SqlSessionFactory test2SqlSessionFactory(@Qualifier("test2DataSource") DataSource datasource)
        throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(datasource);
        bean.setMapperLocations(
            new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/test02/*.xml"));
        return bean.getObject();
    }
    @Bean("test2SqlSessionTemplate")
    public SqlSessionTemplate test2sqlsessiontemplate(
        @Qualifier("test2SqlSessionFactory") SqlSessionFactory sessionfactory) {
        return new SqlSessionTemplate(sessionfactory);
    }
}
```
### 测试controller
```java

/**
 * @auth 猿份哥
 * @description
 * @createTime 2020 - 5 - 19 16:45
 */
@RestController
public class TestController {

    @Autowired
    TestService testService;

    @RequestMapping("/test")
    public Object test(){
        List<Order> list = testService.selectOrder();
        return list;
    }
    @RequestMapping("/test2")
    public Object test2(){
        int i = testService.saveOrder();
        String msg=i>0?"success":"failed";
        return msg;
    }

}
```
### 总结
分包加载不支持分布式事物，如果是多个数据库的事物@Transaction将会失效，所以使用这种方式要避免分布式事物。


