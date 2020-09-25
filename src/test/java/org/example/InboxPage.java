package org.example;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InboxPage {
    public WebDriver driver;

    public InboxPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//*[contains(@aria-label, 'Search mail')]")
    private WebElement searchInput;

    @FindBy(xpath = "//*[contains(@aria-label, 'Show more messages')]/span/span[2]")
    private WebElement lettersCountElement;

    @FindBy(xpath = "//*[contains(text(), 'Compose')]/..")
    private WebElement composeBtn;

    @FindBy(xpath = "//textarea[contains(@aria-label, 'To') and contains(@role, 'combobox')]")
    private WebElement recipientsInput;

    @FindBy(xpath = "//*[contains(@aria-label, 'Subject')]")
    private WebElement subjectInput;

    @FindBy(xpath = "//*[contains(@aria-label, 'Message Body') and contains(@role, 'textbox')]")
    private WebElement messageBodyInput;

    @FindBy(xpath = "//*[contains(@aria-label, 'Send')]")
    private WebElement sendBtn;

    @FindBy(xpath = "//*[contains(@aria-label, 'Google Account:')]")
    private WebElement userMenu;

    @FindBy(xpath = "//*[contains(@id, 'gb_71')]")
    private WebElement logoutBtn;

    public String getLettersCount() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@aria-label, 'search refinement')]")));
        String lettersCount = lettersCountElement.getText();
        return lettersCount;
    }

    public String getUserName() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@aria-label, 'Google Account:')]")));
        String userName = userMenu.getAttribute("aria-label");
        return userName;
    }

    public void inputSearchQuery(String query) {
        searchInput.sendKeys("from:" + query);
        searchInput.sendKeys(Keys.ENTER);
    }

    public void entryMenu() {
        userMenu.click();
    }

    public void entryLetterForm() {
        composeBtn.click();
    }

    public void inputRecipients(String recipients) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[contains(@aria-label, 'To') and contains(@role, 'combobox')]")));
        recipientsInput.sendKeys(recipients);
    }

    public void inputMessage(String message) {
        messageBodyInput.sendKeys(message);
    }

    public void inputSubject(String subject) {
        subjectInput.sendKeys(subject);
    }

    public void sendLetter() {
        sendBtn.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@id, 'link_undo')]")));
    }

    public void userLogout() {
        logoutBtn.click();
    }
}