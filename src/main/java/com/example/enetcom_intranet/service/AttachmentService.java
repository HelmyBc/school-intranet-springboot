package com.example.enetcom_intranet.service;


import com.example.enetcom_intranet.model.Attachment;
import org.springframework.web.multipart.MultipartFile;

public interface AttachmentService {
    Attachment saveAttachment(MultipartFile file) throws Exception;

    Attachment getAttachment(Integer fileId) throws Exception;
}