package gov.srs.spring.services.NCHANDIWebsite_Service.controllers;

import java.util.List;
import java.util.Optional;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import gov.srs.spring.services.NCHANDIWebsite_Service.domain.Admin;
import gov.srs.spring.services.NCHANDIWebsite_Service.services.AdminService;

@RestController
public class AdminController {

	@Autowired
	AdminService adminService;

	@RequestMapping(value = "/admins", method = RequestMethod.GET)
	public List<Admin> getAllAdmins(HttpServletRequest request) {
		return adminService.getAdmins();
	}

	@RequestMapping(value = "/admins/{adminId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Admin> getAdminById(
			@PathVariable String adminId,
			HttpServletRequest request) {
		return adminService.getAdminById(adminId);
	}

	@RequestMapping(value = "/admins", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.CREATED)
	public Admin saveAdmin(
			@RequestBody Admin admin,
			HttpServletRequest request) {
		return adminService.saveAdmin(admin);
	}

	@RequestMapping(value = "/admins/{adminId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public Admin updateAdmin(
			@RequestBody Admin admin,
			@PathVariable String adminId,
			HttpServletRequest request) {
		return adminService.updateAdmin(adminId, admin);
	}

	@RequestMapping(value = "/admins/{adminId}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteAdmin(
			@PathVariable String adminId,
			HttpServletRequest request) {
		adminService.deleteAdmin(adminId);
	}
}
