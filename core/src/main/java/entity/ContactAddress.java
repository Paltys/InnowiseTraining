package entity;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Getter
@Setter
@Embeddable
public class ContactAddress {

    private String country;
    private String town;
    private String street;
    private String house;
    private String flat;
    private int adressIndex;
}
