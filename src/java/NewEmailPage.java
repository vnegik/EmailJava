
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class NewEmailPage extends AbstractMailPage {
    private static final String locatorForSendButton = "//span[contains(text(),'Отправить')]/../../..";
    private static final String locatorForBodyField = "//div[@placeholder='Напишите что-нибудь']";
    private static final String locatorForSubjectField = "//input[@class='composeTextField ComposeSubject-TextField']";
    private static final String locatorForAdressField = "(//div[@class='composeYabbles'])[1]";
    private static final String locatorForReturnToIncomingButton = "//a[contains(text(),'Вернуться во')]";
    @FindBy(xpath = locatorForAdressField)
    private WebElement addressField ;
    @FindBy(xpath = locatorForSubjectField)
    private WebElement subjectField;
    @FindBy(xpath = locatorForBodyField)
    private WebElement bodyField;
    @FindBy(xpath = locatorForSendButton)
    private WebElement sendButton;
    @FindBy(xpath = locatorForReturnToIncomingButton)
    private WebElement returnToIncomingButton;

    public NewEmailPage(WebDriver driver) {
        super(driver);
    }

    public void  fillEmailFiels (String adress, String subject, String body) {
        waiter.waitingForElement(addressField, locatorForAdressField);
        Actions actions = new Actions(driver);
        actions.moveToElement(addressField);
        actions.click();
        actions.sendKeys(adress);
        actions.build().perform();
        System.out.println("Adrees field have been filed: " + adress);
        waiter.waitingForElement(subjectField, locatorForSubjectField);
        subjectField.clear();
        subjectField.sendKeys(subject);
        System.out.println("Subject field have been filed: " + subject);
        waiter.waitingForElement(bodyField, locatorForBodyField);
        bodyField.clear();
        bodyField.sendKeys(body);
        System.out.println("Subject field have been filed: " + body);
    }

    public IncomingEmailPage SendEmail() {
        waiter.waitingForElement(sendButton, locatorForSendButton);
        sendButton.click();
        waiter.waitingForElement(returnToIncomingButton, locatorForReturnToIncomingButton);
        returnToIncomingButton.click();
        return new IncomingEmailPage(driver);
    }
}
