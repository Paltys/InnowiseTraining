package dto;

import entity.ContactEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactDto {

    private int id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String dataBirthday;
    private String gender;
    private String citizenship;
    private String maritalStatus;
    private String website;
    private String email;
    private String workplace;
    private String country;
    private String town;
    private String house;
    private String flat;
    private int adressIndex;

    public ContactDto(ContactEntity contact) {
        id = contact.getId();
        firstName = contact.getFirstName();
        lastName = contact.getLastName();
        middleName = contact.getMiddleName();
        dataBirthday = contact.getDataBirthday().toString();
        gender = contact.getGender().toString();
        citizenship = contact.getCitizenship();
        maritalStatus = contact.getMaritalStatus().toString();
        website = contact.getWebsite();
        email = contact.getEmail();
        workplace = contact.getWorkplace();
        country = contact.getContactAddress().getCountry();
        town = contact.getContactAddress().getTown();
        house = contact.getContactAddress().getHouse();
        flat = contact.getContactAddress().getFlat();
        adressIndex = contact.getContactAddress().getAdressIndex();
    }

}
