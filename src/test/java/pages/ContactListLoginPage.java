package pages;

import org.openqa.selenium.By;
import utilities.BasePage;
import utilities.Logs;

public class ContactListLoginPage extends BasePage {
    //campos de email y contrasenia o clave
    private final By emailLoginLocator = By.id("email");
    private final By passwordLoginLocator = By.id("password");

    //botones para loguearse si se tiene un usuario o boton para crear un nuevo user
    private final By submitLoginLocator = By.id("submit");
    private final By signupLoginLocator = By.id("signup");


    @Override
    public void waitPageToLoad() {
        Logs.info("Esperando a que la pagina de adherir usuario cargue");
        waitPage(submitLoginLocator, this.getClass().getSimpleName());

    }

    @Override
    public void verifyPage() {

    }

    public void completeFormLogin(String email, String password) {
        find(emailLoginLocator).sendKeys(email);
        find(passwordLoginLocator).sendKeys(password);
        find(submitLoginLocator).click();
    }
}
