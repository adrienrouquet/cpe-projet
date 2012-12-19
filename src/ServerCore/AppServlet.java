package ServerCore;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Beans.*;

@WebServlet("/AppServlet")
public class AppServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private Router _router = null;
	
    public AppServlet() {
        super();
        
        this._router = new Router();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("AppServlet: Entering doGet");
		RequestDispatcher rd = request.getRequestDispatcher("content/contacts.html");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		ServletContext context = getServletContext();
		context.setAttribute("routerBean", this._router);

		this._router.setSession(request.getSession(true));
		
		int id = Integer.parseInt( (String) request.getAttribute("userId") );
		String login = (String) request.getParameter("login");
		String password = (String) request.getParameter("password");
		
		this._router.Load(id, login, password);
		
//		
//		
//		System.out.println(session.getAttribute("routerBean"));
		
		RequestDispatcher rd = request.getRequestDispatcher("content/contacts.html");
		rd.forward(request, response);
	}
	
	
}
