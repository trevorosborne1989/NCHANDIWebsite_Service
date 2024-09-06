package nchandi.spring.services.NCHANDIWebsite_Service.domain;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ATTACHMENT")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Attachment {
  @Id
  private String id;

	@Column(name = "FILE_NAME")
	private String fileName;

	@Column(name = "CONTENT_TYPE")
	private String contentType;

	@Column(name = "CONTENT")
	@Lob
  private byte[] content;

	@NotFound(action = NotFoundAction.IGNORE)
  @OneToOne
  @MapsId
	@JoinColumn(name = "PARENT_RESOURCE_ID", referencedColumnName = "ID")
  private ResourceItem parentResource;

  public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String filename) {
		this.fileName = filename;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public ResourceItem getParentResource() {
		return parentResource;
	}

	public void setParentResource(ResourceItem parentResource) {
		this.parentResource = parentResource;
	}
}
