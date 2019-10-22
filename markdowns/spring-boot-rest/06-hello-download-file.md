# Hello, Download File!

REST 的 Download 可以用 GET 和 POST 實做。

1. method 前面的 ```@RequestMapping``` 和 GET 或 POST 的用法類似，不過建議把 ```produces``` 拿掉（因為已經不是回傳 JSON 了）
2. method 回傳的參數改為 ```ResponseEntity<byte[]>```
3. 回傳的時候使用下面的做法

```java
// 使用 GET 的 download API，我們可以設定 attachment header，這樣用瀏覽器直接開啟連結就會直接跳到下載，並檔名自動帶入我們設定的檔案名稱
@RequestMapping(value = "/downloadGet", method = RequestMethod.GET)
public ResponseEntity<byte[]> downloadGet(@RequestParam("param") String param) {
  // get your file's bytes & file name
  byte[] fileBytes = new byte[];
  String fileName = "File Name";

  HttpHeaders headers = new HttpHeaders();
  headers.setContentDispositionFormData("attachment", encodedFileName);
  return new ResponseEntity<>(fileBytes, headers, HttpStatus.OK);
}
```

```java
// 使用 POST 的話就不用特別做了，因為無法使用瀏覽器直接打開，attachment header 就沒有用了。
@RequestMapping(value = "/downloadPost", method = RequestMethod.POST)
public ResponseEntity<byte[]> downloadPost(@RequestBody QueryTO queryTO) {
  // get your file's bytes & file name
  byte[] fileBytes = new byte[];
  String fileName = "File Name";

  return new ResponseEntity<>(fileBytes, HttpStatus.OK);
}
```

下面的練習，請試著根據 TODO 的資訊完成對應的 download REST API。

@[Complete the controller to Download File.]({"stubs": ["src/main/java/com/example/training/rest/QuizController06.java"], "command": "com.example.training.rest.QuizController06Test#restQuiz06"})
