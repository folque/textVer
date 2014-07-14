package pl.ardeo.textver.form;

import java.sql.Timestamp;

public class CommentForm {
	private long id;
	private String comment, username;
	private Timestamp created;
	
	public CommentForm() {
	}

	public long getId() {
		return id;
	}

	public String getComment() {
		return comment;
	}

	public String getUsername() {
		return username;
	}

	public Timestamp getCreated() {
		return created;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

}
