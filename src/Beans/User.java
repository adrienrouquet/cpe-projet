package Beans;

import java.util.*;


/**
 * Class User
 */
public class User {

  private int _id = 0;
  private String _login = "null";
  private String _password = "null";
  private boolean _isConnected = false;
  private int _prout;
  
  public User () { };
  
  public User (int id, String login, String password)
  { 
	  _id = id;
	  _login = login;
	  _password = password;
  };
  
  public void setId ( int newVar )
  {
    _id = newVar;
  }
  public int getId ( ) {
    return _id;
  }

  public void setLogin ( String newVar ) {
    _login = newVar;
  }
  public String getLogin ( ) {
    return _login;
  }

  public void setIsConnected ( boolean newVar ) {
    _isConnected = newVar;
  }
  public boolean getIsConnected ( ) {
    return _isConnected;
  }

  public void setProut ( int newVar ) {
    _prout = newVar;
  }
  public int getProut ( ) {
    return _prout;
  }

  public boolean Send( Msg msg )
  {
	  return false;
  }
}
