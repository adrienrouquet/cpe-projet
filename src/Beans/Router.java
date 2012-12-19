package Beans;

import javax.servlet.http.HttpSession;
import Beans.*;


public class Router {
	
	private HttpSession _session = null;
	private User _user = null;
	
	public Router(){};

	public HttpSession getSession()
	{
		return this._session;
	}
	public void setSession(HttpSession session)
	{
		this._session = session;
	}

	public void Load(int id, String login, String password)
	{
		User user = (User) this._session.getAttribute("userBean");
		
		if (user == null)
		{
			new User(id, login, password);
			this._session.setAttribute("userBean", user);
		}
		
		
		
		System.out.println("User loaded:");
		System.out.println("Id: " + user.getId());
		System.out.println("Login: " + user.getLogin());
//		System.out.println("Password: " + user.getpassword());
	}
	
}
