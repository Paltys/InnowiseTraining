package dto;

import entity.Gender;
import entity.MaritalStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestContactDto {
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
    @Length(max = 200, message = "avatarUrl should not be greater than 50")
    private String avatarUrl;
    private List<PhoneDto> phoneDto;
    private List<AttachmentDto> attachmentDto;

    public RequestContactDto(ContactDto contactDto, List<AttachmentDto> attachmentDtoList, List<PhoneDto> phoneDtoList) {
        this.id = contactDto.getId();
        this.firstName = contactDto.getFirstName();
        this.lastName = contactDto.getLastName();
        this.middleName = contactDto.getMiddleName();
        this.birthday = contactDto.getBirthday();
        this.gender = contactDto.getGender();
        this.citizenship = contactDto.getCitizenship();
        this.maritalStatus = contactDto.getMaritalStatus();
        this.website = contactDto.getWebsite();
        this.email = contactDto.getEmail();
        this.workplace = contactDto.getWorkplace();
        this.country = contactDto.getCountry();
        this.town = contactDto.getTown();
        this.street = contactDto.getStreet();
        this.house = contactDto.getHouse();
        this.flat = contactDto.getFlat();
        this.addressIndex = contactDto.getAddressIndex();
        attachmentDto = attachmentDtoList;
        phoneDto = phoneDtoList;
    }
}