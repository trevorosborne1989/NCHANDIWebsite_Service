package nchandi.spring.services.NCHANDIWebsite_Service.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import nchandi.spring.services.NCHANDIWebsite_Service.domain.PanelMaterial;
import nchandi.spring.services.NCHANDIWebsite_Service.repositories.PanelMaterialRepository;

@Service
public class PanelMaterialService {

	@Autowired
	PanelMaterialRepository panelMaterialRepo;
	
	Logger logger = LoggerFactory.getLogger("nchandi.spring.services.NCHANDIWebsite_Service.services.PanelMaterialService");
	
	public List<PanelMaterial> getPanelMaterials() {
		
	   List<PanelMaterial> panelMaterials = panelMaterialRepo.findAll();
	   
		if (panelMaterials.size() > 0) {
			return panelMaterials;
		} else {
			throw new ResourceNotFoundException("No Records Found");
		} 
	}

	public Optional<PanelMaterial> getPanelMaterialById(String panelMaterialId) {
		
		Optional<PanelMaterial> panelMaterial = panelMaterialRepo.findById(panelMaterialId);
		
		if (panelMaterial == null) {
			throw new ResourceNotFoundException("PanelMaterial with ID:" + panelMaterialId + " not found.");
		}
		
		return panelMaterial;
	}

	public PanelMaterial savePanelMaterial (PanelMaterial panelMaterial) {
	  
	  return panelMaterialRepo.save(panelMaterial);
	}

	public PanelMaterial updatePanelMaterial(String panelMaterialId, PanelMaterial panelMaterial) {
	  
	  Optional<PanelMaterial> existingPanelMaterial = panelMaterialRepo.findById(panelMaterialId);
	  
	  if (!panelMaterial.getId().equals(panelMaterialId)) {
			throw new DataIntegrityViolationException("Invalid ID was passed in the request body.");
	  } else if (existingPanelMaterial == null) {
		throw new ResourceNotFoundException("PanelMaterial with ID:" + panelMaterialId + " not found.");
	  }
	  
	  return panelMaterialRepo.save(panelMaterial);
	}

	public void deletePanelMaterial(String panelMaterialId) {
	  
	  Optional<PanelMaterial> panelMaterial = panelMaterialRepo.findById(panelMaterialId);
	  
	  if (panelMaterial == null) {
		  throw new ResourceNotFoundException("PanelMaterial with ID:" + panelMaterialId + " not found.");
	  }
	  
	  panelMaterialRepo.delete(panelMaterial.get());
	}
}