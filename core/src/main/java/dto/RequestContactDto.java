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

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestContactDto {
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
    private String street;
    private String house;
    private String flat;
    private String addressIndex;
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
        contactEntity.setDataBirthday(Instant.parse(dataBirthday));
        contactEntity.setGender(Gender.valueOf(gender));
        contactEntity.setCitizenship(citizenship);
        contactEntity.setMaritalStatus(Maritalstatus.valueOf(maritalStatus));
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
            phoneEntity.setType(PhoneType.valueOf(phone.getType()));
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
            attachmentEntity.setLoadDate(Instant.parse(attachment.getLoadDate()));
            attachmentEntity.setUpdateDate(Instant.parse(attachment.getUpdateDate()));
            attachmentEntity.setComment(attachment.getComments());
            attachmentEntity.setType(attachment.getType());

            if (attachment.getDeleteDate() == "null")
                attachmentEntity.setDeleteDate(null);
            else {
                attachmentEntity.setDeleteDate((Instant.parse(attachment.getDeleteDate())));
            }

            attachmentList.add(attachmentEntity);
        }
        return attachmentList;

    }
}