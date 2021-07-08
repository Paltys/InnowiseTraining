package entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.Instant;



@Entity
@Table(name = "contact")
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Accessors(chain = true)
public class ContactEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name = "middle_name", length = 50)
    private String middleName;

    @Column(name = "data_birthday")
    private Instant dataBirthday;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "citizenship", length = 50)
    private String citizenship;

    @Column(name = "marital_status")
    @Enumerated(EnumType.STRING)
    private Maritalstatus maritalStatus;

    @Column(name = "website", length = 50)
    private String website;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "workplace", length = 100)
    private String workplace;

    @Column(name = "country", length = 50)
    private String country;

    @Column(name = "town", length = 50)
    private String town;

    @Column(name = "street", length = 50)
    private String street;

    @Column(name = "house", length = 50)
    private String house;

    @Column(name = "flat", length = 50)
    private String flat;

    @Column(name = "adressindex")
    private int adressIndex;

}
