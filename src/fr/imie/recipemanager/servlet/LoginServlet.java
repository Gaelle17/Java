package fr.imie.recipemanager.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

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
		String username = req.getParameter("username");
		String pwd = req.getParameter("pwd");
		req.getSession().setAttribute("username", username);
		req.getSession().setAttribute("pwd", pwd);
		resp.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
		resp.setHeader("Pragma", "no-cache");
		resp.sendRedirect("/RecipeManager/listRecipe");
	}
}