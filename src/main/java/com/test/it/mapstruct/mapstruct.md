# 1. pom.xml增加依赖

```xml
<dependencys>
<!--mapStruct依赖-->
      <dependency>
          <groupId>org.mapstruct</groupId>
          <artifactId>mapstruct-jdk8</artifactId>
          <version>${org.mapstruct.version}</version>
      </dependency>
      <dependency>
          <groupId>org.mapstruct</groupId>
          <artifactId>mapstruct-processor</artifactId>
          <version>${org.mapstruct.version}</version>
          <scope>provided</scope>
      </dependency>
      <dependency>
          <groupId>javax.inject</groupId>
          <artifactId>javax.inject</artifactId>
          <version>1</version>
      </dependency>
</dependencys>
```

# CDI 使用spring时,@Mapper(componentModel = "productMapper"),然后@Autowired可以获取到bean

# 官方实例

[mapstruct-examples](https://github.com/mapstruct/mapstruct-examples)

[官方文档](https://mapstruct.org/documentation/stable/reference/html/)