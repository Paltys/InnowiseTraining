package service.impl;

import dao.InterfaceDao;
import dto.ContactDto;
import entity.ContactEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.ContactService;
import service.response.ContactListResponse;

import java.util.ArrayList;
import java.util.List;

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

}
