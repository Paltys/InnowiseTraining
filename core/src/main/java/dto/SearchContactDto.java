package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchContactDto {
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
    private String street;
    private String house;
    private String flat;
    private String addressIndex;
    private String avatarUrl;
}
