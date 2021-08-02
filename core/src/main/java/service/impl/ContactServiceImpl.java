package service.impl;

import dao.Dao;
import dto.AttachmentDto;
import dto.ContactDto;
import dto.PhoneDto;
import dto.RequestContactDto;
import dto.SearchContactDto;
import entity.AttachmentEntity;
import entity.ContactAddressEmbeddable;
import entity.ContactEntity;
import entity.PhoneEntity;
import entity.PhoneType;
import exceptions.EntityNotFoundException;
import org.springframework.stereotype.Service;
import service.AttachmentService;
import service.ContactService;
import service.response.ContactListResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


@Service
public class ContactServiceImpl implements ContactService {

    private final Dao<ContactEntity> contactDao;
    private final Dao<PhoneEntity> phoneDao;
    private final Dao<AttachmentEntity> attachmentDao;
    private final AttachmentService attachmentService;
    private final PhoneServiceImpl phoneService;


    public ContactServiceImpl(Dao<ContactEntity> contactDao, Dao<PhoneEntity> phoneDao, Dao<AttachmentEntity> attachmentDao, AttachmentServiceImpl attachmentService, PhoneServiceImpl phoneService) {
        this.contactDao = contactDao;
        this.phoneDao = phoneDao;
        this.attachmentDao = attachmentDao;
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

        RequestContactDto requestContactDto = new RequestContactDto(contactDto, attachmentDto, phoneDto);
        return requestContactDto;
    }

    public int createNewContact(RequestContactDto requestContactDto) throws ParseException, EntityNotFoundException {
        ContactEntity contactEntity = new ContactEntity();
        ContactAddressEmbeddable contactAddressEmbeddable = new ContactAddressEmbeddable(
                requestContactDto.getCountry(), requestContactDto.getTown(), requestContactDto.getStreet(), requestContactDto.getHouse(), requestContactDto.getFlat(), requestContactDto.getAddressIndex());
        contactEntity.setFirstName(requestContactDto.getFirstName());
        contactEntity.setLastName(requestContactDto.getLastName());
        contactEntity.setMiddleName(requestContactDto.getMiddleName());
        contactEntity.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(requestContactDto.getBirthday()).toInstant());
        contactEntity.setGender(requestContactDto.getGender());
        contactEntity.setCitizenship(requestContactDto.getCitizenship());
        contactEntity.setMaritalStatus(requestContactDto.getMaritalStatus());
        contactEntity.setWebsite(requestContactDto.getWebsite());
        contactEntity.setEmail(requestContactDto.getEmail());
        contactEntity.setWorkplace(requestContactDto.getWorkplace());
        contactEntity.setContactAddressEmbeddable(contactAddressEmbeddable);
        contactEntity.setAvatarUrl(requestContactDto.getAvatarUrl());
        int contactId = (int) contactDao.create(contactEntity);

        if (requestContactDto.getAttachmentDto() != null) {
            for (AttachmentDto attachment : requestContactDto.getAttachmentDto()) {
                attachmentService.updateAttachment(attachment, contactId);
            }
        }

        if (requestContactDto.getPhoneDto() != null) {
            for (PhoneDto phone : requestContactDto.getPhoneDto()) {
                PhoneEntity phoneEntity = new PhoneEntity();
                phoneEntity.setCountryCode(phone.getCountryCode());
                phoneEntity.setOperatorCode(phone.getOperatorCode());
                phoneEntity.setPhone(phone.getPhone());
                phoneEntity.setType(PhoneType.valueOf(phone.getType().toString()));
                phoneEntity.setDescription(phone.getDescription());
                phoneEntity.setContactEntity(contactEntity);
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
    public ContactListResponse findBy(int size, int number, SearchContactDto searchContactDto) throws ParseException {
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
    public void updateContact(RequestContactDto requestContactDto, int id) throws EntityNotFoundException, ParseException {
        ContactEntity contactEntity = new ContactEntity();
        ContactAddressEmbeddable contactAddressEmbeddable = new ContactAddressEmbeddable(
                requestContactDto.getCountry(), requestContactDto.getTown(), requestContactDto.getStreet(), requestContactDto.getHouse(), requestContactDto.getFlat(), requestContactDto.getAddressIndex());
        contactEntity.setFirstName(requestContactDto.getFirstName());
        contactEntity.setLastName(requestContactDto.getLastName());
        contactEntity.setMiddleName(requestContactDto.getMiddleName());
        contactEntity.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(requestContactDto.getBirthday()).toInstant());
        contactEntity.setGender(requestContactDto.getGender());
        contactEntity.setCitizenship(requestContactDto.getCitizenship());
        contactEntity.setMaritalStatus(requestContactDto.getMaritalStatus());
        contactEntity.setWebsite(requestContactDto.getWebsite());
        contactEntity.setEmail(requestContactDto.getEmail());
        contactEntity.setWorkplace(requestContactDto.getWorkplace());
        contactEntity.setContactAddressEmbeddable(contactAddressEmbeddable);
        contactEntity.setAvatarUrl(requestContactDto.getAvatarUrl());
        contactDao.update(contactEntity);

        List<PhoneEntity> listPhone = getPhoneEntity(requestContactDto);
        for (PhoneEntity phoneEntity : listPhone) {
            phoneDao.update(phoneEntity);
        }

        for (AttachmentDto attachment : requestContactDto.getAttachmentDto()) {
            attachmentService.updateAttachment(attachment, id);
        }
    }

    private List<PhoneEntity> getPhoneEntity(RequestContactDto requestContactDto) {
        List<PhoneEntity> phoneList = new ArrayList<>();

        for (PhoneDto phone : requestContactDto.getPhoneDto()) {
            PhoneEntity phoneEntity = new PhoneEntity();
            phoneEntity.setCountryCode(phone.getCountryCode());
            phoneEntity.setOperatorCode(phone.getOperatorCode());
            phoneEntity.setPhone(phone.getPhone());
            phoneEntity.setType(PhoneType.valueOf(phone.toString()));
            phoneEntity.setDescription(phone.getDescription());
            phoneDao.create(phoneEntity);
            phoneList.add(phoneEntity);
        }
        return phoneList;
    }
}
