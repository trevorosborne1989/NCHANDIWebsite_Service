package gov.srs.spring.services.NCHANDIWebsite_Service.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import gov.srs.spring.services.NCHANDIWebsite_Service.domain.Facility;
import gov.srs.spring.services.NCHANDIWebsite_Service.repositories.FacilityRepository;

@Service
public class FacilityService {

	@Autowired
	FacilityRepository facilityRepo;
	
	Logger logger = LoggerFactory.getLogger("gov.srs.spring.services.NCHANDIWebsite_Service.services.FacilityService");
	
	public List<Facility> getFacilities() {
		
	   List<Facility> facilities = facilityRepo.findAll();
	   
		if (facilities.size() > 0) {
			return facilities;
		} else {
			throw new ResourceNotFoundException("No Records Found");
		} 
	}
	
	public List<Facility> getActiveFacilities() {
		
		List<Facility> facilities = facilityRepo.findByActiveTrue();
		
		if (facilities.size() > 0) {
			return facilities;
		} else {
			throw new ResourceNotFoundException("No Records Found");
		} 
	}

	public Facility getFacilityById(String facilityId) {
		
		Facility facility = facilityRepo.findOne(facilityId);
		
		if (facility == null) {
			throw new ResourceNotFoundException("Facility with ID:" + facilityId + " not found.");
		}
		
		return facility;
	}

	public Facility saveFacility (Facility facility) {
	  
	  return facilityRepo.save(facility);
	}

	public Facility updateFacility(String facilityId, Facility facility) {
	  
	  Facility existingFacility = facilityRepo.findOne(facilityId);
	  
	  if (!facility.getId().equals(facilityId)) {
			throw new DataIntegrityViolationException("Invalid ID was passed in the request body.");
	  } else if (existingFacility == null) {
		throw new ResourceNotFoundException("Facility with ID:" + facilityId + " not found.");
	  }
	  
	  return facilityRepo.save(facility);
	}

	public void deleteFacility(String facilityId) {
	  
	  Facility facility = facilityRepo.findOne(facilityId);
	  
	  if (facility == null) {
		  throw new ResourceNotFoundException("Facility with ID:" + facilityId + " not found.");
	  }
	  
	  facilityRepo.delete(facility);
	}
	
}
