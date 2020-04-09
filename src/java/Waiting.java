import org.openqa.selenium.WebElement;

public interface Waiting {
    void waitingForElement(WebElement element, String locator);
}
