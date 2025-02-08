package com.example.taskmanager.controllers;

import com.example.taskmanager.dto.AttachmentDto;
import com.example.taskmanager.servicies.AttachmentService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("attachment")
public class AttachmentController {

    private final AttachmentService service;

    public AttachmentController(AttachmentService service) {
        this.service = service;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public AttachmentDto upload(@RequestParam(name = "taskId") UUID taskId, @RequestPart(name = "file") MultipartFile file) throws IOException {
        return service.upload(taskId, file);
    }

    @GetMapping(path = "{id}")
    public StreamingResponseBody download(@PathVariable(name = "id") UUID id, HttpServletResponse response) {
        return outputStream -> service.download(id, outputStream, response);
    }

    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable(name = "id") UUID id) {
        service.delete(id);
    }

}
