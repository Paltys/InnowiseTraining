package service.impl;

import dao.Dao;
import dto.PhoneDto;
import entity.ContactEntity;
import entity.PhoneEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.PhoneService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PhoneServiceImpl implements PhoneService {
    @Autowired
    private Dao<PhoneEntity> phoneDao;
    @Autowired
    private Dao<ContactEntity> contactDao;

    @Override
    public PhoneDto getById(int id) {
        Optional<PhoneEntity> optionalPhoneEntity = phoneDao.getById(id);
        if (optionalPhoneEntity.isEmpty()) {
            throw new RuntimeException();
        }
        PhoneEntity phone = optionalPhoneEntity.get();
        return new PhoneDto(phone);
    }

    @Override
    public int createNewPhone(PhoneDto newPhone) {
        PhoneEntity phoneEntity = new PhoneEntity();
        Optional<ContactEntity> contactEntity = contactDao.getById(newPhone.getIdContact());
        ContactEntity contact = contactEntity.get();
        phoneEntity.setContactEntity(contact);
        phoneEntity.setCountryCode(newPhone.getCountryCode());
        phoneEntity.setOperatorCode(newPhone.getOperatorCode());
        phoneEntity.setPhone(newPhone.getPhone());
        phoneEntity.setType(newPhone.getType());
        phoneEntity.setDescription(newPhone.getDescription());
        return (int) phoneDao.create(phoneEntity);
    }

    @Override
    public void deletePhone(int id) {
        Optional<PhoneEntity> optionalPhoneEntity = phoneDao.getById(id);
        if (optionalPhoneEntity.isEmpty()) {
            throw new RuntimeException();
        }
        PhoneEntity phone = optionalPhoneEntity.get();
        phoneDao.delete(phone);
    }

    @Override
    public void updatePhone(PhoneDto updatePhone, int id) {
        PhoneEntity phoneEntity = new PhoneEntity();
        phoneEntity.setId(id);
        Optional<ContactEntity> contactEntity = contactDao.getById(updatePhone.getIdContact());
        if (contactEntity.isEmpty()) {
            throw new RuntimeException();
        }
        ContactEntity contact = contactEntity.get();
        phoneEntity.setContactEntity(contact);
        phoneEntity.setCountryCode(updatePhone.getCountryCode());
        phoneEntity.setOperatorCode(updatePhone.getOperatorCode());
        phoneEntity.setPhone(updatePhone.getPhone());
        phoneEntity.setType(updatePhone.getType());
        phoneEntity.setDescription(updatePhone.getDescription());
        phoneDao.update(phoneEntity);
    }

    public List<PhoneDto> getByContactId(int id) {
        List<PhoneEntity> phoneEntityList = phoneDao.getByContactId(id);
        List<PhoneDto> phoneDtoList = new ArrayList<>();
        for (PhoneEntity phone : phoneEntityList) {
            PhoneDto phoneDto = new PhoneDto(phone);
            phoneDtoList.add(phoneDto);
        }
        return phoneDtoList;
    }
}
