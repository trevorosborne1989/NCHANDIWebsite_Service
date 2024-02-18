package nchandi.spring.services.NCHANDIWebsite_Service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import nchandi.spring.services.NCHANDIWebsite_Service.domain.Pending;

public interface PendingRepository extends JpaRepository<Pending, String> {

}
