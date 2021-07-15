package service.impl;

import dao.InterfaceDao;
import dto.AttachmentDto;
import entity.AttachmentEntity;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import service.AttachmentService;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AttachmentServiceImpl implements AttachmentService {

    @Autowired
    private final InterfaceDao<AttachmentEntity> attachmentDao;

    @Autowired
    private final ResourceLoader resourceLoader;

    @Override
    public Resource getFileById(int id) {
        Optional<AttachmentEntity> optionalAttachmentEntity = attachmentDao.getById(id);
        if (!optionalAttachmentEntity.isPresent()) {
            throw new RuntimeException();
        }
        AttachmentEntity attachmentEntity = optionalAttachmentEntity.get();

        return resourceLoader.getResource(attachmentEntity.getUrl());
    }

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
        String url = null;

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                fileName = file.getOriginalFilename();

                String rootPath = "C:\\path\\";
                File dir = new File(rootPath + File.separator + "loadFiles");

                if (!dir.exists()) {
                    dir.mkdirs();
                }

                url = dir.getAbsolutePath() + File.separator + Instant.now().getEpochSecond()+"-"+ fileName;


                File uploadedFile = new File(url);

                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadedFile));
                stream.write(bytes);
                stream.flush();
                stream.close();

                AttachmentEntity attachmentEntity = new AttachmentEntity();
                attachmentEntity.setName(fileName);
                attachmentEntity.setUrl(url);
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
}

