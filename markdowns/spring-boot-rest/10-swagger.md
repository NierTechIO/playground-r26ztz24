# Swagger

Swagger 可以快速產生 REST API 的 documentation，並且提供 UI 介面可以直接呼叫來做測試。

在 Spring Boot 中引入 Swagger 的方法如下

## 在 pom.xml 中加入 dependencies

```xml
<dependency>
  <groupId>io.springfox</groupId>
  <artifactId>springfox-swagger2</artifactId>
  <version>2.9.2</version>
</dependency>

<dependency>
  <groupId>io.springfox</groupId>
  <artifactId>springfox-swagger-ui</artifactId>
  <version>2.9.2</version>
</dependency>
```

## 撰寫 Swagger 相關設定

在 java source code 裡面創一個 class，並寫如下範例的程式碼

```java
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket buildDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                // 提供 API 相關的資訊，不想設定可以跳過這個 apiInfo call
                .apiInfo(buildApiInf())
                .select()
                // 設定 base package，只有在這個 package 底下的 REST API 才會加入 Swagger 中
                // 如果不想指定，也可以用 RequestHandlerSelectors.any() 代表所有的 REST API
                .apis(RequestHandlerSelectors.basePackage("com.example.training.rest"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo buildApiInf() {
        return new ApiInfoBuilder()
                .title("System RESTful API documentation")
                .contact(new Contact("Nier", "https://www.google.com.tw", "techio@nier.tw"))
                .version("1.0")
                .build();
    }
}
```

在 REST API 層，也可以加一些 annotation 來額外提供資訊給 Swagger。

如果某個 API 不需要加入 Swagger 中，只需要在 API 上面加上 ```@ApiIgnore``` 註解就可以了。

啟動 Spring Boot Server 後，前往 ```http://localhost:8080/swagger-ui.html``` 就可以看到 Swagger UI 的介面。
