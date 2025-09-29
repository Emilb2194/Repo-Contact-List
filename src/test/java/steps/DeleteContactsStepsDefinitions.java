package steps;

import data.DataGiver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.ContactDetailsPage;
import pages.ContactListHomePage;
import pages.ContactListLoginPage;
import utilities.CommonFlows;


public class DeleteContactsStepsDefinitions {
    private final CommonFlows commonFlows = new CommonFlows();
    private final ContactListLoginPage contactListLoginPage = new ContactListLoginPage();
    private final ContactListHomePage contactListHomePage = new ContactListHomePage();
    private final ContactDetailsPage contactDetailsPage = new ContactDetailsPage();


    @Given("Luego de realizar doble click sobre un contacto agregado nos redirecciona a su detalle")
    public void detalleContacto() {
        final var usuario = DataGiver.getValidCredencitial();
        commonFlows.goToLoginPage();
        contactListLoginPage.waitPageToLoad();
        contactListLoginPage.completeFormLogin(usuario.email(), usuario.password());
        contactListHomePage.waitPageToLoad();
    }

    @When("cuando presionamos el boton Delete Contact redirige pantalla home realizamos el proceso para eliminar todos los contactos")
    public void deleteContacts() {
        contactDetailsPage.deleteContacts();
    }

    @Then("validar que los datos han sido eliminados")
    public void validarQueLosDatosHanSidoEliminados() {
        contactListHomePage.tableIsEmpty();
    }
}
