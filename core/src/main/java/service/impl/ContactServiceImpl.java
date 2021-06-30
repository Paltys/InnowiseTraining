package service.impl;

import dao.ContactDao;
import dao.InterfaceDao;
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
    private InterfaceDao<ContactEntity> contactDao;



    public List<ContactDto> getAllContact(int count, int page) {

        List<ContactEntity> contactList = contactDao.getAll(count, page);

        List<ContactDto> contactDtoList = new ArrayList();

        for (ContactEntity contact : contactList) {
            ContactDto contactDto = new ContactDto(contact);
            contactDtoList.add(contactDto);
        }





        return contactDtoList;

    }

//    public ContactDto getById() {
//        ContactDto contactDto = new ContactDto(contactDao.getById());
//        return contactDto;
//
//    }

}
