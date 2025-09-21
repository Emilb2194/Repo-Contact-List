package modelos; // Declara que esta clase pertenece al paquete 'modelos'

// Importa la anotacion JsonProperty desde la libreria Jackson

import com.fasterxml.jackson.annotation.JsonProperty;

// Define la clase AddUser que representa un objeto con datos de login
public class AddUser {

    // Anota que esta propiedad debe serializarse con el nombre 'firstName' en JSON
    @JsonProperty("firstName")
    private String firstName; // Almacena el nombre del usuario

    // Anota que esta propiedad debe serializarse como 'lastName' en JSON
    @JsonProperty("lastName")
    private String lastName; // Almacena el apellido del usuario

    // Anota que esta propiedad debe serializarse como 'email' en JSON
    @JsonProperty("email")
    private String email; // Almacena el email de usuario

    // Anota que esta propiedad debe serializarse como 'password' en JSON
    @JsonProperty("password")
    private String password; // Almacena el password de usuario


    // Metodo publico que devuelve el valor del campo nroDocumento
    public String firstName() {
        return firstName;
    }

    // Metodo publico que devuelve el valor del campo lastName
    public String lastName() {
        return lastName;
    }

    // Metodo publico que devuelve el valor del campo usuario
    public String email() {
        return email;
    }

    // Metodo publico que devuelve el valor del campo password
    public String password() {
        return password;
    }
}
