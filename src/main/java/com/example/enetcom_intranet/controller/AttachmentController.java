package com.example.enetcom_intranet.controller;

import com.example.enetcom_intranet.ResponseData;
import com.example.enetcom_intranet.model.Attachment;
import com.example.enetcom_intranet.model.Classe;
import com.example.enetcom_intranet.model.Department;
import com.example.enetcom_intranet.model.Post;
import com.example.enetcom_intranet.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
public class AttachmentController {
    @Autowired
    private AttachmentService attachmentService;

    @GetMapping("/api/attachment")
    public ResponseEntity<List<Attachment>> getAllAttachments() {
        List<Attachment> attachments = attachmentService.getAttachments();
        return new ResponseEntity<>(attachments, HttpStatus.OK);
    }

    @PostMapping("/api/upload")
    public ResponseEntity<Attachment> uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
        Attachment attachment = null;
        String downloadURl = "";
        attachment = attachmentService.saveAttachment(file);
        downloadURl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("api/download/")
                .path(String.valueOf(attachment.getId()))
                .toUriString();
        ResponseData responseData = new ResponseData(
                attachment.getFileName(),
                downloadURl,
                file.getContentType(),
                file.getSize());
        return new ResponseEntity<>(attachment, HttpStatus.CREATED);

    }

    @GetMapping({"/api/attachment/{id}"})
    public ResponseEntity<Attachment> getAttachment(@PathVariable Integer id) {
        return new ResponseEntity<>(attachmentService.getAttachmentById(id), HttpStatus.OK);
    }

    @GetMapping("/api/download/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) throws Exception {
        Attachment attachment = null;
        attachment = attachmentService.getAttachment(Integer.valueOf(fileId));
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(attachment.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + attachment.getFileName()
                                + "\"")
                .body(new ByteArrayResource(attachment.getData()));
    }
}