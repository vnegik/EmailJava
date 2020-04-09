import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class EmailTest {
    private static final String PASSWORD = "voin12wert";
    private static final String LOGIN = "vitalynickolaenko";
    private static final String START_EMAIL_URL = "https://yandex.by/";
    private static final String EMAIL = "nva87k@gmail.com";
    private static final String SUBJECT = "Test email.";
    private static final String BODY = "Test email. Body.";
    private static final String WEB_DRIVER_PATH = "src\\test\\Resourses\\chromedriver.exe";

    private WebDriver driver;

    @BeforeClass(description = "Start browser")
    public void startBrowser() {
        System.setProperty("webdriver.chrome.driver", WEB_DRIVER_PATH);
        driver = new ChromeDriver();
    }

    @BeforeClass(dependsOnMethods = "startBrowser", description = "Add implicite wait and maximize window")
    public void addImplicitly() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(START_EMAIL_URL);
    }

    @Test(description = "Login to Email account. Assert, that the login is successful.", priority=1)
    public void loginToEmail() {
        new LogInPage(driver).doLogin(LOGIN, PASSWORD);
        Assert.assertTrue(new IncomingEmailPage(driver).emailBoxOpens(), "Looks you are NOT logged in correctly!");
        System.out.println("Login was completed.");
    }

    @Test(description = "Create new email.", priority=2)
    public void createEmail () {
        new IncomingEmailPage(driver).openNewEmailPage().fillEmailFiels(EMAIL, SUBJECT, BODY);
        new NewEmailPage(driver).SendEmail();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assert.assertTrue(new IncomingEmailPage(driver).incomingEmailPageOpens(), "Incorrect folder opens");
        System.out.println("Email is sent");
    }

    @Test(description = "Check that email is in send folder. LogOut", priority=3)
    public void checkoutEmailIsIInSent () {
        new IncomingEmailPage(driver).goToSentFolder();
        Assert.assertTrue(new SentEmailPage(driver).findSentEmail(), "Email is not send. It does not locate in Sent Folder" );
        System.out.println("Email is in Send Folder.");
        new SentEmailPage(driver).logOut();
    }

    @AfterClass(description = "Stop Browser")
    public void stopBrowser() {
        driver.quit();
    }
}
