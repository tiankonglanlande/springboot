报错：InvalidDataAccessApiUsageException: Error attempting to get column 'gender'

### 枚举类型不能转换
解决方法
将druid升级到1.21以上版本

<!-- alibaba 数据库连接池 -->
```xml
<dependency>
   <groupId>com.alibaba</groupId>
   <artifactId>druid-spring-boot-starter</artifactId>
   <version>1.1.21</version>
</dependency>
```
