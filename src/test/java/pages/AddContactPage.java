package pages;

import modelos.Contactos;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.BasePage;
import utilities.Logs;

import java.time.Duration;

// Clase que representa la página de "Agregar Contacto" en la aplicación web.
// Extiende de BasePage, lo que permite reutilizar métodos comunes como find(), waitPage(), etc.
public class AddContactPage extends BasePage {

    // Localizadores de los campos del formulario
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

    // Método que espera a que los elementos clave de la página estén visibles.
    // Se usa para asegurar que la página esté completamente cargada antes de interactuar.
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

    // Método para verificar que la página esté correctamente cargada.
    // Actualmente vacío, pero puede usarse para validaciones adicionales (título, URL, etc.).
    @Override
    public void verifyPage() {
        // Implementar validaciones específicas si se requiere
    }

    // Método que hace clic en el botón de "Submit" del formulario.
    public void clickSubmit() {
        find(buttonSubmitLocator).click();
    }

    // Método principal para agregar un contacto.
    // Completa el formulario, lo envía y opcionalmente prepara el formulario para un nuevo contacto.
    public void agregarContacto(Contactos contacto, boolean prepararSiguiente) {
        completarFormulario(contacto);
        enviarFormulario();

        if (prepararSiguiente) {
            prepararFormularioNuevo();
        }
    }

    // Completa cada campo del formulario con los datos del objeto Contactos.
    public void completarFormulario(Contactos contacto) {
        completarCampo(nameLocator, contacto.getName());
        completarCampo(lastnameLocator, contacto.getLastName());
        completarCampo(birthdateLocator, contacto.getBirthdate());
        completarCampo(emailLocator, contacto.getEmail());
        completarCampo(phoneLocator, contacto.getPhone());
        completarCampo(addressLocator, contacto.getAddress());
        completarCampo(cityLocator, contacto.getCity());
        completarCampo(stateLocator, contacto.getState());
        completarCampo(zipcodeLocator, contacto.getZipCode());
        completarCampo(contryLocator, contacto.getContry());
    }

    // Método reutilizable para completar un campo: limpia el valor actual y escribe el nuevo.
    private void completarCampo(By locator, String valor) {
        WebElement campo = find(locator);
        campo.clear();
        campo.sendKeys(valor);
    }

    // Método que envía el formulario haciendo clic en el botón de "Submit".
    public void enviarFormulario() {
        find(buttonSubmitLocator).click();
    }

    // Prepara el formulario para agregar un nuevo contacto.
    // Navega a la página de lista de contactos, hace clic en "Agregar contacto" y espera que el formulario esté vacío.
    public void prepararFormularioNuevo() {
        ContactListHomePage contactListHomePage = new ContactListHomePage();
        contactListHomePage.waitUntilAddContactButtonIsVisible();
        contactListHomePage.clickAddContact();
        waitPageToLoad();
        waitUntilFormIsEmpty();
    }

    // Espera hasta que el campo "Nombre" esté vacío, indicando que el formulario está listo para un nuevo ingreso.
    public void waitUntilFormIsEmpty() {
        new WebDriverWait(getDriver(), Duration.ofSeconds(5)).until(driver ->
                find(nameLocator).getAttribute("value").isEmpty()
        );
    }
}

