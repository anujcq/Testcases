package learning_automation;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class WebQuestion {
    WebDriver driver;
    public WebQuestion(WebDriver driver){
        this.driver=driver;
    }
    
    public void webQuestion() throws Exception{
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        List<WebElement> LangTabs = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
            By.xpath("//div[@class='ant-tabs-tab' or @class='ant-tabs-tab ant-tabs-tab-active']")));

        System.out.println("Total lang option in web Q "+LangTabs.size());

        for(WebElement tab: LangTabs){
            String type = tab.getText().trim();
            System.out.println(type);
            wait.until(ExpectedConditions.visibilityOf(tab));
            wait.until(ExpectedConditions.elementToBeClickable(tab));

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", tab);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", tab);

            switch (type) {

                case "index.html":
                    typeInAceEditor("html", "Html code");
                    break;
                case "index.css":
                    typeInAceEditor("css", "CSS code");
                    break;
                case "index.js":
                    typeInAceEditor("js", "JS code");
                    break;
                default:
                    System.out.println("select Correct type question");
                    break;
            }
        }
        WebElement submitBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button//span[text()='submit']")));
        try{
            wait.until(ExpectedConditions.visibilityOf(submitBtn)).click();
            wait.until(ExpectedConditions.elementToBeClickable(submitBtn)).click();
            Thread.sleep(5000);
        }catch(Exception e){}

        driver.findElement(By.xpath("//div[@class='app-logo']/button")).click();
    } 

    public void typeInAceEditor(String editorId, String content) {
        WebElement textarea = driver.findElement(By.cssSelector("#" + editorId + " textarea"));
        textarea.sendKeys(content);
    }
}



// package learning_automation;

// import java.time.Duration;
// import java.util.List;

// import org.openqa.selenium.By;
// import org.openqa.selenium.ElementClickInterceptedException;
// import org.openqa.selenium.JavascriptExecutor;
// import org.openqa.selenium.WebDriver;
// import org.openqa.selenium.WebElement;
// import org.openqa.selenium.support.ui.ExpectedConditions;
// import org.openqa.selenium.support.ui.WebDriverWait;

// public class WebQuestion {
//     WebDriver driver;

//     public WebQuestion(WebDriver driver) {
//         this.driver = driver;
//     }

// public void webQuestion() throws Exception {
//     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

//     List<WebElement> webLanguages = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
//             By.xpath("//div[@class='ant-tabs-tab' or @class='ant-tabs-tab ant-tabs-tab-active']")));

//     System.out.println("Total web Languages : " + webLanguages.size());

//     for (WebElement tab : webLanguages) {
//         String fileName = tab.getAttribute("data-node-key");
//         System.out.println("Tab Detected: " + fileName);

//         // Try clicking the tab, fallback to JS click if intercepted
//         try {
//             tab.click();
//         } catch (ElementClickInterceptedException e) {
//             System.out.println("Click intercepted on " + fileName + ". Trying JavaScript click.");
//             ((JavascriptExecutor) driver).executeScript("arguments[0].click();", tab);
//         }

//         wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".ace_editor")));

//         switch (fileName) {
//             case "index.html":
//                 setAceCode("""
//                     <!DOCTYPE html>
//                     <html>
//                     <head>
//                         <title>Simple Page</title>
//                         <link rel="stylesheet" href="index.css">
//                     </head>
//                     <body>
//                         <h1>Welcome</h1>
//                         <p>This is a simple page.</p>
//                         <button id="clickBtn">Click Me</button>
//                         <script src="index.js"></script>
//                     </body>
//                     </html>
//                     """);
//                 Thread.sleep(1000);
//                 break;

//             case "index.css":
//                 setAceCode("""
//                     body {
//                         font-family: Arial, sans-serif;
//                         background-color: #f2f2f2;
//                         text-align: center;
//                         margin-top: 50px;
//                     }

//                     button {
//                         padding: 10px 20px;
//                         background-color: #4CAF50;
//                         border: none;
//                         color: white;
//                         font-size: 16px;
//                         cursor: pointer;
//                     }

//                     button:hover {
//                         background-color: #45a049;
//                     }
//                     """);
//                 Thread.sleep(1000);
//                 break;

//             case "index.js":
//                 setAceCode("""
//                     document.getElementById("clickBtn").addEventListener("click", function() {
//                         document.body.style.backgroundColor = "#e0f7fa";
//                     });
//                     """);
//                 Thread.sleep(1000);
//                 break;
//         }
//     }

//     // Wait and click submit button
//     WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(
//             By.xpath("//button/span[text()='submit']/..")));
//     submitBtn.click();
    

// }


//     // Ace Editor code injection
// private void setAceCode(String code) {
//     JavascriptExecutor js = (JavascriptExecutor) driver;
//     js.executeScript("""
//         let editors = document.querySelectorAll('.ace_editor');
//         let visibleEditor = Array.from(editors).find(e => e.offsetParent !== null);
//         if (visibleEditor) {
//             let editorInstance = ace.edit(visibleEditor);
//             editorInstance.setValue(arguments[0], -1);
//         }
//         """, code);
// }

// }
