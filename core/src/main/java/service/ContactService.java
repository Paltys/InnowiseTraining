package service;

import exceptions.EntityNotFoundException;
import dto.ContactDto;
import dto.RequestContactDto;
import dto.SearchContactDto;
import service.response.ContactListResponse;

import java.text.ParseException;

public interface ContactService {

    ContactListResponse getAllContact(int size, int number);

    RequestContactDto getById(int id) throws EntityNotFoundException;

    int createContact(RequestContactDto obj) throws ParseException, EntityNotFoundException;

    void deleteContact(int id) throws EntityNotFoundException;

    void updateContact(RequestContactDto obj, int id) throws EntityNotFoundException, ParseException;

    ContactListResponse findBy(int size, int number, SearchContactDto searchContactDto) throws ParseException;
}

