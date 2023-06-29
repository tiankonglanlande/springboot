## SpringBoot系列18-validation分组校验+自定义参数校验器
### [原文链接: https://www.lskyf.com/post/212](https://www.lskyf.com/post/212)
## 分组-加入依赖
```xml
<dependencies>
     <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-validation</artifactId>
      </dependency>
</dependencies>
```
## 分组-定义分组接口,均为空接口
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
## 分组UserBean.java
```java
/**
 * @author 猿份哥
 * @description
 * @createTime 2019 - 3 - 18 11:54
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserBean {
    public static final int ONLINE=1;//在线
    public static final int OFFLINE=-1;//离线
    public static final int LEAVE=2;//离开
    public static final int BUSY=3;//忙碌

    @NotNull(message = "不能为空",groups = {UpdateUseGroup.class})
    private Long id;
    @NotNull(message = "不能为空",groups = {AddUserGroup.class})
    private String name;
    @NotNull(message = "不能为空",groups = {AddUserGroup.class,UpdateUseGroup.class})
    private Integer age;
   }
```
## 分组测试代码-GroupController.java
```java
@RestController
public class GroupController {
    /**
     * 没有指定分组接口，不会进行任何校验
     * @param user
     * @return
     */
    @GetMapping("/invalid")
    public UserBean invalid(@Validated UserBean user){
        return user;
    }

    /**
     * 测试地址： http://localhost:8080/add?name=%E5%B0%8F%E6%98%8E&age=18
     * @param user
     * @return
     */
    @GetMapping("/add")
    public UserBean userAdd(@Validated(AddUserGroup.class) UserBean user){
        return user;
    }
}
```
## 自定义校验器-新增CustomUseGroup.java接口
```java
/**
 * 自定义校验器分组
 */
public interface CustomUseGroup {

}
```
## 自定义校验器-StatusConstraintValidator.java
```java

/**
 * @author 猿份哥
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
## 自定义校验器-定义自定义注解Status（可以参照@NotNull注解）
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
## 自定义校验器-新建默认消息提示文件配置
resources下新建ValidationMessages.properties
```properties
com.yuanfenge.valid.group.custom.Status.error=状态错误
```
## 自定义校验器-UserBean.java新增如下代码
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
## 自定义校验器-测试GroupController.java代码
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
## JSR303注解使用场景列表
 |  注解   | 使用场景 |
 |  ----  | ----  |
 | AssertFalse | 只能为false  |
 | AssertTrue | 只能为true  |
 | DecimalMax | 必须小于${inclusive == true ? '或等于' : ''}{value}  |
 | DecimalMin | 必须大于${inclusive == true ? '或等于' : ''}{value}  |
 | Digits | 数字的值允许范围(只允许在{integer}位整数和{fraction}位小数范围内)  |
 | Email |  一个合法的电子邮件地址 |
 | Future | 需要是一个将来的时间  |
 | FutureOrPresent | 需要是一个将来或现在的时间  |
 | Max | 最大不能超过{value} |
 | Min | 最小不能小于{value} |
 | Negative | 必须是负数 |
 | NegativeOrZero | 必须是负数或零 |
 | NotBlank | 不能为空 |
 | NotEmpty | 不能为空 |
 | NotNull | 不能为null |
 | Null | 必须为null |
 | Past | 需要是一个过去的时间 |
 | PastOrPresent | 需要是一个过去或现在的时间 |
 | Pattern | 需要匹配正则表达式"{regexp}" |
 | Positive | 必须是正数 |
 | PositiveOrZero | 必须是正数或零 |
 | Size | 个数必须在{min}和{max}之间 |
 
 ## 小技巧：常用消息提示可参照源码ValidationMessages_zh_CN.properties查看
 
 [源码下载链接](https://github.com/tiankonglanlande/springboot)
 
 [原文链接: https://www.lskyf.com/post/212](https://www.lskyf.com/post/212)
 
 作者：猿份哥，版权所有，欢迎保留原文链接进行转载：)
 