package data.excel;

import com.poiji.bind.Poiji;
import modelos.Contactos;

import java.io.File;
import java.util.List;

public class ExcelReader {
    private static final String excelPath = "src/test/resources/excel/Contactos.xlsx";

    public static List<Contactos> leerListaContactos() {
        return Poiji.fromExcel(new File(excelPath), Contactos.class);
    }

}
