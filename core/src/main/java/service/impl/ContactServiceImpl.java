package service.impl;


import dao.InterfaceDao;
import dto.ContactDto;

import entity.ContactAddressEmbeddable;
import entity.ContactEntity;
import entity.Gender;
import entity.Maritalstatus;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.ContactService;
import service.response.ContactListResponse;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private InterfaceDao<ContactEntity> contactDao;


    public ContactListResponse getAllContact(int size, int number) {

        List<ContactEntity> contactList = contactDao.getAll(size, number);

        List<ContactDto> contactDtoList = new ArrayList<>();

        for (ContactEntity contact : contactList) {
            ContactDto contactDto = new ContactDto(contact);
            contactDtoList.add(contactDto);
        }
        int allsize = contactDao.count();

        ContactListResponse contactResponce = new ContactListResponse(contactDtoList, allsize);
        return contactResponce;
    }

    @Override
    public ContactDto getById(int id) {
        Optional<ContactEntity> optionalContactEntity = contactDao.getById(id);
        if (!optionalContactEntity.isPresent()) {
            throw new RuntimeException();
        }
        ContactEntity contact = optionalContactEntity.get();
        ContactDto contactDto = new ContactDto(contact);
        return contactDto;
    }

    public int createNewContact(ContactEntity newContact){
        return  (int)contactDao.create(newContact);

    }

    @Override
    public void deleteContact(int id) {
        Optional<ContactEntity> optionalContactEntity = contactDao.getById(id);
        if (!optionalContactEntity.isPresent()) {
            throw new RuntimeException();
        }
        ContactEntity contact = optionalContactEntity.get();
        contactDao.delete(contact);
    }

    @Override
    public void updateContact(ContactDto obj, int id) {
        ContactEntity contactEntity = new ContactEntity();
        ContactAddressEmbeddable contactAddressEmbeddable = new ContactAddressEmbeddable(obj.getCountry(),obj.getTown(),obj.getStreet(),obj.getHouse(),obj.getFlat(),obj.getAddressIndex());
        contactEntity.setId(id);
        contactEntity.setFirstName(obj.getFirstName());
        contactEntity.setLastName(obj.getLastName());
        contactEntity.setMiddleName(obj.getMiddleName());
        contactEntity.setDataBirthday(Instant.parse(obj.getDataBirthday()));
        contactEntity.setGender(Gender.valueOf(obj.getGender()));
        contactEntity.setCitizenship(obj.getCitizenship());
        contactEntity.setMaritalStatus(Maritalstatus.valueOf(obj.getMaritalStatus()));
        contactEntity.setWebsite(obj.getWebsite());
        contactEntity.setEmail(obj.getEmail());
        contactEntity.setWorkplace(obj.getWorkplace());
        contactEntity.setContactAddressEmbeddable(contactAddressEmbeddable);

        contactDao.update(contactEntity);
    }
}
