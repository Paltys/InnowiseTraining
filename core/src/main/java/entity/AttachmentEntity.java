//package entity;
//
//
//import javax.persistence.*;
//
//import java.time.Instant;
//
//
//@Entity
//@Table(name="attachment")
//class AttachmentEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false)
//    private int id;
//
//    @Column(name = "id_contact")
//    private int idContact;
//
//    @Column(name = "name", length = 50)
//    private String name;
//
//    @Column(name = "url", length = 100)
//    private String url;
//
//    @Column(name = "load_date")
//    private Instant loadDate;
//
//    @Column(name = "update_date")
//    private Instant updateDate;
//
//    @Column(name = "delete_date")
//    private Instant deleteDate;
//
//    @Column(name = "comment", length = 500)
//    private String comment;
//
//    @Column(name = "type", length = 50)
//    private String type;
//
//
//}
