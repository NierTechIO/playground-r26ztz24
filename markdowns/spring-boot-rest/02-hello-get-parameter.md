# GET parameters

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