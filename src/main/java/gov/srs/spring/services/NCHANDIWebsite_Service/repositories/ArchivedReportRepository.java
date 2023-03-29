package gov.srs.spring.services.NCHANDIWebsite_Service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import gov.srs.spring.services.NCHANDIWebsite_Service.domain.ArchivedReport;

public interface ArchivedReportRepository extends JpaRepository<ArchivedReport, String> {

}
