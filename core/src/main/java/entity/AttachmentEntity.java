package entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;


@Entity
@Table(name = "attachment")
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class AttachmentEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", length = 255)
    private String name;

    @Column(name = "url", length = 100)
    private String url;

    @Column(name = "load_date")
    private Instant loadDate = Instant.now();

    @Column(name = "update_date")
    private Instant updateDate = Instant.now();

    @Column(name = "delete_date")
    private Instant deleteDate;

    @Column(name = "comment", length = 500)
    private String comment;

    @Column(name = "type", length = 50)
    private String type = "temp";

    public AttachmentEntity(String fileName, String urlUploadFile) {
        this.name = fileName;
        this.url = urlUploadFile;
    }
}

