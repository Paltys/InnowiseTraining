package dto;

import entity.ContactEntity;


public class ContactDto {

    private int id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String dataBirthday;
    private String gender;
    private String citizenship;
    private String maritalStatus;
    private String website;
    private String email;
    private String workplace;
    private String country;
    private String town;
    private String house;
    private String flat;
    private int index;

    public ContactDto(ContactEntity contact) {
        id = contact.getId();
        firstName = contact.getFirstName();
        lastName = contact.getLastName();
        middleName = contact.getMiddleName();
        dataBirthday = contact.getDataBirthday().toString();
        gender = contact.getGender().toString();
        citizenship = contact.getCitizenship();
        maritalStatus = contact.getMaritalStatus().toString();
        website = contact.getWebsite();
        email = contact.getEmail();
        workplace = contact.getWorkplace();
        country = contact.getCountry();
        town = contact.getTown();
        house = contact.getHouse();
        flat = contact.getFlat();
        index = contact.getIndex();
    }

    public ContactDto() {
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

    public String getDataBirthday() {
        return dataBirthday;
    }

    public void setDataBirthday(String dataBirthday) {
        this.dataBirthday = dataBirthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
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

    @Override
    public String toString() {
        return "ContactResponseDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", dataBirthday=" + dataBirthday +
                ", gender='" + gender + '\'' +
                ", citizenship='" + citizenship + '\'' +
                ", maritalStatus='" + maritalStatus + '\'' +
                ", website='" + website + '\'' +
                ", email='" + email + '\'' +
                ", workplace='" + workplace + '\'' +
                ", country='" + country + '\'' +
                ", town='" + town + '\'' +
                ", house='" + house + '\'' +
                ", flat='" + flat + '\'' +
                ", index=" + index +
                '}';
    }
}
