package entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name="phone")
@Data
@Accessors(chain = true)
public class PhoneEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "country_code")
    private String countryCode;

    @Column(name = "operator_code")
    private String operatorCode;

    @Column(name = "phone")
    private String phone;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private PhoneType type;

    @Column(name = "description", length = 500)
    private String description;

    @ManyToOne (optional = false,cascade = CascadeType.ALL)
    @JoinColumn (name = "id_contact")
    private ContactEntity contactEntity;

}
