package fr.imie.recipemanager.dao.jpa;

import javax.persistence.EntityManagerFactory;

import fr.imie.recipemanager.dao.IngredientDao;

public class JpaIngredientDao implements IngredientDao {

	private EntityManagerFactory emf;
	
	public JpaIngredientDao(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
}
