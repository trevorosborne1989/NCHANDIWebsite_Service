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
@Table(name = "PENDING")
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Pending {
	@Id
	@GeneratedValue(generator ="system-uuid")
	@GenericGenerator(name ="system-uuid", strategy ="uuid2")
	@Column(name ="ID", unique = true, nullable = false)
	@Length(max = 36)
	private String id;

	@Column(name = "FIRST_NAME")
	@Length(max = 255)
	private String firstName;

	@Column(name = "LAST_NAME")
	@Length(max = 255)
	private String lastName;

	@Column(name = "EMAIL")
	@Length(max = 255)
	private String email;

	@Column(name = "PHONE")
	@Length(max = 255)
	private String phone;

	@Column(name = "PREFERRED_CONTACT_METHOD")
	@Length(max = 16)
	private String preferredContactMethod;

	@Column(name = "FACILITY_NAME")
	@Length(max = 255)
	private String facilityName;

	@Column(name = "DAY_OF_WEEK")
	@Length(max = 255)
	private String dayOfWeek;

	@Column(name = "WEEK_OF_MONTH")
	private Integer weekOfMonth;

	@Column(name = "EVENT_TIME")
	@Length(max = 255)
	private String eventTime;

	@Column(name = "NUMBER_NEEDED")
	private Integer numberNeeded;

	@Column(name = "GENDER")
	private String gender;

	@Column(name ="PANEL_ID")
	@Length(max = 36)
	private String panelId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
		this.preferredContactMethod = preferredContactMethod;
	}

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public Integer getWeekOfMonth() {
		return weekOfMonth;
	}

	public void setWeekOfMonth(Integer weekOfMonth) {
		this.weekOfMonth = weekOfMonth;
	}

	public String getEventTime() {
		return eventTime;
	}

	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}

	public String facilityName() {
		return facilityName;
	}

	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getNumberNeeded() {
		return numberNeeded;
	}

	public void setNumberNeeded(Integer numberNeeded) {
		this.numberNeeded = numberNeeded;
	}

	public String getPanelId() {
		return panelId;
	}

	public void setPanelId(String panelId) {
		this.panelId = panelId;
	}
}
