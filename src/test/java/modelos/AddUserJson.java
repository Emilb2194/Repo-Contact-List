package modelos; // Declara que esta clase pertenece al paquete 'modelos'

// Importa la anotacion JsonProperty de la libreria Jackson para mapear JSON a objetos Java

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

// Clase modelo que representa un objeto JSON que contiene un conjunto de credenciales
public class AddUserJson {

    // Este campo representa un mapa de credenciales
    // Jackson lo vincula con la propiedad 'credentials' del JSON
    @JsonProperty("addUser")
    private Map<String, AddUser> mapCredenciales;

    // Metodo publico que permite acceder al mapa de credenciales
    public Map<String, AddUser> getMapCredenciales() {
        return mapCredenciales; // Devuelve el contenido del mapa
    }
}
