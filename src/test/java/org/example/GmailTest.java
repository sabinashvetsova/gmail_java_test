package org.example;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
public class GmailTest {
    public static LoginPage loginPage;
    public static InboxPage profilePage;
    public static WebDriver driver;
    public static String lettersCount;

    @BeforeClass
    public static void setup() throws MalformedURLException {
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        driver = new RemoteWebDriver(new URL("http://172.19.0.1:4444/wd/hub"), capabilities);
        loginPage = new LoginPage(driver);
        profilePage = new InboxPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(ConfProperties.getProperty("loginpage")); }

    @Test
    public void loginTest() {
        loginPage.inputLogin(ConfProperties.getProperty("login"));
        loginPage.clickLoginBtn();
        loginPage.inputPasswd(ConfProperties.getProperty("password"));
        loginPage.clickLoginBtn();
        String user = profilePage.getUserName();
        boolean isLoginCorrect = user.contains(ConfProperties.getProperty("login"));
        Assert.assertTrue(isLoginCorrect);
    }

    @Test
    public void sendLetterTest() {
        profilePage.inputSearchQuery(ConfProperties.getProperty("searchemail"));
        lettersCount = profilePage.getLettersCount();
        Assert.assertEquals(ConfProperties.getProperty("letterscount"), lettersCount);
        profilePage.entryLetterForm();
        profilePage.inputRecipients(ConfProperties.getProperty("searchemail"));
        profilePage.inputSubject("Тестовое задание. Швецова");
        profilePage.inputMessage(lettersCount);
        profilePage.sendLetter();
    }

    @AfterClass
    public static void tearDown() {
        profilePage.entryMenu();
        profilePage.userLogout();
        driver.quit();
    }
}