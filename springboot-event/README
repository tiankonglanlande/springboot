#### SpringBoot系列3-四步完成观察者事件发布接收（发送消息接收消息）使用异步方不阻塞主线程


#### 首先Application开启异步

```java
@SpringBootApplication
@EnableAsync
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
```
#### 定义事件体（消息）
```java
@Setter
@Getter
@ToString
public class ContentEvent extends ApplicationEvent {

    private String content;

    /**
     * Create a new ApplicationEvent.
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public ContentEvent(Object source) {
        super(source);
    }
    public ContentEvent(Object source, String content){
        super(source);
        this.content = content;
    }
}
```
#### 定义事件监听者（接收者）

```java
@Component
@Slf4j
public class ContentListener{

    @Async
    @EventListener
    public void handler(ContentEvent event){
        log.info("收到消息"+event.getContent());
    }
}
```
#### 发布事件（发送消息）
```java
@Controller
public class ContentController {
    @Autowired
    private ApplicationContext applicationContext;

    @GetMapping("/event/{content}")
    public void sendEvent(@PathVariable String content){
        applicationContext.publishEvent(new ContentEvent(this,content));
    }
}
```
csdn博客文章链接：<a href="https://blog.csdn.net/u013042707/article/details/82686505" target="_blank">https://blog.csdn.net/u013042707/article/details/82686505</a> <br>
个人博客文章链接：<a href="https://www.lskyf.xyz/spring-boot/2018/09/15/SpringBoot系列3-四步完成观察者事件发布接收（发送消息接收消息）使用异步方不阻塞主线程" target="_blank">https://www.lskyf.xyz/spring-boot/2018/09/15/SpringBoot系列3-四步完成观察者事件发布接收（发送消息接收消息）使用异步方不阻塞主线程</a> <br>
