package gov.srs.spring.services.NCHANDIWebsite_Service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import gov.srs.spring.services.NCHANDIWebsite_Service.domain.Admin;

public interface PanelMaterialRepository extends JpaRepository<Admin, String> {

}