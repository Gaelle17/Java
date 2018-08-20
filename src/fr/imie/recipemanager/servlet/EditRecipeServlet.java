package fr.imie.recipemanager.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.imie.recipemanager.entity.Recipe;
import fr.imie.recipemanager.entity.User;



@WebServlet("/editRecipe")
public class EditRecipeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private EntityManagerFactory emf;
	
	@Override
	public void init() throws ServletException {
		this.emf = Persistence.createEntityManagerFactory("RMPU"); // Get the Recipe Manager Persistence Unit
		super.init();
	}
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("id") == null) {
			response.sendRedirect("listRecipe");
			return; 
		}

		// Set standard HTTP/1.1 no-cache headers.
		response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
		// Set standard HTTP/1.0 no-cache header.
		response.setHeader("Pragma", "no-cache");
		
		
		
		EntityManager em = emf.createEntityManager();
		Recipe recipe = em.find( Recipe.class, Long.parseLong( request.getParameter("id") ) );
		
		if(recipe == null) {
			response.sendRedirect("listRecipe");
			return ;
		}
		
		String name = recipe.getName();
		//System.out.println(name+" fully loaded");

		User currentUser = em.find( User.class, request.getSession().getAttribute("userId") );
				
		//pass the recipe to the jsp
		request.setAttribute("recipe", recipe);
		request.setAttribute("user", currentUser);
		
		request.getRequestDispatcher("/editRecipe.jsp").forward(request, response);
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Disable Cache For HTTP 1.1 & 1.0
		response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		
		EntityManager em = emf.createEntityManager();
		
		//prepare the entity
		Recipe recipe = em.find( Recipe.class, Long.parseLong( request.getParameter("id") ) );
		recipe.setName(request.getParameter("name"));
		recipe.setDescription(request.getParameter("description"));
		recipe.setDifficultyLevel(Float.parseFloat(request.getParameter("difficultyLevel")));
		recipe.setPreparationTime(Long.parseLong(request.getParameter("preparationTime")));
		
		//create the entity manager & transaction
		EntityTransaction t = em.getTransaction();
		
		try {
			t.begin();
			em.persist(recipe); // add the entity to the DB
			t.commit();
		}finally{
			// rollback if not commit
			if (t.isActive()) t.rollback(); 
			// close EM
			em.close();
		}

		response.sendRedirect("/RecipeManager/listRecipe");
	}
	
	
	
	@Override
	public void destroy() {
		this.emf.close();//close the emf
		super.destroy();
	}
	
}
