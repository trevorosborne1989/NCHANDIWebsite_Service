package nchandi.spring.services.NCHANDIWebsite_Service.exceptions;

import java.io.IOException;

import jakarta.security.auth.message.AuthException;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/*
 * This class is used to handle exceptions thrown in the controller or service layer.  It provides
 * consistent error responses and is handled automatically by Spring Boot.
 */

@ControllerAdvice
public class GlobalControllerExceptionHandler {
	Logger logger = LoggerFactory.getLogger("nchandi.spring.services.NCHANDIWebsite_Service.exceptions.GlobalControllerExceptionHandler");
	
	@ExceptionHandler(ResourceNotFoundException.class)  //404
	void handleNotFound(HttpServletResponse response) throws IOException {
	    response.sendError(HttpStatus.NOT_FOUND.value());
	}
	
	@ExceptionHandler	//400
	void handleIllegalArgumentException(IllegalArgumentException e, HttpServletResponse response) throws IOException {
	    response.sendError(HttpStatus.BAD_REQUEST.value());
	}
	
	@ExceptionHandler  //401 
	void handleAuthenticationException(AuthException a, HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.UNAUTHORIZED.value());
	}
	
	@ExceptionHandler  //401 
	void handleMissingRolesException(MissingRolesException a, HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.UNAUTHORIZED.value());
	}

	@ExceptionHandler
	void handleDuplicateEntryException(DataIntegrityViolationException d, HttpServletResponse response) throws IOException {
		logger.error(d.getMessage());				
		response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Entry must be unique.  Requested record to be created already exists.");
	}
	
	@ExceptionHandler
	void handleNullPointerExceptioni(NullPointerException e, HttpServletResponse response) throws IOException {
		logger.error(e.getMessage());
		response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),"NullPointerException");
	}
}
