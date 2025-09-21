package steps;

import data.DataGiver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginStepsDefinitions {


    @Given("El usuario navegara a la pagina de login")
    public void navegarPaginaDeLogin() {
    }


    @When("completar formulario de login y hacer click en el boton Login")
    public void escribirCredenciales() {
        final var credenciales = DataGiver.getValidCredencitial();


    }

    @Then("navega a la home page y el usuario valida los botones Logout y Delete account")
    public void navegaToHomePage() {
    }

}
