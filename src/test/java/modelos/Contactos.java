package modelos;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelSheet;


@ExcelSheet("ContactList")
public class Contactos {

    @ExcelCellName("NAME")
    private String name;
    @ExcelCellName("LASTNAME")
    private String lastName;
    @ExcelCellName("BIRTHDATE")
    private String birthdate;
    @ExcelCellName("EMAIL")
    private String email;
    @ExcelCellName("PHONE")
    private String phone;
    @ExcelCellName("ADDRESS")
    private String address;
    @ExcelCellName("CITY")
    private String city;
    @ExcelCellName("STATE/PROVINCE")
    private String state;
    @ExcelCellName("POSTAL CODE")
    private String zipCode;
    @ExcelCellName("COUNTRY")
    private String contry;

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getContry() {
        return contry;
    }
}
