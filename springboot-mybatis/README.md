### 创建数据库表

```sql
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- 创建student表
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `parent_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '家长电话号码逗号分隔',
  `city` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '居住城市json格式存储{\"province\":\"广东省\",\"city\":\"深圳市\",\"district\":\"南山区\"}',
  `net_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '网名逗号分隔',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- 插入学生信息
-- ----------------------------
INSERT INTO `student` VALUES (1, '小英', '13222222222,13333333333,15777777777', '{\"province\":\"广东省\",\"city\":\"深圳市\",\"district\":\"南山区\"}', '会飞的鱼,小小雪,茉莉香果果');
INSERT INTO `student` VALUES (2, '小明', '13222222222,13333333333,15777777777', '{\"province\":\"广东省\",\"city\":\"深圳市\",\"district\":\"南山区\"}', '会飞的鱼2,小小雪2,茉莉香果果2');

SET FOREIGN_KEY_CHECKS = 1;

```
### xml方式

#### 在pom.xml添加mybatis，druid，mysql驱动配置

```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <dependency>
        <groupId>org.mybatis.spring.boot</groupId>
        <artifactId>mybatis-spring-boot-starter</artifactId>
        <version>1.3.2</version>
    </dependency>

    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
    </dependency>

    <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>druid-spring-boot-starter</artifactId>
        <version>1.1.10</version>
    </dependency>
    
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
    </dependency>

```

#### 在application.yml中配置mybatis
```yml
# 使用druid数据源
spring:
    datasource:
        druid:
          driver-class-name: com.mysql.jdbc.Driver
          filters: stat
          maxActive: 20
          initialSize: 1
          maxWait: 60000
          minIdle: 1
          timeBetweenEvictionRunsMillis: 60000
          minEvictableIdleTimeMillis: 300000
          validationQuery: select 'x'
          testWhileIdle: true
          testOnBorrow: false
          testOnReturn: false
          poolPreparedStatements: true
          maxOpenPreparedStatements: 20
          db-type: com.alibaba.druid.pool.DruidDataSource
    thymeleaf:
      cache: false

mybatis:
  type-handlers-package:  com.tiankonglanlande.cn.springboot.mybatis.typehandler
  mapperLocations: classpath:mapper/**.xml
  typeAliasesPackage:  com.tiankonglanlande.cn.springboot.mybatis.bean
  # 开启自动映射
  configuration:
    map-underscore-to-camel-case: true
    lazy-loading-enabled: false
    auto-mapping-behavior: full
```
#### 数据库信息相关配置
```properties
spring.datasource.url = jdbc:mysql://localhost:3306/school?useUnicode=true&characterEncoding-utf8&allowMultiQueries=true
spring.datasource.username= root
spring.datasource.password= root
```
#### 启动文件相关配置
```java
@SpringBootApplication
@MapperScan("com.tiankonglanlande.cn.springboot.mybatis.dao")
public class MybatisDruidApplication {

	public static void main(String[] args) {
		SpringApplication.run(MybatisDruidApplication.class, args);
	}
}
```
说明：@MapperScan扫描注入指定的包名下Mapper接口注入容器

#### 编写数据库表对应的Student实体类
```java
/**
 * 学生实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student implements Serializable {

    private String id;
    private String name;
    private String parentPhone;
    private String city;
    private String netName;
}

```
#### 编写StudentDao Mapper接口
```java
public interface StudentDao {

    /**
     * 查询所有的学生信息
     * @return
     */
    List<Student> selectStudentList();
    
}
```
#### 编写StudentDao.xml Mapper.xml
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="com.tiankonglanlande.cn.springboot.mybatis.dao.StudentDao" >

    <select id="selectStudentList" resultType="Student">
        SELECT * FROM student
    </select>

</mapper>
```
#### 编写StudentService
```java
@Service
public class StudentService {
    @Autowired
    private StudentDao studentDao;
    public List<Student> selectStudentList(){
        return studentDao.selectStudentList();
    }
}

```
#### 编写StudentController
```java
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping("/students")
    public List<Student> selectStudentList(){
        List<Student> students = studentService.selectStudentList();
        return students;
    }
}

```
#### 在浏览器访问：http://localhost:8080/students得到结果
```json
[
  {
    "id": "1",
    "name": "小英",
    "parentPhone": "13222222222,13333333333,15777777777",
    "city": "{\"province\":\"广东省\",\"city\":\"深圳市\",\"district\":\"南山区\"}",
    "netName": "会飞的鱼,小小雪,茉莉香果果"
  },
  {
    "id": "2",
    "name": "小明",
    "parentPhone": "13222222222,13333333333,15777777777",
    "city": "{\"province\":\"广东省\",\"city\":\"深圳市\",\"district\":\"南山区\"}",
    "netName": "会飞的鱼2,小小雪2,茉莉香果果2"
  }
]

```
###  使用纯注解方式
#### StudentDao添加方法selectStudentListByAnnotation
```java
    @Select("SELECT * FROM student")
    List<Student> selectStudentListByAnnotation();
```
#### StudentService添加方法selectStudentListByAnnotation
```java
public List<Student> selectStudentListByAnnotation(){
        return studentDao.selectStudentListByAnnotation();
    }
```
#### StudentController調用
```java
@RequestMapping("/students2")
    public List<Student> selectStudentListByAnnotation(){
        List<Student> students = studentService.selectStudentListByAnnotation();
        return students;
    }
```
#### 在浏览器访问：http://localhost:8080/students得到与xml访问相同的结果
```json
[
  {
    "id": "1",
    "name": "小英",
    "parentPhone": "13222222222,13333333333,15777777777",
    "city": "{\"province\":\"广东省\",\"city\":\"深圳市\",\"district\":\"南山区\"}",
    "netName": "会飞的鱼,小小雪,茉莉香果果"
  },
  {
    "id": "2",
    "name": "小明",
    "parentPhone": "13222222222,13333333333,15777777777",
    "city": "{\"province\":\"广东省\",\"city\":\"深圳市\",\"district\":\"南山区\"}",
    "netName": "会飞的鱼2,小小雪2,茉莉香果果2"
  }
]

```
### 解决上面返回json数据的问题
从上面json的数据可以看出parentPhone和netName是多条数据通过逗号拼接成的一个字段，还有city是json字符串组成的文本
这样前端拿到数据可能还需要进行处理成集合再遍历出来；city是一个json字符串文本，要想转化成json还需要一番周折，
那么作为认真负责的后台开发的我们可以使用Mybatis的TypeHandler将parentPhone和netName转化成集合，将city转化成标准的json方便前端绑定数据。

#### 处理逗号拼接的字符串为集合
首先我们看之前在application.yml中配置mybatis的一段代码
```yml
mybatis:
  type-handlers-package:  com.tiankonglanlande.cn.springboot.mybatis.typehandler
```
说明：这一段代码会扫描com.tiankonglanlande.cn.springboot.mybatis.typehandler包下面的TypeHandler注入到spring容器

#### 修改一下Student实体属性为集合
```java
/**
 * 学生实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student implements Serializable {

    private String id;
    private String name;
    private String[] parentPhone;//此处修改为数组对象
    private String city;
    private String[] netName;//此处修改为数组对象
}
```
#### 自定义typehandler
```java
/**
 * 字符串转int数组
 */
public class StringArrayTypeHandler extends BaseTypeHandler<String[]>{
    private static final String delimiter=",";

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, String[] strings, JdbcType jdbcType) throws SQLException {
        List<String> list=new ArrayList<>();
        for (String item:strings){
            list.add(String.valueOf(item));
        }
        preparedStatement.setString(i,String.join(delimiter,list));
    }

    @Override
    public String[] getNullableResult(ResultSet resultSet, String s) throws SQLException {
        String str=resultSet.getString(s);
        if (resultSet.wasNull()){
            return null;
        }
        return str.split(delimiter);
    }


    @Override
    public String[] getNullableResult(ResultSet resultSet, int i) throws SQLException {
        String str= resultSet.getString(i);
        if (resultSet.wasNull()){
            return null;
        }
        return str.split(delimiter);
    }

    @Override
    public String[] getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        String str= callableStatement.getString(i);
        if (callableStatement.wasNull()){
            return null;
        }
        return str.split(delimiter);
    }
}

```
说明：setNonNullParameter方法会在保存数据库之前执行，我们在此方法将保存的数组使用逗号拼接还原数据库存储方式
其他方法是从数据库取出数据时mybatis将把数据映射成实体类执行，此时我们将逗号拼接的字符串转换为数组形式

#### 验收逗号拼接字符串转换为数组成果
浏览器输入http://localhost:8080/students可以看到原先逗号拼接的字符串已经转换为集合对象
```json
[
  {
    "id": "1",
    "name": "小英",
    "parentPhone": [
      "13222222222",
      "13333333333",
      "15777777777"
    ],
    "city": "{\"province\":\"广东省\",\"city\":\"深圳市\",\"district\":\"南山区\"}",
    "netName": [
      "会飞的鱼",
      "小小雪",
      "茉莉香果果"
    ]
  },
  {
    "id": "2",
    "name": "小明",
    "parentPhone": [
      "13222222222",
      "13333333333",
      "15777777777"
    ],
    "city": "{\"province\":\"广东省\",\"city\":\"深圳市\",\"district\":\"南山区\"}",
    "netName": [
      "会飞的鱼2",
      "小小雪2",
      "茉莉香果果2"
    ]
  }
]
```
#### 解决最后一个问题：将city转化成标准的json方便前端绑定数据
定义CityBean
```java

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityBean implements Serializable{
    private String province;
    private String city;
    private String district;
}

```

修改Student将CityBean作为他的属性
```java

/**
 * 学生实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student implements Serializable {

    private String id;
    private String name;
    private String[] parentPhone;//此处修改为数组对象
    private CityBean city;//修改为CityBean类
    private String[] netName;//此处修改为数组对象
}
```
定义JsonCityBeanTypeHandler
```java

public class JsonCityBeanTypeHandler extends BaseTypeHandler<CityBean> {
    public ObjectMapper objectMapper=new ObjectMapper();

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, CityBean t, JdbcType jdbcType) throws SQLException {
        try {
            ps.setString(i,objectMapper.writeValueAsString(t));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public CityBean getNullableResult(ResultSet resultSet, String s) throws SQLException {
        String str=resultSet.getString(s);
        if (resultSet.wasNull()){
            return null;
        }
        return getBean(str);
    }

    @Override
    public CityBean getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return null;
    }

    @Override
    public CityBean getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return null;
    }

    private CityBean getBean(String str){
        if (StringUtils.isEmpty(str))
            return null;
        try {
            return objectMapper.readValue(str,CityBean.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

```

如果是存储的是json集合字符串可以这样

修改student表添加一个visited字段
```sql

alter table student add visited varchar(256) COMMENT '去过的地方jsonarray存储[{"province":"广东省","city":"深圳市","district":"南山区"}]';
UPDATE student SET visited='[{"province":"四川省","city":"深圳市","district":"南山区"}]' WHERE id=1;
UPDATE student SET visited='[{"province":"四川省","city":"深圳市","district":"南山区"},{"province":"广东省","city":"成都市","district":"青羊区"}]' WHERE id=2;
```
定义VisitedBean
```java
/**
 * 去过的地方
 */
public class VisitedBean extends CityBean{

}
```
定义JsonListTypeHandler
```java

/**
 * json集合字符串转集合
 * @param <T>
 */
public class JsonListTypeHandler<T> extends BaseTypeHandler<List<T>> {
    public static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i,  List<T> parameter, JdbcType jdbcType) throws SQLException {
        try {
            ps.setString(i, objectMapper.writeValueAsString(parameter));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<T> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String str = rs.getString(columnName);
        if (rs.wasNull())
            return null;

        return getBeanList(str);
    }

    @Override
    public List<T> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String str = rs.getString(columnIndex);
        if (rs.wasNull())
            return null;

        return getBeanList(str);
    }

    @Override
    public List<T> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String str = cs.getString(columnIndex);
        if (cs.wasNull())
            return null;

        return getBeanList(str);
    }

    private List<T> getBeanList(String str) {
        if (StringUtils.isEmpty(str)){
            return null;
        }
        try {
            List<T> beanList =objectMapper.readValue(str,new TypeReference<List<T>>(){});
            return beanList;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
```
测试结果，浏览器输入：http://localhost:8080/students
结果如下：
```json
[
  {
    "id": "1",
    "name": "小英",
    "parentPhone": [
      "13222222222",
      "13333333333",
      "15777777777"
    ],
    "city": {
      "province": "广东省",
      "city": "深圳市",
      "district": "南山区"
    },
    "visited": [
      {
        "province": "四川省",
        "city": "深圳市",
        "district": "南山区"
      }
    ],
    "netName": [
      "会飞的鱼",
      "小小雪",
      "茉莉香果果"
    ]
  },
  {
    "id": "2",
    "name": "小明",
    "parentPhone": [
      "13222222222",
      "13333333333",
      "15777777777"
    ],
    "city": {
      "province": "广东省",
      "city": "深圳市",
      "district": "南山区"
    },
    "visited": [
      {
        "province": "四川省",
        "city": "深圳市",
        "district": "南山区"
      },
      {
        "province": "广东省",
        "city": "成都市",
        "district": "青羊区"
      }
    ],
    "netName": [
      "会飞的鱼2",
      "小小雪2",
      "茉莉香果果2"
    ]
  }
]
```

