package dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import entity.Gender;
import entity.MaritalStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchContactDto {
    @Length(max = 50, message = "first name should not be greater than 50")
    private String firstName;
    @Length(max = 50, message = "last name should not be greater than 50")
    private String lastName;
    @Length(max = 50, message = "middle name should not be greater than 50")
    private String middleName;
    @Past(message = "birthday data should not be future")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    private Instant dataBirthday;
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

    private String prepareSearch(String field) {
        return String.join("", "%", field.trim(), "%");
    }

    public String getSearchFirstName() {
        return prepareSearch(firstName);
    }

    public String getSearchLastName() {
        return prepareSearch(lastName);
    }

    public String getSearchMiddleName() {
        return prepareSearch(middleName);
    }

    public String getSearchDataBirthday() {return String.valueOf(dataBirthday); }

    public String getSearchGender() {
        return prepareSearch(gender.toString());
    }

    public String getSearchCitizenship() {
        return prepareSearch(citizenship);
    }

    public String getSearchMaritalStatus() {
        return prepareSearch(maritalStatus.toString());
    }

    public String getSearchWebsite() {
        return prepareSearch(website);
    }

    public String getSearchEmail() {
        return prepareSearch(email);
    }

    public String getSearchWorkplace() {
        return prepareSearch(workplace);
    }

    public String getSearchCountry() {
        return prepareSearch(country);
    }

    public String getSearchTown() {
        return prepareSearch(town);
    }

    public String getSearchStreet() {
        return prepareSearch(street);
    }

    public String getSearchHouse() {
        return prepareSearch(house);
    }

    public String getSearchFlat() {
        return prepareSearch(flat);
    }

    public String getSearchAddressIndex() {
        return prepareSearch(addressIndex);
    }

    public String getSearchAvatarUrl() {
        return prepareSearch(avatarUrl);
    }
}
