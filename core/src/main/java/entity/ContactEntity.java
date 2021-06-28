package entity;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;


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

    @Column(name = "house", length = 50)
    private String house;

    @Column(name = "flat", length = 50)
    private String flat;

    @Column(name = "index")
    private int index;


    public ContactEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Instant getDataBirthday() {
        return dataBirthday;
    }

    public void setDataBirthday(Instant dataBirthday) {
        this.dataBirthday = dataBirthday;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public Maritalstatus getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(Maritalstatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWorkplace() {
        return workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
