package Bean;

import java.io.Serializable;


public class ChatRouter implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6734150431891723390L;
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
