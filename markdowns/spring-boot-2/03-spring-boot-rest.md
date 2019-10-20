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

## Hello, GET parameter!

上面的練習中，我們成功的創建出一個 GET method 的 REST API，不過他沒有接入任何的參數，如果要讓 GET method 能夠接收參數，只需要修改 method 的參數就可以了。

1. 在 method 的參數加上 ```String``` 型別的參數
2. 在參數的前面加上 ```@RequestParam("paramName")``` 的 annotation，並傳入一個 String 當作 GET 參數的名稱
3. 如果這個 GET method 需要接收多個參數，只需要重複上述步驟，設定多個 method 參數即可

假設我們要讓 URL 長的像這樣: ```/api?param1=Yoyo&param2=Yee```，method 的定義就會像是

```java
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
public void api(@RequestParam("param1") String param1, @RequestParam("param2") String param2) {
    // param1 = "Yoyo"
    // param2 = "Yee"
}
```

下面的練習，請在 REST API 上加入一個 GET 參數，並回傳相對應的 answer。

@[Complete the controller to say "Hello, %name%!"]({"stubs": ["src/main/java/com/example/training/rest/QuizController02.java", "src/main/java/com/example/training/to/RestQuiz01Response.java"], "command": "com.example.training.rest.QuizController02Test#restQuiz02"})

> 要注意的是，如果定義了 GET 參數，但是 client 呼叫 REST API 的時候卻沒有帶上，對應的值，我們的 REST API 並不會被真的呼叫，而是會回傳 HTTP 400 回去。因為預設 GET 參數是 required 的。