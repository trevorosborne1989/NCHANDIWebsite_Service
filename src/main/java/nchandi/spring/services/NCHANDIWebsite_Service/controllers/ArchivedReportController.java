package nchandi.spring.services.NCHANDIWebsite_Service.controllers;

import java.util.List;
import java.util.Optional;

import jakarta.servlet.http.HttpServletRequest;
import nchandi.spring.services.NCHANDIWebsite_Service.domain.ArchivedReport;
import nchandi.spring.services.NCHANDIWebsite_Service.services.ArchivedReportService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArchivedReportController {

	@Autowired
	ArchivedReportService archivedReportService;

	@RequestMapping(value = "/archived-reports", method = RequestMethod.GET)
	public List<ArchivedReport> getAllArchivedReports(HttpServletRequest request) {
		return archivedReportService.getArchivedReports();
	}

	@RequestMapping(value = "/archived-reports/{archivedReportId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<ArchivedReport> getArchivedReportById(
			@PathVariable String archivedReportId,
			HttpServletRequest request) {
		return archivedReportService.getArchivedReportById(archivedReportId);
	}

	@RequestMapping(value = "/archived-reports", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.CREATED)
	public ArchivedReport saveArchivedReport(
			@RequestBody ArchivedReport archivedReport,
			HttpServletRequest request) {
		return archivedReportService.saveArchivedReport(archivedReport);
	}

	@RequestMapping(value = "/archived-reports/{archivedReportId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ArchivedReport updateArchivedReport(
			@RequestBody ArchivedReport archivedReport,
			@PathVariable String archivedReportId,
			HttpServletRequest request) {
		return archivedReportService.updateArchivedReport(archivedReportId, archivedReport);
	}

	@RequestMapping(value = "/archived-reports/{archivedReportId}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteArchivedReport(
			@PathVariable String archivedReportId,
			HttpServletRequest request) {
		archivedReportService.deleteArchivedReport(archivedReportId);
	}
}
