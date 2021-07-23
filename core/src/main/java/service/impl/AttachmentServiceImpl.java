package service.impl;

import dao.Dao;
import dto.AttachmentDto;
import entity.AttachmentEntity;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import service.AttachmentService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AttachmentServiceImpl implements AttachmentService {

    @Autowired
    private final Dao<AttachmentEntity> attachmentDao;

    @Override
    public AttachmentDto getById(int id) {
        Optional<AttachmentEntity> optionalAttachmentEntity = attachmentDao.getById(id);
        if (!optionalAttachmentEntity.isPresent()) {
            throw new RuntimeException();
        }
        AttachmentEntity attachmentEntity = optionalAttachmentEntity.get();
        AttachmentDto attachmentDto = new AttachmentDto(attachmentEntity);
        return attachmentDto;
    }

    @Override
    public String createNewAttachment(MultipartFile file, String name) {

        String fileName = null;

        if (!file.isEmpty()) {
            try {
                fileName = file.getOriginalFilename();
                String url = "root/files";
                String fileHost="http://localhost:8080";
                String apiPath = "/api/file/";
                String generatedFileName = Instant.now().getEpochSecond()+"-"+ fileName;
                File dir = new File(url);

                if (!dir.exists()) {
                    dir.mkdirs();
                }

                String fullPath = url+"/"+generatedFileName;
                Files.copy(file.getInputStream(), Paths.get(fullPath), StandardCopyOption.REPLACE_EXISTING);
                System.out.println(fullPath);
                AttachmentEntity attachmentEntity = new AttachmentEntity();
                attachmentEntity.setName(fileName);
                attachmentEntity.setUrl(fileHost+apiPath+generatedFileName);
                attachmentEntity.setLoadDate(Instant.now());
                attachmentEntity.setUpdateDate(Instant.now());
                attachmentEntity.setDeleteDate(null);
                attachmentEntity.setComment("123");
                attachmentEntity.setType("temp");
                attachmentDao.create(attachmentEntity);

                return url;
            } catch (IOException e) {
                return "Error upload file";
            }
        } else {
            return "File is Empty";
        }
    }

    @Override
    public void deleteAttachment(int id) {
        Optional<AttachmentEntity> optionalAttachmentEntity = attachmentDao.getById(id);
        if (!optionalAttachmentEntity.isPresent()) {
            throw new RuntimeException();
        }
        AttachmentEntity attachmentEntity = optionalAttachmentEntity.get();
        attachmentEntity.setDeleteDate(Instant.now());
        attachmentEntity.setType("temp");
        attachmentDao.update(attachmentEntity);
    }

    @Override
    public void updateAttachment(MultipartFile file, String name, int id) {
        Optional<AttachmentEntity> optionalAttachmentEntity = attachmentDao.getById(id);
        if (!optionalAttachmentEntity.isPresent()) {
            throw new RuntimeException();
        }

        String fileName = file.getOriginalFilename();

        AttachmentEntity attachmentEntity = optionalAttachmentEntity.get();
        attachmentEntity.setName(fileName);
        attachmentEntity.setUpdateDate(Instant.now());
        attachmentEntity.setComment("");

        attachmentDao.update(attachmentEntity);
    }

    @SneakyThrows
    @Override
    public Resource getFile(String fileName) {
        String path = String.format("%s/%s","root/files",fileName);
        Path file = Paths.get(path);
        Resource resource = new UrlResource(file.toUri());
        return resource;
        }
    }


