package gov.srs.spring.services.NCHANDIWebsite_Service.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import gov.srs.spring.services.NCHANDIWebsite_Service.domain.Pending;
import gov.srs.spring.services.NCHANDIWebsite_Service.repositories.PendingRepository;

@Service
public class PendingService {

	@Autowired
	PendingRepository pendingRepo;
	
	Logger logger = LoggerFactory.getLogger("gov.srs.spring.services.NCHANDIWebsite_Service.services.PendingService");
	
	public List<Pending> getPendings() {
		
	   List<Pending> pendings = pendingRepo.findAll();
	   
		if (pendings.size() > 0) {
			return pendings;
		} else {
			throw new ResourceNotFoundException("No Records Found");
		} 
	}

	public Pending getPendingById(String pendingId) {
		
		Pending pending = pendingRepo.findOne(pendingId);
		
		if (pending == null) {
			throw new ResourceNotFoundException("Pending with ID:" + pendingId + " not found.");
		}
		
		return pending;
	}

	public Pending savePending (Pending pending) {
	  
	  return pendingRepo.save(pending);
	}

	public Pending updatePending(String pendingId, Pending pending) {
	  
	  Pending existingPending = pendingRepo.findOne(pendingId);
	  
	  if (!pending.getId().equals(pendingId)) {
			throw new DataIntegrityViolationException("Invalid ID was passed in the request body.");
	  } else if (existingPending == null) {
		throw new ResourceNotFoundException("Pending with ID:" + pendingId + " not found.");
	  }
	  
	  return pendingRepo.save(pending);
	}

	public void deletePending(String pendingId) {
	  
	  Pending pending = pendingRepo.findOne(pendingId);
	  
	  if (pending == null) {
		  throw new ResourceNotFoundException("Pending with ID:" + pendingId + " not found.");
	  }
	  
	  pendingRepo.delete(pending);
	}
}
