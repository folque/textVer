package pl.ardeo.textver.services.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.ardeo.textver.model.Document;
import pl.ardeo.textver.model.Version;

@Service
public class DocumentDAO {

	EntityManager entityManager;

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager){
		this.entityManager = entityManager;
	}

	@Autowired
	private VersionDAO versionDAO;

	@Transactional
	public List<Document> findAll(){
		List<Document> documents = entityManager.createQuery("select d from Document as d").getResultList();
		return documents;
	}

	@Transactional
	public Document findById(long id){
		Document document = entityManager.find(Document.class, id);
		return document;
	}

	@Transactional
	public List<Version> versionsOfDocument(Integer id){
		List<Version> versions = entityManager.createQuery("select v from Version as v where v.document_id.id=" + id).getResultList();
		return versions;
	}
	
	@Transactional
	public Document findLast(){
		List<Document> documents = entityManager.createQuery("select d from Document as d order by d.id desc").getResultList();
		if(!documents.isEmpty()){
			return documents.get(0);
		}
		else 
			return null;
	}
	
	@Transactional
	public Version findVersionByName(Document document, String version_name){
		System.out.println(document.getName() + " + " + version_name);
		List<Version> versions = 
				entityManager.createQuery("select v from Version v where v.document_id.id = " + document.getId() + " and v.version_name = '" + version_name + "'").getResultList();
		if(!versions.isEmpty()){
			return versions.get(0);
		}
		else return null;
	}
	

	@Transactional
	public void insertOrUpdate(Document document){
		entityManager.merge(document);
	}	

}
