import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractMailPage {
    protected WebDriver driver;
    protected Waiting waiter;

    public AbstractMailPage(WebDriver driver) {
        this.driver = driver;
        this.waiter = new Waiter(driver);
        PageFactory.initElements(this.driver, this);
    }
}