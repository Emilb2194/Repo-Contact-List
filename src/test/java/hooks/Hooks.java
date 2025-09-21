package hooks; // Define el paquete donde se agrupa esta clase

// Importaciones necesarias para las anotaciones de Cucumber y clases utilitarias

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import utilities.DriverManager;
import utilities.FileManager;
import utilities.Logs;


public class Hooks {

    // Crea una instancia estática y final de DriverManager para gestionar el navegador
    private static final DriverManager driverManager = new DriverManager();

    // Este metodo se ejecuta una vez, antes de que empiecen todos los escenarios
    @BeforeAll
    public static void beforeAll() {
        Logs.info("beforeAll"); // Escribe en los logs
        FileManager.deletePreviusEvidence(); // Limpia la evidencia previa (screenshots, etc.)
    }

    // Este metodo se ejecuta una vez, después de que finalicen todos los escenarios
    @AfterAll
    public static void afterAll() {
        Logs.info("AfterAll"); // Solo escribe en los logs para marcar el final de la suite
        

    }

    // Este metodo se ejecuta antes de cada escenario individual
    @Before
    public static void before(Scenario scenario) {
        Logs.info("before: %s", scenario.getName()); // Log del nombre del escenario
        driverManager.buildDriver(); // Inicializa el navegador (driver de Selenium u otro)
    }

    // Este metodo se ejecuta después de cada escenario individual
    @After
    public static void after(Scenario scenario) {
        // Log del nombre del escenario y su estado final (PASSED, FAILED, SKIPPED, etc.)
        Logs.info("after: %s, status %s", scenario.getName(), scenario.getStatus());

        // Si el escenario fue omitido o falló, se recolectan pruebas
        switch (scenario.getStatus()) {
            case SKIPPED, FAILED -> {
                FileManager.getScreenshot(scenario.getName()); // Toma screenshot
                FileManager.getPageSource(scenario.getName()); // Guarda HTML actual
                FileManager.attachScreenshot(scenario); // Adjunta la captura al reporte
                FileManager.attachePageSource(scenario); // Adjunta el HTML al reporte
            }
        }

        driverManager.killDriver(); // Cierra el navegador y limpia la sesión
    }
}

