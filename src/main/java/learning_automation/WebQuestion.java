package learning_automation;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebQuestion {
    WebDriver driver;

    public WebQuestion(WebDriver driver) {
        this.driver = driver;
    }

    public void webQuestion() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<WebElement> webLanguages = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//div[@class='ant-tabs-tab' or @class='ant-tabs-tab ant-tabs-tab-active']")));
        System.out.println("Total web Languages : " + webLanguages.size());
        for (WebElement tab : webLanguages) {
            String tabText = tab.getText().trim();
            System.out.println("Tab: " + tabText);

            switch (tabText) {
                case "index.html":

                    break;

                default:
                    break;
            }
        }
        driver.findElement(By.xpath("//div[@class = 'app-logo']/button")).click();
    }

}
