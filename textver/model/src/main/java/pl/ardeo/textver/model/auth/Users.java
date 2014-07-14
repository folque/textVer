package pl.ardeo.textver.model.auth;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import pl.ardeo.textver.model.Comment;
import pl.ardeo.textver.model.Version;

@Entity
@Table(name="users")
public class Users {
	protected long id;
	protected String username, password;
	protected boolean enabled;
	protected Set<Comment> comments = new HashSet<Comment>();
	protected Set<Version> versions = new HashSet<Version>();
	protected Set<Users_groups> user_groups = new HashSet<Users_groups>();
	
	public Users() {
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	public long getId() {
		return id;
	}

	@Column(name="username")
	public String getUsername() {
		return username;
	}

	@Column(name="password")
	public String getPassword() {
		return password;
	}

	@Column(name="enabled")
	public boolean isEnabled() {
		return enabled;
	}

	@OneToMany(mappedBy="user_id", targetEntity=Comment.class, fetch=FetchType.LAZY)
	public Set<Comment> getComments() {
		return comments;
	}

	@OneToMany(mappedBy="user_id", targetEntity=Version.class, fetch=FetchType.LAZY)
	public Set<Version> getVersions() {
		return versions;
	}

	@OneToMany(mappedBy="user_id", targetEntity=Users_groups.class, fetch=FetchType.LAZY)
	public Set<Users_groups> getUser_groups() {
		return user_groups;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public void setVersions(Set<Version> versions) {
		this.versions = versions;
	}

	public void setUser_groups(Set<Users_groups> user_groups) {
		this.user_groups = user_groups;
	}
	
	
}
