package steps;

import data.DataGiver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.ContactDetailsPage;
import pages.ContactListHomePage;
import pages.ContactListLoginPage;
import pages.EditContactPage;
import utilities.CommonFlows;

import java.util.List;

public class ModifyContactStepsDefinitions {
    private final CommonFlows commonFlows = new CommonFlows();
    private final ContactListLoginPage contactListLoginPage = new ContactListLoginPage();
    private final ContactListHomePage contactListHomePage = new ContactListHomePage();
    private final ContactDetailsPage contactDetailsPage = new ContactDetailsPage();
    private final EditContactPage editContactPage = new EditContactPage();


    @Given("Luego de realizar doble click sobre un contacto agregado nos redirecciona a detalle del mismo")
    public void contactDetail() {
        final var usuario = DataGiver.getValidCredencitial();
        commonFlows.goToLoginPage();
        contactListLoginPage.waitPageToLoad();
        contactListLoginPage.completeFormLogin(usuario.email(), usuario.password());
        contactListHomePage.waitPageToLoad();
        contactListHomePage.verifyAddContacts();
        contactListHomePage.clickFirstElementOfList();


    }

    @When("cuando presionamos el boton editar navegamos a la pantalla de edicion modificamos y enviamos")
    public void modifyContact() {


    }

    @Then("validar que los datos han sido modificados")
    public void validarQueLosDatosHanSidoModificados() {
        List<String> datosLista = contactDetailsPage.guardaElementos();
        contactDetailsPage.editContact();
        editContactPage.modificarElementos();
        contactDetailsPage.validModifyContact(datosLista);
    }
}
