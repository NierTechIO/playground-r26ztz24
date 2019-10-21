# Hello, POST parameter!

POST method 的 REST 創建方式和 GET 雷同。

1. 在 method 前面的 ```@RequestMapping``` 裡面，改成 ```method = RequestMethod.POST```
2. 在 method 的參數中，改成接收 ```@RequestBody```，並對應 JSON 的一個 POJO

```java
/* 對應一個 JSON 定義如下
 * {
 *   name: string;
 *   value: number;
 * }
 * 的 POJO 為
 */
public class RequestPOJO {
    private String name;
    private Long number;

    // getter, setter for name & number
}

// 而該 POST API 則如下定義
@RequestMapping(value = "/post", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
public void restQuiz04(@RequestBody RequestPOJO request) {
    String name = request.getName();
    Long number = request.getNumber();
}
```

> 這邊我們的 POST method 傳入的 content type 是 JSON，還有另一種以前很常見的 form post，這邊就不介紹 form 格式的 POST REST API 寫法了。

下面的練習，請在 REST API 上加入一個 POST 參數，參數物件 type 為 ```RestQuiz04Request```。
* 傳入的 POST JSON 格式為 ```{name: string;}```
* 請實作 ```RestQuiz04Request``` 物件，使其符合上述的 POST JSON 格式。
* 請實作 ```restQuiz04```，從 ```RestQuiz04Request``` 中取出 ```name```，並用 ```RestQuiz01Response``` 回傳 ```Hello, %name%!```。

@[Complete the controller to say "Hello, %name%!"]({"stubs": ["src/main/java/com/example/training/rest/QuizController04.java", "src/main/java/com/example/training/to/RestQuiz04Request.java", "src/main/java/com/example/training/to/RestQuiz01Response.java"], "command": "com.example.training.rest.QuizController04Test#restQuiz04"})

?[如果傳入的 JSON 是 {}，RestQuiz04Request 裡面的 name 會是什麼值呢？]
- [X] null
- [ ] 空字串
- [ ] 會產生 Exception
- [ ] 會因為找不到對應的 REST API 而回傳 HTTP CODE 400