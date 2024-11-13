package nchandi.spring.services.NCHANDIWebsite_Service.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

// Add this to the @Table annotation: , schema = "NCHANDI_WEBSITE"
@Entity
@Audited
@Table(name = "PANEL")
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Panel implements Cloneable {
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@Column(name = "ID", unique = true, nullable = false)
	@Length(max = 36)
	private String id;

	@Column(name = "DAY_OF_WEEK")
	@Length(max = 255)
	private String dayOfWeek;

	@Column(name = "WEEK_OF_MONTH")
	private Integer weekOfMonth;

	@Column(name = "EVENT_TIME")
	@Length(max = 255)
	private String eventTime;

	@Column(name = "GENDER")
	private String gender;

	@Column(name = "MARK_AS_MEMBERS_NEEDED")
	private Boolean markAsMembersNeeded;

	@Column(name = "NUMBER_NEEDED")
	private Integer numberNeeded;

	@Column(name = "ACTIVE")
	private Boolean active;

	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "FACILITY_ID", referencedColumnName = "ID")
	private Facility facility;

	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "BOARD_CHAMPION_ID", referencedColumnName = "ID")
	private People boardChampion;

	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PANEL_COORDINATOR_ID", referencedColumnName = "ID")
	private People panelCoordinator;

	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PANEL_LEADER_ID", referencedColumnName = "ID")
	private People panelLeader;

	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PANEL_MEMBER_1_ID", referencedColumnName = "ID")
	private People panelMember1;

	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PANEL_MEMBER_2_ID", referencedColumnName = "ID")
	private People panelMember2;

	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PANEL_MEMBER_3_ID", referencedColumnName = "ID")
	private People panelMember3;

	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PANEL_MEMBER_4_ID", referencedColumnName = "ID")
	private People panelMember4;

	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PANEL_MEMBER_5_ID", referencedColumnName = "ID")
	private People panelMember5;

	@Override
	public Object clone()throws CloneNotSupportedException{
		return super.clone();
		}

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

	public People getBoardChampion() {
		return boardChampion;
	}

	public void setBoardChampion(People boardChampion) {
		this.boardChampion = boardChampion;
	}

	public People getPanelCoordinator() {
		return panelCoordinator;
	}

	public void setPanelCoordinator(People panelCoordinator) {
		this.panelCoordinator = panelCoordinator;
	}

	public People getPanelLeader() {
		return panelLeader;
	}

	public void setPanelLeader(People panelLeader) {
		this.panelLeader = panelLeader;
	}

	public People getPanelMember1() {
		return panelMember1;
	}

	public void setPanelMember1(People panelMember1) {
		this.panelMember1 = panelMember1;
	}

	public People getPanelMember2() {
		return panelMember2;
	}

	public void setPanelMember2(People panelMember2) {
		this.panelMember2 = panelMember2;
	}

	public People getPanelMember3() {
		return panelMember3;
	}

	public void setPanelMember3(People panelMember3) {
		this.panelMember3 = panelMember3;
	}

	public People getPanelMember4() {
		return panelMember4;
	}

	public void setPanelMember4(People panelMember4) {
		this.panelMember4 = panelMember4;
	}

	public People getPanelMember5() {
		return panelMember5;
	}

	public void setPanelMember5(People panelMember5) {
		this.panelMember5 = panelMember5;
	}
}
