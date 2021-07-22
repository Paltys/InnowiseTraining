package service;


import dto.ContactDto;
import dto.RequestContactDto;
import dto.SearchContactDto;
import service.response.ContactListResponse;

public interface ContactService {

    ContactListResponse getAllContact(int size, int number);

    ContactDto getById(int id);

    int createNewContact(RequestContactDto obj);

    void deleteContact(int id);

    void updateContact(RequestContactDto obj, int id);

    ContactListResponse findBy(int size, int number, SearchContactDto searchContactDto);
}

