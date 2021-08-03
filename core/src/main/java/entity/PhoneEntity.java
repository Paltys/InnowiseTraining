package entity;

import dto.PhoneDto;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table(name = "phone")
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

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_contact")
    private ContactEntity contactEntity;

    public PhoneEntity() {
    }

    public PhoneEntity(PhoneDto phoneDto, ContactEntity entity) {
        id = phoneDto.getId();
        countryCode = phoneDto.getCountryCode();
        operatorCode = phoneDto.getOperatorCode();
        phone = phoneDto.getPhone();
        type = phoneDto.getType();
        description = phoneDto.getDescription();
        contactEntity = entity;
    }
}
