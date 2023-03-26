package gov.srs.spring.services.NCHANDIWebsite_Service.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity 
@Audited
@Table(name = "MONTHLY_REPORT", schema = "NCHANDI_WEBSITE") 
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class MonthlyReport {
	@Id
	@GeneratedValue(generator ="system-uuid")
	@GenericGenerator(name ="system-uuid", strategy ="uuid2")
	@Column(name ="ID", unique = true, nullable = false) 
	@Length(max = 36)
	private String id;
	
	@Column(name = "IS_ARCHIVE") 
	private Boolean isArchive;
	
	@Column(name = "MONTH_OF_YEAR") 
	private Integer monthOfYear;
	
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	@Column(name = "TIMESTAMP")
	@CreationTimestamp
	private Date timestamp;
	
	@Column(name = "TITLE") 
	@Length(max = 255) 
	private String title;
	
	@Column(name = "TYPE_OF_REPORT") 
	@Length(max = 255) 
	private String typeOfReport;
	
	@Column(name = "URL") 
	@Length(max = 255) 
	private String url;
}
