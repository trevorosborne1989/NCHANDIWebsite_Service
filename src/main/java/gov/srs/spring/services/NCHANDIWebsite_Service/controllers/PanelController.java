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

import gov.srs.spring.services.NCHANDIWebsite_Service.domain.Panel;
import gov.srs.spring.services.NCHANDIWebsite_Service.services.PanelService;

@RestController
public class PanelController {

	@Autowired
	PanelService panelService;

	@RequestMapping(value = "/panels", method = RequestMethod.GET)
	public List<Panel> getAllPanels(HttpServletRequest request) {
		return panelService.getPanels();
	}
	
	@RequestMapping(value = "/panels-open", method = RequestMethod.GET)
	public List<Panel> getOpenPanels(HttpServletRequest request) {
		return panelService.getOpenPanels();
	}

	@RequestMapping(value = "/panels/{panelId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Panel getPanelById(
			@PathVariable String panelId,
			HttpServletRequest request) {
		return panelService.getPanelById(panelId);
	}

	@RequestMapping(value = "/panels", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.CREATED)
	public Panel savePanel(
			@RequestBody Panel panel,
			HttpServletRequest request) {
		return panelService.savePanel(panel);
	}

	@RequestMapping(value = "/panels/{panelId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public Panel updatePanel(
			@RequestBody Panel panel,
			@PathVariable String panelId,
			HttpServletRequest request) {
		return panelService.updatePanel(panelId, panel);
	}

	@RequestMapping(value = "/panels/{panelId}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deletePanel(
			@PathVariable String panelId,
			HttpServletRequest request) {
		panelService.deletePanel(panelId);
	}
}
