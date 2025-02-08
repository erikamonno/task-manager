package com.example.taskmanager.servicies;

import com.example.taskmanager.dto.AttachmentDto;
import com.example.taskmanager.entities.Attachment;
import com.example.taskmanager.exceptions.AttachmentNotFoundException;
import com.example.taskmanager.mappers.AttachmentMapper;
import com.example.taskmanager.mappers.TaskMapper;
import com.example.taskmanager.repositories.AttachmentRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
@Service

public class AttachmentServiceImpl implements AttachmentService {

    private final AttachmentRepository repository;
    private final TaskService taskService;
    private final TaskMapper taskMapper;
    private final AttachmentMapper mapper;

    public AttachmentServiceImpl(AttachmentRepository repository, TaskService taskService, TaskMapper taskMapper, AttachmentMapper mapper) {
        this.repository = repository;
        this.taskService = taskService;
        this.taskMapper = taskMapper;
        this.mapper = mapper;
    }

    @Override
    public AttachmentDto upload(UUID taskId, MultipartFile file) throws IOException {
        Attachment entity = new Attachment();
        entity.setFileName(file.getOriginalFilename());
        entity.setTask(taskMapper.toEntity(taskService.readOneTask(taskId)));
        entity.setContent(file.getBytes());
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    @Override
    public void download(UUID id, OutputStream stream, HttpServletResponse response) throws IOException {
        var oEntity = repository.findById(id);
        if(oEntity.isEmpty()) {
            throw new AttachmentNotFoundException("Attachment not found");
        }
        var entity = oEntity.get();
        // La content disposition è un meta dato (informazioni di un dato) che ci fornisce informazioni aggiuntive sul file da scaricare
        var contentDisposition = ContentDisposition.attachment().filename(entity.getFileName()).build();
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString());
        stream.write(entity.getContent());
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
