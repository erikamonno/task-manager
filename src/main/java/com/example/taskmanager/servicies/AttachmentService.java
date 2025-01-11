package com.example.taskmanager.servicies;

import com.example.taskmanager.dto.AttachmentDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.OutputStream;
import java.util.UUID;

public interface AttachmentService {

    AttachmentDto upload(MultipartFile file);
    // outputstream serve per scrivere in output dei dati, l'input stream serve per leggere dei dati in input
    void download(UUID id, OutputStream stream);
    void delete(UUID id);
}
