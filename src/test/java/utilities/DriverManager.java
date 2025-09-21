package utilities;

import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

public class DriverManager {
    private final boolean runServer = System.getenv("JOV_NAME") != null;

    public void buildDriver() {
        if (runServer) {
            buildRemoteDriver();
        } else {
            buildLocalDriver();
        }
    }

    public void killDriver() {
        Logs.debug("Matando el driver");
        // new WebdriverProvider().get().quit();
    }

    private void buildLocalDriver() {
        final var headlessMode = System.getProperty("headless") != null;

        ChromeOptions options = new ChromeOptions(); // Se declara una sola vez y se usará correctamente más abajo

        var browserProperty = System.getProperty("browser");

        if (browserProperty == null) {
            Logs.debug("Asignando default driver a CHROME");
            browserProperty = "CHROME";
        }

        final var browser = Browser.valueOf(browserProperty.toUpperCase());

        Logs.debug("Inicializqanando el driver %s", browser);
        Logs.debug("Headless mode? %b", headlessMode);

        final var driver = switch (browser) {
            case CHROME -> {
                // Usamos directamente el objeto 'options' configurado arriba
                options.addArguments("--incognito"); // ✅ Activamos modo incógnito correctamente
                if (headlessMode) {
                    options.addArguments("--headless=new"); // Agregamos headless si aplica
                }
                yield new ChromeDriver(options); // ✅ Usamos el objeto correcto con todas las opciones aplicadas
            }
            case EDGE -> {
                final var edgeOptions = new EdgeOptions();
                if (headlessMode) {
                    edgeOptions.addArguments("--headless=new");
                }
                yield new EdgeDriver(edgeOptions);
            }
            case FIREFOX -> {
                final var firefoxOptions = new FirefoxOptions();
                if (headlessMode) {
                    firefoxOptions.addArguments("--headless");
                }
                yield new FirefoxDriver(firefoxOptions);
            }
            case SAFARI -> new SafariDriver();
        };

        Logs.debug("Maximizando la pantalla");
        try {
            driver.manage().window().maximize();
        } catch (WebDriverException e) {
            Logs.info("No se pudo maximizar la ventana: %s", e.getMessage());
        }

        Logs.debug("Borrando las cookies");
        driver.manage().deleteAllCookies();

        Logs.debug("Seteando implicit wait de 5 segundos");

        Logs.debug("Asignando driver al webdriver provider");
        new WebdriverProvider().set(driver);
    }


    private void buildRemoteDriver() {

    }

    private enum Browser {
        CHROME,
        FIREFOX,
        EDGE,
        SAFARI
    }
}
