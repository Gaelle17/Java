package fr.imie.recipemanager.servlet;

import java.io.IOException;
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

import fr.imie.recipemanager.entity.Ingredient;
import fr.imie.recipemanager.entity.User;



@WebServlet("/listMyIngredient")
public class ListMyIngredient extends HttpServlet {

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
		
		TypedQuery<Ingredient> q = em.createQuery("SELECT i FROM Ingredient i WHERE owner=?1",Ingredient.class);
		User currentUser = em.find(User.class, request.getSession().getAttribute("userId") );
		q.setParameter(1, currentUser ); 
		
		List<Ingredient> ingredients = q.getResultList();
		
		//pass the recipe to the jsp
		request.setAttribute("ingredients", ingredients);
		request.setAttribute("nbIng",ingredients.size());
		
		request.getRequestDispatcher("/listIngredient.jsp").forward(request, response);
	}

	@Override
	public void destroy() {
		this.emf.close();
		super.destroy();
	}
}
