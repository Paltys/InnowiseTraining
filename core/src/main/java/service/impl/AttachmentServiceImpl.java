package service.impl;

import dao.Dao;
import dto.AttachmentDto;
import entity.AttachmentEntity;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import service.AttachmentService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
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

    @Autowired
    private final ServletContext context;

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


        if (!file.isEmpty()) {
            try {
//                byte[] bytes = file.getBytes();
                fileName = file.getOriginalFilename();
                String url = "root/files";
                String fileHost="http://localhost:8080";
                String apiPath = "/api/file/";
                String generatedFileName = Instant.now().getEpochSecond()+"-"+ fileName;



//                String rootPath = "C:\\path\\";
                File dir = new File(url);

                if (!dir.exists()) {
                    dir.mkdirs();
                }

//                url = dir.getAbsolutePath() + File.separator + Instant.now().getEpochSecond()+"-"+ fileName;
//                  url = context.getContextPath()+ File.separator + Instant.now().getEpochSecond() + "-" + fileName;
//                URL url1 = new URL("http://localhost:8080/api/file");
//                URL relativeUrl = new URL(url1,Instant.now().getEpochSecond() + "-" + fileName);

                String fullPath = url+"/"+generatedFileName;


                Files.copy(file.getInputStream(), Paths.get(fullPath), StandardCopyOption.REPLACE_EXISTING);


//                File uploadedFile = new File(url,);
                System.out.println(fullPath);
//
//                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadedFile));
//                stream.write(bytes);
//                stream.flush();
//                stream.close();

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
    public void getFile(String fileName, HttpServletResponse responce) {
        String path = String.format("%s/%s","root/files",fileName);
        Path file = Paths.get(path);
        Resource resource = new UrlResource(file.toUri());
        responce.setContentType("image/jpeg");
        IOUtils.copy(resource.getInputStream(), responce.getOutputStream());
        responce.addHeader("Content-disposition","attachment; filename=\""+resource.getFilename()+"\"");
        responce.flushBuffer();


        }

    }


