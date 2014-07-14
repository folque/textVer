package pl.ardeo.textver.services.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.ardeo.textver.model.Version;

@Service
public class VersionDAO {
	
	EntityManager entityManager;
	
	@PersistenceContext
	public void setEntityManager(EntityManager entityManager){
		this.entityManager = entityManager;
	}
	
	@Transactional
	public List<Version> findAllforDocument(Long id){
		List<Version> versions = entityManager.createQuery("select v from Version v where v.document_id.id = " + id).getResultList();
		return versions;
	}
	
	@Transactional
	public Version findLastModifiedforDocument(Long id){
		List<Version> versions = entityManager.createQuery("select v from Version v where v.document_id.id = " + id + " order by v.modified desc").getResultList();
		if(!versions.isEmpty()){
			return versions.get(0);
		}
		return null;
	}
	
	@Transactional
	public Version findLast(){
		List<Version> versions = entityManager.createQuery("select v from Version as v order by v.id desc").getResultList();
		if(!versions.isEmpty()){
			return versions.get(0);
		}
		else
			return null;
	}
	
	@Transactional
	public void insertOrUpdate(Version version){
		entityManager.merge(version);
	}

}
