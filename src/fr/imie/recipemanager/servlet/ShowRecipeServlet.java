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



@WebServlet("/showRecipe")
public class ShowRecipeServlet extends HttpServlet {

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
		Recipe recipe = em.find( Recipe.class, Long.parseLong( request.getParameter("id") ) );
		if(recipe == null) { // Redirect user if the recipe doesn't exist
			response.sendRedirect("listRecipe");
		}else {
			String name = recipe.getName();
			//System.out.println(name+" fully loaded");
	
			User currentUser = em.find( User.class, request.getSession().getAttribute("userId") );
					
			//pass the recipe to the jsp
			request.setAttribute("recipe", recipe);
			request.setAttribute("user", currentUser);
			
			request.getRequestDispatcher("/showRecipe.jsp").forward(request, response);
		}
	}


}
