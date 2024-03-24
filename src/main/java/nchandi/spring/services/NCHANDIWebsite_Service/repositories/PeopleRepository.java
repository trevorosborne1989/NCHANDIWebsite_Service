package nchandi.spring.services.NCHANDIWebsite_Service.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import nchandi.spring.services.NCHANDIWebsite_Service.domain.People;

public interface PeopleRepository extends JpaRepository<People, String> {
  List<People> findByFirstNameAndLastName(String firstName, String lastName);
}
