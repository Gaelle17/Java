package fr.imie.recipemanager.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import fr.imie.recipemanager.dao.RecipeDao;
import fr.imie.recipemanager.entity.Recipe;

public class JpaRecipeDao implements RecipeDao {
	
	private EntityManagerFactory emf;
	
	public JpaRecipeDao(EntityManagerFactory emf) {
		this.emf = emf;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Recipe> getSharedRecipes() {
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("SELECT r FROM Recipe AS r WHERE r.shared = :bool");
		query.setParameter("bool", true);
		List<Recipe> recipes = query.getResultList();
		em.close();
		return recipes;
	}

	@Override
	public void addRecipe(Recipe r) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();
		try {
			t.begin();
			em.persist(r);
			t.commit();
		} finally {
			if (t.isActive()) {
				t.rollback();
			}
			em.close();
		}
	}

	@Override
	public void updateRecipe(Recipe r) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeRecipe(Recipe r) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();
		try {
			t.begin();
			if (!em.contains(r)) {
				r = em.merge(r);
			}
			em.remove(r);
			t.commit();
		} finally {
			if (t.isActive()) {
				t.rollback();
			}
			em.close();
		}
	}

}
