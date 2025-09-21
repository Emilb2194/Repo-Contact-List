package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import utilities.BasePage;
import utilities.Logs;

public class ContactListAddUserPage extends BasePage {

    //Localizadores de los campos para ingresar un nuevo user
    private final By firstNameAddUserLocator = By.id("firstName");
    private final By lastNameAddUserLocator = By.id("lastName");
    private final By emailAddUserLocator = By.id("email");
    private final By passwordAddUserLocator = By.id("password");

    //Botones para crear usuario o cancelar su creacion
    private final By submitButtonAddUserLocator = By.id("submit");
    private final By cancelButtonAddUserLocator = By.id("cancel");


    @Override
    public void waitPageToLoad() {
        Logs.info("Esperando a que la pagina de adherir usuario cargue");
        waitPage(firstNameAddUserLocator, this.getClass().getSimpleName());
    }

    @Override
    public void verifyPage() {
        Logs.info("Verficiando que la pagina de adherir usuario cargue cargo");
        Assertions.assertAll(
                () -> Assertions.assertTrue(find(firstNameAddUserLocator).isDisplayed()),
                () -> Assertions.assertTrue(find(lastNameAddUserLocator).isDisplayed()),
                () -> Assertions.assertTrue(find(emailAddUserLocator).isDisplayed()),
                () -> Assertions.assertTrue(find(passwordAddUserLocator).isDisplayed())
        );

    }

    //realiza click en el boton para cancelar la creacion de un nuevo usuario
    public void clickCancel() {
        find(cancelButtonAddUserLocator).click();
    }

    //Completa el formulario para agregar un usuario nuevo y lo envia
    public void fillAddUserform(String name, String lastName, String email, String password) {
        find(firstNameAddUserLocator).sendKeys(name);
        find(lastNameAddUserLocator).sendKeys(lastName);
        find(emailAddUserLocator).sendKeys(email);
        find(passwordAddUserLocator).sendKeys(password);
        find(submitButtonAddUserLocator).click();

    }
}
