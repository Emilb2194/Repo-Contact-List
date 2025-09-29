package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.BasePage;
import utilities.Logs;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContactListHomePage extends BasePage {

    //Cabecera de la pagina esta compuesta de 2 elementos
    private final By tituloLocator = By.xpath("//h1[text()='Contact List']");
    private final By logoutButonLocator = By.cssSelector("button[id='logout']");

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

    public void clickLogOut() {
        find(logoutButonLocator).click();
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
        assertAll(
                () -> Assertions.assertEquals(3, total, "La cantidad de registros en la tabla no es la esperada")
        );

    }

    public void clickFirstElementOfList() {
        find(firstRegisLocator).click();
    }

    public String tituloDelHome() {
        return getDriver().getTitle();
    }

    public void verifyHomePage(String titulo) {
        assertAll(
                () -> Assertions.assertEquals("My Contacts", titulo),
                () -> assertTrue(find(logoutButonLocator).isDisplayed()),
                () -> Assertions.assertEquals("Contact List", find(tituloLocator).getText())

        );
    }

    /**
     * Espera a que la tabla de contactos esté visible y tenga al menos una fila.
     * Ideal para usar antes de interactuar con la lista de contactos.
     */
    public void esperarTablaCargada() {
        Logs.info("Esperando que la tabla de contactos esté cargada");

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(4));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("table.contactTable")));


        Logs.info("✅ Tabla cargada con contactos visibles");
    }

    /**
     * Devuelve todas las filas visibles de la tabla de contactos.
     * Cada fila representa un contacto que puede ser seleccionado.
     */
    public List<WebElement> obtenerFilasDeContactos() {
        return getDriver().findElements(By.cssSelector("tr.contactTableBodyRow"));
    }

    /**
     * Realiza clic sobre una fila de la tabla de contactos.
     * Esto redirige a la página de detalle del contacto seleccionado.
     *
     * @param fila WebElement que representa una fila de la tabla
     */
    public void clickEnFila(WebElement fila) {
        Logs.info("Clic en fila de contacto: " + fila.getText());
        fila.click();
    }

    public void tableIsEmpty() {
        ContactListHomePage contactListHomePage = new ContactListHomePage();
        List<WebElement> filasRestantes = contactListHomePage.obtenerFilasDeContactos();

        assertAll("Validación de tabla vacía",
                () -> assertNotNull(filasRestantes, "La lista de filas no debería ser null"),
                () -> assertTrue(filasRestantes.isEmpty(), "❌ La tabla aún contiene filas de datos")
        );

        Logs.info("✅ Validación post-eliminación: la tabla está vacía.");
    }


}
