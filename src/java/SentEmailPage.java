import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

public class SentEmailPage extends AbstractMailPage{
    private static final String locatorForEmailInFolder = "(.//span[@title='nva87k@gmail.com'])[1]";
    private static final String locatorForUserTitle = "//div[@data-key=\"view=head-user\"]";
    private static final String locatorForExit = "//a[@data-metric='Sign out of Yandex services']";
    @FindBy(xpath = locatorForEmailInFolder)
    private WebElement emailInFolder;
    @FindBy(xpath = locatorForUserTitle)
    private WebElement userTitle;
    @FindBy(xpath = locatorForExit)
    private WebElement exit;

    public SentEmailPage(WebDriver driver) {
        super(driver);
    }

    public boolean findSentEmail () {
        waiter.waitingForElement(emailInFolder, locatorForEmailInFolder);
        return emailInFolder.isDisplayed();
    }

    public LogInPage logOut () {
        waiter.waitingForElement(userTitle, locatorForUserTitle);
        userTitle.click();
        waiter.waitingForElement(exit, locatorForExit);
        exit.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        return new LogInPage(driver);
    }
}
