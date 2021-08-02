package service.impl;

import dao.Dao;
import dto.AttachmentDto;
import entity.AttachmentEntity;
import exceptions.EntityNotFoundException;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AttachmentServiceImpl implements AttachmentService {

    private final Dao<AttachmentEntity> attachmentDao;

    private final static String REALPATH = "root/files";

    @Autowired
    public AttachmentServiceImpl(Dao<AttachmentEntity> attachmentDao) {
        this.attachmentDao = attachmentDao;
    }

    @Override
    public AttachmentDto getById(int id) {
        Optional<AttachmentEntity> optionalAttachmentEntity = attachmentDao.getById(id);
        if (optionalAttachmentEntity.isEmpty()) {
            throw new RuntimeException();
        }
        AttachmentEntity attachmentEntity = optionalAttachmentEntity.get();
        return new AttachmentDto(attachmentEntity);
    }

    @Override
    public int createNewAttachment(MultipartFile file, String name) throws IOException {

        String fileName;

        if (!file.isEmpty()) {
            fileName = file.getOriginalFilename();
            String fileHost = "http://localhost:8080";
            String apiPath = "/api/files/";
            String generatedFileName = Instant.now().getEpochSecond() + "-" + fileName;
            File dir = new File(REALPATH);

            if (!dir.exists()) {
                dir.mkdirs();
            }

            String fullPath = REALPATH + "/" + generatedFileName;
            String urlUploadFile = fileHost + apiPath + generatedFileName;
            Files.copy(file.getInputStream(), Paths.get(fullPath), StandardCopyOption.REPLACE_EXISTING);
            AttachmentEntity attachmentEntity = new AttachmentEntity(fileName, urlUploadFile);

            return (int) attachmentDao.create(attachmentEntity);
        } else {
            return 0;
        }
    }

    @Override
    public void deleteAttachment(int id) throws EntityNotFoundException {
        Optional<AttachmentEntity> optionalAttachmentEntity = attachmentDao.getById(id);
        if (optionalAttachmentEntity.isEmpty()) {
            throw new EntityNotFoundException("attachment");
        }
        AttachmentEntity attachmentEntity = optionalAttachmentEntity.get();
        attachmentEntity.setDeleteDate(Instant.now());
        attachmentEntity.setType("temp");
        attachmentDao.update(attachmentEntity);
    }

    @Override
    public void updateAttachment(AttachmentDto attachmentDto, int contactId) throws EntityNotFoundException {
        Optional<AttachmentEntity> optionalAttachmentEntity = attachmentDao.getById(attachmentDto.getId());
        if (optionalAttachmentEntity.isEmpty()) {
            throw new EntityNotFoundException("attachment");
        }
        AttachmentEntity attachmentEntity = optionalAttachmentEntity.get();
        attachmentEntity.setName(attachmentDto.getName());
        attachmentEntity.setUpdateDate(Instant.now());
        attachmentEntity.setComment(attachmentDto.getComments());
        attachmentEntity.setType("contact_" + contactId);
        attachmentDao.update(attachmentEntity);
    }

    @SneakyThrows
    @Override
    public Resource getFile(String fileName) {
        String path = String.format("%s/%s", REALPATH, fileName);
        Path file = Paths.get(path);
        return new UrlResource(file.toUri());
    }

    @Override
    public List<AttachmentDto> getByContactId(int id) {
        List<AttachmentEntity> attachmentEntityList = attachmentDao.getByContactId(id);
        List<AttachmentDto> attachmentDtoList = new ArrayList<>();
        for (AttachmentEntity attachmentEntity : attachmentEntityList) {
            AttachmentDto attachmentDto = new AttachmentDto(attachmentEntity);
            attachmentDtoList.add(attachmentDto);
        }
        return attachmentDtoList;
    }
}


