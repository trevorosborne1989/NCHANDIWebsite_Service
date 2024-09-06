package nchandi.spring.services.NCHANDIWebsite_Service.controllers;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import jakarta.servlet.http.HttpServletRequest;
import nchandi.spring.services.NCHANDIWebsite_Service.domain.People;
import nchandi.spring.services.NCHANDIWebsite_Service.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PeopleController {

	@Autowired
	PeopleService peopleService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public Collection<? extends GrantedAuthority> successLogin(HttpServletRequest request) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			return authentication.getAuthorities();
		}
		return null;
	}

	@RequestMapping(value = "/people", method = RequestMethod.GET)
	public List<People> getAllPeoples(HttpServletRequest request) {
		return peopleService.getPeoples();
	}

	@RequestMapping(value = "/people/{personId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<People> getPeopleById(
			@PathVariable String personId,
			HttpServletRequest request) {
		return peopleService.getPeopleById(personId);
	}

	@RequestMapping(value = "/people", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.CREATED)
	public People savePerson(
			@RequestBody People people,
			HttpServletRequest request) {
		return peopleService.savePerson(people);
	}

	@RequestMapping(value = "/people/{personId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public People updatePerson(
			@RequestBody People people,
			@PathVariable String personId,
			HttpServletRequest request) {
		return peopleService.updatePerson(personId, people);
	}

	@RequestMapping(value = "/people/{personId}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deletePeople(
			@PathVariable String personId,
			HttpServletRequest request) {
		peopleService.deletePeople(personId);
	}
}
