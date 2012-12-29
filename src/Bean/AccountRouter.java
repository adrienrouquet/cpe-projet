package Bean;

import java.io.Serializable;


public class AccountRouter implements Serializable {

	private static final long serialVersionUID = 228260554262267396L;
	private String _url = "";
	
	public AccountRouter(){};

	public void setUrl(String url)
	{
		this._url = url;
	}
	public String getUrl()
	{
		return this._url;
	}
}
