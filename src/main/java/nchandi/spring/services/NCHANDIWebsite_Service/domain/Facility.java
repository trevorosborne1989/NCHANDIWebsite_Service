package nchandi.spring.services.NCHANDIWebsite_Service.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

// Add this to the @Table annotation: , schema = "NCHANDI_WEBSITE"
@Entity
@Audited
@Table(name = "FACILITY")
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Facility {
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@Column(name = "ID", unique = true, nullable = false)
	@Length(max = 36)
	private String id;

	@Column(name = "NAME")
	@Length(max = 255)
	private String name;

	@Column(name = "TYPE")
	@Length(max = 255)
	private String type;

	@Column(name = "ADDRESS")
	@Length(max = 255)
	private String address;

	@Column(name = "CITY")
	@Length(max = 255)
	private String city;

	@Column(name = "ZIP")
	@Length(max = 255)
	private String zip;

	@Column(name = "STATE")
	@Length(max = 255)
	private String state;

	@Column(name = "PRIMARY_CONTACT_NAME")
	@Length(max = 255)
	private String primaryContactName;

	@Column(name = "PRIMARY_CONTACT_PHONE")
	@Size(min=11,max=11)
	private String primaryContactPhone;

	@Column(name = "PRIMARY_CONTACT_EMAIL")
	@Length(max = 255)
	private String primaryContactEmail;

	@Column(name = "ALTERNATE_CONTACT")
	private Boolean alternateContact;

	@Column(name = "ALTERNATE_CONTACT_NAME")
	@Length(max = 255)
	private String alternateContactName;

	@Column(name = "ALTERNATE_CONTACT_PHONE")
	@Size(max=11)
	private String alternateContactPhone;

	@Column(name = "ALTERNATE_CONTACT_EMAIL")
	@Length(max = 255)
	private String alternateContactEmail;

	@Column(name = "WEBSITE")
	@Length(max = 255)
	private String website;

	@Column(name = "ACTIVE")
	private Boolean active;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAlternateContactEmail() {
		return alternateContactEmail;
	}

	public void setAlternateContactEmail(String alternateContactEmail) {
		this.alternateContactEmail = alternateContactEmail;
	}

	public String getAlternateContactName() {
		return alternateContactName;
	}

	public void setAlternateContactName(String alternateContactName) {
		this.alternateContactName = alternateContactName;
	}

	public String getAlternateContactPhone() {
		return alternateContactPhone;
	}

	public void setAlternateContactPhone(String alternateContactPhone) {
		this.alternateContactPhone = alternateContactPhone;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFacilityType() {
		return type;
	}

	public void setFacilityType(String type) {
		this.type = type;
	}

	public String getMainContactEmail() {
		return primaryContactEmail;
	}

	public void setMainContactEmail(String primaryContactEmail) {
		this.primaryContactEmail = primaryContactEmail;
	}

	public String getMainContactName() {
		return primaryContactName;
	}

	public void setMainContactName(String primaryContactName) {
		this.primaryContactName = primaryContactName;
	}

	public String getMainContactPhone() {
		return primaryContactPhone;
	}

	public void setMainContactPhone(String primaryContactPhone) {
		this.primaryContactPhone = primaryContactPhone;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}
}
