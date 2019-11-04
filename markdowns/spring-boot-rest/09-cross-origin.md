# Cross Origin

瀏覽器預設會檢查所有跨網域的 AJAX calls，例如你在 https://example.com 要呼叫一個 https://example2.com/api/myRest，瀏覽器會先發送一個 preflight request，用 OPTIONS method 送往該 REST API，並看他回傳的資訊是不是符合要求。

最主要檢查的為下面兩項
* Access-Control-Allow-Origin: 原本的網域有沒有落在這個值中，例如上面的範例，值可以是 "https://example.com" 或是 "*" 代表所有網域都可以
* Access-Control-Allow-Methods: 原本要求的 HTTP Method 有沒有落在這個值中，回傳值可以是複數，例如 "POST, GET, PUT"

如果檢查成功，瀏覽器才會真的去 call 目的地的 REST API，如果檢查失敗則停止呼叫。

這種跨網域的要求稱做 CORS (Cross-Origin Resource Sharing)，預設會被關閉，目的是預防釣魚。假設駭客傳送一個 hackfacebook.com 的連結給使用者，使用者無意中進入後，網站中用 AJAX call 去呼叫 facebook.com 的 API，假設該 API 開啟了 CORS，則 hackfacebook.com 就可以拿到使用者的 login session。

因此對於自己的所有 API，如果非必要請勿開啟 CORS，對於開啟 CORS 的 API 就需要更謹慎的考慮安全性。

在 Spring Boot 中將 REST API 標註為 Cross Origin 的方法如下:

1. 在一個一般的 REST API method 上面多一個 ```@CrossOrigin``` annotation
2. 預設什麼都不設定就是允許所有網域，而可以接受的 Method 就是 @RequestMapping 中標註的 Methods
3. 如果要額外設定網域，可以加上 ```origins = "https://example.com"```

```java
@CrossOrigin(origins = "https://domain2.com", maxAge = 3600)
@RestController
@RequestMapping("/account")
public class AccountController {

    @GetMapping("/{id}")
    public Account retrieve(@PathVariable Long id) {
        // ...
    }
}
```

在下面的練習中，請在 REST API 上加上 Cross Origin，並設定網域只有在 "https://example.com" 才能接受 CORS，並將 max-age 設定為 10 分鐘。

@[Complete the REST API to accept CORS.]({"stubs": ["src/main/java/com/example/training/rest/QuizController09.java"], "command": "com.example.training.rest.QuizController09Test#restQuiz09"})
