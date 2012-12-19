package ServerCore;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AppServlet")
public class AppServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AppServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("AppServlet: Entering doGet");
		RequestDispatcher rd = request.getRequestDispatcher("content/contacts.html");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("AppServlet: Entering doPost");
		
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		
		System.out.println("Login: " + login);
		System.out.println("Password: " + password);
		
		RequestDispatcher rd = request.getRequestDispatcher("content/contacts.html");
		rd.forward(request, response);
	}

}
