package nchandi.spring.services.NCHANDIWebsite_Service.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.UUID;
import java.util.Date;

import lombok.Data;

// Add this to the @Table annotation: , schema = "NCHANDI_WEBSITE"
@Entity
@Audited
@Table(name = "PasswordResetToken")
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class PasswordResetToken {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@Column(name = "ID", unique = true, nullable = false)
	@Length(max = 36)
	private String id;

	@Column(name = "PERSON_ID")
	@Length(max = 255)
	private String personId;

	@Column(name = "TOKEN")
	@Length(max = 255)
	private String token;

	@Column(name = "EXPIRATION")
	private Date expiration;

	/*
	* CTOR
	*/
	public PasswordResetToken() {

  }

	/**
	 * CTOR with aPersonId
	 * @param aPersonsId
	 */
	public PasswordResetToken(String aPersonsId) {
		String token = UUID.randomUUID().toString();
		// Define the expiration time (e.g., 30 minutes from now)
		long expirationTimeMillis = System.currentTimeMillis() + (30 * 60 * 1000); // 30 minutes in milliseconds
		Date expirationDate = new Date(expirationTimeMillis);
		setToken(token);
		setExpiration(expirationDate);
		setPersonId(aPersonsId);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getExpiration() {
		return expiration;
	}

	public void setExpiraton(Date expiration) {
		this.expiration = expiration;
	}
}
