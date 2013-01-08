package ServerCore;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Class.User;
import DB.DBUserToolbox;


@WebServlet("/AccountServlet")
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AccountServlet() throws SQLException {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		System.out.println("AccountServlet: Entering doGet");
		
		accountRouting(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		System.out.println("AccountServlet: Entering doPost");
		
		accountRouting(req,res);
	}
	
	protected void accountRouting(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		HttpSession session 	= req.getSession(true);
		RequestDispatcher rd 	= null;
		
		//On recupere le accountRouterBean de la session
		Bean.Router ar 	= (Bean.Router) session.getAttribute("accountRouterBean");
		if(ar == null)
		{
			System.out.println("Warning: accountRouterBean is null in AccountServlet");
    		ar = new Bean.Router();
			session.setAttribute("accountRouterBean", ar);
		}
		
		//On recupere le UserBean de la session
		Bean.UserBean userBean 			= (Bean.UserBean) session.getAttribute("userBean");		
		if(userBean == null)
		{
			System.out.println("Warning: userBean is null in AccountServlet");
			userBean	=  new Bean.UserBean();
			session.setAttribute("userBean", userBean);
		}
		
		if(!userBean.getIsConnected())
		{	
			System.out.println("User disconnected/No active session");
		}
		else
		{
			System.out.println("User exists and is already connected");
		}
		
		if(req.getParameter("action") != null)
		{
			switch(req.getParameter("action"))
			{
				case "login":
				{
					System.out.println("AccountServlet: Checking User credentials");
						
					String login 		= req.getParameter("login").trim().toLowerCase();
					String password 	= req.getParameter("password").trim();
					DBUserToolbox dbut 	= new DBUserToolbox();
					
					if (dbut.checkCredentials(login,password))
					{
						System.out.println("AccountServlet: Connecting user");
						//On recupere le user de la database et on le set dans un bean session
						//On set au passage le chatRouterBean en session aussi
						
						User user = dbut.getUser(login); 
						user.setIsConnected(true);
						userBean.setUser(user);
						session.setAttribute("userBean", userBean);
						session.setAttribute("chatRouterBean", new Bean.Router());
						System.out.println("AccountServlet: UserId " + userBean.getId() + " is now connected");
						
//							rd = req.getRequestDispatcher("ChatServlet");
						res.sendRedirect("ChatServlet");
					}
					else
					{
						System.out.println("AccountServlet: Wrong credentials");
						//Par defaut, on forward sur accountLogin.jsp
						ar.setUrl("accountLogin.jsp");
						rd = req.getRequestDispatcher("content/account/account.jsp");
						rd.forward(req, res);
					}	
				}break;
				case "logout":
				{
					System.out.println("AccountServlet: Logout action received");
					session.setAttribute("userBean", null);
					session.setAttribute("chatRouterBean", null);
					session.setAttribute("msgManagerBean", null);
					session.invalidate();
					req.getSession(true);
					res.sendRedirect("!");
				}break;
				case "subscribe":
				{
					ar.setUrl("accountSubscribe.jsp");
					rd = req.getRequestDispatcher("content/account/account.jsp");
					rd.forward(req, res);
				}break;
				case "submitSubscribe":
				{
					String firstName	= req.getParameter("firstName").trim().toLowerCase();
					String lastName		= req.getParameter("lastName").trim().toLowerCase();
					String login 		= req.getParameter("login").trim().toLowerCase();
					String email		= req.getParameter("email").trim().toLowerCase();
					String password 	= req.getParameter("password").trim();
					
					DBUserToolbox dbut 	= new DBUserToolbox();
					
					if(!dbut.userExists(login))
					{
						ar.setUrl("accountSubscribeOk.jsp");
					}
					else
					{
						ar.setUrl("accountSubscribe.jsp?error=userExists");						
					}
					rd = req.getRequestDispatcher("content/account/account.jsp");
					rd.forward(req, res);
				}break;
				
				default:
				{
					
				}break;				
			}
		}
		else
		{
			//Par defaut, on forward sur accountLogin.jsp
			ar.setUrl("accountLogin.jsp");
			rd = req.getRequestDispatcher("content/account/account.jsp");
			rd.forward(req, res);
		}
	}
}
