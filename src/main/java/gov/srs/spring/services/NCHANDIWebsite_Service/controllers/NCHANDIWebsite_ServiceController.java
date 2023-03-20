package gov.srs.spring.services.NCHANDIWebsite_Service.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NCHANDIWebsite_ServiceController {
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String getTest() {
		return "Data from endpoint";
	}
}
