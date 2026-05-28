package nchandi.spring.services.NCHANDIWebsite_Service.services;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import nchandi.spring.services.NCHANDIWebsite_Service.domain.PasswordResetToken;
import nchandi.spring.services.NCHANDIWebsite_Service.domain.People;
import nchandi.spring.services.NCHANDIWebsite_Service.repositories.PasswordResetTokenRepository;
import nchandi.spring.services.NCHANDIWebsite_Service.repositories.PeopleRepository;

@Service
public class PasswordResetTokenService {

	@Autowired
	PasswordResetTokenRepository passwordResetTokenRepo;

	@Autowired
	EmailService emailService;

	@Autowired
	PeopleRepository peopleRepo;

	Logger logger = LoggerFactory.getLogger("nchandi.spring.services.NCHANDIWebsite_Service.services.PasswordResetTokenService");

	public Optional<PasswordResetToken> getPasswordResetTokenById(String passwordResetTokenId) {
		Optional<PasswordResetToken> passwordResetToken = passwordResetTokenRepo.findById(passwordResetTokenId);
		if (passwordResetToken == null) {
			throw new ResourceNotFoundException("Password Reset Token with ID:" + passwordResetTokenId + " not found.");
		}
		return passwordResetToken;
	}

	public List<PasswordResetToken> getPasswordResetTokenByToken(String token) {
		String hashedToken;

		try {
			// Create SHA-256 MessageDigest instance
			MessageDigest digest = MessageDigest.getInstance("SHA-256");

			// Hash the token bytes
			byte[] hash = digest.digest(token.getBytes(java.nio.charset.StandardCharsets.UTF_8));

			// Encode to Base64 for a clean string representation
			hashedToken =  Base64.getEncoder().encodeToString(hash);
		} catch (NoSuchAlgorithmException e) {
				throw new RuntimeException("Hash algorithm not found", e);
    }
		List<PasswordResetToken> passwordResetTokens = passwordResetTokenRepo.findByToken(hashedToken);
		if (passwordResetTokens == null) {
			throw new ResourceNotFoundException("Password Reset Token: " + token + " not found.");
		}
		if (passwordResetTokens != null && !passwordResetTokens.isEmpty()) {
			if (passwordResetTokens.get(0).getExpiration().before(new Date()) ) {
				passwordResetTokens.clear();
				throw new NoSuchElementException("Password Reset Token: " + " has expired.");
			}
			deletePasswordResetTokenByPersonId(passwordResetTokens.get(0).getPersonId());
		}
		return passwordResetTokens;
	}

	public PasswordResetToken saveToken(PasswordResetToken passwordResetToken) {
		return passwordResetTokenRepo.save(passwordResetToken);
	}

	public void deletePasswordResetToken(String passwordResetTokenId) {
		Optional<PasswordResetToken> passwordResetToken = passwordResetTokenRepo.findById(passwordResetTokenId);
		if (passwordResetToken == null) {
			throw new ResourceNotFoundException("Password Reset Token with ID:" + passwordResetTokenId + " not found.");
		}
		passwordResetTokenRepo.delete(passwordResetToken.get());
	}

	public void deletePasswordResetTokenByPersonId(String personId) {
		List<PasswordResetToken> passwordResetToken = passwordResetTokenRepo.findByPersonId(personId);
		if (passwordResetToken == null) {
			throw new ResourceNotFoundException("Password Reset Token with personId:" + personId + " not found.");
		}
		passwordResetTokenRepo.deleteByPersonId(personId);
	}

	public void resetPassword(String personEmail) throws MessagingException, UnsupportedEncodingException {
		List<People> people = peopleRepo.findByEmail(personEmail);
		if (people == null) {
			throw new ResourceNotFoundException("Password reset for Email: " + personEmail + " not found.");
		}
		if (people.get(0).getCommitment() == null) {
			throw new InvalidDataAccessResourceUsageException(personEmail + " is not an appointee.");
		}
		PasswordResetToken passwordResetToken = new PasswordResetToken(people.get(0).getId());
		String hashedToken;
		String unHashedToken = passwordResetToken.getToken();

		try {
			// Create SHA-256 MessageDigest instance
			MessageDigest digest = MessageDigest.getInstance("SHA-256");

			// Hash the token bytes
			byte[] hash = digest.digest(passwordResetToken.getToken().getBytes(java.nio.charset.StandardCharsets.UTF_8));

			// Encode to Base64 for a clean string representation
			hashedToken =  Base64.getEncoder().encodeToString(hash);
		} catch (NoSuchAlgorithmException e) {
				throw new RuntimeException("Hash algorithm not found", e);
    }

		passwordResetToken.setToken(hashedToken);
		saveToken(passwordResetToken);
		emailService.emailPasswordReset(personEmail, unHashedToken);
	}
}
