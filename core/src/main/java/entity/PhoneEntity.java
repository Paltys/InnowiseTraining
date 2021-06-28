//package entity;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name="phone")
//public class PhoneEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false)
//    private int id;
//
//    @Column(name = "id_contact")
//    private int idContact;
//
//    @Column(name = "country_code")
//    private int countryCode;
//
//    @Column(name = "operator_code")
//    private int operatorCode;
//
//    @Column(name = "code")
//    private int code;
//
//    @Column(name = "type", length = 50)
//    private String type;
//
//    @Column(name = "type", length = 500)
//    private String description;
//
//    public PhoneEntity() {
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public int getIdContact() {
//        return idContact;
//    }
//
//    public void setIdContact(int idContact) {
//        this.idContact = idContact;
//    }
//
//    public int getCountryCode() {
//        return countryCode;
//    }
//
//    public void setCountryCode(int countryCode) {
//        this.countryCode = countryCode;
//    }
//
//    public int getOperatorCode() {
//        return operatorCode;
//    }
//
//    public void setOperatorCode(int operatorCode) {
//        this.operatorCode = operatorCode;
//    }
//
//    public int getCode() {
//        return code;
//    }
//
//    public void setCode(int code) {
//        this.code = code;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    @Override
//    public String toString() {
//        return "PhoneEntity{" +
//                "id=" + id +
//                ", idContact=" + idContact +
//                ", countryCode=" + countryCode +
//                ", operatorCode=" + operatorCode +
//                ", code=" + code +
//                ", type='" + type + '\'' +
//                ", description='" + description + '\'' +
//                '}';
//    }
//}
