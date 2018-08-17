package fr.imie.recipemanager.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.imie.recipemanager.entity.Recipe;
import fr.imie.recipemanager.entity.User;



@WebServlet("/listMyRecipe")
public class ListMyRecipeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private EntityManagerFactory emf;
	
	@Override
	public void init() throws ServletException {
		this.emf = Persistence.createEntityManagerFactory("RMPU"); // Get the Recipe Manager Persistence Unit
		super.init();
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Set standard HTTP/1.1 no-cache headers.
		response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
		// Set standard HTTP/1.0 no-cache header.
		response.setHeader("Pragma", "no-cache");
		
		EntityManager em = emf.createEntityManager();
		
		TypedQuery<Recipe> q = em.createQuery("SELECT r FROM Recipe r WHERE owner=?1",Recipe.class);
		User currentUser = em.find(User.class, request.getSession().getAttribute("userId") );
		q.setParameter(1, currentUser ); 
		
		List<Recipe> recipes = q.getResultList();
		
		//pass the recipe to the jsp
		request.setAttribute("recipes", recipes);
		request.setAttribute("nbRec",recipes.size());
		
		request.getRequestDispatcher("/listRecipe.jsp").forward(request, response);
	}

	@Override
	public void destroy() {
		this.emf.close();
		super.destroy();
	}
}
