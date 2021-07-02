package service;


import dto.ContactDto;
import service.response.ContactListResponse;

public interface ContactService {
    ContactListResponse getAllContact(int size, int number);

    ContactDto getById(int id);
}

