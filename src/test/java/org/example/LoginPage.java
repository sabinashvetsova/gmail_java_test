package org.example;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    public WebDriver driver;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//*[contains(@id, 'identifierId')]")
    private WebElement loginField;

    @FindBy(xpath = "//*[contains(text(), 'Далее')]/..")
    private WebElement loginBtn;

    @FindBy(xpath = "//*[contains(@name, 'password')]")
    private WebElement passwdField;

    public void inputLogin(String login) {
        loginField.sendKeys(login);
    }

    public void inputPasswd(String passwd) {
        passwdField.sendKeys(passwd);
    }

    public void clickLoginBtn() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Далее')]/..")));
        loginBtn.click();
    }
}