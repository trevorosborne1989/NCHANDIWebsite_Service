package gov.srs.spring.services.NCHANDIWebsite_Service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import gov.srs.spring.services.NCHANDIWebsite_Service.domain.Admin;

public interface PendingRepository extends JpaRepository<Admin, String> {

}
