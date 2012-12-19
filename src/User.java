
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
  
  public void set_id ( int newVar )
  {
    _id = newVar;
  }

  public int get_id ( ) {
    return _id;
  }

  public void set_login ( String newVar ) {
    _login = newVar;
  }

  public String get_login ( ) {
    return _login;
  }

  public void set_isConnected ( boolean newVar ) {
    _isConnected = newVar;
  }

  public boolean get_isConnected ( ) {
    return _isConnected;
  }

  public void set_prout ( int newVar ) {
    _prout = newVar;
  }

  public int get_prout ( ) {
    return _prout;
  }

  public boolean Send( Msg msg )
  {
	  return false;
  }
}
