package controller;

import dto.AttachmentDto;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import service.AttachmentService;
import service.response.ErrorResponce;


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

    @GetMapping("/{id}")
    public ResponseEntity<?> retrieveById(@PathVariable int id) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("Content-Type", "application/json");
        try {
            AttachmentDto attachmentDto = attachmentService.getById(id);
            return new ResponseEntity<>(attachmentDto, HttpStatus.OK);
        } catch (RuntimeException e) {
            ErrorResponce errorResponce = new ErrorResponce("404", "Sorry. Attachment file not found", "attachment");
            return new ResponseEntity<>(errorResponce, params, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> servFile(@PathVariable int id) {
        Resource file = attachmentService.getFileById(id);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }







}