# Hello, Upload File!

REST Upload File 可以使用 Multipart 來傳輸檔案，使用的 method 則是 POST，因此寫法和 POST 很像。

1. method 前面的 ```@RequestMapping``` 和 POST 的用法一模一樣。
2. 在 method 的參數中，改成接收 ```@RequestParam("file")```，並接收的參數型別為 ```MultipartFile```
3. 額外帶的參數，例如指定資料夾等等的參數，可以再用其他的 ```@RequestParam``` 來傳入

對於傳進來的 ```MultipartFile``` 有一些常用的 function
* 取得檔案名稱: multipartFile.getOriginalFilename()
* 取得檔案大小: multipartFile.getSize()
* 取得檔案內容: multipartFile.getBytes()

## Path Traversal when write files

如果沒有做特殊檢查，user 可以在檔名中加入類似 "../" 的字串來企圖寫入伺服器中的任意位置，尤其是如果 server AP 是由管理員權限啟動的情況就更加危險，這種攻擊稱做 **Path Traversal Attack**。

為了有效防止這種行為，我們可以利用 ```Paths.normalize()``` 配合 ```Paths.startsWith()``` 來確保寫入的檔案位置在我們預期的資料夾中，不會跑到其他地方去。

```java
// 確保寫入的檔案位置在現在的資料夾中
Path basePath = Paths.get("./").toAbsolutePath().normalize();
Path newFilePath = Paths.get("./", fileName).toAbsolutePath().normalize();

if (newFilePath.startsWith(basePath)) {
  // write file to path
}
```

> 想想看，為什麼要做 ```toAbsolutePath()```？如果拿掉會怎樣？

> 試試看，如果先做 ```normalize()``` 再做 ```toAbsolutePath()``` 會有什麼結果？這樣的 path traversal check 還有效嗎？

下面的練習，請試著根據 TODO 的資訊完成對應的 upload REST API。
* 傳入的 MultipartFile 名稱為 "file"，並還有一個額外的參數 "name"
* 請將檔案寫入 "./%name%/%fileName%" 中
  * 請檢查寫入的檔案位置會在 "./" 底下，若不是則直接回傳 answer "path traversal"
  * 請檢查檔案大小必須小於等於 50 bytes，若超過則直接回傳 answer "large file"
  * 成功寫入回傳 answer "success"

@[Complete the controller to Upload File.]({"stubs": ["src/main/java/com/example/training/rest/QuizController05.java", "src/main/java/com/example/training/to/RestQuiz01Response.java"], "command": "com.example.training.rest.QuizController05Test#restQuiz05"})
