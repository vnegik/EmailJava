import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class LogInPage extends AbstractMailPage {
    private static final String locatorForLoginField = "//input[@name='login']";
    private static final String locatorForPasswordField = "//input[@name='passwd']";
    private static final String locatorForSubmitForm = "//button[@type='submit']";
    private static final String locatorForEnterInEmailBox = "(.//div[@role='form']/a)[1]";
    private static final String locatorForEnterButtonForTheSecondForm = "//button[@class='passport-Button']";
    @FindBy(xpath = locatorForLoginField)
    private WebElement loginField;
    @FindBy(xpath = locatorForPasswordField)
    private WebElement passwordField;
    @FindBy(xpath = locatorForSubmitForm)
    private WebElement submitForm;
    @FindBy(xpath = locatorForEnterInEmailBox)
    private WebElement enterInEmailBox;
    @FindBy(xpath = locatorForEnterButtonForTheSecondForm)
    private WebElement enterButtonForTheSecondForm;

    public LogInPage (WebDriver driver) {
        super (driver);
    }

    public void LoginIfOnlyLoginFieldIsDisplayed(String login, String password) {
        System.out.println("Typing user login: " + login);
        waiter.waitingForElement(loginField, locatorForLoginField);
        loginField.clear();
        loginField.sendKeys(login);
        waiter.waitingForElement(submitForm, locatorForSubmitForm);
        submitForm.click();
        waiter.waitingForElement(passwordField, locatorForPasswordField);
        System.out.println("Typing user password: " + password);
        passwordField.clear();
        passwordField.sendKeys(password);
        waiter.waitingForElement(submitForm, locatorForSubmitForm);
        submitForm.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void LoginIfLoginAndPasswordFieldsAreDisplayed(String login, String password) {
        System.out.println("Typing user login: " + login);
        waiter.waitingForElement(loginField, locatorForLoginField);
        loginField.clear();
        loginField.sendKeys(login);
        System.out.println("Typing user password: " + password);
        passwordField.clear();
        passwordField.sendKeys(password);
        enterButtonForTheSecondForm = driver.findElement(By.xpath(locatorForEnterButtonForTheSecondForm));
        waiter.waitingForElement(enterButtonForTheSecondForm, locatorForEnterButtonForTheSecondForm);
        enterButtonForTheSecondForm.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public IncomingEmailPage doLogin(String login, String password) {
        System.out.println("Click Button 'Войти в почту' " + login);
        enterInEmailBox = driver.findElement(By.xpath(locatorForEnterInEmailBox));
        waiter.waitingForElement(enterInEmailBox, locatorForEnterInEmailBox);
        enterInEmailBox.click();
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        waiter.waitingForElement(loginField, locatorForLoginField);
        if (submitForm.isDisplayed())
        {
            LoginIfOnlyLoginFieldIsDisplayed(login, password);
        }
        else
        {
            LoginIfLoginAndPasswordFieldsAreDisplayed(login, password);
        }
        return new IncomingEmailPage(driver);
    }
}