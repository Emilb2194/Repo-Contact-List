package data;

import modelos.AddUser;

import java.util.Map;

public class DataGiver {
    private static Map<String, AddUser> obtenerMapCredenciales() {
        return JsonReader.obtenerMapCredenciales().getMapCredenciales();
    }

    public static AddUser getValidCredencitial() {
        return obtenerMapCredenciales().get("usuario1");
    }

}
