package controller;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import service.AttachmentService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RestController
@RequestMapping("/api/files")
public class FilesController {

    private final AttachmentService attachmentService;

    public FilesController(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }

    @PostMapping
    public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("name") String name) throws IOException {

        String url = null;
        if (!file.isEmpty()) {
            url = attachmentService.createNewAttachment(file, name);
        }
        return url;
    }

    @GetMapping("/{key}")
    public void downloadFile(@PathVariable String key, HttpServletResponse response) {
        Resource resource = attachmentService.getFile(key);
        String extension = FilenameUtils.getExtension(key);
        response.setContentType("image/"+extension);
        try {
            IOUtils.copy(resource.getInputStream(), response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        response.addHeader("Content-disposition", "attachment; filename=\"" + resource.getFilename() + "\"");
        try {
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
