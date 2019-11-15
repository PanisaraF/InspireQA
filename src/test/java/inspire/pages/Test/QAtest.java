package inspire.pages.Test;


import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class QAtest {

    public WebDriver driver;

    @BeforeMethod
    public void setUp() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
        driver.get("https://www.inspire.com/");
        driver.findElement(By.xpath("//a[@class='btn-header btn-header-login']")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("email")).sendKeys("panisaraappleid@gmail.com");
        driver.findElement(By.id("pw")).sendKeys("TAK123tak!");
        driver.findElement(By.name("submit")).click();
        Thread.sleep(3000);


    }



    @Test
    public void Test1() throws InterruptedException {
        //Click on “Start A Post (Discussion Or Journal)”
        driver.findElement(By.xpath("/html[1]/body[1]/main[1]/div[1]/div[1]/form[1]/div[1]/a[1]")).click();

        Thread.sleep(2000);
        // Verify the overlay pops up.
        String expectPopup ="Start a Discussion in One Community";
        String actualPopUp = driver.findElement(By.id("post-overlay-h2-title")).getText();

        Assert.assertEquals(expectPopup,actualPopUp);

        //Click on "Journal Entry"
        driver.findElement(By.xpath("//a[contains(text(),'Journal Entry')]")).click();

        //Create a title in the “Title” input field.
        driver.findElement(By.id("title")).sendKeys("qaTest");
        Thread.sleep(1000);

        //write some content in the “Body” textarea.
        driver.findElement(By.xpath("//div[@id='post-modal']//textarea[1]")).sendKeys("Inspire");

        Thread.sleep(1000);
        //Under the "Where should I post my journal entry" heading, select the "Inspire friends" radio button.
        // (With a new account, you won’t have friends yet so this will post just to you.)
        WebElement dropdown = driver.findElement(By.id("privacy"));
        Select dropdownSelect = new Select(dropdown);
        dropdownSelect.selectByValue("Inspire friends");

        //Click Post
        driver.findElement(By.id("post-submit")).click();

       // Verify that your post shows up at the top of the lists of posts in your journal by looking for the text you used to input.
    Thread.sleep(1000);
        String expectTopic = "qaTest";
        String actualTopic =  driver.findElement(By.xpath("//*[starts-with(@id,'jrnl-')]")).getText();

       Assert.assertEquals(expectTopic,actualTopic);

    }


    @AfterMethod
    public void tearDown(){

        driver.quit();
    }


}






