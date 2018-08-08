package fr.imie.recipemanager.dao;

import fr.imie.recipemanager.dao.jpa.JpaIngredientDao;
import fr.imie.recipemanager.dao.jpa.JpaRecipeDao;
import fr.imie.recipemanager.dao.jpa.JpaUserDao;
import fr.imie.recipemanager.util.PersistenceManager;

public class DaoFactory {

	private DaoFactory() {}
	
	public static RecipeDao getRecipeDao() {
		return new JpaRecipeDao(PersistenceManager.getEntityManagerFactory());
	}
	
	public static IngredientDao getIngredientDao() {
		return new JpaIngredientDao(PersistenceManager.getEntityManagerFactory());
	}
	
	public static UserDao getUserDao() {
		return new JpaUserDao(PersistenceManager.getEntityManagerFactory());
	}
	
}
