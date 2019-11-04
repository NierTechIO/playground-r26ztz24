# Global Exception Handler

我們可以對在 REST 層中發生的 error 統一做一個 Exception Handler。

1. 創一個 class，並標註為 ```@RestControllerAdvice```
2. 在 class 中創一個 method，並標註 ```@ExceptionHandler(value = {ExceptionClass})```
    * 例如假設要處理所有的 Exception，可以寫成 ```@ExceptionHandler(value = Exception.class)```
3. method 接收兩個參數 ```(HttpServletRequest req, Exception e)```
4. method return 和一般的 REST return 值相同，例如我們可以回傳 ResponseEntity<Object>

```java
@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(value = Exception.class)
  public ResponseEntity<Object> defaultErrorHandler(HttpServletRequest req, Exception e) {
    String stackTraceString = ExceptionUtils.getStackTrace(e);
    // 可以根據 exception 的 type 來做不同處理
    // 例如如果遇到 AccessDeniedException 就丟給她 401 的特殊 page，其他的就回空 page
    if (e instanceof AccessDeniedException) {
      // 使用這個方法而不用 redirect 的優點是，URL 並不會被修改，但會看到 401 的頁面，user 如果在別的頁面登入後重新整理，就可以直接回到該去的頁面
      String resultHtmlString = IOUtils.toString(this.getClass().getResourceAsStream("/errors/401.html"), StandardCharsets.UTF_8);
      return ResponseEntity.status(401).contentType(MediaType.TEXT_HTML).body(resultHtmlString);
    } else {
      return ResponseEntity.status(getStatusCode(e)).body(null);
    }
  }
  
  private int getStatusCode(Exception exception) {
    if (exception instanceof WebApplicationException) {
      return ((WebApplicationException) exception).getResponse().getStatus();
    }
    
    return Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
  }
}
```

使用 Global Exception Handler 可以 catch Controller 層的 error 並做處理，但問題是如果 Exception 發生在 Filter 層（例如 Security Filter），還沒進入 Controller，就不會被這個 Global Exception Handler catch 到，所以需要加上下面的 Error Handler。

Spring Boot 預設的 Exception 處理機制是使用 BasicErrorController，我們可以自己定義一個 Controller 實現 ErrorController interface，這樣預設的 BasicErrorController 就不會被觸發，會被我們的蓋掉

我們將所有的 Exception 都重複利用上面的 GlobalExceptionHandler 來處理

```java
// 一般來說只要使用 @Component 即可，但因為我們要實現 @RequestMapping，所以改用 @RestController
@RestController
public class CustomErrorController implements ErrorController {
  // 這個就是上面定義的 GlobalExceptionHandler，我們將用 DI 來注入
  private final GlobalExceptionHandler globalExceptionHandler;
  
  // 使用 constructor 注入
  @Autowired
  public CustomErrorHandler(GlobalExceptionHandler globalExceptionHandler) {
    this.globalExceptionHandler = globalExceptionHandler;
  }
  
  // 宣告一個 @RequestMapping，並直接將 Exception 導向 GlobalExceptionHandler
  @RequestMapping("/error")
  public ResponseEntity<Object> handleError(HttpServletRequest request) {
    Exception exception = (Exception) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
    return globalException.defaultErrorHandler(request, exception);
  }
  
  @Override
  public String getErrorPath() {
    // 這個 String 要和上面的 @RequestMapping 相同
    return "/error";
  }
}
```

在下面的練習中，請完成 QuizControllerAdvice，並依照 TODO 所標示的回傳不同的 error code。

@[Complete the ControllerAdvice to return corresponding error code.]({"stubs": ["src/main/java/com/example/training/rest/QuizControllerAdvice.java"], "command": "com.example.training.rest.QuizController08Test#restQuiz08"})
