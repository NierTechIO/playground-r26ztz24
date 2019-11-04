# Path variables

REST 的參數還可以透過 URL 來傳遞，例如 ```/api/{var1}/{var2}```，的情況，我們前往 ```/api/hello/world``` 的話，var1 就會是 hello，而 var2 則是 world。

創建方法和 GET 非常類似。

1. method 前面的 ```@RequestMapping``` 中把 URL 的部分加上變數名稱並用 ```{}``` 框起來
    * 簡單的做法就是 ```/api/{var1}```
    * 也可以用 regular expression 來做 match，例如 ```/api/{var1:[a-z]+}```，這樣 ```/api/hello``` 會 match，```/api/Hello``` 就不會進這個 REST API
2. method 的參數加上 ```@PathVariable```，變數名稱預設名字和變數名稱要一樣，如果不同的話要特別寫出來
    * 承 1. 的範例，我們可以寫 ```@PathVariable String var1```，如果要換名字的話就要寫成 ```@PathVariable("var1") String name```

```java
@RestController
public class ExampleController07 {
    @RequestMapping(
            value = "/example07/{name}/{text:[a-zA-Z0-9]+}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET
    )
    public RestQuiz01Response example07(@PathVariable String name, @PathVariable("text") String content) {
        RestQuiz01Response result = new RestQuiz01Response();
        result.setAnswer(name + ": " + content);
        return result;
    }
}
```

下面的練習，請試著根據 TODO 的資訊完成對應的 path variables 設定。

@[Complete the path variables controller.]({"stubs": ["src/main/java/com/example/training/rest/QuizController07.java"], "command": "com.example.training.rest.QuizController07Test#restQuiz07"})
