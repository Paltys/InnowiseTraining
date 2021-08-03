package dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import entity.AttachmentEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.PastOrPresent;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttachmentDto {
    private int id;
    @Length(max = 255, message = "name attachment should not be greater than 50")
    private String name;
    @Length(max = 100, message = "url attachment should not be greater than 50")
    private String url;
    @PastOrPresent(message = "loadDate data should not be future")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss", timezone = "UTC")
    private Instant loadDate;
    @PastOrPresent(message = "updateDate data should not be future")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss", timezone = "UTC")
    private Instant updateDate;
    @PastOrPresent(message = "deleteDate data should not be future")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss", timezone = "UTC")
    private Instant deleteDate;
    @Length(max = 500, message = "comments should not be greater than 500")
    private String comments;
    @Length(max = 50, message = "type should not be greater than 50")
    private String type;

    public AttachmentDto(AttachmentEntity attachment) {
        id = attachment.getId();
        name = attachment.getName();
        url = attachment.getUrl();
        loadDate = attachment.getLoadDate();
        updateDate = attachment.getUpdateDate();
        if (attachment.getDeleteDate() == null) {
            deleteDate = null;
        } else {
            deleteDate = attachment.getDeleteDate();
        }
        comments = attachment.getComment();
        type = attachment.getType();
    }
}
