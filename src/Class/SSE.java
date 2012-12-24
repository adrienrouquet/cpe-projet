package Class;

import java.util.ArrayList;
import java.util.Hashtable;

//Cette classe est abstraite et gere les actions des server sent events pour les differentes notifications de l'application
public abstract class SSE {

	private static Hashtable<Integer,ArrayList<Integer>> _messagesDelivered = new Hashtable<Integer,ArrayList<Integer>>();
	private static Hashtable<Integer,ArrayList<Integer>> _newMessagesReceived = new Hashtable<Integer,ArrayList<Integer>>();
	
	//Un utilisateur srcUserId a envoye un message a dstUserId. Cette methode va envoyer une notification a dstUserId
	public static void setNewMessageReceived(int srcUserId, int dstUserId)
	{
		ArrayList<Integer> userNewMessagesReceived = null;
		
		if( _newMessagesReceived.containsKey(dstUserId) )
		{
			userNewMessagesReceived = _newMessagesReceived.get(dstUserId);
			userNewMessagesReceived.add(srcUserId);
		}
		else
		{
			userNewMessagesReceived = new ArrayList<Integer>();
			userNewMessagesReceived.add(srcUserId);
		}
		_newMessagesReceived.put(dstUserId, userNewMessagesReceived);
	}
	
	//Quand on a publie la notification, on la supprime de la hashtable
	public static void deleteUserNewMessagesReceived(int userId)
	{
		_newMessagesReceived.remove(userId);
	}
	
	//La conversion en JSON de la hashtable pour le user ID demande
	public static String newMessagesReceivedToJSON(int userId)
	{
		String output = "";
		output +="{\"srcUserId\":";
		if(_newMessagesReceived.containsKey(userId))
		{
			output +=" [";
			int i=0;
			for( Integer srcUserId : _newMessagesReceived.get(userId))
			{
				if(i > 0)
					output += ",";
				
				output += srcUserId;
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
	
	//Un utilisateur vient de se connecter et vient de lire le msg msgId envoye par userId. Cette methode va envoyer une notification a userId
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
	
	//Quand on a publie la notification, on la supprime de la hashtable
	public static void deleteUserMessagesDelivered(int userId)
	{
		_messagesDelivered.remove(userId);
	}
	
	//La conversion en JSON de la hashtable pour le user ID demande
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
