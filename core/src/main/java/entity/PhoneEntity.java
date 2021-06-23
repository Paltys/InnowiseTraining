package entity;

import javax.persistence.*;

@Entity
@Table(name="phone")
public class PhoneEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "id_contact")
    private int idContact;

    @Column(name = "country_code")
    private int countryCode;

    @Column(name = "operator_code")
    private int operatorCode;

    @Column(name = "code")
    private int code;

    @Column(name = "type", length = 50)
    private String type;

    @Column(name = "type", length = 500)
    private String description;


}
