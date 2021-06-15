## SpringBoot系列17-validation分组校验+自定义参数校验器
## 加入依赖
```xml
<dependencies>
     <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-validation</artifactId>
      </dependency>
</dependencies>
```
## 定义分组接口,均为空接口
```java
/**
 * 新增用户组
 */
public interface AddUserGroup {

}
/**
 * 修改用户组
 */
public interface UpdateUseGroup {

}
```
## 自定义校验器
```java

/**
 * @author yuanfenge
 * @description 自定义StatusConstraintValidator implements ConstraintValidator<自定义注解类,要校验的参数类型>
 */
public class StatusConstraintValidator implements ConstraintValidator<Status,Integer> {
    Set<Integer> set=new HashSet<>();

    /**
     * 初始化时将数据添加到集合中
     * @param annotation
     */
    @Override
    public void initialize(Status annotation) {
        int[] values = annotation.values();
        for (int v:values) {
            if (!set.contains(v)){
                set.add(v);
            }
        }
    }

    /**
     * 在此方法进行校验
     * @param value
     * @param context
     * @return
     */
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        // TODO 校验业务代码
        //如果值符合校验通过
        return set.contains(value);
    }
}
```
## 定义自定义注解Status（可以参照@NotNull注解）
```java

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = { StatusConstraintValidator.class})//指定StatusConstraintValidator校验器，可以指定多个校验器
public @interface Status {
    //指定错误默认提示信息
    String message() default "{com.yuanfenge.valid.group.custom.Status.error}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
    
    //定义注解方法：接收一个数组
    int [] values() default { };
}
```
## 默认消息文件配置
resources下新建ValidationMessages.properties
```properties
com.yuanfenge.valid.group.custom.Status.error=状态错误
```
## UserBean.java新增如下代码
```java
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public class UserBean {
            //自定义校验器Status
            @Status(values={ONLINE,OFFLINE,LEAVE,BUSY},groups = {CustomUseGroup.class})
            private Integer status;
    }
 ```
 ## 测试GroupController.java代码
 ```java
 @RestController
 public class GroupController {
           /**
            * 测试地址：http://localhost:8080/customValid?status=100
            * 自定义校验器地址：
            * @param user
            * @return
            */
           @GetMapping("/customValid")
           public UserBean customizeValid(@Validated(CustomUseGroup.class) UserBean user){
               return user;
           }
 }
 ```
 
 [源码下载链接](https://github.com/tiankonglanlande/springboot)
 
 [原文链接: https://www.lskyf.com/post/211](https://www.lskyf.com/post/212)
 
 作者：猿份哥，版权所有，欢迎保留原文链接进行转载：)
 