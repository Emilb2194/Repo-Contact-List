package steps;

import data.DataGiver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pages.ContactListAddUserPage;
import utilities.CommonFlows;

public class AddUsertStepsDefinitions {
    private final CommonFlows commonFlows = new CommonFlows();
    private final ContactListAddUserPage contactListAddUserPage = new ContactListAddUserPage();


    @Given("El usuario navegara a la pagina de adduser")
    public void navigateToAddUserPage() {
        commonFlows.goToSignUpAddUser();
    }

    @Then("completar formulario de adduser y hacer click en el boton submit")
    public void completarFormularioDeAdduserYHacerClickEnElBotonSubmit() {
        final var usuario = DataGiver.getValidCredencitial();
        contactListAddUserPage.fillAddUserform(usuario.firstName(), usuario.lastName(), usuario.email(), usuario.password());
    }
}
