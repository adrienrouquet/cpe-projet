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
import DB.DBUserToolbox;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() throws SQLException {
        super();
    
        DBReset.resetDatabase();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		System.out.println("LoginServlet: Entering doGet");
		
		
		
		RequestDispatcher rd = req.getRequestDispatcher("content/login/login.jsp");
		rd.forward(req, res);
		
		HttpSession s = req.getSession(false);
		
		if (s == null)
		{
			System.out.println("No active session");
		}
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		System.out.println("LoginServlet: Entering doPost");
		
		String login = req.getParameter("login");
		String password = req.getParameter("password");
		HttpSession session = req.getSession(true);
		
		Beans.User user = (Beans.User) session.getAttribute("userBean");
		
		if(user != null)
		{
			if(!user.getIsConnected())
				System.out.println("User disconnected");
			else
			{
				System.out.println("User exists and is connected");
				RequestDispatcher rd = req.getRequestDispatcher("ChatServlet");
				rd.forward(req, res);
			}
		}
		
		else
		{
			if (CredentialsMatch(login,password))
			{
				user = new Beans.User();
				user.setId(2); //A CHANGER DANS LE FUTUR!!!!
				user.setIsConnected(true);
				
				session.setAttribute("userBean", user);
				session.setAttribute("chatRouterBean", new Beans.ChatRouter());
				
				RequestDispatcher rd = req.getRequestDispatcher("ChatServlet");
				rd.forward(req, res);
			}
		}
	}
	
	private boolean CredentialsMatch(String login, String password)
	{
		boolean match = true;
				
		//DBUserToolbox utb = new DBUserToolbox(); 
		
		
		
		return match;
	}

	
}
