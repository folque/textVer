package pl.ardeo.textver.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import pl.ardeo.textver.model.auth.Users;


@Entity
@Table(name="versions")
public class Version {
	protected long id;
	protected String version_name, title, content, description;
	protected Timestamp modified;
	protected Users user_id;
	protected Document document_id;
	protected Set<Comment> comments = new HashSet<Comment>();
	
	public Version() {
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	public long getId() {
		return id;
	}

	@ManyToOne
	@JoinColumn(name="user_id", referencedColumnName="id")
	public Users getUser_id() {
		return user_id;
	}

	@ManyToOne
	@JoinColumn(name="document_id", referencedColumnName="id")
	public Document getDocument_id() {
		return document_id;
	}

	@Column(name="version_name")
	public String getVersion_name() {
		return version_name;
	}

	@Column(name="title")
	public String getTitle() {
		return title;
	}

	@Lob
	@Column(name="content", columnDefinition="LONGTEXT")
	public String getContent() {
		return content;
	}

	@Lob
	@Column(name="description", columnDefinition="TEXT")
	public String getDescription() {
		return description;
	}

	@Column(name="modified")
	public Timestamp getModified() {
		return modified;
	}

	
	public void setId(long id) {
		this.id = id;
	}

	public void setUser_id(Users user_id) {
		this.user_id = user_id;
	}

	public void setDocument_id(Document document_id) {
		this.document_id = document_id;
	}

	public void setVersion_name(String version_name) {
		this.version_name = version_name;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setModified(Timestamp modified) {
		this.modified = modified;
	}
	
	@Override
	public String toString(){
		return "id = " + getId() + " version_name = " + getVersion_name() + " title = " + getTitle() + " content = " + getContent() + " description = " + getDescription()
				+ " modified = " + getModified();
	}
	

}
