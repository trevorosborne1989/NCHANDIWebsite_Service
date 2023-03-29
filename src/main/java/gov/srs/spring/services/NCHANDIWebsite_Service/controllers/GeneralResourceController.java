package gov.srs.spring.services.NCHANDIWebsite_Service.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import gov.srs.spring.services.NCHANDIWebsite_Service.domain.GeneralResource;
import gov.srs.spring.services.NCHANDIWebsite_Service.services.GeneralResourceService;

@RestController
public class GeneralResourceController {

	@Autowired
	GeneralResourceService generalResourceService;

	@RequestMapping(value = "/general-resources", method = RequestMethod.GET)
	public List<GeneralResource> getAllGeneralResources(HttpServletRequest request) {
		return generalResourceService.getGeneralResources();
	}

	@RequestMapping(value = "/general-resources/{generalResourceId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public GeneralResource getGeneralResourceById(
			@PathVariable String generalResourceId,
			HttpServletRequest request) {
		return generalResourceService.getGeneralResourceById(generalResourceId);
	}

	@RequestMapping(value = "/general-resources", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.CREATED)
	public GeneralResource saveGeneralResource(
			@RequestBody GeneralResource generalResource,
			HttpServletRequest request) {
		return generalResourceService.saveGeneralResource(generalResource);
	}

	@RequestMapping(value = "/general-resources/{generalResourceId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public GeneralResource updateGeneralResource(
			@RequestBody GeneralResource generalResource,
			@PathVariable String generalResourceId,
			HttpServletRequest request) {
		return generalResourceService.updateGeneralResource(generalResourceId, generalResource);
	}

	@RequestMapping(value = "/general-resources/{generalResourceId}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteGeneralResource(
			@PathVariable String generalResourceId,
			HttpServletRequest request) {
		generalResourceService.deleteGeneralResource(generalResourceId);
	}
}
