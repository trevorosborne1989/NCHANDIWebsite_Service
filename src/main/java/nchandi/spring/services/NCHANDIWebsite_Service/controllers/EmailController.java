package nchandi.spring.services.NCHANDIWebsite_Service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import nchandi.spring.services.NCHANDIWebsite_Service.dto.ContactForm;
import nchandi.spring.services.NCHANDIWebsite_Service.dto.LiteratureRequest;
import nchandi.spring.services.NCHANDIWebsite_Service.services.EmailService;

@RestController
public class EmailController {

  @Autowired
	EmailService emailService;

  @RequestMapping(value = "/email/literature-request", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.CREATED)
	public LiteratureRequest emailLiteratureRequest(
			@RequestBody LiteratureRequest literatureRequest,
			HttpServletRequest request) throws MessagingException {
		return emailService.emailLiteratureRequest(literatureRequest);
	}

  @RequestMapping(value = "/email/contact-form", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.CREATED)
	public ContactForm emailContactForm(
			@RequestBody ContactForm contactForm,
			HttpServletRequest request) throws MessagingException {
		return emailService.emailContactForm(contactForm);
	}



}
