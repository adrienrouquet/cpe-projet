package Beans;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import Beans.*;


public class ChatRouter {
	
	private String _url = "";
	private Integer _contactId = 0;
	
	public ChatRouter(){};

	public void setUrl(String url)
	{
		this._url = url;
	}
	public String getUrl()
	{
		return this._url;
	}

	public Integer getContactId()
	{
		return this._contactId;
	}
	public void setContactId(Integer contactId)
	{
		this._contactId = contactId;
	}
	
}
