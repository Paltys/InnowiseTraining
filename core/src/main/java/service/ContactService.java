package service;


import service.response.ContactListResponse;

public interface ContactService {
    ContactListResponse getAllContact(int size, int number);
}

