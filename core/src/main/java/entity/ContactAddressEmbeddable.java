package entity;


import lombok.AllArgsConstructor;
import lombok.Data;
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
}
