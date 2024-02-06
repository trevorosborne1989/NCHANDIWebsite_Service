package nchandi.spring.services.NCHANDIWebsite_Service.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity 
@Audited
@Table(name = "PANEL", schema = "NCHANDI_WEBSITE") 
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Panel {
	@Id
	@GeneratedValue(generator ="system-uuid")
	@GenericGenerator(name ="system-uuid", strategy ="uuid2")
	@Column(name ="ID", unique = true, nullable = false) 
	@Length(max = 36)
	private String id;
	
	@Column(name = "ACTIVE") 
	private Boolean active;
	
	@Column(name = "DAY_OF_WEEK") 
	@Length(max = 255) 
	private String dayOfWeek;
	
	@Column(name = "EVENT_TIME") 
	@Length(max = 255) 
	private String eventTime;
	
	@Column(name = "GENDER") 
	private String gender;
	
	@Column(name = "LOCATION") 
	@Length(max = 255) 
	private String location;
	
	@Column(name = "MARK_AS_MEMBERS_NEEDED") 
	private Boolean markAsMembersNeeded;
	
	@Column(name = "NUMBER_NEEDED") 
	private Integer numberNeeded;
	
	@Column(name = "REMINDER") 
	private Boolean reminder;
	
	@Column(name = "WEEK_OF_MONTH") 
	private Integer weekOfMonth;
	
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade({ CascadeType.ALL })
	@JoinColumn(name = "BOARD_CHAMPION_ID", referencedColumnName = "ID", insertable = false, updatable = false)
	private Member boardChampion;
	
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade({ CascadeType.ALL })
	@JoinColumn(name = "FACILITY_ID", referencedColumnName = "ID", insertable = false, updatable = false)
	private Facility facility;

	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade({ CascadeType.ALL })
	@JoinColumn(name = "PANEL_COORDINATOR_ID", referencedColumnName = "ID", insertable = false, updatable = false)
	private Member panelCoordinator;
	
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade({ CascadeType.ALL })
	@JoinColumn(name = "PANEL_LEADER_ID", referencedColumnName = "ID", insertable = false, updatable = false)
	private Member panelLeader;
	
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade({ CascadeType.ALL })
	@JoinColumn(name = "PANEL_MEMBER_1_ID", referencedColumnName = "ID", insertable = false, updatable = false)
	private Member panelMember1;
	
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade({ CascadeType.ALL })
	@JoinColumn(name = "PANEL_MEMBER_2_ID", referencedColumnName = "ID", insertable = false, updatable = false)
	private Member panelMember2;
	
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade({ CascadeType.ALL })
	@JoinColumn(name = "PANEL_MEMBER_3_ID", referencedColumnName = "ID", insertable = false, updatable = false)
	private Member panelMember3;
	
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade({ CascadeType.ALL })
	@JoinColumn(name = "PANEL_MEMBER_4_ID", referencedColumnName = "ID", insertable = false, updatable = false)
	private Member panelMember4;
	
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade({ CascadeType.ALL })
	@JoinColumn(name = "PANEL_MEMBER_5_ID", referencedColumnName = "ID", insertable = false, updatable = false)
	private Member panelMember5;
	
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
	
	public Member getBoardChampion() {
		return boardChampion;
	}

	public void setBoardChampion(Member boardChampion) {
		this.boardChampion = boardChampion;
	}
	
	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	
	public String getEventTime() {
		return eventTime;
	}

	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}
	
	public Facility getFacility() {
		return facility;
	}

	public void setFacility(Facility facility) {
		this.facility = facility;
	}
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	public Boolean getMarkAsMembersNeeded() {
		return markAsMembersNeeded;
	}

	public void setMarkAsMembersNeeded(Boolean markAsMembersNeeded) {
		this.markAsMembersNeeded = markAsMembersNeeded;
	}
	
	public Integer getNumberNeeded() {
		return numberNeeded;
	}

	public void setNumberNeeded(Integer numberNeeded) {
		this.numberNeeded = numberNeeded;
	}
	
	public Member getPanelCoordinator() {
		return panelCoordinator;
	}

	public void setPanelCoordinator(Member panelCoordinator) {
		this.panelCoordinator = panelCoordinator;
	}
	
	public Member getPanelLeader() {
		return panelLeader;
	}

	public void setPanelLeader(Member panelLeader) {
		this.panelLeader = panelLeader;
	}
	
	public Member getPanelMember1() {
		return panelMember1;
	}

	public void setPanelMember1(Member panelMember1) {
		this.panelMember1 = panelMember1;
	}
	
	public Member getPanelMember2() {
		return panelMember2;
	}

	public void setPanelMember2(Member panelMember2) {
		this.panelMember2 = panelMember2;
	}
	
	public Member getPanelMember3() {
		return panelMember3;
	}

	public void setPanelMember3(Member panelMember3) {
		this.panelMember3 = panelMember3;
	}
	
	public Member getPanelMember4() {
		return panelMember4;
	}

	public void setPanelMember4(Member panelMember4) {
		this.panelMember4 = panelMember4;
	}
	
	public Member getPanelMember5() {
		return panelMember5;
	}

	public void setPanelMember5(Member panelMember5) {
		this.panelMember5 = panelMember5;
	}
	
	public Boolean getReminder() {
		return reminder;
	}

	public void setId(Boolean reminder) {
		this.reminder = reminder;
	}
	
	public Integer getWeekOfMonth() {
		return weekOfMonth;
	}

	public void setId(Integer weekOfMonth) {
		this.weekOfMonth = weekOfMonth;
	}
}
