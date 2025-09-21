package utilities;

import org.openqa.selenium.WebDriver;
import pages.ContactListAddUserPage;
import pages.ContactListLoginPage;

public class CommonFlows {

    private WebDriver getDriver() {
        return new WebdriverProvider().get();
    }

    public void goToSignUpAddUser() {


        Logs.info("Navegando a la url");
        getDriver().get("https://thinking-tester-contact-list.herokuapp.com/addUser");

        new ContactListAddUserPage().waitPageToLoad();//espero que cargue la pagina
    }

    public void goToLoginPage() {


        Logs.info("Navegando a la url");
        getDriver().get("https://thinking-tester-contact-list.herokuapp.com/login");

        new ContactListLoginPage().waitPageToLoad();//espero que cargue la pagina
    }

}
