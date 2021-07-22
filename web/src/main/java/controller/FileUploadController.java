package controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import service.AttachmentService;

import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/api/file")
public class FileUploadController {

    private final AttachmentService attachmentService;

    public FileUploadController(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }

    @PostMapping
    public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("name") String name) {

        String url = null;
        if (!file.isEmpty()) {
            url = attachmentService.createNewAttachment(file, name);
        }
        return url;
    }
//    @GetMapping("/{id}")
//    public ResponseEntity<?> retrieveById(@PathVariable int id) {
//        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//        params.add("Content-Type", "application/json");
//        try {
//            AttachmentDto attachmentDto = attachmentService.getById(id);
//            return new ResponseEntity<>(attachmentDto, HttpStatus.OK);
//        } catch (RuntimeException e) {
//            ErrorResponce errorResponce = new ErrorResponce("404", "Sorry. Attachment file not found", "attachment");
//            return new ResponseEntity<>(errorResponce, params, HttpStatus.NOT_FOUND);
//        }
//    }
    @GetMapping("/{key}")
    public void downloadFile( @PathVariable String key, HttpServletResponse responce) {
        attachmentService.getFile(key, responce);
    }
}
