package nchandi.spring.services.NCHANDIWebsite_Service.controllers;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import nchandi.spring.services.NCHANDIWebsite_Service.domain.Attachment;
import nchandi.spring.services.NCHANDIWebsite_Service.services.AttachmentService;
import nchandi.spring.services.NCHANDIWebsite_Service.services.ResourceItemService;

@RestController
public class AttachmentController {

  @Autowired
	AttachmentService attachmentService;

	@Autowired
	ResourceItemService resourceItemService;

	@RequestMapping(value = "/attachments/{attachmentId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public @ResponseBody HttpEntity<byte[]> getAttachment(
			@PathVariable String attachmentId,
			HttpServletRequest request) {

			Attachment attachment = attachmentService.getAttachmentById(attachmentId).get();

			HttpHeaders springHttpHeaders = new HttpHeaders();
      springHttpHeaders.setContentType(MediaType.valueOf((attachment.getContentType())));
			springHttpHeaders.set("Content-Disposition", String.format("inline; filename=%s", attachment.getFileName()));
			springHttpHeaders.set("Content-Length", String.format("%d", attachment.getContent().length));
			return new HttpEntity<>(attachment.getContent(), springHttpHeaders);
	}

	@RequestMapping(value = "/attachments/{resourceItemId}", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseStatus(value = HttpStatus.CREATED)
	public Attachment saveAttachment(
			@PathVariable String resourceItemId,
			@RequestParam("content") MultipartFile file,
			HttpServletRequest request) throws IOException {
		return attachmentService.saveAttachment(resourceItemId, file);
	}

	@RequestMapping(value = "/attachments/{attachmentId}", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
	public Attachment updateAttachment(
			@PathVariable String attachmentId,
			@RequestBody Attachment attachment,
			HttpServletRequest request) throws IOException {
		return attachmentService.updateAttachment(attachmentId, attachment);
	}

	@RequestMapping(value = "/attachments/{attachmentId}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteAttachment(
			@PathVariable String attachmentId,
			HttpServletRequest request) {
		attachmentService.deleteAttachment(attachmentId);
	}
}
