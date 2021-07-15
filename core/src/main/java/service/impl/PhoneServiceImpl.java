package service.impl;

import dao.InterfaceDao;
import dto.PhoneDto;
import entity.ContactEntity;
import entity.PhoneEntity;
import entity.PhoneType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.PhoneService;

import java.util.Optional;

@Service
public class PhoneServiceImpl implements PhoneService {

    @Autowired
    private InterfaceDao<PhoneEntity> phoneDao;

    @Autowired
    private InterfaceDao<ContactEntity> contactDao;

    @Override
    public PhoneDto getById(int id) {
        Optional<PhoneEntity> optionalPhoneEntity = phoneDao.getById(id);
        if (!optionalPhoneEntity.isPresent()) {
            throw new RuntimeException();
        }
        PhoneEntity phone = optionalPhoneEntity.get();
        PhoneDto phonetDto = new PhoneDto(phone);
        return phonetDto;
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
        phoneEntity.setType(PhoneType.valueOf(newPhone.getType()));
        phoneEntity.setDescription(newPhone.getDescription());
        return (int) phoneDao.create(phoneEntity);
    }

    @Override
    public void deletePhone(int id) {
        Optional<PhoneEntity> optionalPhoneEntity = phoneDao.getById(id);
        if (!optionalPhoneEntity.isPresent()) {
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
        ContactEntity contact = contactEntity.get();
        phoneEntity.setContactEntity(contact);
        phoneEntity.setCountryCode(updatePhone.getCountryCode());
        phoneEntity.setOperatorCode(updatePhone.getOperatorCode());
        phoneEntity.setPhone(updatePhone.getPhone());
        phoneEntity.setType(PhoneType.valueOf(updatePhone.getType()));
        phoneEntity.setDescription(updatePhone.getDescription());

        phoneDao.update(phoneEntity);


    }
}
