package nchandi.spring.services.NCHANDIWebsite_Service.controllers;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import nchandi.spring.services.NCHANDIWebsite_Service.domain.PasswordResetToken;
import nchandi.spring.services.NCHANDIWebsite_Service.services.PasswordResetTokenService;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PasswordResetTokenController {

  @Autowired
	PasswordResetTokenService passwordResetTokenService;;

	@RequestMapping(value = "/password-reset-tokens/{token}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PasswordResetToken> getPasswordResetTokenToken(
			@PathVariable String token,
			HttpServletRequest request) {
		return passwordResetTokenService.getPasswordResetTokenByToken(token);
  }

	@RequestMapping(value = "/password-reset-tokens/{personEmail}", method = RequestMethod.POST)
	public void resetPassword(
		@PathVariable String personEmail,
		HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {
		passwordResetTokenService.resetPassword(personEmail);
	}
}
