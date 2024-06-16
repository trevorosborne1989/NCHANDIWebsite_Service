package nchandi.spring.services.NCHANDIWebsite_Service.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import nchandi.spring.services.NCHANDIWebsite_Service.domain.People;
import nchandi.spring.services.NCHANDIWebsite_Service.repositories.PeopleRepository;
import nchandi.spring.services.NCHANDIWebsite_Service.utils.PersonComparator;

@Service
public class PeopleService {

	@Autowired
	PeopleRepository peopleRepo;

	Logger logger = LoggerFactory.getLogger("nchandi.spring.services.NCHANDIWebsite_Service.services.PeopleService");

	public List<People> getPeoples() {

		List<People> people = peopleRepo.findAll();
		if (people.size() > 0) {
			Collections.sort(people, new PersonComparator());
			return people;
		} else {
			throw new ResourceNotFoundException("No Records Found");
		}
	}

	public Optional<People> getPeopleById(String personId) {

		Optional<People> people = peopleRepo.findById(personId);
		if (people == null) {
			throw new ResourceNotFoundException("People with ID:" + personId + " not found.");
		}
		return people;
	}

	public People savePerson (People people) {

		List<People> existingPersons = peopleRepo.findByFirstNameAndLastName(people.getFirstName(), people.getLastName());
		if (existingPersons.size() > 0) {
			People existingPerson = existingPersons.get(0);
			people.setId(existingPerson.getId());
			return updatePerson(existingPerson.getId(), people);
		}
		return peopleRepo.save(people);
	}

	public People updatePerson(String personId, People people) {
		Optional<People> existingPeople = peopleRepo.findById(personId);
		if (!people.getId().equals(personId)) {
			throw new DataIntegrityViolationException("Invalid ID was passed in the request body.");
		} else if (existingPeople.isEmpty()) {
		throw new ResourceNotFoundException("Person with ID:" + personId + " not found.");
		}
		return peopleRepo.save(people);
	}

	public void deletePeople(String personId) {

		Optional<People> people = peopleRepo.findById(personId);
		if (people.isEmpty()) {
			throw new ResourceNotFoundException("People with ID:" + personId + " not found.");
		}
		peopleRepo.delete(people.get());
	}
}
