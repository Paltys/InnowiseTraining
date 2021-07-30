package dto;

import entity.ContactEntity;
import entity.Gender;
import entity.MaritalStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.ZoneId;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactDto {
    private int id;
    @NotBlank(message = "firstName cannot be null")
    @Length(max = 50, message = "first name should not be greater than 50")
    private String firstName;
    @NotBlank(message = "lastName cannot be null")
    @Length(max = 50, message = "last name should not be greater than 50")
    private String lastName;
    @Length(max = 50, message = "middle name should not be greater than 50")
    private String middleName;
    private String birthday;
    private Gender gender;
    @Length(max = 50, message = "citizenship should not be greater than 50")
    private String citizenship;
    private MaritalStatus maritalStatus;
    @Length(max = 50, message = "workplace should not be greater than 50")
    private String website;
    @Email(message = "its not be email")
    private String email;
    @Length(max = 100, message = "workplace should not be greater than 100")
    private String workplace;
    @Length(max = 50, message = "country should not be greater than 50")
    private String country;
    @Length(max = 50, message = "town should not be greater than 50")
    private String town;
    @Length(max = 50, message = "street should not be greater than 50")
    private String street;
    @Length(max = 50, message = "house should not be greater than 50")
    private String house;
    @Length(max = 50, message = "flat should not be greater than 50")
    private String flat;
    @Length(max = 50, message = "addressIndex should not be greater than 50")
    private String addressIndex;
    @Length(max = 50, message = "avatarUrl should not be greater than 50")
    private String avatarUrl;

    public ContactDto(ContactEntity contact) {
        id = contact.getId();
        firstName = contact.getFirstName();
        lastName = contact.getLastName();
        middleName = contact.getMiddleName();
        birthday = LocalDate.ofInstant(contact.getBirthday(),ZoneId.systemDefault()).toString();
        gender = contact.getGender();
        citizenship = contact.getCitizenship();
        maritalStatus = contact.getMaritalStatus();
        website = contact.getWebsite();
        email = contact.getEmail();
        workplace = contact.getWorkplace();
        country = contact.getContactAddressEmbeddable().getCountry();
        town = contact.getContactAddressEmbeddable().getTown();
        street = contact.getContactAddressEmbeddable().getStreet();
        house = contact.getContactAddressEmbeddable().getHouse();
        flat = contact.getContactAddressEmbeddable().getFlat();
        addressIndex = contact.getContactAddressEmbeddable().getAddressIndex();
        avatarUrl=contact.getAvatarUrl();
    }

}
