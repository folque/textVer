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
@Table(name="groups_authorities")
public class Groups_authorities {
	protected long id;
	protected String authority;
	protected Groups group_id;
	
	public Groups_authorities() {
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	public long getId() {
		return id;
	}

	@Column(name="authority")
	public String getAuthority() {
		return authority;
	}

	@ManyToOne
	@JoinColumn(name="group_id", referencedColumnName="id")
	public Groups getGroup_id() {
		return group_id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public void setGroup_id(Groups group_id) {
		this.group_id = group_id;
	}
	
	

}
