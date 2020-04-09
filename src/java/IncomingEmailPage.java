import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class IncomingEmailPage extends AbstractMailPage {
    private static final String locatorForWriteButton = "//a[@title='Написать (w, c)']";
    private static final String locatorForYourEmailBox = "//div[@data-key=\"view=head-user\"]";
    private static final String locatorForSentFolderButton = "//*[.='Отправленные']";
    private static final String INCOMING_EMAIL_PAGE_TITLE = "Входящие — Яндекс.Почта";
    @FindBy(xpath = locatorForYourEmailBox)
    private WebElement yourEmailBox;
    @FindBy (xpath = locatorForWriteButton)
    private WebElement writeButton;
    @FindBy (xpath = locatorForSentFolderButton)
    private WebElement sentFolderButton;

    public IncomingEmailPage(WebDriver driver) {
        super(driver);
    }

    public boolean emailBoxOpens() {
        waiter.waitingForElement(yourEmailBox, locatorForYourEmailBox);
        return yourEmailBox.isDisplayed();
    }

    public NewEmailPage openNewEmailPage () {
        waiter.waitingForElement(writeButton, locatorForWriteButton);
        writeButton.click();
        return new NewEmailPage(driver);
    }

    public boolean incomingEmailPageOpens() {
        String actualTitle = driver.getTitle();
        return actualTitle.contains(INCOMING_EMAIL_PAGE_TITLE);
    }

    public SentEmailPage goToSentFolder() {
        waiter.waitingForElement(sentFolderButton, locatorForSentFolderButton);
        sentFolderButton.click();
        return new SentEmailPage(driver);
    }
}