package dto;

import entity.AttachmentEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttachmentDto {
    private int id;
    private String name;
    private String url;
    private String loadDate;
    private String updateDate;
    private String deleteDate;
    private String comments;
    private String type;


    public AttachmentDto(AttachmentEntity attachment){
        id=attachment.getId();
        name=attachment.getName();
        url =attachment.getUrl();
        loadDate=attachment.getLoadDate().toString();
        updateDate=attachment.getUpdateDate().toString();

        if (attachment.getDeleteDate()==null)
            deleteDate="null";
        else
            deleteDate = attachment.getDeleteDate().toString();

        comments=attachment.getComment();
        type=attachment.getType();
    }
}
