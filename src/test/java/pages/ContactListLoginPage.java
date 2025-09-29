package pages;

import org.junit.jupiter.api.Assertions;
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

    //mensaje de error locator
    private final By errorMessageLocator = By.xpath("//span[text()='Incorrect username or password']");

    @Override
    public void waitPageToLoad() {
        Logs.info("Esperando a que la pagina de adherir usuario cargue");
        waitPage(submitLoginLocator, this.getClass().getSimpleName());

    }

    @Override
    public void verifyPage() {
        var titulo = getDriver().getTitle();
        Assertions.assertAll(
                () -> Assertions.assertEquals("Contact List App", titulo, "El titulo no es el esperado"),
                () -> Assertions.assertTrue(find(signupLoginLocator).isDisplayed()),
                () -> Assertions.assertTrue(find(emailLoginLocator).isDisplayed()),
                () -> Assertions.assertTrue(find(passwordLoginLocator).isDisplayed())
        );

    }

    public void completeFormLogin(String email, String password) {
        find(emailLoginLocator).sendKeys(email);
        find(passwordLoginLocator).sendKeys(password);
        find(submitLoginLocator).click();
    }

    public void verifyMessageError() {
        Logs.info("Esperando mensaje de error en login");
        waitPage(errorMessageLocator, "Mensaje de error de login");

        var errorText = find(errorMessageLocator).getText();
        Logs.info("📛 Texto recibido: %s", errorText);

        Assertions.assertAll(
                () -> Assertions.assertTrue(errorText.contains("Incorrect username"), "Texto inesperado: " + errorText),
                () -> Assertions.assertTrue(find(errorMessageLocator).isDisplayed(), "El mensaje no se muestra"),
                () -> Assertions.assertEquals("Incorrect username or password", errorText)
        );


    }
}
