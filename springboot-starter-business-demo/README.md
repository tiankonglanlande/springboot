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

### 说明
1.此demo注入spring容器是通过META-INF/spring.factories中配置代码
```factories
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
com.yuanfenge.student.StudentAutoConfiguration
```
2.如果是springboot3.x可以通过创建META-INF/spring/org.springframework.boot.autoconfigure.Autoconfiguration.imports
然后配置一下代码即可
```imports
com.yuanfenge.student.StudentAutoConfiguration
```
