package com.example.enetcom_intranet.service;


import com.example.enetcom_intranet.model.Attachment;
import com.example.enetcom_intranet.model.Classe;
import com.example.enetcom_intranet.model.Post;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AttachmentService {

    List<Attachment> getAttachments();
    Attachment getAttachmentById(Integer id);

    Attachment saveAttachment(MultipartFile file) throws Exception;

    Attachment getAttachment(Integer fileId) throws Exception;
}