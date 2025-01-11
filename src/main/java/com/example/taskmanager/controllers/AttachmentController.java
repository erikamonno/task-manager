package com.example.taskmanager.controllers;

import com.example.taskmanager.dto.AttachmentDto;
import com.example.taskmanager.servicies.AttachmentService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.util.UUID;

@RestController
@RequestMapping("attachment")
public class AttachmentController {

    private final AttachmentService service;

    public AttachmentController(AttachmentService service) {
        this.service = service;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public AttachmentDto upload(@RequestPart(name = "file") MultipartFile file) {
        return service.upload(file);
    }

    @GetMapping(path = "{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public StreamingResponseBody download(@PathVariable(name = "id") UUID id) {
        return outputStream -> service.download(id, outputStream);
    }

    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable(name = "id") UUID id) {
        service.delete(id);
    }

}
