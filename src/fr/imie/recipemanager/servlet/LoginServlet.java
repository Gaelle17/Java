package fr.imie.recipemanager.servlet;

import java.io.IOException;
import java.util.function.Predicate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import fr.imie.recipemanager.entity.Recipe;
import fr.imie.recipemanager.entity.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private EntityManagerFactory emf;
	
	
	
	
	@Override
	public void init() throws ServletException {
		this.emf = Persistence.createEntityManagerFactory("RMPU"); // Get the Recipe Manager Persistence Unit
		super.init();
	}
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Set standard HTTP/1.1 no-cache headers.
		resp.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
		// Set standard HTTP/1.0 no-cache header.
		resp.setHeader("Pragma", "no-cache");
		req.getRequestDispatcher("/login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// get http parameters
		String username = req.getParameter("username");
		String pwd = req.getParameter("pwd");
		
		EntityManager em = emf.createEntityManager();

		TypedQuery<User> q = em.createQuery("SELECT u FROM User u WHERE username=?1",User.class);
		q.setParameter(1, username); 
		User result;
		try {
			result = q.getSingleResult();
	    } catch (final NoResultException nre) {
	    	result = this.signIn(username, pwd);
	    }
		
		// no cahe
		resp.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
		resp.setHeader("Pragma", "no-cache");

		if (result.verifyPwd(pwd)) {
			System.out.println("Connected as User #"+result.getId()+".");
			req.getSession().setAttribute("userId",result.getId());
			
			//Redirect When Done
			resp.sendRedirect("/RecipeManager/listRecipe");
		} else {
			System.out.println("Wrong password");
		}
		
		
		
	}
	
	protected User signIn(String username,String password) {
		//prepare the entity
				User user = new User();
				user.setPwd(password);
				user.setUsername(username);
				
				//create the entity manager & transaction
				EntityManager em = emf.createEntityManager();
				EntityTransaction t = em.getTransaction();
				
				try {
					t.begin();
					em.persist(user); // add the entity to the DB
					t.commit();
				}finally{
					// rollback if not commit
					if (t.isActive()) t.rollback(); 
					// close EM
					em.close();
				}
				
				return user;
	}
	
	
	@Override
	public void destroy() {
		this.emf.close();//close the emf
		super.destroy();
	}
	
	
}