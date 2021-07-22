package service;

import dto.AttachmentDto;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;


public interface AttachmentService {

    Resource getFileById(int id);

    AttachmentDto getById(int id);

    String createNewAttachment(MultipartFile file, String name);

    void deleteAttachment(int id);

    void updateAttachment(MultipartFile file, String name, int id);

    void getFile(String key, HttpServletResponse responce);
}
