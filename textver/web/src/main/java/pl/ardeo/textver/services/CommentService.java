package pl.ardeo.textver.services;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.ardeo.textver.form.CommentForm;
import pl.ardeo.textver.model.Comment;
import pl.ardeo.textver.model.Document;
import pl.ardeo.textver.services.dao.CommentDAO;
import pl.ardeo.textver.services.dao.DocumentDAO;
import pl.ardeo.textver.services.dao.UserDAO;

@Service
public class CommentService {

	@Autowired
	CommentDAO commentDAO;

	@Autowired
	DocumentDAO documentDAO;

	@Autowired
	UserDAO userDAO;

	@Autowired
	CurrentUser currentUser;


	public void insert(CommentForm commentForm, Document document, String version_name){
		Comment comment = new Comment();
		List<Comment> allComments = commentDAO.findAll();
		if(!allComments.isEmpty()){
			comment.setId(commentDAO.findLast().getId() + 1);
		}
		else {
			comment.setId(1);
		}
		comment.setComment(commentForm.getComment());
		comment.setUser_id(userDAO.findByUsername(currentUser.getUser()));
		comment.setCreated(new Timestamp(System.currentTimeMillis()));
		comment.setVersion_id(documentDAO.findVersionByName(document, version_name));
		commentDAO.insertOrUpdate(comment);
	}
	
	public List<Comment> getCommentsForVersion(Document document, String version_name){
		List<Comment> comments = commentDAO.findForVersionName(document, version_name);
		return comments;
	}
}
