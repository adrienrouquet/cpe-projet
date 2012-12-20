package ServerCore;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import TestEnvSetup.DBReset;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() throws SQLException {
        super();
        DBReset.resetDatabase();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("LoginServlet: Entering doGet");
		
		
		
		RequestDispatcher rd = request.getRequestDispatcher("content/login.html");
		rd.forward(request, response);
		
		HttpSession s = request.getSession(false);
		
		if (s == null)
		{
			System.out.println("No active session");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("LoginServlet: Entering doPost");
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		
		if ((login != null) && (password != null))
		{
			if (CredentialsMatch(login,password))
			{	
				request.setAttribute("userId", "12");
				RequestDispatcher rd = request.getRequestDispatcher("AppServlet");
				rd.forward(request, response);
			}
		}
		else
		{
			System.out.println("login or password NULL");
		}
	}
	
	private boolean CredentialsMatch(String login, String password)
	{
		return true;
	}

}
