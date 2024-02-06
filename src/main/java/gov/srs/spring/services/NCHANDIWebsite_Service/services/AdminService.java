package gov.srs.spring.services.NCHANDIWebsite_Service.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import gov.srs.spring.services.NCHANDIWebsite_Service.domain.Admin;
import gov.srs.spring.services.NCHANDIWebsite_Service.repositories.AdminRepository;

@Service
public class AdminService {
	
	@Autowired
	AdminRepository adminRepo;
	
//	TODO:
//	@Autowired
//	AuthService authService;
	
	 Logger logger = LoggerFactory.getLogger("gov.srs.spring.services.NCHANDIWebsite_Service.services.AdminService");
	
	 public List<Admin> getAdmins() {
	    	
	      List<Admin> admins = adminRepo.findAll();
	      
			if (admins.size() > 0) {
				return admins;
			} else {
				throw new ResourceNotFoundException("No Records Found");
			} 
	  }
	 
	  public Optional<Admin> getAdminById(String adminId) {
	    	
			Optional<Admin> admin = adminRepo.findById(adminId);
			
			if (admin == null) {
				throw new ResourceNotFoundException("Admin with ID:" + adminId + " not found.");
			}
			
			return admin;
	    }
	  
	  public Admin saveAdmin (Admin admin) {
		  
		  return adminRepo.save(admin);
	  }
	  
	  public Admin updateAdmin(String adminId, Admin admin) {
		  
		  Optional<Admin> existingAdmin = adminRepo.findById(adminId);
		  
		  if (!admin.getId().equals(adminId)) {
				throw new DataIntegrityViolationException("Invalid ID was passed in the request body.");
		  } else if (existingAdmin == null) {
			throw new ResourceNotFoundException("Admin with ID:" + adminId + " not found.");
		  }
		  
		  return adminRepo.save(admin);
	  }
	  
	  public void deleteAdmin(String adminId) {
		  
		  Optional<Admin> admin = adminRepo.findById(adminId);
		  
		  if (admin == null) {
			  throw new ResourceNotFoundException("Admin with ID:" + adminId + " not found.");
		  }
		  
		  adminRepo.delete(admin.get());
	  }
}
