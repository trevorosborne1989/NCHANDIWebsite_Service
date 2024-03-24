package nchandi.spring.services.NCHANDIWebsite_Service.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import nchandi.spring.services.NCHANDIWebsite_Service.domain.ArchivedReport;
import nchandi.spring.services.NCHANDIWebsite_Service.repositories.ArchivedReportRepository;

@Service
public class ArchivedReportService {

	@Autowired
	ArchivedReportRepository archivedReportRepo;
	
	Logger logger = LoggerFactory.getLogger("nchandi.spring.services.NCHANDIWebsite_Service.services.ArchivedReportService");
	
	public List<ArchivedReport> getArchivedReports() {
		
		List<ArchivedReport> archivedReports = archivedReportRepo.findAll();
		
		if (archivedReports.size() > 0) {
			return archivedReports;
		} else {
			throw new ResourceNotFoundException("No Records Found");
		}
	}
	
	public Optional<ArchivedReport> getArchivedReportById(String archivedReportId) {
    	
		Optional<ArchivedReport> archivedReport = archivedReportRepo.findById(archivedReportId);
		
		if (archivedReport == null) {
			throw new ResourceNotFoundException("ArchivedReport with ID:" + archivedReportId + " not found.");
		}
		
		return archivedReport;
    }
	
	public ArchivedReport saveArchivedReport (ArchivedReport archivedReport) {
		  
		  return archivedReportRepo.save(archivedReport);
	}
	
	public ArchivedReport updateArchivedReport(String archivedReportId, ArchivedReport archivedReport) {
		  
		Optional<ArchivedReport> existingArchivedReport = archivedReportRepo.findById(archivedReportId);
		
		  if (!archivedReport.getId().equals(archivedReportId)) {
				throw new DataIntegrityViolationException("Invalid ID was passed in the request body.");
		  } else if (existingArchivedReport == null) {
				throw new ResourceNotFoundException("ArchivedReport with ID:" + archivedReportId + " not found.");
		  }
			
		  return archivedReportRepo.save(archivedReport);
	 }
	
	public void deleteArchivedReport(String archivedReportId) {
		
		  Optional<ArchivedReport> archivedReport = archivedReportRepo.findById(archivedReportId);
		
		  if (archivedReport == null) {
			  throw new ResourceNotFoundException("ArchivedReport with ID:" + archivedReportId + " not found.");
		  }
		  
		  archivedReportRepo.delete(archivedReport.get());
	 }
	
}
