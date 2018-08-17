package fr.imie.recipemanager.servlet;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.imie.recipemanager.entity.Ingredient;
import fr.imie.recipemanager.entity.Recipe;
import fr.imie.recipemanager.entity.User;

@WebServlet("/addIngredient")
public class AddIngredientServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private EntityManagerFactory emf;
	

	
	
	@Override
	public void init() throws ServletException {
		this.emf = Persistence.createEntityManagerFactory("RMPU"); // Get the Recipe Manager Persistence Unit
		super.init();
	}
	
	
	

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Disable Cache For HTTP 1.1 & 1.0
		response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		
		request.getRequestDispatcher("/addIngredient.jsp").forward(request, response);
	}
	
	

	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Disable Cache For HTTP 1.1 & 1.0
		response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		
		//prepare the entity
		Ingredient ingredient = new Ingredient();
		ingredient.setName(request.getParameter("name"));
		ingredient.setPrice(Float.parseFloat(request.getParameter("price")));
		
		//create the entity manager & transaction
		EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();

		User currentUser = em.find(User.class,request.getSession().getAttribute("userId"));
		ingredient.setOwner(currentUser);
		
		try {
			t.begin();
			em.persist(ingredient); // add the entity to the DB
			t.commit();
		}finally{
			// rollback if not commit
			if (t.isActive()) t.rollback(); 
			// close EM
			em.close();
		}

		response.sendRedirect("/RecipeManager/listMyIngredient");
	}
	

	
	
	@Override
	public void destroy() {
		this.emf.close();//close the emf
		super.destroy();
	}
	

}
