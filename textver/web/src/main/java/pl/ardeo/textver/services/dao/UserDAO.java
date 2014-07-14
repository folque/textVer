package pl.ardeo.textver.services.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.ardeo.textver.model.auth.Users;

@Service
public class UserDAO {

	EntityManager entityManager;
	
	@PersistenceContext
	public void setEntityManager(EntityManager entityManager){
		this.entityManager = entityManager;
	}
	
	@Transactional
	public List<Users> findAll() {
		List<Users> users = entityManager.createQuery("select u from Users as u").getResultList();
		return users;
	}
	
	@Transactional
	public Users findById(long id){
		Users user = entityManager.find(Users.class, id);
		return user;
	}
	
	@Transactional
	public Users findByUsername(String username) {
		List<Users> users = entityManager.createQuery("select u from Users as u where u.username = '" + username + "'").getResultList();
		if (!users.isEmpty()) {
			return users.get(0);
		} else
			return null;
	}
}
