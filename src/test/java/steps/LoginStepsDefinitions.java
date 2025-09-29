package steps;

import com.github.javafaker.Faker;
import data.DataGiver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.ContactListHomePage;
import pages.ContactListLoginPage;
import utilities.CommonFlows;

public class LoginStepsDefinitions {
    CommonFlows commonFlows = new CommonFlows();
    ContactListLoginPage contactListLoginPage = new ContactListLoginPage();
    ContactListHomePage contactListHomePage = new ContactListHomePage();

    @Given("El usuario navegara a la pagina de login")
    public void navegarPaginaDeLogin() {
        commonFlows.goToLoginPage();

    }


    @When("completar formulario de login y hacer click en el boton Login")
    public void escribirCredenciales() {
        final var credenciales = DataGiver.getValidCredencitial();
        contactListLoginPage.completeFormLogin(credenciales.email(), credenciales.password());


    }

    @When("completar formulario de login con mail invalido y hacer click en el boton Login")
    public void invalidMail() {
        Faker dato = new Faker();
        var mail = dato.internet().emailAddress();
        final var credenciales = DataGiver.getValidCredencitial();
        contactListLoginPage.completeFormLogin(mail, credenciales.password());
    }

    @Then("navega a la home page validando cada elemento dentro de la misma")
    public void navegaToHomePage() {
        contactListHomePage.waitPageToLoad();
        var tituloHome = contactListHomePage.tituloDelHome();
        contactListHomePage.verifyHomePage(tituloHome);
    }

    @Then("devuelve un mensaje de error")
    public void devuelveUnMensajeDeError() {
        contactListLoginPage.verifyMessageError();
    }


}
