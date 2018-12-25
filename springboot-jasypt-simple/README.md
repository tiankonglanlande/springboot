使用jasypt加密配置文件内容提高项目的安全性简单版

#### 为什么配置文件需要加密
spring boot项目中的application.properties或application.yml里面包含了mysql，redis，第三方的密钥等等
这些配置都是明文，所以我们为了提高安全性就需要将这些明文混淆了。jasypt可以很轻松的做到，下面我们来
看看它的用法
#### 首先引入pom依赖文件
```xml
<dependency>
    <groupId>com.github.ulisesbocchio</groupId>
    <artifactId>jasypt-spring-boot-starter</artifactId>
    <version>2.1.0</version>
</dependency>
```
 如果您不使用@SpringBootApplication或@EnableAutoConfiguration自动配置注释，则将此依赖项添加到您的项目：
  
```xml
<dependency>
        <groupId>com.github.ulisesbocchio</groupId>
        <artifactId>jasypt-spring-boot</artifactId>
        <version>2.1.0</version>
</dependency>
```
#### 在application.properties或者application.yml文件中配置加密密码
```properties
jasypt.encryptor.password=tiankonglanlande
```
#### 获取加密内容例如:我想加密的内容是username和pwd
编写一个测试类注入StringEncryptor加密器
```java
public class JasyptSimpleApplicationTests {

	@Autowired
	private StringEncryptor stringEncryptor;

	@Test
	public void jiami() {
		String username="tiankonglanlande";
		String pwd="tiankonglanlande_pwd";
		
		System.out.println("username原始值:"+username);
		System.out.println("pwd加密串原始值:"+pwd);
		
		System.out.println("username加密串:"+stringEncryptor.encrypt(username));
		System.out.println("pwd加密串:"+stringEncryptor.encrypt(pwd));
	}
}

```
运行jiami方法控制台输出加密的内容如下
```
username加密串:uF42uO9PxXDQYtnYe5++ebszGCkWXdY1NynJ+5Lptsg=
pwd加密串:+v5XN2Tv+d6VDfpeapME+S6vw2nOfE9L/1sjh6UFzso=
username原始值:tiankonglanlande
pwd原始值:tiankonglanlande_pwd
```
#### 将加密串填写到application配置文件
```properties
u.username=ENC(uF42uO9PxXDQYtnYe5++ebszGCkWXdY1NynJ+5Lptsg=)
u.pwd=ENC(+v5XN2Tv+d6VDfpeapME+S6vw2nOfE9L/1sjh6UFzso=)
```
在JasyptSimpleApplicationTests测试类中编写测试方法验证是否获取到解密串
```java
public class JasyptSimpleApplicationTests {
   @Value("${u.username}")
	private String username;
	@Value("${u.pwd}")
	private String pwd;

	@Test
	public void jiemi(){
		System.out.println("username解密后:"+username);
		System.out.println("pwd解密后:"+pwd);
	}
}
```
运行测试方法jiemi控制台输出
```
username解密后:tiankonglanlande
pwd解密后:tiankonglanlande_pwd

```
可以看出正好是我们加密之前的值。
是不是很简单呢感谢开源大神给我们提供的jasypt开源项目使我们的配置文件内容加密变得这么简单
#### 但是我们的项目可能需要动态的传入密码而不是写死在配置文件里面，如果写死在配置文件里面就像将们上了锁然后又把钥匙交给了小偷一样。毫无意义！
下一篇我将介绍如何自定义一个stater将jasypt封装为一个stater供多个项目使用，同时运行时动态传入加密密码

[源码下载链接](https://github.com/tiankonglanlande/springboot)

[原文链接:http://www.lskyf.com/view/27](http://www.lskyf.com/view/27)

[参考文档](https://github.com/ulisesbocchio/jasypt-spring-boot)

作者：猿份哥，版权所有，欢迎保留原文链接进行转载：)