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

import gov.srs.spring.services.NCHANDIWebsite_Service.domain.PanelMaterial;
import gov.srs.spring.services.NCHANDIWebsite_Service.services.PanelMaterialService;

@RestController
public class PanelMaterialController {

	@Autowired
	PanelMaterialService panelMaterialService;

	@RequestMapping(value = "/panel-materials", method = RequestMethod.GET)
	public List<PanelMaterial> getAllPanelMaterials(HttpServletRequest request) {
		return panelMaterialService.getPanelMaterials();
	}

	@RequestMapping(value = "/panel-materials/{panelMaterialId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public PanelMaterial getPanelMaterialsById(
			@PathVariable String panelMaterialId,
			HttpServletRequest request) {
		return panelMaterialService.getPanelMaterialById(panelMaterialId);
	}

	@RequestMapping(value = "/panel-materials", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.CREATED)
	public PanelMaterial savePanelMaterial(
			@RequestBody PanelMaterial panelMaterial,
			HttpServletRequest request) {
		return panelMaterialService.savePanelMaterial(panelMaterial);
	}

	@RequestMapping(value = "/panel-materials/{panelMaterialId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public PanelMaterial updatePanelMaterial(
			@RequestBody PanelMaterial panelMaterial,
			@PathVariable String panelMaterialId,
			HttpServletRequest request) {
		return panelMaterialService.updatePanelMaterial(panelMaterialId, panelMaterial);
	}

	@RequestMapping(value = "/panel-materials/{panelMaterialId}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deletePanelMaterial(
			@PathVariable String panelMaterialId,
			HttpServletRequest request) {
		panelMaterialService.deletePanelMaterial(panelMaterialId);
	}
}
