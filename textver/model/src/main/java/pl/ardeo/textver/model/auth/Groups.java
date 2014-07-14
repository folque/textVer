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

@Entity
@Table(name="groups")
public class Groups {
	protected long id;
	protected String name;
	protected Set<Users_groups> user_groups = new HashSet<Users_groups>();
	protected Set<Groups_authorities> group_authorities = new HashSet<Groups_authorities>();
	
	public Groups() {
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	public long getId() {
		return id;
	}

	@Column(name="name")
	public String getName() {
		return name;
	}

	@OneToMany(mappedBy="group_id", targetEntity=Users_groups.class, fetch=FetchType.LAZY)
	public Set<Users_groups> getUser_groups() {
		return user_groups;
	}

	@OneToMany(mappedBy="group_id", targetEntity=Groups_authorities.class, fetch=FetchType.LAZY)
	public Set<Groups_authorities> getGroup_authorities() {
		return group_authorities;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUser_groups(Set<Users_groups> user_groups) {
		this.user_groups = user_groups;
	}

	public void setGroup_authorities(Set<Groups_authorities> group_authorities) {
		this.group_authorities = group_authorities;
	}
	
	

}
