package gov.srs.spring.services.NCHANDIWebsite_Service.controllers;

import java.util.List;
import java.util.Optional;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import gov.srs.spring.services.NCHANDIWebsite_Service.domain.Pending;
import gov.srs.spring.services.NCHANDIWebsite_Service.services.PendingService;

@RestController
public class PendingController {

	@Autowired
	PendingService pendingService;

	@RequestMapping(value = "/pendings", method = RequestMethod.GET)
	public List<Pending> getAllPendings(HttpServletRequest request) {
		return pendingService.getPendings();
	}

	@RequestMapping(value = "/pendings/{pendingId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Pending> getPendingsById(
			@PathVariable String pendingId,
			HttpServletRequest request) {
		return pendingService.getPendingById(pendingId);
	}

	@RequestMapping(value = "/pendings", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.CREATED)
	public Pending savePending(
			@RequestBody Pending pending,
			HttpServletRequest request) {
		return pendingService.savePending(pending);
	}

	@RequestMapping(value = "/pendings/{pendingId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public Pending updatePending(
			@RequestBody Pending pending,
			@PathVariable String pendingId,
			HttpServletRequest request) {
		return pendingService.updatePending(pendingId, pending);
	}

	@RequestMapping(value = "/pendings/{pendingId}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deletePending(
			@PathVariable String pendingId,
			HttpServletRequest request) {
		pendingService.deletePending(pendingId);
	}
}
