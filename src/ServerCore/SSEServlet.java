package ServerCore;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Class.SSE;

/**
 * Servlet implementation class SSEServlet
 */
@WebServlet("/SSEServlet")
public class SSEServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SSEServlet() {
        super();
        
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    try {
	        response.setContentType("text/event-stream; charset=utf-8;");
	
	        PrintWriter out = response.getWriter();  

	        Bean.User user = (Bean.User) request.getSession().getAttribute("userBean");
	        //On publie ces infos de type event-stream sur la servlet a intervalles reguliers
	        out.print("retry: 5000\n");
	        out.print("event: newMessageReceived\n");
	        out.print("data: " + SSE.newMessagesReceivedToJSON(user.getId()) + "\n\n");
	        out.print("retry: 5000\n");
	        out.print("event: messageDelivered\n");
	        out.print("data: " + SSE.messagesDeliveredToJSON(user.getId()) + "\n\n");
	        
	        //On n'oublie pas de flush et close. Obligatoire
	        out.flush();
	        out.close();
//	        
	        //Si on a fait un ajout, c'est bien de supprimer les entrees pour le user afin de vider la hashtable
	        SSE.deleteUserMessagesDelivered(user.getId());
	        SSE.deleteUserNewMessagesReceived(user.getId());
	      }
	      catch (IOException e) {
	        e.printStackTrace();
	      }
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	

}
