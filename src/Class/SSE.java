package Class;

import java.util.ArrayList;
import java.util.Hashtable;

public abstract class SSE {

	private static Hashtable<Integer,ArrayList<Integer>> _messagesDelivered = new Hashtable<Integer,ArrayList<Integer>>();
	
	public static void setMessageDelivered(int userId, int msgId)
	{
		ArrayList<Integer> userMessagesDelivered = null;
		
		if( _messagesDelivered.containsKey(userId) )
		{
			userMessagesDelivered = _messagesDelivered.get(userId);
			userMessagesDelivered.add(msgId);
		}
		else
		{
			userMessagesDelivered = new ArrayList<Integer>();
			userMessagesDelivered.add(msgId);
		}
		_messagesDelivered.put(userId, userMessagesDelivered);
	}
	
	
	public static void deleteUserMessagesDelivered(int userId)
	{
		_messagesDelivered.remove(userId);
	}
	
	public static String messagesDeliveredToJSON(int userId)
	{
		String output = "";
		output +="{\"msgId\":"; 
		if(_messagesDelivered.containsKey(userId))
		{
			output +=" [";
			int i=0;
			for( Integer msgId : _messagesDelivered.get(userId))
			{
				if(i > 0)
					output += ",";
				
				output += msgId;
				i++;
			}
			output += "]";
		}
		else
		{
			output += "\"\"";
		}
		
		output += "}";
		return output;
	}
}
