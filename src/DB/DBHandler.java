package DB;


/**
 * Class DBHandler
 */
public class DBHandler {

	  //
	  // Fields
	  //
	  private String _dbName = null;
	  
	  //
	  // Constructors
	  //
	  public DBHandler (String dbName)
	  {
		  _dbName = dbName;
	  }
	
	  
	  //
	  // Methods
	  //
	  protected boolean Connect()
	  {
		  return false;
	  }
	  
	  protected boolean Disconnect()
	  {
		  return false;
	  }
	  
	  protected boolean Create()
	  {
		  return false;
	  }
	  
	  protected boolean Delete()
	  {
		  return false;
	  }
	
	  protected boolean Update()
	  {
		  return false;
	  }
	  //
	  // Accessor methods
	  //
	
	  //
	  // Other methods
	  //

}
