package dto;
import entity.PhoneEntity;
import entity.PhoneType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneDto {
    private int id;
    private int idContact;
    @Length(max = 50, message = "countryCode should not be greater than 50")
    private String countryCode;
    @Length(max = 50, message = "operatorCode should not be greater than 50")
    private String operatorCode;
    @Length(max = 50, message = "phone should not be greater than 50")
    private String phone;
    @NotNull
    private PhoneType type;
    @Length(max = 500, message = "description should not be greater than 500")
    private String description;

    public PhoneDto(PhoneEntity phoneEntity) {
        id = phoneEntity.getId();
        idContact = phoneEntity.getContactEntity().getId();
        countryCode = phoneEntity.getCountryCode();
        operatorCode = phoneEntity.getOperatorCode();
        phone = phoneEntity.getPhone();
        type = phoneEntity.getType();
        description = phoneEntity.getDescription();
    }
}
