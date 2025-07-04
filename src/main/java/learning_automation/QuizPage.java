package learning_automation;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.io.File;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ByClassName;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class QuizPage {
    WebDriver driver;

    public QuizPage(WebDriver driver) {
        this.driver = driver;
    }

    public void quizData() throws Exception {

        List<WebElement> sections = driver.findElements(By.xpath("//*[@class=\"dashboard-segment-container \"]"));

        for (WebElement section : sections) {

            List<WebElement> questions = driver.findElements(By.xpath("//*[@class=\"ant-table-tbody\"]/tr"));
            System.out.println(questions.size());
            for (WebElement question : questions) {
                String questionType = question.findElement(By.xpath(".//td[3]")).getText();
                switch (questionType) {
                    case "MCQ": {
                        question.findElement(By.xpath(".//td[2]/a")).click();
                        mcqQuestion();
                        return;
                    }
                    case "Web": {
                        question.findElement(By.xpath(".//td[2]/a")).click();
                        webQuestion();
                        return;
                    }
                    case "Subjective": {
                        question.findElement(By.xpath(".//td[2]/a")).click();
                        subjectiveQuestion();
                        return;
                    }
                    case "MQ": {
                        question.findElement(By.xpath(".//td[2]/a")).click();
                        mqQuestion();
                        return;
                    }
                    case "Coding": {
                        question.findElement(By.xpath(".//td[2]/a")).click();
                        coding();
                        return;
                    }
                }
            }
        }
    }

    public void mcqQuestion() throws Exception {

        List<WebElement> mcqOptions = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//span[@class='ant-radio']")));

        if (!mcqOptions.isEmpty()) {
            Random rand = new Random();
            int randomIndex = rand.nextInt(mcqOptions.size()); // generate random index
            WebElement randomOption = mcqOptions.get(randomIndex);
            randomOption.click();
        } else {
            System.out.println("No MCQ options found!");
        }
        driver.findElement(By.xpath("//button/span[text()='submit']")).click();
    }

    public void webQuestion() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<WebElement> webLanguages = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
            By.xpath("//div[@class='ant-tabs-tab' or @class='ant-tabs-tab ant-tabs-tab-active']")
        ));
        System.out.println("Total web Languages : " + webLanguages.size());
        for(WebElement tab : webLanguages){
            String tabText = tab.getText().trim();
            System.out.println("Tab: " + tabText);

            switch (tabText) {
                case "index.html":
                    
                    break;
            
                default:
                    break;
            }
        }

    }

    public void subjectiveQuestion() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement editor = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("div.ql-editor")));
        editor.click();
        editor.sendKeys("This is subjective question");

        try {
            String fileName = "src/test/resources/file-sample_PDF_1MB.pdf";
            File file = new File(fileName);
            if (!file.exists()) {
                System.out.println("File not found: ");
            } else {
                driver.findElement(By.id("upldFile")).sendKeys(file.getAbsolutePath());
            }

        } catch (Exception e) {
            System.out.println("Upload elements not found: ");
        }
        driver.findElement(By.xpath("//button/span[text()='submit']")).click();

    }

    public void mqQuestion() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<WebElement> textAreas = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//div[@class='ant-form-item-control-input-content']/span/textarea")));

        for (WebElement textArea : textAreas) {
            textArea.sendKeys("T");
        }
        driver.findElement(By.xpath("//button/span[text()='submit']")).click();

    }

    public void coding() throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector(".ant-select-selector")));
        dropdown.click();

        // 2. Get all language options from the dropdown

        Thread.sleep(1000);
        List<WebElement> languageOptions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.cssSelector(".ant-select-item-option-content")));
        List<String> languages = new ArrayList<>();
        for (WebElement option : languageOptions) {
            String lang = option.getText().trim();
            languages.add(lang);
            System.out.println("Found language: " + lang);
        }

        Random rand = new Random();
        int randomIndex = rand.nextInt(languages.size());
        String selectedLanguage = languages.get(randomIndex);
        System.out.println("Selected language: " + selectedLanguage);
        languageOptions.get(randomIndex).click();

        int version = rand.nextInt(4);

        String codeToSend = switch (selectedLanguage) {
            case "C" -> switch (version) {
                case 0 -> """
                        #include <stdio.h>
                        int main() {
                            int num;
                            scanf("%d", &num);
                            if (num % 2 == 0)
                                printf("even");
                            else
                                printf("odd");
                            return 0;
                        """;
                case 1 -> """
                        #include <stdio.h>
                        int main() {
                            int num;
                            scanf("%d", &num);
                            if (num % 2 == 0)
                                printf("odd");
                            else
                                printf("odd");
                            return 0;
                        """;
                case 2 -> """
                        #include <stdio.h>
                        int main() {
                            int num;
                            scanf("%d", &num);
                            if (num % 2 != 0)
                                printf("even");
                            else
                                printf("odd");
                            return 0;
                        """;
                default -> """
                        #include <stdio.h>
                        int main() {
                        printf("Hello world");
                            return 0;
                                """;
            };
            case "C++" -> switch (version) {
                case 0 -> """
                        #include <iostream>
                        using namespace std;

                        int main() {
                            int num;
                            cin >> num;
                            if (num % 2 == 0)
                                cout << "even";
                            else
                                cout << "odd";
                            return 0;
                        """;
                case 1 -> """
                        #include <iostream>
                        using namespace std;

                        int main() {
                            int num;
                            cin >> num;
                            if (num % 2 == 0)
                                cout << "odd";
                            else
                                cout << "odd";
                            return 0;
                        """;
                case 2 -> """
                        #include <iostream>
                        using namespace std;

                        int main() {
                            int num;
                            cin >> num;
                            if (num % 2 != 0)
                                cout << "even";
                            else
                                cout << "odd";
                            return 0;
                        """;
                default -> """
                            #include <iostream>
                        using namespace std;
                        int main() {
                        cout << "Hello world";
                        return 0;
                            """;
            };

            case "Java" -> switch (version) {
                case 0 -> """
                        import java.util.Scanner;

                        public class Main {
                            public static void main(String[] args) {
                                Scanner sc = new Scanner(System.in);
                                int num = sc.nextInt();
                                if (num % 2 == 0)
                                    System.out.println("even");
                                else
                                    System.out.println("odd");
                        """;
                case 1 -> """
                        import java.util.Scanner;

                        public class Main {
                            public static void main(String[] args) {
                                Scanner sc = new Scanner(System.in);
                                int num = sc.nextInt();
                                if (num % 2 == 0)
                                    System.out.println("odd");
                                else
                                    System.out.println("odd");
                        """;
                case 2 -> """
                        import java.util.Scanner;

                        public class Main {
                            public static void main(String[] args) {
                                Scanner sc = new Scanner(System.in);
                                int num = sc.nextInt();
                                if (num % 2 != 0)
                                    System.out.println("even");
                                else
                                    System.out.println("odd");
                        """;
                default -> """
                        import java.util.Scanner;

                        public class Main {
                        public static void main(String[] args) {
                            system.out.println("Hello world");
                                """;
            };
            case "Python 3" -> switch (version) {
                case 0 -> """
                        num = int(input())
                        if num % 2 == 0:
                            print("even")
                        else:
                            print("odd")
                        """;
                case 1 -> """
                        num = int(input())
                        if num % 2 == 0:
                            print("odd")
                        else:
                            print("odd")
                        """;

                case 2 -> """
                        num = int(input())
                        if num % 2 != 0:
                            print("even")
                        else:
                            print("odd")
                        """;
                default -> """
                        print("Hello world")
                        """;

            };
            default -> "// No code for selected language";
        };

        WebElement aceTextarea = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("textarea.ace_text-input")));

        // Clear any existing code
        aceTextarea.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        aceTextarea.sendKeys(Keys.DELETE);
        for (String line : codeToSend.split("\n")) {
            aceTextarea.sendKeys(line);
            aceTextarea.sendKeys(Keys.ENTER);
        }
        driver.findElement(By.xpath("//button/span[text()='submit']")).click();

        WebElement nextQuestionButton = wait.until(
        ExpectedConditions.elementToBeClickable(
        By.xpath("//button//span[text()='next question']")));

        nextQuestionButton.click();
    }

}
