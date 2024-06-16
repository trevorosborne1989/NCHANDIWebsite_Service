package nchandi.spring.services.NCHANDIWebsite_Service.services;

import java.io.IOException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;
import nchandi.spring.services.NCHANDIWebsite_Service.domain.Attachment;
import nchandi.spring.services.NCHANDIWebsite_Service.domain.ResourceItem;
import nchandi.spring.services.NCHANDIWebsite_Service.repositories.AttachmentRepository;
import nchandi.spring.services.NCHANDIWebsite_Service.repositories.ResourceItemRepository;

@Service
public class AttachmentService {

  @Autowired
  AttachmentRepository attachmentRepo;

  @Autowired
  ResourceItemRepository resourceItemRepo;

  Logger logger = LoggerFactory.getLogger("nchandi.spring.services.NCHANDIWebsite_Service.services.AttachmentService");

  public Optional<Attachment> getAttachmentById(String attachmentId) {
    Optional<Attachment> attachment = attachmentRepo.findById(attachmentId);
    if (attachment == null) {
      throw new ResourceNotFoundException("Attachment with ID:" + attachmentId + " not found.");
    }
    return attachment;
  }

  @Transactional
  public Attachment saveAttachment(String resourceItemId, MultipartFile file) throws IOException {
    Optional<ResourceItem> existingResourceItem = resourceItemRepo.findById(resourceItemId);
    if (existingResourceItem == null) {
      throw new ResourceNotFoundException("ResourceItem with ID:" + resourceItemId + " not found.");
    }
    Attachment newAttachment = new Attachment();
    String fileName = file.getOriginalFilename();
    String extPattern = "(?<!^)[.]" + ".*";
    newAttachment.setFileName(fileName.replaceAll(extPattern, ""));
    newAttachment.setContentType(file.getContentType());
    newAttachment.setContent(file.getBytes());
    newAttachment.setParentResource(existingResourceItem.get());
    return attachmentRepo.save(newAttachment);
  }

  public Attachment updateAttachment(String attachmentId, Attachment attachment) {
    Optional<Attachment> existingAttachment = attachmentRepo.findById(attachmentId);
    if (!attachment.getId().equals(attachmentId)) {
      throw new DataIntegrityViolationException("Invalid ID was passed in the request body.");
    } else if (existingAttachment == null) {
      throw new ResourceNotFoundException("Attachment with ID:" + attachmentId + " not found.");
    }
    return attachmentRepo.save(attachment);
  }

  public void deleteAttachment(String attachmentId) {
    Optional<Attachment> attachment = attachmentRepo.findById(attachmentId);
    if (attachment == null) {
      throw new ResourceNotFoundException("Attachment with ID:" + attachmentId + " not found.");
    }
    attachmentRepo.delete(attachment.get());
  }

}
