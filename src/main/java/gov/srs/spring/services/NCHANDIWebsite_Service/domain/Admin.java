package gov.srs.spring.services.NCHANDIWebsite_Service.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity 
@Audited
@Table(name = "ADMIN", schema = "NCHANDI_WEBSITE") 
@JsonIgnoreProperties(ignoreUnknown = true)
public class Admin {
	@Id
	@GeneratedValue(generator ="system-uuid")
	@GenericGenerator(name ="system-uuid", strategy ="uuid2")
	@Column(name ="ID", unique = true, nullable = false) 
	@Length(max = 36)
	private String id;
	
	@Column(name = "COMMITMENT") 
	@Length(max = 255) 
	private String commitment;
	
	@Column(name = "EMAIL") 
	@Length(max = 255) 
	private String email;
	
	@Column(name = "FIRST_NAME") 
	@Length(max = 255) 
	private String firstName;
	
	@Column(name = "LAST_NAME") 
	@Length(max = 255) 
	private String lastName;
	
	@Column(name = "PHONE") 
	@Length(max = 255) 
	private String phone;
	
	@Column(name = "PREFFERED_CONTACT_METHOD") 
	@Length(max = 255) 
	private String preferredContactMethod;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getCommitment() {
		return commitment;
	}

	public void setCommitment(String commitment) {
		this.commitment = commitment;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getPreferredContactMethod() {
		return preferredContactMethod;
	}

	public void setPreferredContactMethod(String preferredContactMethod) {
		this.id = preferredContactMethod;
	}
}
