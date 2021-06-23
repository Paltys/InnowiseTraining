package entity;


import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@Table(name="attachment")
class AttachmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "id_contact")
    private int idContact;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "url", length = 100)
    private String url;

    @Column(name = "load_date")
    private Timestamp loadDate;

    @Column(name = "load_date")
    private Timestamp updateDate;

    @Column(name = "load_date")
    private Timestamp deleteDate;

    @Column(name = "comment", length = 500)
    private String comment;

    @Column(name = "type", length = 50)
    private String type;


}
