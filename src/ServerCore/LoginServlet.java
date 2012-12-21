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
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		System.out.println("LoginServlet: Entering doGet");
		
		loginRouting(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		System.out.println("LoginServlet: Entering doPost");
		
		loginRouting(req,res);
	}
	
	protected void loginRouting(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		HttpSession session 	= req.getSession(true);
		RequestDispatcher rd 	= null;
		
		//On recupere le UserBean de la session
		Bean.User user 			= (Bean.User) session.getAttribute("userBean");
		
		if(user != null)
		{
			if(!user.getIsConnected())
			{	
				System.out.println("User disconnected/No active session");
				rd = req.getRequestDispatcher("content/account/account.jsp");
			}
			else
			{
				System.out.println("User exists and is already connected");
				//Si on est deja connecte, on passe sur le ChatServlet
				rd = req.getRequestDispatcher("ChatServlet");
			}
		}
		//Si le bean user n'etait pas set, c'est qu'on cherche a se connecter!
		else
		{
			if(req.getParameter("action") != null && req.getParameter("login") != null && req.getParameter("password") != null)
			{
				System.out.println("LoginServlet: Checking User credentials");
				
				String login 		= req.getParameter("login").trim().toLowerCase();
				String password 	= req.getParameter("password").trim();
				DBUserToolbox dbut 	= new DBUserToolbox();
				
				if (dbut.checkCredentials(login,password))
				{
					System.out.println("LoginServlet: Connecting user");
					//On recupere le user de la database et on le set dans un bean session
					//On set au passage le chatRouterBean en session aussi
					
					user = dbut.getUser(login); 
					user.setIsConnected(true);
					session.setAttribute("userBean", user);
					session.setAttribute("chatRouterBean", new Bean.ChatRouter());
					
					rd = req.getRequestDispatcher("ChatServlet");
				}
				else
				{
					System.out.println("LoginServlet: Wrong credentials");
					//Par defaut, on forward sur account.jsp
					rd = req.getRequestDispatcher("content/account/account.jsp");
					
				}
			}
			else
			{
				//Par defaut, on forward sur account.jsp
				rd = req.getRequestDispatcher("content/account/account.jsp");
			}
		}
		rd.forward(req, res);
	}
}
