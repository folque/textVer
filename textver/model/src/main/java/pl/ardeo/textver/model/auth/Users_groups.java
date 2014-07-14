package pl.ardeo.textver.model.auth;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="users_groups")
public class Users_groups {
	protected long id;
	protected Users user_id;
	protected Groups group_id;
	
	public Users_groups() {
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
	@JoinColumn(name="group_id", referencedColumnName="id")
	public Groups getGroup_id() {
		return group_id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setUser_id(Users user_id) {
		this.user_id = user_id;
	}

	public void setGroup_id(Groups group_id) {
		this.group_id = group_id;
	}
	
	

}
