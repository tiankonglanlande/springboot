## SpringBoot 整合redisson单机版实现

### 导入pom.xml

```xml
<dependencys>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-redis</artifactId>
        <exclusions>
            <exclusion>
                <groupId>redis.clients</groupId>
                <artifactId>jedis</artifactId>
            </exclusion>
            <exclusion>
                <groupId>io.lettuce</groupId>
                <artifactId>lettuce-core</artifactId>
            </exclusion>
        </exclusions>
    </dependency>
    <dependency>
        <groupId>org.redisson</groupId>
        <artifactId>redisson</artifactId>
        <version>3.15.6</version>
    </dependency>
</dependencys>

```
### 配置application.yml
```yaml
spring:
  application:
    name: springboot-redisson-single
  redis:
    host: 192.168.56.200
    port: 6379
    password: yuanfenge@redis
    database: 0
```

### 配置redisson

```java
@Configuration
public class RedissonConfig {

    @Bean
    public RedissonClient redissonClient() {
        String url = String.format("redis://%s:%d", redisProperties.getHost(), redisProperties.getPort());
        // 1. Create config object
        Config config = new Config();
        SingleServerConfig singleServerConfig = config.useSingleServer().setAddress(url);
        if (Objects.nonNull(redisProperties.getPassword())) {
            singleServerConfig.setPassword(redisProperties.getPassword());
        }
        //2. 使用json序列化方式
        Codec codec = new JsonJacksonCodec();
        config.setCodec(codec);
        RedissonClient client = Redisson.create(config);
        return client;
    }
}
```
### 使用方法
```java

@Service
public class RedissonServiceImpl implements RedissonService {
    public volatile int num=0;
    String LOCK_PRODUCT_PREFIX="product:";
    @Autowired
    RedissonClient redissonClient;
    @Override
    public Map<String, Object> createOrder(Integer productId) {
        Map<String,Object> resultMap=new HashMap<>();
        //1.获取锁
        RLock lock = redissonClient.getLock(LOCK_PRODUCT_PREFIX+productId);
        lock.lock();
        try {
            // TODO 业务逻辑
            Thread.sleep(500);
            num+=1;
            String content = String.format("i=%d;productId=%s",num,productId);
            System.out.println(content);
            resultMap.put("productId",productId);
            resultMap.put("num",num);
            return resultMap;
        } catch (Exception e) {
            //TODO 错误日志
        } finally {
            //2.释放锁
            if (lock.isLocked() && lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
        return null;
    }
}
```
