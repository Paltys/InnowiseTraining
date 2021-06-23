package entity;

import org.springframework.core.SpringVersion;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="contact")
public class ContactEntity {
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
    private Timestamp dataBirthday;

    @Enumerated(EnumType.STRING)
    private String gender;

    @Column(name = "citizenship", length = 50)
    private String citizenship;

    @Enumerated(EnumType.STRING)
    private String maritalStatus;

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

    @Column(name = "house", length = 50)
    private String house;

    @Column(name = "flat", length = 50)
    private String flat;

    @Column(name = "index")
    private int index;


}
