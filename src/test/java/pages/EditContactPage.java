package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import utilities.BasePage;

public class EditContactPage extends BasePage {

    //titulo
    private final By titleLocator = By.tagName("h1");

    //Elementos editables
    private final By lastNameLocator = By.id("lastName");
    private final By phoneLocator = By.id("phone");
    private final By postalCodeLocator = By.id("postalCode");

    //Boton Submit
    private final By submitButtonLocator = By.id("submit");


    @Override
    public void waitPageToLoad() {
        waitPage(titleLocator, this.getClass().getSimpleName());
    }

    @Override
    public void verifyPage() {

    }

    public void modificarElementos() {
        waitPageToLoad();

        //Generando datos aliatoriios con faker
        Faker datos = new Faker();
        String lastName = datos.name().lastName();
        String phone = datos.phoneNumber().cellPhone();
        String postalCode = datos.address().zipCode();

        find(lastNameLocator).click();
        find(lastNameLocator).clear();
        find(lastNameLocator).sendKeys(lastName);
        find(phoneLocator).clear();
        find(phoneLocator).sendKeys(phone);
        find(postalCodeLocator).clear();
        find(postalCodeLocator).sendKeys(postalCode);
        find(submitButtonLocator).click();


    }


}
