package nchandi.spring.services.NCHANDIWebsite_Service.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import nchandi.spring.services.NCHANDIWebsite_Service.domain.Attachment;
import nchandi.spring.services.NCHANDIWebsite_Service.domain.ResourceItem;
import nchandi.spring.services.NCHANDIWebsite_Service.repositories.ResourceItemRepository;

@Service
public class ResourceItemService {

	@Autowired
	ResourceItemRepository resourceItemRepository;

	@Autowired
	AttachmentService attachmentService;

	Logger logger = LoggerFactory.getLogger("nchandi.spring.services.NCHANDIWebsite_Service.services.ResourceItemService");

	public List<ResourceItem> getResourceItems() {
		List<ResourceItem> resourceItems = resourceItemRepository.findAll();
		if (resourceItems.size() > 0) {
			return resourceItems;
		} else {
			throw new ResourceNotFoundException("No Records Found");
		}
	}

	public Optional<ResourceItem> getResourceItemById(String resourceItemId) {
		Optional<ResourceItem> resourceItem = resourceItemRepository.findById(resourceItemId);
		if (resourceItem == null) {
			throw new ResourceNotFoundException("ResourceItem with ID:" + resourceItemId + " not found.");
		}
		return resourceItem;
	}

	public ResourceItem saveResourceItem(ResourceItem resourceItem) {
		Date currentDate = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		resourceItem.setCreatedDate(simpleDateFormat.format(currentDate));
		List<ResourceItem> existingMonthAndType = resourceItemRepository.findByTypeAndMonthOfYear(resourceItem.getType(), resourceItem.getMonthOfYear());
		if (!existingMonthAndType.isEmpty()) {
			deleteResourceItem(existingMonthAndType.get(0).getId());
		}
		return resourceItemRepository.save(resourceItem);
	}

	public ResourceItem updateItem(String resourceItemId, ResourceItem resourceItem) {
		Optional<ResourceItem> existingResourceItem = resourceItemRepository.findById(resourceItemId);
		if (!resourceItem.getId().equals(resourceItemId)) {
			throw new DataIntegrityViolationException("Invalid ID was passed in the request body.");
		} else if (existingResourceItem == null) {
			throw new ResourceNotFoundException("ResourceItem with ID:" + resourceItemId + " not found.");
		}
		return resourceItemRepository.save(resourceItem);
	}

	public void deleteResourceItem(String resourceItemId) {
		Optional<ResourceItem> resourceItem = resourceItemRepository.findById(resourceItemId);
		if (resourceItem == null) {
			throw new ResourceNotFoundException("ResourceItem with ID:" + resourceItemId + " not found.");
		}
		Optional<Attachment> attachment = attachmentService.getAttachmentById(resourceItemId);
		if (attachment != null) {
			attachmentService.deleteAttachment(resourceItemId);
		}
		resourceItemRepository.delete(resourceItem.get());
	}
}
