package service;


import exceptions.EntityNotFoundException;
import dto.ContactDto;
import dto.RequestContactDto;
import dto.SearchContactDto;
import service.response.ContactListResponse;

public interface ContactService {

    ContactListResponse getAllContact(int size, int number);

    ContactDto getById(int id) throws EntityNotFoundException;

    int createNewContact(ContactDto obj);

    void deleteContact(int id) throws EntityNotFoundException;

    void updateContact(RequestContactDto obj, int id) throws EntityNotFoundException;

    ContactListResponse findBy(int size, int number, SearchContactDto searchContactDto);
}

