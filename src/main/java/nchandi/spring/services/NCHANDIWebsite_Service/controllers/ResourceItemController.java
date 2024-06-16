package nchandi.spring.services.NCHANDIWebsite_Service.controllers;

import java.util.List;
import java.util.Optional;

import jakarta.servlet.http.HttpServletRequest;
import nchandi.spring.services.NCHANDIWebsite_Service.domain.ResourceItem;
import nchandi.spring.services.NCHANDIWebsite_Service.services.ResourceItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceItemController {

	@Autowired
	ResourceItemService resourceItemService;

	@RequestMapping(value = "/resource-items", method = RequestMethod.GET)
	public List<ResourceItem> getAllResourceItems(HttpServletRequest request) {
		return resourceItemService.getResourceItems();
	}

	@RequestMapping(value = "/resource-items/{resourceItemId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<ResourceItem> getResourceItemById(
			@PathVariable String resourceItemId,
			HttpServletRequest request) {
		return resourceItemService.getResourceItemById(resourceItemId);
	}

	@RequestMapping(value = "/resource-items", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResourceItem saveResourceItem(
			@RequestBody ResourceItem resourceItem,
			HttpServletRequest request) {
		return resourceItemService.saveResourceItem(resourceItem);
	}

	@RequestMapping(value = "/resource-items/{resourceItemId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResourceItem updateItem(
			@RequestBody ResourceItem resourceItem,
			@PathVariable String resourceItemId,
			HttpServletRequest request) {
		return resourceItemService.updateItem(resourceItemId, resourceItem);
	}

	@RequestMapping(value = "/resource-items/{resourceItemId}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteResourceItem(
			@PathVariable String resourceItemId,
			HttpServletRequest request) {
		resourceItemService.deleteResourceItem(resourceItemId);
	}
}
