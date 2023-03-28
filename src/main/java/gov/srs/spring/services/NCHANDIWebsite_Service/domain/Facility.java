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

import lombok.Data;

@Entity 
@Audited
@Table(name = "FACILITY", schema = "NCHANDI_WEBSITE") 
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Facility {
	@Id
	@GeneratedValue(generator ="system-uuid")
	@GenericGenerator(name ="system-uuid", strategy ="uuid2")
	@Column(name ="ID", unique = true, nullable = false) 
	@Length(max = 36)
	private String id;
	
	@Column(name = "ACTIVE") 
	private Boolean active;
	
	@Column(name = "ADDRESS") 
	@Length(max = 255) 
	private String address;
	
	@Column(name = "ALERNATE_CONTACT_EMAIL") 
	@Length(max = 255) 
	private String alternateContactEmail;
	
	@Column(name = "ALTERNATE_CONTACT_NAME") 
	@Length(max = 255) 
	private String alternateContactName;
	
	@Column(name = "ALTERNATE_CONTACT_PHONE") 
	@Length(max = 255) 
	private String alternateContactPhone;
	
	@Column(name = "CITY") 
	@Length(max = 255) 
	private String city;
	
	@Column(name = "FACILITY_NAME") 
	@Length(max = 255) 
	private String facilityName;
	
	@Column(name = "FACILITY_TYPE") 
	@Length(max = 255) 
	private String facilityType;
	
	@Column(name = "MAIN_CONTACT_EMAIL") 
	@Length(max = 255) 
	private String mainContactEmail;
	
	@Column(name = "MAIN_CONTACT_NAME") 
	@Length(max = 255) 
	private String mainContactName;
	
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

	public String getFacilityName() {
		return facilityName;
	}

	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}

	public String getFacilityType() {
		return facilityType;
	}

	public void setFacilityType(String facilityType) {
		this.facilityType = facilityType;
	}

	public String getMainContactEmail() {
		return mainContactEmail;
	}

	public void setMainContactEmail(String mainContactEmail) {
		this.mainContactEmail = mainContactEmail;
	}

	public String getMainContactName() {
		return mainContactName;
	}

	public void setMainContactName(String mainContactName) {
		this.mainContactName = mainContactName;
	}

	public String getMainContactPhone() {
		return mainContactPhone;
	}

	public void setMainContactPhone(String mainContactPhone) {
		this.mainContactPhone = mainContactPhone;
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

	@Column(name = "MAIN_CONTACT_PHONE") 
	@Length(max = 255) 
	private String mainContactPhone;
	
	@Column(name = "STATE") 
	@Length(max = 255) 
	private String state;
	
	@Column(name = "WEBSITE") 
	@Length(max = 255) 
	private String website;
	
	@Column(name = "ZIP") 
	@Length(max = 255) 
	private String zip;
	
	
}
