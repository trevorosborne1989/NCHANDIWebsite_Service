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

import gov.srs.spring.services.NCHANDIWebsite_Service.domain.Facility;
import gov.srs.spring.services.NCHANDIWebsite_Service.services.FacilityService;

@RestController
public class FacilityController {

	@Autowired
	FacilityService facilityService;

	@RequestMapping(value = "/facilities", method = RequestMethod.GET)
	public List<Facility> getAllFacilities(HttpServletRequest request) {
		return facilityService.getFacilities();
	}
	
	@RequestMapping(value = "/facilities-active", method = RequestMethod.GET)
	public List<Facility> getActiveFacilities(HttpServletRequest request) {
		return facilityService.getActiveFacilities();
	}

	@RequestMapping(value = "/facilities/{facilityId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Facility getFacilitiesById(
			@PathVariable String facilityId,
			HttpServletRequest request) {
		return facilityService.getFacilityById(facilityId);
	}

	@RequestMapping(value = "/facilities", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.CREATED)
	public Facility saveFacility(
			@RequestBody Facility facility,
			HttpServletRequest request) {
		return facilityService.saveFacility(facility);
	}

	@RequestMapping(value = "/facilities/{facilityId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public Facility updateFacility(
			@RequestBody Facility facility,
			@PathVariable String facilityId,
			HttpServletRequest request) {
		return facilityService.updateFacility(facilityId, facility);
	}

	@RequestMapping(value = "/facilities/{facilityId}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteFacility(
			@PathVariable String facilityId,
			HttpServletRequest request) {
		facilityService.deleteFacility(facilityId);
	}
}
