package gov.srs.spring.services.NCHANDIWebsite_Service.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import gov.srs.spring.services.NCHANDIWebsite_Service.domain.Panel;

public interface PanelRepository extends JpaRepository<Panel, String> {

	List<Panel> findByMarkAsMembersNeededTrue();
}
