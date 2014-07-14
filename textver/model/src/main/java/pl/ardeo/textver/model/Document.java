package pl.ardeo.textver.model;

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
@Table(name="documents")
public class Document {
	protected long id;
	protected String name;
	protected Set<Version> versions = new HashSet<Version>();
	
	public Document() {
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
	
	@OneToMany(mappedBy="document_id", fetch=FetchType.LAZY)
	public Set<Version> getVersions() {
		return versions;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setVersions(Set<Version> versions) {
		this.versions = versions;
	}
	
	@Override
	public String toString(){
		return "id = " + getId() + " name = " + getName();
	}
}
