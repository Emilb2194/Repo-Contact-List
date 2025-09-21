package utilities;

import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {
    private final static String screenshotPath = "src/test/resources/screenshots/";
    private final static String pageStructurePath = "src/test/resources/pageStructures/";

    public static void getScreenshot(String screenshotName) {
        Logs.debug("Tomando screenshot");

        final var screenshotFile = ((TakesScreenshot) new WebdriverProvider().get()).getScreenshotAs(OutputType.FILE);

        final var path = String.format("%s%s.png", screenshotPath, screenshotName);

        try {
            FileUtils.copyFile(screenshotFile, new File(path));
        } catch (IOException ioException) {
            Logs.error("Error al tomar el screenshot: %s", ioException.getLocalizedMessage());
        }
    }

    public static void getPageSource(String fileName) {
        Logs.debug("Tomando el pageu source de la pagina");

        final var path = String.format("%s%s.html", pageStructurePath, fileName);

        try {
            final var file = new File(path);

            Logs.debug("Creando los directorios padres si es que no existen");
            if (file.getParentFile().mkdirs()) {
                final var fileWriter = new FileWriter(file);
                final var pageSource = new WebdriverProvider().get().getPageSource();
                fileWriter.write(Jsoup.parse(pageSource).toString());
                fileWriter.close();
            }
        } catch (Exception ioException) {
            Logs.error("Error al obtener el page source: %s", ioException.getLocalizedMessage());
        }
    }

    public static void deletePreviusEvidence() {
        try {
            Logs.debug("Borrando evidencia anterior");
            FileUtils.deleteDirectory(new File(screenshotPath));
            FileUtils.deleteDirectory(new File(pageStructurePath));
        } catch (IOException ioException) {
            Logs.error(
                    "Error al borrar la evidencia anterio: %s",
                    ioException.getLocalizedMessage()
            );
        }
    }

    public static void attachScreenshot(Scenario scenario) {
        final var screenshotFile = ((TakesScreenshot) new WebdriverProvider().
                get()).
                getScreenshotAs(OutputType.BYTES);

        scenario.attach(
                screenshotFile,
                "image/png",
                scenario.getName()
        );
    }

    public static void attachePageSource(Scenario scenario) {
        final var pagesource = new WebdriverProvider().get().getPageSource();
        final var parsedPageSource = Jsoup.parse(pagesource).toString();

        scenario.attach(
                parsedPageSource,
                "text/plain",
                scenario.getName()
        );
    }
}
