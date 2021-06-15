# SpringBoot系列16-统一异常处理（包含简单的JSR303参数校验）
### 原文链接：https://www.lskyf.com/post/211
## 方法1.通过ControllerAdvice实现+简单的JSR303参数校验实现
### 1.1 加入依赖
```xml
<dependencies>
     <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-validation</artifactId>
      </dependency>
</dependencies>
```
### 1.2 校验Bean.java实现
```java
/**
 * @author yuanfenge
 * @description
 * @createTime 2019 - 3 - 07 11:54
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bean {

    @NotNull(message = "不能为空")
    private String name;

    @Min(value = 1, message = "大于0")
    @NotNull(message = "不能为空")
    private Integer age;
}
``` 
## 统一异常处理的ExceptionControllerAdvice.java
```java
/**
 * @author yuanfenge
 * 方法1：异常处理
 */
@Slf4j
@ControllerAdvice
public class ExceptionControllerAdvice {

    /**
     * 已废弃：发现此方法已失效
     * 使用{@link ExceptionControllerAdvice#bindExceptionHandler(MethodArgumentNotValidException)}替代
     * 处理BindException异常
     * 获得参数和错误消息封装到map集合通过json返回
     * @param ex
     * @return
     * @throws Exception
     */
    @Deprecated
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public Map<String, Object> bindExceptionHandler(BindException ex) throws Exception {
        String s = ex.getFieldError().getField() + ":" + ex.getFieldError().getDefaultMessage();
        Map<String, Object> map = new HashMap<>();
        map.put("code", 400);
        map.put("msg", s);
        return map;
    }

    /**
     * 最新参数绑定异常
     * @param ex
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public Map<String, Object> bindExceptionHandler(MethodArgumentNotValidException ex) throws Exception {
        BindingResult result = ex.getBindingResult();
        StringBuilder s=new StringBuilder();
        if (result.hasErrors()) {

            List<ObjectError> errors = result.getAllErrors();
            errors.forEach(p ->{
                FieldError fieldError = (FieldError) p;
                s.append(fieldError.getField()+":"+fieldError.getDefaultMessage());
            });
        }
        Map<String,Object> map=new HashMap<>();
        map.put("code",400);
        map.put("msg",s);
        return map;
    }
    /**
     * 处理自定义的ParamException异常
     * 获得参数和错误消息封装到map集合通过json返回
     * @param ex
     * @return
     * @throws Exception
     */
    @ExceptionHandler(ParamException.class)
    @ResponseBody
    public Map<String, Object> customExceptionHandler(ParamException ex) throws Exception {
        String msg = ex.getMessage();
        Map<String, Object> map = new HashMap<>();
        map.put("code", 400);
        map.put("msg", msg);
        return map;
    }

    /**
     * 处理其他异常
     * @param ex
     * @return
     * @throws Exception
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Map<String, Object> exceptionHandler(Exception ex) throws Exception {
        String msg = ex.getMessage();
        Map<String, Object> map = new HashMap<>();
        map.put("code", 500);
        map.put("msg", msg);
        return map;
    }
}
```
### 测试TestContorller.java
```java

@RestController
public class TestContorller {

    @RequestMapping("/valid")
    public Object valid(@Valid Bean bean) throws ParamException, CustomException {
        customParamCheck(bean);
        //customException();//打开注释可以测试自定义异常处理代码
        runtimeException();
        return bean;
    }

    private void runtimeException() {
        int a = 1 / 0;
    }

    private void customParamCheck(@Valid Bean bean) throws ParamException {
        if (bean.getAge() > 100) {
            throw new ParamException("age不能超过100");
        }
    }

    private void customException() throws CustomException {
        throw new CustomException("customException!!!");
    }
}
```
 ## 方法2.通过继承BaseController实现
 ```java

/**
 * 方法2：异常处理 其他controller继承BaseController即可
 */
public class BaseController {
    /**
     * 已废弃：发现此方法已失效
     * 使用{@link BaseController#bindExceptionHandler(MethodArgumentNotValidException)}替代
     * 处理BindException异常
     * 获得参数和错误消息封装到map集合通过json返回
     * @param ex
     * @return
     * @throws Exception
     */
    @Deprecated
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public Map<String, Object> bindExceptionHandler(BindException ex) throws Exception {
        String s = ex.getFieldError().getField() + ":" + ex.getFieldError().getDefaultMessage();
        Map<String, Object> map = new HashMap<>();
        map.put("code", 400);
        map.put("msg", s);
        return map;
    }

    /**
     * 最新参数绑定异常
     * @param ex
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public Map<String, Object> bindExceptionHandler(MethodArgumentNotValidException ex) throws Exception {
        BindingResult result = ex.getBindingResult();
        StringBuilder s=new StringBuilder();
        if (result.hasErrors()) {

            List<ObjectError> errors = result.getAllErrors();
            errors.forEach(p ->{
                FieldError fieldError = (FieldError) p;
                s.append(fieldError.getField()+":"+fieldError.getDefaultMessage());
            });
        }
        Map<String,Object> map=new HashMap<>();
        map.put("code",400);
        map.put("msg",s);
        return map;
    }

    @ExceptionHandler(ParamException.class)
    @ResponseBody
    public Map<String, Object> customExceptionHandler(ParamException ex) throws Exception {
        String msg = ex.getMessage();
        Map<String, Object> map = new HashMap<>();
        map.put("code", 400);
        map.put("msg", msg);
        return map;
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Map<String, Object> customExceptionHandler(Exception ex) throws Exception {
        String msg = ex.getMessage();
        Map<String, Object> map = new HashMap<>();
        map.put("code", 500);
        map.put("msg", msg);
        return map;
    }
}
```
 [源码下载链接](https://github.com/tiankonglanlande/springboot)
 
 [原文链接: https://www.lskyf.com/post/211](https://www.lskyf.com/post/211)
 
 作者：猿份哥，版权所有，欢迎保留原文链接进行转载：)
    
