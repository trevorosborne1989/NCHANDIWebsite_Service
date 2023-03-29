package gov.srs.spring.services.NCHANDIWebsite_Service.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import gov.srs.spring.services.NCHANDIWebsite_Service.domain.Panel;
import gov.srs.spring.services.NCHANDIWebsite_Service.repositories.PanelRepository;

@Service
public class PanelService {
	
	@Autowired
	PanelRepository panelRepo;
	
//	TODO: 
//	@Autowired
//	AuthService authService;
	
	Logger logger = LoggerFactory.getLogger("gov.srs.spring.services.NCHANDIWebsite_Service.services.PanelService");
	
	 public List<Panel> getPanels() {
	    	
	      List<Panel> panels = panelRepo.findAll();
	      
			if (panels.size() > 0) {
				return panels;
			} else {
				throw new ResourceNotFoundException("No Records Found");
			} 
	  }
	 
	 public List<Panel> getOpenPanels() {
		 
		 List<Panel> panels = panelRepo.findByMarkAsMembersNeededTrue();
		 
		 if (panels.size() > 0) {
				return panels;
		 } else {
				throw new ResourceNotFoundException("No Records Found");
		 } 
	 }
	 
	 public Panel getPanelById(String panelId) {
	    	
			Panel panel = panelRepo.findOne(panelId);
			
			if (panel == null) {
				throw new ResourceNotFoundException("Panel with ID:" + panelId + " not found.");
			}
			return panel;
	  }
	  
	  public Panel savePanel(Panel panel) {
		  
		  return panelRepo.save(panel);
	  }
	  
	  public Panel updatePanel(String panelId, Panel panel) {
		  
		  Panel existingPanel = panelRepo.findOne(panelId);
		  
		  if (!panel.getId().equals(panelId)) {
				throw new DataIntegrityViolationException("Invalid ID was passed in the request body.");
		  } else if (existingPanel == null) {
				throw new ResourceNotFoundException("Panel with ID:" + panelId + " not found.");
		  }
			
		  return panelRepo.save(panel);
	  }
	  
	  public void deletePanel(String panelId) {
		  
		  Panel panel = panelRepo.findOne(panelId);
		  
		  if (panel == null) {
			  throw new ResourceNotFoundException("Panel with ID:" + panelId + " not found.");
		  }
		  
		  panelRepo.delete(panel);
	  }
}
