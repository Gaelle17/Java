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



@WebServlet("/listRecipe")
public class ListRecipeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private EntityManagerFactory emf;
	
	@Override
	public void init() throws ServletException {
		this.emf = Persistence.createEntityManagerFactory("RMPU");
		super.init();
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Set standard HTTP/1.1 no-cache headers.
		response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
		// Set standard HTTP/1.0 no-cache header.
		response.setHeader("Pragma", "no-cache");
		

		EntityManager em = emf.createEntityManager();
		TypedQuery<Recipe> q = em.createQuery("SELECT r FROM Recipe r",Recipe.class);
		List<Recipe> recipes = q.getResultList();
		em.close();
		
		// create fake recipe for test purpose
		//ArrayList<Recipe> recipes = new ArrayList<Recipe>();
		//Recipe r = new Recipe();
		//r.setName("confiture de rose");
		//recipes.add(r);
		
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
