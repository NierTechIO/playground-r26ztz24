# Spring Boot REST

## Hello, REST!

Spring Boot 創建 REST API 的方法

1. 創一個 Class
2. Class 上面加上 ```@RestController``` 的 annotation
3. Class 裡面創一個 public method
4. Method 上面加上 ```@RequestMapping``` 的 annotation
5. 替 ```@RequestMapping``` 加上 REST API 的設定
    * ```value```: URL 位址，例如 ```"/hello"```
    * ```produces```: 回傳的類型，例如回傳 JSON 的話就填上 ```MediaType.APPLICATION_JSON_VALUE```
    * ```method```: Request 的 method，例如 GET 的話就寫 ```RequestMethod.GET```
6. 這個 method 回傳的 Java POJO，就會自動被轉換成對應的 JSON 並回傳

下面的練習，請試著回傳一個 Java POJO，並在 answer 裡面填上 "Hello, REST!"

@[Complete the controller to say "Hello, REST!"]({"stubs": ["src/main/java/com/example/training/rest/QuizController01.java", "src/main/java/com/example/training/to/RestQuiz01Response.java"], "command": "com.example.training.rest.QuizController01Test#restQuiz01"})