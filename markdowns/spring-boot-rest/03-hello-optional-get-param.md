# Hello, Optional GET parameter!

GET parameter 可以是可選的參數，例如用關鍵字搜尋，可以設定為如果不傳入任何關鍵字預設回傳全部的資料，用下面的做法可以達成

1. 改寫 ```@RequestParam("paramName")```，變成 ```@RequestParam(name = "paramName", required = false)```

這時候，我們的 URL 可以是 ```/api?param1=Yoyo```，或是不傳 ```/api```。

如果不傳入參數的時候，我們在 Controller 拿到的 String 就會是 null，使用前記得做 null check。

```java
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
public void api(@RequestParam(name = "param1", required = false) String param1) {
    // /api?param1=Yoyo, param1 = "Yoyo"
    // /api, param1 = null
}
```

下面的練習，請在 REST API 上加入一個 optional 的 GET 參數 "name"。
* 如果有傳入 "name"，則回傳 "Hello, %name%!"
* 如果沒有傳入 "name"，則回傳 "Hello, World!"

@[Complete the controller to say "Hello, %name%!" or "Hello, World!"]({"stubs": ["src/main/java/com/example/training/rest/QuizController03.java", "src/main/java/com/example/training/to/RestQuiz01Response.java"], "command": "com.example.training.rest.QuizController03Test#restQuiz03"})

?[如果 URL 長的像 "/restQuiz03?name=" 的情況，Controller 裡面的 name 會是什麼值呢？]
- [ ] null
- [X] 空字串
- [ ] "Yoyo"
- [ ] 5566