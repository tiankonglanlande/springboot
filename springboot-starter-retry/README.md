# 异常重试starter组件
可以配置在Controller和Service业务类等，用于出现异常自动重试功能,重试次数达到上限时回调fallback方法。

注意：调用fallback方法，这个实现很基础，没有处理方法参数或返回值类型不匹配的情况

列如：
http请求失败重试，
代码异常重试等等。

# 如何使用
### 1.导入springboot-starter-retry的依赖到pom.xml文件
```xml
<dependency>
    <groupId>com.yuanfenge</groupId>
    <artifactId>springboot-starter-retry</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>

```
### 2.入口类开启重试
```java
@SpringBootApplication
@EnableRetry
public class BusinessApp {

    public static void main(String[] args) {
        SpringApplication.run(BusinessApp.class,args);
    }
}
```
### 3.在要异常重试的目标方法进行注解（可使用默认参数或者自定义设置）
```java
    @Retry
    public String retry(){
        int a=1/0;
        return "retry";
    }
```

### 5.yml文件参数配置（可选）
配置加载顺序：代码中配置 > yml或property配置 > 默认配置

application.yml
```yaml
yuanfenge:
  retry:
    max-retry-count: 1 #最大重试次数（可选）
    fallback: fallback2 #重试上限后回调fallback2方法，需要在重试异常的类中实现。没有实现则调用默认的fallback方法（可选）
    time: 100 #重试上限后回调fallback2方法，（可选）
    class-names: #指定发生异常重试的异常类名数组（可选）
      - java.lang.NullPointerException
      - java.lang.ArithmeticException
```

作者：猿份哥，版权所有，欢迎保留原文链接进行转载：)

