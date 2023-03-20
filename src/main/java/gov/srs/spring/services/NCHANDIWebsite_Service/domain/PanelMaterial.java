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
@Table(name = "PANEL_MATERIAL", schema = "NCHANDI_WEBSITE") 
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class PanelMaterial {
	@Id
	@GeneratedValue(generator ="system-uuid")
	@GenericGenerator(name ="system-uuid", strategy ="uuid2")
	@Column(name ="ID", unique = true, nullable = false) 
	@Length(max = 36)
	private String id;
	
	@Column(name = "ORDER") 
	private Integer order;
	
	@Column(name = "TITLE") 
	@Length(max = 255) 
	private String title;
	
	@Column(name = "URL") 
	@Length(max = 255) 
	private String url;
}
