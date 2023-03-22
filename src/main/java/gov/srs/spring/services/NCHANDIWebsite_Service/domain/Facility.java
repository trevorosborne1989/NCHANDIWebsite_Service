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
