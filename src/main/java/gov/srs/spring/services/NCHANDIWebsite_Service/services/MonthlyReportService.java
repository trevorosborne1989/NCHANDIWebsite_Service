package gov.srs.spring.services.NCHANDIWebsite_Service.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import gov.srs.spring.services.NCHANDIWebsite_Service.domain.MonthlyReport;
import gov.srs.spring.services.NCHANDIWebsite_Service.repositories.MonthlyReportRepository;

@Service
public class MonthlyReportService {

	@Autowired
	MonthlyReportRepository monthlyReportRepo;
	
	Logger logger = LoggerFactory.getLogger("gov.srs.spring.services.NCHANDIWebsite_Service.services.MonthlyReportService");
	
	public List<MonthlyReport> getMonthlyReports() {
		
	   List<MonthlyReport> monthlyReports = monthlyReportRepo.findAll();
	   
		if (monthlyReports.size() > 0) {
			return monthlyReports;
		} else {
			throw new ResourceNotFoundException("No Records Found");
		} 
	}

	public Optional<MonthlyReport> getMonthlyReportById(String monthlyReportId) {
		
		Optional<MonthlyReport> monthlyReport = monthlyReportRepo.findById(monthlyReportId);
		
		if (monthlyReport == null) {
			throw new ResourceNotFoundException("MonthlyReport with ID:" + monthlyReportId + " not found.");
		}
		
		return monthlyReport;
	}

	public MonthlyReport saveMonthlyReport (MonthlyReport monthlyReport) {
	  
	  return monthlyReportRepo.save(monthlyReport);
	}

	public MonthlyReport updateMonthlyReport(String monthlyReportId, MonthlyReport monthlyReport) {
	  
	  Optional<MonthlyReport> existingMonthlyReport = monthlyReportRepo.findById(monthlyReportId);
	  
	  if (!monthlyReport.getId().equals(monthlyReportId)) {
			throw new DataIntegrityViolationException("Invalid ID was passed in the request body.");
	  } else if (existingMonthlyReport == null) {
		throw new ResourceNotFoundException("MonthlyReport with ID:" + monthlyReportId + " not found.");
	  }
	  
	  return monthlyReportRepo.save(monthlyReport);
	}

	public void deleteMonthlyReport(String monthlyReportId) {
	  
	  Optional<MonthlyReport> monthlyReport = monthlyReportRepo.findById(monthlyReportId);
	  
	  if (monthlyReport == null) {
		  throw new ResourceNotFoundException("MonthlyReport with ID:" + monthlyReportId + " not found.");
	  }
	  
	  monthlyReportRepo.delete(monthlyReport.get());
	}
}
