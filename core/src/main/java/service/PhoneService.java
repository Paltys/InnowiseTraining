package service;

import dto.PhoneDto;



public interface PhoneService {

    PhoneDto getById(int id);

    int createNewPhone(PhoneDto obj);

    void deletePhone(int id);

    void updatePhone(PhoneDto obj, int id);
}
