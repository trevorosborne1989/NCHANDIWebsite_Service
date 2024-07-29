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

// Add this to the @Table annotation: , schema = "NCHANDI_WEBSITE"
@Entity
@Audited
@Table(name = "PEOPLE")
@JsonIgnoreProperties(ignoreUnknown = true)
public class People {
  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid2")
  @Column(name = "ID", unique = true, nullable = false)
  @Length(max = 36)
  private String id;

  @Column(name = "FIRST_NAME")
  @Length(max = 255)
  private String firstName;

  @Column(name = "LAST_NAME")
  @Length(max = 255)
  private String lastName;

  @Column(name = "PHONE")
  @Length(max = 255)
  private String phone;

  @Column(name = "EMAIL")
  @Length(max = 255)
  private String email;

  @Column(name = "PREFERRED_CONTACT_METHOD")
  @Length(max = 255)
  private String preferredContactMethod;

  @Column(name = "COMMITMENT")
  @Length(max = 255)
  private String commitment;

  @Column(name = "USERNAME")
  @Length(max = 255)
  private String username;

  @Column(name = "PASSWORD")
  @Length(max = 255)
  private String password;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
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

  public String getCommitment() {
    return commitment;
  }

  public void setCommitment(String commitment) {
    this.commitment = commitment;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
