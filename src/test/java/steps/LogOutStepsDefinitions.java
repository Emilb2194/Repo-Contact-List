package steps;

import data.DataGiver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.ContactListHomePage;
import pages.ContactListLoginPage;
import utilities.CommonFlows;

public class LogOutStepsDefinitions {

    CommonFlows commonFlows = new CommonFlows();
    ContactListLoginPage contactListLoginPage = new ContactListLoginPage();
    ContactListHomePage contactListHomePage = new ContactListHomePage();

    @Given("El usuario se logueara dentro de la pagina")
    public void elUsuarioSeLoguearaDentroDeLaPagina() {
        commonFlows.goToLoginPage();
        final var credenciales = DataGiver.getValidCredencitial();
        contactListLoginPage.completeFormLogin(credenciales.email(), credenciales.password());
    }

    @When("navega a la home y luego procede a precionar el boton Logout")
    public void navegaALaHomeYLuegoProcedeAPrecionarElBotonLogout() {
        contactListHomePage.waitPageToLoad();
        contactListHomePage.clickLogOut();
    }

    @Then("navega a la pagina de login nuevamente")
    public void navegaALaPaginaDeLoginNuevamente() {
        contactListLoginPage.waitPageToLoad();
        contactListLoginPage.verifyPage();
    }
}
