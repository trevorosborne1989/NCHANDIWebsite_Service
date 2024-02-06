package gov.srs.spring.services.NCHANDIWebsite_Service.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import gov.srs.spring.services.NCHANDIWebsite_Service.domain.GeneralResource;
import gov.srs.spring.services.NCHANDIWebsite_Service.repositories.GeneralResourceRepository;

@Service
public class GeneralResourceService {

	@Autowired
	GeneralResourceRepository generalResourceRepo;
	
	Logger logger = LoggerFactory.getLogger("gov.srs.spring.services.NCHANDIWebsite_Service.services.GeneralResourceService");
	
	public List<GeneralResource> getGeneralResources() {
		
	   List<GeneralResource> generalResources = generalResourceRepo.findAll();
	   
		if (generalResources.size() > 0) {
			return generalResources;
		} else {
			throw new ResourceNotFoundException("No Records Found");
		} 
	}

	public Optional<GeneralResource> getGeneralResourceById(String generalResourceId) {
		
		Optional<GeneralResource> generalResource = generalResourceRepo.findById(generalResourceId);
		
		if (generalResource == null) {
			throw new ResourceNotFoundException("GeneralResource with ID:" + generalResourceId + " not found.");
		}
		
		return generalResource;
	}

	public GeneralResource saveGeneralResource (GeneralResource generalResource) {
	  
	  return generalResourceRepo.save(generalResource);
	}

	public GeneralResource updateGeneralResource(String generalResourceId, GeneralResource generalResource) {
	  
	  Optional<GeneralResource> existingGeneralResource = generalResourceRepo.findById(generalResourceId);
	  
	  if (!generalResource.getId().equals(generalResourceId)) {
			throw new DataIntegrityViolationException("Invalid ID was passed in the request body.");
	  } else if (existingGeneralResource == null) {
		throw new ResourceNotFoundException("GeneralResource with ID:" + generalResourceId + " not found.");
	  }
	  
	  return generalResourceRepo.save(generalResource);
	}

	public void deleteGeneralResource(String generalResourceId) {
	  
	  Optional<GeneralResource> generalResource = generalResourceRepo.findById(generalResourceId);
	  
	  if (generalResource == null) {
		  throw new ResourceNotFoundException("GeneralResource with ID:" + generalResourceId + " not found.");
	  }
	  
	  generalResourceRepo.delete(generalResource.get());
	}
}
