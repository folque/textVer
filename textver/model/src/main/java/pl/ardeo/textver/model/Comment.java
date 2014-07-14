package pl.ardeo.textver.model;

import java.sql.Timestamp;

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
@Table(name="comments")
public class Comment {
	protected long id;
	protected String comment;
	protected Timestamp created;
	protected Version version_id;
	protected Users user_id;
	
	public Comment() {
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	public long getId() {
		return id;
	}

	@Lob
	@Column(name="comment", columnDefinition="TEXT")
	public String getComment() {
		return comment;
	}

	@Column(name="created")
	public Timestamp getCreated() {
		return created;
	}

	@ManyToOne
	@JoinColumn(name="version_id", referencedColumnName="id")
	public Version getVersion_id() {
		return version_id;
	}

	@ManyToOne
	@JoinColumn(name="user_id", referencedColumnName="id")
	public Users getUser_id() {
		return user_id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public void setVersion_id(Version version_id) {
		this.version_id = version_id;
	}

	public void setUser_id(Users user_id) {
		this.user_id = user_id;
	}
	
	

}
