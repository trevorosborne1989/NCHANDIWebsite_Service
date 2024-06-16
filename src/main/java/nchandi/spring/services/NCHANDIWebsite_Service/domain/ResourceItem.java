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

import lombok.Data;

// Add this to the @Table annotation: , schema = "NCHANDI_WEBSITE"
@Entity
@Audited
@Table(name = "RESOURCE_ITEM")
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ResourceItem {
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@Column(name = "ID", unique = true, nullable = false)
	@Length(max = 36)
	private String id;

	@Column(name = "NAME")
	@Length(max = 255)
	private String name;

	@Column(name = "BODY")
	@Length(max = 255)
	private String body;

	@Column(name = "TYPE")
	@Length(max = 25)
	private String type;

	@Column(name = "MONTH_OF_YEAR")
	@Length(max = 25)
	private String monthOfYear;

	@Column(name = "CREATED_DATE")
	@Length(max = 255)
	private String createdDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMonthOfYear() {
		return monthOfYear;
	}

	public void setMonthOfYear(String monthOfYear) {
		this.monthOfYear = monthOfYear;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

}
