package Bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.*;

import Class.User;
import Manager.MsgManager;


/**
 * Class User
 */
public class UserBean implements Serializable {

	private static final long serialVersionUID = 5313606931922030655L;
	private User _user = null;
	private MsgManager _msgManager = null;
	
	public UserBean () {
//		_user = new User();
		_msgManager = new MsgManager();
	};
	
//	public UserBean (int id, String login, String password)
//	{ 
//		_user = new User(id,login,password);
//	};
//	
//	public UserBean (int id, String login, String email, String phone, String firstName, String lastName, Timestamp lastLoginDate)
//	{ 
//		_user = new User(id,login,email,phone,firstName,lastName,lastLoginDate);
//	};
//
//	public UserBean (int id, String login, String password, String email, String phone, String firstName, String lastName, Timestamp lastLoginDate)
//	{ 
//		_user = new User(id,login,password,email,phone,firstName,lastName,lastLoginDate);
//	};
	
	public User getUser() {
		return _user;
	}
		
	public void setUser(User user) {
		this._user = user;
		this._msgManager.setSrcUserId(this._user.getId());
	}

	public Boolean getApprovalStatus()
	{
		return this._user.getApprovalStatus();
	}
	public void setApprovalStatus(Boolean approvalStatus)
	{
		this._user.setApprovalStatus(approvalStatus);
	}	
	
	public ArrayList<User> getContacts() {
		return _user.getContacts();
	}
	
	public ArrayList<User> getContactRequests() {
		return _user.getContactRequests();
	}
	
	public int getContactRequestsCount()
	{
		return _user.getContactRequestsCount();
	}
	
	public MsgManager getMsgManager() {
		return this._msgManager;
	}
	
	public void setId ( int id )
	{
		this._user.setId(id);
		this._msgManager.setSrcUserId(id);
	}
	public int getId ( ) {
		return this._user.getId();
	}
	public void setLogin ( String login ) {
		this._user.setLogin(login);
	}
	public String getLogin ( ) {
		return this._user.getLogin();
	}
	public String getEmail ( ) {
		return this._user.getEmail();
	}
	public void setEmail ( String email ) {
		this._user.setEmail(email);
	}
	public String getPhone() {
		return this._user.getPhone();
	}
	public String getFirstName() {
		return this._user.getFirstName();
	}

	public void setFirstName(String firstName) {
		this._user.setFirstName(firstName);
	}

	public String getLastName() {
		return this._user.getLastName();
	}

	public void setLastName(String lastName) {
		this._user.setLastName(lastName);
	}

	public String getName() {
		return _user.getName();
	}
	
	public Timestamp getLastLoginDate() {
		return this._user.getLastLoginDate();
	}

	public void setLastLoginDate(Timestamp lastLoginDate) {
		this._user.setLastLoginDate(lastLoginDate);
	}

	public void setPhone(String phone) {
		this._user.setPhone(phone);
	}
	public void setIsConnected ( boolean isConnected ) {
		this._user.setIsConnected(isConnected);
	}
	public boolean getIsConnected () {
		return this._user.getIsConnected();
	}
}
