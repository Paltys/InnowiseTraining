package service.impl;

import dao.ContactDao;
import dto.ContactDto;
import entity.ContactEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.ContactService;
import service.response.ContactResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactDao contactDao;



    public List<ContactDto> getAllContact() {

        List<ContactEntity> contactList = contactDao.getAll();

        List<ContactDto> contactDtoList = new ArrayList();

        for (ContactEntity contact : contactList) {
            ContactDto contactDto = new ContactDto(contact);
            contactDtoList.add(contactDto);
        }

        ContactResponse contactResponse = new ContactResponse(contactDtoList);

//        return contactResponse;



        return contactDtoList;

    }

//    public ContactDto getById() {
//        ContactDto contactDto = new ContactDto(contactDao.getById());
//        return contactDto;
//
//    }

}
