package service.impl;

import dao.ContactDao;
import dao.InterfaceDao;
import dto.ContactDto;
import entity.ContactAddress;
import entity.ContactEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.ContactService;
import service.response.ContactListResponse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
//        return getById(idSaveElement);
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
    public void updateContact(ContactEntity obj) {
        contactDao.update(obj);
    }
}
