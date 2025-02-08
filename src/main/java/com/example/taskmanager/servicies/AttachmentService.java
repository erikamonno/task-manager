package com.example.taskmanager.servicies;

import com.example.taskmanager.dto.AttachmentDto;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

public interface AttachmentService {

    AttachmentDto upload(UUID taskId, MultipartFile file) throws IOException;
    // outputstream serve per scrivere in output dei dati, l'inputstream serve per leggere dei dati in input
    void download(UUID id, OutputStream stream, HttpServletResponse response) throws IOException;
    void delete(UUID id);
}
