package service.impl;

import dao.Dao;
import dto.AttachmentDto;
import dto.ContactDto;
import dto.PhoneDto;
import dto.RequestContactDto;
import dto.SearchContactDto;
import entity.ContactAddressEmbeddable;
import entity.ContactEntity;
import entity.PhoneEntity;
import exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.AttachmentService;
import service.ContactService;
import service.response.ContactListResponse;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


@Service
public class ContactServiceImpl implements ContactService {

    private final Dao<ContactEntity> contactDao;
    private final Dao<PhoneEntity> phoneDao;
    private final AttachmentService attachmentService;
    private final PhoneServiceImpl phoneService;

    @Autowired
    public ContactServiceImpl(Dao<ContactEntity> contactDao, Dao<PhoneEntity> phoneDao,
                              AttachmentServiceImpl attachmentService,
                              PhoneServiceImpl phoneService) {
        this.contactDao = contactDao;
        this.phoneDao = phoneDao;
        this.attachmentService = attachmentService;
        this.phoneService = phoneService;
    }


    public ContactListResponse getAllContact(int size, int number) {
        List<ContactEntity> contactList = contactDao.getAll(size, number);
        List<ContactDto> contactDtoList = new ArrayList<>();

        for (ContactEntity contact : contactList) {
            ContactDto contactDto = new ContactDto(contact);
            contactDtoList.add(contactDto);
        }
        int allsize = contactDao.count();
        return new ContactListResponse(contactDtoList, allsize);
    }

    @Override
    public RequestContactDto getById(int id) throws EntityNotFoundException {
        Optional<ContactEntity> optionalContactEntity = contactDao.getById(id);
        if (optionalContactEntity.isEmpty()) {
            throw new EntityNotFoundException("contact");
        }
        ContactEntity contact = optionalContactEntity.get();
        ContactDto contactDto = new ContactDto(contact);

        List<AttachmentDto> attachmentDto = attachmentService.getByContactId(id);
        List<PhoneDto> phoneDto = phoneService.getByContactId(id);

        return new RequestContactDto(contactDto, attachmentDto, phoneDto);
    }

    public int createContact(RequestContactDto requestContactDto) throws ParseException, EntityNotFoundException ,NullPointerException{
        ContactAddressEmbeddable addressEmbeddable = new ContactAddressEmbeddable(requestContactDto);
        ContactEntity contactEntity = new ContactEntity(requestContactDto, addressEmbeddable);
        int contactId = (int) contactDao.create(contactEntity);
        if (requestContactDto.getAttachmentDto() != null) {
            for (AttachmentDto attachment : requestContactDto.getAttachmentDto()) {
                attachmentService.updateAttachment(attachment, contactId);
            }
        }

        if (requestContactDto.getPhoneDto() != null) {
            for (PhoneDto phone : requestContactDto.getPhoneDto()) {
                PhoneEntity phoneEntity = new PhoneEntity(phone, contactEntity);
                phoneDao.create(phoneEntity);
            }
        }
        return contactId;
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
    public ContactListResponse findBy(int size, int number, SearchContactDto searchContactDto)
            throws ParseException {
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
    public void updateContact(RequestContactDto requestContactDto, int id)
            throws EntityNotFoundException, ParseException {
        ContactAddressEmbeddable addressEmbeddable = new ContactAddressEmbeddable(requestContactDto);
        ContactEntity contactEntity = new ContactEntity(requestContactDto, addressEmbeddable);
        contactEntity.setId(id);
        contactDao.update(contactEntity);

        if (requestContactDto.getPhoneDto() != null) {
            for (PhoneDto phone : requestContactDto.getPhoneDto()) {
                PhoneEntity phoneEntity = new PhoneEntity(phone, contactEntity);
                phoneDao.update(phoneEntity);
            }
        }

        for (AttachmentDto attachment : requestContactDto.getAttachmentDto()) {
            attachmentService.updateAttachment(attachment, id);
        }
    }
}
