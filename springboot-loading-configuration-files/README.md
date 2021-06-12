###  SpringBoot系列14-加载配置文件信息
SpringBoot系列14-加载yml,properties配置文件信息,加载外部的yml文件，加载外部的properties配置文件
[使用参考说明见: https://www.lskyf.com/post/73](https://www.lskyf.com/post/73)

### 最新加载外部yaml文件步骤
1.定义MyEnvironmentPostProcessor.java
```java
public class MyEnvironmentPostProcessor implements EnvironmentPostProcessor {

    private final YamlPropertySourceLoader loader = new YamlPropertySourceLoader();

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        Resource path = new ClassPathResource("external-application.yml");
        PropertySource<?> propertySource = loadYaml(path);
        environment.getPropertySources().addLast(propertySource);
    }
    private PropertySource<?> loadYaml(Resource path) {
        Assert.isTrue(path.exists(), () -> "Resource " + path + " does not exist");
        try {
            return this.loader.load("custom-resource", path).get(0);
        }
        catch (IOException ex) {
            throw new IllegalStateException("Failed to load yaml configuration from " + path, ex);
        }
    }
}
```
2.resources/META-INF 文件夹下创建spring.factories文件配置好MyEnvironmentPostProcessor
```properties
org.springframework.boot.env.EnvironmentPostProcessor=com.yuanfenge.loading.MyEnvironmentPostProcessor
```