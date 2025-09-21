package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.BasePage;

import java.time.Duration;
import java.util.List;

public class ContactListHomePage extends BasePage {

    private final By buttonAddContactLocator = By.xpath("//button[text()='Add a New Contact']");
    private final By contactsAddLocator = By.cssSelector("table#myTable tr.contactTableBodyRow");

    //Si agregue registros se vera la siguiente tabla
    private final By firstRegisLocator = By.xpath("//tr[contains(., 'mm.2195@gmail.com')]");

    @Override
    public void waitPageToLoad() {
        waitPage(buttonAddContactLocator, this.getClass().getSimpleName());

    }

    @Override
    public void verifyPage() {

    }

    public void clickAddContact() {
        find(buttonAddContactLocator).click();
    }

    public void waitUntilAddContactButtonIsVisible() {
        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(buttonAddContactLocator));
    }

    public void verifyAddContacts() {

        new WebDriverWait(getDriver(), Duration.ofSeconds(6))
                .until(ExpectedConditions.numberOfElementsToBeMoreThan(contactsAddLocator, 0));

        // Localizamos todos los <tr> con clase contactTableBodyRow
        List<WebElement> filas = findAll(contactsAddLocator);

        int total = filas.size();

        // Validamos que haya exactamente 3 registros
        Assertions.assertAll(
                () -> Assertions.assertEquals(3, total, "La cantidad de registros en la tabla no es la esperada")
        );

    }

    public void clickFirstElementOfList() {
        find(firstRegisLocator).click();
    }

}
