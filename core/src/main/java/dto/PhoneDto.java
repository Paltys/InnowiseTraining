package dto;

import entity.PhoneEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneDto {
    private int id;
    private int idContact;
    private String countryCode;
    private String operatorCode;
    private String phone;
    private String type;
    private String description;

    public PhoneDto(PhoneEntity phoneEntity) {
        id = phoneEntity.getId();
        idContact = phoneEntity.getContactEntity().getId();
        countryCode = phoneEntity.getCountryCode();
        operatorCode = phoneEntity.getOperatorCode();
        phone = phoneEntity.getPhone();
        type = phoneEntity.getType().toString();
        description = phoneEntity.getDescription();
    }

}
