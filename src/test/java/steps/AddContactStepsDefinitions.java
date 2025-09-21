package steps;

import data.DataGiver;
import data.excel.ExcelReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import modelos.Contactos;
import pages.AddContactPage;
import pages.ContactListHomePage;
import pages.ContactListLoginPage;
import utilities.CommonFlows;

public class AddContactStepsDefinitions {
    private final CommonFlows commonFlows = new CommonFlows();
    private final ContactListLoginPage contactListLoginPage = new ContactListLoginPage();
    private final ContactListHomePage contactListHomePage = new ContactListHomePage();
    private final AddContactPage addContactPage = new AddContactPage();


    @Given("Luego de averse logueado el usuario aniade contactos haciendo click en el boton Add a New Contact")
    public void addNewContact() {
        final var usuario = DataGiver.getValidCredencitial();
        commonFlows.goToLoginPage();
        contactListLoginPage.waitPageToLoad();
        contactListLoginPage.completeFormLogin(usuario.email(), usuario.password());
        contactListHomePage.waitPageToLoad();
        contactListHomePage.clickAddContact();
    }

    @When("completa los datos para agregar un nuevo contacto y preciona el boton submit")
    public void completeFormAddUser() {
        final var listaContactos = ExcelReader.leerListaContactos();
        System.out.printf("%s", listaContactos.size());

        for (int i = 0; i < listaContactos.size(); i++) {
            Contactos contacto = listaContactos.get(i);

            addContactPage.completeFormContact(
                    contacto.getName(),
                    contacto.getLastName(),
                    contacto.getBirthdate(),
                    contacto.getEmail(),
                    contacto.getPhone(),
                    contacto.getAddress(),
                    contacto.getCity(),
                    contacto.getState(),
                    contacto.getZipCode(),
                    contacto.getContry()
            );

            addContactPage.clickSubmit();

            if (i < (listaContactos.size() - 1)) {
                // Solo después del primer envío, volvemos a cargar el formulario
                contactListHomePage.waitUntilAddContactButtonIsVisible();
                contactListHomePage.clickAddContact();
                addContactPage.waitPageToLoad();
                addContactPage.waitUntilFormIsEmpty();
            }
        }

    }

    @Then("validar la existencia de los registros creados dentro de la tabla")
    public void verifyContactsAdded() {
        contactListHomePage.verifyAddContacts();
    }
}
