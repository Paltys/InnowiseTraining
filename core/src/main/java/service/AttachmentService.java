package service;

import dto.AttachmentDto;
import exceptions.EntityNotFoundException;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


public interface AttachmentService {

    AttachmentDto getById(int id);

    int createNewAttachment(MultipartFile file, String name) throws IOException;

    void deleteAttachment(int id) throws EntityNotFoundException;

    void updateAttachment(AttachmentDto attachmentDto, int id) throws EntityNotFoundException;

    Resource getFile(String key);

    List<AttachmentDto> getByContactId(int id);
}
