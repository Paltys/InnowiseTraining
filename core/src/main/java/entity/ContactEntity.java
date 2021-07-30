package entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Collection;


@Entity
@Table(name = "contact")
@Data
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

    @Column(name = "birthday")
    private Instant birthday;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "citizenship", length = 50)
    private String citizenship;

    @Column(name = "marital_status")
    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;

    @Column(name = "website", length = 50)
    private String website;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "workplace", length = 100)
    private String workplace;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="country",column = @Column(name ="country")),
            @AttributeOverride(name="town",column = @Column(name ="town")),
            @AttributeOverride(name="street",column = @Column(name ="street")),
            @AttributeOverride(name="house",column = @Column(name ="house")),
            @AttributeOverride(name="flat",column = @Column(name ="flat")),
            @AttributeOverride(name="addressIndex",column = @Column(name ="addressIndex")),
    })
    private ContactAddressEmbeddable contactAddressEmbeddable;

    @OneToMany(mappedBy = "contactEntity",cascade = CascadeType.ALL )
    private Collection<PhoneEntity> phoneEntity;

    @Column(name="avatar_url", length = 100)
    private String avatarUrl;
}
