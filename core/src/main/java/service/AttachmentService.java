package service;

import exceptions.EntityNotFoundException;
import dto.AttachmentDto;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;


public interface AttachmentService {

    AttachmentDto getById(int id);

    String createNewAttachment(MultipartFile file, String name);

    void deleteAttachment(int id) throws EntityNotFoundException;

    void updateAttachment(MultipartFile file, String name, int id);

    Resource getFile(String key);
}
