package service.impl;

import dao.Dao;
import dto.ContactDto;
import dto.RequestContactDto;
import dto.SearchContactDto;
import entity.AttachmentEntity;
import entity.ContactAddressEmbeddable;
import entity.ContactEntity;
import entity.PhoneEntity;
import exceptions.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.ContactService;
import service.response.ContactListResponse;

import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private final Dao<ContactEntity> contactDao;

    @Autowired
    private final Dao<PhoneEntity> phoneDao;

    @Autowired
    private final Dao<AttachmentEntity> attachmentDao;


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
    public ContactDto getById(int id) throws EntityNotFoundException {
        Optional<ContactEntity> optionalContactEntity = contactDao.getById(id);
        if (optionalContactEntity.isEmpty()) {
            throw new EntityNotFoundException("contact");
        }
        ContactEntity contact = optionalContactEntity.get();
        ContactDto contactDto = new ContactDto(contact);
        return contactDto;
    }

    public int createNewContact(ContactDto contactDto) {
        ContactEntity contactEntity = new ContactEntity();
        ContactAddressEmbeddable contactAddressEmbeddable = new ContactAddressEmbeddable(
                contactDto.getCountry(),contactDto.getTown(), contactDto.getStreet(), contactDto.getHouse(), contactDto.getFlat(), contactDto.getAddressIndex());
        contactEntity.setFirstName(contactDto.getFirstName());
        contactEntity.setLastName(contactDto.getLastName());
        contactEntity.setMiddleName(contactDto.getMiddleName());
        contactEntity.setDataBirthday(contactDto.getDataBirthday());
        contactEntity.setGender(contactDto.getGender());
        contactEntity.setCitizenship(contactDto.getCitizenship());
        contactEntity.setMaritalStatus(contactDto.getMaritalStatus());
        contactEntity.setWebsite(contactDto.getWebsite());
        contactEntity.setEmail(contactDto.getEmail());
        contactEntity.setWorkplace(contactDto.getWorkplace());
        contactEntity.setContactAddressEmbeddable(contactAddressEmbeddable);
        contactEntity.setAvatarUrl(contactDto.getAvatarUrl());
        return (int)contactDao.create(contactEntity);

//        for (PhoneEntity phone : contactDto.getPhoneEntity()) {
//            phoneDao.create(phone);
//        }
//        for (AttachmentEntity attachment : contactDto.getAttachmentEntity()) {
//            attachmentDao.create(attachment);
//        }

    }

    @Override
    public void deleteContact(int id) throws EntityNotFoundException {
        Optional<ContactEntity> optionalContactEntity = contactDao.getById(id);
        if (optionalContactEntity.isEmpty()) {
            throw new EntityNotFoundException("contact");
        }
        ContactEntity contact = optionalContactEntity.get();
        contactDao.delete(contact);
    }

    @Override
    public ContactListResponse findBy(int size, int number, SearchContactDto searchContactDto) {
        List<ContactEntity> contactList = contactDao.findBy(size, number, searchContactDto);
        List<ContactDto> contactDtoList = new LinkedList<>();

        for (ContactEntity contact : contactList) {
            ContactDto Dto = new ContactDto(contact);
            contactDtoList.add(Dto);
        }
        int allsize = contactDao.count();
        return new ContactListResponse(contactDtoList, allsize);
    }

    @Override
    public void updateContact(RequestContactDto requestContactDto, int id) throws EntityNotFoundException {

        contactDao.update(requestContactDto.getContactEntity());

        for (PhoneEntity phone : requestContactDto.getPhoneEntity()) {
            phoneDao.update(phone);
        }
        for (AttachmentEntity attachment : requestContactDto.getAttachmentEntity()) {
            attachment.setUpdateDate(Instant.now());
            attachmentDao.update(attachment);
        }
    }
}
