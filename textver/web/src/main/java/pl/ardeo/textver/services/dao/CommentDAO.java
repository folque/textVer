package pl.ardeo.textver.services.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.ardeo.textver.model.Comment;
import pl.ardeo.textver.model.Document;
import pl.ardeo.textver.model.Version;

@Service
public class CommentDAO {
	
	EntityManager entityManager;
	
	@Autowired
	DocumentDAO documentDAO;
	
	@PersistenceContext
	public void setEntityManager(EntityManager entityManager){
		this.entityManager = entityManager;
	}
	
	@Transactional
	public List<Comment> findAll(){
		List<Comment> comments = entityManager.createQuery("select c from Comment c").getResultList();
		return comments;
	}
	
	@Transactional
	public Comment findById(long id){
		Comment comment = entityManager.find(Comment.class, id);
		return comment;
	}
	
	@Transactional
	public List<Comment> findForVersionName(Document document, String version_name){
		Version version = documentDAO.findVersionByName(document, version_name);
		List<Comment> comments = entityManager.createQuery("select c from Comment c where c.version_id.id = " + version.getId()).getResultList();
		return comments;
	}

	@Transactional
	public Comment findLast(){
		List<Comment> comments = entityManager.createQuery("select c from Comment c order by id desc").getResultList();
		if(!comments.isEmpty())
			return comments.get(0);
		else return null;
	}
	
	@Transactional
	public void insertOrUpdate(Comment comment){
		entityManager.merge(comment);
	}
}
