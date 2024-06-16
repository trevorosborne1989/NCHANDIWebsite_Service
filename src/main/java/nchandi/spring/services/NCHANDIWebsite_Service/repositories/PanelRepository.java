package nchandi.spring.services.NCHANDIWebsite_Service.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import nchandi.spring.services.NCHANDIWebsite_Service.domain.Panel;

public interface PanelRepository extends JpaRepository<Panel, String> {

	List<Panel> findByMarkAsMembersNeededTrueAndActiveTrue();
}
