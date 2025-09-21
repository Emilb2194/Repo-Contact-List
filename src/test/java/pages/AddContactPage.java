package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.BasePage;
import utilities.Logs;

import java.time.Duration;

public class AddContactPage extends BasePage {
    private final By nameLocator = By.id("firstName");
    private final By lastnameLocator = By.id("lastName");
    private final By birthdateLocator = By.id("birthdate");
    private final By emailLocator = By.id("email");
    private final By phoneLocator = By.id("phone");
    private final By addressLocator = By.id("street1");
    private final By cityLocator = By.id("city");
    private final By stateLocator = By.id("stateProvince");
    private final By zipcodeLocator = By.id("postalCode");
    private final By contryLocator = By.id("country");
    private final By buttonSubmitLocator = By.id("submit");


    @Override
    public void waitPageToLoad() {
        Logs.info("Esperando a que la pagina de adherir usuario cargue");
        waitPage(nameLocator, this.getClass().getSimpleName());
        waitPage(lastnameLocator, this.getClass().getSimpleName());
        waitPage(birthdateLocator, this.getClass().getSimpleName());
        waitPage(emailLocator, this.getClass().getSimpleName());
        waitPage(phoneLocator, this.getClass().getSimpleName());
        waitPage(addressLocator, this.getClass().getSimpleName());
    }

    @Override
    public void verifyPage() {

    }

    public void completeFormContact(
            String name,
            String lastName,
            String birthdate,
            String email,
            String phone,
            String address,
            String city,
            String state,
            String zipcode,
            String contry
    ) {
        find(nameLocator).clear();
        find(nameLocator).sendKeys(name);

        find(lastnameLocator).clear();
        find(lastnameLocator).sendKeys(lastName);

        find(birthdateLocator).clear();
        find(birthdateLocator).sendKeys(birthdate);

        find(emailLocator).clear();
        find(emailLocator).sendKeys(email);

        find(phoneLocator).clear();
        find(phoneLocator).sendKeys(phone);

        find(addressLocator).clear();
        find(addressLocator).sendKeys(address);

        find(cityLocator).clear();
        find(cityLocator).sendKeys(city);

        find(stateLocator).clear();
        find(stateLocator).sendKeys(state);

        find(zipcodeLocator).clear();
        find(zipcodeLocator).sendKeys(zipcode);

        find(contryLocator).clear();
        find(contryLocator).sendKeys(contry);


    }

    public void clickSubmit() {
        find(buttonSubmitLocator).click();
    }

    public void waitUntilFormIsEmpty() {
        new WebDriverWait(getDriver(), Duration.ofSeconds(5)).until(driver ->
                find(nameLocator).getAttribute("value").isEmpty()
        );
    }

}
