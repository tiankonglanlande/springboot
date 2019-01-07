#### 最简单的
pom.xml文件引入依赖文件
```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>
</dependencies>
```
说明：spring-boot-starter-web包含了文件上传的依赖，引入spring-boot-starter-thymeleaf不是必须的你也可以使用jsp代替

application.properties配置一下存储位置
```
#文件存储位置
file.path=D:/test
```

toUpload.html前端页面
```html
<!DOCTYPE html>
<html lang="zh" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>springboot 单个文件上传</title>
</head>
<body>
    <form th:action="@{doUpload}" th:method="post" enctype="multipart/form-data">
        <label>选择文件：</label><input th:type="file" th:name="file">
        <input th:type="submit" th:value="上传">
    </form>
</body>
</html>
```
说明：@{doUpload}上传接口的请求路径

result.html结果页面
```html
<!DOCTYPE html>
<html lang="zh" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>springboot 文件上传结果页面</title>
</head>
<body>
  <h3 th:text="${message}">结果消息</h3>
</body>
</html>
```

说明：${message}显示上传后的消息

FileUploadController.java
```java
/**
 * @author 猿份哥
 * @description
 * @createTime 2019/1/6 20:29
 */
@Controller
public class FileUploadController {
    @Value("${file.path}")
    private String filePath;

    @RequestMapping("/toUpload")
    public String toUpload(){
        return "toUpload";
    }
    @RequestMapping("/doUpload")
    public String doUpload(@RequestParam("file")MultipartFile multipartFile, Model model) throws IOException {

        if (multipartFile.isEmpty()){
            model.addAttribute("message","请选择文件");
        }
        File file=new File(filePath,multipartFile.getOriginalFilename());
        multipartFile.transferTo(file);
        model.addAttribute("message","上传成功");
        return "result";
    }
}
```
说明：doUpload实现了接收文件并且将文件保存到指定目录

运行项目浏览器访问http://localhost:8080/toUpload即可上传单个文件

#### 设置文件大小，请求大小
```properties
#默认单个上传文件是1MB
spring.servlet.multipart.max-file-size=5MB
#默认上传文件总大小是10MB
spring.servlet.multipart.max-request-size=50MB
```
说明：虽然我们什么都可以不用配置就可以完成上传功能，但是默认单个上传文件是1MB,默认上传文件总大小是10MB.如果文件过大就会
上传失败，此时我们可以根据需求调整这了两个参数值

#### 多文件上传
FileUploadController.java
```java
/**
 * @author 猿份哥
 * @description
 * @createTime 2019/1/6 20:29
 */
@Controller
public class FileUploadController {
    @RequestMapping("/toMultiUpload")
    public String toMultiUpload(){
        return "toMultiUpload";
    }
    @RequestMapping("/multiUpload")
    public String mutiUpload(HttpServletRequest request,Model model){
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        for (int i = 0; i < files.size(); i++) {
            MultipartFile multipartFile = files.get(i);
            String fileName=multipartFile.getOriginalFilename();
            File file=new File(filePath,fileName);
            try {
                multipartFile.transferTo(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        model.addAttribute("message","上传成功");
        return "result";
    }
 }
```


