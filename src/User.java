
import java.util.*;


/**
 * Class User
 */
public class User {

  //
  // Fields
  //

  private int _id = 0;
  private String _login = "null";
  private boolean _isConnected = false;
  private int _prout;
  
  //
  // Constructors
  //
  public User () { };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of _id
   * @param newVar the new value of _id
   */
  private void set_id ( int newVar ) {
    _id = newVar;
  }

  /**
   * Get the value of _id
   * @return the value of _id
   */
  private int get_id ( ) {
    return _id;
  }

  /**
   * Set the value of _login
   * @param newVar the new value of _login
   */
  private void set_login ( String newVar ) {
    _login = newVar;
  }

  /**
   * Get the value of _login
   * @return the value of _login
   */
  private String get_login ( ) {
    return _login;
  }

  /**
   * Set the value of _isConnected
   * @param newVar the new value of _isConnected
   */
  private void set_isConnected ( boolean newVar ) {
    _isConnected = newVar;
  }

  /**
   * Get the value of _isConnected
   * @return the value of _isConnected
   */
  private boolean get_isConnected ( ) {
    return _isConnected;
  }

  /**
   * Set the value of _prout
   * @param newVar the new value of _prout
   */
  private void set_prout ( int newVar ) {
    _prout = newVar;
  }

  /**
   * Get the value of _prout
   * @return the value of _prout
   */
  private int get_prout ( ) {
    return _prout;
  }

  //
  // Other methods
  //

  /**
   * @return       boolean
   * @param        msg
   */
  public boolean Send( Msg msg )
  {
	  return false;
  }


  

}
