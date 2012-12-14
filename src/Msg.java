
import java.util.*;


/**
 * Class Msg
 */
public class Msg {

  //
  // Fields
  //

  private String _text = "null";
  private int _senderId = 0;
  private int _receiverId = 0;
  
  //
  // Constructors
  //
  public Msg () { };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of _text
   * @param newVar the new value of _text
   */
  private void set_text ( String newVar ) {
    _text = newVar;
  }

  /**
   * Get the value of _text
   * @return the value of _text
   */
  private String get_text ( ) {
    return _text;
  }

  /**
   * Set the value of _senderId
   * @param newVar the new value of _senderId
   */
  private void set_senderId ( int newVar ) {
    _senderId = newVar;
  }

  /**
   * Get the value of _senderId
   * @return the value of _senderId
   */
  private int get_senderId ( ) {
    return _senderId;
  }

  /**
   * Set the value of _receiverId
   * @param newVar the new value of _receiverId
   */
  private void set_receiverId ( int newVar ) {
    _receiverId = newVar;
  }

  /**
   * Get the value of _receiverId
   * @return the value of _receiverId
   */
  private int get_receiverId ( ) {
    return _receiverId;
  }

  //
  // Other methods
  //

}
