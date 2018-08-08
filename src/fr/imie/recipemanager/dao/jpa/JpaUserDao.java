package fr.imie.recipemanager.dao.jpa;

import javax.persistence.EntityManagerFactory;

import fr.imie.recipemanager.dao.UserDao;

public class JpaUserDao implements UserDao{
	
	private EntityManagerFactory emf;
	
	public JpaUserDao(EntityManagerFactory emf) {
		this.emf = emf;
	}

}
