### 功能实现
使用starter的方式实现Web模块的抽取，本demo实现了Student模块的抽取，其他想要使用的地方。

### 使用方法
需要使用的模块引入如下代码
调用方的 pom.xml文件
```xml
        <dependency>
            <groupId>com.yuanfenge.student</groupId>
            <artifactId>springboot-starter-student</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>
```
调用方的配置文件
```properties
student.id=100
student.name=yuanfenge
student.age=20
```

### 1.配置在配置文件的方式：
1.1.此demo注入spring容器是通过META-INF/spring.factories中配置代码
```factories
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
com.yuanfenge.student.StudentAutoConfiguration
```
1.2.如果是springboot3.x可以通过创建META-INF/spring/org.springframework.boot.autoconfigure.Autoconfiguration.imports
然后配置一下代码即可
```imports
com.yuanfenge.student.StudentAutoConfiguration
```

### 2.不需要配置文件的方式：添加@Configuration
```java
@Configuration //表示是配置类组件
@Import({StudentController.class, StudentService.class, Student.class}) //导入需要的组件类
public class StudentAutoConfiguration {
}
```

### 3.以注解的方式启用
3.1导入组件类到StudentAutoConfiguration中
```java
@Import({StudentController.class, StudentService.class, Student.class}) //导入需要的组件类
public class StudentAutoConfiguration {
}
```
3.2编写注解@EnableStudent,调用的时候作为控制开关
```java
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@java.lang.annotation.Target({java.lang.annotation.ElementType.TYPE})
@java.lang.annotation.Documented
@Import(StudentAutoConfiguration.class)//导入StudentAutoConfiguration类
public @interface EnableStudent {
}
```
3.2.使用方法@EnableStudent，即可打开业务开关
```java
@SpringBootApplication
@EnableStudent
public class BusinessApp {

    public static void main(String[] args) {
        SpringApplication.run(BusinessApp.class,args);
    }
}
```