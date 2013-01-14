package Bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.*;

import AppCore.User;
import Manager.MsgManager;


public class UserBean implements Serializable {

	private static final long serialVersionUID = 5313606931922030655L;
	private User _user = null;
	private MsgManager _msgManager = null;
	
	public UserBean () {
		_msgManager = new MsgManager();
	};
	
	
	public User getUser() {
		if (this._user == null) {
			this._user = new User();
		}
		
		return _user;
	}
		
	public void setUser(User user) {
		this._user = user;
		this._msgManager.setSrcUserId(this._user.getId());
	}

	public int getApprovalStatus()
	{
		return this.getUser().getApprovalStatus();
	}
	public void setApprovalStatus(int approvalStatus)
	{
		this.getUser().setApprovalStatus(approvalStatus);
	}	
	
	public ArrayList<User> getContacts() {
		return getUser().getContacts();
	}
	
	public ArrayList<User> getContactRequests() {
		return getUser().getContactRequests();
	}
	
	public int getContactRequestsCount()
	{
		return getUser().getContactRequestsCount();
	}
	
	public MsgManager getMsgManager() {
		if (this._msgManager == null) {
			this._msgManager = new MsgManager();
			if (this.getUser().getId() != 0)
				this._msgManager.setSrcUserId(this.getUser().getId());
		}
		return this._msgManager;
	}
	
	public void setId ( int id )
	{
		this.getUser().setId(id);
		this._msgManager.setSrcUserId(id);
	}
	public int getId ( ) {
		return this.getUser().getId();
	}
	public void setLogin ( String login ) {
		this.getUser().setLogin(login);
	}
	public String getLogin ( ) {
		return this.getUser().getLogin();
	}
	public String getEmail ( ) {
		return this.getUser().getEmail();
	}
	public void setEmail ( String email ) {
		this.getUser().setEmail(email);
	}
	public String getPhone() {
		return this.getUser().getPhone();
	}
	public String getFirstName() {
		return this.getUser().getFirstName();
	}

	public void setFirstName(String firstName) {
		this.getUser().setFirstName(firstName);
	}

	public String getLastName() {
		return this.getUser().getLastName();
	}

	public void setLastName(String lastName) {
		this.getUser().setLastName(lastName);
	}

	public String getName() {
		return getUser().getName();
	}
	
	public Timestamp getLastLoginDate() {
		return this.getUser().getLastLoginDate();
	}

	public void setLastLoginDate(Timestamp lastLoginDate) {
		this.getUser().setLastLoginDate(lastLoginDate);
	}

	public void setPhone(String phone) {
		this.getUser().setPhone(phone);
	}
	public void setIsConnected ( boolean isConnected ) {
		this.getUser().setIsConnected(isConnected);
	}
	public boolean getIsConnected () {
		return this.getUser().getIsConnected();
	}
}
