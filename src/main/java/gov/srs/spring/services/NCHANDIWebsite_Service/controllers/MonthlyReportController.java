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

import gov.srs.spring.services.NCHANDIWebsite_Service.domain.MonthlyReport;
import gov.srs.spring.services.NCHANDIWebsite_Service.services.MonthlyReportService;

@RestController
public class MonthlyReportController {

	
	@Autowired
	MonthlyReportService monthlyReportService;

	@RequestMapping(value = "/monthly-reports", method = RequestMethod.GET)
	public List<MonthlyReport> getAllMonthlyReports(HttpServletRequest request) {
		return monthlyReportService.getMonthlyReports();
	}

	@RequestMapping(value = "/monthly-reports/{monthlyReportId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public MonthlyReport getMonthlyReportsById(
			@PathVariable String monthlyReportId,
			HttpServletRequest request) {
		return monthlyReportService.getMonthlyReportById(monthlyReportId);
	}

	@RequestMapping(value = "/monthly-reports", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.CREATED)
	public MonthlyReport saveMonthlyReport(
			@RequestBody MonthlyReport monthlyReport,
			HttpServletRequest request) {
		return monthlyReportService.saveMonthlyReport(monthlyReport);
	}

	@RequestMapping(value = "/monthly-reports/{monthlyReportId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public MonthlyReport updateMonthlyReport(
			@RequestBody MonthlyReport monthlyReport,
			@PathVariable String monthlyReportId,
			HttpServletRequest request) {
		return monthlyReportService.updateMonthlyReport(monthlyReportId, monthlyReport);
	}

	@RequestMapping(value = "/monthly-reports/{monthlyReportId}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteMonthlyReport(
			@PathVariable String monthlyReportId,
			HttpServletRequest request) {
		monthlyReportService.deleteMonthlyReport(monthlyReportId);
	}
}
