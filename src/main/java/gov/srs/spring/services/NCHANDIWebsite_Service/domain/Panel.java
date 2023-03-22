package gov.srs.spring.services.NCHANDIWebsite_Service.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
	private Integer gender;
	
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
}
