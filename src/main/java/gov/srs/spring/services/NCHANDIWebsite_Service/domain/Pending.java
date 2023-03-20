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
@Table(name = "PENDING", schema = "NCHANDI_WEBSITE") 
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Pending {
	@Id
	@GeneratedValue(generator ="system-uuid")
	@GenericGenerator(name ="system-uuid", strategy ="uuid2")
	@Column(name ="ID", unique = true, nullable = false) 
	@Length(max = 36)
	private String id;
	
	@Column(name = "DAY_OF_WEEK") 
	@Length(max = 16) 
	private String dayOfWeek;
	
	@Column(name = "EMAIL") 
	@Length(max = 255) 
	private String email;
	
	@Column(name = "EVENT_TIME") 
	@Length(max = 16) 
	private String eventTime;
	
	@Column(name = "FACILITY_NAME") 
	@Length(max = 255) 
	private String facilityName;
	
	@Column(name = "FIRST_NAME") 
	@Length(max = 16) 
	private String firstName;
	
	@Column(name = "LAST_NAME") 
	@Length(max = 16) 
	private String lastName;
	
	@Column(name = "PANEL_ID") 
	@Length(max = 16) 
	private String panelId;
	
	@Column(name = "PHONE") 
	@Length(max = 16) 
	private String phone;
	
	@Column(name = "PREFERRED_CONTACT_METHOD") 
	@Length(max = 16) 
	private String preferredContactMethod;
	
	@Column(name = "WEEK_OF_MONTH") 
	@Length(max = 16) 
	private String weekOfMonth;
}
