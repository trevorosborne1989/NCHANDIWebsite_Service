package nchandi.spring.services.NCHANDIWebsite_Service.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import nchandi.spring.services.NCHANDIWebsite_Service.domain.Facility;

public interface FacilityRepository extends JpaRepository<Facility, String> {

	List<Facility> findByActiveTrue();

}
