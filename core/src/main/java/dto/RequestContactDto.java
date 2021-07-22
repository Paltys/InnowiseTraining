package dto;
import entity.AttachmentEntity;
import entity.ContactAddressEmbeddable;
import entity.ContactEntity;
import entity.Gender;
import entity.Maritalstatus;
import entity.PhoneEntity;
import entity.PhoneType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestContactDto {
    @NotNull(message = "id cannot be null")
    @Length(message = "id should not be greater than 2147483647" )
    private int id;
    @NotNull(message = "firstName cannot be null")
    @Length(max = 50, message = "first name should not be greater than 50")
    private String firstName;
    @NotNull(message = "lastName cannot be null")
    @Length(max = 50, message = "last name should not be greater than 50")
    private String lastName;
    @Length(max = 50, message = "middle name should not be greater than 50")
    private String middleName;
    @Past(message = "birthday data should not be future")
    private Instant dataBirthday;
    private Gender gender;
    @Length(max = 50, message = "citizenship should not be greater than 50")
    private String citizenship;
    private Maritalstatus maritalStatus;
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
    private List<PhoneDto> phoneDto;
    private List<AttachmentDto> attachmentDto;


    public ContactEntity getContactEntity() {
        ContactEntity contactEntity = new ContactEntity();
        ContactAddressEmbeddable contactAddressEmbeddable = new ContactAddressEmbeddable(
                country, town, street, house, flat, addressIndex);
        contactEntity.setId(id);
        contactEntity.setFirstName(firstName);
        contactEntity.setLastName(lastName);
        contactEntity.setMiddleName(middleName);
        contactEntity.setDataBirthday(dataBirthday);
        contactEntity.setGender(Gender.valueOf(gender.toString()));
        contactEntity.setCitizenship(citizenship);
        contactEntity.setMaritalStatus(Maritalstatus.valueOf(maritalStatus.toString()));
        contactEntity.setWebsite(website);
        contactEntity.setEmail(email);
        contactEntity.setWorkplace(workplace);
        contactEntity.setContactAddressEmbeddable(contactAddressEmbeddable);
        contactEntity.setAvatarUrl(avatarUrl);
        return contactEntity;
    }

    public List<PhoneEntity> getPhoneEntity() {
        List<PhoneEntity> phoneList = new ArrayList<>();

        for (PhoneDto phone : phoneDto) {
            PhoneEntity phoneEntity = new PhoneEntity();
            phoneEntity.setContactEntity(getContactEntity());
            phoneEntity.setCountryCode(phone.getCountryCode());
            phoneEntity.setOperatorCode(phone.getOperatorCode());
            phoneEntity.setPhone(phone.getPhone());
            phoneEntity.setType(PhoneType.valueOf(phone.toString()));
            phoneEntity.setDescription(phone.getDescription());

            phoneList.add(phoneEntity);
        }
        return phoneList;
    }

    public List<AttachmentEntity> getAttachmentEntity() {
        List<AttachmentEntity> attachmentList = new ArrayList<>();

        for (AttachmentDto attachment : attachmentDto) {
            AttachmentEntity attachmentEntity = new AttachmentEntity();
            attachmentEntity.setName(attachment.getName());
            attachmentEntity.setUrl(attachment.getUrl());
            attachmentEntity.setLoadDate(attachment.getLoadDate());
            attachmentEntity.setUpdateDate(attachment.getUpdateDate());
            attachmentEntity.setComment(attachment.getComments());
            attachmentEntity.setType(String.valueOf(id));
            if (attachment.getDeleteDate() == null)
                attachmentEntity.setDeleteDate(null);
            else {
                attachmentEntity.setDeleteDate(attachment.getDeleteDate());
            }
            attachmentList.add(attachmentEntity);
        }
        return attachmentList;
    }
}