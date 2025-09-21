package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import utilities.BasePage;
import utilities.Logs;

import java.util.Arrays;
import java.util.List;

public class ContactDetailsPage extends BasePage {

    //Butons Edit, Delete and Return
    private final By buttonEditLocator = By.cssSelector("#edit-contact");
    private final By buttonDeleteLocator = By.cssSelector("#delete");
    private final By buttonReturnLocator = By.cssSelector("#return");

    //Elementos de los campos a modificar
    private final By lastNameLocator = By.id("lastName");
    private final By phoneLocator = By.id("phone");
    private final By codigoPostalLocator = By.id("postalCode");

    @Override
    public void waitPageToLoad() {
        Logs.info("Esperando a que la pagina de adherir usuario cargue");
        waitPage(buttonEditLocator, this.getClass().getSimpleName());
        waitPage(buttonDeleteLocator, this.getClass().getSimpleName());
        waitPage(buttonReturnLocator, this.getClass().getSimpleName());
    }

    @Override
    public void verifyPage() {

    }

    public void editContact() {
        find(buttonEditLocator).click();
    }

    public void deleteContact() {
        find(buttonDeleteLocator).click();
    }

    public void returnToContactList() {
        find(buttonReturnLocator).click();
    }

    public List<String> guardaElementos() {
        String lastName = find(lastNameLocator).getText();
        String phone = find(phoneLocator).getText();
        String postalCode = find(codigoPostalLocator).getText();

        return Arrays.asList(lastName, phone, postalCode);
    }

    public void validModifyContact(List<String> datosViejos) {
        Assertions.assertAll(
                () -> Assertions.assertNotEquals(datosViejos.get(0), find(lastNameLocator).getText()),
                () -> Assertions.assertNotEquals(datosViejos.get(1), find(lastNameLocator).getText()),
                () -> Assertions.assertNotEquals(datosViejos.get(0), find(lastNameLocator).getText())
        );
    }

}
