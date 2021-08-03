package entity;


import dto.RequestContactDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

@Getter
@Setter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class ContactAddressEmbeddable {
    private String country;
    private String town;
    private String street;
    private String house;
    private String flat;
    private String addressIndex;

    public ContactAddressEmbeddable(RequestContactDto req) {
        country = req.getCountry();
        town = req.getTown();
        street = req.getStreet();
        house = req.getHouse();
        flat = req.getFlat();
        addressIndex = req.getAddressIndex();
    }
}

