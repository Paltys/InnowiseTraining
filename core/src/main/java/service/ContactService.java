package service;


import dto.ContactDto;
import entity.ContactEntity;
import service.response.ContactListResponse;

public interface ContactService {

    ContactListResponse getAllContact(int size, int number);

    ContactDto getById(int id);

    int createNewContact(ContactEntity obj);

    void deleteContact(int id);

    void updateContact(ContactDto obj, int id);
}

