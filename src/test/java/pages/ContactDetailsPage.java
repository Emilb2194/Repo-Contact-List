package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.BasePage;
import utilities.Logs;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class ContactDetailsPage extends BasePage {

    //Botons Edit, Delete and Return
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

    public void deleteContactBoton() {
        find(buttonDeleteLocator).click();
    }

    public void returnToContactList() {
        find(buttonReturnLocator).click();
    }

    public void waitForVisibility(By locator) {
        new WebDriverWait(getDriver(), Duration.ofSeconds(4))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }


    public List<String> devolveraElementos() {

        waitForVisibility(lastNameLocator);
        waitForVisibility(phoneLocator);
        waitForVisibility(codigoPostalLocator);

        String lastName = find(lastNameLocator).getText();
        String phone = find(phoneLocator).getText();
        String postalCode = find(codigoPostalLocator).getText();

        return Arrays.asList(lastName, phone, postalCode);
    }

    public void validModifyContact(List<String> datosViejos) {
        System.out.printf("Los datos viejos son:" + datosViejos);
        Assertions.assertAll(
                () -> Assertions.assertNotEquals(datosViejos.get(0), find(lastNameLocator).getText()),
                () -> Assertions.assertNotEquals(datosViejos.get(1), find(phoneLocator).getText()),
                () -> Assertions.assertNotEquals(datosViejos.get(2), find(codigoPostalLocator).getText())
        );
    }

    public void deleteContacts() {
        ContactListHomePage contactListHomePage = new ContactListHomePage();
        contactListHomePage.esperarTablaCargada();

        // Bucle que se repite mientras haya filas en la tabla
        while (!contactListHomePage.obtenerFilasDeContactos().isEmpty()) {
            // Refrescar la tabla y obtener la primera fila
            contactListHomePage.esperarTablaCargada();
            List<WebElement> filas = contactListHomePage.obtenerFilasDeContactos();

            WebElement fila = filas.get(0); // Siempre tomamos la primera
            contactListHomePage.clickEnFila(fila);

            waitPageToLoad();
            deleteContactBoton();
            Alert alert = getDriver().switchTo().alert();
            alert.accept();
            // Refrescar la tabla y obtener la primera fila
            contactListHomePage.esperarTablaCargada();
        }
        Logs.info("✅ Todos los contactos fueron eliminados exitosamente.");
    }


}
